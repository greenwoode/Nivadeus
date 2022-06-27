package com.eight.nivadeus.common.entities;

import com.eight.nivadeus.Nivadeus;
import com.eight.nivadeus.common.entities.customEntities.TestEntity;

import net.minecraft.core.Registry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntity {
	private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Nivadeus.MOD_ID);

	// Entity Registration
    public static final RegistryObject<EntityType<TestEntity>> TEST_ENTITY = ENTITIES.register("glitch", () -> EntityType.Builder.of(TestEntity::new, MobCategory.CREATURE)
            .sized(0.6f, 1.95f)
            .clientTrackingRange(8)
            .build("glitch"));
	
	public static void register(IEventBus eventBus) {
		ENTITIES.register(eventBus);
	}

}
