package io.github.ennuil.damage_incorporated.mixin.general;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedGameRules;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {
	private MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@ModifyArg(
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z",
			ordinal = 0
		),
		method = "tickMovement()V"
	)
	private Key<BooleanRule> modifyMobPickUpGameRuleArg(Key<BooleanRule> originalRule) {
		if (this.world.getGameRules().getBoolean(originalRule)) {
			return DamageIncorporatedGameRules.CAN_MOBS_PICK_UP_LOOT_RULE;
		}
		return originalRule;
	}
}
