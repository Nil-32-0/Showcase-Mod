package com.nil.showcase.datagen;

import com.nil.showcase.ShowcaseMod;
import com.nil.showcase.datagen.recipe.ShowcaseRecipeProvider;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ShowcaseMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ShowcaseDataGenerators {

    private ShowcaseDataGenerators() {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        PackOutput output = generator.getPackOutput();

        boolean includeClient = event.includeClient();
        boolean includeServer = event.includeServer();

        generator.addProvider(includeClient, new ShowcaseBlockStateProvider(output, fileHelper));
        generator.addProvider(includeClient, new ShowcaseItemModelProvider(output, fileHelper));
        generator.addProvider(includeServer, new ShowcaseRecipeProvider(output));
    }
}
