package io.github.joaoh1.damageincorporated.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import io.github.joaoh1.damageincorporated.DamageIncorporatedMod;
import io.github.joaoh1.damageincorporated.utils.DamageIncorporatedUtils;

@Mixin(FireballEntity.class)
public class FireballEntityMixin extends AbstractFireballEntity {
	private FireballEntityMixin(EntityType<? extends FireballEntity> entityType, World world) {
		super(entityType, world);
	}

	@ModifyArgs(
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/World.createExplosion(Lnet/minecraft/entity/Entity;DDDFZLnet/minecraft/world/explosion/Explosion$DestructionType;)Lnet/minecraft/world/explosion/Explosion;"
		),
		method = "onCollision(Lnet/minecraft/util/hit/HitResult;)V"
	)
	private void modifyExplosion(Args args) {
		if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
			args.set(5, this.world.getGameRules().get(DamageIncorporatedMod.CAN_FIREBALLS_SPREAD_FIRE_RULE).get());
			args.set(6, DamageIncorporatedUtils.translateDestructionDrops(this.world.getGameRules().get(DamageIncorporatedMod.FIREBALL_DESTRUCTION_TYPE_RULE).get()));
		}
	}
}
