package com.github.x3r.fantasy_trees.common.data;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.github.x3r.fantasy_trees.common.datagen.FantasyBlockStateProvider;
import com.github.x3r.fantasy_trees.registry.BiomeRegistry;
import com.github.x3r.fantasy_trees.registry.ConfiguredFeatureRegistry;
import com.github.x3r.fantasy_trees.registry.PlacedFeatureRegistry;
import com.github.x3r.fantasy_trees.registry.StructureRegistry;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = FantasyTrees.MOD_ID)
public class DataGenerationHandler
{
    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ConfiguredFeatureRegistry::bootstrap)
            .add(Registries.PLACED_FEATURE, PlacedFeatureRegistry::bootstrap)
            .add(Registries.TEMPLATE_POOL, StructureRegistry::bootstrapStructurePools)
            .add(Registries.STRUCTURE, StructureRegistry::bootstrap)
            .add(Registries.STRUCTURE_SET, StructureRegistry::bootstrapStructureSets)
            .add(Registries.BIOME, BiomeRegistry::bootstrapBiomes);


    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        FantasyBlockTagProvider tagProvider = new FantasyBlockTagProvider(output, event.getLookupProvider(), existingFileHelper);
        generator.addProvider(event.includeServer(), tagProvider);
        generator.addProvider(event.includeServer(), new FantasyItemTagProvider(output, event.getLookupProvider(), tagProvider.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(output, event.getLookupProvider(), BUILDER, Set.of(FantasyTrees.MOD_ID)));
        generator.addProvider(event.includeServer(), new FantasyBlockStateProvider(output, existingFileHelper));
        generator.addProvider(event.includeServer(), new FantasyItemModelProvider(output, existingFileHelper));
        generator.addProvider(event.includeServer(), new FantasyLanguageProvider(output));
        generator.addProvider(event.includeServer(), new FantasyLootTableProvider(output));
        generator.addProvider(event.includeServer(), new FantasyRecipeProvider(output));
    }
}