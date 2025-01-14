package com.huanyu_mod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class debug_block00 extends Block {
    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    public debug_block00() {
        super(BlockBehaviour.Properties
                .of()
                .sound(SoundType.AMETHYST)
                .strength(1f, 1200f)
                .lightLevel(s -> 15)
                .noOcclusion()
        );
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
    }
    public static Item.Properties itemProperties() {
        return new Item.Properties()
                .rarity(Rarity.EPIC);
    }
    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> box(0, 0, 4, 16, 16, 16);
            case SOUTH -> box(0, 0, 0, 16, 16, 12);
            case EAST -> box(0, 0, 0, 12, 16, 16);
            case WEST -> box(4, 0, 0, 16, 16, 16);
            case UP -> box(0, 0, 0, 16, 12, 16);
            case DOWN -> box(0, 4, 0, 16, 16, 16);
        };
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }
    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return Objects.requireNonNull(super.getStateForPlacement(context)).setValue(FACING, context.getClickedFace());
    }
    public @NotNull BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }
    public @NotNull BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }
}
