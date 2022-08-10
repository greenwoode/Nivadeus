package com.eight.nivadeus.spells;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.swing.text.html.parser.Entity;

public class SpellCasting {

    public static void cast(Level world, Player holder, String spellEncoded){

        Spell spell = new Spell(holder, world);

        parseAndCast(spell, spellEncoded);

    }

    private static Spell parseAndCast(Spell spell, String spellEncoded){

        System.out.println(spell.vecPOS.toString());

        int split;
        String type = "null";
        String argsAll;

        if (spellEncoded.isEmpty()){
            System.out.println("end of Recursion for spell");
            return spell;
        }else{
            System.out.println("parsing \"" + spellEncoded + "\"");
        }


        if(spellEncoded.contains("(")){
            split = spellEncoded.indexOf('(');
            type = spellEncoded.substring(0, split);
            argsAll = spellEncoded.substring(split + 1, spellEncoded.length()-1);
        }else{
            argsAll = spellEncoded;
        }

        String[] args = SpellCasting.splitArgs(argsAll);

        //System.out.println(type); //used to check parsing
        //System.out.println(args); //used to check parsing

        switch (type){

            case "MU":
                return SpellComponents.moveUp(parseAndCast(spell, args[0]), Double.parseDouble(args[1]));

            case "MD":
                return SpellComponents.moveDown(parseAndCast(spell, args[0]), Double.parseDouble(args[1]));

            case "MN":
                return SpellComponents.moveNorth(parseAndCast(spell, args[0]), Double.parseDouble(args[1]));

            case "MS":
                return SpellComponents.moveSouth(parseAndCast(spell, args[0]), Double.parseDouble(args[1]));

            case "ME":
                return SpellComponents.moveEast(parseAndCast(spell, args[0]), Double.parseDouble(args[1]));

            case "MW":
                return SpellComponents.moveWest(parseAndCast(spell, args[0]), Double.parseDouble(args[1]));

            case "MTP":
                return SpellComponents.moveTowardPlayer(parseAndCast(spell, args[0]), Double.parseDouble(args[1]));

            case "MAP":
                return SpellComponents.moveAwayPlayer(parseAndCast(spell, args[0]), Double.parseDouble(args[1]));

            case "MPF":
                return SpellComponents.movePlayerFacing(parseAndCast(spell, args[0]), Double.parseDouble(args[1]));

            case "FP":
                return SpellComponents.makeFireParticle(parseAndCast(spell, args[0]));

            case "OOP":
                return SpellComponents.setOriginOnPlayer(parseAndCast(spell, args[0]));

            case "TP":
                return SpellComponents.teleportOwner(parseAndCast(spell, args[0]));

            case "SCP":
                return SpellComponents.singleCircleParticle(parseAndCast(spell, args[0]), Double.parseDouble(args[1]), Integer.parseInt(args[2]), Double.parseDouble(args[3]));

            case "MCP":
                return SpellComponents.multiCircleParticle(parseAndCast(spell, args[0]), Integer.parseInt(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]), Integer.parseInt(args[4]));

            case "LCU":
                return SpellComponents.launchCasterUp(parseAndCast(spell, args[0]),Double.parseDouble(args[1]));

            default:
                System.out.println("spell type not found");
                return spell;
        }

    }

    private static String[] splitArgs(String argsJoined){

        int lastClose = argsJoined.lastIndexOf(')')+1;

        int count = (int)(argsJoined.substring(lastClose).chars().filter(ch -> ch == ',').count()) + 1;

        String[] args = new String[count];

        args[0] = argsJoined.substring(0, lastClose);
        String remArgs = argsJoined.substring(lastClose);
        for(int k = count - 1; k > 0; k--){
            args[k] = remArgs.substring(remArgs.lastIndexOf(',') + 1);
            remArgs = remArgs.substring( 0, remArgs.lastIndexOf(','));
        }


        return args;
    }
    private static int countArgs(String args){
        return -1;
    }


}
