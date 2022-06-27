package com.eight.nivadeus.common.block.customBlock;

import com.eight.nivadeus.common.BlockEntity.ModBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TestMagicBlockBE extends BlockEntity {

	private final ItemStackHandler itemHandler = createHandler();
	private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
	
	
    public TestMagicBlockBE(BlockPos pos, BlockState state) {
        super(ModBlockEntity.TEST_MAGIC_BLOCK_BE.get(), pos, state);
    }

    
    
	@Override
	public void setRemoved() {
		super.setRemoved();
		handler.invalidate();
	}



	@Override
	public void load(CompoundTag tag) {
		if (tag.contains("Inventory")) {
			itemHandler.deserializeNBT(tag.getCompound("Inventory"));
		}
		super.load(tag);
	}

	private ItemStackHandler createHandler() {
		return new ItemStackHandler(1) {

			@Override
			protected void onContentsChanged(int slot) {
				// TODO Auto-generated method stub
				setChanged();
			}

			@Override
			public boolean isItemValid(int slot, ItemStack stack) {
				if (stack == null 
						|| stack.getItem().getRegistryName() == Items.BOOK.getRegistryName() 
						|| stack.getItem().getRegistryName() == Items.PAPER.getRegistryName()) {
					return super.isItemValid(slot, stack);			
				}
				return false;
				
			}

			@Override
			public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
				if (stack.getItem().getRegistryName() != Items.BOOK.getRegistryName()
						&& stack.getItem().getRegistryName() != Items.PAPER.getRegistryName()) {
					return stack;
				}				
				return super.insertItem(slot, stack, simulate);
			}
		};
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return handler.cast();			
		}
		return super.getCapability(cap, side);
	}

    
}
