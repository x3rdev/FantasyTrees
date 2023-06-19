package com.github.x3r.fantasy_trees.registry;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.github.x3r.fantasy_trees.common.blocks.*;
import com.github.x3r.fantasy_trees.common.blocks.grower.FantasyTreeGrower;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FantasyTrees.MOD_ID);

    public static final Material FANTASY_WOOD = new Material.Builder(MaterialColor.WOOD).build();

    public static final RegistryObject<Block> FANTASY_FLOWER = BLOCKS.register("fantasy_flower",
            () -> new FlowerBlock(() -> MobEffects.ABSORPTION, 20, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).lightLevel(value -> 15)));
    public static final Map<String, RegistryObject<Block>> WOODS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> LOGS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> STRIPPED_LOGS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> LEAVES = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> PLANKS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> STAIRS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> DOORS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> FENCES = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> FENCE_GATES = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> CRAFTING_TABLES = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> SAPLINGS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> STRIPPED_WOODS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> TRAPDOORS = new HashMap<>();

    protected static final WoodType[] WOOD_TYPES = {WoodType.OAK, WoodType.SPRUCE, WoodType.BIRCH, WoodType.ACACIA, WoodType.JUNGLE, WoodType.DARK_OAK};
    public static void registerFantasyBlocks() {
        Arrays.stream(WOOD_TYPES).forEach(woodType -> {
            String name = woodType.name();

            RegistryObject<Block> wood = BLOCKS.register(String.format("fantasy_%s_wood", name), FantasyLogBlock::new);
            WOODS.put(name, wood);
            RegistryObject<Block> log = BLOCKS.register(String.format("fantasy_%s_log", name), FantasyLogBlock::new);
            LOGS.put(name, log);
            RegistryObject<Block> stripped_wood = BLOCKS.register(String.format("fantasy_stripped_%s_wood", name), FantasyLogBlock::new);
            STRIPPED_WOODS.put(name, stripped_wood);
            RegistryObject<Block> stripped_log = BLOCKS.register(String.format("fantasy_stripped_%s_log", name), FantasyLogBlock::new);
            STRIPPED_LOGS.put(name, stripped_log);
            RegistryObject<Block> leaves = BLOCKS.register(String.format("fantasy_%s_leaves", name), FantasyLeavesBlock::new);
            LEAVES.put(name, leaves);
            RegistryObject<Block> planks = BLOCKS.register(String.format("fantasy_%s_planks", name), FantasyPlanksBlock::new);
            PLANKS.put(name, planks);
            RegistryObject<Block> stairs = BLOCKS.register(String.format("fantasy_%s_stairs", name), () -> new StairBlock(() -> planks.get().defaultBlockState(), BlockBehaviour.Properties.copy(planks.get()).lightLevel(value -> 7)));
            STAIRS.put(name, stairs);
            RegistryObject<Block> door = BLOCKS.register(String.format("fantasy_%s_door", name), FantasyDoorBlock::new);
            DOORS.put(name, door);
            RegistryObject<Block> trapdoor = BLOCKS.register(String.format("fantasy_%s_trapdoor", name), FantasyTrapDoorBlock::new);
            TRAPDOORS.put(name, trapdoor);
            RegistryObject<Block> fence = BLOCKS.register(String.format("fantasy_%s_fence", name), () -> new FenceBlock(BlockBehaviour.Properties.of(BlockRegistry.FANTASY_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).lightLevel(value -> 7)));
            FENCES.put(name, fence);
            RegistryObject<Block> fence_gate = BLOCKS.register(String.format("fantasy_%s_fence_gate", name), () -> new FenceGateBlock(BlockBehaviour.Properties.of(FANTASY_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).lightLevel(value -> 7)));
            FENCE_GATES.put(name, fence_gate);
            RegistryObject<Block> crafting_table = BLOCKS.register(String.format("fantasy_%s_crafting_table", name), () -> new FantasyCraftingTableBlock(BlockBehaviour.Properties.of(FANTASY_WOOD).strength(2.5F).sound(SoundType.WOOD).lightLevel(value -> 7)));
            CRAFTING_TABLES.put(name, crafting_table);
            RegistryObject<Block> sapling = BLOCKS.register(String.format("fantasy_%s_sapling", name), () -> new FantasySaplingBlock(new FantasyTreeGrower(name)));
            SAPLINGS.put(name, sapling);
        });
    }
}
