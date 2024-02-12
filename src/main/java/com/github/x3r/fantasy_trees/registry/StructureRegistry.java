package com.github.x3r.fantasy_trees.registry;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.github.x3r.fantasy_trees.common.structures.LargeTreeStructure;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class StructureRegistry {

    public static final DeferredRegister<StructureType<?>> STRUCTURE_FEATURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, FantasyTrees.MOD_ID);

    public static final RegistryObject<StructureType<LargeTreeStructure>> LARGE_TREE_STRUCTURES = STRUCTURE_FEATURES.register("large_tree_structures", () -> () -> LargeTreeStructure.CODEC);
}
