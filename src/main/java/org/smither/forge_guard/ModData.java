package org.smither.forge_guard;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModData {

	private final Map<String, String> mods;

	private ModData(Map<String, String> mods) {
		this.mods = mods;
	}

	public static ModData getModData(byte[] data) {
		Map<String, String> mods = new HashMap<>();
		String modName = "";
		for (byte aByte : data) {
			if (aByte == 0) {
				mods.put(modName, "?");
				modName = "";
			} else {
				modName += (char) aByte;
			}
		}
		return new ModData(mods);
	}

	public Map<String, String> getMods() {
		return Collections.unmodifiableMap(mods);
	}
}