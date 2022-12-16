package org.embeddedt.doomedgrass.registry;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.embeddedt.doomedgrass.DoomedGrass;
import org.embeddedt.doomedgrass.block.DoomedGrassBlock;
import org.embeddedt.doomedgrass.item.DoomedFragmentItem;
import org.embeddedt.doomedgrass.loot.WitherSkeletonFragmentModifier;

import static org.embeddedt.doomedgrass.DoomedGrass.MODID;

public class Registration {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MODID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        LOOT_MODIFIERS.register(bus);
    }

    // Some common properties for our blocks and items
    public static final BlockBehaviour.Properties BLOCK_PROPERTIES = BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5f).sound(SoundType.GRAVEL).randomTicks();
    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(DoomedGrass.ITEM_GROUP);


    public static final RegistryObject<Block> DOOMED_GRASS = BLOCKS.register("doomed_grass", () -> new DoomedGrassBlock(BLOCK_PROPERTIES));
    public static final RegistryObject<Item> DOOMED_GRASS_ITEM = fromBlock(DOOMED_GRASS);

    public static final RegistryObject<Item> DOOMED_FRAGMENT = ITEMS.register("doomed_fragment", () -> new DoomedFragmentItem(ITEM_PROPERTIES));

    public static final RegistryObject<Codec<WitherSkeletonFragmentModifier>> WITHER_SKELETON_FRAGMENT_MODIFIER = LOOT_MODIFIERS.register("wither_skeleton_doomed_fragment", WitherSkeletonFragmentModifier.CODEC);

    public static final TagKey<Block> DOOMABLE_BLOCK = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(DoomedGrass.MODID, "doomable_block"));

    // Conveniance function: Take a RegistryObject<Block> and make a corresponding RegistryObject<Item> from it
    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
    }
}
