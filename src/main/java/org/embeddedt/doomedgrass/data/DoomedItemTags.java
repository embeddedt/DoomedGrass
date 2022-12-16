package org.embeddedt.doomedgrass.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.embeddedt.doomedgrass.DoomedGrass;

public class DoomedItemTags extends ItemTagsProvider {

    public DoomedItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(generator, blockTags, DoomedGrass.MODID, helper);
    }

    @Override
    protected void addTags() {
    }

    @Override
    public String getName() {
        return "Doomed Grass Tags";
    }
}
