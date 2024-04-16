package com.github.x3r.fantasy_trees.common.worldgen;

import com.github.x3r.fantasy_trees.registry.ProcessorRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public class WaterLoggingFixProcessor extends StructureProcessor {

    public static final Codec<WaterLoggingFixProcessor> CODEC = Codec.unit(WaterLoggingFixProcessor::new);

    public WaterLoggingFixProcessor() {

    }

    @Override
    protected StructureProcessorType<?> getType() {
        return ProcessorRegistry.WATER_LOGGING_FIX_PROCESSOR.get();
    }
}
