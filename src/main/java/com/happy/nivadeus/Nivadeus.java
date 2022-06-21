package com.happy.nivadeus;

import com.happy.nivadeus.block.ModBlocks;
import com.happy.nivadeus.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
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
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		ModItems.register(eventBus);
		ModBlocks.register(eventBus);
		
		// Register the setup method for modloading
		eventBus.addListener(this::setup);
		
		
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		LOGGER.info("HELLO FROM PREINIT");
		LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
	}
}
