package com.eight.nivadeus.common.entities.customEntities;

import javax.annotation.Nonnull;

import com.eight.nivadeus.Nivadeus;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TestEntityRenderer extends HumanoidMobRenderer<TestEntity, TestEntityModel>{

	private static final ResourceLocation TEXTURE = new ResourceLocation(Nivadeus.MOD_ID, "textures/entities/test_mob.png");
	
	public TestEntityRenderer(EntityRendererProvider.Context context) {
		super(context, new TestEntityModel(context.bakeLayer(TestEntityModel.TEST_MODEL_LAYER)), 1f);
	}

	@Nonnull
	@Override
	public ResourceLocation getTextureLocation(TestEntity pEntity) {
		return TEXTURE;
	}
	
	

}
