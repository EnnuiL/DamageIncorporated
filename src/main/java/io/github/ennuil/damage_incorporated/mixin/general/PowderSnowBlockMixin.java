package io.github.ennuil.damage_incorporated.mixin.general;

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

import com.llamalad7.mixinextras.injector.WrapWithCondition;

import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedGameRules;
import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedEnums.AllowedEntities;

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
		AllowedEntities gameRuleValue = world.getGameRules().get(DamageIncorporatedGameRules.CAN_BURNING_MOBS_BREAK_POWDER_SNOW_RULE).get();

		if (!gameRuleValue.equals(AllowedEntities.ALL) && !gameRuleValue.equals(AllowedEntities.OFF)) {
			if (entity.isOnFire()) {
				cancelPowderSnowBreak = gameRuleValue.equals(AllowedEntities.MOB_ONLY) == entity instanceof PlayerEntity;
			}
		} else {
			cancelPowderSnowBreak = gameRuleValue.equals(AllowedEntities.OFF);
		}

		// painnnnnnnnnnnnnn
		if (cancelPowderSnowBreak) {
			ci.cancel();
			entity.setOnFire(false);
		}
	}

	// TODO - i hate switching pcs
	/*
	@WrapWithCondition(
		method = "onEntityCollision(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;)V",
		at = @At("")
	)
	private boolean condition() {}
	*/
}
