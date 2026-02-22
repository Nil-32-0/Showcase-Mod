package com.nil.showcase.blocks;

import com.nil.showcase.ShowcaseMod;
import com.nil.showcase.blocks.entity.ShowcaseSimpleChestBlockEntity;
import com.nil.showcase.gui.ShowcaseSimpleChestMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.network.NetworkHooks;

public class ShowcaseSimpleChestBlock extends Block implements EntityBlock {

    public ShowcaseSimpleChestBlock(Block.Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ShowcaseSimpleChestBlockEntity(pos, state);
    }

    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new MenuProvider() {
            @Override
            public AbstractContainerMenu createMenu(int containerId, Inventory playerInv, Player player) {
                var v = ((ShowcaseSimpleChestBlockEntity) level.getBlockEntity(pos)).getCapability(ForgeCapabilities.ITEM_HANDLER);
                return new ShowcaseSimpleChestMenu(containerId, playerInv, ContainerLevelAccess.create(level, pos), 
                    v.orElseThrow(null)
                );
            }

            @Override
            public Component getDisplayName() {
                return Component.translatable("menu.title." + ShowcaseMod.MODID + "." + ShowcaseSimpleChestMenu.NAME);
            }
        };
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
            InteractionHand hand, BlockHitResult result) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(serverPlayer, state.getMenuProvider(level, pos));
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
