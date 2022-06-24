package com.eight.nivadeus.setup;

//import java.util.function.Supplier;
//
//import com.happy.nivadeus.Nivadeus;
//import com.happy.nivadeus.common.item.ModCreativeModeTab;
//import com.happy.nivadeus.common.item.ModItems;
//
//import net.minecraft.world.item.BlockItem;
//import net.minecraft.world.item.CreativeModeTab;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.state.BlockBehaviour;
//import net.minecraft.world.level.material.Material;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;

public class Registration {
	//TODO: move registration from items & blocks (and future registration stuff) to here.
//	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Nivadeus.MOD_ID);
//	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Nivadeus.MOD_ID);
	
	public static void init() {
//		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
//		BLOCKS.register(bus);
//		ITEMS.register(bus);
	}
	
	// Registering Blocks
//	public static final RegistryObject<Block> TEST_BLOCK = registerBlock("test_block",
//			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()),
//			ModCreativeModeTab.NIVADEUS_TAB);
//	
//	public static final RegistryObject<Block> TEST_ORE = registerBlock("test_ore",
//			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(4f).requiresCorrectToolForDrops()),
//			ModCreativeModeTab.NIVADEUS_TAB);
//	
//	////////////////////////////////////////////////////////////////////////////////
//	
//	// Registering Items	
//	public static final RegistryObject<Item> TEST_INGOT = ITEMS.register("test_ingot",
//			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.NIVADEUS_TAB)));
//	public static final RegistryObject<Item> TEST_NUGGET = ITEMS.register("test_nugget", 
//			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.NIVADEUS_TAB)));
//	
//	public static final RegistryObject<Item> MANA_BALL = ITEMS.register("mana_ball",
//			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.NIVADEUS_TAB)));
//
//	
//	// Helper methods for blocks
//	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
//		RegistryObject<T> toReturn = BLOCKS.register(name, block);
//		registerBlockItem(name,toReturn,tab);
//		return toReturn;
//	}
//	private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
//		return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
//	}
}
