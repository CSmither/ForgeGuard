package org.smither.forge_guard.listeners;

import java.util.concurrent.TimeUnit;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.smither.forge_guard.ForgeGuard;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PostLoginEvent event){
        ProxiedPlayer proxiedPlayer = event.getPlayer();

        ProxyServer.getInstance().getScheduler().schedule(ForgeGuard.getInstance(), () -> {
            proxiedPlayer.sendData("fml:handshake", new byte[]{-2, 0});
            proxiedPlayer.sendData("fml:handshake", new byte[]{0, 2, 0, 0, 0, 0});
            proxiedPlayer.sendData("fml:handshake", new byte[]{2, 0, 0, 0, 0});
        }, 2, TimeUnit.SECONDS);
    }
}
