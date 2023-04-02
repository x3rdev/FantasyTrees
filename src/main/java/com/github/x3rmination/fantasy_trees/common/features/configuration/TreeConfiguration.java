package com.github.x3rmination.fantasy_trees.common.features.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.List;

public class TreeConfiguration implements FeatureConfiguration {

    public static final Codec<TreeConfiguration> CODEC = RecordCodecBuilder.create((codec) -> {
        return codec.group(ResourceLocation.CODEC.listOf().fieldOf("trees").forGetter(o -> {
            return o.trees;
        })).apply(codec, TreeConfiguration::new);
    });
    public final List<ResourceLocation> trees;
    public TreeConfiguration(List<ResourceLocation> trees) {
        this.trees = trees;
    }
}
