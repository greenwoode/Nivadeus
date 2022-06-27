package com.eight.nivadeus.common.entities.customEntities;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class TestEntityAttackGoal extends MeleeAttackGoal{

	private final TestEntity glitch;
	private int raiseArmTicks;
	
	public TestEntityAttackGoal(TestEntity glitch, double p_25553_, boolean p_25554_) {
		super(glitch, p_25553_, p_25554_);
		this.glitch = glitch;
	}
	
	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void start() {
		super.start();
		this.raiseArmTicks = 0;
	}
	
	/**
	 * Reset the task's internal state. Called when this task is interrupted by
	 * another one
	 */
	public void stop() {
		super.stop();
		this.glitch.setAggressive(false);
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void tick() {
		super.tick();
		++this.raiseArmTicks;
		if (this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
			this.glitch.setAggressive(true);
		} else {
			this.glitch.setAggressive(false);
		}

	}
}
