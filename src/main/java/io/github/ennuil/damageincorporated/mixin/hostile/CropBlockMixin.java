package io.github.ennuil.damageincorporated.mixin.hostile;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;

@Mixin(CropBlock.class)
public class CropBlockMixin {
    @Unique
	private World storedWorld;

	@Inject(
		at = @At("HEAD"),
		method = "onEntityCollision(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;)V"
	)
	private void getOnEntityCollisionArgs(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
		this.storedWorld = world;
	}

	@ModifyArg(
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
		),
		method = "onEntityCollision(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;)V"
	)
	private Key<BooleanRule> modifyRavagerCropGameRule(Key<BooleanRule> originalRule) {
		if (this.storedWorld.getGameRules().getBoolean(originalRule)) {
			return DamageIncorporatedMod.CAN_RAVAGERS_BREAK_CROPS_RULE;
		}
		return originalRule;
	}
}
