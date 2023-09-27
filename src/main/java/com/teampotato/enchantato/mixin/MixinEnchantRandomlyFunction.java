package com.teampotato.enchantato.mixin;

import com.teampotato.enchantato.Enchantato;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("DataFlowIssue")
@Mixin(EnchantRandomlyFunction.class)
public abstract class MixinEnchantRandomlyFunction {
    @Dynamic
    @Redirect(method = {"method_26267", "lambda$run$0", "func_237421_a_"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment;canEnchant(Lnet/minecraft/world/item/ItemStack;)Z"))
    private static boolean onEnchant(@NotNull Enchantment instance, ItemStack arg) {
        if (!Enchantato.INVERTED_MODE.get()) {
            if (Enchantato.ENCHANTMENT_LIST.get().contains(instance.getRegistryName().toString())) return false;
            return instance.canEnchant(arg);
        } else {
            return instance.canEnchant(arg) && Enchantato.ENCHANTMENT_LIST.get().contains(instance.getRegistryName().toString());
        }
    }
}
