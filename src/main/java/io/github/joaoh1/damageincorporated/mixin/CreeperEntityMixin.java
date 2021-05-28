package io.github.joaoh1.damageincorporated.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion.DestructionType;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import io.github.joaoh1.damageincorporated.DamageIncorporatedMod;
import io.github.joaoh1.damageincorporated.utils.DamageIncorporatedUtils;

@Mixin(CreeperEntity.class)
public class CreeperEntityMixin extends HostileEntity {
	private CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	@Shadow
	public boolean shouldRenderOverlay() { return false; }

	@ModifyArg(
		at = @At(value = "INVOKE", target = "net/minecraft/world/World.createExplosion(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/explosion/Explosion$DestructionType;)Lnet/minecraft/world/explosion/Explosion;"),
		method = "explode()V",
		index = 5
	)
	private DestructionType modifyDestructionType(DestructionType originalDestructionType) {
		if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
			if (!this.shouldRenderOverlay()) {
				//Handle the creeperDestructionType game rule
				return DamageIncorporatedUtils.translateDestructionDrops(this.world.getGameRules().get(DamageIncorporatedMod.CREEPER_DESTRUCTION_TYPE_RULE).get());
			} else {
				//Handle the chargedCreeperDestructionType game rule
				return DamageIncorporatedUtils.translateDestructionDrops(this.world.getGameRules().get(DamageIncorporatedMod.CHARGED_CREEPER_DESTRUCTION_TYPE_RULE).get());
			}
		} else {
			return originalDestructionType;
		}
	}
}
