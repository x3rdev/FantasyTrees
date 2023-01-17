package com.github.x3rmination.fantasy_trees.registry;

import com.github.x3rmination.fantasy_trees.FantasyTrees;
import com.github.x3rmination.fantasy_trees.common.structures.TreeStructures;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class StructureRegistry {

    public static final DeferredRegister<StructureFeature<?>> STRUCTURE_FEATURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, FantasyTrees.MOD_ID);

    public static final RegistryObject<StructureFeature<?>> TREE_STRUCTURES = STRUCTURE_FEATURES.register("tree_structures", TreeStructures::new);
}
