package io.github.ennuil.damage_incorporated.mixin.undead;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedGameRules;
import net.minecraft.entity.ai.goal.BreakDoorGoal;
import net.minecraft.entity.ai.goal.DoorInteractGoal;
import net.minecraft.entity.mob.MobEntity;

@Mixin(BreakDoorGoal.class)
public class BreakDoorGoalMixin extends DoorInteractGoal {
	private BreakDoorGoalMixin(MobEntity mob) {
        super(mob);
    }

    @Inject(
		method = "canStart()Z",
		at = @At("RETURN"),
		cancellable = true
	)
	private void controlDoorBreak(CallbackInfoReturnable<Boolean> cir) {
		if (cir.getReturnValueZ()) {
			if (!this.mob.world.getGameRules().getBoolean(DamageIncorporatedGameRules.CAN_MOBS_BREAK_DOORS_RULE)) {
				cir.setReturnValue(false);
			}
		}
	}
}
