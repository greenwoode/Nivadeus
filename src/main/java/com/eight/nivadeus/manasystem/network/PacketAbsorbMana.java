package com.eight.nivadeus.manasystem.network;

import java.util.function.Supplier;

import com.eight.nivadeus.manasystem.data.ManaManager;
import com.eight.nivadeus.manasystem.data.PlayerManaProvider;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

/**
 * A packet that is sent to the server from client when they "absorb: mana
 * @author Lam Ta
 *
 */
public class PacketAbsorbMana {

	public static final String MESSAGE_NO_MANA = "message.nomana";
	
	public PacketAbsorbMana() {
	}
	
	public PacketAbsorbMana(FriendlyByteBuf buf) {
	}
	
	public void toBytes(FriendlyByteBuf buf) {
		
	}
	
	/*
	 * This fires when the server recieved a packet
	 */
	public boolean handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context ctx = supplier.get();
		// enqueueWork is use to make sure we are on the right thread
		ctx.enqueueWork(() -> {
			// Server side
			// Extract mana from chunk's mana pool then add to player's manapool
			ServerPlayer player = ctx.getSender();
			int extracted = ManaManager.get(player.level).extractMana(player.blockPosition());
			if (extracted <= 0) {
				player.sendMessage(new TranslatableComponent(MESSAGE_NO_MANA).withStyle(ChatFormatting.RED), Util.NIL_UUID);		
			} else {
				player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(playerMana -> {
					playerMana.addMana(extracted);
				});
			}
			
		});
		return true;
	}
	
}
