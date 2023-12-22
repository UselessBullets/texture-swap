package cookie.texture;

import com.google.gson.Gson;
import cookie.texture.helper.ItemArrayHelper;
import cookie.texture.util.TextureEntry;
import cookie.texture.util.TextureHelper;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class TextureSwap implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "textureswap";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Gson GSON = new Gson();

    @Override
    public void onInitialize() {

        LOGGER.info("TextureSwap initialized.");
    }

	@Override
	public void beforeGameStart() {

	}

	@Override
	public void afterGameStart() {
		TextureHelper.loadTexturesFromJson();
		for (TextureEntry entry : TextureHelper.textureEntryFiles){
			LOGGER.info(entry.itemName);
			TextureHelper.getItemFromKey(entry.itemName);

			for (int num: entry.entries.values()) {
				TextureHelper.getFolderFromKey(entry.itemName, String.valueOf(num));
				ItemArrayHelper.getOrCreateDynamicTexture(entry.itemName.replace(".", "_") + "/" + num + ".png");
			}
		}
	}

	@Override
	public void onRecipesReady() {

	}
}
