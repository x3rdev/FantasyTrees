package com.github.x3r.fantasy_trees.registry;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.github.x3r.fantasy_trees.common.structures.LargeTreeStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class StructureRegistry {

    public static final DeferredRegister<StructureType<?>> STRUCTURE_FEATURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, FantasyTrees.MOD_ID);

    public static final RegistryObject<StructureType<LargeTreeStructures>> LARGE_TREE_STRUCTURES = STRUCTURE_FEATURES.register("large_tree_structures", () -> () -> LargeTreeStructures.CODEC);
}
