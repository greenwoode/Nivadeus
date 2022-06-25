package com.eight.nivadeus.common.BlockEntity;

import com.eight.nivadeus.Nivadeus;
import com.eight.nivadeus.common.block.ModBlocks;
import com.eight.nivadeus.common.block.customBlock.TestMagicBlockBE;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntity {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Nivadeus.MOD_ID);

	public static final RegistryObject<BlockEntityType<TestMagicBlockBE>> TEST_MAGIC_BLOCK_BE = BLOCK_ENTITIES.register("test_magic_block", 
			() -> BlockEntityType.Builder.of(TestMagicBlockBE::new, ModBlocks.TEST_MAGIC_BLOCK.get()).build(null));
	
	
	public static void register(IEventBus eventBus){
		BLOCK_ENTITIES.register(eventBus);		
	}
	
}
