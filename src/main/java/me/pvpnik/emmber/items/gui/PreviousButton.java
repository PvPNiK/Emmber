package me.pvpnik.emmber.items.gui;

import me.mattstudios.mfgui.gui.components.GuiAction;
import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import me.pvpnik.emmber.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class PreviousButton extends ItemStack {
    public PreviousButton() {
        super(ItemUtils.hideFlags(ItemBuilder.from(Material.PAPER).setName("Previous").build()));
    }
    public GuiItem asGuiItem() {
        return new GuiItem(this);
    }
    public GuiItem asGuiItem(@Nullable final GuiAction<InventoryClickEvent> action) {
        return new GuiItem(this, action);
    }
}
