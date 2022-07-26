package com.eight.nivadeus.common.block;

import java.util.function.Supplier;

import com.eight.nivadeus.Nivadeus;
import com.eight.nivadeus.common.block.customBlock.TestMagicBlock;
import com.eight.nivadeus.common.item.ModCreativeModeTab;
import com.eight.nivadeus.common.item.ModItems;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Nivadeus.MOD_ID);
	
	// Blocks Registration
	public static final RegistryObject<Block> TEST_BLOCK = registerBlock("test_block",
			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()),
			ModCreativeModeTab.NIVADEUS_TAB);
	
	public static final RegistryObject<Block> TEST_ORE = registerBlock("test_ore",
			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(4f).requiresCorrectToolForDrops()),
			ModCreativeModeTab.NIVADEUS_TAB);
	
	public static final RegistryObject<Block> MANA_ORE = registerBlock("mana_ore",
			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()),
			ModCreativeModeTab.NIVADEUS_TAB);
	
	public static final RegistryObject<TestMagicBlock>TEST_MAGIC_BLOCK = registerBlock("test_magic_block"
			, TestMagicBlock::new, ModCreativeModeTab.NIVADEUS_TAB);
	
	public static final RegistryObject<Block> MANA_BATTERY = registerBlock("mana_battery",
            () -> new Block(BlockBehaviour.Properties.of(Material.GLASS, MaterialColor.COLOR_BROWN)
            		.strength(3.0f, 1)
            		.sound(SoundType.WOOD)
            		.requiresCorrectToolForDrops()),
            ModCreativeModeTab.NIVADEUS_TAB);

	
	// Helper methods
	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name,toReturn,tab);
		return toReturn;
	}
	
	private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
		return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
	
	public static void register(IEventBus eventBus){
		BLOCKS.register(eventBus);		
	}

}
