package br.com.gamemods.bukkitmod.mod;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = BukkitMod.MODID, version = BukkitMod.VERSION)
public class BukkitMod
{
    public static final String MODID = "BukkitMod";
    public static final String VERSION = "0.1-SNAPSHOT";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }
}
