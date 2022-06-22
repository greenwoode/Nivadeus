package com.happy.nivadeus.setup;

import com.happy.nivadeus.common.item.ModItems;
import com.happy.nivadeus.manasystem.data.ManaEvents;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup {

    public static void setup() {
        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addGenericListener(Entity.class, ManaEvents::onAttachCapabilitiesPlayer);
        bus.addListener(ManaEvents::onPlayerCloned);
        bus.addListener(ManaEvents::onRegisterCapabilities);
        bus.addListener(ManaEvents::onWorldTick);
    }
	
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
        	// TODO: find use
        });
        Messages.register();
    }
	
}
