package com.github.x3r.fantasy_trees.common.structures;

import com.github.x3r.fantasy_trees.registry.StructureRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class FantasyTreeStructure extends Structure {
    public static final Codec<FantasyTreeStructure> CODEC = RecordCodecBuilder.<FantasyTreeStructure>mapCodec(instance ->
            instance.group(FantasyTreeStructure.settingsCodec(instance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    Codec.INT.fieldOf("offset").forGetter(structure -> structure.offset)
            ).apply(instance, FantasyTreeStructure::new)).codec();

    private final Holder<StructureTemplatePool> startPool;
    private final int offset;

    public FantasyTreeStructure(Structure.StructureSettings config, Holder<StructureTemplatePool> startPool, int offset) {
        super(config);
        this.startPool = startPool;
        this.offset = offset;
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
        Rotation rotation = Rotation.NONE;
//                Rotation.getRandom(context.random());
        BlockPos centerPos = pos.offset(new BlockPos((structureTemplate.getSize().getX()/2), pos.getY(), (structureTemplate.getSize().getZ()/2)).rotate(rotation));
        int y = context.chunkGenerator().getFirstOccupiedHeight(centerPos.getX(), centerPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());
        centerPos = centerPos.atY(y + offset);
        if(!FantasyTreeStructure.isFeatureChunk(context, centerPos)) {
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
        if(!StructureUtils.isAreaDry(pos, context.chunkGenerator(), context.heightAccessor(),4, context.randomState())) {
            return false;
        }
        return true;
    }

    public static boolean validBiomeOnTop(GenerationContext context, Heightmap.Types pHeightmapType) {
        int x = context.chunkPos().getMiddleBlockX();
        int z = context.chunkPos().getMiddleBlockZ();
        int height = context.chunkGenerator().getFirstOccupiedHeight(x, z, pHeightmapType, context.heightAccessor(), context.randomState());
        Holder<Biome> biome = context.chunkGenerator().getBiomeSource().getNoiseBiome(QuartPos.fromBlock(x), QuartPos.fromBlock(height), QuartPos.fromBlock(z), context.randomState().sampler());
        return context.validBiome().test(biome);
    }

    @Override
    public StructureType<?> type() {
        return StructureRegistry.FANTASY_TREE_STRUCTURES.get();
    }
}
