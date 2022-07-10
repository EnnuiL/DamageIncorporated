package io.github.ennuil.damage_incorporated.mixin.explosions;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedEnums;
import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedGameRules;
import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedEnums.DamageIncDestructionType;

@Mixin(CreeperEntity.class)
public class CreeperEntityMixin extends HostileEntity {
	private CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	@Unique
	private DamageIncDestructionType di$storedCreeperGameRuleValue;

	@Unique
	private DamageIncDestructionType di$storedChargedCreeperGameRuleValue;

	@Inject(
		method = "explode()V",
		at = @At("HEAD")
	)
	private void getCreeperGameRuleValues(CallbackInfo ci) {
		this.di$storedCreeperGameRuleValue = this.world.getGameRules().get(DamageIncorporatedGameRules.CREEPER_DESTRUCTION_TYPE_RULE).get();
		this.di$storedChargedCreeperGameRuleValue = this.world.getGameRules().get(DamageIncorporatedGameRules.CHARGED_CREEPER_DESTRUCTION_TYPE_RULE).get();
	}

	@ModifyArgs(
		method = "explode()V",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/explosion/Explosion$DestructionType;)Lnet/minecraft/world/explosion/Explosion;"
		)
	)
	private void modifyCreeperExplosion(Args args) {
		if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
			if (!this.isOverlayConditionMet()) {
				if (this.di$storedCreeperGameRuleValue.equals(DamageIncDestructionType.NONE)) {
					args.set(4, 0.0F);
				}
				args.set(5, DamageIncorporatedEnums.translateDestructionType(this.di$storedCreeperGameRuleValue));
			} else {
				if (this.di$storedChargedCreeperGameRuleValue.equals(DamageIncDestructionType.NONE)) {
					args.set(4, 0.0F);
				}
				args.set(5, DamageIncorporatedEnums.translateDestructionType(this.di$storedChargedCreeperGameRuleValue));
			}
		}
	}


	@Shadow
	public boolean isOverlayConditionMet() { return false; }
}
