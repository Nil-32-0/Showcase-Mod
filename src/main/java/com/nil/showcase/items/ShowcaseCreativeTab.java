package com.nil.showcase.items;

import com.nil.showcase.ShowcaseMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ShowcaseCreativeTab {
    public static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ShowcaseMod.MODID);

    public static final RegistryObject<CreativeModeTab> DIRT_TAB = REGISTER.register("dirt_tab", () -> CreativeModeTab.builder()
        .icon(() -> ShowcaseItems.DIRT_STICK.get().getDefaultInstance())
        .displayItems((parameters, output) -> {
            output.accept(ShowcaseItems.DIRT_STICK.get());
            output.accept(ShowcaseItems.ITEM_CAPSULE.get());
            output.accept(ShowcaseItems.COMPRESSED_DIRT.get());
            output.accept(ShowcaseItems.SIMPLE_CHEST.get());
        }).build());


    public static void register(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
