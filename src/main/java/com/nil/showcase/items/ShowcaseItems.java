package com.nil.showcase.items;

import com.nil.showcase.ShowcaseMod;
import com.nil.showcase.blocks.ShowcaseBlocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ShowcaseItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, ShowcaseMod.MODID);

    public static final RegistryObject<Item> DIRT_STICK = registerDefault("dirt_stick");
    public static final RegistryObject<Item> COMPRESSED_DIRT = registerBlockDefault(ShowcaseBlocks.COMPRESSED_DIRT);
    public static final RegistryObject<Item> SIMPLE_CHEST = registerBlockDefault(ShowcaseBlocks.SIMPLE_CHEST);

    private static RegistryObject<Item> registerDefault(String itemId) {
        return REGISTER.register(itemId, () -> new Item(new Item.Properties()));
    }

    private static RegistryObject<Item> registerBlockDefault(RegistryObject<Block> block) {
        ShowcaseMod.LOGGER.debug("Registering item for block " + block.getKey() + " with id " + block.getKey().location().getPath());
        return REGISTER.register(block.getKey().location().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
