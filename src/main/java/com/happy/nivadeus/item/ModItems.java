package com.happy.nivadeus.item;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;

import com.happy.nivadeus.Nivadeus;

/**
 * A list of all items to be register
 * @author Lam Ta
 *
 */
public class ModItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Nivadeus.MOD_ID);
	
	//Items
	public static final RegistryObject<Item> TEST_INGOT = ITEMS.register("test_ingot",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.NIVADEUS_TAB)));
	public static final RegistryObject<Item> TEST_NUGGET = ITEMS.register("test_nugget", 
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.NIVADEUS_TAB)));
	
	public static final RegistryObject<Item> MANA_BALL = ITEMS.register("mana_ball",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.NIVADEUS_TAB)));
	
	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);		
	}
}
