package br.com.gamemods.bukkitmod.mod;

import br.com.gamemods.bukkitmod.scheduler.PlugModScheduler;
import br.com.gamemods.bukkitmod.server.PlugModServer;
import br.com.gamemods.bukkitmod.server.VolatileServer;
import com.google.common.base.Preconditions;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.Logger;
import org.bukkit.Bukkit;

@Mod(modid = PlugMod.MODID, name = PlugMod.MODID, version = PlugMod.VERSION)
public class PlugMod
{
    public static final String MODID = "PlugMod";
    public static final String VERSION = "0.1-SNAPSHOT";

    private Logger logger;
    public PlugModScheduler scheduler;

    @EventHandler
    public void onPreInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        Bukkit.setServer(new VolatileServer());
    }

    @EventHandler
    public void onServerAboutToStart(FMLServerAboutToStartEvent event)
    {
        getLogger().info("Initializing a new bukkit server");
        MinecraftServer minecraftServer = event.getServer();
        VolatileServer volatileServer;
        try
        {
            volatileServer = (VolatileServer)Bukkit.getServer();
            Preconditions.checkNotNull(volatileServer);
        }
        catch (Exception e)
        {
            throw new IllegalStateException("Bukkit.getServer is NOT a VolatileServer!",e);
        }

        PlugModServer server = new PlugModServer(this, minecraftServer);
        volatileServer.setCurrentServer(server);
        server.start();
    }

    @EventHandler
    public void onServerStopping(FMLServerStoppingEvent event)
    {
        Bukkit.shutdown();
    }

    public Logger getLogger()
    {
        return logger;
    }
}
