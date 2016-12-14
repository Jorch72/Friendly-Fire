package net.darkhax.friendlyfire;

import java.io.File;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {
    
    public static Configuration config;
    
    public static boolean protectPets;
    public static boolean protectBabies;
    
    public ConfigHandler(File configFile) {
        
        config = new Configuration(configFile);
        MinecraftForge.EVENT_BUS.register(this);
        this.syncConfigData();
    }
    
    @SubscribeEvent
    public void onConfigChange (ConfigChangedEvent.OnConfigChangedEvent event) {
        
        if (event.modID.equals("friendlyfire"))
            this.syncConfigData();
    }
    
    private void syncConfigData () {
        
        protectPets = config.getBoolean("pets", Configuration.CATEGORY_GENERAL, true, "Should pets be immune to damage from their owners?");
        protectBabies = config.getBoolean("babies", Configuration.CATEGORY_GENERAL, false, "Should babies be immune to damage?");
        config.save();
    }
}