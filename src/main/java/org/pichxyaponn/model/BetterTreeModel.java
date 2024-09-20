package org.pichxyaponn.model;

import java.util.*;

import org.bukkit.Material;

public class BetterTreeModel {
    private final Set<Material> treeBlocks;
    private final Map<Material, Float> toolDelays;

    public BetterTreeModel() {
        this.treeBlocks = new HashSet<>();
        this.toolDelays = new HashMap<>();
        this.initializeTreeBlocks();
        this.initializeToolDelays();
    }

    public boolean isTreeBlock(Material material) {
        return treeBlocks.contains(material);
    }

    public float getToolDelay(Material material) {
        return toolDelays.getOrDefault(material, 3.0f);
    }

    private void initializeTreeBlocks() {
        treeBlocks.add(Material.OAK_LOG);
        treeBlocks.add(Material.BIRCH_LOG);
        treeBlocks.add(Material.JUNGLE_LOG);
        treeBlocks.add(Material.ACACIA_LOG);
        treeBlocks.add(Material.DARK_OAK_LOG);
        treeBlocks.add(Material.SPRUCE_LOG);
    }

    private void initializeToolDelays() {
        toolDelays.put(Material.GOLDEN_AXE, 1.25f);
        toolDelays.put(Material.NETHERITE_AXE, 1.3f);
        toolDelays.put(Material.DIAMOND_AXE, 1.4f);
        toolDelays.put(Material.IRON_AXE, 1.5f);
        toolDelays.put(Material.STONE_AXE, 1.75f);
        toolDelays.put(Material.WOODEN_AXE, 2.5f);
    }
}
