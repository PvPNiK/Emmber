package me.pvpnik.emmber.api.scoreboard;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import me.pvpnik.emmber.utils.OUT;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class EmmberScoreboard extends BukkitRunnable {
    private final String id;

    private Scoreboard scoreboard;

    private String title;
    private Map<String, Integer> scores;
    private Map<Team, Integer> upda;
    private List<Team> teams;

    public EmmberScoreboard(String id, String title) {
        this.id = id;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.title = title;
        this.scores = Maps.newLinkedHashMap();
        this.upda = Maps.newLinkedHashMap();
        this.teams = Lists.newArrayList();
    }

    public abstract void update();

    public void blankLine() {
        add(" ");
    }

    public void add(String text) {
        add(text, null);
    }

    public void add(String text, Integer score) {
        Preconditions.checkArgument(text.length() < 40, "text cannot be over 40 characters in length");
        text = fixDuplicates(text);

        scores.put(text, score);
    }

    public void addTeam(String text, Integer score) {
        Preconditions.checkArgument(text.length() < 40, "text cannot be over 40 characters in length");
        text = fixDuplicates(text);

        Team team = scoreboard.registerNewTeam("gemsb-" + scoreboard.getTeams().size());
        team.addEntry(text);
        team.setPrefix("");
        team.setSuffix("");
        upda.put(team, score);
        scores.put(text, score);
    }

    public Optional<Team> getTeam(Integer score) {
        for (Team team : upda.keySet()) {
            if (upda.get(team) == score) {
                return Optional.of(team);
            }
        }
        return Optional.ofNullable(null);
    }

    private String fixDuplicates(String text) {
        while (scores.containsKey(text))
            text += ChatColor.RESET;
        if (text.length() > 40)
            text = text.substring(0, 39);
        return text;
    }

    public void build() {
        Objective obj = scoreboard.registerNewObjective((title.length() > 16 ? title.substring(0, 15) : title), "dummy");
        obj.setDisplayName(title);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        int index = scores.size();

        for (Map.Entry<String, Integer> text : scores.entrySet()) {
            Integer score = text.getValue() != null ? text.getValue() : index;
            /*String str = text.getKey();
            if (str.length() > 40) {
                addTeam(str.substring(str.length() - 40), score);
                getTeam(score).get().setPrefix(str.substring(0, str.length() - 40));
            } else {
                obj.getScore(text.getKey()).setScore(score);
            }*/
            //OUT.toConsole(text.getValue() + ", " + text.getKey() + ", l: " + text.getKey().length());
            obj.getScore(text.getKey()).setScore(score);
            index -= 1;
        }
    }

    public void reset() {
        title = null;
        scores.clear();
        for (Team t : teams)
            t.unregister();
        teams.clear();
        try {
            this.cancel();
        } catch (Exception e) {}
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    @Override
    public void run() {

    }

    public String getId() {
        return id;
    }
}

