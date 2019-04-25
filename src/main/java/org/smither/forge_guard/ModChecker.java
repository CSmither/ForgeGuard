package org.smither.forge_guard;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ModChecker {

	private static ModChecker instance;

	public static ModChecker getInstance() {
		instance = instance != null ? instance : new ModChecker();
		return instance;
	}

	private ModMonitor modMonitor = ModMonitor.getInstance();

	public Map<String, Boolean> rateMods(UUID id) {
		ModData mods = modMonitor.getPlayer(id);
		ProxiedPlayer player = ProxyServer.getInstance().getPlayer(id);
		if (mods != null) {
			return mods.getMods().keySet().stream().collect(Collectors.toMap(s -> s, s -> canLoadMod(s,
				player.getPermissions())));
		}
		return Collections.emptyMap();
	}

	private boolean canLoadMod(String mod, Collection<String> permissions) {
		String query = "forgeGuard\\.(\\*|(mod))\\.?\\*";
		return permissions.stream().anyMatch(s -> s.matches(query));
	}
}
