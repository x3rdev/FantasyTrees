package com.github.x3rmination.fantasy_trees;

import net.minecraftforge.common.ForgeConfigSpec;

public class FantasyTreesConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue can_grow_fantasy_sapling;
    public static final ForgeConfigSpec.IntValue growth_delay;

    static {
        BUILDER.push("Fantasy Trees Config");
        can_grow_fantasy_sapling = BUILDER.comment("Can grow fantasy trees").define("can_grow_fantasy_sapling", true);
        growth_delay = BUILDER.comment("Tree growth delay").defineInRange("growth_delay", 3, 0, 100);


        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
