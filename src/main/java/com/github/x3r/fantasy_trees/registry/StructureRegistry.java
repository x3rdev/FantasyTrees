package com.github.x3r.fantasy_trees.registry;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.github.x3r.fantasy_trees.common.structures.FantasyTreeStructure;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class StructureRegistry {

    public static final DeferredRegister<StructureType<?>> STRUCTURE_FEATURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, FantasyTrees.MOD_ID);
    public static final RegistryObject<StructureType<FantasyTreeStructure>> FANTASY_TREE_STRUCTURES = STRUCTURE_FEATURES.register("fantasy_tree_structures", () -> () -> FantasyTreeStructure.CODEC);

    public static final List<String> TREES = List.of(
            "fantasy_acacia_small",
//            "fantasy_acacia_medium",
            "fantasy_acacia_large",
            "fantasy_birch_small",
            "fantasy_birch_medium",
            "fantasy_birch_large",
//            "fantasy_dark_oak_small",
//            "fantasy_dark_oak_medium",
            "fantasy_dark_oak_large",
            "fantasy_jungle_small",
            "fantasy_jungle_medium",
            "fantasy_jungle_large",
            "fantasy_oak_small",
            "fantasy_oak_medium",
            "fantasy_oak_large",
            "fantasy_spruce_small",
            "fantasy_spruce_medium",
            "fantasy_spruce_large"
    );

    public static void bootstrapStructurePools(BootstapContext<StructureTemplatePool> context) {
        HolderGetter<StructureTemplatePool> structureTemplatePoolHolderGetter = context.lookup(Registries.TEMPLATE_POOL);
        Holder.Reference<StructureTemplatePool> empty = structureTemplatePoolHolderGetter.getOrThrow(Pools.EMPTY);
        for(String tree : TREES) {
            int[] ids = switch (tree) {
                case "fantasy_acacia_small" -> new int[]{1, 2, 3};
                case "fantasy_acacia_medium" -> new int[]{};
                case "fantasy_acacia_large" -> new int[]{1, 2, 3};
                case "fantasy_birch_small" -> new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
                case "fantasy_birch_medium" -> new int[]{1, 2, 3, 4};
                case "fantasy_birch_large" -> new int[]{1};
                case "fantasy_dark_oak_small" -> new int[]{};
                case "fantasy_dark_oak_medium" -> new int[]{};
                case "fantasy_dark_oak_large" -> new int[]{2, 3, 4};
                case "fantasy_jungle_small" -> new int[]{1, 2};
                case "fantasy_jungle_medium" -> new int[]{1, 2};
                case "fantasy_jungle_large" -> new int[]{1, 2, 3};
                case "fantasy_oak_small" -> new int[]{1, 2, 3, 4, 5, 6, 7};
                case "fantasy_oak_medium" -> new int[]{1, 2, 3, 4, 5};
                case "fantasy_oak_large" -> new int[]{1, 2, 3};
                case "fantasy_spruce_small" -> new int[]{1, 2, 3};
                case "fantasy_spruce_medium" -> new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
                case "fantasy_spruce_large" -> new int[]{1, 2, 3};
                default -> throw new IllegalStateException("Unexpected value: " + tree);
            };
            List<Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer>> list = new ArrayList<>();
            for (int id : ids) {
                list.add(Pair.of(StructurePoolElement.single("fantasy_trees:" + tree + "_" + id), 1));
            }
            context.register(ResourceKey.create(Registries.TEMPLATE_POOL, new ResourceLocation(FantasyTrees.MOD_ID, tree)),
                    new StructureTemplatePool(empty, list, StructureTemplatePool.Projection.RIGID));
        }
    }
    public static void bootstrapStructureSets(BootstapContext<StructureSet> context) {
        HolderGetter<Structure> structureHolderGetter = context.lookup(Registries.STRUCTURE);
        RandomSource randomSource = RandomSource.create(0);
        for(String tree : TREES) {
            ResourceKey<StructureSet> key = ResourceKey.create(Registries.STRUCTURE_SET,
                    new ResourceLocation(FantasyTrees.MOD_ID, tree));
            ResourceKey<Structure> structureResourceKey = ResourceKey.create(Registries.STRUCTURE,
                    new ResourceLocation(FantasyTrees.MOD_ID, tree));
            context.register(key, new StructureSet(
                    structureHolderGetter.getOrThrow(structureResourceKey),
                    new RandomSpreadStructurePlacement(2, 1, RandomSpreadType.LINEAR, Mth.abs(randomSource.nextInt()))));
        }
    }
    public static void bootstrap(BootstapContext<Structure> context) {
        HolderGetter<Biome> biomeHolderGetter = context.lookup(Registries.BIOME);
        HolderGetter<StructureTemplatePool> structureTemplatePoolHolderGetter = context.lookup(Registries.TEMPLATE_POOL);
        for(String tree : TREES) {
            ResourceKey<Structure> key = ResourceKey.create(Registries.STRUCTURE,
                    new ResourceLocation(FantasyTrees.MOD_ID, tree));
            StringBuilder builder = new StringBuilder(tree);
            builder.replace(0, 8, "").replace(builder.lastIndexOf("_"), builder.length(), "");
            TagKey<Biome> biomeTagKey = TagKey.create(Registries.BIOME,
                    new ResourceLocation(FantasyTrees.MOD_ID, "has_structure/" + builder + "_biomes"));
            ResourceKey<StructureTemplatePool> poolResourceKey = ResourceKey.create(Registries.TEMPLATE_POOL,
                    new ResourceLocation(FantasyTrees.MOD_ID, tree));
            context.register(key, new FantasyTreeStructure(
                    new Structure.StructureSettings(biomeHolderGetter.getOrThrow(biomeTagKey), Map.of(), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.NONE),
                    structureTemplatePoolHolderGetter.getOrThrow(poolResourceKey), 0));
        }
    }

}
