package com.github.x3r.fantasy_trees;

import net.minecraftforge.common.ForgeConfigSpec;

public class FantasyTreesConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue can_grow_fantasy_sapling;
    public static final ForgeConfigSpec.IntValue growth_delay;
    public static final ForgeConfigSpec.BooleanValue override_blocks;
    public static final ForgeConfigSpec.IntValue oak_region_weight;
    public static final ForgeConfigSpec.IntValue spruce_region_weight;
    public static final ForgeConfigSpec.IntValue birch_region_weight;
    public static final ForgeConfigSpec.IntValue dark_oak_region_weight;
    public static final ForgeConfigSpec.IntValue jungle_region_weight;
    public static final ForgeConfigSpec.IntValue savanna_region_weight;
    public static final ForgeConfigSpec.BooleanValue glowing_trees;

    static {
        BUILDER.push("Fantasy Trees Config");
        can_grow_fantasy_sapling = BUILDER.comment("Can grow fantasy trees").define("can_grow_fantasy_sapling", true);
        growth_delay = BUILDER.comment("Tree growth delay").defineInRange("growth_delay", 7, 0, 100);
        override_blocks = BUILDER.comment("Trees growing from saplings replace blocks").define("override_blocks", true);
        oak_region_weight = BUILDER.comment("Fantasy forest biome weight").defineInRange("oak_region_weight", 10, 0, 100);
        spruce_region_weight = BUILDER.comment("Fantasy taiga biome weight").defineInRange("spruce_region_weight", 10, 0, 100);
        birch_region_weight = BUILDER.comment("Fantasy birch forest biome weight").defineInRange("birch_region_weight", 10, 0, 100);
        dark_oak_region_weight = BUILDER.comment("Fantasy dark forest biome weight").defineInRange("dark_oak_region_weight", 10, 0, 100);
        jungle_region_weight = BUILDER.comment("Fantasy jungle biome weight").defineInRange("jungle_region_weight", 10, 0, 100);
        savanna_region_weight = BUILDER.comment("Fantasy savanna biome weight").defineInRange("savanna_region_weight", 10, 0, 100);
        glowing_trees = BUILDER.comment("Should tree blocks emit light").define("glowing_trees", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
