package io.github.ennuil.damageincorporated.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import io.github.ennuil.damageincorporated.utils.DamageIncorporatedUtils.FARMLAND_TRAMPLING_ENUM;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(FarmlandBlock.class)
@SuppressWarnings("rawtypes")
public class FarmlandBlockMixin {
	@Unique
	private FARMLAND_TRAMPLING_ENUM storedGameRuleValue;

	@Inject(
		at = @At("HEAD"),
		method = "onLandedUpon(Lnet/minecraft/world/World;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;F)V"
	)
	private void getOnLandedUponArgs(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance, CallbackInfo ci) {
		this.storedGameRuleValue = world.getGameRules().get(DamageIncorporatedMod.FARMLAND_TRAMPLING_RULE).get();
	}

	@ModifyConstant(
		constant = @Constant(classValue = LivingEntity.class),
		method = "onLandedUpon(Lnet/minecraft/world/World;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;F)V"
	)
	private Class modifyEntityConstant(Object object, Class originalClass) {
		return switch (this.storedGameRuleValue) {
			case PLAYER -> PlayerEntity.class;
			case ENTITY -> {
				if (object instanceof PlayerEntity) {
					yield Integer.class;
				} else {
					yield originalClass;
				}
			}
			case OFF -> Integer.class;
			default -> originalClass;
		};
	}

	@ModifyConstant(
		constant = @Constant(classValue = PlayerEntity.class),
		method = "onLandedUpon(Lnet/minecraft/world/World;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;F)V"
	)
	private Class modifyPlayerConstant(Object object, Class originalClass) {
		return switch (this.storedGameRuleValue) {
			case ENTITY -> Integer.class;
			default -> originalClass;
		};
	}
}