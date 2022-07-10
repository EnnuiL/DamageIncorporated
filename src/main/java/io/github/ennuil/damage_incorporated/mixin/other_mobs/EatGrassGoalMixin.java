package io.github.ennuil.damage_incorporated.mixin.other_mobs;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedGameRules;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.world.World;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;

@Mixin(EatGrassGoal.class)
public class EatGrassGoalMixin {
	@Shadow
	@Final
	private World world;

	@ModifyArg(
		method = "tick()V",
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z",
			ordinal = 0
		)
	)
	private Key<BooleanRule> modifyGrassGameRule(Key<BooleanRule> originalRule) {
		if (this.world.getGameRules().getBoolean(originalRule)) {
			return DamageIncorporatedGameRules.CAN_SHEEP_BREAK_GRASS_RULE;
		}
		return originalRule;
	}

	@ModifyArg(
		method = "tick()V",
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z",
			ordinal = 1
		)
	)
	private Key<BooleanRule> modifyGrassBlockGameRule(Key<BooleanRule> originalRule) {
		if (this.world.getGameRules().getBoolean(originalRule)) {
			return DamageIncorporatedGameRules.CAN_SHEEP_TURN_GRASS_BLOCKS_INTO_DIRT_RULE;
		}
		return originalRule;
	}
}
