package br.com.gamemods.bukkitmod.scheduler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitWorker;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;
import java.util.logging.Level;

public class PlugModScheduler implements BukkitScheduler
{
    private int lastId = -1;
    private Queue<PlugModTask<?>> queue = new ConcurrentLinkedQueue<>();
    private Map<Plugin, Collection<PlugModTask<?>>> pluginTasks = new HashMap<>();
    private Map<Integer, PlugModTask<?>> taskIds = new HashMap<>();
    protected List<PlugModTask.PlugModWorker> workers = new LinkedList<>();

    private synchronized int nextId()
    {
        return ++lastId;
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event)
    {
        if(event.phase != TickEvent.Phase.START)
            return;

        //System.out.println("ServerTick");
        for (PlugModTask<?> task : queue)
        {
            try
            {
                task.tick();
            } catch (Throwable e)
            {
                Bukkit.getLogger().log(Level.WARNING, "Failed to execute a bukkit task", e);
            }
        }
    }

    @Override
    public int scheduleSyncDelayedTask(Plugin plugin, Runnable task, long delay)
    {
        return runTaskLater(plugin, task, delay).getTaskId();
    }

    @Override
    public int scheduleSyncDelayedTask(Plugin plugin, BukkitRunnable task, long delay)
    {
        return runTaskLater(plugin, task, delay).getTaskId();
    }

    @Override
    public int scheduleSyncDelayedTask(Plugin plugin, Runnable task)
    {
        return runTask(plugin, task).getTaskId();
    }

    @Override
    public int scheduleSyncDelayedTask(Plugin plugin, BukkitRunnable task)
    {
        return runTask(plugin, task).getTaskId();
    }

    @Override
    public int scheduleSyncRepeatingTask(Plugin plugin, Runnable task, long delay, long period)
    {
        return runTaskTimer(plugin, task, delay, period).getTaskId();
    }

    @Override
    public int scheduleSyncRepeatingTask(Plugin plugin, BukkitRunnable task, long delay, long period)
    {
        return runTaskTimer(plugin, task, delay, period).getTaskId();
    }

    @Override
    public int scheduleAsyncDelayedTask(Plugin plugin, Runnable task, long delay)
    {
        return runTaskLaterAsynchronously(plugin, task, delay).getTaskId();
    }

    @Override
    public int scheduleAsyncDelayedTask(Plugin plugin, Runnable task)
    {
        return runTaskAsynchronously(plugin, task).getTaskId();
    }

    @Override
    public int scheduleAsyncRepeatingTask(Plugin plugin, Runnable task, long delay, long period)
    {
        return runTaskTimerAsynchronously(plugin, task, delay, period).getTaskId();
    }

    @Override
    public synchronized void cancelTask(int taskId)
    {
        PlugModTask<?> task = taskIds.get(taskId);
        if (task == null) return;
        cancelTask(task);
    }

    public synchronized void cancelTask(PlugModTask<?> task)
    {
        this.queue.remove(task);
        task.cancel(false);
        Collection<PlugModTask<?>> list = this.pluginTasks.get(task.getOwner());
        if (list != null)
            list.remove(task);
        this.taskIds.remove(task.getTaskId());
    }

    @Override
    public synchronized void cancelTasks(Plugin plugin)
    {
        Collection<PlugModTask<?>> tasks = this.pluginTasks.remove(plugin);
        if (tasks == null) return;
        this.queue.removeAll(tasks);
        for (PlugModTask<?> task : tasks)
        {
            this.taskIds.remove(task.getTaskId());
            task.cancel(true);
        }
    }

    @Override
    public synchronized void cancelAllTasks()
    {
        for (PlugModTask<?> task : this.queue)
            cancelTask(task);
    }

    @Override
    public boolean isCurrentlyRunning(int taskId)
    {
        PlugModTask<?> task = taskIds.get(taskId);
        if (task == null) return false;
        return task.isRunning();
    }

    @Override
    public boolean isQueued(int taskId)
    {
        PlugModTask<?> task = taskIds.get(taskId);
        if (task == null) return false;
        return queue.contains(task);
    }

    @Override
    public List<BukkitWorker> getActiveWorkers()
    {
        return new ArrayList<BukkitWorker>(workers);
    }

    @Override
    public List<BukkitTask> getPendingTasks()
    {
        return new ArrayList<BukkitTask>(queue);
    }

    @Override
    public BukkitTask runTask(Plugin plugin, Runnable task) throws IllegalArgumentException
    {
        return runTaskLater(plugin, task, 0);
    }

    @Override
    public BukkitTask runTask(Plugin plugin, BukkitRunnable task) throws IllegalArgumentException
    {
        return runTaskLater(plugin, task, 0);
    }

    @Override
    public BukkitTask runTaskAsynchronously(Plugin plugin, Runnable task) throws IllegalArgumentException
    {
        return runTaskLaterAsynchronously(plugin, task, 0);
    }

    @Override
    public BukkitTask runTaskAsynchronously(Plugin plugin, BukkitRunnable task) throws IllegalArgumentException
    {
        return runTaskLaterAsynchronously(plugin, task, 0);
    }

    private void register(PlugModTask<?> task)
    {
        Collection<PlugModTask<?>> collection = pluginTasks.get(task.getOwner());
        if (collection == null) pluginTasks.put(task.getOwner(), collection = new ArrayList<PlugModTask<?>>(1));
        collection.add(task);
        taskIds.put(task.getTaskId(), task);
        queue.add(task);
    }

    @Override
    public BukkitTask runTaskLater(Plugin plugin, Runnable task, long delay) throws IllegalArgumentException
    {
        return runTaskLater(plugin, new CallableRunnable(task), delay);
    }

    @Override
    public BukkitTask runTaskLater(Plugin plugin, BukkitRunnable task, long delay) throws IllegalArgumentException
    {
        return task.runTaskLater(plugin, delay);
    }

    @Override
    public BukkitTask runTaskLaterAsynchronously(Plugin plugin, Runnable task, long delay) throws IllegalArgumentException
    {
        return runTaskLaterAsynchronously(plugin, new CallableRunnable(task), delay);
    }

    @Override
    public BukkitTask runTaskLaterAsynchronously(Plugin plugin, BukkitRunnable task, long delay) throws IllegalArgumentException
    {
        return task.runTaskLaterAsynchronously(plugin, delay);
    }

    @Override
    public BukkitTask runTaskTimer(Plugin plugin, Runnable task, long delay, long period) throws IllegalArgumentException
    {
        return runTaskTimer(plugin, new CallableRunnable(task), delay, period);
    }

    @Override
    public BukkitTask runTaskTimer(Plugin plugin, BukkitRunnable task, long delay, long period) throws IllegalArgumentException
    {
        return task.runTaskTimer(plugin, delay, period);
    }

    @Override
    public BukkitTask runTaskTimerAsynchronously(Plugin plugin, Runnable task, long delay, long period) throws IllegalArgumentException
    {
        return runTaskTimerAsynchronously(plugin, new CallableRunnable(task), delay, period);
    }

    @Override
    public BukkitTask runTaskTimerAsynchronously(Plugin plugin, BukkitRunnable task, long delay, long period) throws IllegalArgumentException
    {
        return task.runTaskTimerAsynchronously(plugin, delay, period);
    }

    @Override
    public <T> Future<T> callSyncMethod(Plugin plugin, Callable<T> task)
    {
        return runTask(plugin, task);
    }

    public <V> FutureBukkitTask<V> runTask(Plugin plugin, Callable<V> task) throws IllegalArgumentException
    {
        return runTaskLater(plugin, task, 0);
    }

    public <V> FutureBukkitTask<V> runTaskAsynchronously(Plugin plugin, Callable<V> task) throws IllegalArgumentException
    {
        return runTaskLaterAsynchronously(plugin, task, 0);
    }

    public <V> FutureBukkitTask<V> runTaskLater(Plugin plugin, Callable<V> task, long delay) throws IllegalArgumentException
    {
        PlugModTask<V> bukkitTask = new PlugModTask<>(this, nextId(), plugin, true, task, delay, -1);
        register(bukkitTask);
        return bukkitTask;
    }

    public <V> FutureBukkitTask<V> runTaskLaterAsynchronously(Plugin plugin, Callable<V> task, long delay) throws IllegalArgumentException
    {
        PlugModTask<V> bukkitTask = new PlugModTask<>(this, nextId(), plugin, false, task, delay, -1);
        register(bukkitTask);
        return bukkitTask;
    }

    public <V> FutureBukkitTask<V> runTaskTimer(Plugin plugin, Callable<V> task, long delay, long period) throws IllegalArgumentException
    {
        PlugModTask<V> bukkitTask = new PlugModTask<>(this, nextId(), plugin, true, task, delay, period);
        register(bukkitTask);
        return bukkitTask;
    }

    public <V> FutureBukkitTask<V> runTaskTimerAsynchronously(Plugin plugin, Callable<V> task, long delay, long period) throws IllegalArgumentException
    {
        PlugModTask<V> bukkitTask = new PlugModTask<>(this, nextId(), plugin, false, task, delay, period);
        register(bukkitTask);
        return bukkitTask;
    }
}