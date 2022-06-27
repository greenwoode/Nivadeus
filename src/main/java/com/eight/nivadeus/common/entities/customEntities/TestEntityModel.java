package com.eight.nivadeus.common.entities.customEntities;

import com.eight.nivadeus.Nivadeus;

import net.minecraft.client.model.AbstractZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.resources.ResourceLocation;

public class TestEntityModel extends AbstractZombieModel<TestEntity>{

	public static final String BODY = "body";
	
	public static ModelLayerLocation TEST_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Nivadeus.MOD_ID, "glitch"), BODY);
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = createMesh(CubeDeformation.NONE, 0.6f);
		return LayerDefinition.create(meshDefinition, 64, 32);
	}
	
	public TestEntityModel(ModelPart part) {
		super(part);
	}

	@Override
	public boolean isAggressive(TestEntity pEntity) {
		return pEntity.isAggressive();
	}
		

}
