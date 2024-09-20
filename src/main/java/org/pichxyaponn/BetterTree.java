package org.pichxyaponn;

import org.bukkit.plugin.java.JavaPlugin;
import org.pichxyaponn.View.BetterTreeView;
import org.pichxyaponn.controller.BetterTreeController;
import org.pichxyaponn.model.BetterTreeModel;

public final class BetterTree extends JavaPlugin {
    private BetterTreeController betterTreeController;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("This is Better Tree Plugin!");

        BetterTreeModel betterTreeModel = new BetterTreeModel();
        BetterTreeView betterTreeView = new BetterTreeView();
        betterTreeController = new BetterTreeController(this, betterTreeModel, betterTreeView);
        betterTreeController.registerEvents();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Goodbye Better Tree Plugin!");
    }

    @Override
    public void onLoad() {}
}