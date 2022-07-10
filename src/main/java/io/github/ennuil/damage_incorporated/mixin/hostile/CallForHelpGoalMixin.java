package io.github.ennuil.damage_incorporated.mixin.hostile;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedGameRules;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;

@Mixin(targets = "net/minecraft/entity/mob/SilverfishEntity$CallForHelpGoal")
public class CallForHelpGoalMixin {
	@Shadow
	@Final
	private SilverfishEntity silverfish;

	@ModifyArg(
		method = "tick()V",
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
		),
		index = 0
	)
	private Key<BooleanRule> modifyCallForHelpGameRuleArg(Key<BooleanRule> originalRule) {
		if (this.silverfish.world.getGameRules().getBoolean(originalRule)) {
			return DamageIncorporatedGameRules.PERMANENT_INFESTED_BLOCK_DAMAGE_RULE;
		}
		return originalRule;
	}
}
