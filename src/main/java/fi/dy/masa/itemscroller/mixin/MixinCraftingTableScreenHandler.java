package fi.dy.masa.itemscroller.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import fi.dy.masa.itemscroller.util.InventoryUtils;

@Mixin(net.minecraft.screen.CraftingTableScreenHandler.class)
public abstract class MixinCraftingTableScreenHandler
{
    @Shadow @Final private net.minecraft.inventory.CraftingInventory craftingInv;
    @Shadow @Final private net.minecraft.inventory.CraftingResultInventory resultInv;
    @Shadow @Final private net.minecraft.entity.player.PlayerEntity player;

    @Inject(method = "onContentChanged", at = @At("RETURN"))
    private void onSlotChangedCraftingGrid(net.minecraft.inventory.Inventory inventory, CallbackInfo ci)
    {
        InventoryUtils.onSlotChangedCraftingGrid(this.player, this.craftingInv, this.resultInv);
    }

    @Inject(method = "updateResult", at = @At("RETURN"))
    private static void onUpdateResult(
            int windowId,
            net.minecraft.world.World world,
            net.minecraft.entity.player.PlayerEntity player,
            net.minecraft.inventory.CraftingInventory craftingInv,
            net.minecraft.inventory.CraftingResultInventory resultInv,
            CallbackInfo ci)
    {
        InventoryUtils.onSlotChangedCraftingGrid(player, craftingInv, resultInv);
    }
}
