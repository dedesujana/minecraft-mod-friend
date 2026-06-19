package com.friendmod.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import com.friendmod.FriendMod;
import com.friendmod.data.FriendData;

@Mod.EventBusSubscriber(modid = FriendMod.MOD_ID)
public class PlayerEventHandler {
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        FriendData.setOnlineStatus(event.getEntity().getUUID(), true);
        System.out.println("[FriendMod] " + event.getEntity().getName().getString() + " joined!");
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        FriendData.setOnlineStatus(event.getEntity().getUUID(), false);
        System.out.println("[FriendMod] " + event.getEntity().getName().getString() + " left!");
    }
}
