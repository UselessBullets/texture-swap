package cookie.texture.util;

import java.io.File;
import java.util.HashMap;

public class JsonChecker {
	private static final HashMap<String, File> fileHashMap = new HashMap<>();

	public static void loadJson() {
		if (!fileHashMap.get("textureswap").exists())
			return;
		else
	}
}
