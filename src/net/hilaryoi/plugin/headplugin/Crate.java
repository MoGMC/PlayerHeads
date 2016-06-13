package net.hilaryoi.plugin.headplugin;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_10_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class Crate extends NBTItem {

		String hash, crateId;

		public Crate(String hash, String crateId) {

			super();

			nbt.setString("hash", hash);
			nbt.setString("crateId", crateId);

		}

		// gets information from itemstack rather raw input
		public Crate(ItemStack item) {

			nmsItem = CraftItemStack.asNMSCopy(item);

			nbt = nmsItem.getTag();

			hash = nbt.getString("hash");
			crateId = nbt.getString("crateId");

		}

		@Override
		public void createItem() {

			ItemStack crate = new ItemStack(Material.CHEST);

			nmsItem = CraftItemStack.asNMSCopy(crate);

			nbt.set("display", getDisplayCompound());

			nmsItem.setTag(nbt);

			item = CraftItemStack.asBukkitCopy(nmsItem);

		}

		public String getHash() {
			return hash;

		}

		public String getCrateId() {
			return crateId;

		}

}
