package org.embeddedt.doomedgrass.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.embeddedt.doomedgrass.DoomedGrass;
import org.embeddedt.doomedgrass.registry.Registration;

public class DoomedBlockTags extends BlockTagsProvider {

    public DoomedBlockTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, DoomedGrass.MODID, helper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(Registration.DOOMED_GRASS.get());
        tag(Registration.DOOMABLE_BLOCK)
                .addTag(BlockTags.DIRT);
    }

    @Override
    public String getName() {
        return "Doomed Grass Block Tags";
    }
}
