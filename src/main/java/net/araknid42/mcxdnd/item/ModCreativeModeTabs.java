package net.araknid42.mcxdnd.item;

import net.araknid42.mcxdnd.MCxDND;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MCxDND.MOD_ID);

    public static final Supplier<CreativeModeTab>  DICE_TAB = CREATIVE_MODE_TAB.register("dice_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.D20.get()))
                    .title(Component.translatable("creativetab.mcxdnd.dice_tab"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModItems.D20);
                        output.accept(ModItems.D12);
                        output.accept(ModItems.D10);
                        output.accept(ModItems.D8);
                        output.accept(ModItems.D6);
                        output.accept(ModItems.D4);

                        output.accept(ModItems.D20_ADVANTAGE);
                        output.accept(ModItems.D20_DISADVANTAGE);

                    })
                    .build());

    public static final Supplier<CreativeModeTab>  DND_WEAPONS_TAB = CREATIVE_MODE_TAB.register("dnd_weapons_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DAGGER.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(MCxDND.MOD_ID, "dice_tab"))
                    .title(Component.translatable("creativetab.mcxdnd.dnd_weapons_tab"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModItems.DAGGER);

                    })
                    .build());

    public static final Supplier<CreativeModeTab>  SPELLS_TAB = CREATIVE_MODE_TAB.register("spells_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SPELLBOOK.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(MCxDND.MOD_ID, "dnd_weapons_tab"))
                    .title(Component.translatable("creativetab.mcxdnd.spells_tab"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModItems.SPELLBOOK);

                    })
                    .build());

    public static final Supplier<CreativeModeTab>  CHARACTER_SHEET_TAB = CREATIVE_MODE_TAB.register("character_sheet_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.STRENGTH_ATTRIBUTE.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(MCxDND.MOD_ID, "spells_tab"))
                    .title(Component.translatable("creativetab.mcxdnd.character_sheet_tab"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModItems.STRENGTH_ATTRIBUTE);

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}