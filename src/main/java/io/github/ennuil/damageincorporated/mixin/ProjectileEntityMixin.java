package io.github.ennuil.damageincorporated.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import io.github.ennuil.damageincorporated.utils.DamageIncorporatedUtils.AllowedEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

@Mixin(ProjectileEntity.class)
public class ProjectileEntityMixin {
	@Unique
	private AllowedEntities storedGameRuleValue;

	@Inject(
		at = @At("HEAD"),
		method = "canModifyAt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Z"
	)
	private void getCanModifyAtArgs(World world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		this.storedGameRuleValue = world.getGameRules().get(DamageIncorporatedMod.CAN_BURNING_PROJECTILES_MODIFY_BLOCKS_RULE).get();
	}

	// why
	@Inject(
		at = @At("RETURN"),
		method = "canModifyAt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Z",
		cancellable = true,
		locals = LocalCapture.CAPTURE_FAILHARD
	)
	private void modifyProjectileReturnValue(World world, BlockPos pos, CallbackInfoReturnable<Boolean> cir, Entity entity) {
		switch (this.storedGameRuleValue) {
			case PLAYER_ONLY -> cir.setReturnValue(entity instanceof PlayerEntity ? entity.canModifyAt(world, pos) : entity == null);
			case MOB_ONLY -> cir.setReturnValue((entity == null || world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) && !(entity instanceof PlayerEntity));
			case OFF -> cir.setReturnValue(false);
			default -> {}
		}
	}
}
