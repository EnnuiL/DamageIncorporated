package io.github.ennuil.damage_incorporated.game_rules;

import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedEnums.AllowedEntities;
import io.github.ennuil.damage_incorporated.game_rules.DamageIncorporatedEnums.DamageIncDestructionType;
import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.gamerule.v1.rule.EnumRule;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.BooleanRule;

public class DamageIncorporatedGameRules {
	// Categories
	public static CustomGameRuleCategory DAMAGE_INCORPORATED_EXPLOSIONS_CATEGORY = new CustomGameRuleCategory(
		new Identifier("damage_incorporated", "explosions_gamerules"),
		Text.translatable("damage_incorporated.gamerule.category.explosions").formatted(Formatting.BOLD, Formatting.DARK_AQUA)
	);

	public static CustomGameRuleCategory DAMAGE_INCORPORATED_GENERAL_CATEGORY = new CustomGameRuleCategory(
		new Identifier("damage_incorporated", "general_gamerules"),
		Text.translatable("damage_incorporated.gamerule.category.general").formatted(Formatting.BOLD, Formatting.DARK_AQUA)
	);

	public static CustomGameRuleCategory DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY = new CustomGameRuleCategory(
		new Identifier("damage_incorporated", "hostile_mobs_gamerules"),
		Text.translatable("damage_incorporated.gamerule.category.hostile_mobs").formatted(Formatting.BOLD, Formatting.DARK_AQUA)
	);

	public static CustomGameRuleCategory DAMAGE_INCORPORATED_UNDEAD_MOBS_CATEGORY = new CustomGameRuleCategory(
		new Identifier("damage_incorporated", "undead_mobs_gamerules"),
		Text.translatable("damage_incorporated.gamerule.category.undead_mobs").formatted(Formatting.BOLD, Formatting.DARK_AQUA)
	);

	public static CustomGameRuleCategory DAMAGE_INCORPORATED_PASSIVE_MOBS_CATEGORY = new CustomGameRuleCategory(
		new Identifier("damage_incorporated", "passive_gamerules"),
		Text.translatable("damage_incorporated.gamerule.category.passive_mobs").formatted(Formatting.BOLD, Formatting.DARK_AQUA)
	);

	// Explosions
	public static GameRules.Key<EnumRule<DamageIncDestructionType>> CREEPER_DESTRUCTION_TYPE_RULE = GameRuleRegistry.register("creeperDestructionType", DAMAGE_INCORPORATED_EXPLOSIONS_CATEGORY, GameRuleFactory.createEnumRule(DamageIncDestructionType.LOSSY_EXPLOSION));
	public static GameRules.Key<EnumRule<DamageIncDestructionType>> CHARGED_CREEPER_DESTRUCTION_TYPE_RULE = GameRuleRegistry.register("chargedCreeperDestructionType", DAMAGE_INCORPORATED_EXPLOSIONS_CATEGORY, GameRuleFactory.createEnumRule(DamageIncDestructionType.LOSSY_EXPLOSION));
	public static GameRules.Key<BooleanRule> CAN_GHAST_FIREBALLS_SPREAD_FIRE_RULE = GameRuleRegistry.register("canGhastFireballsSpreadFire", DAMAGE_INCORPORATED_EXPLOSIONS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<EnumRule<DamageIncDestructionType>> GHAST_FIREBALL_DESTRUCTION_TYPE_RULE = GameRuleRegistry.register("ghastFireballDestructionType", DAMAGE_INCORPORATED_EXPLOSIONS_CATEGORY, GameRuleFactory.createEnumRule(DamageIncDestructionType.LOSSY_EXPLOSION));
	public static GameRules.Key<EnumRule<DamageIncDestructionType>> WITHER_SPAWN_DESTRUCTION_TYPE_RULE = GameRuleRegistry.register("witherSpawnDestructionType", DAMAGE_INCORPORATED_EXPLOSIONS_CATEGORY, GameRuleFactory.createEnumRule(DamageIncDestructionType.LOSSY_EXPLOSION));
	public static GameRules.Key<EnumRule<DamageIncDestructionType>> WITHER_SKULL_DESTRUCTION_TYPE_RULE = GameRuleRegistry.register("witherSkullDestructionType", DAMAGE_INCORPORATED_EXPLOSIONS_CATEGORY, GameRuleFactory.createEnumRule(DamageIncDestructionType.LOSSY_EXPLOSION));

	// General
	public static GameRules.Key<EnumRule<AllowedEntities>> FARMLAND_TRAMPLING_RULE = GameRuleRegistry.register("farmlandTrampling", DAMAGE_INCORPORATED_GENERAL_CATEGORY, GameRuleFactory.createEnumRule(AllowedEntities.ALL));
	public static GameRules.Key<BooleanRule> CAN_WITHER_ROSE_BE_PLANTED_RULE = GameRuleRegistry.register("canWitherRoseBePlanted", DAMAGE_INCORPORATED_GENERAL_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<EnumRule<AllowedEntities>> TURTLE_EGG_TRAMPLING_RULE = GameRuleRegistry.register("turtleEggTrampling", DAMAGE_INCORPORATED_GENERAL_CATEGORY, GameRuleFactory.createEnumRule(AllowedEntities.ALL));
	public static GameRules.Key<BooleanRule> CAN_MOBS_PICK_UP_LOOT_RULE = GameRuleRegistry.register("canMobsPickUpLoot", DAMAGE_INCORPORATED_GENERAL_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<EnumRule<AllowedEntities>> CAN_BURNING_MOBS_BREAK_POWDER_SNOW_RULE = GameRuleRegistry.register("canBurningMobsBreakPowderSnow", DAMAGE_INCORPORATED_GENERAL_CATEGORY, GameRuleFactory.createEnumRule(AllowedEntities.ALL));
	public static GameRules.Key<EnumRule<AllowedEntities>> CAN_BURNING_PROJECTILES_MODIFY_BLOCKS_RULE = GameRuleRegistry.register("canBurningProjectilesModifyBlocks", DAMAGE_INCORPORATED_GENERAL_CATEGORY, GameRuleFactory.createEnumRule(AllowedEntities.ALL));

	// Hostile Mobs
	public static GameRules.Key<BooleanRule> CAN_ENDERMEN_PICK_BLOCKS_RULE = GameRuleRegistry.register("canEndermenPickBlocks", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_ENDERMEN_PLACE_BLOCKS_RULE = GameRuleRegistry.register("canEndermenPlaceBlocks", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_SILVERFISH_INFEST_BLOCKS_RULE = GameRuleRegistry.register("canSilverfishInfestBlocks", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> PERMANENT_INFESTED_BLOCK_DAMAGE_RULE = GameRuleRegistry.register("permanentInfestedBlockDamage", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_WITHER_BREAK_BLOCKS_RULE = GameRuleRegistry.register("canWitherBreakBlocks", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_RAVAGERS_BREAK_LEAVES_RULE = GameRuleRegistry.register("canRavagersBreakLeaves", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_RAVAGERS_BREAK_CROPS_RULE = GameRuleRegistry.register("canRavagersBreakCrops", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_ENDER_DRAGON_DESTROY_BLOCKS = GameRuleRegistry.register("canEnderDragonDestroyBlocks", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_BLAZE_FIREBALLS_SPREAD_FIRE_RULE = GameRuleRegistry.register("canBlazeFireballsSpreadFire", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_PIGLINS_GATHER_RULE = GameRuleRegistry.register("canPiglinsGather", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_EVOKERS_WOLOLO_RULE = GameRuleRegistry.register("canEvokersWololo", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));

	// Undead Mobs
	public static GameRules.Key<BooleanRule> CAN_MOBS_BREAK_DOORS_RULE = GameRuleRegistry.register("canMobsBreakDoors", DAMAGE_INCORPORATED_UNDEAD_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_TURTLE_EGGS_BE_STOMPED_BY_ZOMBIES_RULE = GameRuleRegistry.register("canTurtleEggsBeStompedByZombies", DAMAGE_INCORPORATED_UNDEAD_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_TURTLE_EGGS_BE_STOMPED_BY_ZOMBIFIED_PIGLINS_RULE = GameRuleRegistry.register("canTurtleEggsBeStompedByZombifiedPiglins", DAMAGE_INCORPORATED_UNDEAD_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_TURTLE_EGGS_BE_STOMPED_BY_ZOMBIE_VILLAGERS_RULE = GameRuleRegistry.register("canTurtleEggsBeStompedByZombieVillagers", DAMAGE_INCORPORATED_UNDEAD_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_TURTLE_EGGS_BE_STOMPED_BY_HUSKS_RULE = GameRuleRegistry.register("canTurtleEggsBeStompedByHusks", DAMAGE_INCORPORATED_UNDEAD_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_TURTLE_EGGS_BE_STOMPED_BY_DROWNEDS_RULE = GameRuleRegistry.register("canTurtleEggsBeStompedByDrowneds", DAMAGE_INCORPORATED_UNDEAD_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));

	// Animals & Utility
	public static GameRules.Key<BooleanRule> CAN_SHEEP_BREAK_GRASS_RULE = GameRuleRegistry.register("canSheepBreakGrass", DAMAGE_INCORPORATED_PASSIVE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_SHEEP_TURN_GRASS_BLOCKS_INTO_DIRT_RULE = GameRuleRegistry.register("canSheepTurnGrassBlocksIntoDirt", DAMAGE_INCORPORATED_PASSIVE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_RABBITS_EAT_CARROT_CROPS_RULE = GameRuleRegistry.register("canRabbitsEatCarrotCrops", DAMAGE_INCORPORATED_PASSIVE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_FOXES_PICK_BERRIES_RULE = GameRuleRegistry.register("canFoxesPickBerries", DAMAGE_INCORPORATED_PASSIVE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_FARMER_VILLAGERS_FARM_RULE = GameRuleRegistry.register("canFarmerVillagersFarm", DAMAGE_INCORPORATED_PASSIVE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> SNOW_GOLEM_TRAIL_RULE = GameRuleRegistry.register("snowGolemTrail", DAMAGE_INCORPORATED_PASSIVE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));

	// A little trick to initialize all those game rules
	public DamageIncorporatedGameRules() {}
}
