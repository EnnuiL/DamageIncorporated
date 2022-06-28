package io.github.ennuil.damage_incorporated.mixin.hostile;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedGameRules;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.world.World;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;

@Mixin(SmallFireballEntity.class)
public class SmallFireballEntityMixin extends AbstractFireballEntity {
	private SmallFireballEntityMixin(EntityType<? extends AbstractFireballEntity> entityType, World world) {
		super(entityType, world);
	}

	@ModifyArg(
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
		),
		method = "onBlockHit(Lnet/minecraft/util/hit/BlockHitResult;)V"
	)
	private Key<BooleanRule> modifyBlazeFireballGameRuleArg(Key<BooleanRule> originalRule) {
		if (this.world.getGameRules().getBoolean(originalRule)) {
			return DamageIncorporatedGameRules.CAN_BLAZE_FIREBALLS_SPREAD_FIRE_RULE;
		}
		return originalRule;
	}
}
