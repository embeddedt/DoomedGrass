package org.embeddedt.doomedgrass.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.ForgeEventFactory;
import org.embeddedt.doomedgrass.registry.Registration;

import java.util.*;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class DoomedGrassBlock extends Block {
    public static final IntegerProperty DECAY = IntegerProperty.create("decay", 0, 15);
    public DoomedGrassBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.getStateDefinition().any().setValue(DECAY, 0));

    }

    private boolean isStateEmpty(BlockState state) {
        return !state.getMaterial().blocksMotion();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(DECAY);
    }

    private void tryToSpreadNearby(Level level, BlockState selfState, BlockPos selfPos, RandomSource random) {
        List<BlockPos> targets = new ArrayList<>();
        for(int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (x != 0 || y != 0 || z != 0)
                        targets.add(new BlockPos(selfPos.offset(x, y, z)));
                }
            }
        }
        Collections.shuffle(targets);
        for(BlockPos thePos : targets) {
            if(!level.isLoaded(thePos))
                continue;
            BlockState state = level.getBlockState(thePos);
            if(state.is(Registration.DOOMABLE_BLOCK) && isStateEmpty(level.getBlockState(thePos.above()))) {
                level.setBlock(thePos, selfState.setValue(DECAY, selfState.getValue(DECAY) + 1), 3);
                level.scheduleTick(thePos, this, 1 + random.nextInt(8));
                level.scheduleTick(selfPos, this, 1 + random.nextInt(8));
                return;
            }
        }
    }

    private Optional<MobSpawnSettings.SpawnerData> findMob(ServerLevel worldServer, BlockPos pos, RandomSource rand) {
        return NaturalSpawner.getRandomSpawnMobAt(worldServer, worldServer.structureManager(), worldServer.getChunkSource().getGenerator(), MobCategory.MONSTER, rand, pos);
    }

    @Override
    public boolean isFireSource(BlockState state, LevelReader level, BlockPos pos, Direction direction) {
        return direction == Direction.UP;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        if(state.getValue(DECAY) < 15) {
            tryToSpreadNearby(level, state, pos, rand);
        }

        BlockPos spawnPos = pos.above();
        if(level.getMaxLocalRawBrightness(spawnPos) >= 9 || !isStateEmpty(level.getBlockState(spawnPos)))
            return;

        AABB nearbyEntityBox = new AABB(pos).inflate(7, 4, 7);
        if(level.getEntitiesOfClass(LivingEntity.class, nearbyEntityBox, e -> e instanceof Monster).size() < 8) {
            findMob(level, spawnPos, rand).ifPresent(spawnData -> {
                var entity = spawnData.type.create(level);
                if(!(entity instanceof Mob mob)) {
                    return;
                }
                mob.moveTo(spawnPos, 0, 0);
                if(!ForgeEventFactory.doSpecialSpawn(mob, level, (float)mob.getX(), (float)mob.getY(), (float)mob.getZ(), null, MobSpawnType.NATURAL)) {
                    mob.finalizeSpawn(level, level.getCurrentDifficultyAt(spawnPos), MobSpawnType.NATURAL, null, null);
                    level.addFreshEntity(mob);
                }
            });
        }
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if(!pLevel.isClientSide && !pIsMoving) {
            pLevel.scheduleTick(pPos, this, 1 + pLevel.getRandom().nextInt(8));
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if(level.isClientSide) {
            int numParticles = random.nextInt(4) + 4;
            for(int i = 0; i < numParticles; i++) {
                level.addParticle(ParticleTypes.SMOKE, pos.getX() + random.nextDouble(), pos.getY() + 1, pos.getZ() + random.nextDouble(), 0, 0, 0);
            }
        }
    }
}
