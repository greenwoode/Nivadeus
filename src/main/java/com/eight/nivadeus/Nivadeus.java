package com.eight.nivadeus;

import com.eight.nivadeus.common.block.ModBlocks;
import com.eight.nivadeus.common.item.ModItems;
import com.eight.nivadeus.setup.ClientSetup;
import com.eight.nivadeus.setup.Config;
import com.eight.nivadeus.setup.ModSetup;
import com.eight.nivadeus.setup.Registration;
import com.mojang.logging.LogUtils;

import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


import org.slf4j.Logger;

@Mod(Nivadeus.MOD_ID)
public class Nivadeus
{
	public static final String MOD_ID = "nivadeus";    
	private static final Logger LOGGER = LogUtils.getLogger();

	public Nivadeus() {
		
		
		// Register the deferred registry
		ModSetup.setup();
		Registration.init();
		Config.register();
	
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		ModItems.register(modBus);
		ModBlocks.register(modBus);
		modBus.addListener(this::setup);
		modBus.addListener(ModSetup::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modBus.addListener(ClientSetup::init));
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		LOGGER.info("HELLO FROM PREINIT");
		LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
	}
}
