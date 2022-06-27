package com.eight.nivadeus.setup;

import com.eight.nivadeus.common.BlockEntity.ModBlockEntity;
import com.eight.nivadeus.common.block.ModBlocks;
import com.eight.nivadeus.common.containers.ModContainers;
import com.eight.nivadeus.common.entities.ModEntity;
import com.eight.nivadeus.common.item.ModItems;

import net.minecraftforge.eventbus.api.IEventBus;

public class Registration {

	public static void init(IEventBus modBus) {
		// Register the deferred registry
		ModSetup.setup();
		
		//Registration.init();
		Config.register();
		
		// Mod Blocks/Items/Entities register
		ModItems.register(modBus);
		ModBlocks.register(modBus);
		ModBlockEntity.register(modBus);
		ModContainers.register(modBus);
		ModEntity.register(modBus);
	}
}
