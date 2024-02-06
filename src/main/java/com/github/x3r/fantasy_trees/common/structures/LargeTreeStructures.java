package com.github.x3r.fantasy_trees.common.structures;

import com.github.x3r.fantasy_trees.common.features.TreeConfiguration;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
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
        if (startJigsawName.isEmpty()) {
            return Optional.empty();
        }
        StructureTemplate structureTemplate = context.structureTemplateManager().getOrCreate(startJigsawName.get());
        BlockPos pos = context.chunkPos().getWorldPosition();
        Rotation rotation = Rotation.getRandom(context.random());
        BlockPos centerPos = pos.offset(new BlockPos((structureTemplate.getSize().getX()/2), pos.getY(), (structureTemplate.getSize().getZ()/2)).rotate(rotation));
        int y = context.chunkGenerator().getFirstOccupiedHeight(centerPos.getX(), centerPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());
        centerPos = centerPos.atY(y - 5 + getOffset(startJigsawName.get()));
        if(!LargeTreeStructures.isFeatureChunk(context, centerPos)) {
            return Optional.empty();
        }
        return FantasyTreesJigsawPlacement.addPieces(
                context, this.startPool, startJigsawName, this.size,
                pos.atY(centerPos.getY()),
                false,
                this.projectStartToHeightmap,
                this.maxDistanceFromCenter,
                rotation);
    }

    public static boolean isFeatureChunk(@NotNull Structure.GenerationContext context, BlockPos pos) {
        if(!validBiomeOnTop(context, Heightmap.Types.WORLD_SURFACE_WG)) {
            return false;
        }
        if(!StructureUtils.isChunkFlat(pos, context.randomState().sampler(), Climate.Parameter.span(-0.2F, 0.2F), Climate.Parameter.span(-0.6F, 0.6F))) {
            return false;
        }
        if(!StructureUtils.isAreaDry(pos, context, 4, 3)) {
            return false;
        }
        return true;
    }

    public static boolean validBiomeOnTop(GenerationContext context, Heightmap.Types pHeightmapType) {
        int $$1 = context.chunkPos().getMiddleBlockX();
        int $$2 = context.chunkPos().getMiddleBlockZ();
        int $$3 = context.chunkGenerator().getFirstOccupiedHeight($$1, $$2, pHeightmapType, context.heightAccessor(), context.randomState());
        Holder<Biome> $$4 = context.chunkGenerator().getBiomeSource().getNoiseBiome(QuartPos.fromBlock($$1), QuartPos.fromBlock($$3), QuartPos.fromBlock($$2), context.randomState().sampler());
        return context.validBiome().test($$4);
    }

    @Override
    public StructureType<?> type() {
        return null;
    }

    private static int getOffset(ResourceLocation resourceLocation) {
        if (resourceLocation.getPath().equals("fantasy_dark_oak_large_1")) {
            return -15;
        }
        if (resourceLocation.getPath().equals("fantasy_acacia_large_2")) {
            return 4;
        }
        return 0;
    }
}
