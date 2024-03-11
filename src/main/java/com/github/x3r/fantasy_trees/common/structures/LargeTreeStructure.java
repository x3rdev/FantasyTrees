package com.github.x3r.fantasy_trees.common.structures;

import com.github.x3r.fantasy_trees.registry.StructureRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.DesertPyramidStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class LargeTreeStructure extends Structure {
    public static final Codec<LargeTreeStructure> CODEC = RecordCodecBuilder.<LargeTreeStructure>mapCodec(instance ->
            instance.group(LargeTreeStructure.settingsCodec(instance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool)
            ).apply(instance, LargeTreeStructure::new)).codec();

    private final Holder<StructureTemplatePool> startPool;

    public LargeTreeStructure(Structure.StructureSettings config, Holder<StructureTemplatePool> startPool) {
        super(config);
        this.startPool = startPool;
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        if(startPool.unwrapKey().isEmpty()) {
            return Optional.empty();
        }
        ResourceLocation key = startPool.unwrapKey().get().location();
        StructureTemplate structureTemplate = context.structureTemplateManager().getOrCreate(key);
        BlockPos pos = context.chunkPos().getWorldPosition();
        Rotation rotation = Rotation.getRandom(context.random());
        BlockPos centerPos = pos.offset(new BlockPos((structureTemplate.getSize().getX()/2), pos.getY(), (structureTemplate.getSize().getZ()/2)).rotate(rotation));
        int y = context.chunkGenerator().getFirstOccupiedHeight(centerPos.getX(), centerPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());
        centerPos = centerPos.atY(y + getOffset(key));
        if(!LargeTreeStructure.isFeatureChunk(context, centerPos)) {
            return Optional.empty();
        }
        return FantasyTreesJigsawPlacement.addPieces(
                context,
                this.startPool,
                Optional.empty(),
                1,
                pos.atY(centerPos.getY()),
                false,
                Optional.empty(),
                1,
                rotation);
    }

    public static boolean isFeatureChunk(@NotNull Structure.GenerationContext context, BlockPos pos) {
        if(!validBiomeOnTop(context, Heightmap.Types.WORLD_SURFACE_WG)) {
            return false;
        }
        if(!StructureUtils.isChunkFlat(pos, context.randomState().sampler(), Climate.Parameter.span(-0.2F, 0.2F), Climate.Parameter.span(-0.6F, 0.6F))) {
            return false;
        }
        if(!StructureUtils.isAreaDry(pos, context, 4)) {
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
        return StructureRegistry.LARGE_TREE_STRUCTURES.get();
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
