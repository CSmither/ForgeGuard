package org.smither.forge_guard.listeners;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerHandshakeEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Event;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.scheduler.GroupedThreadFactory;
import net.md_5.bungee.event.EventHandler;
import org.smither.forge_guard.ForgeGuard;

import java.util.concurrent.TimeUnit;

public class JoinListener implements Listener {
	@EventHandler
	public void onJoin(PostLoginEvent event) {
		ProxiedPlayer proxiedPlayer = event.getPlayer();
		if (proxiedPlayer.isForgeUser()) {
			ForgeGuard.getInstance().getLogger().info("MODS->" + String.join(", ",
				proxiedPlayer.getModList().keySet()));
		}
	}
	@EventHandler
	public void onConnected(ServerConnectedEvent event) {
		ProxiedPlayer proxiedPlayer = event.getPlayer();
		if (proxiedPlayer.isForgeUser()) {
			proxiedPlayer.sendData("FML|HS", new byte[]{0, 2, 0, 0, 0, 0});
			proxiedPlayer.sendData("FML|HS", new byte[]{2, 0, 0, 0, 0});
			ForgeGuard.getInstance().getLogger().info("MODS->" + String.join(", ",
				proxiedPlayer.getModList().keySet()));
		}
	}
}
