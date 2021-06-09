package io.github.ennuil.damageincorporated.utils;

import net.minecraft.world.explosion.Explosion.DestructionType;

public class DamageIncorporatedUtils {
    public static enum DestructionDrops {
		DISABLED,
		LOSSLESS,
		LOSSY
	};

	public static DestructionType translateDestructionDrops(DestructionDrops destructionDrops) {
		switch (destructionDrops) {
			case DISABLED:
				return DestructionType.NONE;
			case LOSSLESS:
				return DestructionType.BREAK;
			case LOSSY:
				return DestructionType.DESTROY;
			default:
				return DestructionType.NONE;
		}
	}
}
