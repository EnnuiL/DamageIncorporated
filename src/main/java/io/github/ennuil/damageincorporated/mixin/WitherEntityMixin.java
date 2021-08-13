package io.github.ennuil.damageincorporated.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import io.github.ennuil.damageincorporated.utils.DamageIncorporatedUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;
import net.minecraft.world.explosion.Explosion.DestructionType;

@Mixin(WitherEntity.class)
public class WitherEntityMixin extends HostileEntity {
    private WitherEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyArg(
		at = @At(
            value = "INVOKE",
            target = "net/minecraft/world/World.createExplosion(Lnet/minecraft/entity/Entity;DDDFZLnet/minecraft/world/explosion/Explosion$DestructionType;)Lnet/minecraft/world/explosion/Explosion;",
            ordinal = 0
        ),
		method = "mobTick()V",
		index = 6
	)
	private DestructionType modifyDestructionType(DestructionType originalDestructionType) {
		if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
			return DamageIncorporatedUtils.translateDestructionDrops(this.world.getGameRules().get(DamageIncorporatedMod.WITHER_SPAWN_DESTRUCTION_TYPE_RULE).get());
		} else {
			return originalDestructionType;
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
	private Key<BooleanRule> modifyGameRule(Key<BooleanRule> originalRule) {
		if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
			return DamageIncorporatedMod.CAN_WITHER_BREAK_BLOCKS_RULE;
		}
		return originalRule;
	}
}
