package org.smither.forge_guard;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ModMonitor {

	private Map<UUID, ModData> playerMods;

	private static ModMonitor instance;

	private ModMonitor() {
		playerMods = new HashMap<>();
	}

	public static ModMonitor getInstance() {
		instance = (instance != null) ? instance : new ModMonitor();
		return instance;
	}

	public void addPlayer(UUID player, ModData modData){
		playerMods.put(player, modData);
	}

	public ModData getPlayer(UUID player){
		return playerMods.get(player);
	}

	public ModData clearPlayer(UUID player){
		return playerMods.remove(player);
	}
}
