package cookie.texture;

import com.google.gson.Gson;
import cookie.texture.helper.ItemArrayHelper;
import cookie.texture.util.TextureHelper;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.item.material.ArmorMaterial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class TextureSwap implements GameStartEntrypoint {
    public static final String MOD_ID = "textureswap";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Gson GSON = new Gson();
	@Override
	public void beforeGameStart() {
		LOGGER.info("TextureSwap initialized.");
	}

	@Override
	public void afterGameStart() {
	}
}
