package net.araknid42.mcxdnd.item;

import net.araknid42.mcxdnd.MCxDND;
import net.araknid42.mcxdnd.item.custom.AdvantageDieItem;
import net.araknid42.mcxdnd.item.custom.CharacterSheetItem;
import net.araknid42.mcxdnd.item.custom.DieItem;
import net.araknid42.mcxdnd.item.custom.DisadvantageDieItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MCxDND.MOD_ID);

    // Register Die Items
    public static final DeferredItem<Item> D20 = ITEMS.register("d20",
            () -> new DieItem(new Item.Properties().durability(1)));
    public static final DeferredItem<Item> D12 = ITEMS.register("d12",
            () -> new DieItem(new Item.Properties().durability(1)));
    public static final DeferredItem<Item> D10 = ITEMS.register("d10",
            () -> new DieItem(new Item.Properties().durability(1)));
    public static final DeferredItem<Item> D8 = ITEMS.register("d8",
            () -> new DieItem(new Item.Properties().durability(1)));
    public static final DeferredItem<Item> D6 = ITEMS.register("d6",
            () -> new DieItem(new Item.Properties().durability(1)));
    public static final DeferredItem<Item> D4 = ITEMS.register("d4",
            () -> new DieItem(new Item.Properties().durability(1)));

    public static final DeferredItem<Item> D20_ADVANTAGE = ITEMS.register("d20_advantage",
            () -> new AdvantageDieItem(new Item.Properties().durability(1).rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> D20_DISADVANTAGE = ITEMS.register("d20_disadvantage",
            () -> new DisadvantageDieItem(new Item.Properties().durability(1).rarity(Rarity.UNCOMMON)));

    // Register Weapon Items
    public static final DeferredItem<Item> DAGGER = ITEMS.register("dagger",
            () -> new DieItem(new Item.Properties()));

    // Register Spell Items
    public static final DeferredItem<Item> SPELLBOOK = ITEMS.register("spellbook",
            () -> new Item(new Item.Properties()));

    // Register Character Sheet Items
    public static final DeferredItem<Item> STRENGTH_ATTRIBUTE = ITEMS.register("strength_attribute",
            () -> new CharacterSheetItem(new Item.Properties().durability(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}