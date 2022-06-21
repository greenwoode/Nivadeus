package com.happy.nivadeus.util;

import com.happy.nivadeus.Nivadeus;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {

	public static class Blocks{
		private static TagKey<Block> tag(String name){
			return BlockTags.create(new ResourceLocation(Nivadeus.MOD_ID, name));
		}
		
		private static TagKey<Block> forgeTag(String name){
			return BlockTags.create(new ResourceLocation("forge", name));
		}
		
	}
	
	public static class Items{
		private static TagKey<Item> tag(String name){
			return ItemTags.create(new ResourceLocation(Nivadeus.MOD_ID, name));
		}
		
		private static TagKey<Block> forgeTag(String name){
			return ItemTags.create(new ResourceLocation("forge", name));
		}
	}
}
