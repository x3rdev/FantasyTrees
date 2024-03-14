package com.github.x3r.fantasy_trees.registry;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class BiomeRegistry {
    protected static final List<ResourceKey<Biome>> BIOMES = Lists.newArrayList();
    public static final ResourceKey<Biome> FANTASY_TAIGA = registerBiome("fantasy_taiga");
    public static final ResourceKey<Biome> FANTASY_FOREST = registerBiome("fantasy_forest");
    public static final ResourceKey<Biome> FANTASY_BIRCH_FOREST = registerBiome("fantasy_birch_forest");
    public static final ResourceKey<Biome> FANTASY_DARK_FOREST = registerBiome("fantasy_dark_forest");
    public static final ResourceKey<Biome> FANTASY_JUNGLE = registerBiome("fantasy_jungle");
    public static final ResourceKey<Biome> FANTASY_SAVANNA = registerBiome("fantasy_savanna");


    public static void bootstrapBiomes(BootstapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatureGetter = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carverGetter = context.lookup(Registries.CONFIGURED_CARVER);
        register(context, FANTASY_TAIGA, BiomeRegistry.buildFantasyTaiga(placedFeatureGetter, carverGetter));
        register(context, FANTASY_FOREST, BiomeRegistry.buildFantasyForest(placedFeatureGetter, carverGetter));
        register(context, FANTASY_BIRCH_FOREST, BiomeRegistry.buildFantasyBirchForest(placedFeatureGetter, carverGetter));
        register(context, FANTASY_DARK_FOREST, BiomeRegistry.buildFantasyDarkForest(placedFeatureGetter, carverGetter));
        register(context, FANTASY_JUNGLE, BiomeRegistry.buildFantasyJungle(placedFeatureGetter, carverGetter));
        register(context, FANTASY_SAVANNA, BiomeRegistry.buildFantasySavanna(placedFeatureGetter, carverGetter));
    }

    private static void register(BootstapContext<Biome> context, ResourceKey<Biome> key, Biome biome) {
        context.register(key, biome);
    }

    private static ResourceKey<Biome> registerBiome(String name) {
        ResourceKey<Biome> key = ResourceKey.create(Registries.BIOME, new ResourceLocation(FantasyTrees.MOD_ID, name));
        BIOMES.add(key);
        return key;
    }

    public static List<ResourceKey<Biome>> getBiomes()
    {
        return ImmutableList.copyOf(BIOMES);
    }

    public static Biome buildFantasyTaiga(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        float temp = 0.25F;
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(mobspawnsettings$builder);
        BiomeDefaultFeatures.commonSpawns(mobspawnsettings$builder);
        globalOverworldGeneration(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_OLD_GROWTH_SPRUCE_TAIGA);
//        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_OLD_GROWTH_PINE_TAIGA);
        BiomeDefaultFeatures.addDefaultFlowers(genBuilder);
        BiomeDefaultFeatures.addTaigaGrass(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatureRegistry.FANTASY_FLOWERS_CHECKED);
        return (new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(temp)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .downfall(0.8F)
                .specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(temp)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
                .mobSpawnSettings(mobspawnsettings$builder.build())
                .generationSettings(genBuilder.build())
                .build());
    }

    public static Biome buildFantasyForest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        float temp = 0.6F;
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(mobspawnsettings$builder);
        BiomeDefaultFeatures.commonSpawns(mobspawnsettings$builder);
        globalOverworldGeneration(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatureRegistry.FANTASY_FOREST_GRASS_CHECKED);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatureRegistry.FANTASY_VANILLA_FLOWERS_CHECKED);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatureRegistry.FANTASY_FLOWERS_CHECKED);
        return (new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(temp)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .downfall(0.6F)
                .specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(temp)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
                .mobSpawnSettings(mobspawnsettings$builder.build())
                .generationSettings(genBuilder.build())
                .build());
    }

    public static Biome buildFantasyBirchForest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        float temp = 0.6F;
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(mobspawnsettings$builder);
        BiomeDefaultFeatures.commonSpawns(mobspawnsettings$builder);
        globalOverworldGeneration(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatureRegistry.FANTASY_FOREST_GRASS_CHECKED);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatureRegistry.FANTASY_VANILLA_FLOWERS_CHECKED);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatureRegistry.FANTASY_FLOWERS_CHECKED);
        return (new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(temp)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .downfall(0.6F)
                .specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(temp)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
                .mobSpawnSettings(mobspawnsettings$builder.build())
                .generationSettings(genBuilder.build())
                .build());
    }

    public static Biome buildFantasyDarkForest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        float temp = 0.7F;
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(mobspawnsettings$builder);
        BiomeDefaultFeatures.commonSpawns(mobspawnsettings$builder);
        globalOverworldGeneration(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.DARK_FOREST_VEGETATION);
        BiomeDefaultFeatures.addForestFlowers(genBuilder);
        BiomeDefaultFeatures.addDefaultFlowers(genBuilder);
        BiomeDefaultFeatures.addForestGrass(genBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatureRegistry.FANTASY_FOREST_GRASS_CHECKED);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatureRegistry.FANTASY_FLOWERS_CHECKED);
        return (new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(temp)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .downfall(0.8F)
                .specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(temp)).grassColorModifier(BiomeSpecialEffects.GrassColorModifier.DARK_FOREST).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
                .mobSpawnSettings(mobspawnsettings$builder.build())
                .generationSettings(genBuilder.build())
                .build());
    }

    public static Biome buildFantasyJungle(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        float temp = 0.9F;
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.baseJungleSpawns(mobspawnsettings$builder);
        globalOverworldGeneration(genBuilder);
        BiomeDefaultFeatures.addJungleTrees(genBuilder);
        BiomeDefaultFeatures.addWarmFlowers(genBuilder);
        BiomeDefaultFeatures.addJungleGrass(genBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(genBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(genBuilder);
        BiomeDefaultFeatures.addJungleVines(genBuilder);
        BiomeDefaultFeatures.addJungleMelons(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatureRegistry.FANTASY_VANILLA_FLOWERS_CHECKED);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatureRegistry.FANTASY_FLOWERS_CHECKED);
        return (new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(temp)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .downfall(0.8F)
                .specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(temp)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
                .mobSpawnSettings(mobspawnsettings$builder.build())
                .generationSettings(genBuilder.build())
                .build());
    }

    public static Biome buildFantasySavanna(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        float temp = 2.0F;
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        globalOverworldGeneration(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatureRegistry.FANTASY_FOREST_GRASS_CHECKED);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatureRegistry.FANTASY_FLOWERS_CHECKED);
        return (new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(temp)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .downfall(0.0F)
                .specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(temp)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
                .mobSpawnSettings(mobspawnsettings$builder.build())
                .generationSettings(genBuilder.build())
                .build());
    }

    private static int calculateSkyColor(float color) {
        float v = color / 3.0F;
        v = Mth.clamp(v, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - v * 0.05F, 0.5F + v * 0.1F, 1.0F);
    }


    private static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }
}
