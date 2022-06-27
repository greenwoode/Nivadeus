package com.eight.nivadeus.common.entities.customEntities;

import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.eight.nivadeus.common.entities.ModEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreakDoorGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class TestEntity extends Monster {

	private static final Predicate<Difficulty> DOOR_BREAKING_PREDICATE = (p_34284_) -> {
		return p_34284_ == Difficulty.NORMAL;
	};
	
	private final BreakDoorGoal breakDoorGoal = new BreakDoorGoal(this, DOOR_BREAKING_PREDICATE);
	private boolean canBreakDoors;

	public TestEntity(EntityType<? extends Monster> type, Level worldIn) {
		super(type, worldIn);
	}
	
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.addBehaviourGoals();
	}
	
	protected void addBehaviourGoals() {
		this.goalSelector.addGoal(2, new TestEntityAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this,Player.class, true));
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0D)
				.add(Attributes.MOVEMENT_SPEED, (double) 0.23F).add(Attributes.ATTACK_DAMAGE, 3.0D)
				.add(Attributes.ARMOR, 2.0D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
	}
	
	public boolean canBreakDoors() {
		return this.canBreakDoors;
	}
	
	/**
	 * Sets or removes EntityAIBreakDoor task
	 */
	public void setCanBreakDoors(boolean pEnabled) {
		if (this.supportsBreakDoorGoal() && GoalUtils.hasGroundPathNavigation(this)) {
			if (this.canBreakDoors != pEnabled) {
				this.canBreakDoors = pEnabled;
				((GroundPathNavigation) this.getNavigation()).setCanOpenDoors(pEnabled);
				if (pEnabled) {
					this.goalSelector.addGoal(1, this.breakDoorGoal);
				} else {
					this.goalSelector.removeGoal(this.breakDoorGoal);
				}
			}
		} else if (this.canBreakDoors) {
			this.goalSelector.removeGoal(this.breakDoorGoal);
			this.canBreakDoors = false;
		}
	}
	
	protected boolean supportsBreakDoorGoal() {
		return true;
	}
	
	/**
	 * Get the experience points the entity currently has.
	 */
	@Override
	protected int getExperienceReward(Player pPlayer) {	
		return super.getExperienceReward(pPlayer);
	}
	
	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
		super.onSyncedDataUpdated(key);
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void tick() {
		super.tick();
	}
	
	@Override
	public void aiStep() {
		// TODO: here is where we can add things like sun sensitive
		super.aiStep();
	}
	
	/**
	 * Called when the entity is attacked.
	 */
	public boolean hurt(DamageSource pSource, float pAmount) {
		if (!super.hurt(pSource, pAmount)) {
			return false;
		} else if (!(this.level instanceof ServerLevel)) {
			return false;
		} else {
//			ServerLevel serverlevel = (ServerLevel) this.level;
//			LivingEntity livingentity = this.getTarget();
//			if (livingentity == null && pSource.getEntity() instanceof LivingEntity) {
//				livingentity = (LivingEntity) pSource.getEntity();
//			}
//
//			int i = Mth.floor(this.getX());
//			int j = Mth.floor(this.getY());
//			int k = Mth.floor(this.getZ());
//			net.minecraftforge.event.entity.living.ZombieEvent.SummonAidEvent event = net.minecraftforge.event.ForgeEventFactory
//					.fireZombieSummonAid(this, level, i, j, k, livingentity,
//							this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).getValue());
//			if (event.getResult() == net.minecraftforge.eventbus.api.Event.Result.DENY)
//				return true;
//			if (event.getResult() == net.minecraftforge.eventbus.api.Event.Result.ALLOW || livingentity != null
//					&& this.level.getDifficulty() == Difficulty.HARD && (double) this.random.nextFloat() < this
//							.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).getValue()
//					&& this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)) {
//				Zombie zombie = event.getCustomSummonedAid() != null
//						&& event.getResult() == net.minecraftforge.eventbus.api.Event.Result.ALLOW
//								? event.getCustomSummonedAid()
//								: EntityType.ZOMBIE.create(this.level);
//
//				for (int l = 0; l < 50; ++l) {
//					int i1 = i + Mth.nextInt(this.random, 7, 40) * Mth.nextInt(this.random, -1, 1);
//					int j1 = j + Mth.nextInt(this.random, 7, 40) * Mth.nextInt(this.random, -1, 1);
//					int k1 = k + Mth.nextInt(this.random, 7, 40) * Mth.nextInt(this.random, -1, 1);
//					BlockPos blockpos = new BlockPos(i1, j1, k1);
//					EntityType<?> entitytype = zombie.getType();
//					SpawnPlacements.Type spawnplacements$type = SpawnPlacements.getPlacementType(entitytype);
//					if (NaturalSpawner.isSpawnPositionOk(spawnplacements$type, this.level, blockpos, entitytype)
//							&& SpawnPlacements.checkSpawnRules(entitytype, serverlevel, MobSpawnType.REINFORCEMENT,
//									blockpos, this.level.random)) {
//						zombie.setPos((double) i1, (double) j1, (double) k1);
//						if (!this.level.hasNearbyAlivePlayer((double) i1, (double) j1, (double) k1, 7.0D)
//								&& this.level.isUnobstructed(zombie) && this.level.noCollision(zombie)
//								&& !this.level.containsAnyLiquid(zombie.getBoundingBox())) {
//							if (livingentity != null)
//								zombie.setTarget(livingentity);
//							zombie.finalizeSpawn(serverlevel, this.level.getCurrentDifficultyAt(zombie.blockPosition()),
//									MobSpawnType.REINFORCEMENT, (SpawnGroupData) null, (CompoundTag) null);
//							serverlevel.addFreshEntityWithPassengers(zombie);
//							this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE)
//									.addPermanentModifier(new AttributeModifier("Zombie reinforcement caller charge",
//											(double) -0.05F, AttributeModifier.Operation.ADDITION));
//							zombie.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE)
//									.addPermanentModifier(new AttributeModifier("Zombie reinforcement callee charge",
//											(double) -0.05F, AttributeModifier.Operation.ADDITION));
//							break;
//						}
//					}
//				}
//			}

			return true;
		}
	}
	
	public boolean doHurtTarget(Entity pEntity) {
		boolean flag = super.doHurtTarget(pEntity);
		if (flag) {
			float f = this.level.getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
			if (this.getMainHandItem().isEmpty() && this.isOnFire() && this.random.nextFloat() < f * 0.3F) {
				pEntity.setSecondsOnFire(2 * (int) f);
			}
		}

		return flag;
	}
	
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ZOMBIE_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource pDamageSource) {
		return SoundEvents.ZOMBIE_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ZOMBIE_DEATH;
	}

	protected SoundEvent getStepSound() {
		return SoundEvents.ZOMBIE_STEP;
	}

	protected void playStepSound(BlockPos pPos, BlockState pBlock) {
		this.playSound(this.getStepSound(), 0.15F, 1.0F);
	}
	
	public MobType getMobType() {
		return MobType.UNDEAD;
	}

	/**
	 * Gives armor or weapon for entity based on given DifficultyInstance
	 */
	protected void populateDefaultEquipmentSlots(DifficultyInstance pDifficulty) {
		super.populateDefaultEquipmentSlots(pDifficulty);
		if (this.random.nextFloat() < (this.level.getDifficulty() == Difficulty.NORMAL ? 0.05F : 0.01F)) {
			int i = this.random.nextInt(3);
			if (i == 0) {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
			} else {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SHOVEL));
			}
		}

	}
	
	public void addAdditionalSaveData(CompoundTag pCompound) {
		super.addAdditionalSaveData(pCompound);
		pCompound.putBoolean("CanBreakDoors", this.canBreakDoors());
	}
	
	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readAdditionalSaveData(CompoundTag pCompound) {
		super.readAdditionalSaveData(pCompound);
		this.setCanBreakDoors(pCompound.getBoolean("CanBreakDoors"));
	}
	
	public void killed(ServerLevel pLevel, LivingEntity pKilledEntity) {
		super.killed(pLevel, pKilledEntity);
		if (pLevel.getDifficulty() == Difficulty.NORMAL || pLevel.getDifficulty() == Difficulty.HARD) {
			if (pLevel.getDifficulty() != Difficulty.HARD && this.random.nextBoolean()) {
				return;
			}

			if (!this.isSilent()) {
				pLevel.levelEvent((Player) null, 1026, this.blockPosition(), 0);
			}
		}

	}
	
}
