package io.github.ennuil.damageincorporated.mixin.general;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.ennuil.damageincorporated.game_rules.DamageIncorporatedGameRules;
import io.github.ennuil.damageincorporated.game_rules.DamageIncorporatedEnums.AllowedEntities;
import net.minecraft.block.TurtleEggBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

@Mixin(TurtleEggBlock.class)
public class TurtleEggBlockMixin {
	@Unique
	private AllowedEntities storedGameRuleValue;

	@Inject(
		at = @At("HEAD"),
		method = "breaksEgg(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;)Z"
	)
	private void getBreaksEggArgs(World world, Entity entity, CallbackInfoReturnable<Boolean> cir) {
		this.storedGameRuleValue = world.getGameRules().get(DamageIncorporatedGameRules.TURTLE_EGG_TRAMPLING_RULE).get();
	}

	@ModifyConstant(
		constant = @Constant(classValue = LivingEntity.class),
		method = "breaksEgg(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;)Z"
	)
	private Class<?> modifyTurtleEggEntityConstant(Object object, Class<?> originalClass) {
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
		method = "breaksEgg(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;)Z"
	)
	private Class<?> modifyTurtleEggPlayerConstant(Object object, Class<?> originalClass) {
		return switch (this.storedGameRuleValue) {
			case MOB_ONLY -> Integer.class;
			default -> originalClass;
		};
	}
}
