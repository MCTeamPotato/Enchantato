package com.teampotato.enchantato.mixin;

import com.teampotato.enchantato.Enchantato;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(value = Enchantment.class, remap = false)
public class MixinEnchantment extends ForgeRegistryEntry<Enchantment> {
    @Inject(method = "canApplyAtEnchantingTable", at = @At("HEAD"), cancellable = true)
    private void banEnchantments(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (Enchantato.ENCHANTMENT_LIST.get().contains(Objects.requireNonNull(this.getRegistryName()).toString())) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
