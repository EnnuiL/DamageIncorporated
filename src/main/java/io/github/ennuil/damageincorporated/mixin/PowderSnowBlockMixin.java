package io.github.ennuil.damageincorporated.mixin;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import net.minecraft.block.Block;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin extends Block {
    public PowderSnowBlockMixin(Settings settings) {
        super(settings);
    }

    @Redirect(
            method = "onEntityCollision",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z", ordinal = 0)
    )
    public boolean redirectMobGriefing(GameRules gameRules, GameRules.Key<GameRules.BooleanRule> rule){
        if(!gameRules.getBoolean(DamageIncorporatedMod.CAN_MOBS_ON_FIRE_DESTROY_POWDER_SNOW_BLOCKS))
            return false;
        return gameRules.getBoolean(GameRules.DO_MOB_GRIEFING);
    }
}
