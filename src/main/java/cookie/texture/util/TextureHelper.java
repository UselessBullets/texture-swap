package cookie.texture.util;

import com.google.gson.stream.JsonReader;
import cookie.texture.TextureSwap;
import net.minecraft.client.Minecraft;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TextureHelper {
	public static final Map<String, TextureEntry> textureEntryFiles = new HashMap<>();
	private static final Minecraft mc = Minecraft.getMinecraft(Minecraft.class);

	public static TextureEntry loadTexturesFromJson() {
		String jsonLoc = "/textureswap/textures.json";

		if (textureEntryFiles.containsKey(jsonLoc)) {
			return textureEntryFiles.get(jsonLoc);
		}

		try {
			JsonReader reader = new JsonReader(new BufferedReader(new InputStreamReader(mc.texturePackList.selectedTexturePack.getResourceAsStream(jsonLoc))));
			TextureEntry textureEntry = TextureSwap.GSON.fromJson(reader, TextureEntry.class);
			textureEntryFiles.put(jsonLoc, textureEntry);
			return textureEntry;
		} catch (NullPointerException e){
			return null;
		}
	}
}
