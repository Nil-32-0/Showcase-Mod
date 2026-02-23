package com.nil.showcase.items;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.nil.showcase.ShowcaseMod;
import com.nil.showcase.gui.ShowcaseItemCapsuleMenu;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;

public class ShowcaseItemCapsule extends Item {

    public ShowcaseItemCapsule(Item.Properties properties) {
        super(properties);
    }
    

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ICapabilitySerializable<CompoundTag> () {
            private ItemStackHandler itemHandler = new ItemStackHandler(2);

            @Override
            public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                return ForgeCapabilities.ITEM_HANDLER.orEmpty(cap, LazyOptional.of(() -> itemHandler));
            }

            @Override
            public CompoundTag serializeNBT() {
                return itemHandler.serializeNBT();
            }

            @Override
            public void deserializeNBT(CompoundTag nbt) {
                this.itemHandler.deserializeNBT(nbt);
            }
        };
    }

    private MenuProvider getMenuProvider(LazyOptional<IItemHandler> dataInventoryLazyOptional) {
        return new MenuProvider() {
            @Override
            public AbstractContainerMenu createMenu(int containerId, Inventory playerInv, Player player) {
                return new ShowcaseItemCapsuleMenu(containerId, playerInv, dataInventoryLazyOptional.orElseThrow(null));
            }

            @Override
            public Component getDisplayName() {
                return Component.translatable("menu.title." + ShowcaseMod.MODID + "." + ShowcaseItemCapsuleMenu.NAME);
            }
        };
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            ItemStack stack = player.getItemInHand(hand);
            if (stack.is(this)) {
                NetworkHooks.openScreen(serverPlayer, getMenuProvider(stack.getCapability(ForgeCapabilities.ITEM_HANDLER)));
            }
        }
        return super.use(level, player, hand);
    }
}