package io.github.ennuil.damageincorporated.utils;

import net.minecraft.world.explosion.Explosion.DestructionType;

public class DamageIncorporatedUtils {
	public static enum DamageIncDestructionType {
		NONE,
		DAMAGE_ONLY,
		LOSSLESS_EXPLOSION,
		LOSSY_EXPLOSION
	};

	public static enum FARMLAND_TRAMPLING_ENUM {
		ALL,
		PLAYER,
		ENTITY,
		OFF
	};

	public static DestructionType translateDestructionDrops(DamageIncDestructionType destructionDrops) {
		return switch (destructionDrops) {
			case NONE -> DestructionType.NONE;
			case DAMAGE_ONLY -> DestructionType.NONE;
			case LOSSLESS_EXPLOSION -> DestructionType.BREAK;
			case LOSSY_EXPLOSION -> DestructionType.DESTROY;
			default -> DestructionType.NONE;
		};
	}
}
