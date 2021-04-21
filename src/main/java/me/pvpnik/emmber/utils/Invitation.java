package me.pvpnik.emmber.utils;

import me.pvpnik.emmber.Emmber;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Invitation {

    public enum InviteType {
        GUILD;



    }

    public static Invite newInvite(InviteType inviteType, Player player, Player target) {
        Invite invite = new Invite(inviteType, player, target);

        return invite;
    }

    public boolean alreadyInvited() {
        return false;
    }

    private static class Invite implements Listener {

        private final InviteType inviteType;
        private final Player player;
        private final Player target;



        public Invite(InviteType inviteType, Player player, Player target) {
            this.inviteType = inviteType;
            this.player = player;
            this.target = target;

            Bukkit.getPluginManager().registerEvents(this, Emmber.getInstance());
        }

        @EventHandler
        public void onInviteCommand(PlayerCommandPreprocessEvent event) {

        }


    }

}
