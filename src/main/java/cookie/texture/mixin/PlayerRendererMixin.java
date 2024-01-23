package cookie.texture.mixin;

import cookie.texture.helper.ItemArrayHelper;
import cookie.texture.util.TextureHelper;
import net.minecraft.client.render.entity.LivingRenderer;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.render.model.ModelBase;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.net.command.TextFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

@Mixin(value = PlayerRenderer.class, remap = false)
public abstract class PlayerRendererMixin extends LivingRenderer<EntityPlayer> {

	public PlayerRendererMixin(ModelBase model, float shadowSize) {
		super(model, shadowSize);
	}

	@Inject(method = "setArmorModel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/PlayerRenderer;loadTexture(Ljava/lang/String;)V", shift = At.Shift.AFTER, ordinal = 3))
	private void textureSwap_changeArmorViaName(EntityPlayer entity, int renderPass, float partialTick, CallbackInfoReturnable<Boolean> cir) {
		ItemStack itemstack = entity.inventory.armorItemInSlot(3 - renderPass);
		if (itemstack != null) {
			if (itemstack.getItem() instanceof ItemArmor) {
				String path = "/armor/" + itemstack.getItemName().replace(".", "_") + "/" + itemstack.getDisplayName().replace(" ", "_").replace(TextFormatting.ITALIC.toString(), "") + "_" + (renderPass != 2 ? 1 : 2) + ".png";
				loadTexture(path);
				System.out.println(path);
			}
		}
	}
}
