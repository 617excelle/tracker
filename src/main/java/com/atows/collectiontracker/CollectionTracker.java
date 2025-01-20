package com.atows.collectiontracker;

import com.atows.collectiontracker.handlers.ChatEventHandler;
import com.atows.collectiontracker.handlers.xpGui;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = CollectionTracker.MODID, version = CollectionTracker.VERSION)
public class CollectionTracker {
    public static final String MODID = "collectiontracker";
    public static final String VERSION = "1.0";

    /**
     * If everything goes to plan, you should see this message in the console.

     * This is the main class and method of your mod. It is the entry point for the mod and
     * is used to register all the things that your mod needs to function.
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ChatEventHandler chatEventHandler = new ChatEventHandler();
        xpGui xpGui = new xpGui();

        chatEventHandler.addObserver(xpGui);

        MinecraftForge.EVENT_BUS.register(chatEventHandler);
        MinecraftForge.EVENT_BUS.register(xpGui);
    }
}
