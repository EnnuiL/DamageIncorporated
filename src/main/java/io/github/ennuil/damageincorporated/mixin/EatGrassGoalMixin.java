package io.github.ennuil.damageincorporated.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;

@Mixin(EatGrassGoal.class)
public class EatGrassGoalMixin {
    @Shadow
    @Final
    private MobEntity mob;

    @ModifyArg(
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z",
            ordinal = 0
		),
		method = "tick()V"
	)
	private Key<BooleanRule> modifyGrassGameRule(Key<BooleanRule> originalRule) {
		if (this.mob.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
			return DamageIncorporatedMod.CAN_SHEEP_BREAK_GRASS_RULE;
		}
		return originalRule;
	}

    @ModifyArg(
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z",
            ordinal = 1
		),
		method = "tick()V"
	)
	private Key<BooleanRule> modifyGrassBlockGameRule(Key<BooleanRule> originalRule) {
		if (this.mob.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
			return DamageIncorporatedMod.CAN_SHEEP_TURN_GRASS_BLOCKS_INTO_DIRT_RULE;
		}
		return originalRule;
	}
}
