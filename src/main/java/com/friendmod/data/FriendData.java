package com.friendmod.data;

import java.util.*;

public class FriendData {
    private static final Map<UUID, Set<UUID>> friendLists = new HashMap<>();
    private static final Map<UUID, Boolean> onlineStatus = new HashMap<>();

    public static void addFriend(UUID playerUUID, UUID friendUUID) {
        friendLists.computeIfAbsent(playerUUID, k -> new HashSet<>()).add(friendUUID);
        System.out.println("[FriendMod] " + playerUUID + " added friend " + friendUUID);
    }

    public static void removeFriend(UUID playerUUID, UUID friendUUID) {
        Set<UUID> friends = friendLists.get(playerUUID);
        if (friends != null) {
            friends.remove(friendUUID);
        }
    }

    public static Set<UUID> getFriends(UUID playerUUID) {
        return friendLists.getOrDefault(playerUUID, new HashSet<>());
    }

    public static void setOnlineStatus(UUID playerUUID, boolean online) {
        onlineStatus.put(playerUUID, online);
    }

    public static boolean isOnline(UUID playerUUID) {
        return onlineStatus.getOrDefault(playerUUID, false);
    }

    public static boolean isFriend(UUID playerUUID, UUID otherUUID) {
        return getFriends(playerUUID).contains(otherUUID);
    }
}
