package com.nil.showcase;

import com.mojang.logging.LogUtils;
import com.nil.showcase.blocks.ShowcaseBlocks;
import com.nil.showcase.blocks.entity.ShowcaseBlockEntities;
import com.nil.showcase.gui.ShowcaseGuis;
import com.nil.showcase.gui.ShowcaseItemCapsuleScreen;
import com.nil.showcase.gui.ShowcaseSimpleChestScreen;
import com.nil.showcase.items.ShowcaseCreativeTab;
import com.nil.showcase.items.ShowcaseItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ShowcaseMod.MODID)
public class ShowcaseMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "showcase";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public ShowcaseMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        ShowcaseBlocks.register(modEventBus);
        ShowcaseItems.register(modEventBus);
        ShowcaseCreativeTab.register(modEventBus);
        ShowcaseBlockEntities.register(modEventBus);
        ShowcaseGuis.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register to an existing creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, ShowcaseConfig.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (ShowcaseConfig.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(ShowcaseConfig.magicNumberIntroduction + ShowcaseConfig.magicNumber);

        ShowcaseConfig.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(ShowcaseItems.COMPRESSED_DIRT);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            event.enqueueWork(() -> MenuScreens.register(ShowcaseGuis.SIMPLE_CHEST.get(), ShowcaseSimpleChestScreen::new));
            event.enqueueWork(() -> MenuScreens.register(ShowcaseGuis.ITEM_CAPSULE.get(), ShowcaseItemCapsuleScreen::new));
        }
    }
}
