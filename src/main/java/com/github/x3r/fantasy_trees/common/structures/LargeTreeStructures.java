package com.github.x3r.fantasy_trees.common.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class LargeTreeStructures extends Structure {
    public static final Codec<LargeTreeStructures> CODEC = RecordCodecBuilder.<LargeTreeStructures>mapCodec(instance ->
            instance.group(LargeTreeStructures.settingsCodec(instance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter)
            ).apply(instance, LargeTreeStructures::new)).codec();

    private final Holder<StructureTemplatePool> startPool;
    private final Optional<ResourceLocation> startJigsawName;
    private final int size;
    private final HeightProvider startHeight;
    private final Optional<Heightmap.Types> projectStartToHeightmap;
    private final int maxDistanceFromCenter;

    public LargeTreeStructures(Structure.StructureSettings config,
                               Holder<StructureTemplatePool> startPool,
                               Optional<ResourceLocation> startJigsawName,
                               int size,
                               HeightProvider startHeight,
                               Optional<Heightmap.Types> projectStartToHeightmap,
                               int maxDistanceFromCenter) {
        super(config);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.size = size;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
    }


    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.TOP_LAYER_MODIFICATION;
    }


    @Override
    public @NotNull Optional<Structure.GenerationStub> findGenerationPoint(Structure.@NotNull GenerationContext context) {
        BlockPos blockPos = context.chunkPos().getWorldPosition();
        Optional<Structure.GenerationStub> structurePiecesGenerator =
                JigsawPlacement.addPieces(
                        context,
                        this.startPool, this.startJigsawName, this.size, blockPos,
                        false, this.projectStartToHeightmap, this.maxDistanceFromCenter);
        return structurePiecesGenerator;
    }

    @Override
    public StructureType<?> type() {
        return null;
    }

    private static int getOffset(ResourceLocation resourceLocation) {
        if(resourceLocation.getPath().equals("fantasy_dark_oak_large_1")) {
            return -15;
        }
        if(resourceLocation.getPath().equals("fantasy_acacia_large_2")) {
            return 4;
        }
        return 0;
    }
}
