package com.eight.nivadeus.common.containers;

import com.eight.nivadeus.Nivadeus;
import com.eight.nivadeus.common.block.customBlock.TestMagicBlockContainer;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModContainers {
	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Nivadeus.MOD_ID);
	
	public static final RegistryObject<MenuType<TestMagicBlockContainer>> TEST_MAGIC_BLOCK_CONTAINER = CONTAINERS.register("test_magic_block", 
			() -> IForgeMenuType.create((windowId, inv, data) -> new TestMagicBlockContainer(windowId, data.readBlockPos(), inv, inv.player)));
	
	public static void register(IEventBus eventBus){
		CONTAINERS.register(eventBus);		
	}

}
