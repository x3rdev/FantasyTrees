package com.github.x3r.fantasy_trees.registry;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.github.x3r.fantasy_trees.common.structures.FantasyTreeStructure;
import com.github.x3r.fantasy_trees.common.worldgen.WaterLoggingFixProcessor;
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
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class StructureRegistry {

    public static final DeferredRegister<StructureType<?>> STRUCTURE_FEATURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, FantasyTrees.MOD_ID);
    public static final RegistryObject<StructureType<FantasyTreeStructure>> FANTASY_TREE_STRUCTURES = STRUCTURE_FEATURES.register("fantasy_tree_structures", () -> () -> FantasyTreeStructure.CODEC);

    public static final List<Pair<String, Integer>> ACACIA_LARGE_WEIGHTS = List.of(
            Pair.of("fantasy_acacia_large_1", 1),
            Pair.of("fantasy_acacia_large_2", 1),
            Pair.of("fantasy_acacia_large_3", 1)
    );
    public static final List<Pair<String, Integer>> ACACIA_SMALL_WEIGHTS = List.of(
            Pair.of("fantasy_acacia_small_1", 1),
            Pair.of("fantasy_acacia_small_2", 1),
            Pair.of("fantasy_acacia_small_3", 1)
    );
    public static final List<Pair<String, Integer>> BIRCH_LARGE_WEIGHTS = List.of(
            Pair.of("fantasy_birch_large_1", 1)
    );
    public static final List<Pair<String, Integer>> BIRCH_MEDIUM_WEIGHTS = List.of(
            Pair.of("fantasy_birch_medium_1", 1),
            Pair.of("fantasy_birch_medium_2", 1),
            Pair.of("fantasy_birch_medium_3", 1),
            Pair.of("fantasy_birch_medium_4", 1)
    );
    public static final List<Pair<String, Integer>> BIRCH_SMALL_WEIGHTS = List.of(
            Pair.of("fantasy_birch_small_1", 1),
            Pair.of("fantasy_birch_small_2", 1),
            Pair.of("fantasy_birch_small_3", 1),
            Pair.of("fantasy_birch_small_4", 1),
            Pair.of("fantasy_birch_small_5", 1),
            Pair.of("fantasy_birch_small_6", 1),
            Pair.of("fantasy_birch_small_7", 1),
            Pair.of("fantasy_birch_small_8", 1),
            Pair.of("fantasy_birch_small_9", 1),
            Pair.of("fantasy_birch_small_10", 1)
    );
    public static final List<Pair<String, Integer>> DARK_OAK_LARGE_WEIGHTS = List.of(
            Pair.of("fantasy_dark_oak_large_2", 1),
            Pair.of("fantasy_dark_oak_large_3", 1),
            Pair.of("fantasy_dark_oak_large_4", 1)
    );
    public static final List<Pair<String, Integer>> JUNGLE_LARGE_WEIGHTS = List.of(
            Pair.of("fantasy_jungle_large_1", 1),
            Pair.of("fantasy_jungle_large_2", 1),
            Pair.of("fantasy_jungle_large_3", 1)
    );
    public static final List<Pair<String, Integer>> JUNGLE_MEDIUM_WEIGHTS = List.of(
            Pair.of("fantasy_jungle_medium_1", 1),
            Pair.of("fantasy_jungle_medium_2", 1)
    );
    public static final List<Pair<String, Integer>> JUNGLE_SMALL_WEIGHTS = List.of(
            Pair.of("fantasy_jungle_small_1", 1),
            Pair.of("fantasy_jungle_small_2", 1)
    );
    public static final List<Pair<String, Integer>> OAK_LARGE_WEIGHTS = List.of(
            Pair.of("fantasy_oak_large_1", 1),
            Pair.of("fantasy_oak_large_2", 1),
            Pair.of("fantasy_oak_large_3", 1),
            Pair.of("fantasy_oak_large_4", 1),
            Pair.of("fantasy_oak_large_5", 1)
    );
    public static final List<Pair<String, Integer>> OAK_MEDIUM_WEIGHTS = List.of(
            Pair.of("fantasy_oak_medium_1", 1),
            Pair.of("fantasy_oak_medium_2", 1),
            Pair.of("fantasy_oak_medium_3", 1),
            Pair.of("fantasy_oak_medium_4", 1),
            Pair.of("fantasy_oak_medium_5", 1)
    );
    public static final List<Pair<String, Integer>> OAK_SMALL_WEIGHTS = List.of(
            Pair.of("fantasy_oak_small_1", 1),
            Pair.of("fantasy_oak_small_2", 1),
            Pair.of("fantasy_oak_small_3", 1),
            Pair.of("fantasy_oak_small_4", 1),
            Pair.of("fantasy_oak_small_4", 1),
            Pair.of("fantasy_oak_small_5", 1),
            Pair.of("fantasy_oak_small_6", 1),
            Pair.of("fantasy_oak_small_7", 1)
    );
    public static final List<Pair<String, Integer>> SPRUCE_LARGE_WEIGHTS = List.of(
            Pair.of("fantasy_spruce_large_1", 1),
            Pair.of("fantasy_spruce_large_2", 1),
            Pair.of("fantasy_spruce_large_3", 1)
    );
    public static final List<Pair<String, Integer>> SPRUCE_MEDIUM_WEIGHTS = List.of(
            Pair.of("fantasy_spruce_medium_1", 1),
            Pair.of("fantasy_spruce_medium_2", 1),
            Pair.of("fantasy_spruce_medium_3", 1),
            Pair.of("fantasy_spruce_medium_4", 1),
            Pair.of("fantasy_spruce_medium_5", 1),
            Pair.of("fantasy_spruce_medium_6", 1),
            Pair.of("fantasy_spruce_medium_7", 1),
            Pair.of("fantasy_spruce_medium_8", 1),
            Pair.of("fantasy_spruce_medium_9", 1),
            Pair.of("fantasy_spruce_medium_10", 1),
            Pair.of("fantasy_spruce_medium_11", 1),
            Pair.of("fantasy_spruce_medium_12", 1)
    );
    public static final List<Pair<String, Integer>> SPRUCE_SMALL_WEIGHTS = List.of(
            Pair.of("fantasy_spruce_small_1", 1),
            Pair.of("fantasy_spruce_small_2", 1),
            Pair.of("fantasy_spruce_small_3", 1)
    );

    public static void bootstrapStructurePools(BootstapContext<StructureTemplatePool> context) {
        Holder.Reference<StructureTemplatePool> empty = context.lookup(Registries.TEMPLATE_POOL).getOrThrow(Pools.EMPTY);
        registerStructurePool(context, "fantasy_acacia_large", empty,
                ACACIA_LARGE_WEIGHTS);
        registerStructurePool(context, "fantasy_acacia_small", empty,
                ACACIA_SMALL_WEIGHTS);
        registerStructurePool(context, "fantasy_birch_large", empty,
                BIRCH_LARGE_WEIGHTS);
        registerStructurePool(context, "fantasy_birch_medium", empty,
                BIRCH_MEDIUM_WEIGHTS);
        registerStructurePool(context, "fantasy_birch_small", empty,
                BIRCH_SMALL_WEIGHTS);
        registerStructurePool(context, "fantasy_dark_oak_large", empty,
                DARK_OAK_LARGE_WEIGHTS);
        registerStructurePool(context, "fantasy_jungle_large", empty,
                JUNGLE_LARGE_WEIGHTS);
        registerStructurePool(context, "fantasy_jungle_medium", empty,
                JUNGLE_MEDIUM_WEIGHTS);
        registerStructurePool(context, "fantasy_jungle_small", empty,
                JUNGLE_SMALL_WEIGHTS);
        registerStructurePool(context, "fantasy_oak_large", empty,
                OAK_LARGE_WEIGHTS);
        registerStructurePool(context, "fantasy_oak_medium", empty,
                OAK_MEDIUM_WEIGHTS);
        registerStructurePool(context, "fantasy_oak_small", empty,
                OAK_SMALL_WEIGHTS);
        registerStructurePool(context, "fantasy_spruce_large", empty,
                SPRUCE_LARGE_WEIGHTS);
        registerStructurePool(context, "fantasy_spruce_medium", empty,
                SPRUCE_MEDIUM_WEIGHTS);
        registerStructurePool(context, "fantasy_spruce_small", empty,
                SPRUCE_SMALL_WEIGHTS);
    }

    private static void registerStructurePool(BootstapContext<StructureTemplatePool> context, String name, Holder<StructureTemplatePool> fallback, List<Pair<String, Integer>> weights) {
        List<Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer>> list = new ArrayList<>();
        Holder<StructureProcessorList> processorListHolder = Holder.direct(new StructureProcessorList(List.of(new WaterLoggingFixProcessor())));
        for (Pair<String, Integer> weight : weights) {
            list.add(Pair.of(StructurePoolElement.single(FantasyTrees.MOD_ID + ":" + weight.getFirst(), processorListHolder), weight.getSecond()));
        }

        context.register(ResourceKey.create(Registries.TEMPLATE_POOL, new ResourceLocation(FantasyTrees.MOD_ID, name)),
                new StructureTemplatePool(fallback, list, StructureTemplatePool.Projection.RIGID));
    }

    public static void bootstrapStructureSets(BootstapContext<StructureSet> context) {
        RandomSource rand = RandomSource.create(1);
        registerStructureSet(context, "fantasy_acacia_large", rand, 2, 1);
        registerStructureSet(context, "fantasy_acacia_small", rand, 1, 0);
        registerStructureSet(context, "fantasy_birch_large", rand, 2, 1);
        registerStructureSet(context, "fantasy_birch_medium", rand, 1, 0);
        registerStructureSet(context, "fantasy_birch_small", rand, 1, 0);
        registerStructureSet(context, "fantasy_dark_oak_large", rand, 2, 1);
        registerStructureSet(context, "fantasy_jungle_large", rand, 2, 1);
        registerStructureSet(context, "fantasy_jungle_medium", rand, 1, 0);
        registerStructureSet(context, "fantasy_jungle_small", rand, 1, 0);
        registerStructureSet(context, "fantasy_oak_large", rand, 2, 1);
        registerStructureSet(context, "fantasy_oak_medium", rand, 1, 0);
        registerStructureSet(context, "fantasy_oak_small", rand, 1, 0);
        registerStructureSet(context, "fantasy_spruce_large", rand, 2, 1);
        registerStructureSet(context, "fantasy_spruce_medium", rand, 1, 0);
        registerStructureSet(context, "fantasy_spruce_small", rand, 1, 0);
    }
    private static void registerStructureSet(BootstapContext<StructureSet> context, String name, RandomSource rand, int spacing, int separation) {
        ResourceKey<StructureSet> key = ResourceKey.create(Registries.STRUCTURE_SET,
                new ResourceLocation(FantasyTrees.MOD_ID, name));
        ResourceKey<Structure> structureResourceKey = ResourceKey.create(Registries.STRUCTURE,
                new ResourceLocation(FantasyTrees.MOD_ID, name));
        context.register(key, new StructureSet(
                context.lookup(Registries.STRUCTURE).getOrThrow(structureResourceKey),
                new RandomSpreadStructurePlacement(spacing, separation, RandomSpreadType.LINEAR, Mth.abs(rand.nextInt()))));
    }
    public static void bootstrap(BootstapContext<Structure> context) {
        registerStructure(context, "fantasy_acacia_large", "acacia");
        registerStructure(context, "fantasy_acacia_small", "acacia");
        registerStructure(context, "fantasy_birch_large", "birch");
        registerStructure(context, "fantasy_birch_medium", "birch");
        registerStructure(context, "fantasy_birch_small", "birch");
        registerStructure(context, "fantasy_dark_oak_large", "dark_oak");
        registerStructure(context, "fantasy_jungle_large", "jungle");
        registerStructure(context, "fantasy_jungle_medium", "jungle");
        registerStructure(context, "fantasy_jungle_small", "jungle");
        registerStructure(context, "fantasy_oak_large", "oak");
        registerStructure(context, "fantasy_oak_medium", "oak");
        registerStructure(context, "fantasy_oak_small", "oak");
        registerStructure(context, "fantasy_spruce_large", "spruce");
        registerStructure(context, "fantasy_spruce_medium", "spruce");
        registerStructure(context, "fantasy_spruce_small", "spruce");
    }

    private static void registerStructure(BootstapContext<Structure> context, String name, String biomeName) {
        ResourceKey<Structure> key = ResourceKey.create(Registries.STRUCTURE,
                new ResourceLocation(FantasyTrees.MOD_ID, name));
        ResourceKey<StructureTemplatePool> poolResourceKey = ResourceKey.create(Registries.TEMPLATE_POOL,
                new ResourceLocation(FantasyTrees.MOD_ID, name));
        TagKey<Biome> biomeTagKey = TagKey.create(Registries.BIOME,
                new ResourceLocation(FantasyTrees.MOD_ID, "has_structure/" + biomeName + "_biomes"));
        context.register(key, new FantasyTreeStructure(
                new Structure.StructureSettings(context.lookup(Registries.BIOME).getOrThrow(biomeTagKey), Map.of(), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.NONE),
                context.lookup(Registries.TEMPLATE_POOL).getOrThrow(poolResourceKey)));
    }
}
