package me.pvpnik.emmber.core.guild;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.files.Messages;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import me.pvpnik.emmber.core.scoreboard.EmmberScoreboards;
import me.pvpnik.emmber.items.player.OfflinePlayerInfo;
import me.pvpnik.emmber.items.player.PlayerInfo;
import me.pvpnik.emmber.utils.ChatResponse;
import me.pvpnik.emmber.utils.HebrewUtil;
import me.pvpnik.emmber.utils.Invitation;
import me.pvpnik.emmber.utils.ItemUtils;
import me.pvpnik.emmber.utils.anvilGUI.AnvilGUI;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.*;

public class GuildMenuInventory {

    private GuildMenuInventory() {
    }


    private static Messages messages = Emmber.getInstance().localFileManager.messages;
    private static GuildInvites guildInvites = Emmber.getInstance().emmberCoreManager.guildInvites;

    // opens guild's gui
    public static void openGuildmenuInventory(@Nonnull final Player player) {
        Guild g = Emmber.getInstance().emmberCoreManager.guilds.getGuild(player.getUniqueId());
        if (g == null) {
            noGuild(player);
        } else {
            hasGuild(player, g);
        }
    }

    // no guild gui, here u can create your own guild
    private static void noGuild(Player player) {
        Gui gui = new Gui(1, ChatColor.DARK_GRAY + messages.getMessage("guild.guild"));
        gui.setDefaultClickAction(event -> event.setCancelled(true));

        for (int i = 0; i < 9; i++) {
            gui.setItem(i, ItemBuilder.from(ItemUtils.hideFlags(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15))).setName("   ").asGuiItem());
        }
        gui.setItem(4, ItemBuilder.from(ItemUtils.hideFlags(new ItemStack(Material.DIAMOND_SPADE, 1, (short) 128))).setName(ChatColor.GREEN + messages.getMessage("guild.create")).
                setLore(ChatColor.GRAY + "----------------",
                        ChatColor.WHITE + messages.getMessage("guild.createguild"),
                        ChatColor.WHITE + messages.getMessage("items.bank.goldcoins") + getMoneyColor(player) + " 5000 " + ChatColor.WHITE + messages.getMessage("guild.creationcost"),
                        getLevelColor(player) + "10 " + ChatColor.WHITE + messages.getMessage("item.lore.minlvl"),
                        ChatColor.GRAY + "----------------").asGuiItem(event -> {
            EmmberJob emmberJob = Emmber.getInstance().playerManager.get(event.getWhoClicked().getUniqueId());
            /*if (!event.getWhoClicked().hasPermission("guild.anylevel") && emmberJob.getLevel() < 10) {
                event.getWhoClicked().closeInventory();
                //ChatColor.RED + HebrewUtil.reverseHebrew("הרמה הדרושה לפתיחת ברית היא 10");
                return;
            }
            if (emmberJob.getMoney() < 5000) {
                event.getWhoClicked().closeInventory();
                //ChatColor.RED + HebrewUtil.reverseHebrew("אין לך מספיק כסף כדי לפתוח ברית משלך"));
                return;
            }*/

            openGuildCreateConfirmation(player);
        }));

        gui.open(player);
    }

    private static ChatColor getLevelColor(Player player) {
        return Emmber.getInstance().playerManager.get(player.getUniqueId()).getLevel() >= 10 ? ChatColor.GREEN : ChatColor.RED;
    }
    private static ChatColor getMoneyColor(Player player) {
        return Emmber.getInstance().playerManager.get(player.getUniqueId()).getMoney() >= 5000 ? ChatColor.GREEN : ChatColor.RED;
    }


    // shows player's guilds gui
    private static void hasGuild(Player player, Guild g) {
        Gui gui = new Gui(3, ChatColor.BLUE + HebrewUtil.reverseHebrew(g.getName()) + " " + ChatColor.DARK_GRAY + messages.getMessage("guild.guild"));
        gui.setDefaultClickAction(event -> event.setCancelled(true));

        // static items
        GuiItem delete = ItemBuilder.from(ItemUtils.hideFlags(new ItemStack(Material.DIAMOND_SPADE, 1, (short) 127)))
                .setName(ChatColor.RED + messages.getMessage("guild.delete"))
                .asGuiItem(event -> {
                    openGuildDisbandConfiramtion(player, g);
                });

        GuiItem invite = ItemBuilder.from(ItemUtils.hideFlags(new ItemStack(Material.DIAMOND_SPADE, 1, (short) 126)))
                .setName(ChatColor.GREEN + messages.getMessage("guild.invite"))
                .asGuiItem(event -> {
                    if (g.getGuildPlayers().size() >= 10) {
                        player.sendMessage("guild is full !" + g.getMembersList().size() + "/10");
                        return;
                    }
                    event.getWhoClicked().closeInventory();
                    new ChatResponse((Player) event.getWhoClicked(), "name: ", name -> {
                        Player target = Bukkit.getPlayer(name);
                        if (target == null) {
                            event.getWhoClicked().sendMessage(name + " not found");
                            return;
                        }

                        Guild targetGuild = Emmber.getInstance().emmberCoreManager.guilds.getGuild(target.getUniqueId());
                        if (targetGuild != null) {
                            event.getWhoClicked().sendMessage(target.getName() + " in a guild already");
                            return;
                        }

                        if (!guildInvites.getInvites().containsKey(player.getName())) {
                            guildInvites.getInvites().put(player.getName(), new HashSet<>());
                        }
                        guildInvites.getInvites().get(player.getName()).add(target.getUniqueId());
                        Bukkit.broadcastMessage("t: " + guildInvites.getInvites());
                        player.sendMessage("you invited " + target.getName() + " to your guild");
                        target.sendMessage(player.getName() + " has invited you to his guild");
                        target.sendMessage("write: /guild " + player.getName() + "accept - to accept the invitation");
                        target.sendMessage("write: /guild " + player.getName() + "decline - to decline the invitation");
                    });
                });

        GuiItem kick = ItemBuilder.from(ItemUtils.hideFlags(new ItemStack(Material.DIAMOND_SPADE, 1, (short) 125)))
                .setName(ChatColor.RED + messages.getMessage("guild.kick"))
                .asGuiItem(event -> {
                    event.getWhoClicked().closeInventory();
                    openGuildKick((Player) event.getWhoClicked(), g);
                    /*new ChatResponse((Player) event.getWhoClicked(), "name: ", name -> {
                        OfflinePlayer target = Bukkit.getOfflinePlayer(name);
                        if (target == null) {
                            event.getWhoClicked().closeInventory();
                            event.getWhoClicked().sendMessage(name + " not found");
                            return;
                        }
                        g.removeMember(target.getUniqueId());
                        event.getWhoClicked().sendMessage("removed: " + name);
                    });*/
                });

        GuiItem leave = ItemBuilder.from(ItemUtils.hideFlags(new ItemStack(Material.DIAMOND_SPADE, 1, (short) 127)))
                .setName(ChatColor.RED + messages.getMessage("guild.leave"))
                .asGuiItem(event -> {
                    event.getWhoClicked().closeInventory();
                    g.removeMember(event.getWhoClicked().getUniqueId());
                    event.getWhoClicked().sendMessage(Messages.PREFIX + ChatColor.GREEN + messages.getMessage("guild.uleft"));
                    g.sendMessage(Messages.PREFIX + ChatColor.GOLD + event.getWhoClicked().getName() + " " + ChatColor.RED + messages.getMessage("guild.heleft"));
                });

        GuiItem guildItem = ItemBuilder.from(ItemUtils.hideFlags(new ItemStack(Material.DIAMOND_SPADE, 1, (short) 128)))
                .setName(ChatColor.GREEN + messages.getMessage("guild.guildstats"))
                .setLore(ChatColor.GRAY + "----------------",
                        ChatColor.GRAY + Bukkit.getOfflinePlayer(g.getLeader()).getName() + " " + ChatColor.WHITE + messages.getMessage("guild.leader"),
                        ChatColor.GRAY + "" + g.getPoints() + " " + ChatColor.WHITE + messages.getMessage("guild.totalpoints"),
                        ChatColor.GRAY + "----------------")
                .asGuiItem(event -> {
                });

        // leader + members
        GuiItem leader;
        EmmberJob emmberJobLeader = Emmber.getInstance().playerManager.get(g.getLeader());
        if (emmberJobLeader == null) {
            leader = ItemBuilder.from(new OfflinePlayerInfo(Bukkit.getOfflinePlayer(g.getLeader()))).asGuiItem();
        } else {
            leader = ItemBuilder.from(new PlayerInfo(emmberJobLeader)).asGuiItem();
        }

        gui.setItem(0, leader);
        int slot = 0;
        for (UUID uuid : g.getMembersList()) {
            GuiItem guiItem;
            EmmberJob emmberJob = Emmber.getInstance().playerManager.get(uuid);
            if (emmberJobLeader.getPlayer() == null) {
                guiItem = ItemBuilder.from(new OfflinePlayerInfo(Bukkit.getOfflinePlayer(uuid))).asGuiItem();
            } else {
                guiItem = ItemBuilder.from(new PlayerInfo(emmberJob)).asGuiItem();
            }

            gui.setItem(9 + slot, guiItem);
            slot++;
        }

        if (g.isLeader(player.getUniqueId())) {
            gui.setItem(8, delete);
            gui.setItem(7, invite);
            gui.setItem(6, kick);
        } else {
            gui.setItem(8, leave);
        }
        gui.setItem(4, guildItem);

        gui.open(player);
    }

    // choosing guild's name
    private static void onPlayerCreatingGuild(Player p) {
        new AnvilGUI(Emmber.getInstance(), p, " ", (player, name) -> {
            if (name == null || name.isEmpty()) {
                return null;
            }
            name = HebrewUtil.removeHebrewLetters(name.replace(" ", "").replace(":", ""));
            Optional<String> isValidName = Emmber.getInstance().emmberCoreManager.guilds.isValidGuildName(name);

            if (isValidName.isPresent()) {
                p.sendMessage(Messages.PREFIX + isValidName.get());
                return null;
            }

            EmmberJob emmberJob = Emmber.getInstance().playerManager.get(p.getUniqueId());
            emmberJob.withdraw(5000);
            Emmber.getInstance().emmberCoreManager.guilds.addGuild(new Guild(name, p.getUniqueId()));
            return null;
        });
    }

    // confirmation for creating guild
    private static void openGuildCreateConfirmation(Player p) {
        Gui gui = new Gui(9, ChatColor.DARK_GRAY + messages.getMessage("guild.confirmation.create.q"));
        gui.setDefaultClickAction(event -> event.setCancelled(true));

        gui.getFiller().fill(ItemBuilder.from(ItemUtils.hideFlags(new ItemStack(Material.SPIDER_EYE))).setName("   ").asGuiItem());

        gui.setItem(3, ItemBuilder.from(ItemUtils.hideFlags(new ItemStack(Material.DIAMOND_SPADE, 1, (byte) 134)))
                .setName(ChatColor.RED + messages.getMessage("guild.confirmation.create.n")).asGuiItem(event -> {
                    event.getWhoClicked().closeInventory();
                }));
        gui.setItem(5, ItemBuilder.from(ItemUtils.hideFlags(new ItemStack(Material.DIAMOND_SPADE, 1, (byte) 134)))
                .setName(ChatColor.GREEN + messages.getMessage("guild.confirmation.create.y")).asGuiItem(event -> {
                    onPlayerCreatingGuild(p);
                }));

        gui.open(p);
    }

    // disband guild confirmation
    private static void openGuildDisbandConfiramtion(Player p, Guild guild) {
        Gui gui = new Gui(9, ChatColor.BLUE + guild.getName() + " " + ChatColor.DARK_GRAY + messages.getMessage("guild.confirmation.delete.q"));
        gui.setDefaultClickAction(event -> event.setCancelled(true));

        gui.getFiller().fill(ItemBuilder.from(ItemUtils.hideFlags(new ItemStack(Material.SPIDER_EYE))).setName("   ").asGuiItem());

        gui.setItem(3, ItemBuilder.from(ItemUtils.hideFlags(new ItemStack(Material.DIAMOND_SPADE, 1, (byte) 134)))
                .setName(ChatColor.RED + messages.getMessage("guild.confirmation.delete.n")).asGuiItem(event -> {
                    hasGuild(p, guild);
                }));
        gui.setItem(5, ItemBuilder.from(ItemUtils.hideFlags(new ItemStack(Material.DIAMOND_SPADE, 1, (byte) 127)))
                .setName(ChatColor.GREEN + messages.getMessage("guild.confirmation.delete.y")).asGuiItem(event -> {
                    event.getWhoClicked().closeInventory();
                    Emmber.getInstance().emmberCoreManager.guilds.deleteGuild(guild.getName());
                    p.sendMessage("you disbaned the guild");
                    Emmber.getInstance().emmberCoreManager.scoreboardManager.update(EmmberScoreboards.MAIN, p);
                }));

        gui.open(p);
    }

    private static void openGuildKick(Player p, Guild g) {
        Gui gui = new Gui(2, "choose");

        gui.setDefaultClickAction(event -> event.setCancelled(true));

        // leader + members
        GuiItem leader;
        EmmberJob emmberJobLeader = Emmber.getInstance().playerManager.get(g.getLeader());
        if (emmberJobLeader == null) {
            leader = ItemBuilder.from(new OfflinePlayerInfo(Bukkit.getOfflinePlayer(g.getLeader()))).asGuiItem();
        } else {
            leader = ItemBuilder.from(new PlayerInfo(emmberJobLeader)).asGuiItem();
        }

        gui.setItem(0, leader);
        int slot = 0;
        for (UUID uuid : g.getMembersList()) {
            GuiItem guiItem;
            EmmberJob emmberJob = Emmber.getInstance().playerManager.get(uuid);
            if (emmberJobLeader.getPlayer() == null) {
                guiItem = ItemBuilder.from(new OfflinePlayerInfo(Bukkit.getOfflinePlayer(uuid))).asGuiItem(event -> {
                    event.getWhoClicked().closeInventory();
                    gotKicked(g, p, emmberJob);
                    return;
                });
            } else {
                guiItem = ItemBuilder.from(new PlayerInfo(emmberJob)).asGuiItem(event -> {
                    event.getWhoClicked().closeInventory();
                    gotKicked(g, p, emmberJob);
                    return;
                });
            }

            gui.setItem(9 + slot, guiItem);
            slot++;
        }

        gui.open(p);
    }

    private static void gotKicked(Guild guild, Player player, EmmberJob target) {
        if (target.getPlayer() != null) {
            target.getPlayer().sendMessage("you got kicked from the guild");
            player.sendMessage("you kicked " + target.getPlayer().getName());
        }
        guild.removeMember(target.getUuid());
        openGuildKick(player, guild);
    }

}
