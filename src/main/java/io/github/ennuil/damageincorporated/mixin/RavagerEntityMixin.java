package io.github.ennuil.damageincorporated.mixin;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.world.World;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(RavagerEntity.class)
public abstract class RavagerEntityMixin extends RaiderEntity {
	private RavagerEntityMixin(EntityType<? extends RaiderEntity> entityType, World world) {
		super(entityType, world);
	}

	@ModifyArg(
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
		),
		method = "tickMovement",
		index = 0
	)
	private Key<BooleanRule> modifyRavagerLeavesGameRuleArg(Key<BooleanRule> originalRule) {
		if (this.world.getGameRules().getBoolean(originalRule)) {
			return DamageIncorporatedMod.CAN_RAVAGERS_BREAK_LEAVES_RULE;
		}
		return originalRule;
	}
}
