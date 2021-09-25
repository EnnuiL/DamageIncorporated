package io.github.ennuil.damageincorporated.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import net.minecraft.block.TurtleEggBlock;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;

@Mixin(TurtleEggBlock.class)
public class TurtleEggBlockMixin {
    @Unique
	private World storedWorld;

	@Inject(
		at = @At("HEAD"),
		method = "breaksEgg(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;)Z"
	)
	private void getOnLandedUponArgs(World world, Entity entity, CallbackInfo info) {
		this.storedWorld = world;
	}

	@ModifyArg(
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
		),
		method = "breaksEgg(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;)Z"
	)
	private Key<BooleanRule> modifyGameRule(Key<BooleanRule> originalRule) {
		if (this.storedWorld.getGameRules().getBoolean(originalRule)) {
			return DamageIncorporatedMod.CAN_MOBS_BREAK_TURTLE_EGGS_RULE;
		}
		return originalRule;
	}
}
