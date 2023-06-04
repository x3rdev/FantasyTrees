package com.github.x3rmination.fantasy_trees.common.structures;

import com.github.x3rmination.fantasy_trees.common.util.FantasyJigsawPlacement;
import com.github.x3rmination.fantasy_trees.common.util.StructureUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import terrablender.api.ParameterUtils;

import java.util.Optional;
import java.util.Random;

public class LargeTreeStructures extends StructureFeature<JigsawConfiguration> {

    private static Random random = new Random();
    public static final Codec<JigsawConfiguration> CODEC = RecordCodecBuilder.create((codec) -> {
        return codec.group(
                StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(JigsawConfiguration::startPool),
                Codec.intRange(1, 15).fieldOf("size").forGetter(JigsawConfiguration::maxDepth)
        ).apply(codec, JigsawConfiguration::new);
    });

    public LargeTreeStructures() {
        super(CODEC, LargeTreeStructures::createPiecesGenerator, PostPlacementProcessor.NONE);
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.TOP_LAYER_MODIFICATION;
    }

    public static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context, BlockPos pos) {
        if(!context.validBiomeOnTop(Heightmap.Types.WORLD_SURFACE_WG)) {
            return false;
        }
        if(!StructureUtils.isChunkFlat(pos, context.chunkGenerator(), Climate.Parameter.span(-0.2F, 0.2F), Climate.Parameter.span(-0.6F, 0.6F))) {
            return false;
        }
        if(!StructureUtils.isAreaDry(context, pos, 4)) {
            return false;
        }
        return true;
    }

    public static Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        ResourceLocation location = context.config().startPool().value().getName();
        StructureTemplate structuretemplate = context.structureManager().getOrCreate(location);
        BlockPos pos = context.chunkPos().getWorldPosition();
        Rotation rotation = Rotation.getRandom(random);
        int xO = random.nextInt(5);
        int zO = random.nextInt(5);
        BlockPos centerOffset = new BlockPos((structuretemplate.getSize().getX()/2), pos.getY(), (structuretemplate.getSize().getZ()/2)).rotate(rotation);
        BlockPos centerPos = pos.offset(centerOffset).offset(xO, 0, zO);
        int y = context.chunkGenerator().getFirstOccupiedHeight(centerPos.getX(), centerPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
        centerPos = centerPos.atY(y);
        if(!LargeTreeStructures.isFeatureChunk(context, centerPos)) {
            return Optional.empty();
        }

        Optional<PieceGenerator<JigsawConfiguration>> structurePiecesGenerator =
                FantasyJigsawPlacement.addPieces(
                        context,
                        PoolElementStructurePiece::new,
                        pos.atY(y - 5 + getOffset(location)),
                        false,
                        false,
                        rotation
                );

        return structurePiecesGenerator;
    }

    private static int getOffset(ResourceLocation resourceLocation) {
        if(resourceLocation.getPath().equals("fantasy_dark_oak_large_1")) {
            return -15;
        }
        return 0;
    }
}
