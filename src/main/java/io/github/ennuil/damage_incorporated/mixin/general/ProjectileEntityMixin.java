package io.github.ennuil.damage_incorporated.mixin.general;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedGameRules;
import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedEnums.AllowedEntities;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

// FIXME - This is broken
@Mixin(ProjectileEntity.class)
public class ProjectileEntityMixin {
	@Unique
	private AllowedEntities storedGameRuleValue;

	@Inject(
		at = @At("HEAD"),
		method = "canModifyAt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Z"
	)
	private void getCanModifyAtArgs(World world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		this.storedGameRuleValue = world.getGameRules().get(DamageIncorporatedGameRules.CAN_BURNING_PROJECTILES_MODIFY_BLOCKS_RULE).get();
	}

	@ModifyExpressionValue(
		method = "canModifyAt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Z",
		at = @At(value = "RETURN", ordinal = 0)
	)
	private boolean modifyProjectilePlayerCondition(boolean original) {
		return original && this.storedGameRuleValue != AllowedEntities.MOB_ONLY && this.storedGameRuleValue != AllowedEntities.OFF;
	}

	@ModifyExpressionValue(
		method = "canModifyAt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Z",
		at = @At(value = "RETURN", ordinal = 1)
	)
	private boolean modifyProjectileGenericCondition(boolean original) {
		return original && this.storedGameRuleValue != AllowedEntities.PLAYER_ONLY && this.storedGameRuleValue != AllowedEntities.OFF;
	}
}
