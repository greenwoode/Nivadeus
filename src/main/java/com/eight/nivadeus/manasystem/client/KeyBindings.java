package com.eight.nivadeus.manasystem.client;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.settings.KeyConflictContext;

/**
 * Setup keybindings for mana system
 * @author Lam Ta
 *
 */
public class KeyBindings {
	public static final String KEY_CATEGORIES_NIVADEUS = "key.categories.nivadeus";
	public static final String KEY_ABSORB_MANA = "key.absorbMana";
	
	public static KeyMapping gatherManaKeyMapping;
	
	public static void init() {
		// Map the key then register it
		gatherManaKeyMapping = new KeyMapping(KEY_ABSORB_MANA,
				KeyConflictContext.IN_GAME,InputConstants.getKey("key.keyboard.period"),
				KEY_CATEGORIES_NIVADEUS);		
		ClientRegistry.registerKeyBinding(gatherManaKeyMapping);
	}
	
}
