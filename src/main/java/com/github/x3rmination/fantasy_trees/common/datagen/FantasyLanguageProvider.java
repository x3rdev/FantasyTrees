package com.github.x3rmination.fantasy_trees.common.datagen;

import com.github.x3rmination.fantasy_trees.FantasyTrees;
import com.github.x3rmination.fantasy_trees.registry.BlockItemRegistry;
import com.github.x3rmination.fantasy_trees.registry.ItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;

public class FantasyLanguageProvider extends LanguageProvider {

    public FantasyLanguageProvider(DataGenerator gen) {
        super(gen, FantasyTrees.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.fantasy_trees", "Fantasy Trees Item");
        BlockItemRegistry.BLOCK_ITEMS.getEntries().forEach(entry -> {
            addTranslation(entry.get());
        });
        ItemRegistry.ITEMS.getEntries().forEach(entry -> {
            addTranslation(entry.get());
        });
    }

    private void addTranslation(Item item) {
        String name = item.getRegistryName().getPath();
        String[] split = name.split("_");
        StringBuilder localized = new StringBuilder();
        for (String s : split) {
            s = s.substring(0, 1).toUpperCase() + s.substring(1);
            localized.append(s).append(" ");
        }
        this.add(item, localized.toString().strip());
    }
}
