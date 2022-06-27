package com.eight.nivadeus.manasystem.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nonnull;

import org.antlr.v4.runtime.misc.NotNull;

import com.eight.nivadeus.manasystem.ManaConfig;
import com.eight.nivadeus.manasystem.network.PacketSyncManaToClient;
import com.eight.nivadeus.setup.Messages;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

// SavedData allow for attaching data to the world
public class ManaManager extends SavedData{

	private final Map<ChunkPos, Mana> manaMap = new HashMap<>();
	private final Random random = new Random();
	
	private int counter = 0;
	
	/**
	 * This method grab mana data from the current level
	 * Should not be done on clientside or it will break
	 * @param level
	 * @return mana level
	 */
	@Nonnull
	public static ManaManager get(Level level) {
		if (level.isClientSide) {
			throw new RuntimeException("Do not access this client-side!");
		}
		DimensionDataStorage storage = ((ServerLevel) level).getDataStorage();
		return storage.computeIfAbsent(ManaManager::new, ManaManager::new, "manamanager");
	}
	
	/**
	 * A lazy way to populate chunks with mana
	 * @param pos
	 * @return Mana
	 */
	@NotNull
	private Mana getManaInternal(BlockPos pos) {
		ChunkPos chunkPos = new ChunkPos(pos);
		return manaMap.computeIfAbsent(chunkPos, cp ->
		// TODO: Change math to logCurve instead of random number generator
				new Mana(random.nextInt(ManaConfig.CHUNK_MAX_MANA.get()) + ManaConfig.CHUNK_MIN_MANA.get()));
	}
	
	/**
	 * a way to retrieve mana from a pos
	 * @param pos
	 * @return int
	 */
	public int getMana(BlockPos pos) {
		Mana mana = getManaInternal(pos);
		return mana.getMana();
	}
	
	/**
	 * Helper method to extract mana from chunk
	 * @param pos
	 * @return int
	 */
	public int extractMana(BlockPos pos) {
		Mana mana = getManaInternal(pos);
		int present = mana.getMana();
		if(present > 0) {
			mana.setMana(present-1);
			setDirty(); // Mark to be save
			return 1;
		} else {
			return 0;
		}
		
	}
	
	public void tick(Level level) {
		counter--;
		if (counter <= 0) {
			counter = 10;
			// Synchronize mana to players in this world
			// TODO: make it so only update when there is change
			level.players().forEach(player -> {
				if (player instanceof ServerPlayer serverPlayer) {
					int playerMana = serverPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
							.map(PlayerMana::getMana)
							.orElse(-1);
					int chunkMana = getMana(serverPlayer.blockPosition());
					Messages.sendToPlayer(new PacketSyncManaToClient(playerMana, chunkMana), serverPlayer);
				}
			});
		}
		
		// TODO: Maybe Player can slowly accumulate mana
	}
	
	public ManaManager() {}
	
	public ManaManager(CompoundTag tag) {
		
		// Load chunk mana data
		ListTag list = tag.getList("mana", Tag.TAG_COMPOUND);
		for (Tag t : list) {
			CompoundTag manaTag = (CompoundTag) t;
			Mana mana = new Mana(manaTag.getInt("mana"));
			ChunkPos pos = new ChunkPos(manaTag.getInt("x"),manaTag.getInt("z"));
			manaMap.put(pos, mana);
		}
	}
	
	@Override
	public CompoundTag save(CompoundTag pCompoundTag) {
		
		// Save mana for every chunk then add it to tag
		ListTag list = new ListTag();
		manaMap.forEach((chunkPos, mana) -> {
			CompoundTag manaTag	= new CompoundTag();
			manaTag.putInt("x", chunkPos.x);
			manaTag.putInt("z", chunkPos.z);
			manaTag.putInt("mana", mana.getMana());
			list.add(manaTag);
		});
		pCompoundTag.put("mana", list);
		return pCompoundTag;
	}



}
