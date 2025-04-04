package net.araknid42.mcxdnd.item.custom;

//import net.araknid42.mcxdnd.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;


import java.util.List;

public class AdvantageDieItem extends DieItem {

    // Constructor
    public AdvantageDieItem(Properties pProperties) {
        super(pProperties);
    }

    /* Custom variables */
    public static int NUM_SIDES = 20;
    public static int MODIFIER = 0;

    /* Custom Functions */
    // Roll Functions
    @Override
    public MutableComponent rollDieMutableComponent(boolean useColor) {

        /* Init */
        // Init rolls
        int roll1 = (int)((Math.random()* AdvantageDieItem.NUM_SIDES + 1) + AdvantageDieItem.MODIFIER);
        int roll2 = (int)((Math.random()* AdvantageDieItem.NUM_SIDES + 1) + AdvantageDieItem.MODIFIER);

        // Init Colors
        int rollMaxColor = ChatFormatting.GOLD.getColor();
        int roll1Color = ChatFormatting.WHITE.getColor();
        int roll2Color = ChatFormatting.WHITE.getColor();

        /* Calculate */
        // Calc Colors
        if (useColor) {
            if (roll1 > roll2) {
                roll1Color = rollMaxColor;
            } else if (roll1 < roll2) {
                roll2Color = rollMaxColor;
            }
        }

        // Calc Strings
        String roll1String = String.valueOf(roll1) + " ";
        String roll2String = String.valueOf(roll2);

        // Calc Components
        MutableComponent rollComponent1 = Component.literal(roll1String).withColor(roll1Color);
        MutableComponent rollComponent2 = Component.literal(roll2String).withColor(roll2Color);

        return rollComponent1.append(rollComponent2);
    }

    @Override
    public String determineCriticalString(String rollString, ItemStack pStack) {
        String criticalString = "";
        String failureString = "CRITICAL FAILURE";
        String successString = "CRITICAL SUCCESS";
        String blankString = "";

        // Subtitle
        if (rollString.equals("1 1")) {
            criticalString = failureString;
        }
        else if (rollString.contains("20")) {
            criticalString = successString;
        }
        else {
            criticalString = blankString;
        }

        return criticalString;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.mcxdnd.d_advantage.tooltip"));

        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}