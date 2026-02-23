package com.nil.showcase.gui;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ShowcaseItemCapsuleMenu extends AbstractContainerMenu {
    public static String NAME = "item_capsule";

    public ShowcaseItemCapsuleMenu(int containerId, Inventory playerInv) {
        this(containerId, playerInv, new ItemStackHandler(2));
    }

    public ShowcaseItemCapsuleMenu(int containerId, Inventory playerInv, IItemHandler dataInventory) {
        super(ShowcaseGuis.ITEM_CAPSULE.get(), containerId);

        int startCol = 8;
        int centerCol = startCol + 4 * 18;

        for (int i = 0; i < dataInventory.getSlots(); i++) {
            this.addSlot(new SlotItemHandler(dataInventory, i, centerCol, 18 + 18 * i));
        }

        for (int playerInvRow = 0; playerInvRow < 3; playerInvRow++) {
            for (int playerInvCol = 0; playerInvCol < 9; playerInvCol++) {
                this.addSlot(new Slot(playerInv, playerInvCol + playerInvRow * 9 + 9, startCol + playerInvCol * 18, 66 + playerInvRow * 18));
            }
        }

        for (int hotBarSlot = 0; hotBarSlot < 9; hotBarSlot++) {
            this.addSlot(new Slot(playerInv, hotBarSlot, startCol + hotBarSlot * 18, 124));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int quickMovedSlotIndex) {
                ItemStack quickMovedStack = ItemStack.EMPTY;
        Slot quickMovedSlot = this.slots.get(quickMovedSlotIndex);

        if (quickMovedSlot != null && quickMovedSlot.hasItem()) {
            ItemStack rawStack = quickMovedSlot.getItem();
            quickMovedStack = rawStack.copy();

            if (quickMovedSlotIndex < 2) { // In capsule
                if (!this.moveItemStackTo(rawStack, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.moveItemStackTo(rawStack, 0, 2, false)) {
                    if (quickMovedSlotIndex < 29) {
                        if (!this.moveItemStackTo(rawStack, 29, 38, false)) {
                            return ItemStack.EMPTY;
                        } 
                    } else if (!this.moveItemStackTo(rawStack, 2, 27, false)) {
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
