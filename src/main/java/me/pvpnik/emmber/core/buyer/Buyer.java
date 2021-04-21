package me.pvpnik.emmber.core.buyer;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.item.ItemLoreUtils;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import me.pvpnik.emmber.items.EmmberSkull;
import me.pvpnik.emmber.utils.HebrewUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.atomic.AtomicReference;

public class Buyer {

    private Gui gui;
    private int lastMoney = -1;

    public Buyer() {
        gui = new Gui(3, "te");

        for (int row = 1; row <= 3; row++) {
            for (int col = 1; col <= 9; col++) {
                short data = 0;
                if (col <= 3) {
                    data = 5;
                } else if (col >= 7) {
                    data = 3;
                } else {
                    data = 15;
                }
                if (row == 2 && col == 2) {
                    continue;
                }
                gui.setItem(row, col, glass(data));
            }
        }

        gui.setItem(3, 5, qm());
        gui.setItem(2, 5, x());
        gui.setItem(1, 5, vi());
        updateGold(null);

        gui.setDefaultClickAction(event -> {
            ItemStack itemStack = gui.getInventory().getItem(10);
            updateGold(itemStack);
        });
    }

    public void open(Player player) {
        gui.open(player);
    }

    private void close() {
        ItemStack itemStack = gui.getInventory().getItem(10);
        Player player = (Player) gui.getInventory().getViewers().get(0);
        if (itemStack != null) {
            player.getInventory().addItem(itemStack);
        }
        player.closeInventory();
    }

    private GuiItem glass(short data) {
        return ItemBuilder.from(new ItemStack(Material.STAINED_GLASS_PANE, 1, data)).setName("§a").addItemFlags(ItemFlag.values()).asGuiItem(event -> event.setCancelled(true));
    }

    private GuiItem qm() {
        /*AtomicReference<ItemStack> item = new AtomicReference<>(new ItemStack(Material.STONE));
        Bukkit.getConsoleSender().sendMessage("item: " + item.get());
        Emmber.getInstance().headDatabaseAPI.ifPresent(headDatabaseAPI -> item.set(headDatabaseAPI.getItemHead("6365")));
        Bukkit.getConsoleSender().sendMessage("item: " + item.get());*/
        ItemStack item = EmmberSkull.getSkull("6365");
        Bukkit.getConsoleSender().sendMessage("item: " + item);
        return ItemBuilder.from(item).setName("§7" + HebrewUtil.reverseHebrew("מידע"))
                .setLore(HebrewUtil.reverseHebrew("בצד שמאל הניחו את החפצים שברצונכם למכור לסוחר,"),
                        HebrewUtil.reverseHebrew("בצד ימין עמדו על מטיל הזהב בכדי לראות את הצעת הסוחר."),
                        HebrewUtil.reverseHebrew("אישרו בלחיצה על הוי,"),
                        HebrewUtil.reverseHebrew("ביטלו בלחיצה על האיקס.")).addItemFlags(ItemFlag.values()).asGuiItem(event -> event.setCancelled(true));
    }

    private GuiItem vi() {
        AtomicReference<ItemStack> item = new AtomicReference<>(new ItemStack(Material.STONE));
        Emmber.getInstance().headDatabaseAPI.ifPresent(headDatabaseAPI -> item.set(headDatabaseAPI.getItemHead("845")));
        return ItemBuilder.from(item.get()).setName("§a" + HebrewUtil.reverseHebrew("אישור")).addItemFlags(ItemFlag.values()).asGuiItem(event -> {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (lastMoney == 0) {
                close();
                return;
            }
            EmmberJob emmberJob = Emmber.getInstance().playerManager.get(player.getUniqueId());
            emmberJob.deposit(lastMoney);
            player.sendMessage("§a" + HebrewUtil.reverseHebrew("מטבעות") + " " + ChatColor.GOLD + lastMoney + " §a" + HebrewUtil.reverseHebrew("מכרת את החפץ בתמורה ל"));
            player.closeInventory();
        });
    }

    private GuiItem x() {
        AtomicReference<ItemStack> item = new AtomicReference<>(new ItemStack(Material.STONE));
        Emmber.getInstance().headDatabaseAPI.ifPresent(headDatabaseAPI -> item.set(headDatabaseAPI.getItemHead("844")));
        return ItemBuilder.from(item.get()).setName(ChatColor.RED + HebrewUtil.reverseHebrew("ביטול")).addItemFlags(ItemFlag.values()).asGuiItem(event -> {event.setCancelled(true); close(); });
    }

    private void updateGold(ItemStack itemStack) {
        Bukkit.broadcastMessage("update gold: " + ItemLoreUtils.getPrice(itemStack));
        if (itemStack == null) {
            if (lastMoney != 0) {
                gui.setItem(2, 8, gold(0));
                lastMoney = 0;
            }
            gui.update();
            return;
        }
        int price = ItemLoreUtils.getPrice(itemStack);
        int truePrice = (int) (price * 0.25 /*25% back*/ * itemStack.getAmount());
        if (lastMoney != truePrice) {
            gui.setItem(2, 8, gold(truePrice));
            lastMoney = truePrice;
        }
        gui.update();
    }

    private GuiItem gold(int money) {
        return ItemBuilder.from(Material.GOLD_INGOT).setName("§a" + money + " " + HebrewUtil.reverseHebrew("הצעת הסוחר:")).addItemFlags(ItemFlag.values()).asGuiItem();
    }

}
