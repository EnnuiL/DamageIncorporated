package io.github.joaoh1.damageincorporated.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.joaoh1.damageincorporated.DamageIncorporatedMod;
import net.minecraft.entity.mob.EndermanEntity;

@Mixin(EndermanEntity.PlaceBlockGoal.class)
public class PlaceBlockGoalMixin {
	@Shadow
	@Final
	private EndermanEntity enderman;

	@Inject(
		at = @At("RETURN"),
		method = "canStart",
		cancellable = true
	)
	private boolean controlEndermanPickUp(CallbackInfoReturnable<Boolean> cir) {
		boolean returnedValue = cir.getReturnValueZ();
		if (returnedValue) {
			if (!this.enderman.world.getGameRules().getBoolean(DamageIncorporatedMod.CAN_ENDERMEN_PLACE_BLOCKS_RULE)) {
				cir.setReturnValue(false);
				return false;
			}   
		}
		return returnedValue;
	}
}