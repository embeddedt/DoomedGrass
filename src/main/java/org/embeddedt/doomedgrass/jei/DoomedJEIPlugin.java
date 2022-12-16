package org.embeddedt.doomedgrass.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.embeddedt.doomedgrass.DoomedGrass;
import org.embeddedt.doomedgrass.registry.Registration;

@JeiPlugin
public class DoomedJEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(DoomedGrass.MODID, "jei");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addIngredientInfo(new ItemStack(Registration.DOOMED_GRASS_ITEM.get()), VanillaTypes.ITEM_STACK, Component.translatable("doomed_grass.description.doomed_grass_block"));
    }
}
