package com.github.x3r.fantasy_trees.registry;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.github.x3r.fantasy_trees.common.structures.LargeTreeStructure;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class StructureRegistry {

    public static final DeferredRegister<StructureType<?>> STRUCTURE_FEATURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, FantasyTrees.MOD_ID);

    public static final RegistryObject<StructureType<LargeTreeStructure>> LARGE_TREE_STRUCTURES = STRUCTURE_FEATURES.register("large_tree_structures", () -> () -> LargeTreeStructure.CODEC);

    public static final List<String> TREES = List.of(
            "fantasy_acacia_small", "fantasy_acacia_medium", "fantasy_acacia_large",
            "fantasy_birch_small", "fantasy_birch_medium", "fantasy_birch_large",
            "fantasy_dark_oak_small", "fantasy_dark_oak_medium", "fantasy_dark_oak_large",
            "fantasy_jungle_small", "fantasy_jungle_medium", "fantasy_jungle_large",
            "fantasy_oak_small", "fantasy_oak_medium", "fantasy_oak_large",
            "fantasy_spruce_small", "fantasy_spruce_medium", "fantasy_spruce_large"
    );


    public static void bootstrapTemplatePools(BootstapContext<StructureTemplatePool> context) {
        HolderGetter<StructureTemplatePool> structureTemplatePoolHolderGetter = context.lookup(Registries.TEMPLATE_POOL);
        for(String tree : TREES) {
            ResourceKey<StructureTemplatePool> key = ResourceKey.create(Registries.TEMPLATE_POOL,
                    new ResourceLocation(FantasyTrees.MOD_ID, tree));
            Holder<StructureTemplatePool> empty = structureTemplatePoolHolderGetter.getOrThrow(Pools.EMPTY);
            List<Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer>> list = ImmutableList.of(
                    Pair.of(StructurePoolElement.single(FantasyTrees.MOD_ID + ""), 1)
            );
            context.register(key, new StructureTemplatePool(empty, list, StructureTemplatePool.Projection.RIGID));
        }

    }
    public static void bootstrapStructureSets(BootstapContext<StructureSet> context) {
        for(String tree : TREES) {

        }
    }
    public static void bootstrap(BootstapContext<Structure> context) {
        HolderGetter<Biome> biomeHolderGetter = context.lookup(Registries.BIOME);
        HolderGetter<StructureTemplatePool> structureTemplatePoolHolderGetter = context.lookup(Registries.TEMPLATE_POOL);
        for(String tree : TREES) {
            ResourceKey<StructureTemplatePool> startPool = ResourceKey.create(Registries.TEMPLATE_POOL,
                    new ResourceLocation(FantasyTrees.MOD_ID, tree));
            ResourceKey<Structure> key = ResourceKey.create(Registries.STRUCTURE,
                    new ResourceLocation(FantasyTrees.MOD_ID, tree));
            TagKey<Biome> tagKey = TagKey.create(Registries.BIOME,
                    new ResourceLocation(FantasyTrees.MOD_ID, "worldgen/biome/has_structure/" + tree + "_biomes"));
            context.register(key, new LargeTreeStructure(
                    new Structure.StructureSettings(biomeHolderGetter.getOrThrow(tagKey), Map.of(), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.NONE),
                    structureTemplatePoolHolderGetter.getOrThrow(startPool)));
        }
    }
}
