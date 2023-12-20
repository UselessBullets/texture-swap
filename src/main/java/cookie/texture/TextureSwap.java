package cookie.texture;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cookie.texture.util.JsonSerializationData;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.TextureHelper;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;
import turniplabs.halplibe.util.TextureHandler;


public class TextureSwap implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "textureswap";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Gson GSON = new Gson();

    @Override
    public void onInitialize() {
		// Serialization
		JsonSerializationData obj = new JsonSerializationData();
		GSON.toJson(obj);

		//

		// De-serialization

		// Finish
		TextureHelper.getOrCreateItemTexture(MOD_ID, obj.newTexture);
        LOGGER.info("TextureSwap initialized.");
    }

	@Override
	public void beforeGameStart() {

	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void onRecipesReady() {

	}
}
