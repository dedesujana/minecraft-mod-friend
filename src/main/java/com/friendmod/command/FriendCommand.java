package com.friendmod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import com.friendmod.data.FriendData;

import java.util.Collection;

public class FriendCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("friend")
                .then(Commands.literal("add")
                        .then(Commands.argument("player", EntityArgument.player())
                                .executes(ctx -> addFriend(ctx.getSource(), EntityArgument.getPlayer(ctx, "player")))))
                .then(Commands.literal("remove")
                        .then(Commands.argument("player", EntityArgument.player())
                                .executes(ctx -> removeFriend(ctx.getSource(), EntityArgument.getPlayer(ctx, "player")))))
                .then(Commands.literal("list")
                        .executes(ctx -> listFriends(ctx.getSource())))
                .then(Commands.literal("status")
                        .then(Commands.argument("player", EntityArgument.player())
                                .executes(ctx -> checkStatus(ctx.getSource(), EntityArgument.getPlayer(ctx, "player"))))));
    }

    private static int addFriend(CommandSourceStack source, ServerPlayer friend) {
        ServerPlayer player = source.getPlayerOrException();
        FriendData.addFriend(player.getUUID(), friend.getUUID());
        player.displayClientMessage(Component.literal("§a[Friend] Added " + friend.getName().getString() + " as friend!"), false);
        return 1;
    }

    private static int removeFriend(CommandSourceStack source, ServerPlayer friend) {
        ServerPlayer player = source.getPlayerOrException();
        FriendData.removeFriend(player.getUUID(), friend.getUUID());
        player.displayClientMessage(Component.literal("§c[Friend] Removed " + friend.getName().getString() + " from friends."), false);
        return 1;
    }

    private static int listFriends(CommandSourceStack source) {
        ServerPlayer player = source.getPlayerOrException();
        Collection<ServerPlayer> allPlayers = source.getServer().getPlayerList().getPlayers();
        StringBuilder friendList = new StringBuilder("§6[Friends Online]: ");
        
        for (ServerPlayer onlinePlayer : allPlayers) {
            if (FriendData.isFriend(player.getUUID(), onlinePlayer.getUUID())) {
                friendList.append(onlinePlayer.getName().getString()).append(", ");
            }
        }
        
        player.displayClientMessage(Component.literal(friendList.toString()), false);
        return 1;
    }

    private static int checkStatus(CommandSourceStack source, ServerPlayer friend) {
        ServerPlayer player = source.getPlayerOrException();
        boolean online = FriendData.isOnline(friend.getUUID());
        String status = online ? "§aONLINE" : "§cOFFLINE";
        player.displayClientMessage(Component.literal("[Friend] " + friend.getName().getString() + " is " + status), false);
        return 1;
    }
}
