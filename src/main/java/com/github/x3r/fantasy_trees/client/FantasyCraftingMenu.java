package com.github.x3r.fantasy_trees.client;

import com.github.x3r.fantasy_trees.common.blocks.FantasyCraftingTableBlock;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;

public class FantasyCraftingMenu extends CraftingMenu {

    private final ContainerLevelAccess access;

    public FantasyCraftingMenu(int pContainerId, Inventory pPlayerInventory, ContainerLevelAccess pAccess) {
        super(pContainerId, pPlayerInventory, pAccess);
        this.access = pAccess;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return FantasyCraftingMenu.stillValid(access, pPlayer);
    }

    protected static boolean stillValid(ContainerLevelAccess pAccess, Player pPlayer) {
        return pAccess.evaluate((p_38916_, p_38917_) -> p_38916_.getBlockState(p_38917_).getBlock() instanceof FantasyCraftingTableBlock && pPlayer.distanceToSqr((double) p_38917_.getX() + 0.5D, (double) p_38917_.getY() + 0.5D, (double) p_38917_.getZ() + 0.5D) <= 64.0D, true);
    }
}
