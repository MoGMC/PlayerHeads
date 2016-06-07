package net.hilaryoi.plugin.headplugin;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_9_R2.NBTTagCompound;
import net.minecraft.server.v1_9_R2.NBTTagList;
import net.minecraft.server.v1_9_R2.NBTTagString;

public class Head {

	String name;

	// "value" is the head's skin.
	String value;

	ItemStack item;

	NBTTagCompound nbt;

	NBTTagList lore;

	public Head(String name, String uuid, String value) {

		this.name = name;
		this.value = value;

		nbt = new NBTTagCompound();

		// skull information

		NBTTagCompound skullOwner = new NBTTagCompound();

		skullOwner.setString("Id", uuid);

		NBTTagCompound propertiesCompound = new NBTTagCompound();

		NBTTagList texturesCompound = new NBTTagList();

		NBTTagCompound valueCompound = new NBTTagCompound();

		valueCompound.setString("Value", value);

		texturesCompound.add(valueCompound);

		propertiesCompound.set("textures", texturesCompound);

		skullOwner.set("Properties", propertiesCompound);

		nbt.set("SkullOwner", skullOwner);

		lore = new NBTTagList();

	}

	// creates the physical item or recreates it (if some effect was added)
	public void createItem() {

		item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);

		net.minecraft.server.v1_9_R2.ItemStack mHead = CraftItemStack.asNMSCopy(item);

		/*
		 * add glowing effects, etc here
		 */

		// display

		NBTTagCompound display = new NBTTagCompound();

		display.setString("Name", name);

		display.set("Lore", lore);

		nbt.set("display", display);

		System.out.println(nbt.toString());

		mHead.setTag(nbt);

		item = CraftItemStack.asBukkitCopy(mHead);

	}

	public void addLore(String line) {

		lore.add(new NBTTagString(line));

	}

	// gets item
	public ItemStack getItem() {

		if (item == null) {
			createItem();

		}

		return item;

	}

}
