package com.github.x3r.fantasy_trees.registry;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.github.x3r.fantasy_trees.common.worldgen.WaterLoggingFixProcessor;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ProcessorRegistry {

    public static final DeferredRegister<StructureProcessorType<?>> PROCESSORS = DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, FantasyTrees.MOD_ID);

    public static final RegistryObject<StructureProcessorType<WaterLoggingFixProcessor>> WATER_LOGGING_FIX_PROCESSOR = PROCESSORS.register("water_logging_fix_processor",
            () -> () -> WaterLoggingFixProcessor.CODEC);

}
