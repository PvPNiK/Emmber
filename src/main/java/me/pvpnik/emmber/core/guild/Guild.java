package me.pvpnik.emmber.core.guild;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.core.scoreboard.EmmberScoreboards;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class Guild {

	private String name;
	private UUID leader;
	private Set<UUID> members;
	//public static final int cost = 5000;

	public Guild(String name, UUID leaderUUID) {
		this.name = name;
		this.leader = leaderUUID;
		members = new HashSet<>();
	}

	public void addMember(UUID uuid) {
		members.add(uuid);
		Emmber.getInstance().sqlManager.guildMembers.add(name, uuid);
		Emmber.getInstance().emmberCoreManager.scoreboardManager.update(EmmberScoreboards.MAIN, Bukkit.getPlayer(uuid));
		//CastleOfEssver.bsmanager.updateScoreboard(p);
	}

	public void removeMember(UUID uuid) {
		members.remove(uuid);
		Emmber.getInstance().sqlManager.guildMembers.remove(uuid);
		Emmber.getInstance().emmberCoreManager.scoreboardManager.update(EmmberScoreboards.MAIN, Bukkit.getPlayer(uuid));
		//if (Bukkit.getPlayer(uuid) != null)
		//	CastleOfEssver.bsmanager.updateScoreboard(Bukkit.getPlayer(uuid));
	}

	public void sendMessage(String message) {
		List<UUID> uuids = new ArrayList<>(members);
		uuids.add(leader);
		for (UUID uuid : uuids) {
			Player player = Bukkit.getPlayer(uuid);
			if (player != null) {
				player.sendMessage(message);
			}
		}
	}

	public String getName() {
		return this.name;
	}

	public String getLeaderName() {
		return Bukkit.getOfflinePlayer(leader).getName();
	}

	public int getPoints() {
		int points = 0;
		return points;
	}

	public UUID getLeader() {
		return leader;
	}

	public Set<UUID> getGuildPlayers() {
		HashSet<UUID> set = new HashSet<>(getMembersList());
		set.add(leader);
		return set;
	}

	public Set<UUID> getMembersList() {
		//return MembersManager.getInstance().getGuildPlayers(name);
		return members;
	}

	public boolean contains(UUID uuid) {
		return (getLeader().equals(uuid) || getMembersList().contains(uuid));
	}

	public boolean isLeader(UUID uuid) {
		return (getLeader().equals(uuid));
	}

	public int getSize() {
		return getMembersList().size();
	}
}
