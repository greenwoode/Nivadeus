package com.eight.nivadeus.spells;


import net.minecraft.Util;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.Vec3;


public class SpellComponents {

    public static Spell moveUp(Spell spell, double dis){

        spell.vecPOS = spell.vecPOS.add(0, dis, 0);

        return spell;
    }
    public static Spell moveDown(Spell spell, double dis){

        spell.vecPOS = spell.vecPOS.add(0, -dis, 0);

        return spell;
    }
    public static Spell moveNorth(Spell spell, double dis){

        spell.vecPOS = spell.vecPOS.add(0, 0, -dis);

        TextComponent msg = new TextComponent("moved north: " + spell.vecPOS.toString());
        spell.owner.sendMessage(msg, Util.NIL_UUID);

        return spell;
    }
    public static Spell moveSouth(Spell spell, double dis){

        spell.vecPOS = spell.vecPOS.add(0, 0, dis);

        return spell;
    }
    public static Spell moveEast(Spell spell, double dis){

        spell.vecPOS = spell.vecPOS.add(dis, 0, 0);

        return spell;
    }
    public static Spell moveWest(Spell spell, double dis){

        spell.vecPOS = spell.vecPOS.add(-dis, 0, 0);

        return spell;
    }

    public static Spell moveTowardPlayer(Spell spell, double dis){
        spell.vecPOS = spell.vecPOS.add(spell.origin.vectorTo(spell.vecPOS).normalize().multiply(-dis,0f,-dis));

        return spell;
    }
    public static Spell moveAwayPlayer(Spell spell, double dis){
        spell.vecPOS = spell.vecPOS.add(spell.origin.vectorTo(spell.vecPOS).normalize().multiply(dis,0f,dis));

        return spell;
    }

    public static Spell movePlayerFacing(Spell spell, double dis){

        //spell.vecPOS = spell.vecPOS.add((Vec3)spell.castFacing.getNormal());

        //System.out.println("moved in direction casted");
        TextComponent msg = new TextComponent("ERR: not_yet_implemented");
        spell.owner.sendMessage(msg, Util.NIL_UUID);

        return spell;
    }

    public static Spell makeFireParticle(Spell spell){

        spell.world.addParticle(ParticleTypes.FLAME, spell.vecPOS.x, spell.vecPOS.y, spell.vecPOS.z, 0.0D, 0.01D, 0.0D);
        System.out.println("created fire particle");

        return spell;
    }

    public static Spell setOriginOnPlayer(Spell spell){

        spell.setPointOrigin(spell.origin);
        System.out.println("set spell origin");

        return spell;
    }

    public static Spell teleportOwner(Spell spell){

        spell.owner.teleportTo(spell.vecPOS.x, spell.vecPOS.y, spell.vecPOS.z);

        TextComponent msg = new TextComponent("teleported player to: " + spell.vecPOS.toString());
        spell.owner.sendMessage(msg, Util.NIL_UUID);

        return spell;
    }

}
