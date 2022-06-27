package com.eight.nivadeus.init;

import com.eight.nivadeus.Nivadeus;

import com.eight.nivadeus.item.Wand;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Nivadeus.MOD_ID);

    public static final RegistryObject<Item> MANA_EYE = register("mana_eye",
            () -> new Item(
                    new Item.Properties().tab(Nivadeus.NIVADEUS_TAB).stacksTo(8)
            ));

    public static final RegistryObject<Item> WAND_FIRE = register("wand_test_fire",
            () -> new Wand(
                    new Item.Properties().tab(Nivadeus.NIVADEUS_TAB).stacksTo(1)
            ));

    private static <T extends Item>RegistryObject<T> register(final String name, final Supplier<T> item) {
        return ITEMS.register(name, item);
    }

}
