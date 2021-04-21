package me.pvpnik.emmber.api.portals;

import me.pvpnik.emmber.api.jobs.EmmberJob;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerMoveEvent;

public class EmmberPortal extends Portal {

    private String message = "", denyMessage = "", title = "", subtitle = "";
    private int prizeInPoints = 0;
    private Location output, deny;

    /*
        Player Must have
     */
    private String permission = "";
    private int minlevel = -1, feeInPoints = 0;

    public EmmberPortal(String id, Location pos1, Location pos2) {
        super(id, pos1, pos2);
        buildBarriers();
    }

    public boolean canUsePortal(EmmberJob player) {
        if (!permission.isEmpty()) {
            if (!player.getPlayer().hasPermission(permission)) {
                return false;
            }
        }

        if (player.getLevel() < minlevel) {
            return false;
        }

        if (player.getPoints() < feeInPoints) {
            return false;
        }

        return true;
    }

    @Override
    public void execute(EmmberJob player, PlayerMoveEvent event) {
        if (canUsePortal(player)) {
            if (output != null) {
                event.setTo(output);
                //player.getPlayer().teleport(output);
            }
            if (!message.isEmpty()) {
                player.getPlayer().sendMessage(message);
            }
            if (!title.isEmpty() || !subtitle.isEmpty()) {
                player.getPlayer().sendTitle(title, subtitle, 1, 3, 1);
            }
        } else {
            if (deny != null) {
                event.setTo(deny);
                //player.getPlayer().teleport(deny);
            }
            if (!denyMessage.isEmpty()) {
                player.getPlayer().sendMessage(denyMessage);
            }
        }
    }

    public String getMessage() {
        return message;
    }

    public EmmberPortal setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDenyMessage() {
        return denyMessage;
    }

    public EmmberPortal setDenyMessage(String denyMessage) {
        this.denyMessage = denyMessage;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public EmmberPortal setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public EmmberPortal setSubtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public int getPrizeInPoints() {
        return prizeInPoints;
    }

    public EmmberPortal setPrizeInPoints(int prizeInPoints) {
        this.prizeInPoints = prizeInPoints;
        return this;
    }

    public Location getOutput() {
        return output;
    }

    public EmmberPortal setOutput(Location output) {
        this.output = output;
        return this;
    }

    public Location getDeny() {
        return deny;
    }

    public EmmberPortal setDeny(Location deny) {
        this.deny = deny;
        return this;
    }

    public String getPermission() {
        return permission;
    }

    public EmmberPortal setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public int getMinlevel() {
        return minlevel;
    }

    public EmmberPortal setMinlevel(int minlevel) {
        this.minlevel = minlevel;
        return this;
    }

    public int getFeeInPoints() {
        return feeInPoints;
    }

    public EmmberPortal setFeeInPoints(int feeInPoints) {
        this.feeInPoints = feeInPoints;
        return this;
    }
}
