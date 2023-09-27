package com.teampotato.enchantato;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.util.List;

@Mod(Enchantato.MOD_ID)
public class Enchantato {
    public static final String MOD_ID = "enchantato";
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> ENCHANTMENT_LIST;
    public static ForgeConfigSpec.BooleanValue INVERTED_MODE;

    public Enchantato() {
        if (MixinConfigPlugin.initFailed) throw new UnsupportedOperationException("Enchantato Mixin Config Initialization failed to process.");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_CONFIG, MOD_ID + "-common.toml");
    }

    static {
        ForgeConfigSpec.Builder CONFIG_BUILDER = new ForgeConfigSpec.Builder();
        CONFIG_BUILDER.push("Enchantato");
        INVERTED_MODE = CONFIG_BUILDER.comment("If you enable this, the 'DisabledEnchantments' will become 'AllowedEnchantments'").define("InvertedMode", false);
        ENCHANTMENT_LIST = CONFIG_BUILDER.defineList("DisabledEnchantments", new ObjectArrayList<>(), o -> true);
        CONFIG_BUILDER.pop();
        COMMON_CONFIG = CONFIG_BUILDER.build();
    }
}