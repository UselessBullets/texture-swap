package cookie.texture.helper;

import net.minecraft.core.Global;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.ItemCoords;
import turniplabs.halplibe.util.TextureHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemArrayHelper {
	public static List<TextureHandler> textureHandlers = new ArrayList<>();
	public static List<int[]> requestedCoords = new ArrayList<>();
	public static List<int[]> usedCoords = new ArrayList<>();
	public static Map<String, int[]> registeredItemTextures = new HashMap<>();
	public static Map<String, Integer> textureDestinationResolutions = new HashMap<>();
	public static Map<String, Integer> textureAtlasWidths = new HashMap<>();

	static {
		// Assign default texture atlas size
		textureDestinationResolutions.put("/gui/items.png", 16);
		// Assign default texture atlas size
		textureAtlasWidths.put("/gui/items.png", Global.TEXTURE_ATLAS_WIDTH_TILES);
	}

	public static void addTextureToItems(String itemNumber, int x, int y) {
		textureHandlers.add(new TextureHandler("/gui/items.png",
			"/textureswap/" + itemNumber,
			Item.iconCoordToIndex(x, y),
			16,
			1));
	}

	public static int[] getOrCreateDynamicTexture(String textureNumber) {
		int[] possibleCoords = registeredItemTextures.get(textureNumber);
		usedCoords.add(possibleCoords);

		int[] newCoords = ItemCoords.nextCoords();
		requestedCoords.add(newCoords);
		registeredItemTextures.put(textureNumber, newCoords);
		addTextureToItems(textureNumber, newCoords[0], newCoords[1]);

		if (requestedCoords.size() > usedCoords.size())
			return possibleCoords;
		else
			return newCoords;
	}
}
