package io.github.ennuil.damageincorporated.mixin;

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

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import io.github.ennuil.damageincorporated.utils.DamageIncorporatedUtils;
import io.github.ennuil.damageincorporated.utils.DamageIncorporatedUtils.DamageIncDestructionType;

@Mixin(CreeperEntity.class)
public class CreeperEntityMixin extends HostileEntity {
	private CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	@Unique
	private DamageIncDestructionType storedCreeperGameRuleValue;

	@Unique
	private DamageIncDestructionType storedChargedCreeperGameRuleValue;

	@Inject(
		at = @At("HEAD"),
		method = "explode()V"
	)
	private void getCreeperGameRuleValues(CallbackInfo ci) {
		this.storedCreeperGameRuleValue = this.world.getGameRules().get(DamageIncorporatedMod.CREEPER_DESTRUCTION_TYPE_RULE).get();
		this.storedChargedCreeperGameRuleValue = this.world.getGameRules().get(DamageIncorporatedMod.CHARGED_CREEPER_DESTRUCTION_TYPE_RULE).get();
	}

	@ModifyArgs(
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/World.createExplosion(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/explosion/Explosion$DestructionType;)Lnet/minecraft/world/explosion/Explosion;"
		),
		method = "explode()V"
	)
	private void modifyCreeperExplosion(Args args) {
		if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
			if (!this.isOverlayConditionMet()) {
				if (this.storedCreeperGameRuleValue.equals(DamageIncDestructionType.NONE)) {
					args.set(4, 0.0F);
				}
				args.set(5, DamageIncorporatedUtils.translateDestructionDrops(this.storedCreeperGameRuleValue));
			} else {
				if (this.storedChargedCreeperGameRuleValue.equals(DamageIncDestructionType.NONE)) {
					args.set(4, 0.0F);
				}
				args.set(5, DamageIncorporatedUtils.translateDestructionDrops(this.storedChargedCreeperGameRuleValue));
			}
		}
	}

	@Shadow
	public boolean isOverlayConditionMet() { return false; }
}
