package com.github.x3rmination.fantasy_trees.registry;

import com.github.x3rmination.fantasy_trees.FantasyTrees;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BiomeRegistry {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, FantasyTrees.MOD_ID);

    public static final RegistryObject<Biome> FANTASY_TAIGA_BIOME = BIOMES.register("fantasy_taiga", BiomeRegistry::buildFantasyTaiga);
    public static final RegistryObject<Biome> FANTASY_FOREST_BIOME = BIOMES.register("fantasy_forest", BiomeRegistry::buildFantasyForest);
    public static final RegistryObject<Biome> FANTASY_BIRCH_FOREST_BIOME = BIOMES.register("fantasy_birch_forest", BiomeRegistry::buildFantasyBirchForest);

    public static final RegistryObject<Biome> FANTASY_DARK_FOREST_BIOME = BIOMES.register("fantasy_dark_forest", BiomeRegistry::buildFantasyDarkForest);

    public static final ResourceKey<Biome> FANTASY_TAIGA = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_taiga"));
    public static final ResourceKey<Biome> FANTASY_FOREST = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_forest"));
    public static final ResourceKey<Biome> FANTASY_BIRCH_FOREST = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_birch_forest"));
    public static final ResourceKey<Biome> FANTASY_DARK_FOREST = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_dark_forest"));

    public static Biome buildFantasyTaiga() {
        float temp = 0.25F;
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(mobspawnsettings$builder);
        BiomeDefaultFeatures.commonSpawns(mobspawnsettings$builder);
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(genBuilder);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_OLD_GROWTH_SPRUCE_TAIGA);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_OLD_GROWTH_PINE_TAIGA);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        BiomeDefaultFeatures.addDefaultFlowers(genBuilder);
        BiomeDefaultFeatures.addTaigaGrass(genBuilder);
        return (new Biome.BiomeBuilder()
                .precipitation(Biome.Precipitation.RAIN)
                .biomeCategory(Biome.BiomeCategory.TAIGA)
                .temperature(temp)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .downfall(0.8F)
                .specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(temp)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
                .mobSpawnSettings(mobspawnsettings$builder.build())
                .generationSettings(genBuilder.build())
                .build());
    }

    public static Biome buildFantasyForest() {
        float temp = 0.6F;
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(mobspawnsettings$builder);
        BiomeDefaultFeatures.commonSpawns(mobspawnsettings$builder);
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        return (new Biome.BiomeBuilder()
                .precipitation(Biome.Precipitation.RAIN)
                .biomeCategory(Biome.BiomeCategory.FOREST)
                .temperature(temp)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .downfall(0.6F)
                .specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(temp)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
                .mobSpawnSettings(mobspawnsettings$builder.build())
                .generationSettings(genBuilder.build())
                .build());
    }

    public static Biome buildFantasyBirchForest() {
        float temp = 0.6F;
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(mobspawnsettings$builder);
        BiomeDefaultFeatures.commonSpawns(mobspawnsettings$builder);
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        return (new Biome.BiomeBuilder()
                .precipitation(Biome.Precipitation.RAIN)
                .biomeCategory(Biome.BiomeCategory.FOREST)
                .temperature(temp)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .downfall(0.6F)
                .specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(temp)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
                .mobSpawnSettings(mobspawnsettings$builder.build())
                .generationSettings(genBuilder.build())
                .build());
    }

    public static Biome buildFantasyDarkForest() {
        float temp = 0.7F;
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(mobspawnsettings$builder);
        BiomeDefaultFeatures.commonSpawns(mobspawnsettings$builder);
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(genBuilder);
        BiomeDefaultFeatures.addForestFlowers(genBuilder);
        BiomeDefaultFeatures.addDefaultFlowers(genBuilder);
        BiomeDefaultFeatures.addForestGrass(genBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(genBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(genBuilder);
        return (new Biome.BiomeBuilder()
                .precipitation(Biome.Precipitation.RAIN)
                .biomeCategory(Biome.BiomeCategory.FOREST)
                .temperature(temp)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .downfall(0.8F)
                .specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(temp)).grassColorModifier(BiomeSpecialEffects.GrassColorModifier.DARK_FOREST).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
                .mobSpawnSettings(mobspawnsettings$builder.build())
                .generationSettings(genBuilder.build())
                .build());
    }

    private static int calculateSkyColor(float color)
    {
        float v = color / 3.0F;
        v = Mth.clamp(v, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - v * 0.05F, 0.5F + v * 0.1F, 1.0F);
    }


    private static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {

        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
        BiomeDefaultFeatures.addFossilDecoration(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
    }
}
