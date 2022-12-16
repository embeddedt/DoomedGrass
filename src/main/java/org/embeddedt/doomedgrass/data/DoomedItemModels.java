package org.embeddedt.doomedgrass.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.embeddedt.doomedgrass.DoomedGrass;
import org.embeddedt.doomedgrass.registry.Registration;

public class DoomedItemModels extends ItemModelProvider {

    public DoomedItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, DoomedGrass.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(Registration.DOOMED_GRASS_ITEM.getId().getPath(), modLoc("block/doomed_grass"));
        singleTexture(Registration.DOOMED_FRAGMENT.getId().getPath(), new ResourceLocation("item/generated"), "layer0", modLoc("item/doomed_fragment"));
    }
}
