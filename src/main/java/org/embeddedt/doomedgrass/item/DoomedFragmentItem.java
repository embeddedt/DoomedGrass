package org.embeddedt.doomedgrass.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.embeddedt.doomedgrass.registry.Registration;

import net.minecraft.world.item.Item.Properties;

public class DoomedFragmentItem extends Item {
    public DoomedFragmentItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState theState = level.getBlockState(blockpos);
        if(theState.is(Registration.DOOMABLE_BLOCK)) {
            if(level instanceof ServerLevel) {
                level.setBlock(blockpos, Registration.DOOMED_GRASS.get().defaultBlockState(), 3);
            }
            pContext.getItemInHand().shrink(1);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }
}
