package com.eight.nivadeus.setup;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.minecraftforge.client.gui.ForgeIngameGui.HOTBAR_ELEMENT;

import com.eight.nivadeus.Nivadeus;
import com.eight.nivadeus.client.TestMagicBlockScreen;
import com.eight.nivadeus.common.block.ModBlocks;
import com.eight.nivadeus.common.containers.ModContainers;
import com.eight.nivadeus.manasystem.client.KeyBindings;
import com.eight.nivadeus.manasystem.client.KeyInputHandler;
import com.eight.nivadeus.manasystem.client.ManaOverlay;

@EventBusSubscriber(modid = Nivadeus.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
	
	public static void init(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(ModContainers.TEST_MAGIC_BLOCK_CONTAINER.get(), TestMagicBlockScreen::new);
			ItemBlockRenderTypes.setRenderLayer(ModBlocks.TEST_MAGIC_BLOCK.get(), RenderType.translucent());
		});
		
        MinecraftForge.EVENT_BUS.addListener(KeyInputHandler::onKeyInput);
		KeyBindings.init();
		OverlayRegistry.registerOverlayAbove(HOTBAR_ELEMENT, "name", ManaOverlay.HUD_MANA);
	}

}
