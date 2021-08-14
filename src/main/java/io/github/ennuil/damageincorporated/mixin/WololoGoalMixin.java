package io.github.ennuil.damageincorporated.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;

@Mixin(EvokerEntity.WololoGoal.class)
public abstract class WololoGoalMixin {
	@Shadow
	private EvokerEntity field_7268;

    @ModifyArg(
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
		),
		method = "canStart"
	)
	private Key<BooleanRule> modifyGameRule(Key<BooleanRule> originalRule) {
		if (field_7268.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
			return DamageIncorporatedMod.CAN_EVOKERS_RECOLOR_BLUE_SHEEP_RULE;
		}
		return originalRule;
	}
}
