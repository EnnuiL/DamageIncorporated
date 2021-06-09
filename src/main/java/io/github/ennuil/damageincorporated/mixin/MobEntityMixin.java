package io.github.ennuil.damageincorporated.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {
    private MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
        at = @At(
			value = "INVOKE_ASSIGN",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
		),
        method = "tickMovement()V",
        cancellable = true
    )
    private void tickMovementMixin(CallbackInfo ci) {
        if (!this.world.getGameRules().getBoolean(DamageIncorporatedMod.CAN_MOBS_PICK_UP_LOOT_RULE)) {
			ci.cancel();
		}
    }
}
