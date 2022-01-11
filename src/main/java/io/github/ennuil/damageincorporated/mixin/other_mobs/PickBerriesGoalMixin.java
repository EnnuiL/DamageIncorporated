package io.github.ennuil.damageincorporated.mixin.other_mobs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;

@Mixin(FoxEntity.PickBerriesGoal.class)
public class PickBerriesGoalMixin {
	@Shadow(aliases = "field_17975")
	private FoxEntity field_17975;
	
	@ModifyArg(
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
		),
		method = "pickFromTargetPos()V"
	)
	private Key<BooleanRule> modifyFoxGoalsGameRuleArg(Key<BooleanRule> originalRule) {
		if (field_17975.world.getGameRules().getBoolean(originalRule)) {
			return DamageIncorporatedMod.CAN_FOXES_PICK_BERRIES_RULE;
		}
		return originalRule;
	}
}
