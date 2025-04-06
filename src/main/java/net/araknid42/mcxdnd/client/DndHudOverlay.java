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

import java.awt.*;

import static net.araknid42.mcxdnd.data.ModDataAttachments.*;

public class DndHudOverlay implements LayeredDraw.Layer {

    public static final ResourceLocation DND_HUD_OVERLAY = ResourceLocation.fromNamespaceAndPath(MCxDND.MOD_ID,
            "textures/hud/dnd_hud.png");

    private final Minecraft minecraft = Minecraft.getInstance();

    public DndHudOverlay() {
    }

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player != null) {
            int x = 20;
            int y = 75;

            this.minecraft.getProfiler().push("dnd_hud");
            RenderSystem.enableBlend();

            guiGraphics.drawString(minecraft.font, "Strength: " + String.valueOf(minecraft.player.getData(STRENGTH_SCORE)),
                    20, 15, 0xFFFFFF);
            guiGraphics.drawString(minecraft.font, "Dexterity: " + String.valueOf(minecraft.player.getData(DEXTERITY_SCORE)),
                    20, 25, 0xFFFFFF);
            guiGraphics.drawString(minecraft.font, "Constitution: " + String.valueOf(minecraft.player.getData(CONSTITUTION_SCORE)),
                    20, 35, 0xFFFFFF);
            guiGraphics.drawString(minecraft.font, "Intelligence: " + String.valueOf(minecraft.player.getData(INTELLIGENCE_SCORE)),
                    20, 45, 0xFFFFFF);
            guiGraphics.drawString(minecraft.font, "Wisdom: " + String.valueOf(minecraft.player.getData(WISDOM_SCORE)),
                    20, 55, 0xFFFFFF);
            guiGraphics.drawString(minecraft.font, "Charisma: " + String.valueOf(minecraft.player.getData(CHARISMA_SCORE)),
                    20, 65, 0xFFFFFF);

            guiGraphics.blit(DND_HUD_OVERLAY, x, y, 0, 0, 32, 32, 32, 32);

            // guiGraphics.fill(20, 10, 100, 50, 0xF3EAFFEB); THIS LAST VALUE NEEDS TO BE ARGB

            RenderSystem.disableBlend();
            this.minecraft.getProfiler().pop();
        }
    }
}

