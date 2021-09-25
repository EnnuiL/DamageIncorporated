package io.github.ennuil.damageincorporated.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;

@Mixin(EnderDragonEntity.class)
public class EnderDragonEntityMixin extends MobEntity {
	private EnderDragonEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
		super(entityType, world);
	}
	
	@ModifyArg(
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
		),
		method = "destroyBlocks(Lnet/minecraft/util/math/Box;)Z"
	)
	private Key<BooleanRule> modifyEnderDragonGameRuleArg(Key<BooleanRule> originalRule) {
		if (this.world.getGameRules().getBoolean(originalRule)) {
			return DamageIncorporatedMod.CAN_ENDER_DRAGON_DESTROY_BLOCKS;
		}
		return originalRule;
	}
}
