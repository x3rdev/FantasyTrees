package com.github.x3r.fantasy_trees.registry;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.github.x3r.fantasy_trees.common.structures.LargeTreeStructure;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.AncientCityStructurePieces;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.PillagerOutpostPools;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
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
//            "fantasy_acacia_small", "fantasy_acacia_medium",
            "fantasy_acacia_large"
//            "fantasy_birch_small", "fantasy_birch_medium", "fantasy_birch_large",
//            "fantasy_dark_oak_small", "fantasy_dark_oak_medium", "fantasy_dark_oak_large",
//            "fantasy_jungle_small", "fantasy_jungle_medium", "fantasy_jungle_large",
//            "fantasy_oak_small", "fantasy_oak_medium", "fantasy_oak_large",
//            "fantasy_spruce_small", "fantasy_spruce_medium", "fantasy_spruce_large"
    );
    public static void bootstrapStructureSets(BootstapContext<StructureSet> context) {
//        HolderGetter<Structure> structureHolderGetter = context.lookup(Registries.STRUCTURE);
//        for(String tree : TREES) {
//            ResourceKey<StructureSet> key = ResourceKey.create(Registries.STRUCTURE_SET,
//                    new ResourceLocation(FantasyTrees.MOD_ID, tree));
//            ResourceKey<Structure> structureResourceKey = ResourceKey.create(Registries.STRUCTURE,
//                    new ResourceLocation(FantasyTrees.MOD_ID, tree));
//            context.register(key, new StructureSet(
//                    structureHolderGetter.getOrThrow(structureResourceKey),
//                    new RandomSpreadStructurePlacement(5, 4, RandomSpreadType.LINEAR, 67801345)));
//        }
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
                    new ResourceLocation(FantasyTrees.MOD_ID, "tags/worldgen/biome/has_structure/" + builder + "_biomes"));
//            ResourceKey<StructureTemplatePool> poolResourceKey = ResourceKey.create(Registries.TEMPLATE_POOL,
//                    new ResourceLocation(FantasyTrees.MOD_ID, tree));
            //biomeHolderGetter.getOrThrow(biomeTagKey)

            context.register(key, new LargeTreeStructure(
                    new Structure.StructureSettings(biomeHolderGetter.getOrThrow(BiomeTags.HAS_NETHER_FORTRESS), Map.of(), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.NONE),
                    structureTemplatePoolHolderGetter.getOrThrow(PillagerOutpostPools.START)));
        }
    }
}
