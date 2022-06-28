package io.github.ennuil.damage_incorporated;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedGameRules;

public class DamageIncorporatedMod implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        // Initialize the game rules
        new DamageIncorporatedGameRules();
    }
}
