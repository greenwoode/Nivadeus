package com.eight.nivadeus.manasystem.network;

import java.util.function.Supplier;

import com.eight.nivadeus.manasystem.client.ClientManaData;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

/**
 * A packet that is send to sync mana from server-side to clients
 * @author Lam Ta
 *
 */
public class PacketSyncManaToClient {

	private final int playerMana;
	private final int chunkMana;
	
	public PacketSyncManaToClient(int playerMana, int chunkMana) {
		this.playerMana = playerMana;
		this.chunkMana = chunkMana;
	}
	
	public PacketSyncManaToClient(FriendlyByteBuf buf) {
		playerMana = buf.readInt();
		chunkMana = buf.readInt();		
	}
	
	// Serialize method
	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(playerMana);
		buf.writeInt(chunkMana);
	}
	
	public boolean handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context ctx = supplier.get();
		ctx.enqueueWork(() ->{
			// Client side
			// Do not try to access client-only classes here
			// This packet need to be available server-side too
			ClientManaData.set(playerMana,chunkMana);
		});
		return true;
	}
}
