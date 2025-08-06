package net.cosmic.cdepth.block.custom;

import net.cosmic.cdepth.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FraudPlushieBlock extends Block {
    protected static final VoxelShape SHAPE;
    public static final DirectionProperty FACING;
    public static final BooleanProperty POWERED;
    public FraudPlushieBlock(Settings settings) {super(settings);}
    @Nullable @Override public BlockState getPlacementState(ItemPlacementContext ctx) {return this.getDefaultState().with(FACING,ctx.getHorizontalPlayerFacing().getOpposite());}
    @Override protected BlockState rotate(BlockState state, BlockRotation rotation) {return state.with(FACING, rotation.rotate(state.get(FACING)));}
    @Override protected BlockState mirror(BlockState state, BlockMirror mirror) {return state.rotate(mirror.getRotation(state.get(FACING)));}
    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {builder.add(FACING, POWERED);}
    @Override protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {return SHAPE;}
    @Override protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {world.playSound(null,pos, ModSounds.FRAUD_PLUSHIE_SQUEAK, SoundCategory.BLOCKS, 0.5F,1.0F);}
        return ActionResult.SUCCESS;
    }

    @Override protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        boolean bl = world.isReceivingRedstonePower(pos);
        if (bl != state.get(POWERED)) {
            if (bl) {world.playSound(null,pos, ModSounds.FRAUD_PLUSHIE_SQUEAK, SoundCategory.BLOCKS, 0.5F,1.0F);}
            world.setBlockState(pos, state.with(POWERED, bl), 3);
        }
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
        POWERED = Properties.POWERED;
        SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 14.0, 13.0);}
}
