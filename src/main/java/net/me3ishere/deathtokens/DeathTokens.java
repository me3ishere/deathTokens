/*
 * Copyright (c) [2024] [me3ishere]
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.me3ishere.deathtokens;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathTokens extends JavaPlugin implements Listener{

    private DeathTokens plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("DeathTokens has been enabled!");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("DeathTokens has been disabled!");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        if (victim.getKiller() instanceof Player) {
            Player killer = victim.getKiller();
            giveKillToken(killer);
        }
    }

    private void giveKillToken(Player player) {
        PlayerInventory inventory = player.getInventory();

        ItemStack killToken = new ItemStack(Material.COMMAND_BLOCK);

        ItemMeta mm = killToken.getItemMeta();
        mm.setDisplayName("Kill Token");

        killToken.setItemMeta(mm);

        inventory.addItem(killToken);
    }
}
