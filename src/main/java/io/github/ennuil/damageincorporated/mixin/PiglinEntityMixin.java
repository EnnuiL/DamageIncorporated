package io.github.ennuil.damageincorporated.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@Mixin(PiglinEntity.class)
public abstract class PiglinEntityMixin extends AbstractPiglinEntity {
	private PiglinEntityMixin(EntityType<? extends AbstractPiglinEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(
		at = @At("RETURN"),
		method = "canGather(Lnet/minecraft/item/ItemStack;)Z",
		cancellable = true
	)
	private void controlPiglinGather(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
		if (cir.getReturnValueZ()) {
			if (!this.world.getGameRules().getBoolean(DamageIncorporatedMod.CAN_PIGLINS_GATHER_RULE)) {
				cir.setReturnValue(false);
			}
		}
	}
}