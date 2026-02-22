package com.nil.showcase.gui;

import com.nil.showcase.ShowcaseMod;

import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ShowcaseGuis {
    public static DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ShowcaseMod.MODID);

    public static RegistryObject<MenuType<ShowcaseSimpleChestMenu>> SIMPLE_CHEST = REGISTER.register(ShowcaseSimpleChestMenu.NAME, () ->
        new MenuType<>(ShowcaseSimpleChestMenu::new, FeatureFlags.DEFAULT_FLAGS)
    );

    public static void register(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
