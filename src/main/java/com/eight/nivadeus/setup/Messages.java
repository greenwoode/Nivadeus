package com.eight.nivadeus.setup;

import com.eight.nivadeus.Nivadeus;
import com.eight.nivadeus.manasystem.network.PacketAbsorbMana;
import com.eight.nivadeus.manasystem.network.PacketSyncManaToClient;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class Messages {
	
	private static SimpleChannel INSTANCE;
	
	private static int packetId = 0;
	private static int id() { return packetId++; }
	
	public static void register() {
		
		SimpleChannel net = NetworkRegistry.ChannelBuilder
				.named(new ResourceLocation(Nivadeus.MOD_ID, "messages"))
				.networkProtocolVersion(() -> "1.0")
				.clientAcceptedVersions(s -> true)
				.serverAcceptedVersions(s -> true)
				.simpleChannel();
		
		INSTANCE = net;
		
		// Register mana absorb request packet client to server
		net.messageBuilder(PacketAbsorbMana.class, id(), NetworkDirection.PLAY_TO_SERVER)
				.decoder(PacketAbsorbMana::new)
				.encoder(PacketAbsorbMana::toBytes)
				.consumer(PacketAbsorbMana::handle)
				.add();;
				
		// Register mana sync packets from Server to client
		net.messageBuilder(PacketSyncManaToClient.class, id(), NetworkDirection.PLAY_TO_CLIENT)
				.decoder(PacketSyncManaToClient::new)
				.encoder(PacketSyncManaToClient::toBytes)
				.consumer(PacketSyncManaToClient::handle)
				.add();;		
	}
	
	public static <MSG> void sendToServer(MSG message) { 
		INSTANCE.sendToServer(message);
	}
	
	public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
		INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
	}
	
}
