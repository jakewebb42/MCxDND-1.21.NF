package net.araknid42.mcxdnd.item;

import net.araknid42.mcxdnd.MCxDND;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MCxDND.MOD_ID);

    // Register Die Items
    public static final DeferredItem<Item> D20 = ITEMS.register("d20",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> D12 = ITEMS.register("d12",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> D10 = ITEMS.register("d10",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> D8 = ITEMS.register("d8",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> D6 = ITEMS.register("d6",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> D4 = ITEMS.register("d4",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> D20_ADVANTAGE = ITEMS.register("d20_advantage",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> D20_DISADVANTAGE = ITEMS.register("d20_disadvantage",
            () -> new Item(new Item.Properties()));


    // Register Weapon Items
    public static final DeferredItem<Item> DAGGER = ITEMS.register("dagger",
            () -> new Item(new Item.Properties()));

    // Register Spell Items
    public static final DeferredItem<Item> SPELLBOOK = ITEMS.register("spellbook",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}