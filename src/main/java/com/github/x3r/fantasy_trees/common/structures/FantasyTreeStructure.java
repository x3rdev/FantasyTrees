package com.github.x3r.fantasy_trees.common.structures;

import com.github.x3r.fantasy_trees.registry.StructureRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class FantasyTreeStructure extends Structure {

    public static final int TREE_OFFSET = 3;
    public static final Codec<FantasyTreeStructure> CODEC = RecordCodecBuilder.<FantasyTreeStructure>mapCodec(instance ->
            instance.group(FantasyTreeStructure.settingsCodec(instance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool)
            ).apply(instance, FantasyTreeStructure::new)).codec();

    private final Holder<StructureTemplatePool> startPool;

    public FantasyTreeStructure(Structure.StructureSettings config, Holder<StructureTemplatePool> startPool) {
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
        Rotation rotation = Rotation.getRandom(context.random());
        StructurePoolElement element = startPool.get().getRandomTemplate(context.random());
        Vec3i size = element.getSize(context.structureTemplateManager(), rotation);
        BlockPos pos = context.chunkPos().getWorldPosition();
        BlockPos centerPos = pos.offset(new BlockPos(size.getX()/2, 100, size.getZ()/2).rotate(rotation));
        if(!FantasyTreeStructure.isValidBiome(context, centerPos)) {
            return Optional.empty();
        }
        int y = context.chunkGenerator().getFirstFreeHeight(centerPos.getX(), centerPos.getZ(), Heightmap.Types.OCEAN_FLOOR_WG, context.heightAccessor(), context.randomState()) - TREE_OFFSET;
        centerPos = centerPos.atY(y);
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
                128,
                element,
                rotation);
    }

    public static boolean isValidBiome(Structure.GenerationContext context, BlockPos blockpos) {
        return context.validBiome().test(context.chunkGenerator().getBiomeSource().getNoiseBiome(QuartPos.fromBlock(blockpos.getX()), QuartPos.fromBlock(blockpos.getY()), QuartPos.fromBlock(blockpos.getZ()), context.randomState().sampler()));
    }

    public static boolean isFeatureChunk(@NotNull Structure.GenerationContext context, BlockPos pos) {
        if(!StructureUtils.isChunkFlat(pos, context.randomState().sampler(), Climate.Parameter.span(-0.3F, 0.3F), Climate.Parameter.span(-0.5F, 0.5F))) {
            return false;
        }
        if(!StructureUtils.isVolumeDry(pos, context.chunkGenerator(), context.heightAccessor(), 2, context.randomState())) {
            return false;
        }
        return true;
    }

    @Override
    public StructureType<?> type() {
        return StructureRegistry.FANTASY_TREE_STRUCTURES.get();
    }
}
