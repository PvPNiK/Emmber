package me.pvpnik.emmber.core.friends;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.components.ScrollType;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import me.mattstudios.mfgui.gui.guis.ScrollingGui;
import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.files.Messages;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import me.pvpnik.emmber.items.gui.InvisibleItem;
import me.pvpnik.emmber.items.gui.NextButton;
import me.pvpnik.emmber.items.gui.PreviousButton;
import me.pvpnik.emmber.items.player.OfflinePlayerInfo;
import me.pvpnik.emmber.items.player.PlayerInfo;
import me.pvpnik.emmber.sql.SQLFriends;
import me.pvpnik.emmber.utils.HebrewUtil;
import me.pvpnik.emmber.utils.ItemUtils;
import me.pvpnik.emmber.utils.anvilGUI.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class FriendsInventory {

    private FriendsInventory() {}

    public static void getFriendsMenu(Player p) {
        Messages messages = Emmber.getInstance().localFileManager.messages;
        EmmberJob player = Emmber.getInstance().playerManager.get(p.getUniqueId());

        ScrollingGui scrollingGui = new ScrollingGui(3, 10, ChatColor.DARK_GRAY + messages.getMessage("friends.friends"), ScrollType.VERTICAL);

        scrollingGui.setDefaultClickAction(event -> event.setCancelled(true));

        // row 1
        for (int i = 1; i < 8; i++) {
            scrollingGui.setItem(1, i, new GuiItem(new InvisibleItem()));
        }

        scrollingGui.setItem(1, 8, ItemBuilder.from(ItemUtils.hideFlags(new ItemStack (Material.DIAMOND_SPADE, 1, (short) 125)))
                .setName(messages.getMessage("friends.removefriend")).asGuiItem(event -> {
                    FriendsRemoveInventory.getFriendsRemoveMenu((Player) event.getWhoClicked());
                }));

        scrollingGui.setItem(1, 9, ItemBuilder.from(ItemUtils.hideFlags(new ItemStack (Material.DIAMOND_SPADE, 1, (short) 126)))
                .setName(messages.getMessage("friends.addfriend")).asGuiItem(event -> {
                    new AnvilGUI(Emmber.getInstance(), (Player) event.getWhoClicked() ,Emmber.getInstance().localFileManager.messages.getMessage("friends.playername"), (whoClicked, clickedItemDisplayName) -> {
                        clickedItemDisplayName = HebrewUtil.removeHebrewLetters(clickedItemDisplayName.replace(" ", "").replace(":", ""));
                        Player friend = Bukkit.getPlayer(clickedItemDisplayName);
                        if (friend == null) {
                            Emmber.getInstance().localFileManager.messages.sendMessage(whoClicked, "friends.notonline");
                            return null;
                        }
                        SQLFriends sqlFriends = Emmber.getInstance().sqlManager.friends;
                        if (sqlFriends.friends(whoClicked.getUniqueId(), friend.getUniqueId())) {
                            Emmber.getInstance().localFileManager.messages.sendMessage(whoClicked, "friends.alreadyfriends");
                            return null;
                        }
                        sqlFriends.addFriend(whoClicked.getUniqueId(), friend.getUniqueId());
                        return null;
                    });
                }));

        // row 3
        scrollingGui.setItem(3, 1, new PreviousButton().asGuiItem(event -> scrollingGui.previous()));
        for (int i = 2; i <= 8; i++) {
            scrollingGui.setItem(3, i, new GuiItem(new InvisibleItem()));
        }
        scrollingGui.setItem(3, 9, new NextButton().asGuiItem(event -> scrollingGui.next()));

        for (UUID uuid : player.getFriends()) {
            EmmberJob emmberJob = Emmber.getInstance().playerManager.get(uuid);
            ItemStack itemStack = emmberJob == null ? new OfflinePlayerInfo(Bukkit.getOfflinePlayer(uuid)) : new PlayerInfo(emmberJob);
            scrollingGui.addItem(ItemBuilder.from(itemStack).asGuiItem(event -> {}));
        }

        scrollingGui.open(player.getPlayer());
    }

}
