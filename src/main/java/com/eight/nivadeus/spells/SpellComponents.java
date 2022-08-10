package com.eight.nivadeus.spells;


import net.minecraft.Util;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.common.data.SoundDefinition;


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
        spell.vecPOS = spell.vecPOS.add(spell.origin.vectorTo(spell.vecPOS).normalize().multiply(-dis,-dis,-dis));

        return spell;
    }
    public static Spell moveAwayPlayer(Spell spell, double dis){
        spell.vecPOS = spell.vecPOS.add(spell.origin.vectorTo(spell.vecPOS).normalize().multiply(dis,dis,dis));

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

    public static Spell singleCircleParticle(Spell spell, double r, int resolution, double offset){


        double step = (Math.PI*2.0)/resolution;

        for(int i = 0; i < resolution; i++){

            double xOff = r*Math.sin(i*step + offset);
            double zOff = r*Math.cos(i*step + offset);

            spell.world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, spell.vecPOS.x + xOff, spell.vecPOS.y, spell.vecPOS.z + zOff, xOff/10, 0.1D, zOff/10);
            spell.world.playSound(spell.owner, spell.vecPOS.x + xOff, spell.vecPOS.y, spell.vecPOS.z + zOff, SoundEvents.BLAZE_BURN, SoundSource.AMBIENT, 0.1f, 0.5f);

        }


        return spell;
    }

    public static Spell multiCircleParticle(Spell spell, int count, double rStart, double rEnd, int resolution){

        double step = (rEnd - rStart)/(count-1);

        for(double i = rStart; i <= rEnd; i += step ){

            double offset = (step/count) * i;

            singleCircleParticle(spell, i, resolution, offset);

        }

        return spell;
    }

    public static Spell launchCasterUp(Spell spell, double force){
        Vec3 vect = new Vec3(0.0f, force, 0.0f);
        //spell.owner.move(MoverType.SELF, vect);
        spell.owner.push(0.0, force, 0.0);

        spell.world.playSound(spell.owner, spell.owner.getX(), spell.owner.getY(), spell.owner.getZ(), SoundEvents.FIREWORK_ROCKET_SHOOT, SoundSource.AMBIENT, 1.0f, 0.2f);

        return spell;
    }

    public static Spell launchCasterForward(Spell spell, double force){
        Vec3 vect = spell.owner.getForward();
        spell.owner.push(vect.x * force, Math.sqrt(force), vect.z * force);

        spell.world.playSound(spell.owner, spell.owner.getX(), spell.owner.getY(), spell.owner.getZ(), SoundEvents.FIREWORK_ROCKET_SHOOT, SoundSource.AMBIENT, 1.0f, 0.2f);

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
