package com.eight.nivadeus.spells;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class Spell {

    public LivingEntity owner;
    public Vec3 vecPOS;
    public Direction castFacing;
    public Vec3 origin;
    public Level world;

    public Spell(LivingEntity user, Level pWorld){

        this.owner = user;
        this.origin = new Vec3(this.owner.getX(), this.owner.getY(), this.owner.getZ());
        this.vecPOS = new Vec3(this.origin.x, this.origin.y, this.origin.z);
        this.castFacing = this.owner.getDirection();
        this.world = pWorld;

    }

    public void setPointOrigin(Vec3 point){
        this.vecPOS = point;
    }

}
