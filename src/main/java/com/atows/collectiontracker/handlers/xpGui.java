package com.atows.collectiontracker.handlers;

import com.atows.collectiontracker.util.Observer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import java.awt.Color;

import static net.minecraft.client.gui.Gui.drawRect;

public class xpGui implements Observer {

    private final Minecraft mc = Minecraft.getMinecraft(); // Get the Minecraft instance
    private double xp = 0;
    private String skill = "";
    private boolean turnedOn = false;
    final float scale = 1.0f; // Default scale, you can change this as needed

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Text event) {
        if (!turnedOn) return;

        // Coordinates for the rectangle
        int x = 10; // X position of the rectangle
        int y = 10; // Y position of the rectangle
        int width = 100; // Width of the rectangle
        int height = 40; // Height of the rectangle (enough for two lines of text)
        int padding = 5; // Padding inside the rectangle

        // Text positions relative to the rectangle
        int textX = x + padding;
        int skillY = y + padding;
        int xpY = skillY + mc.fontRendererObj.FONT_HEIGHT + 2; // Position for the second line

        // Semi-transparent black color for the rectangle
        int rectColor = (100 << 24); // RGBA: 100 alpha, 0 red, 0 green, 0 blue

        // Push matrix for drawing
        GL11.glPushMatrix();

        // Apply scaling
        GL11.glScalef(scale, scale, scale);

        // Draw the semi-transparent black rectangle
        drawRect(x, y, x + width, y + height, rectColor);

        // Draw the text
        mc.fontRendererObj.drawString(skill, textX, skillY, Color.CYAN.getRGB());
        mc.fontRendererObj.drawString(String.valueOf(xp), textX, xpY, Color.CYAN.getRGB());

        // Pop matrix to restore previous OpenGL state
        GL11.glPopMatrix();
    }

    public void setXpText(double s){
        this.xp = s;
    }
    public void setSkill(String s){
        this.skill = s;
    }
    public void setTurnedOn(boolean s){
        this.turnedOn = s;
    }

    @Override
    public void update(double xp, String skill, boolean turnedOn) {
        setSkill(skill);
        setXpText(xp);
        setTurnedOn(turnedOn);
    }
}
