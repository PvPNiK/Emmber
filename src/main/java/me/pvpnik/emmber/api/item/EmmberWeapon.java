package me.pvpnik.emmber.api.item;

import me.pvpnik.emmber.Emmber;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class EmmberWeapon extends EmmberItem {

    /**
     * Item's attack speed
     */
    private ItemAtkSpeed itemAtkSpeed;

    public EmmberWeapon(Material material, String name, ItemAtkSpeed itemAtkSpeed) {
        super(material, name);
        this.itemAtkSpeed = itemAtkSpeed;
    }

    @Override
    public EmmberItem reloadLore() {
        super.reloadLore();
        ItemMeta itemMeta = getItemMeta();
        List<String> lore = new ArrayList<>();
        if (getPrice() >= 0) {
            lore.add("" + ChatColor.GRAY + getPrice() + " " + ChatColor.WHITE + Emmber.getInstance().localFileManager.messages.getMessage("item.lore.price"));
        }
        if (getMinLevel() >= 0) {
            lore.add("" + ChatColor.GRAY + getMinLevel() + " " + ChatColor.WHITE + Emmber.getInstance().localFileManager.messages.getMessage("item.lore.minlvl"));
        }
        if (getDmg() >= 0) {
            lore.add("" + ChatColor.GRAY + getDmg() + " " + ChatColor.WHITE + Emmber.getInstance().localFileManager.messages.getMessage("item.lore.dmg"));
        }
        if (getArmor() >= 0) {
            lore.add("" + ChatColor.GRAY + getArmor() + " " + ChatColor.WHITE + Emmber.getInstance().localFileManager.messages.getMessage("item.lore.armor"));
        }
        if (getHp() >= 0) {
            lore.add("" + ChatColor.GRAY + getHp() + " " + ChatColor.WHITE + Emmber.getInstance().localFileManager.messages.getMessage("item.lore.hp"));
        }
        lore.add("" + ChatColor.GRAY + itemAtkSpeed.getDisplayName() + " " + ChatColor.WHITE + Emmber.getInstance().localFileManager.messages.getMessage("item.lore.atkspeed"));
        //if (!ItemFactory.isArmor(item))
        //    lore.add("" + ChatColor.GRAY + ItemAtkSpeed.REGULAR.getDisplayName() + " " + ChatColor.WHITE + ChatUtil.reverseHebrew("מהירות:"));
        if (!lore.isEmpty()) {
            itemMeta.setLore(lore);
        }
        setItemMeta(itemMeta);
        return this;
    }

    public ItemAtkSpeed getItemAtkSpeed() {
        return itemAtkSpeed;
    }

    public EmmberWeapon setItemAtkSpeed(ItemAtkSpeed itemAtkSpeed) {
        this.itemAtkSpeed = itemAtkSpeed;
        return this;
    }
}
