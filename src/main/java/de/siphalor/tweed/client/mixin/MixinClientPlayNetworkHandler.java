package de.siphalor.tweed.client.mixin;

import de.siphalor.tweed.Core;
import de.siphalor.tweed.config.*;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.packet.GameJoinS2CPacket;
import net.minecraft.util.PacketByteBuf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class MixinClientPlayNetworkHandler {
	@Inject(method = "onGameJoin", at = @At("RETURN"))
	public void onGameJoined(GameJoinS2CPacket packet, CallbackInfo callbackInfo) {
		for(ConfigFile configFile : TweedRegistry.getConfigFiles()) {
			PacketByteBuf packetByteBuf = new PacketByteBuf(Unpooled.buffer());
			packetByteBuf.writeString(configFile.getName());
			packetByteBuf.writeEnumConstant(ConfigEnvironment.SYNCED);
			packetByteBuf.writeEnumConstant(ConfigScope.WORLD);
			packetByteBuf.writeEnumConstant(ConfigOrigin.DATAPACK);
			ClientSidePacketRegistry.INSTANCE.sendToServer(Core.REQUEST_SYNC_C2S_PACKET, packetByteBuf);
		}
	}
}
