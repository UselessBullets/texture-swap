package cookie.texture;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;
import cookie.texture.util.Textures;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

import java.io.FileWriter;
import java.io.IOException;


public class TextureSwap implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "textureswap";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Gson GSON = new Gson();

    @Override
    public void onInitialize() {
		// Serialization
		Textures obj = new Textures();
		GSON = new GsonBuilder().setPrettyPrinting().create();

		try (FileWriter writer = new FileWriter("textureswap/textures.json")) {
			GSON.toJson(obj, writer);
		} catch (IOException e) {
            System.err.println("Couldn't create textureswap/textures.json!");
			e.printStackTrace();
        }

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
