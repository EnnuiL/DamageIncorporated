package io.github.joaoh1.damageincorporated.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.joaoh1.damageincorporated.DamageIncorporatedMod;
import io.github.joaoh1.damageincorporated.DamageIncorporatedMod.FARMLAND_TRAMPLING_ENUM;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(FarmlandBlock.class)
public class FarmlandBlockMixin extends Block {
	private FarmlandBlockMixin(Settings settings) {
		super(settings);
	}

	@Inject(
		at = @At("HEAD"),
		method = "onLandedUpon(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;F)V",
		cancellable = true
	)
	private void onLandedUponMixin(World world, BlockPos pos, Entity entity, float distance, CallbackInfo info) {
		FARMLAND_TRAMPLING_ENUM gameRuleValue = world.getGameRules().get(DamageIncorporatedMod.FARMLAND_TRAMPLING_RULE).get();
		if (gameRuleValue.equals(FARMLAND_TRAMPLING_ENUM.OFF)) {
			super.onLandedUpon(world, pos, entity, distance);
			info.cancel();
		}

		if (gameRuleValue.equals(FARMLAND_TRAMPLING_ENUM.PLAYER)) {
			if (!world.isClient && world.random.nextFloat() < distance - 0.5F && entity instanceof PlayerEntity && entity.getWidth() * entity.getWidth() * entity.getHeight() > 0.512F) {
				setToDirt(world.getBlockState(pos), world, pos);
			}
			super.onLandedUpon(world, pos, entity, distance);
			info.cancel();
		}
	}

	@Shadow
	public static void setToDirt(BlockState state, World world, BlockPos pos) {}
}