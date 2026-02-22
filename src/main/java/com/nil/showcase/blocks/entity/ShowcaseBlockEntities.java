package com.nil.showcase.blocks.entity;

import com.nil.showcase.ShowcaseMod;
import com.nil.showcase.blocks.ShowcaseBlocks;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ShowcaseBlockEntities {
    public static DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ShowcaseMod.MODID);

    public static RegistryObject<BlockEntityType<ShowcaseSimpleChestBlockEntity>> SIMPLE_CHEST = REGISTER.register(
        ShowcaseSimpleChestBlockEntity.NAME, () -> BlockEntityType.Builder.of(ShowcaseSimpleChestBlockEntity::new, ShowcaseBlocks.SIMPLE_CHEST.get()).build(null)
    );

    public static void register(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
