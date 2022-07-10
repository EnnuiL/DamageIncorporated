package io.github.ennuil.damage_incorporated.mixin.hostile;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedGameRules;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;

@Mixin(EvokerEntity.WololoGoal.class)
public abstract class WololoGoalMixin {
	@Shadow(aliases = "field_7268")
	private EvokerEntity field_7268;

	@ModifyArg(
		method = "canStart()Z",
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
		)
	)
	private Key<BooleanRule> modifyWololoGameRuleArg(Key<BooleanRule> originalRule) {
		if (field_7268.world.getGameRules().getBoolean(originalRule)) {
			return DamageIncorporatedGameRules.CAN_EVOKERS_WOLOLO_RULE;
		}
		return originalRule;
	}
}
