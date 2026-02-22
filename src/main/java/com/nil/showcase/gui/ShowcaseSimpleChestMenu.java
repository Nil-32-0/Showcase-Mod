package com.nil.showcase.gui;
import com.nil.showcase.blocks.ShowcaseBlocks;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ShowcaseSimpleChestMenu extends AbstractContainerMenu {
    public static String NAME = "simple_chest";

    private ContainerLevelAccess access;

    public ShowcaseSimpleChestMenu(int containerId, Inventory playerInv) {
        this(containerId, playerInv, ContainerLevelAccess.NULL, new ItemStackHandler(18));
    }

    public ShowcaseSimpleChestMenu(int containerId, Inventory playerInv, ContainerLevelAccess access, IItemHandler dataInventory) {
        super(ShowcaseGuis.SIMPLE_CHEST.get(), containerId);
        this.access = access;

        for (int i = 0; i < dataInventory.getSlots(); i++) {
            this.addSlot(new SlotItemHandler(dataInventory, i, 8 + 18 * (i % 9), 18 + 18 * (i / 9)));
        }

        for (int playerInvRow = 0; playerInvRow < 3; playerInvRow++) {
            for (int playerInvCol = 0; playerInvCol < 9; playerInvCol++) {
                this.addSlot(new Slot(playerInv, playerInvCol + playerInvRow * 9 + 9, 8 + playerInvCol * 18, 66 + playerInvRow * 18));
            }
        }

        for (int hotBarSlot = 0; hotBarSlot < 9; hotBarSlot++) {
            this.addSlot(new Slot(playerInv, hotBarSlot, 8 + hotBarSlot * 18, 124));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return AbstractContainerMenu.stillValid(access, player, ShowcaseBlocks.SIMPLE_CHEST.get());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int quickMovedSlotIndex) {
        ItemStack quickMovedStack = ItemStack.EMPTY;
        Slot quickMovedSlot = this.slots.get(quickMovedSlotIndex);

        if (quickMovedSlot != null && quickMovedSlot.hasItem()) {
            ItemStack rawStack = quickMovedSlot.getItem();
            quickMovedStack = rawStack.copy();

            if (quickMovedSlotIndex < 18) { // In chest
                if (!this.moveItemStackTo(rawStack, 18, 54, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.moveItemStackTo(rawStack, 0, 18, false)) {
                    if (quickMovedSlotIndex < 45) {
                        if (!this.moveItemStackTo(rawStack, 45, 54, false)) {
                            return ItemStack.EMPTY;
                        } 
                    } else if (!this.moveItemStackTo(rawStack, 18, 45, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (rawStack.isEmpty()) {
                quickMovedSlot.set(ItemStack.EMPTY);
            } else {
                quickMovedSlot.setChanged();
            }
        }
        return quickMovedStack;
    }
}
