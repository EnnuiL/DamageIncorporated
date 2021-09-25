package io.github.ennuil.damageincorporated.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import net.minecraft.entity.ai.brain.task.FarmerVillagerTask;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;

@Mixin(FarmerVillagerTask.class)
public class FarmerVillagerTaskMixin {
	@Unique
	private ServerWorld storedServerWorld;

	@Inject(
		at = @At("HEAD"),
		method = "shouldRun(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/VillagerEntity;)Z"
	)
	private void getOnLandedUponArgs(ServerWorld serverWorld, VillagerEntity villagerEntity, CallbackInfo info) {
		this.storedServerWorld = serverWorld;
	}

	@ModifyArg(
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
		),
		method = "shouldRun(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/VillagerEntity;)Z"
	)
	private Key<BooleanRule> modifyRavagerCropGameRule(Key<BooleanRule> originalRule) {
		if (this.storedServerWorld.getGameRules().getBoolean(originalRule)) {
			return DamageIncorporatedMod.CAN_FARMER_VILLAGERS_FARM_RULE;
		}
		return originalRule;
	}
}
