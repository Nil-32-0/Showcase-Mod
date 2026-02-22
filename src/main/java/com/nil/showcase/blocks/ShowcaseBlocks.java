package com.nil.showcase.blocks;

import com.nil.showcase.ShowcaseMod;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ShowcaseBlocks {
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, ShowcaseMod.MODID);

    public static final RegistryObject<Block> COMPRESSED_DIRT = registerDefault("compressed_dirt", MapColor.DIRT);
    public static final RegistryObject<Block> SIMPLE_CHEST = REGISTER.register("simple_chest", () -> 
        new ShowcaseSimpleChestBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIAMOND)));

    private static RegistryObject<Block> registerDefault(String blockId, MapColor color) {
        return REGISTER.register(blockId, () -> new Block(BlockBehaviour.Properties.of().mapColor(color)));
    }

    public static void register(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
