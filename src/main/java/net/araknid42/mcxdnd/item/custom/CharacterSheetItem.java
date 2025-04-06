package net.araknid42.mcxdnd.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import static net.araknid42.mcxdnd.data.ModDataAttachments.STRENGTH_SCORE;

public class CharacterSheetItem extends Item {
    public CharacterSheetItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        // Start using item
        pPlayer.startUsingItem(pHand);

        // Increase Str
        int strengthScore =  pPlayer.getData(STRENGTH_SCORE);

        switch (strengthScore) {
            case 10:
                pPlayer.setData(STRENGTH_SCORE, 1);
                break;

            default:
                pPlayer.setData(STRENGTH_SCORE, pPlayer.getData(STRENGTH_SCORE) + 1);
                break;
        }

        // Return Success
        return InteractionResultHolder.sidedSuccess(pPlayer.getItemInHand(pHand), pLevel.isClientSide());
    }
}
