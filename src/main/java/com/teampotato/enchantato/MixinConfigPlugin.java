package com.teampotato.enchantato;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraftforge.fml.loading.FMLLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.*;
import java.util.List;
import java.util.Set;

public class MixinConfigPlugin implements IMixinConfigPlugin {
    public static boolean initFailed;

    private boolean disableAnvilUsage;
    private boolean disableEnchantCommandUsage;
    private boolean disableEnchantmentTableUsage;
    private boolean disableLootUsage;

    public static final Logger LOGGER = LogManager.getLogger(MixinConfigPlugin.class);

    public MixinConfigPlugin() {
        File config = new File(FMLLoader.getGamePath().toFile(), "config");
        config.mkdirs();
        File configFile = new File(config, Enchantato.MOD_ID + "-mixins.json");
        if (!configFile.exists()) {
            try {
                FileWriter writer = writeFile(configFile);
                writer.close();
            } catch (Exception e) {
                initFailed = true;
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            JsonObject configObj = new JsonParser().parse(reader).getAsJsonObject();
            this.disableAnvilUsage = configObj.get("disableAnvilUsage").getAsBoolean();
            this.disableEnchantCommandUsage = configObj.get("disableEnchantCommandUsage").getAsBoolean();
            this.disableEnchantmentTableUsage = configObj.get("disableEnchantmentTableUsage").getAsBoolean();
            this.disableLootUsage = configObj.get("disableLootUsage").getAsBoolean();
        } catch (Throwable e) {
            initFailed = true;
        }
    }

    @NotNull
    private static FileWriter writeFile(File configFile) throws IOException {
        JsonObject defaultConfig = new JsonObject();
        defaultConfig.addProperty("disableAnvilUsage", true);
        defaultConfig.addProperty("disableEnchantCommandUsage", true);
        defaultConfig.addProperty("disableEnchantmentTableUsage", true);
        defaultConfig.addProperty("disableLootUsage", true);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = new FileWriter(configFile);
        gson.toJson(defaultConfig, writer);
        return writer;
    }

    @Override
    public void onLoad(String s) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String s, @NotNull String s1) {
        if (s1.contains("MixinAnvilMenu")) {
            LOGGER.debug("MixinAnvilMenu: " + this.disableAnvilUsage);
            return this.disableAnvilUsage;
        }
        if (s1.contains("MixinEnchantCommand")) {
            LOGGER.debug("MixinEnchantCommand: " + this.disableEnchantCommandUsage);
            return this.disableEnchantCommandUsage;
        }
        if (s1.contains("MixinEnchantmentHelper")) {
            LOGGER.debug("MixinEnchantmentHelper: " + this.disableEnchantmentTableUsage);
            return this.disableEnchantmentTableUsage;
        }
        if (s1.contains("MixinEnchantRandomlyFunction")) {
            LOGGER.debug("MixinEnchantRandomlyFunction: " + this.disableLootUsage);
            return this.disableLootUsage;
        }
        return false;//This should not happen
    }

    @Override
    public void acceptTargets(Set<String> set, Set<String> set1) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }

    @Override
    public void postApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }
}
