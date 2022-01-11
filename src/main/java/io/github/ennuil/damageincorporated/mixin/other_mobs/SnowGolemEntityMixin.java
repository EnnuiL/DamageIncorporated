package io.github.ennuil.damageincorporated.mixin.other_mobs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.ennuil.damageincorporated.game_rules.DamageIncorporatedGameRules;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.world.World;

@Mixin(SnowGolemEntity.class)
public class SnowGolemEntityMixin extends GolemEntity {
	private SnowGolemEntityMixin(EntityType<? extends GolemEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
		),
		method = "tickMovement()V",
		cancellable = true
	)
	private void disableSnowGolemSnowPath(CallbackInfo ci) {
		if (!this.world.getGameRules().getBoolean(DamageIncorporatedGameRules.SNOW_GOLEM_TRAIL_RULE)) {
			ci.cancel();
		}
	}
}
