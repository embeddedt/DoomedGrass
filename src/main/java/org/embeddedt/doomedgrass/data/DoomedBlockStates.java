package org.embeddedt.doomedgrass.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import org.embeddedt.doomedgrass.DoomedGrass;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.embeddedt.doomedgrass.registry.Registration;

public class DoomedBlockStates extends BlockStateProvider {

    public DoomedBlockStates(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, DoomedGrass.MODID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        ResourceLocation model = models().getBuilder(Registration.DOOMED_GRASS.getId().getPath())
                .parent(new ModelFile.UncheckedModelFile("block/grass_block"))
                .texture("overlay", "doomedgrass:block/doomed_grass_block_side_overlay")
                .texture("top", "doomedgrass:block/doomed_grass_block_top").getLocation();
        getVariantBuilder(Registration.DOOMED_GRASS.get()).forAllStates(
                state -> ConfiguredModel.builder().modelFile(new ModelFile.UncheckedModelFile(model)).build()
        );
    }
}
