package com.happy.nivadeus.manasystem.client;

import com.happy.nivadeus.manasystem.ManaConfig;

import net.minecraftforge.client.gui.IIngameOverlay;

public class ManaOverlay {

	public static final IIngameOverlay HUD_MANA = (gui, poseStack, partialTicks, width, height) -> {
		String toDisplay = String.format("Player Mana: %d | Chunk Mana: %d", ClientManaData.getPlayerMana(), ClientManaData.getChunkMana());
//		String toDisplay = ClientManaData.getPlayerMana() + "/" + ClientManaData.getChunkMana();
		int x = ManaConfig.MANA_HUD_X.get();
		int y = ManaConfig.MANA_HUD_Y.get();
		if (x >= 0 && y >= 0) {
			gui.getFont().draw(poseStack, toDisplay, x, y, ManaConfig.MANA_HUD_COLOR.get());
		}
	};
	
}
