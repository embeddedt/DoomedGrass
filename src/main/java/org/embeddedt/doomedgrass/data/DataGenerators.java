package org.embeddedt.doomedgrass.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.data.event.GatherDataEvent;
import org.embeddedt.doomedgrass.DoomedGrass;

@Mod.EventBusSubscriber(modid = DoomedGrass.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        DoomedBlockTags blockTags = new DoomedBlockTags(generator, event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new DoomedItemTags(generator, blockTags, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new DoomedLootModifierProvider(generator, DoomedGrass.MODID));
        generator.addProvider(event.includeClient(), new DoomedBlockStates(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new DoomedItemModels(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new DoomedLanguageProvider(generator, DoomedGrass.MODID, "en_us"));
    }
}
