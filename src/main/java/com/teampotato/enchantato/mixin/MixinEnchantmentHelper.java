package com.teampotato.enchantato.mixin;

import com.teampotato.enchantato.Enchantato;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("DataFlowIssue")
@Mixin(EnchantmentHelper.class)
public abstract class MixinEnchantmentHelper {
    @Redirect(method = "getAvailableEnchantmentResults", at = @At(value = "INVOKE", remap = false, target = "Lnet/minecraft/world/item/enchantment/Enchantment;canApplyAtEnchantingTable(Lnet/minecraft/world/item/ItemStack;)Z"))
    private static boolean onGetEnchantments(Enchantment instance, ItemStack arg) {
        if (!Enchantato.INVERTED_MODE.get()) {
            if (Enchantato.ENCHANTMENT_LIST.get().contains(ForgeRegistries.ENCHANTMENTS.getKey(instance).toString()))
                return false;
            return instance.canApplyAtEnchantingTable(arg);
        } else {
            return instance.canApplyAtEnchantingTable(arg) && Enchantato.ENCHANTMENT_LIST.get().contains(ForgeRegistries.ENCHANTMENTS.getKey(instance).toString());
        }
    }
}
