package com.eight.nivadeus.setup;

import com.eight.nivadeus.Nivadeus;
import com.eight.nivadeus.common.entities.ModEntity;
import com.eight.nivadeus.common.entities.customEntities.TestEntity;
import com.eight.nivadeus.common.item.ModItems;
import com.eight.nivadeus.manasystem.data.ManaEvents;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Nivadeus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
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
	
    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
    	event.put(ModEntity.TEST_ENTITY.get(), TestEntity.createAttributes().build());
    }
}
