package cookie.texture.mixin;

import cookie.texture.helper.ItemArrayHelper;
import cookie.texture.util.TextureHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.RenderEngine;
import net.minecraft.client.render.dynamictexture.DynamicTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = RenderEngine.class, remap = false)
public abstract class RenderEngineMixin {
	@Shadow
	private List<DynamicTexture> dynamicTextures;
    @Inject(method = "initDynamicTextures", at = @At(value = "TAIL"))
	private void textureswap_updateSize(List<Throwable> errors, CallbackInfo ci) {
		Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
		ItemArrayHelper.packChange();
		TextureHelper.loadTexturesFromJson();
		ItemArrayHelper.textureHandlers.forEach((handler) -> dynamicTextures.add(handler.newHandler(mc)));
	}
}
