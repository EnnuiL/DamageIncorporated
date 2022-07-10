
package io.github.ennuil.damage_incorporated.mixin.general;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedGameRules;
import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedEnums.AllowedEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

// I don't like this mixin at all, but oh well
@Mixin(ProjectileEntity.class)
public abstract class ProjectileEntityMixin {
	@Unique
	private AllowedEntities di$storedGameRuleValue;

	@Shadow
	public abstract Entity getOwner();

	@Inject(
		method = "canModifyAt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Z",
		at = @At("HEAD")
	)
	private void getCanModifyAtArgs(World world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		this.di$storedGameRuleValue = world.getGameRules().get(DamageIncorporatedGameRules.CAN_BURNING_PROJECTILES_MODIFY_BLOCKS_RULE).get();
	}

	@Inject(
		method = "canModifyAt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Z",
		at = @At(
			value = "RETURN",
			ordinal = 0
		),
		cancellable = true
	)
	private void modifyPlayerProjectileReturnValue(World world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		switch (this.di$storedGameRuleValue) {
			case MOB_ONLY -> cir.setReturnValue(false);
			case OFF -> cir.setReturnValue(false);
			default -> {}
		}
	}

	@Inject(
		method = "canModifyAt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Z",
		at = @At(
			value = "RETURN",
			ordinal = 1
		),
		cancellable = true
	)
	private void modifyProjectileReturnValue(World world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		Entity entity = this.getOwner();

		switch (this.di$storedGameRuleValue) {
			case PLAYER_ONLY -> cir.setReturnValue(false);
			case MOB_ONLY -> cir.setReturnValue((entity == null || world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) && !(entity instanceof PlayerEntity));
			case OFF -> cir.setReturnValue(entity == null);
			default -> {}
		}
	}
}
