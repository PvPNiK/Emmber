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
import me.pvpnik.emmber.utils.HebrewUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class FriendsRemoveInventory {

    private FriendsRemoveInventory() {
    }

    public static void getFriendsRemoveMenu(Player p) {
        Messages messages = Emmber.getInstance().localFileManager.messages;
        EmmberJob player = Emmber.getInstance().playerManager.get(p.getUniqueId());

        List<UUID> friends = player.getFriends();
        if (friends.isEmpty()) {
            p.closeInventory();
            Emmber.getInstance().localFileManager.messages.sendMessage(p, "friends.nofriends");
            return;
        }

        ScrollingGui scrollingGui = new ScrollingGui(2, 10, ChatColor.DARK_GRAY + messages.getMessage("friends.choosefriendtoremove"), ScrollType.HORIZONTAL);

        scrollingGui.setDefaultClickAction(event -> event.setCancelled(true));

        scrollingGui.setItem(2, 1, new PreviousButton().asGuiItem(event -> scrollingGui.previous()));
        for (int i = 2; i <= 8; i++) {
            scrollingGui.setItem(2, i, new GuiItem(new InvisibleItem()));
        }
        scrollingGui.setItem(2, 9, new NextButton().asGuiItem(event -> scrollingGui.next()));

        for (UUID uuid : player.getFriends()) {
            final UUID uuid1 = uuid;
            EmmberJob emmberJob = Emmber.getInstance().playerManager.get(uuid);
            ItemStack itemStack = emmberJob == null ? new OfflinePlayerInfo(Bukkit.getOfflinePlayer(uuid)) : new PlayerInfo(emmberJob);

            scrollingGui.addItem(ItemBuilder.from(itemStack).asGuiItem(event -> {
                Player player1 = (Player) event.getWhoClicked();
                OfflinePlayer friend = Bukkit.getOfflinePlayer(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
                /*if (friend == null) { // impossible
                    return;
                }*/
                Emmber.getInstance().sqlManager.friends.removeFriend(player1.getUniqueId(), uuid1);
                String remove = Emmber.getInstance().localFileManager.messages.getMessage("friends.remove");
                String fromlist = Emmber.getInstance().localFileManager.messages.getMessage("friends.fromlist");
                player1.sendMessage(Messages.PREFIX + HebrewUtil.reverseHebrew(fromlist) + " " + (friend == null ? "None" : friend.getName()) + " " + remove);
                player1.closeInventory();
                getFriendsRemoveMenu(player1);
                return;
            }));
        }

        scrollingGui.open(p);
    }

}
