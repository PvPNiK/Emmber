package me.pvpnik.emmber.core.stars;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.files.Messages;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import me.pvpnik.emmber.utils.HebrewUtil;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class StarsInventory {

    private final int ITEM_SLOT = 10;

    public void open(Player player) {

        Messages messages = Emmber.getInstance().localFileManager.messages;

        Gui gui = new Gui(6, messages.getMessage("stars.invName"));

        gui.setDefaultClickAction(event -> event.setCancelled(true));

        // upgrade
        gui.setItem(13, ItemBuilder.from(new ItemStack(Material.DIAMOND_SPADE, 1, (short) 133)).setName(messages.getMessage("stars.upgrade")).addItemFlags(ItemFlag.values()).asGuiItem(event -> {

        }));

        // reset
        gui.setItem(14, ItemBuilder.from(new ItemStack (Material.DIAMOND_SPADE, 1, (short) 131)).setName("stars.reset").addItemFlags(ItemFlag.values()).asGuiItem(event -> {
            ItemStack is = event.getClickedInventory().getItem(ITEM_SLOT);

            if (Stars.getLevel(is) == 0) return;

            int resetCost = Stars.getResetCost(is);

            EmmberJob emmberJob = Emmber.getInstance().playerManager.get(event.getWhoClicked().getUniqueId());

            if (emmberJob.getMoney() < resetCost) {
                event.getWhoClicked().closeInventory();
                event.getWhoClicked().sendMessage("§cnot enought money to RESET the item.");
                return;
            }

            ItemStack original = Stars.reset(is);
            if (original == null) return;

            emmberJob.withdraw(resetCost);

            event.getInventory().setItem(10, original);

            for (int i = 0; i < 7; i++) {
                event.getInventory().setItem(37 + i, new ItemStack(Material.AIR));
            }

            ((Player) event.getWhoClicked()).updateInventory();
            //p.openInventory(StarInventory.get(p, original));
            return;
        }));

        // help
        gui.setItem(15, ItemBuilder.from(new ItemStack (Material.DIAMOND_SPADE, 1, (short) 130)).setName("stars.help").addItemFlags(ItemFlag.values()).asGuiItem(event -> {
            event.getWhoClicked().closeInventory();

            TextComponent second = new TextComponent(HebrewUtil.reverseHebrew("למידע והדרכה אודות המערכת"));
            second.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.google.com/"));

            TextComponent first = new TextComponent(HebrewUtil.reverseHebrew("לחצו כאן"));
            first.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.google.com/"));

            event.getWhoClicked().spigot().sendMessage(first, second);
        }));

        // doom inventory
        gui.setItem(16, ItemBuilder.from(Material.SAND).setName("stars.upgrade").addItemFlags(ItemFlag.values()).asGuiItem(event -> {

        }));

        /*13, upgrade(is));
        inv.setItem(14, reset(is));
        inv.setItem(15, help());
        inv.setItem(16, doomInv());*/


    }

}
