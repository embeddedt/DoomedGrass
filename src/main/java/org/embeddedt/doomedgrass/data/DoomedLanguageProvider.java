package org.embeddedt.doomedgrass.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import org.embeddedt.doomedgrass.DoomedGrass;
import org.embeddedt.doomedgrass.registry.Registration;

public class DoomedLanguageProvider extends LanguageProvider {
    public DoomedLanguageProvider(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + DoomedGrass.MODID, "Doomed Grass");
        add(Registration.DOOMED_GRASS.get(), "Doomed Grass Block");
        add(Registration.DOOMED_FRAGMENT.get(), "Doomed Fragment");
        add("doomed_grass.description.doomed_grass_block", "Created by right-clicking with a Doomed Fragment on a dirt-like block. Spawns monsters at a heavily increased rate");
    }
}
