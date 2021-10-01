package io.github.ennuil.damageincorporated;

import io.github.ennuil.damageincorporated.utils.DamageIncorporatedUtils.DamageIncDestructionType;
import io.github.ennuil.damageincorporated.utils.DamageIncorporatedUtils.AllowedEntities;
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
	public static GameRules.Key<EnumRule<DamageIncDestructionType>> CREEPER_DESTRUCTION_TYPE_RULE;
	public static GameRules.Key<EnumRule<DamageIncDestructionType>> CHARGED_CREEPER_DESTRUCTION_TYPE_RULE;
	public static GameRules.Key<BooleanRule> CAN_ENDERMEN_PICK_BLOCKS_RULE;
	public static GameRules.Key<BooleanRule> CAN_ENDERMEN_PLACE_BLOCKS_RULE;
	public static GameRules.Key<BooleanRule> CAN_GHAST_FIREBALLS_SPREAD_FIRE_RULE;
	public static GameRules.Key<EnumRule<DamageIncDestructionType>> GHAST_FIREBALL_DESTRUCTION_TYPE_RULE;
	public static GameRules.Key<EnumRule<AllowedEntities>> FARMLAND_TRAMPLING_RULE;
	public static GameRules.Key<BooleanRule> CAN_SILVERFISH_INFEST_BLOCKS_RULE;
	public static GameRules.Key<BooleanRule> PERMANENT_INFESTED_BLOCK_DAMAGE_RULE;
	public static GameRules.Key<BooleanRule> SNOW_GOLEM_TRAIL_RULE;
	public static GameRules.Key<BooleanRule> CAN_MOBS_PICK_UP_LOOT_RULE;
	public static GameRules.Key<EnumRule<DamageIncDestructionType>> WITHER_SPAWN_DESTRUCTION_TYPE_RULE;
	public static GameRules.Key<BooleanRule> CAN_WITHER_BREAK_BLOCKS_RULE;
	public static GameRules.Key<EnumRule<DamageIncDestructionType>> WITHER_SKULL_DESTRUCTION_TYPE_RULE;
	public static GameRules.Key<BooleanRule> CAN_WITHER_ROSE_BE_PLANTED_RULE;
	public static GameRules.Key<BooleanRule> CAN_EVOKERS_WOLOLO_RULE;
	public static GameRules.Key<BooleanRule> CAN_SHEEP_BREAK_GRASS_RULE;
	public static GameRules.Key<BooleanRule> CAN_SHEEP_TURN_GRASS_BLOCKS_INTO_DIRT_RULE;
	public static GameRules.Key<BooleanRule> CAN_RABBITS_EAT_CARROT_CROPS_RULE;
	public static GameRules.Key<BooleanRule> CAN_FOXES_PICK_BERRIES_RULE;
	public static GameRules.Key<BooleanRule> CAN_TURTLE_EGGS_BE_STOMPED_BY_ZOMBIES_RULE;
	public static GameRules.Key<BooleanRule> CAN_TURTLE_EGGS_BE_STOMPED_BY_ZOMBIFIED_PIGLINS_RULE;
	public static GameRules.Key<BooleanRule> CAN_TURTLE_EGGS_BE_STOMPED_BY_ZOMBIE_VILLAGERS_RULE;
	public static GameRules.Key<BooleanRule> CAN_TURTLE_EGGS_BE_STOMPED_BY_HUSKS_RULE;
	public static GameRules.Key<BooleanRule> CAN_TURTLE_EGGS_BE_STOMPED_BY_DROWNEDS_RULE;
	public static GameRules.Key<EnumRule<AllowedEntities>> CAN_BURNING_MOBS_BREAK_POWDER_SNOW_RULE;
	public static GameRules.Key<EnumRule<AllowedEntities>> TURTLE_EGG_TRAMPLING_RULE;
	public static GameRules.Key<BooleanRule> CAN_RAVAGERS_BREAK_LEAVES_RULE;
	public static GameRules.Key<BooleanRule> CAN_RAVAGERS_BREAK_CROPS_RULE;
	public static GameRules.Key<BooleanRule> CAN_MOBS_BREAK_DOORS_RULE;
	public static GameRules.Key<BooleanRule> CAN_ENDER_DRAGON_DESTROY_BLOCKS;
	public static GameRules.Key<BooleanRule> CAN_BLAZE_FIREBALLS_SPREAD_FIRE_RULE;
	public static GameRules.Key<BooleanRule> CAN_PIGLINS_GATHER_RULE;
	public static GameRules.Key<BooleanRule> CAN_FARMER_VILLAGERS_FARM_RULE;
	public static GameRules.Key<EnumRule<AllowedEntities>> CAN_BURNING_PROJECTILES_MODIFY_BLOCKS_RULE;
	
	@Override
	public void onInitialize() {
		// Explosion-related rules
		CREEPER_DESTRUCTION_TYPE_RULE = GameRuleRegistry.register("creeperDestructionType", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createEnumRule(DamageIncDestructionType.LOSSY_EXPLOSION));
		CHARGED_CREEPER_DESTRUCTION_TYPE_RULE = GameRuleRegistry.register("chargedCreeperDestructionType", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createEnumRule(DamageIncDestructionType.LOSSY_EXPLOSION));
		CAN_GHAST_FIREBALLS_SPREAD_FIRE_RULE = GameRuleRegistry.register("canGhastFireballsSpreadFire", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		GHAST_FIREBALL_DESTRUCTION_TYPE_RULE = GameRuleRegistry.register("ghastFireballDestructionType", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createEnumRule(DamageIncDestructionType.LOSSY_EXPLOSION));
		WITHER_SPAWN_DESTRUCTION_TYPE_RULE = GameRuleRegistry.register("witherSpawnDestructionType", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createEnumRule(DamageIncDestructionType.LOSSY_EXPLOSION));
		WITHER_SKULL_DESTRUCTION_TYPE_RULE = GameRuleRegistry.register("witherSkullDestructionType", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createEnumRule(DamageIncDestructionType.LOSSY_EXPLOSION));
		
		// Block-changing-related rules (General)
		FARMLAND_TRAMPLING_RULE = GameRuleRegistry.register("farmlandTrampling", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createEnumRule(AllowedEntities.ALL));
		CAN_WITHER_ROSE_BE_PLANTED_RULE = GameRuleRegistry.register("canWitherRoseBePlanted", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		TURTLE_EGG_TRAMPLING_RULE = GameRuleRegistry.register("turtleEggTrampling", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createEnumRule(AllowedEntities.ALL));
		
		// Block-changing-related rules (Animals & Utility)
		SNOW_GOLEM_TRAIL_RULE = GameRuleRegistry.register("snowGolemTrail", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_SHEEP_BREAK_GRASS_RULE = GameRuleRegistry.register("canSheepBreakGrass", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_SHEEP_TURN_GRASS_BLOCKS_INTO_DIRT_RULE = GameRuleRegistry.register("canSheepTurnGrassBlocksIntoDirt", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_RABBITS_EAT_CARROT_CROPS_RULE = GameRuleRegistry.register("canRabbitsEatCarrotCrops", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_FOXES_PICK_BERRIES_RULE = GameRuleRegistry.register("canFoxesPickBerries", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_FARMER_VILLAGERS_FARM_RULE = GameRuleRegistry.register("canFarmerVillagersFarm", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));

		// Block-changing-related rules (Hostile Mobs)
		CAN_ENDERMEN_PICK_BLOCKS_RULE = GameRuleRegistry.register("canEndermenPickBlocks", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_ENDERMEN_PLACE_BLOCKS_RULE = GameRuleRegistry.register("canEndermenPlaceBlocks", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_SILVERFISH_INFEST_BLOCKS_RULE = GameRuleRegistry.register("canSilverfishInfestBlocks", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		PERMANENT_INFESTED_BLOCK_DAMAGE_RULE = GameRuleRegistry.register("permanentInfestedBlockDamage", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_WITHER_BREAK_BLOCKS_RULE = GameRuleRegistry.register("canWitherBreakBlocks", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_TURTLE_EGGS_BE_STOMPED_BY_ZOMBIES_RULE = GameRuleRegistry.register("canTurtleEggsBeStompedByZombies", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_TURTLE_EGGS_BE_STOMPED_BY_ZOMBIFIED_PIGLINS_RULE = GameRuleRegistry.register("canTurtleEggsBeStompedByZombifiedPiglins", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_TURTLE_EGGS_BE_STOMPED_BY_ZOMBIE_VILLAGERS_RULE = GameRuleRegistry.register("canTurtleEggsBeStompedByZombieVillagers", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_TURTLE_EGGS_BE_STOMPED_BY_HUSKS_RULE = GameRuleRegistry.register("canTurtleEggsBeStompedByHusks", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_TURTLE_EGGS_BE_STOMPED_BY_DROWNEDS_RULE = GameRuleRegistry.register("canTurtleEggsBeStompedByDrowneds", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_BURNING_MOBS_BREAK_POWDER_SNOW_RULE = GameRuleRegistry.register("canBurningMobsBreakPowderSnow", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createEnumRule(AllowedEntities.ALL));
		CAN_RAVAGERS_BREAK_LEAVES_RULE = GameRuleRegistry.register("canRavagersBreakLeaves", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_RAVAGERS_BREAK_CROPS_RULE = GameRuleRegistry.register("canRavagersBreakCrops", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_MOBS_BREAK_DOORS_RULE = GameRuleRegistry.register("canMobsBreakDoors", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_ENDER_DRAGON_DESTROY_BLOCKS = GameRuleRegistry.register("canEnderDragonDestroyBlocks", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_BLAZE_FIREBALLS_SPREAD_FIRE_RULE = GameRuleRegistry.register("canBlazeFireballsSpreadFire", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_BURNING_PROJECTILES_MODIFY_BLOCKS_RULE = GameRuleRegistry.register("canBurningProjectilesModifyBlocks", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createEnumRule(AllowedEntities.ALL));
		
		// Item-consuming rules
		CAN_MOBS_PICK_UP_LOOT_RULE = GameRuleRegistry.register("canMobsPickUpLoot", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		CAN_PIGLINS_GATHER_RULE = GameRuleRegistry.register("canPiglinsGather", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
		
		// Entity-changing rules
		CAN_EVOKERS_WOLOLO_RULE = GameRuleRegistry.register("canEvokersWololo", DAMAGE_INCORPORATED_CATEGORY, GameRuleFactory.createBooleanRule(true));
	}
}
