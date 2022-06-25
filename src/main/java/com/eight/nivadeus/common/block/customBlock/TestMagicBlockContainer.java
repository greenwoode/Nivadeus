package com.eight.nivadeus.common.block.customBlock;

import com.eight.nivadeus.common.block.ModBlocks;
import com.eight.nivadeus.common.containers.ModContainers;
import com.eight.nivadeus.manasystem.data.PlayerMana;
import com.eight.nivadeus.manasystem.data.PlayerManaProvider;
import com.eight.nivadeus.setup.Registration;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class TestMagicBlockContainer extends AbstractContainerMenu{

	private BlockEntity blockEntity;
	private Player playerEntity;
	private IItemHandler playerInventory;
	
	public TestMagicBlockContainer(int windowId, BlockPos pos, Inventory PlayerInv, Player player) {
		super(ModContainers.TEST_MAGIC_BLOCK_CONTAINER.get(), windowId);
		blockEntity = player.getCommandSenderWorld().getBlockEntity(pos);
		this.playerEntity = player;
		this.playerInventory = new InvWrapper(PlayerInv);
		
		if (blockEntity != null) {
			blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h ->{
				addSlot(new SlotItemHandler(h,0,233,125));	
			});
		}
		layoutPlayerInventorySlots(47,151);
		trackMana();
	}

	// Setup syning of mana from server to client in order to display mana levels it in the block
	private void trackMana() {	
		addDataSlot(new DataSlot() {

			@Override
			public int get() {
				return (getMana()) & 0xffff;
			}

			@Override
			public void set(int pValue) {
				playerEntity.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(h -> {
					int manaStored = h.getMana() & 0xffff0000;
					h.setMana(manaStored + (pValue & 0xffff));
				});
			}
		});
		
		addDataSlot(new DataSlot() {

			@Override
			public int get() {
				return (getMana() >> 16) & 0xffff;
			}

			@Override
			public void set(int pValue) {
				playerEntity.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(h -> {
					int manaStored = h.getMana() & 0x0000ffff;
					h.setMana(manaStored | (pValue << 16));
				});
				
			}
			
		});
	} 
	
	public int getMana() {
		return playerEntity.getCapability(PlayerManaProvider.PLAYER_MANA).map(PlayerMana::getMana).orElse(0);
	}
	
	@Override
	public boolean stillValid(Player pPlayer) {
		return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), playerEntity, ModBlocks.TEST_MAGIC_BLOCK.get());
	}
		
    @Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();
            if (index == 0) { // Move items from output slot to inventory
                if (!this.moveItemStackTo(stack, 1, 37, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemstack);
            } else {
            	// Move legal item from inventory to output slot
                if (stack.getItem().getRegistryName()  == Items.PAPER.getRegistryName()
                		|| stack.getItem().getRegistryName()  == Items.BOOK.getRegistryName()) {
                    if (!this.moveItemStackTo(stack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 28) { // move illegal item from inventory to hotbar
                    if (!this.moveItemStackTo(stack, 28, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 37 && !this.moveItemStackTo(stack, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return itemstack;
    }

	private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }
	
    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

}
