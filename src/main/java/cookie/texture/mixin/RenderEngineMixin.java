package cookie.texture.mixin;

import cookie.texture.TextureSwap;
import cookie.texture.helper.ItemArrayHelper;
import cookie.texture.util.TextureEntry;
import cookie.texture.util.TextureHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.RenderEngine;
import net.minecraft.client.render.dynamictexture.DynamicTexture;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = RenderEngine.class, remap = false)
public abstract class RenderEngineMixin {

	@Shadow
	private List<DynamicTexture> dynamicTextures;
	@Unique
	private final RenderEngine renderEngine = (RenderEngine)(Object)this;

	@Inject(method = "initDynamicTextures", at = @At("TAIL"))
	private void textureswap_initJson(List<Throwable> errors, CallbackInfo ci) {
		TextureHelper.loadTexturesFromJson();
		for (TextureEntry entry : TextureHelper.textureEntryFiles){
			TextureHelper.getItemFromKey(entry.itemName);

			for (int num: entry.entries.values()) {
				TextureHelper.getFolderFromKey(entry.itemName, String.valueOf(num));
				TextureSwap.LOGGER.info(String.valueOf(num));
			}
		}
	}

	@Inject(method = "initDynamicTextures", at = @At("TAIL"))
	private void textureswap_updateSize(List<Throwable> errors, CallbackInfo ci) {
		Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
		for (String key: ItemArrayHelper.textureDestinationResolutions.keySet()) {
			// Updates atlas resolution references
			GL11.glBindTexture(3553, renderEngine.getTexture(key));
			int destinationResolution = GL11.glGetTexLevelParameteri(3553, 0, 4096) / ItemArrayHelper.textureAtlasWidths.get(key);
			ItemArrayHelper.textureDestinationResolutions.put(key, destinationResolution);
		}
		ItemArrayHelper.textureHandlers.forEach((handler) -> dynamicTextures.add(handler.newHandler(mc)));
	}
}
