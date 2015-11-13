package br.com.gamemods.bukkitmod.server;

import br.com.gamemods.bukkitmod.mod.BukkitMod;
import com.avaje.ebean.config.ServerConfig;
import net.minecraft.server.MinecraftServer;
import org.bukkit.*;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.*;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.CachedServerIcon;

import javax.annotation.Nonnull;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.logging.Logger;

public class BukkitServer implements Server
{
    @Nonnull
    private final BukkitMod mod;
    @Nonnull
    private final MinecraftServer server;

    public BukkitServer(@Nonnull BukkitMod mod, @Nonnull MinecraftServer server)
    {
        this.mod = mod;
        this.server = server;
    }

    @Override
    public String getName()
    {
        return "BukkitMod";
    }

    @Override
    public String getVersion()
    {
        return "1.7.10-R1-SNAPSHOT";
    }

    @Override
    public String getBukkitVersion()
    {
        return "1.7.10-R1-SNAPSHOT";
    }

    @Override
    public Player[] _INVALID_getOnlinePlayers()
    {
        return new Player[0];
    }

    @Override
    public Collection<? extends Player> getOnlinePlayers()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getMaxPlayers()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getPort()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getViewDistance()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getIp()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getServerName()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getServerId()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getWorldType()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getGenerateStructures()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getAllowEnd()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getAllowNether()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasWhitelist()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setWhitelist(boolean b)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<OfflinePlayer> getWhitelistedPlayers()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void reloadWhitelist()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int broadcastMessage(String s)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getUpdateFolder()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public File getUpdateFolderFile()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getConnectionThrottle()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getTicksPerAnimalSpawns()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getTicksPerMonsterSpawns()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Player getPlayer(String s)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Player getPlayerExact(String s)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Player> matchPlayer(String s)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Player getPlayer(UUID uuid)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public PluginManager getPluginManager()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public BukkitScheduler getScheduler()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public ServicesManager getServicesManager()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<World> getWorlds()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public World createWorld(WorldCreator worldCreator)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean unloadWorld(String s, boolean b)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean unloadWorld(World world, boolean b)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public World getWorld(String s)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public World getWorld(UUID uuid)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public MapView getMap(short i)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public MapView createMap(World world)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void reload()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Logger getLogger()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public PluginCommand getPluginCommand(String s)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void savePlayers()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean dispatchCommand(CommandSender commandSender, String s) throws CommandException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void configureDbConfig(ServerConfig serverConfig)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addRecipe(Recipe recipe)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Recipe> getRecipesFor(ItemStack itemStack)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Recipe> recipeIterator()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clearRecipes()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void resetRecipes()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, String[]> getCommandAliases()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getSpawnRadius()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setSpawnRadius(int i)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getOnlineMode()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getAllowFlight()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isHardcore()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean useExactLoginLocation()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void shutdown()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int broadcast(String s, String s1)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public OfflinePlayer getOfflinePlayer(String s)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public OfflinePlayer getOfflinePlayer(UUID uuid)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> getIPBans()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void banIP(String s)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unbanIP(String s)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<OfflinePlayer> getBannedPlayers()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public BanList getBanList(BanList.Type type)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<OfflinePlayer> getOperators()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public GameMode getDefaultGameMode()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setDefaultGameMode(GameMode gameMode)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public ConsoleCommandSender getConsoleSender()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public File getWorldContainer()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public OfflinePlayer[] getOfflinePlayers()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Messenger getMessenger()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public HelpMap getHelpMap()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Inventory createInventory(InventoryHolder inventoryHolder, InventoryType inventoryType)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Inventory createInventory(InventoryHolder inventoryHolder, InventoryType inventoryType, String s)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Inventory createInventory(InventoryHolder inventoryHolder, int i) throws IllegalArgumentException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Inventory createInventory(InventoryHolder inventoryHolder, int i, String s) throws IllegalArgumentException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getMonsterSpawnLimit()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAnimalSpawnLimit()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getWaterAnimalSpawnLimit()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAmbientSpawnLimit()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isPrimaryThread()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getMotd()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getShutdownMessage()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Warning.WarningState getWarningState()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public ItemFactory getItemFactory()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public ScoreboardManager getScoreboardManager()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public CachedServerIcon getServerIcon()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public CachedServerIcon loadServerIcon(File file) throws IllegalArgumentException, Exception
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public CachedServerIcon loadServerIcon(BufferedImage bufferedImage) throws IllegalArgumentException, Exception
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setIdleTimeout(int i)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getIdleTimeout()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public UnsafeValues getUnsafe()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendPluginMessage(Plugin plugin, String s, byte[] bytes)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> getListeningPluginChannels()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Server.Spigot spigot()
    {
        return new Spigot();
    }

    private class Spigot extends Server.Spigot
    {

    }
}
