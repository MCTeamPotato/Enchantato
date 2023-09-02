package com.teampotato.enchantato.mixin;

import com.teampotato.enchantato.Enchantato;
import net.minecraft.server.commands.EnchantCommand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("DataFlowIssue")
@Mixin(EnchantCommand.class)
public abstract class MixinEnchantCommand {
    @Redirect(method = "enchant", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment;canEnchant(Lnet/minecraft/world/item/ItemStack;)Z"))
    private static boolean onEnchant(Enchantment instance, ItemStack arg) {
        if (Enchantato.ENCHANTMENT_LIST.get().contains(instance.getRegistryName().toString())) return false;
        return instance.canEnchant(arg);
    }
}
