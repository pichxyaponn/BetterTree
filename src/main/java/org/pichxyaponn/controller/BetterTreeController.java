package org.pichxyaponn.controller;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.pichxyaponn.view.BetterTreeView;
import org.pichxyaponn.model.BetterTreeModel;

import java.util.*;
import java.util.stream.Collectors;

public class BetterTreeController implements Listener {
    private final JavaPlugin plugin;
    private final BetterTreeModel model;
    private final BetterTreeView view;
    private final Queue<Block> blocksToCut = new LinkedList<>();

    public BetterTreeController(JavaPlugin plugin, BetterTreeModel model, BetterTreeView view) {
        this.plugin = plugin;
        this.model = model;
        this.view = view;
    }

    public void registerEvents() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block brokenBlock = event.getBlock();
        if (!blocksToCut.isEmpty()) {
            view.sendMessage(player, "You're cutting the tree. Please wait a moment.");
            return;
        }


        if (model.isTreeBlock(brokenBlock.getType())) {
            event.setCancelled(true);
            ItemStack tool = player.getInventory().getItemInMainHand();

            float delay = calculateCooldown(tool);

            view.sendMessage(player, "Starting to cutting down tree...");
            cutDownTree(brokenBlock, player, delay, tool);
        }
    }

    public int calculateCooldown(ItemStack tool) {
        if (tool == null)
            return 3;

        Material material = tool.getType();
        float defaultDelayTime = model.getToolDelay(material);

        ItemMeta meta = tool.getItemMeta();
        if (meta == null)
            return (int) (defaultDelayTime);

        float efficiencyFactor = 1.0f;
        if (tool.hasItemMeta() && meta.hasEnchant(Enchantment.EFFICIENCY)) {
            int efficiencyLevel = meta.getEnchantLevel(Enchantment.EFFICIENCY);
            for (int i = 0; i < efficiencyLevel; i++) {
                efficiencyFactor /= 1.3f;
            }
        }
        return (int) (defaultDelayTime * efficiencyFactor);
    }

    private void cutDownTree(Block startBlock, Player player, float delay, ItemStack tool) {
        int delayAsInteger = Math.round(delay);
        new BukkitRunnable() {
            private final Set<Block> processedBlocks = new HashSet<>();
            private final Material treeType = startBlock.getType();
            private static final int MAX_BLOCKS = 200;
            private int blocksProcessed = 0;

            {
                blocksToCut.add(startBlock);
                processedBlocks.add(startBlock);
            }

            @Override
            public void run() {
                if (!blocksToCut.isEmpty()) {
                    Block block = blocksToCut.poll();
                    if (block != null) {
                        processBlock(block);
                    }
                }

                if (isTreeCompletelyFelled()) {
                    view.sendMessage(player, "Tree cut completely! Blocks processed: " + blocksProcessed);
                    this.cancel();
                }
            }

            private void processBlock(Block block) {
                block.breakNaturally();
                blocksProcessed++;
                view.playSound(player, Sound.BLOCK_WOOD_BREAK, 1.0f, 1.0f);

                if (tool != null) {
                    int randomChance = new Random().nextInt(100);
                    if (tool.containsEnchantment(Enchantment.UNBREAKING)) {
                        int damageChance = (int) (100d / ((double) tool.getEnchantmentLevel(Enchantment.UNBREAKING) + 1d));

                        if (randomChance >= damageChance)
                            return;
                    }

                    int ench = 100;
                    if (tool.getEnchantments().containsKey(Enchantment.UNBREAKING)) {
                        ench = 100 / (tool.getEnchantmentLevel(Enchantment.UNBREAKING) + 1);
                    }

                    if (randomChance > ench)
                        return;

                    short currDurability = tool.getDurability();
                    switch (Objects.requireNonNull(tool.getType())) {
                        case GOLDEN_AXE:
                        case DIAMOND_AXE:
                        case IRON_AXE:
                        case STONE_AXE:
                        case WOODEN_AXE:
                        case NETHERITE_AXE:
                            tool.setDurability((short) (currDurability + 1));
                            break;
                        default:
                            tool.setDurability((short) (currDurability + 2));
                            break;
                    }
                }

                List<Block> newBlocks = getAdjacentBlocks(block).stream()
                        .filter(b -> b.getType() == treeType && !processedBlocks.contains(b))
                        .collect(Collectors.toList());

                blocksToCut.addAll(newBlocks);
                processedBlocks.addAll(newBlocks);
            }

            private List<Block> getAdjacentBlocks(Block block) {
                List<Block> adjacentBlocks = new ArrayList<>();
                for (int x = -1; x <= 1; x++) {
                    for (int y = 0; y <= 1; y++) {
                        for (int z = -1; z <= 1; z++) {
                            if (x != 0 || y != 0 || z != 0) {
                                adjacentBlocks.add(block.getRelative(x, y, z));
                            }
                        }
                    }
                }
                return adjacentBlocks;
            }

            private boolean isTreeCompletelyFelled() {
                return blocksToCut.isEmpty() || blocksProcessed >= MAX_BLOCKS;
            }
        }.runTaskTimer(plugin, 0, delayAsInteger * 20L);
    }
}
