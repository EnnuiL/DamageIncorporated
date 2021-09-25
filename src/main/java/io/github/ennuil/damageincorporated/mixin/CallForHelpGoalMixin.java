package io.github.ennuil.damageincorporated.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;

@Mixin(SilverfishEntity.CallForHelpGoal.class)
public class CallForHelpGoalMixin {
	@Shadow
	@Final
	private SilverfishEntity silverfish;
	
	@ModifyArg(
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
		),
		method = "tick()V",
		index = 0
	)
	private Key<BooleanRule> modifySilverfishHelpGameRuleArg(Key<BooleanRule> originalRule) {
		if (this.silverfish.world.getGameRules().getBoolean(originalRule)) {
			return DamageIncorporatedMod.PERMANENT_INFESTED_BLOCK_DAMAGE_RULE;
		}
		return originalRule;
	}
}
