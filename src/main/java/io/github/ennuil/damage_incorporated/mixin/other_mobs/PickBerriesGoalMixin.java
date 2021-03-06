package io.github.ennuil.damage_incorporated.mixin.other_mobs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedGameRules;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;

@Mixin(FoxEntity.PickBerriesGoal.class)
public class PickBerriesGoalMixin {
	@Shadow(aliases = "field_17975")
	private FoxEntity field_17975;

	@ModifyArg(
		method = "pickFromTargetPos()V",
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
		)
	)
	private Key<BooleanRule> modifyFoxGoalsGameRuleArg(Key<BooleanRule> originalRule) {
		if (field_17975.world.getGameRules().getBoolean(originalRule)) {
			return DamageIncorporatedGameRules.CAN_FOXES_PICK_BERRIES_RULE;
		}
		return originalRule;
	}
}
