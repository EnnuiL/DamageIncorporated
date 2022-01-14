package io.github.ennuil.damageincorporated;

import io.github.ennuil.damageincorporated.game_rules.DamageIncorporatedGameRules;
import net.fabricmc.api.ModInitializer;

public class DamageIncorporatedMod implements ModInitializer {
    @Override
    public void onInitialize() {
        // Initialize the game rules
        new DamageIncorporatedGameRules();
    }
}
