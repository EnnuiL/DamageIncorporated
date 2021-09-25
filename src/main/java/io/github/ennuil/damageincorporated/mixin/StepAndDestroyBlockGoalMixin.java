package io.github.ennuil.damageincorporated.mixin;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.ai.goal.StepAndDestroyBlockGoal;
import net.minecraft.entity.mob.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(StepAndDestroyBlockGoal.class)
public class StepAndDestroyBlockGoalMixin extends MoveToTargetPosGoal {
    public StepAndDestroyBlockGoalMixin(PathAwareEntity mob, double speed, int range, MobEntity stepAndDestroyMob, Block targetBlock) {
        super(mob, speed, range);
        this.stepAndDestroyMob = stepAndDestroyMob;
    }

    @Shadow @Final
    @Mutable
    private final MobEntity stepAndDestroyMob;

    @Redirect(
            method = "canStart",
            at = @At(value = "INVOKE",target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z", ordinal = 0)
    )
    private boolean redirectMobGriefing(GameRules gameRules, GameRules.Key<GameRules.BooleanRule> rule){
        if(this.stepAndDestroyMob.getType() == EntityType.ZOMBIE
                && !gameRules.getBoolean(DamageIncorporatedMod.CAN_ZOMBIES_BREAK_TURTLE_EGGS)){
            return false;
        }else if(this.stepAndDestroyMob.getType() == EntityType.ZOMBIFIED_PIGLIN
                && !gameRules.getBoolean(DamageIncorporatedMod.CAN_ZOMBIFIED_PIGLINS_BREAK_TURTLE_EGGS)){
            return false;
        }else if(this.stepAndDestroyMob.getType() == EntityType.ZOMBIE_VILLAGER
                && !gameRules.getBoolean(DamageIncorporatedMod.CAN_ZOMBIE_VILLAGERS_BREAK_TURTLE_EGGS)){
            return false;
        }else if(this.stepAndDestroyMob.getType() == EntityType.DROWNED
                && !gameRules.getBoolean(DamageIncorporatedMod.CAN_DROWNEDS_BREAK_TURTLE_EGGS)){
            return false;
        }else if(this.stepAndDestroyMob.getType() == EntityType.HUSK
                && !gameRules.getBoolean(DamageIncorporatedMod.CAN_HUSKS_BREAK_TURTLE_EGGS)){
            return false;
        }else {
            return gameRules.getBoolean(GameRules.DO_MOB_GRIEFING);
        }
    }

    @Shadow
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        return false;
    }
}
