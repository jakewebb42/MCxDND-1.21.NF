package net.araknid42.mcxdnd.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.araknid42.mcxdnd.MCxDND;
import net.araknid42.mcxdnd.item.ModItems;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;

import static net.araknid42.mcxdnd.data.ModDataAttachments.STRENGTH_SCORE;

public class DndHudOverlay implements LayeredDraw.Layer {

    public static final ResourceLocation DND_HUD_OVERLAY = ResourceLocation.fromNamespaceAndPath(MCxDND.MOD_ID,
            "textures/item/dnd_hud");

    private final Minecraft minecraft = Minecraft.getInstance();

    public DndHudOverlay() {
    }

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player != null) {
            int x = guiGraphics.guiWidth() / 2 - 8;
            int y = guiGraphics.guiHeight() - 39;

            this.minecraft.getProfiler().push("dnd_hud");
            RenderSystem.enableBlend();

            guiGraphics.drawString(minecraft.font, "Strength: " + String.valueOf(minecraft.player.getData(STRENGTH_SCORE)), 25, 15, 0xFFFFFF);
            guiGraphics.blitSprite(DND_HUD_OVERLAY, x, y, 32, 32);

            RenderSystem.disableBlend();
            this.minecraft.getProfiler().pop();
        }
    }
}





//guiGraphics.drawString(minecraft.font, "Name: Brick", 25, 15, 0xFFFFFF);
//guiGraphics.drawString(minecraft.font, "Class/Level: §6Paladin§r, Level 8", 25, 25, 0xFFFFFF);
//guiGraphics.drawString(minecraft.font, "HP: 80/80", 25, 35, 0xFFFFFF);
//guiGraphics.fill(20, 10, 100, 50, 0xFFFFFF);