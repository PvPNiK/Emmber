package me.pvpnik.emmber.api.item;

import me.mattstudios.mfgui.gui.components.GuiAction;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import me.pvpnik.emmber.Emmber;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EmmberItem extends ItemStack {

    /**
     * Item's UUID
     */
    private String name;
    /**
     * Item's price in different shops
     */
    private int price;
    /**
     * Players with a level under this number cannot use the item
     */
    private int minLevel;
    /**
     * How much dmg the item will give to it's user
     */
    private int dmg;
    /**
     * How much hp the item will give to it's user
     */
    private int hp;
    /**
     * How much armor the item will give to it's user
     */
    private int armor;

    public EmmberItem(Material material, String name) {
        super(material);
        this.name = name;
        if (!name.isEmpty()) {
            setDisplayName(name);
        }
        price = -1;
        minLevel = -1;
        dmg = -1;
        hp = -1;
        armor = -1;
        hideFlags();
    }

    private void setDisplayName(String str) {
        ItemMeta itemMeta = getItemMeta();
        itemMeta.setDisplayName(str);
        setItemMeta(itemMeta);
    }

    public EmmberItem reloadLore() {
        ItemMeta itemMeta = getItemMeta();
        List<String> lore = new ArrayList<>();
        if (price >= 0) {
            lore.add("" + ChatColor.GRAY + price + " " + ChatColor.WHITE + Emmber.getInstance().localFileManager.messages.getMessage("item.lore.price"));
        }
        if (minLevel >= 0) {
            lore.add("" + ChatColor.GRAY + minLevel + " " + ChatColor.WHITE + Emmber.getInstance().localFileManager.messages.getMessage("item.lore.minlvl"));
        }
        if (dmg >= 0) {
            lore.add("" + ChatColor.GRAY + dmg + " " + ChatColor.WHITE + Emmber.getInstance().localFileManager.messages.getMessage("item.lore.dmg"));
        }
        if (armor >= 0) {
            lore.add("" + ChatColor.GRAY + armor + " " + ChatColor.WHITE + Emmber.getInstance().localFileManager.messages.getMessage("item.lore.armor"));
        }
        if (hp >= 0) {
            lore.add("" + ChatColor.GRAY + hp + " " + ChatColor.WHITE + Emmber.getInstance().localFileManager.messages.getMessage("item.lore.hp"));
        }
        //if (!ItemFactory.isArmor(item))
        //    lore.add("" + ChatColor.GRAY + ItemAtkSpeed.REGULAR.getDisplayName() + " " + ChatColor.WHITE + ChatUtil.reverseHebrew("מהירות:"));
        if (!lore.isEmpty()) {
            itemMeta.setLore(lore);
        }
        setItemMeta(itemMeta);
        return this;
    }

    public net.minecraft.server.v1_12_R1.ItemStack asNMSCopy() {
        return CraftItemStack.asNMSCopy(this);
    }

    public GuiItem asGuiItem() {
        return new GuiItem(this);
    }

    public GuiItem asGuiItem(@NotNull final GuiAction<InventoryClickEvent> action) {
        return new GuiItem(this, action);
    }

    public String getNameFromMessages(String itemNameinMessages) {
        return Emmber.getInstance().localFileManager.messages.getMessage("items.food." + itemNameinMessages);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setDisplayName(name);
    }

    public int getPrice() {
        return price;
    }

    public EmmberItem setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public EmmberItem setMinLevel(int minLevel) {
        this.minLevel = minLevel;
        return this;
    }

    public int getDmg() {
        return dmg;
    }

    public EmmberItem setDmg(int dmg) {
        this.dmg = dmg;
        return this;
    }

    public int getHp() {
        return hp;
    }

    public EmmberItem setHp(int hp) {
        this.hp = hp;
        return this;
    }

    public int getArmor() {
        return armor;
    }

    public EmmberItem setArmor(int armor) {
        this.armor = armor;
        return this;
    }

    private void hideFlags() {
        ItemMeta itemMeta = getItemMeta();
        itemMeta.addItemFlags(ItemFlag.values());
        itemMeta.setUnbreakable(true);
        setItemMeta(itemMeta);
    }

}
