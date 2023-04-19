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

public class FantasyOakRegion extends Region {

    public FantasyOakRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(Biomes.FOREST, BiomeRegistry.FANTASY_FOREST);

            List<Climate.ParameterPoint> fantasyForestPoints = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(ParameterUtils.Temperature.COOL)
                    .humidity(ParameterUtils.Humidity.DRY, ParameterUtils.Humidity.NEUTRAL)
                    .continentalness(ParameterUtils.Continentalness.span(ParameterUtils.Continentalness.NEAR_INLAND, ParameterUtils.Continentalness.INLAND))
                    .erosion(ParameterUtils.Erosion.span(ParameterUtils.Erosion.EROSION_0, ParameterUtils.Erosion.EROSION_2))
                    .depth(ParameterUtils.Depth.SURFACE)
                    .weirdness(ParameterUtils.Weirdness.span(ParameterUtils.Weirdness.LOW_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.LOW_SLICE_VARIANT_ASCENDING))
                    .build();

            fantasyForestPoints.forEach(parameterPoint -> builder.replaceBiome(parameterPoint, BiomeRegistry.FANTASY_FOREST));
        });
    }
}
