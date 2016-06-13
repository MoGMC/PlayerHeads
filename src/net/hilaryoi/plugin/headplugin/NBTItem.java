package net.hilaryoi.plugin.headplugin;

import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_10_R1.NBTTagCompound;
import net.minecraft.server.v1_10_R1.NBTTagList;
import net.minecraft.server.v1_10_R1.NBTTagString;

public abstract class NBTItem {

		/*
		 * Not really an NBT item, just a way of making things easier with this plugin
		 * 
		 */

		public NBTItem() {
			nbt = new NBTTagCompound();
			lore = new NBTTagList();

		}

		protected String name;

		protected net.minecraft.server.v1_10_R1.ItemStack nmsItem;

		protected ItemStack item;

		protected NBTTagCompound nbt;

		protected NBTTagList lore;

		// creates the physical item or recreates it (if some effect was added)
		public abstract void createItem();

		public void setName(String name) {

			this.name = name;

		}

		public void addLore(String line) {

			lore.add(new NBTTagString(line));

		}

		public net.minecraft.server.v1_10_R1.ItemStack getNmsItem() {

			return nmsItem;

		}

		public ItemStack getItem() {

			if (item == null) {
					createItem();

			}

			return item;

		}

		protected NBTTagCompound getDisplayCompound() {

			NBTTagCompound display = new NBTTagCompound();

			display.setString("Name", name);

			display.set("Lore", lore);

			return display;

		}

}
