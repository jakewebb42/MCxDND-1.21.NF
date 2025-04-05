package net.araknid42.mcxdnd.item.custom;

import net.araknid42.mcxdnd.item.ModItems;

import net.araknid42.mcxdnd.sound.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class DieItem extends BowItem {

    // Constructor
    public DieItem(Properties pProperties) {
        super(pProperties);
    }

    /* Custom variables */
    public static int NUM_SIDES;
    public static int MODIFIER = 0;
    public static SoundEvent END_SOUND = SoundEvents.DRAGON_FIREBALL_EXPLODE;
    public static SoundEvent ROLL_SOUND = ModSounds.DICE_ROLL.get();
    //public static SoundEvent ROLL_SOUND = SoundEvents.EXPERIENCE_ORB_PICKUP;
    public static int TICK_COUNTER = 1;

    /* Custom Functions */
    // Set Functions
    public void setNumSides(ItemStack pStack) {
        if (pStack.is(ModItems.D20.get())){
            DieItem.NUM_SIDES = 20;
        }
        else if (pStack.is(ModItems.D12.get())) {
            DieItem.NUM_SIDES = 12;
        }
        else if (pStack.is(ModItems.D10.get())) {
            DieItem.NUM_SIDES = 10;
        }
        else if (pStack.is(ModItems.D8.get())) {
            DieItem.NUM_SIDES = 8;
        }
        else if (pStack.is(ModItems.D6.get())) {
            DieItem.NUM_SIDES = 6;
        }
        else if (pStack.is(ModItems.D4.get()) ||
                pStack.is(ModItems.DAGGER.get())) {
            DieItem.NUM_SIDES = 4;
        }
    }

    // Roll Functions
    public MutableComponent rollDieMutableComponent(boolean useColor) {
        int roll = (int)((Math.random()*DieItem.NUM_SIDES + 1) + DieItem.MODIFIER);
        return Component.literal(String.valueOf(roll));
    }
    public String determineCriticalString(String rollString, ItemStack pStack) {
        String criticalString = "";
        String failureString = "CRITICAL FAILURE";
        String successString = "CRITICAL SUCCESS";
        String blankString = "";

        // Subtitle
        if (rollString.equals("1") && (pStack.is(ModItems.D20.get()))) {
            criticalString = failureString;
        }
        else if (rollString.equals("20") && (pStack.is(ModItems.D20.get()))) {
            criticalString = successString;
        }
        else {
            criticalString = blankString;
        }

        return criticalString;
    }

    // Output Functions
    public void playDiceRoll(Level pLevel, BlockPos playerPos) {
        //Tick Counter
        if (DieItem.TICK_COUNTER < 20){
            DieItem.TICK_COUNTER += 1;
        }
        else if (DieItem.TICK_COUNTER == 20){
            DieItem.TICK_COUNTER = 1;
        }

        // Output Sound
        if (DieItem.TICK_COUNTER == 2){
            pLevel.playSound(null, playerPos, DieItem.ROLL_SOUND, SoundSource.PLAYERS);
        }
    }

    /* Use Functions */
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        // Start using item
        pPlayer.startUsingItem(pHand);

        // Set numSides
        ItemStack pStack = pPlayer.getItemInHand(pHand);
        setNumSides(pStack);

        // Return Success
        return InteractionResultHolder.sidedSuccess(pPlayer.getItemInHand(pHand), pLevel.isClientSide());
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pCount) {
        if (!pLevel.isClientSide) {
            // Init
            BlockPos playerPos = new BlockPos((int)pLivingEntity.getX(), (int)pLivingEntity.getY(), (int)pLivingEntity.getZ());
            Minecraft minecraft = Minecraft.getInstance();
            String blankString = ""; // show blank subtitle no matter what

            // Roll
            MutableComponent rollComponent = rollDieMutableComponent(false);

            // Output Roll
            minecraft.gui.setTimes(0, 50, 50);
            minecraft.gui.setTitle(rollComponent);
            minecraft.gui.setSubtitle(Component.literal(blankString));

            // Output Sound
            playDiceRoll(pLevel, playerPos);

        }

    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if ((pEntityLiving != null) && (!pLevel.isClientSide)) {

            // Init Die
            Minecraft minecraft = Minecraft.getInstance();
            BlockPos playerPos = new BlockPos((int)pEntityLiving.getX(), (int)pEntityLiving.getY(), (int)pEntityLiving.getZ());

            // Init charge
            int useTime = this.getUseDuration(pStack, pEntityLiving) - pTimeLeft;
            float pullProgress = getPowerForTime(useTime);

            // Charge
            if (pullProgress > 0.45F) {
                // Generate roll and subtitle
                MutableComponent rollComponent = rollDieMutableComponent(true);
                String subtitleString = determineCriticalString(rollComponent.getString(), pStack);

                // Output Roll
                minecraft.gui.setTitle(rollComponent);
                minecraft.gui.setSubtitle(Component.literal(subtitleString));

                // Output Sound
                pLevel.playSound(null, playerPos, DieItem.END_SOUND, SoundSource.PLAYERS);
            }
        }
    }
}