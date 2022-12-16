package org.embeddedt.doomedgrass.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import org.embeddedt.doomedgrass.loot.WitherSkeletonFragmentModifier;
import org.embeddedt.doomedgrass.registry.Registration;

public class DoomedLootModifierProvider extends GlobalLootModifierProvider
{
    public DoomedLootModifierProvider(DataGenerator gen, String modid)
    {
        super(gen, modid);
    }

    @Override
    protected void start()
    {
        add("wither_skeleton_doomed_fragment", new WitherSkeletonFragmentModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(new ResourceLocation("entities/wither_skeleton")).build(),
                        LootItemRandomChanceCondition.randomChance(1.0f).build()
                })
        );
    }
}