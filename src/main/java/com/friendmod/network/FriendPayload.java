package com.friendmod.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import com.friendmod.FriendMod;
import com.friendmod.data.FriendData;

import java.util.UUID;

public record FriendPayload(UUID friendUUID, String action) implements CustomPacketPayload {
    public static final StreamCodec<ByteBuf, FriendPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, FriendPayload::action,
            ByteBufCodecs.STRING_UTF8, buf -> UUID.fromString(buf),
            FriendPayload::new);

    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(FriendMod.MOD_ID, "friend_packet");

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handle(FriendPayload payload, IPayloadContext context) {
        // Handle on server
        context.enqueueWork(() -> {
            System.out.println("[FriendMod] Received: " + payload.action() + " for " + payload.friendUUID());
        });
    }
}
