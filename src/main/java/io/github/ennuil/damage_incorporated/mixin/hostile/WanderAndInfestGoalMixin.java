package io.github.ennuil.damage_incorporated.mixin.hostile;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedGameRules;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;

@Mixin(targets = "net/minecraft/entity/mob/SilverfishEntity$WanderAndInfestGoal")
public class WanderAndInfestGoalMixin extends WanderAroundGoal {
	private WanderAndInfestGoalMixin(PathAwareEntity mob, double speed) {
		super(mob, speed);
	}

	@ModifyArg(
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
		),
		method = "canStart()Z"
	)
	private Key<BooleanRule> modifySilverfishGameRuleArg(Key<BooleanRule> originalRule) {
		if (this.mob.world.getGameRules().getBoolean(originalRule)) {
			return DamageIncorporatedGameRules.CAN_SILVERFISH_INFEST_BLOCKS_RULE;
		}
		return originalRule;
	}
}
