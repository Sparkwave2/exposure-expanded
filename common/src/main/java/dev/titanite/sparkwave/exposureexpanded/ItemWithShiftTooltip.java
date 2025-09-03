package dev.titanite.sparkwave.exposureexpanded;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class ItemWithShiftTooltip extends Item {

    public String tooltip;

    public ItemWithShiftTooltip(Properties properties, String tooltip) {
        super(properties);
        this.tooltip = tooltip;
    }

    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> components, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable(tooltip).withColor(11184810));
        } else {
            components.add(Component.translatable("tooltip.exposure.hold_for_details"));
        }

    }
}
