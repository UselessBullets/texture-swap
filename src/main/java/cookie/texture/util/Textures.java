package cookie.texture.util;

import java.util.HashMap;

public class Textures {
	public TextureEntry[] textures;

	public Textures() {
		textures = new TextureEntry[] {
			new TextureEntry("item_ingot_iron", new HashMap<String, Integer>(){{put("OwO", 1); put("What's this...?", 2);}}),
		};
	}
}
