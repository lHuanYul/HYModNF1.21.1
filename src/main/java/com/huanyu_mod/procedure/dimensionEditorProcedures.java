package com.huanyu_mod.procedure;

import com.huanyu_mod.HuanYuMod;
import com.huanyu_mod.world.inventory.dimensionEditorInterfaceInv;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.NotNull;

public class dimensionEditorProcedures {
	public static InteractionResult openGui(LevelAccessor levelAccessor, double x, double y, double z, Player player) {
		try {
			if (player == null) return InteractionResult.FAIL;
			if (player instanceof ServerPlayer _player) {
				BlockPos blockPos = BlockPos.containing(x, y, z);
				_player.openMenu(new MenuProvider() {
					@Override
					public @NotNull Component getDisplayName() {
						return Component.literal("dimension_editorGUI");
					}
					@Override
					public boolean shouldTriggerClientSideContainerClosingOnOpen() {
						return false;
					}
					@Override
					public AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player _player) {
						return new dimensionEditorInterfaceInv(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(blockPos));
					}
				}, blockPos);
                return InteractionResult.SUCCESS;
			} else {
                return InteractionResult.FAIL;
            }
		} catch (Exception e) {
			HuanYuMod.LOGGER.error("An error occurred at ", e);
			return InteractionResult.FAIL;
		}
	}
}
