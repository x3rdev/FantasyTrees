package com.github.x3r.fantasy_trees.mixins;

import com.github.x3r.fantasy_trees.common.worldgen.WaterLoggingFixProcessor;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StructureTemplate.class)
public class StructureTemplateMixin {

    @Inject(method = "placeInWorld", at = @At(value = "HEAD"))
    private void preventAutoWaterLogging(ServerLevelAccessor pServerLevel, BlockPos pOffset, BlockPos pPos, StructurePlaceSettings pSettings, RandomSource pRandom, int pFlags, CallbackInfoReturnable<Boolean> cir) {
        if(pSettings.getProcessors().stream().anyMatch(WaterLoggingFixProcessor.class::isInstance)) {
            pSettings.setKeepLiquids(false);
        }
    }
}
