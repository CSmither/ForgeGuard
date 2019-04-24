package org.smither.forge_guard;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import org.smither.forge_guard.listeners.ForgeChannelListener;
import org.smither.forge_guard.listeners.JoinListener;

public class ForgeGuard extends Plugin {

	private static ForgeGuard instance;
	private JoinListener joinListener;
	private ForgeChannelListener forgeChannelListener;
	public ForgeGuard() {
		instance = this;
	}

	public static ForgeGuard getInstance() {
		instance = (instance != null) ? instance : new ForgeGuard();
		return instance;
	}

	public void onEnable() {
		registerListener();
		registerChannel();
	}

	private void registerChannel() {
		ProxyServer.getInstance().registerChannel("FML|HS");
	}

	private void registerListener() {
		joinListener = new JoinListener();
		forgeChannelListener = new ForgeChannelListener();
		ProxyServer.getInstance().getPluginManager().registerListener(this, joinListener);
		ProxyServer.getInstance().getPluginManager().registerListener(this, forgeChannelListener);
	}

	public void onDisable() {
		ProxyServer.getInstance().getPluginManager().unregisterListener(joinListener);
		ProxyServer.getInstance().getPluginManager().unregisterListener(forgeChannelListener);
	}
}
