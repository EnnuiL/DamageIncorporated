package io.github.ennuil.damageincorporated.mixin;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
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
				return DamageIncorporatedMod.CAN_ZOMBIES_TARGET_TURTLE_EGGS_RULE;
			} else if (mobType.equals(EntityType.ZOMBIFIED_PIGLIN)) {
				return DamageIncorporatedMod.CAN_ZOMBIFIED_PIGLINS_TARGET_TURTLE_EGGS_RULE;
			} else if (mobType.equals(EntityType.ZOMBIE_VILLAGER)) {
				return DamageIncorporatedMod.CAN_ZOMBIE_VILLAGERS_TARGET_TURTLE_EGGS_RULE;
			} else if (mobType.equals(EntityType.DROWNED)) {
				return DamageIncorporatedMod.CAN_DROWNEDS_TARGET_TURTLE_EGGS_RULE;
			} else if (mobType.equals(EntityType.HUSK)) {
				return DamageIncorporatedMod.CAN_HUSKS_TARGET_TURTLE_EGGS_RULE;
			}
		}
		return originalRule;
	}
}
