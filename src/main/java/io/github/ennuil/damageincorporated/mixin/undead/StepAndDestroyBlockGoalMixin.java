package io.github.ennuil.damageincorporated.mixin.undead;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.StepAndDestroyBlockGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import io.github.ennuil.damageincorporated.game_rules.DamageIncorporatedGameRules;

@Mixin(StepAndDestroyBlockGoal.class)
public class StepAndDestroyBlockGoalMixin {
	@Shadow
	@Final
	private MobEntity stepAndDestroyMob;

	@ModifyArg(
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
		),
		method = "canStart()Z",
		index = 0
	)
	private Key<BooleanRule> modifyStepAndDestroyGameRuleArg(Key<BooleanRule> originalRule) {
		if (this.stepAndDestroyMob.world.getGameRules().getBoolean(originalRule)) {
			EntityType<?> mobType = this.stepAndDestroyMob.getType();
			if (mobType.equals(EntityType.ZOMBIE)) {
				return DamageIncorporatedGameRules.CAN_TURTLE_EGGS_BE_STOMPED_BY_ZOMBIES_RULE;
			} else if (mobType.equals(EntityType.ZOMBIFIED_PIGLIN)) {
				return DamageIncorporatedGameRules.CAN_TURTLE_EGGS_BE_STOMPED_BY_ZOMBIFIED_PIGLINS_RULE;
			} else if (mobType.equals(EntityType.ZOMBIE_VILLAGER)) {
				return DamageIncorporatedGameRules.CAN_TURTLE_EGGS_BE_STOMPED_BY_ZOMBIE_VILLAGERS_RULE;
			} else if (mobType.equals(EntityType.HUSK)) {
				return DamageIncorporatedGameRules.CAN_TURTLE_EGGS_BE_STOMPED_BY_HUSKS_RULE;
			} else if (mobType.equals(EntityType.DROWNED)) {
				return DamageIncorporatedGameRules.CAN_TURTLE_EGGS_BE_STOMPED_BY_DROWNEDS_RULE;
			}
		}
		return originalRule;
	}
}
