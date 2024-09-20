package org.pichxyaponn;

import org.bukkit.plugin.java.JavaPlugin;
import org.pichxyaponn.view.BetterTreeView;
import org.pichxyaponn.controller.BetterTreeController;
import org.pichxyaponn.model.BetterTreeModel;

public final class BetterTree extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("This is Better Tree Plugin!");

        BetterTreeModel betterTreeModel = new BetterTreeModel();
        BetterTreeView betterTreeView = new BetterTreeView();
        BetterTreeController betterTreeController = new BetterTreeController(this, betterTreeModel, betterTreeView);
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
