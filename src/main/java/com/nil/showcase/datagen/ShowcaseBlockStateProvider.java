package com.nil.showcase.datagen;

import com.nil.showcase.ShowcaseMod;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ShowcaseBlockStateProvider extends BlockStateProvider {
	
    private final ExistingFileHelper existingFileHelper;
    
    public ShowcaseBlockStateProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, ShowcaseMod.MODID, fileHelper);
        this.existingFileHelper = fileHelper;
    }

    @Override
    protected void registerStatesAndModels() {
        for (var entry : ForgeRegistries.BLOCKS.getEntries()) {
            Block block = entry.getValue();
            ResourceLocation key = entry.getKey().location();

            if (ShowcaseMod.MODID.equals(key.getNamespace()) && !exists(key)) {
                String name = key.getPath();

                if (modelExists(key)) {
                    simpleBlock(block, models().getExistingFile(prefix(name)));
                } else {
                    simpleBlock(block);
                }
            }
        }
    }

    private boolean exists(ResourceLocation name) {
		return existingFileHelper.exists(name, PackType.CLIENT_RESOURCES, ".json", "blockstates");
    }

    private boolean modelExists(ResourceLocation name) {
		return existingFileHelper.exists(name, PackType.CLIENT_RESOURCES, ".json", "models/block");
    }

    private ResourceLocation prefix(String name) {
		return ResourceLocation.fromNamespaceAndPath(ShowcaseMod.MODID, "block/" + name);
    }
}
