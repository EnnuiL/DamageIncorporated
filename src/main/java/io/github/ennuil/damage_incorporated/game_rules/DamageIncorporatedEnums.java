package io.github.ennuil.damage_incorporated.game_rules;

import net.minecraft.world.explosion.Explosion.DestructionType;

public class DamageIncorporatedEnums {
	public static enum DamageIncDestructionType {
		NONE,
		DAMAGE_ONLY,
		LOSSLESS_EXPLOSION,
		LOSSY_EXPLOSION
	};

	public static enum AllowedEntities {
		ALL,
		PLAYER_ONLY,
		MOB_ONLY,
		OFF
	};

	public static DestructionType translateDestructionType(DamageIncDestructionType destructionType) {
		return switch (destructionType) {
			case NONE -> DestructionType.NONE;
			case DAMAGE_ONLY -> DestructionType.NONE;
			case LOSSLESS_EXPLOSION -> DestructionType.BREAK;
			case LOSSY_EXPLOSION -> DestructionType.DESTROY;
			default -> DestructionType.NONE;
		};
	}
}
