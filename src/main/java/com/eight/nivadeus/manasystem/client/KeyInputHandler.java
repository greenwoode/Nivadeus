package com.eight.nivadeus.manasystem.client;

import com.eight.nivadeus.manasystem.network.PacketAbsorbMana;
import com.eight.nivadeus.setup.Messages;

import net.minecraftforge.client.event.InputEvent;

public class KeyInputHandler {
	public static void onKeyInput(InputEvent.KeyInputEvent event) {
		if (KeyBindings.gatherManaKeyMapping.consumeClick()) {
			Messages.sendToServer(new PacketAbsorbMana());
		}
		
	}
	
	
}
