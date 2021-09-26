package io.github.ennuil.damageincorporated.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import io.github.ennuil.damageincorporated.utils.DamageIncorporatedUtils.AllowedEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(FarmlandBlock.class)
public class FarmlandBlockMixin {
	@Unique
	private AllowedEntities storedGameRuleValue;

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
	private Class<?> modifyFarmlandEntityConstant(Object object, Class<?> originalClass) {
		return switch (this.storedGameRuleValue) {
			case PLAYER_ONLY -> PlayerEntity.class;
			case MOB_ONLY -> {
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
	private Class<?> modifyFarmlandPlayerConstant(Object object, Class<?> originalClass) {
		return switch (this.storedGameRuleValue) {
			case MOB_ONLY -> Integer.class;
			default -> originalClass;
		};
	}
}