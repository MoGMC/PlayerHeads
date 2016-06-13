package net.hilaryoi.plugin.headplugin;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_10_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class Key extends NBTItem {

		static String name_config;
		static List<String> lore_config;

		String hash;

		public Key(String hash) {

			super();

			nbt.setString("hash", hash);

			setName(name_config);

			for (String line : lore_config) {
					addLore(line);

			}

		}

		public Key(ItemStack item) {

			nmsItem = CraftItemStack.asNMSCopy(item);

			hash = nmsItem.getTag().getString("hash");

		}

		@Override
		public void createItem() {

			ItemStack crate = new ItemStack(Material.GOLD_INGOT);

			nmsItem = CraftItemStack.asNMSCopy(crate);

			nbt.set("display", getDisplayCompound());

			nmsItem.setTag(nbt);

			item = CraftItemStack.asBukkitCopy(nmsItem);

		}

		public String getHash() {

			return hash;

		}

		public static void setDisplay(String name, List<String> lore) {

			name_config = name;
			lore_config = lore;

		}

}
