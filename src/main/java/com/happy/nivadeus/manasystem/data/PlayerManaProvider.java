package com.happy.nivadeus.manasystem.data;

import javax.annotation.Nonnull;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerManaProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

	// The Capability
	public static Capability<PlayerMana> PLAYER_MANA = CapabilityManager.get(new CapabilityToken<>(){});
	
	private PlayerMana playerMana = null;
	
	// Lazy option is used to store the capability
	private final LazyOptional<PlayerMana> opt = LazyOptional.of(this::createPlayerMana);
	
	/**
	 * Helper method that will cache player's mana
	 * @return
	 */
	@Nonnull
	private PlayerMana createPlayerMana() {
		if (playerMana == null) {
			playerMana = new PlayerMana();
		}
		return playerMana;
	}
	
	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		createPlayerMana().saveNBTData(nbt);
		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		createPlayerMana().loadNBTData(nbt);		
	}
	
	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap) {
		if (cap == PLAYER_MANA) {
			return opt.cast();
		}
		return LazyOptional.empty();
	}
	
	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return getCapability(cap);
	}
	

}
