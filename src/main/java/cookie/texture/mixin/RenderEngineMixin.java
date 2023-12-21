package cookie.texture.mixin;

import cookie.texture.TextureSwap;
import cookie.texture.util.TextureEntry;
import cookie.texture.util.TextureHelper;
import net.minecraft.client.render.RenderEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = RenderEngine.class, remap = false)
public abstract class RenderEngineMixin {

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
}
