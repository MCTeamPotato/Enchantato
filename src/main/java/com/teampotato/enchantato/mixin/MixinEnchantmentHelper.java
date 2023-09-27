package com.teampotato.enchantato.mixin;

import com.teampotato.enchantato.Enchantato;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("DataFlowIssue")
@Mixin(EnchantmentHelper.class)
public abstract class MixinEnchantmentHelper {
    @Redirect(method = "getAvailableEnchantmentResults", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment;canApplyAtEnchantingTable(Lnet/minecraft/world/item/ItemStack;)Z"))
    private static boolean onGetEnchantments(@NotNull Enchantment instance, ItemStack stack) {
        if (!Enchantato.INVERTED_MODE.get()) {
            if (Enchantato.ENCHANTMENT_LIST.get().contains(instance.getRegistryName().toString())) return false;
            return instance.canApplyAtEnchantingTable(stack);
        } else {
            return instance.canApplyAtEnchantingTable(stack) && Enchantato.ENCHANTMENT_LIST.get().contains(instance.getRegistryName().toString());
        }
    }
}
