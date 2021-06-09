package io.github.ennuil.damageincorporated.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.SilverfishEntity;

@Mixin(SilverfishEntity.WanderAndInfestGoal.class)
public class WanderAndInfestGoalMixin extends WanderAroundGoal {
	private WanderAndInfestGoalMixin(PathAwareEntity mob, double speed) {
		super(mob, speed);
	}

	@Shadow
	private boolean canInfest;

	@Inject(
		at = @At("RETURN"),
		method = "canStart",
		cancellable = true
	)
	private void canStartMixin(CallbackInfoReturnable<Boolean> cir) {
		boolean returnedValue = cir.getReturnValueZ();
		if (returnedValue && this.canInfest) {
			if (!this.mob.world.getGameRules().getBoolean(DamageIncorporatedMod.CAN_SILVERFISH_INFEST_BLOCKS_RULE)) {
				this.canInfest = false;
				cir.setReturnValue(super.canStart());
			}
		}
	}
}
