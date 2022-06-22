package com.happy.nivadeus.manasystem.data;

import com.happy.nivadeus.Nivadeus;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ManaEvents {

	public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
		// attach capability to Vanilla player
		if (event.getObject() instanceof Player) {
			if(event.getObject().getCapability(PlayerManaProvider.PLAYER_MANA).isPresent() == false) {
				event.addCapability(new ResourceLocation(Nivadeus.MOD_ID, "playermana"), new PlayerManaProvider());
			}
		}
	}
	
	public static void onPlayerCloned(PlayerEvent.Clone event) {
		if (event.isWasDeath()) {
			// Copy from the capability
			event.getOriginal().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(oldStore -> {
				event.getPlayer().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(newStore -> {
					newStore.copyFrom(oldStore);
				});
			});
			
		}
	}
	
	public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) { event.register(PlayerMana.class); }
	
	public static void onWorldTick(TickEvent.WorldTickEvent event) {
		// Do nothing if on client side
		if (event.world.isClientSide) {
			return;
		} 
		// Only use end tick so there will be no repeats
		if (event.phase == TickEvent.Phase.START) {
			return;			
		}
		ManaManager manager = ManaManager.get(event.world);
		manager.tick(event.world);
		
	}
}
