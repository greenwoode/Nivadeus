package com.eight.nivadeus.setup;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.minecraftforge.client.gui.ForgeIngameGui.HOTBAR_ELEMENT;

import com.eight.nivadeus.Nivadeus;
import com.eight.nivadeus.client.TestMagicBlockScreen;
import com.eight.nivadeus.common.block.ModBlocks;
import com.eight.nivadeus.common.containers.ModContainers;
import com.eight.nivadeus.common.entities.ModEntity;
import com.eight.nivadeus.common.entities.customEntities.TestEntityModel;
import com.eight.nivadeus.common.entities.customEntities.TestEntityRenderer;
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
		
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.TEST_MAGIC_BLOCK.get(), RenderType.cutout());
		
        MinecraftForge.EVENT_BUS.addListener(KeyInputHandler::onKeyInput);
		KeyBindings.init();
		OverlayRegistry.registerOverlayAbove(HOTBAR_ELEMENT, "name", ManaOverlay.HUD_MANA);
	}
	
	@SubscribeEvent
	public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(TestEntityModel.TEST_MODEL_LAYER, TestEntityModel::createBodyLayer);
	}
	
	@SubscribeEvent
	public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ModEntity.TEST_ENTITY.get(), TestEntityRenderer::new);
	}

}
