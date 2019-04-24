package org.smither.forge_guard.listeners;

import java.util.Map;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.smither.forge_guard.ModData;

public class ForgeChannelListener implements Listener {

    @EventHandler
    public void onMessageRecieve(PluginMessageEvent event) {
        ProxyServer.getInstance()
            .getLogger()
            .info("######################### EVENT:->" + event.getTag());
        ProxyServer.getInstance()
            .getLogger()
            .info("Content:->" + new String(event.getData()));
        if (event.getTag().equals("fml:handshake")) {
            ModData modData = ModData.getModData(event.getData());
            Map<String, String> mods = modData.getMods();
            ProxiedPlayer proxiedPlayer = (ProxiedPlayer) event.getSender();
            if (proxiedPlayer != null) {
                if (!mods.isEmpty()) {
                    proxiedPlayer.disconnect(TextComponent.fromLegacyText("No Forge!"));
                }
            }
        }
    }
}
