package me.pvpnik.emmber.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class CommandShop extends BukkitCommand {

    public CommandShop() {
        super("shop");
        this.description = "Sends shops url";
        this.usageMessage = "/shop";
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player player = (Player) commandSender;
        TextComponent message = new TextComponent("Click me!");
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://emmber.co.il/shop"));
        player.spigot().sendMessage(message);
        return true;
    }

}
