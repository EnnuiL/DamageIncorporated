package io.github.ennuil.damageincorporated;

import io.github.ennuil.damageincorporated.utils.DamageIncorporatedUtils.DestructionDrops;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.gamerule.v1.rule.EnumRule;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.BooleanRule;

public class DamageIncorporatedMod implements ModInitializer {
	public static CustomGameRuleCategory DAMAGE_INCORPORATED_CATEGORY = new CustomGameRuleCategory(
		new Identifier("damageincorporated", "gamerules"),
		new TranslatableText("damageincorporated.gamerule.category")
	);
	// The creeperDestructionType game rule
	public static GameRules.Key<EnumRule<DestructionDrops>> CREEPER_DESTRUCTION_TYPE_RULE;
	// The chargedCreeperDestructionType game rule
	public static GameRules.Key<EnumRule<DestructionDrops>> CHARGED_CREEPER_DESTRUCTION_TYPE_RULE;
	public static GameRules.Key<BooleanRule> CAN_ENDERMEN_PICK_BLOCKS_RULE;
	public static GameRules.Key<BooleanRule> CAN_ENDERMEN_PLACE_BLOCKS_RULE;
	public static GameRules.Key<BooleanRule> CAN_FIREBALLS_SPREAD_FIRE_RULE;
	public static GameRules.Key<EnumRule<DestructionDrops>> FIREBALL_DESTRUCTION_TYPE_RULE;
	public static GameRules.Key<BooleanRule> CAN_SILVERFISH_INFEST_BLOCKS_RULE;
	public static GameRules.Key<BooleanRule> PERMANENT_INFESTED_BLOCK_DAMAGE_RULE;
	public static GameRules.Key<BooleanRule> SNOW_GOLEM_TRAIL_RULE;
	public static GameRules.Key<BooleanRule> CAN_MOBS_PICK_UP_LOOT_RULE;
	public static GameRules.Key<EnumRule<DestructionDrops>> WITHER_SPAWN_DESTRUCTION_TYPE_RULE;
	public static GameRules.Key<BooleanRule> CAN_WITHER_BREAK_BLOCKS_RULE;
	public static GameRules.Key<EnumRule<DestructionDrops>> WITHER_SKULL_DESTRUCTION_TYPE_RULE;
	public static GameRules.Key<BooleanRule> CAN_WITHER_ROSE_BE_PLANTED_RULE;
	public static GameRules.Key<BooleanRule> CAN_EVOKERS_WOLOLO_RULE;
	public static GameRules.Key<BooleanRule> CAN_SHEEP_BREAK_GRASS_RULE;
	public static GameRules.Key<BooleanRule> CAN_SHEEP_TURN_GRASS_BLOCKS_INTO_DIRT_RULE;
	public static GameRules.Key<BooleanRule> CAN_RABBITS_EAT_CARROT_CROPS_RULE;
	public static GameRules.Key<BooleanRule> CAN_FOXES_EAT_IN_WORLD_BERRIES_RULE;
	public static GameRules.Key<BooleanRule> CAN_ZOMBIES_BREAK_TURTLE_EGGS;
	public static GameRules.Key<BooleanRule> CAN_DROWNEDS_BREAK_TURTLE_EGGS;
	public static GameRules.Key<BooleanRule> CAN_ZOMBIFIED_PIGLINS_BREAK_TURTLE_EGGS;
	public static GameRules.Key<BooleanRule> CAN_HUSKS_BREAK_TURTLE_EGGS;
	public static GameRules.Key<BooleanRule> CAN_ZOMBIE_VILLAGERS_BREAK_TURTLE_EGGS;
	public static GameRules.Key<BooleanRule> CAN_MOBS_ON_FIRE_DESTROY_POWDER_SNOW_BLOCKS;
	public static GameRules.Key<BooleanRule> CAN_RAVAGERS_BREAK_BLOCKS;
	public static enum FARMLAND_TRAMPLING_ENUM {
		ALL,
		PLAYER,
		OFF
	};
	public static GameRules.Key<EnumRule<FARMLAND_TRAMPLING_ENUM>> FARMLAND_TRAMPLING_RULE;

	@Override
	public void onInitialize() {
		CREEPER_DESTRUCTION_TYPE_RULE = GameRuleRegistry.register("creeperDestructionType", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createEnumRule(DestructionDrops.LOSSY));
		CHARGED_CREEPER_DESTRUCTION_TYPE_RULE = GameRuleRegistry.register("chargedCreeperDestructionType", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createEnumRule(DestructionDrops.LOSSY));
		CAN_ENDERMEN_PICK_BLOCKS_RULE = GameRuleRegistry.register("canEndermenPickBlocks", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_ENDERMEN_PLACE_BLOCKS_RULE = GameRuleRegistry.register("canEndermenPlaceBlocks", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_FIREBALLS_SPREAD_FIRE_RULE = GameRuleRegistry.register("canFireballsSpreadFire", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		FIREBALL_DESTRUCTION_TYPE_RULE = GameRuleRegistry.register("fireballDestructionType", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createEnumRule(DestructionDrops.LOSSY));
		FARMLAND_TRAMPLING_RULE = GameRuleRegistry.register("farmlandTrampling", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createEnumRule(FARMLAND_TRAMPLING_ENUM.ALL));
		CAN_SILVERFISH_INFEST_BLOCKS_RULE = GameRuleRegistry.register("canSilverfishInfestBlocks", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		PERMANENT_INFESTED_BLOCK_DAMAGE_RULE = GameRuleRegistry.register("permanentInfestedBlockDamage", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		SNOW_GOLEM_TRAIL_RULE = GameRuleRegistry.register("snowGolemTrail", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_MOBS_PICK_UP_LOOT_RULE = GameRuleRegistry.register("canMobsPickUpLoot", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		WITHER_SPAWN_DESTRUCTION_TYPE_RULE = GameRuleRegistry.register("witherSpawnDestructionType", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createEnumRule(DestructionDrops.LOSSY));
		CAN_WITHER_BREAK_BLOCKS_RULE = GameRuleRegistry.register("canWitherBreakBlocks", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		WITHER_SKULL_DESTRUCTION_TYPE_RULE = GameRuleRegistry.register("witherSkullDestructionType", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createEnumRule(DestructionDrops.LOSSY));
		CAN_WITHER_ROSE_BE_PLANTED_RULE = GameRuleRegistry.register("canWitherRoseBePlanted", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_EVOKERS_WOLOLO_RULE = GameRuleRegistry.register("canEvokersWololo", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_SHEEP_BREAK_GRASS_RULE = GameRuleRegistry.register("canSheepBreakGrass", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_SHEEP_TURN_GRASS_BLOCKS_INTO_DIRT_RULE = GameRuleRegistry.register("canSheepTurnGrassBlocksIntoDirt", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_RABBITS_EAT_CARROT_CROPS_RULE = GameRuleRegistry.register("canRabbitsEatCarrotCrops", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_FOXES_EAT_IN_WORLD_BERRIES_RULE = GameRuleRegistry.register("canFoxesEatInWorldBerries", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_ZOMBIES_BREAK_TURTLE_EGGS = GameRuleRegistry.register("canZombiesBreakTurtleEggs", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_DROWNEDS_BREAK_TURTLE_EGGS = GameRuleRegistry.register("canDrownedBreakTurtleEggs", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_ZOMBIFIED_PIGLINS_BREAK_TURTLE_EGGS = GameRuleRegistry.register("canZombifiedPiglinsBreakTurtleEggs", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_ZOMBIE_VILLAGERS_BREAK_TURTLE_EGGS = GameRuleRegistry.register("canZombieVillagersBreakTurtleEggs", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_HUSKS_BREAK_TURTLE_EGGS = GameRuleRegistry.register("canHusksBreakTurtleEggs", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_MOBS_ON_FIRE_DESTROY_POWDER_SNOW_BLOCKS = GameRuleRegistry.register("canMobsOnFireDestroyPowderSnowBlocks",DAMAGE_INCORPORATED_CATEGORY,GameRuleFactory.createBooleanRule(true));
		CAN_RAVAGERS_BREAK_BLOCKS = GameRuleRegistry.register("canRavagersBreakBlocks",DAMAGE_INCORPORATED_CATEGORY,GameRuleFactory.createBooleanRule(true));
	}
}
