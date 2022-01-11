package io.github.ennuil.damageincorporated.mixin.other_mobs;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import net.minecraft.entity.passive.RabbitEntity;

@Mixin(RabbitEntity.EatCarrotCropGoal.class)
public class EatCarrotCropGoalMixin {
	@Shadow
	@Final
	private RabbitEntity rabbit;

	@Inject(
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/entity/passive/RabbitEntity$EatCarrotCropGoal;hasTarget:Z"
		),
		method = "canStart()Z",
		cancellable = true
	)
	private void controlRabbitCarrotEating(CallbackInfoReturnable<Boolean> cir) {
		if (!this.rabbit.world.getGameRules().getBoolean(DamageIncorporatedMod.CAN_RABBITS_EAT_CARROT_CROPS_RULE)) {
			cir.setReturnValue(false);
		}
	}
}
