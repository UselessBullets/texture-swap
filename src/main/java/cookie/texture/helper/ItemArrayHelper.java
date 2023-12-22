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
	public static List<int[]> freeCoords = new ArrayList<>();
	public static Map<String, int[]> registeredItemTextures = new HashMap<>();

	public static void addTextureToItems(String textureSource, int x, int y) {
		textureHandlers.add(new TextureHandler("/gui/items.png",
			"/textureswap/" + textureSource,
			Item.iconCoordToIndex(x, y),
			16,
			1));
	}

	public static int[] getOrCreateDynamicTexture(String textureSource) {
		if (registeredItemTextures.containsKey(textureSource)){
			return registeredItemTextures.get(textureSource);
		}
		int[] coords;
		if (!freeCoords.isEmpty()){
			coords = freeCoords.remove(0);
		} else {
			coords = ItemCoords.nextCoords();
			requestedCoords.add(coords);
		}
		registeredItemTextures.put(textureSource, coords);
		addTextureToItems(textureSource, coords[0], coords[1]);
        return coords;
    }
	public static void packChange(){
		registeredItemTextures.clear();
		textureHandlers.clear();
		freeCoords = new ArrayList<>();
		freeCoords.addAll(requestedCoords);
	}
}
