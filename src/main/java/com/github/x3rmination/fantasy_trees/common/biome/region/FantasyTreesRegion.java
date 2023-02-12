package com.github.x3rmination.fantasy_trees.common.biome.region;

import com.github.x3rmination.fantasy_trees.registry.BiomeRegistry;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class FantasyTreesRegion extends Region {

    public FantasyTreesRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        Climate.ParameterPoint fantasy_taiga_point = new Climate.ParameterPoint(
                ParameterUtils.Temperature.COOL.parameter(),
                ParameterUtils.Humidity.NEUTRAL.parameter(),
                ParameterUtils.Continentalness.INLAND.parameter(),
                ParameterUtils.Erosion.EROSION_0.parameter(),
                ParameterUtils.Depth.FLOOR.parameter(),
                ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING.parameter(),
                0L
        );
        this.addBiome(mapper, fantasy_taiga_point, BiomeRegistry.FANTASY_TAIGA);
    }
}
