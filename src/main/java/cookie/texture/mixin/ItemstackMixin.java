package cookie.texture.mixin;

import com.mojang.nbt.CompoundTag;
import cookie.texture.helper.ItemArrayHelper;
import cookie.texture.util.TextureEntry;
import cookie.texture.util.TextureHelper;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.net.command.TextFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemStack.class, remap = false)
public abstract class ItemstackMixin {
	@Shadow
	private CompoundTag tag;

	@Shadow
	public abstract String getDisplayName();

	@Shadow
	public int itemID;

	@Inject(method = "getIconIndex", at = @At("HEAD"), cancellable = true)
	private void textureswap_changeIcon(CallbackInfoReturnable<Integer> cir) {
		if (this.tag.getBoolean("overrideName")){
			String name = getDisplayName().replace(TextFormatting.ITALIC.toString(), "");
			for (TextureEntry entry : TextureHelper.textureEntryFiles){
				if (entry.itemName.equals(Item.itemsList[itemID].getKey())){
					int lookupInt = entry.entries.getOrDefault(name, -1);
					if (lookupInt == -1) break;
					int[] texture = ItemArrayHelper.getOrCreateDynamicTexture(Item.itemsList[itemID].getKey().replace(".", "_") + "/" + lookupInt + ".png");
					cir.setReturnValue(Block.texCoordToIndex(texture[0], texture[1]));

				}
			}
		}
	}
}
