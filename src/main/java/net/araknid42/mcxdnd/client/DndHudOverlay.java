package net.araknid42.mcxdnd.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.araknid42.mcxdnd.MCxDND;
import net.araknid42.mcxdnd.item.ModItems;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;

public class DndHudOverlay implements LayeredDraw.Layer {

    public static final ResourceLocation DND_HUD_OVERLAY = ResourceLocation.fromNamespaceAndPath(MCxDND.MOD_ID,
            "textures/hud/dnd_hud.png");

    private final Minecraft minecraft = Minecraft.getInstance();

    public DndHudOverlay() {
    }

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Player pPlayer = Minecraft.getInstance().player;

        if (pPlayer != null && pPlayer.getMainHandItem().getItem() == ModItems.D20.get()) {
            int x = guiGraphics.guiWidth() / 2 - 8;
            int y = guiGraphics.guiHeight() - 39;
            this.minecraft.getProfiler().push("dnd_hud_overlay");

            RenderSystem.enableBlend();
            guiGraphics.blitSprite(DND_HUD_OVERLAY, x, y, 32, 32);
            RenderSystem.disableBlend();

            this.minecraft.getProfiler().pop();
        }
    }
}