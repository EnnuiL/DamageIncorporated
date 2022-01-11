package io.github.ennuil.damageincorporated.mixin.general;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import io.github.ennuil.damageincorporated.utils.DamageIncorporatedUtils.AllowedEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {
	// I really don't like doing this, but well, at least it isn't a @Redirect
	@Inject(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/Entity;isOnFire()Z"
		),
		method = "onEntityCollision(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;)V",
		cancellable = true
	)
	private void cancelIfStatement(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
		boolean cancelPowderSnowBreak = false;
		AllowedEntities gameRuleValue = world.getGameRules().get(DamageIncorporatedMod.CAN_BURNING_MOBS_BREAK_POWDER_SNOW_RULE).get();
		
		if (!gameRuleValue.equals(AllowedEntities.OFF)) {
			if (entity.isOnFire()) {
				if (entity instanceof PlayerEntity) {
					if (gameRuleValue.equals(AllowedEntities.MOB_ONLY)) {
						cancelPowderSnowBreak = true;
					}
				} else {
					if (gameRuleValue.equals(AllowedEntities.PLAYER_ONLY)) {
						cancelPowderSnowBreak = true;
					}
				}
			}
		} else {
			cancelPowderSnowBreak = true;
		}

		// painnnnnnnnnnnnnn
		if (cancelPowderSnowBreak) {
			ci.cancel();
		}
		entity.setOnFire(false);
	}
}
