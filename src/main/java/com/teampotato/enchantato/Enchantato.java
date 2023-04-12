package com.teampotato.enchantato;

import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.util.List;

@Mod("enchantato")
public class Enchantato {
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> ENCHANTMENT_LIST;

    public Enchantato() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_CONFIG);
    }

    static {
        ForgeConfigSpec.Builder CONFIG_BUILDER = new ForgeConfigSpec.Builder();
        CONFIG_BUILDER.comment("Enchantato").push("General");
        ENCHANTMENT_LIST = CONFIG_BUILDER.defineList("enchantments that will be forbidden", Lists.newArrayList(), o -> o instanceof String);
        CONFIG_BUILDER.pop();
        COMMON_CONFIG = CONFIG_BUILDER.build();
    }
}
