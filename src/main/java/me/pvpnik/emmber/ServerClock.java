package me.pvpnik.emmber;

import me.pvpnik.emmber.utils.OUT;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * Every piece of code that need to be executed every X seconds,
 * Needs to be registered here.
 */
public class ServerClock extends BukkitRunnable {

    private HashMap<String, Action> actions = new HashMap<>();
    /**
     * how much to wait between runs, in seconds
     */
    private int period = 1;

    public ServerClock(Plugin plugin) {
        this.runTaskTimer(plugin, 20, period*20);
    }

    public void addAction(Action action) {
        actions.put(action.id, action);
    }

    public void addAction(String id, int cooldown, Consumer consumer) {
        actions.put(id, new Action(id, cooldown, consumer));
    }

    public void deleteAction(String id) {
        actions.remove(id);
    }

    public void stopAction(String id) {
        Action action = actions.get(id);
        if (action != null) {
            action.running.set(false);
            OUT.toServer("action stopped: " + id);
        }
    }

    public void startAction(String id) {
        Action action = actions.get(id);
        if (action != null) {
            action.running.set(true);
            OUT.toServer("action started: " + id);
        }
    }

    @Override
    public void run() {
        for (Action action : new ArrayList<>(actions.values())) {
            if (!action.running.get()) {
                continue;
            }
            if (action.currentTime.addAndGet(-period) <= 0) {
                try {
                    action.consumer.accept(action.cycle.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (action.repeat.get()) {
                    if (action.cycle.get() == -1) { // infinite
                        action.currentTime.set(action.cooldown);
                    } else { // has cycles
                        if (action.cycle.addAndGet(-1) == 0) { // remove 1 cycle and check if == 0
                            actions.remove(action.id);
                        } else { // if cycle > 0
                            action.currentTime.set(action.cooldown);
                        }
                    }
                } else {
                    actions.remove(action.id);
                }
            }
        }
    }

    public static class Action {
        /**
         * A way to identify this action from other actions
         */
        private final String id;
        /**
         * how much to wait between runs, in seconds
         */
        private final int cooldown;
        private AtomicInteger currentTime;
        /**
         * The function to run when time comes
         * Return {@link Action#cycle}
         */
        private final Consumer<Integer> consumer;
        private AtomicBoolean running;
        private AtomicBoolean repeat;
        /**
         * if repeat if True,
         * how much time runs to do, if set to -1 it will run forever
         */
        private AtomicInteger cycle;

        public Action(String id, int cooldown, Consumer<Integer> consumer) {
            this.id = id;
            this.cooldown = cooldown;
            currentTime = new AtomicInteger(cooldown);
            this.consumer = consumer;
            running = new AtomicBoolean(true);
            repeat = new AtomicBoolean(true);
            cycle = new AtomicInteger(-1);
        }

        public Action setRepeat(boolean repeat) {
            this.repeat.set(repeat);
            return this;
        }

        public Action setCycle(int cycle) {
            cycle = cycle <= 0 ? -1 : cycle; // Cycle must be above 0
            this.cycle.set(cycle);
            return this;
        }

    }

}
