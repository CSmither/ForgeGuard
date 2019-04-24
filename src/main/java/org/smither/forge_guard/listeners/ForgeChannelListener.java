package org.smither.forge_guard.listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.smither.forge_guard.ModData;
import org.smither.forge_guard.ModMonitor;

import java.util.Map;

public class ForgeChannelListener implements Listener {

	private ModMonitor modMonitor = ModMonitor.getInstance();

	@EventHandler
	public void onMessageRecieve(PluginMessageEvent event) {
		if (event.getTag().equals("REGISTER")) {
			ModData modData = ModData.getModData(event.getData());
			Map<String, String> mods = modData.getMods();
			ProxiedPlayer proxiedPlayer = (ProxiedPlayer) event.getSender();
			if (proxiedPlayer != null) {
				modMonitor.addPlayer(proxiedPlayer.getUniqueId(), modData);
				ProxyServer.getInstance().getLogger().info("MODS-> " + String.join(", ", mods.keySet()));
				if (!mods.isEmpty()) {
					proxiedPlayer.sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText("You're using " +
						                                                                             "Forge!"));
					proxiedPlayer.sendMessage(TextComponent.fromLegacyText("MODS-> " + String.join(", ",
						mods.keySet())));
				}
			}
		}
	}
}
