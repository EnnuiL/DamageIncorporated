package io.github.ennuil.damageincorporated.mixin.explosions;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import io.github.ennuil.damageincorporated.utils.DamageIncorporatedUtils;
import io.github.ennuil.damageincorporated.utils.DamageIncorporatedUtils.DamageIncDestructionType;

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
	private void modifyGhastFireballExplosion(Args args) {
		if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
			DamageIncDestructionType destructionType = this.world.getGameRules().get(DamageIncorporatedMod.GHAST_FIREBALL_DESTRUCTION_TYPE_RULE).get();
			if (destructionType.equals(DamageIncDestructionType.NONE)) {
				args.set(4, 0.0F);
			}
			args.set(5, this.world.getGameRules().getBoolean(DamageIncorporatedMod.CAN_GHAST_FIREBALLS_SPREAD_FIRE_RULE));
			args.set(6, DamageIncorporatedUtils.translateDestructionDrops(destructionType));
		}
	}
}
