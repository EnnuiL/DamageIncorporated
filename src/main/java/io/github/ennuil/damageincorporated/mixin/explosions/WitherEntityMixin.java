package io.github.ennuil.damageincorporated.mixin.explosions;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import io.github.ennuil.damageincorporated.utils.DamageIncorporatedUtils;
import io.github.ennuil.damageincorporated.utils.DamageIncorporatedUtils.DamageIncDestructionType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;

@Mixin(WitherEntity.class)
public class WitherEntityMixin extends HostileEntity {
	private WitherEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	@ModifyArgs(
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/World.createExplosion(Lnet/minecraft/entity/Entity;DDDFZLnet/minecraft/world/explosion/Explosion$DestructionType;)Lnet/minecraft/world/explosion/Explosion;"
		),
		method = "mobTick()V"
	)
	private void modifyWitherSpawnExplosion(Args args) {
		if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
			DamageIncDestructionType destructionType = this.world.getGameRules().get(DamageIncorporatedMod.WITHER_SPAWN_DESTRUCTION_TYPE_RULE).get();
			if (destructionType.equals(DamageIncDestructionType.NONE)) {
				args.set(4, 0.0F);
			}
			args.set(6, DamageIncorporatedUtils.translateDestructionDrops(destructionType));
		}
	}

	@ModifyArg(
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z",
			ordinal = 1
		),
		method = "mobTick()V"
	)
	private Key<BooleanRule> modifyWitherGameRuleArg(Key<BooleanRule> originalRule) {
		if (this.world.getGameRules().getBoolean(originalRule)) {
			return DamageIncorporatedMod.CAN_WITHER_BREAK_BLOCKS_RULE;
		}
		return originalRule;
	}
}
