package cookie.texture.util;

import java.util.HashMap;

public class TextureEntry {
	public String itemName;
	public HashMap<String, Integer> entries;

	public TextureEntry(String itemName, HashMap<String, Integer> entries) {
		this.itemName = itemName;
		this.entries = entries;
	}
}
