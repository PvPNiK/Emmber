package me.pvpnik.emmber.utils;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.ServerClock;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.function.Consumer;

public class ChatResponse implements Listener {

    private volatile Player player;
    private Consumer<String> consumer; // returns player's message

    public ChatResponse(Player player, String message, Consumer<String> consumer, int wait) {
        this.player = player;
        Bukkit.getPluginManager().registerEvents(this, Emmber.getInstance());
        player.sendMessage(message);
        this.consumer = consumer;
        Emmber.getInstance().serverClock.addAction(new ServerClock.Action("ChatResponse" + player.getUniqueId().toString(),
                wait, integer -> {
            if (this.player != null) {
                this.player.sendMessage("[Elf] stop taking my time u moron!");
                HandlerList.unregisterAll(this);
            }
        }).setRepeat(false));
    }

    public ChatResponse(Player player, String message, Consumer<String> consumer) {
        this(player, message, consumer, 10);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        e.getRecipients().remove(player);
        if (e.getPlayer().equals(player)) {
            e.getRecipients().clear();
            consumer.accept(e.getMessage());
            player = null;
            HandlerList.unregisterAll(this);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.player = null;
        HandlerList.unregisterAll(this);
    }

}
