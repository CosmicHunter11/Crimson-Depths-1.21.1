package net.cosmic.cdepth.block.custom;

import net.cosmic.cdepth.particle.ModParticles;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

public class CrimsoniteLeavesBlock extends LeavesBlock {
    private int ticksSinceLastSpawn = 0;
    private static final int ticksPerParticle = 8;

    public CrimsoniteLeavesBlock(Settings settings) {super(settings);}

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        ticksSinceLastSpawn++; if (ticksSinceLastSpawn >= ticksPerParticle) {
            this.spawnCrimsonParticles(world, pos, state); ticksSinceLastSpawn = 0;}
        super.randomDisplayTick(state, world, pos, random);
    }

    private void spawnCrimsonParticles(World world, BlockPos pos, BlockState state) {
        if (state.getFluidState().isEmpty() && !(world.random.nextFloat() < 0.3F)) {
            VoxelShape voxelShape = state.getCollisionShape(world, pos);
            double d = voxelShape.getMax(Direction.Axis.Y);
            if (d >= 1.0 && !state.isIn(BlockTags.IMPERMEABLE)) {
                double e = voxelShape.getMin(Direction.Axis.Y);
                if (e > 0.0) {
                    this.addCrimsonParticle(world, pos, voxelShape, (double)pos.getY() + e - 0.05);
                } else {
                    BlockPos blockPos = pos.down();
                    BlockState blockState = world.getBlockState(blockPos);
                    VoxelShape voxelShape2 = blockState.getCollisionShape(world, blockPos);
                    double f = voxelShape2.getMax(Direction.Axis.Y);
                    if ((f < 1.0 || !blockState.isFullCube(world, blockPos)) && blockState.getFluidState().isEmpty()) {
                        this.addCrimsonParticle(world, pos, voxelShape, (double)pos.getY() - 0.05);
                    }
                }
            }

        }
    }

    private void addCrimsonParticle(World world, BlockPos pos, VoxelShape shape, double height) {
        this.addCrimsonParticle(world, (double)pos.getX() + shape.getMin(Direction.Axis.X), (double)pos.getX() + shape.getMax(Direction.Axis.X), (double)pos.getZ() + shape.getMin(Direction.Axis.Z), (double)pos.getZ() + shape.getMax(Direction.Axis.Z), height);
    }

    private void addCrimsonParticle(World world, double minX, double maxX, double minZ, double maxZ, double height) {
        world.addParticle(ModParticles.CRIMSONITE_LEAVES, MathHelper.lerp(world.random.nextDouble(), minX, maxX), height, MathHelper.lerp(world.random.nextDouble(), minZ, maxZ), 0.0, 0.0, 0.0);
    }
}
