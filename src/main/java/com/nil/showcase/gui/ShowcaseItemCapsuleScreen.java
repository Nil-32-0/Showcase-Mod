package com.nil.showcase.gui;

import com.nil.showcase.ShowcaseMod;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ShowcaseItemCapsuleScreen extends AbstractContainerScreen<ShowcaseItemCapsuleMenu> {

    private static final ResourceLocation BACKGROUND_LOCATION = ResourceLocation.fromNamespaceAndPath(ShowcaseMod.MODID, "textures/gui/container/item_capsule.png");

    public ShowcaseItemCapsuleScreen(ShowcaseItemCapsuleMenu menu, Inventory playerInv, Component title) {
        super(menu, playerInv, title);

        this.titleLabelX = 8;
        this.inventoryLabelX = 8;
        this.inventoryLabelY -= 17;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTick);

        this.renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        graphics.blit(BACKGROUND_LOCATION, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }
}
