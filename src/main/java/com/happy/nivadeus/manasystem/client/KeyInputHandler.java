package com.happy.nivadeus.manasystem.client;

import net.minecraftforge.client.event.InputEvent;

import com.happy.nivadeus.manasystem.network.PacketAbsorbMana;
import com.happy.nivadeus.setup.Messages;

public class KeyInputHandler {
	public static void onKeyInput(InputEvent.KeyInputEvent event) {
		if (KeyBindings.gatherManaKeyMapping.consumeClick()) {
			Messages.sendToServer(new PacketAbsorbMana());
		}
		
	}
	
	
}
