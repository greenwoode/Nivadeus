package com.eight.nivadeus.common.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
	public static final CreativeModeTab NIVADEUS_TAB = new CreativeModeTab("nivadeusmodtab") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModItems.MANA_EYE.get());
		}
	};
	
}
