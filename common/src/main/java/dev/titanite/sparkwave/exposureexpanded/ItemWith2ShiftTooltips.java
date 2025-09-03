package dev.titanite.sparkwave.exposureexpanded;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class ItemWith2ShiftTooltips extends Item {

    public String tooltip1;
    public String tooltip2;

    public ItemWith2ShiftTooltips(Properties properties, String tooltip1, String tooltip2) {
        super(properties);
        this.tooltip1 = tooltip1;
        this.tooltip2 = tooltip2;
    }

    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> components, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable(tooltip1).withColor(11184810));
            components.add(Component.translatable(tooltip2).withColor(11184810));
        } else {
            components.add(Component.translatable("tooltip.exposure.hold_for_details"));
        }

    }
}
