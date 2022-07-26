package com.eight.nivadeus.common.item;

import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.eight.nivadeus.Nivadeus;
import com.eight.nivadeus.common.entities.ModEntity;
import com.eight.nivadeus.setup.ModSetup;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;

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
	
	public static final RegistryObject<Item> TEST_CLUMP = ITEMS.register("test_clump", 
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.NIVADEUS_TAB)));
	
	public static final RegistryObject<Item> MANA_CLUSTER = ITEMS.register("mana_cluster", 
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.NIVADEUS_TAB)));
	
	public static final RegistryObject<Item> MANA_BALL = ITEMS.register("mana_ball",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.NIVADEUS_TAB)));
	
	public static final RegistryObject<Item> MANA_EYE = ITEMS.register("mana_eye",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.NIVADEUS_TAB).stacksTo(8)));
	
	public static final RegistryObject<Item> GLITCH_EGG = ITEMS.register("glitch", 
			() -> new ForgeSpawnEggItem(ModEntity.TEST_ENTITY, 0xf200ff, 0x000000, new Item.Properties().tab(ModCreativeModeTab.NIVADEUS_TAB)));

    public static final RegistryObject<Item> WAND_FIRE = ITEMS.register("wand_test_fire",
            () -> new Wand(
                    new Item.Properties().tab(ModCreativeModeTab.NIVADEUS_TAB).stacksTo(1)
            ));
	
	
	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);		
	}
}
