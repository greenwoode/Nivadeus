package com.eight.nivadeus.common.item;

import com.eight.nivadeus.spells.SpellCasting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.nbt.StringTag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.NotNull;

public class Wand extends Item {

    public Wand(Properties properties) {
        super(properties);
    }

    private void addSpell(ItemStack itemStack){

        if(!itemStack.hasTag()){
            CompoundTag spellTag = new CompoundTag();
            spellTag.putString("nivadeus.spell", "LCU(MCP(oop(),3,1.0,2.5,31),1.5)");
            itemStack.setTag(spellTag);
        }

    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity holder, int slotId, boolean isSelected) {

        if (!world.isClientSide()){

            addSpell(stack);

        }

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {

        if (!world.isClientSide() && hand == InteractionHand.MAIN_HAND){

            String spellEncoded = player.getMainHandItem().getTag().get("nivadeus.spell").getAsString();
            System.out.println(player.getName().getString() + " casting spell: " + spellEncoded);
            SpellCasting.cast(world, player, spellEncoded);


        }else if(hand == InteractionHand.MAIN_HAND){
            //world.destroyBlock(player.eyeBlockPosition(), false);
            //System.out.println(player.getName().getString() + "has used wand in hand " + hand.name());
            String spellEncoded = player.getMainHandItem().getTag().get("nivadeus.spell").getAsString();
            System.out.println(player.getName().getString() + " casting spell: " + spellEncoded);
            SpellCasting.cast(world, player, spellEncoded);
        }

        return super.use(world, player, hand);
    }


}
