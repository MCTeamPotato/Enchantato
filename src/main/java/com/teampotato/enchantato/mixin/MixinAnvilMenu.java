package com.teampotato.enchantato.mixin;

import com.teampotato.enchantato.Enchantato;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("DataFlowIssue")
@Mixin(AnvilMenu.class)
public abstract class MixinAnvilMenu {
    @Redirect(method = "createResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment;canEnchant(Lnet/minecraft/world/item/ItemStack;)Z"))
    private boolean onEnchant(@NotNull Enchantment instance, ItemStack arg) {
        if (!Enchantato.INVERTED_MODE.get()) {
            if (Enchantato.ENCHANTMENT_LIST.get().contains(instance.getRegistryName().toString())) return false;
            return instance.canEnchant(arg);
        } else {
            return instance.canEnchant(arg) && Enchantato.ENCHANTMENT_LIST.get().contains(instance.getRegistryName().toString());
        }
    }
}
