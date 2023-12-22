package cookie.texture.util;

import com.google.gson.stream.JsonReader;
import cookie.texture.TextureSwap;
import net.minecraft.client.Minecraft;
import net.minecraft.core.item.Item;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static cookie.texture.TextureSwap.LOGGER;

public class TextureHelper {
	public static final List<TextureEntry> textureEntryFiles = new ArrayList<>();
	private static final Minecraft mc = Minecraft.getMinecraft(Minecraft.class);

	public static void loadTexturesFromJson() {
		String jsonLoc = "/textureswap/textures.json";

		try {
			JsonReader reader = new JsonReader(new BufferedReader(new InputStreamReader(mc.texturePackList.selectedTexturePack.getResourceAsStream(jsonLoc))));
			Textures textures = TextureSwap.GSON.fromJson(reader, Textures.class);
			LOGGER.info(Arrays.toString(textures.textures));
			for (TextureEntry entry : textures.textures){
				textureEntryFiles.add(entry);
				LOGGER.info(entry.itemName);
			}
		} catch (NullPointerException ignored){
			LOGGER.info("null");
		}
	}

	public static Item getItemFromKey(String key) {
		for(Item item : Item.itemsList) {
			if (item != null) {
				if (item.getKey().equalsIgnoreCase(key)) {
					return item;
				}
			}
		}
		throw new NullPointerException("Could not find an item that corresponds to key '" + key + "'");
	}

	public static InputStreamReader getFolderFromKey(String key, String num) {
		String theFiles = "/textureswap/" + key.replace(".", "_") + "/" + num + ".png";

		return new InputStreamReader(mc.texturePackList.selectedTexturePack.getResourceAsStream(theFiles));
	}
}
