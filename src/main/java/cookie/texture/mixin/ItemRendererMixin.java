package cookie.texture.mixin;

import net.minecraft.client.render.ItemRenderer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ItemRenderer.class, remap = false)
public class ItemRendererMixin {
	@Redirect(method = "renderItem(Lnet/minecraft/core/entity/Entity;Lnet/minecraft/core/item/ItemStack;Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/item/Item;getIconIndex(Lnet/minecraft/core/item/ItemStack;)I"))
	private int getActuallyCorrectIcon(Item instance, ItemStack itemstack){
		return itemstack.getIconIndex();
	}
}
