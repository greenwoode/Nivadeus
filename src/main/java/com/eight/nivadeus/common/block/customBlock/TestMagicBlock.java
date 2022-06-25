package com.eight.nivadeus.common.block.customBlock;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

public class TestMagicBlock extends Block implements EntityBlock {

	public static final String MESSAGE_TESTMAGICBLOCK = "message.testmagicblock";
	public static final String SCREEN_NIVADEUS_TESTMAGICBLOCK = "screen.nivadeus.testmagicblock";
	
	private static final VoxelShape RENDER_SHAPE = Shapes.box(0.1, 0.1, 0.1, 0.9, 0.9, 0.9);
	
	public TestMagicBlock() {
		super(Properties.of(Material.WOOD)
				.sound(SoundType.WOOD)
				.strength(2.0f)
				.requiresCorrectToolForDrops()
				
		);
	}	
	
	@Override
	public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) { return RENDER_SHAPE; }

//	@Override
//	public void appendHoverText(ItemStack stack, @Nullable BlockGetter reader, List<Component> list, TooltipFlag flags) {
//		list.add(new TranslatableComponent(MESSAGE_TESTMAGICBLOCK, Integer.toString(PowergenBE.POWERGEN_GENERATE))
//                .withStyle(ChatFormatting.BLUE));
//	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		// TODO Auto-generated method stub
		return new TestMagicBlockBE(pPos, pState);
	}



	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!level.isClientSide) {
			BlockEntity be = level.getBlockEntity(pos);
			if (be instanceof TestMagicBlockBE) {
				MenuProvider containerProvider = new MenuProvider() {

					@Override
					public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player playerEntity) {
						return new TestMagicBlockContainer(containerId, pos, playerInventory, playerEntity);
					}

					@Override
					public Component getDisplayName() {
						return new TranslatableComponent(SCREEN_NIVADEUS_TESTMAGICBLOCK);
					}
					
				};
				NetworkHooks.openGui((ServerPlayer) player, containerProvider, be.getBlockPos());
			} else {
                throw new IllegalStateException("Our named container provider is missing!");
            }
		}		
		
		return InteractionResult.SUCCESS;
	}

	



	
	
}
