package com.github.x3rmination.fantasy_trees.common.features.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.List;

public class FSpruceMediumConfiguration implements FeatureConfiguration {

    public static final Codec<FSpruceMediumConfiguration> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(ResourceLocation.CODEC.listOf().fieldOf("trees").forGetter(o -> {
            return o.trees;
        })).apply(instance, FSpruceMediumConfiguration::new);
    });
    public final List<ResourceLocation> trees;
    public FSpruceMediumConfiguration(List<ResourceLocation> trees) {
        this.trees = trees;
    }
}
