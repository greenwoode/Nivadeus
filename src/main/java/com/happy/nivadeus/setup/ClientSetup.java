package com.happy.nivadeus.setup;

import com.happy.nivadeus.Nivadeus;
import com.happy.nivadeus.manasystem.client.KeyBindings;
import com.happy.nivadeus.manasystem.client.KeyInputHandler;
import com.happy.nivadeus.manasystem.client.ManaOverlay;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.minecraftforge.client.gui.ForgeIngameGui.HOTBAR_ELEMENT;

@EventBusSubscriber(modid = Nivadeus.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
	
	public static void init(FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.addListener(KeyInputHandler::onKeyInput);
		KeyBindings.init();
		OverlayRegistry.registerOverlayAbove(HOTBAR_ELEMENT, "name", ManaOverlay.HUD_MANA);
	}

}
