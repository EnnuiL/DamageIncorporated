package io.github.ennuil.damageincorporated.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;

@Mixin(ProjectileEntity.class)
public class ProjectileEntityMixin {
	@Unique
	private World storedWorld;

	@Inject(
		at = @At("HEAD"),
		method = "canModifyAt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Z"
	)
	private void getCanModifyAtArgs(World world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		this.storedWorld = world;
	}

	@ModifyArg(
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
		),
		method = "canModifyAt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Z"
	)
	private Key<BooleanRule> modifyProjectileGameRule(Key<BooleanRule> originalRule) {
		if (this.storedWorld.getGameRules().getBoolean(originalRule)) {
			return DamageIncorporatedMod.CAN_BURNING_ARROWS_FROM_MOBS_MODIFY_BLOCKS_RULE;
		}
		return originalRule;
	}
}
