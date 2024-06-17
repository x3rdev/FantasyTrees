package com.github.x3r.fantasy_trees.mixins;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Map;

@Mixin(AxeItem.class)
public interface AxeItemAccess {

    @Invoker("<init>")
    static AxeItem create(Tier tier, float f, float g, Item.Properties properties) {
        throw new Error("Mixin did not apply");
    }

    @Accessor("STRIPPABLES")
    static Map<Block, Block> getStrippables() {
        throw new Error("Mixin did not apply");
    }

    @Accessor("STRIPPABLES")
    @Mutable
    static void setStrippables(Map<Block, Block> newMap) {
        throw new Error("Mixin did not apply");
    }
}
