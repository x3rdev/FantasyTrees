package com.github.x3r.fantasy_trees.common.structures;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.google.common.collect.Lists;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pools.EmptyPoolElement;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.Optional;

public class FantasyTreesJigsawPlacement {

    public static Optional<Structure.GenerationStub> addPieces(Structure.GenerationContext pContext, Holder<StructureTemplatePool> pStartPool, Optional<ResourceLocation> pStartJigsawName, int pMaxDepth, BlockPos pPos, boolean pUseExpansionHack, Optional<Heightmap.Types> pProjectStartToHeightmap, int pMaxDistanceFromCenter, Rotation rotation) {
        RegistryAccess registryaccess = pContext.registryAccess();
        ChunkGenerator chunkgenerator = pContext.chunkGenerator();
        StructureTemplateManager structuretemplatemanager = pContext.structureTemplateManager();
        LevelHeightAccessor levelheightaccessor = pContext.heightAccessor();
        WorldgenRandom worldgenrandom = pContext.random();
        Registry<StructureTemplatePool> registry = registryaccess.registryOrThrow(Registries.TEMPLATE_POOL);
        StructureTemplatePool structuretemplatepool = pStartPool.value();
        StructurePoolElement structurepoolelement = structuretemplatepool.getRandomTemplate(worldgenrandom);
        if (structurepoolelement == EmptyPoolElement.INSTANCE) {
            return Optional.empty();
        } else {
            BlockPos blockpos;
            if (pStartJigsawName.isPresent()) {
                ResourceLocation resourcelocation = pStartJigsawName.get();
                Optional<BlockPos> optional = JigsawPlacement.getRandomNamedJigsaw(structurepoolelement, resourcelocation, pPos, rotation, structuretemplatemanager, worldgenrandom);
                if (optional.isEmpty()) {
                    FantasyTrees.LOGGER.error("No starting jigsaw {} found in start pool {}", resourcelocation, pStartPool.unwrapKey().map((p_248484_) -> {
                        return p_248484_.location().toString();
                    }).orElse("<unregistered>"));
                    return Optional.empty();
                }

                blockpos = optional.get();
            } else {
                blockpos = pPos;
            }

            Vec3i vec3i = blockpos.subtract(pPos);
            BlockPos blockpos1 = pPos.subtract(vec3i);
            PoolElementStructurePiece poolelementstructurepiece = new PoolElementStructurePiece(structuretemplatemanager, structurepoolelement, blockpos1, structurepoolelement.getGroundLevelDelta(), rotation, structurepoolelement.getBoundingBox(structuretemplatemanager, blockpos1, rotation));
            BoundingBox boundingbox = poolelementstructurepiece.getBoundingBox();
            int i = (boundingbox.maxX() + boundingbox.minX()) / 2;
            int j = (boundingbox.maxZ() + boundingbox.minZ()) / 2;
            int k;
            if (pProjectStartToHeightmap.isPresent()) {
                k = pPos.getY() + chunkgenerator.getFirstFreeHeight(i, j, pProjectStartToHeightmap.get(), levelheightaccessor, pContext.randomState());
            } else {
                k = blockpos1.getY();
            }

            int l = boundingbox.minY() + poolelementstructurepiece.getGroundLevelDelta();
            poolelementstructurepiece.move(0, k - l, 0);
            int i1 = k + vec3i.getY();
            return Optional.of(new Structure.GenerationStub(new BlockPos(i, i1, j), (p_227237_) -> {
                List<PoolElementStructurePiece> list = Lists.newArrayList();
                list.add(poolelementstructurepiece);
                if (pMaxDepth > 0) {
                    AABB aabb = new AABB((i - pMaxDistanceFromCenter), (i1 - pMaxDistanceFromCenter), (j - pMaxDistanceFromCenter), (double)(i + pMaxDistanceFromCenter + 1), (double)(i1 + pMaxDistanceFromCenter + 1), (double)(j + pMaxDistanceFromCenter + 1));
                    VoxelShape voxelshape = Shapes.join(Shapes.create(aabb), Shapes.create(AABB.of(boundingbox)), BooleanOp.ONLY_FIRST);
                    JigsawPlacement.addPieces(pContext.randomState(), pMaxDepth, pUseExpansionHack, chunkgenerator, structuretemplatemanager, levelheightaccessor, worldgenrandom, registry, poolelementstructurepiece, list, voxelshape);
                    list.forEach(p_227237_::addPiece);
                }
            }));
        }
    }
}
