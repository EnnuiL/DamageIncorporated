package io.github.ennuil.damage_incorporated.mixin.explosions;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedEnums;
import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedGameRules;
import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedEnums.DamageIncDestructionType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

@Mixin(WitherSkullEntity.class)
public class WitherSkullEntityMixin extends ExplosiveProjectileEntity {
	private WitherSkullEntityMixin(EntityType<? extends ExplosiveProjectileEntity> entityType, World world) {
		super(entityType, world);
	}

	@ModifyArgs(
		method = "onCollision(Lnet/minecraft/util/hit/HitResult;)V",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFZLnet/minecraft/world/explosion/Explosion$DestructionType;)Lnet/minecraft/world/explosion/Explosion;"
		)
	)
	private void modifyWitherSkullExplosion(Args args) {
		if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
			DamageIncDestructionType destructionType = this.world.getGameRules().get(DamageIncorporatedGameRules.WITHER_SKULL_DESTRUCTION_TYPE_RULE).get();
			if (destructionType.equals(DamageIncDestructionType.NONE)) {
				args.set(4, 0.0F);
			}
			args.set(6, DamageIncorporatedEnums.translateDestructionType(destructionType));
		}
	}
}
