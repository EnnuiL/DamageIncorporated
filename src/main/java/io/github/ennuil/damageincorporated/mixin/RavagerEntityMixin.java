package io.github.ennuil.damageincorporated.mixin;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RavagerEntity.class)
public class RavagerEntityMixin extends RaiderEntity {
    protected RavagerEntityMixin(EntityType<? extends RaiderEntity> entityType, World world) {
        super(entityType, world);
    }

    @Redirect(
            method = "tickMovement",
            at = @At(value = "INVOKE",target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z", ordinal = 0)
    )
    private boolean redirectMobGriefing(GameRules gameRules, GameRules.Key<GameRules.BooleanRule> rule){
        if(!gameRules.getBoolean(DamageIncorporatedMod.CAN_RAVAGERS_BREAK_BLOCKS))
            return false;
        return gameRules.getBoolean(GameRules.DO_MOB_GRIEFING);
    }

    @Shadow
    public void addBonusForWave(int wave, boolean unused) {

    }

    @Shadow
    public SoundEvent getCelebratingSound() {
        return null;
    }
}
