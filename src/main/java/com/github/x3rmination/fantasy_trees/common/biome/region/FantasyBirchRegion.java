package com.github.x3rmination.fantasy_trees.common.biome.region;

import com.github.x3rmination.fantasy_trees.registry.BiomeRegistry;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.List;
import java.util.function.Consumer;

public class FantasyBirchRegion extends Region {

    public FantasyBirchRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(Biomes.BIRCH_FOREST, BiomeRegistry.FANTASY_BIRCH_FOREST);

            List<Climate.ParameterPoint> fantasyForestPoints = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(Climate.Parameter.span(-0.3F, 0F))
                    .humidity(ParameterUtils.Humidity.DRY, ParameterUtils.Humidity.NEUTRAL)
                    .continentalness(Climate.Parameter.span(0.25F, 1.0F))
                    .erosion(ParameterUtils.Erosion.FULL_RANGE)
                    .depth(ParameterUtils.Depth.SURFACE)
                    .weirdness(Climate.Parameter.span(-0.35F, 0.35F))
                    .build();

            fantasyForestPoints.forEach(parameterPoint -> builder.replaceBiome(parameterPoint, BiomeRegistry.FANTASY_BIRCH_FOREST));
        });
    }
}
