package cookie.texture.util;

import com.google.gson.stream.JsonReader;
import cookie.texture.TextureSwap;
import net.minecraft.client.Minecraft;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static cookie.texture.TextureSwap.LOGGER;

public class TextureHelper {
	public static final Map<String, TextureEntry> textureEntryFiles = new HashMap<>();
	private static final Minecraft mc = Minecraft.getMinecraft(Minecraft.class);

	public static void loadTexturesFromJson() {
		String jsonLoc = "/textureswap/textures.json";

		try {
			JsonReader reader = new JsonReader(new BufferedReader(new InputStreamReader(mc.texturePackList.selectedTexturePack.getResourceAsStream(jsonLoc))));
			Textures textures = TextureSwap.GSON.fromJson(reader, Textures.class);
			LOGGER.info(Arrays.toString(textures.textures));
			for (TextureEntry entry : textures.textures){
				textureEntryFiles.put(jsonLoc, entry);
				LOGGER.info(entry.itemName);
			}
		} catch (NullPointerException ignored){
			LOGGER.info("null");
		}
	}
}
