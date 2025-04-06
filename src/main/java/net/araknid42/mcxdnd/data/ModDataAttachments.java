package net.araknid42.mcxdnd.data;

import com.mojang.serialization.Codec;
import net.araknid42.mcxdnd.MCxDND;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModDataAttachments {

    // Create the DeferredRegister for attachment types
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MCxDND.MOD_ID);

    // Serialization via codec
    public static final Supplier<AttachmentType<Integer>> STRENGTH_SCORE = ATTACHMENT_TYPES.register(
            "strength_score", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }

    /* Processing */
    // Return Ability Score Modifier
    public int returnAbilityScoreModifier (int abilityScore) {
        int abilityScoreModifier = switch (abilityScore) {
            case 1 -> -5;
            case (2 | 3) -> -4;
            case (4 | 5) -> -3;
            case (6 | 7) -> -2;
            case (8 | 9) -> -1;
            case (10 | 11) -> 0;
            case (12 | 13) -> 1;
            case (14 | 15) -> 2;
            case (16 | 17) -> 3;
            case (18 | 19) -> 4;
            case (20 | 21) -> 5;
            case (22 | 23) -> 6;
            case (24 | 25) -> 7;
            case (26 | 27) -> 8;
            case (28 | 29) -> 9;
            case (30) -> 10;
            default -> 0;
        };
        return abilityScoreModifier;
    }
}