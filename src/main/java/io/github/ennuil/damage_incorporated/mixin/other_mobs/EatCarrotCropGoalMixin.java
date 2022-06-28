package io.github.ennuil.damage_incorporated.mixin.other_mobs;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedGameRules;
import net.minecraft.entity.passive.RabbitEntity;

@Mixin(targets = "net.minecraft.entity.passive.RabbitEntity$EatCarrotCropGoal")
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
		if (!this.rabbit.world.getGameRules().getBoolean(DamageIncorporatedGameRules.CAN_RABBITS_EAT_CARROT_CROPS_RULE)) {
			cir.setReturnValue(false);
		}
	}
}
