package com.github.x3rmination.fantasy_trees.common.datagen;

import com.github.x3rmination.fantasy_trees.FantasyTrees;
import com.github.x3rmination.fantasy_trees.registry.BlockItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.Arrays;
import java.util.Scanner;

public class FantasyLanguageProvider extends LanguageProvider {

    public FantasyLanguageProvider(DataGenerator gen) {
        super(gen, FantasyTrees.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        BlockItemRegistry.BLOCK_ITEMS.getEntries().forEach(entry -> {
            String name = entry.get().getRegistryName().getPath();
            String[] split = name.split("_");
            StringBuilder localized = new StringBuilder();
            for (String s : split) {
                s = s.substring(0, 1).toUpperCase() + s.substring(1);
                localized.append(s).append(" ");
            }
            this.add(entry.get(), localized.toString().strip());
        });
    }
}
