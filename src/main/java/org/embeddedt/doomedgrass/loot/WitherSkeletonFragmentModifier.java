package org.embeddedt.doomedgrass.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.embeddedt.doomedgrass.registry.Registration;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

public class WitherSkeletonFragmentModifier extends LootModifier {

    public static final Supplier<Codec<WitherSkeletonFragmentModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> LootModifier.codecStart(inst).apply(inst, WitherSkeletonFragmentModifier::new)));
    public WitherSkeletonFragmentModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    public LootItemCondition[] getConditions() {
        return conditions;
    }

    @NotNull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(new ItemStack(Registration.DOOMED_FRAGMENT.get()));
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
