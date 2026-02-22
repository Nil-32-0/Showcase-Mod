package com.nil.showcase.datagen;

import com.nil.showcase.ShowcaseMod;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ShowcaseItemModelProvider extends ItemModelProvider {

	private static final String BLOCK_PREFIX = "block/";

    public ShowcaseItemModelProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, ShowcaseMod.MODID, fileHelper);
    }

    @Override
    protected void registerModels() {
        for (var entry : ForgeRegistries.ITEMS.getEntries()) {
            Item item = entry.getValue();
            ResourceLocation key = entry.getKey().location();

            if (ShowcaseMod.MODID.equals(key.getNamespace()) && !exists(key)) {
                String name = key.getPath();

                if (item instanceof BlockItem) {
                    withExistingParent(name, ResourceLocation.fromNamespaceAndPath(ShowcaseMod.MODID, BLOCK_PREFIX + name));
                } else {
                    basicItem(key);
                }
            }
        }
    }

    private boolean exists(ResourceLocation key) {
		return existingFileHelper.exists(key, PackType.CLIENT_RESOURCES, ".json", "models/item");
    }
}
