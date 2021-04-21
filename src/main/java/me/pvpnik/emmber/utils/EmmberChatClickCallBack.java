package me.pvpnik.emmber.utils;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Consumer;

import me.pvpnik.emmber.Emmber;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.scheduler.BukkitRunnable;

public class EmmberChatClickCallBack {
    private final static HashMap<UUID, Consumer<Player>> callbacks = new HashMap<>();

    public EmmberChatClickCallBack(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void command(PlayerCommandPreprocessEvent e) {
                if (e.getMessage().startsWith("/emmber:callback")) {
                    String[] args = e.getMessage().split(" ");
                    if (args.length == 2) {
                        if (args[1].split("-").length == 5) {
                            UUID uuid = UUID.fromString(args[1]);
                            Consumer<Player> c = callbacks.remove(uuid);
                            if (c != null) {
                                c.accept(e.getPlayer());
                            }
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }, plugin);
    }

    public void createCallback(Player player, TextComponent text, Consumer<Player> consumer, int durationInTicks) {
        final UUID uuid = UUID.randomUUID();
        callbacks.put(uuid, consumer);
        text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/emmber:callback " + uuid));
        player.spigot().sendMessage(text);
        new BukkitRunnable() {
            @Override
            public void run() {
                callbacks.remove(uuid);
            }
        }.runTaskLaterAsynchronously(Emmber.getInstance(), durationInTicks);
    }
}