package net.hilaryoi.plugin.headplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_10_R1.EntityHuman;
import net.minecraft.server.v1_10_R1.Items;

public class GUI implements Listener {

		@EventHandler
		public void onPlayerInteract(PlayerInteractEvent e) {

			Action action = e.getAction();

			System.out.println(e.getAction());

			if ((action != Action.RIGHT_CLICK_AIR) && (action != Action.RIGHT_CLICK_BLOCK)) {
					return;

			}

			System.out.println(e.getPlayer().getInventory().getItemInMainHand());

			ItemStack item = e.getPlayer().getInventory().getItemInMainHand();

			if (item == null) {
					System.out.println("Item is null");
					return;

			}

			if (item.getType() != Material.CHEST) {
					System.out.println("Item is not chest");
					return;

			}

			if (!item.hasItemMeta()) {
					System.out.println("Doesn't have item meta.");
					return;

			}

			System.out.println("Has item meta");

			Crate crate = new Crate(item);

			System.out.println("Crate Hash: " + crate.getHash());

			System.out.println("Crate ID: " + crate.getCrateId());

			System.out.println(crate.getHash().isEmpty());

			Inventory i = Bukkit.createInventory(e.getPlayer(), InventoryType.MERCHANT);

			i.setItem(0, new ItemStack(Material.GOLD_INGOT));

			e.getPlayer().openInventory(i);

			// i.setItem(0, new ItemStack(Material.ACACIA_DOOR));

			// EntityHuman ePlayer = ((CraftPlayer) e.getPlayer()).getHandle();

			// ePlayer.openTrade(new CrateMerchant(ePlayer, crate, new net.minecraft.server.v1_10_R1.ItemStack(Items.GOLD_INGOT)));

		}

}
