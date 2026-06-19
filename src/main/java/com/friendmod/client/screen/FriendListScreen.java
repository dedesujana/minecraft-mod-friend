package com.friendmod.client.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;
import com.friendmod.data.FriendData;

import java.util.Set;
import java.util.UUID;

public class FriendListScreen extends Screen {
    public FriendListScreen() {
        super(Component.literal("Friend List"));
    }

    @Override
    protected void init() {
        this.addRenderableWidget(Button.builder(
                Component.literal("Close"),
                button -> this.onClose())
                .bounds(this.width / 2 - 50, this.height - 30, 100, 20)
                .build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            Set<UUID> friends = FriendData.getFriends(mc.player.getUUID());
            int y = 20;
            guiGraphics.drawString(this.font, "Your Friends:", 20, y, 0xFFFFFF);
            y += 15;
            
            for (UUID friendUUID : friends) {
                boolean online = FriendData.isOnline(friendUUID);
                String status = online ? "§aONLINE" : "§cOFFLINE";
                guiGraphics.drawString(this.font, "• " + friendUUID + " " + status, 30, y, 0xFFFFFF);
                y += 15;
            }
        }
        
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(null);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
