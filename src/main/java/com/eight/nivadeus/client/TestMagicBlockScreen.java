package com.eight.nivadeus.client;

import com.eight.nivadeus.Nivadeus;
import com.eight.nivadeus.common.block.customBlock.TestMagicBlockContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class TestMagicBlockScreen extends AbstractContainerScreen<TestMagicBlockContainer>{

	private final ResourceLocation GUI = new ResourceLocation(Nivadeus.MOD_ID, "textures/gui/test_magic_block_gui.png");
	
	public TestMagicBlockScreen(TestMagicBlockContainer pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
		this.leftPos = 0;
		this.topPos = 0;
		this.imageWidth = 256;
		this.imageHeight = 256;
	}
		
	@Override
	public void render(PoseStack pMatrixStack, int pMouseX, int pMouseY, float pPartialTicks) {
		this.renderBackground(pMatrixStack);
		super.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
		this.renderTooltip(pMatrixStack, pMouseX, pMouseY);
	}

	@SuppressWarnings("resource")
	@Override
	protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
		drawString(pPoseStack, Minecraft.getInstance().font, "Mana: " + menu.getMana(), 10, 10, 0xffffff);
	}

	@Override
	protected void renderBg(PoseStack pPoseStack, float pPartialTicks, int pMouseX, int pMouseY) {
		RenderSystem.setShaderTexture(0, GUI);
		this.blit(pPoseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
		
	}

}
