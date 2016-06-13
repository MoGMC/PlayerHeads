package net.hilaryoi.plugin.headplugin;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_10_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_10_R1.NBTTagCompound;
import net.minecraft.server.v1_10_R1.NBTTagList;

public class Head extends NBTItem {

		// "value" is the head's skin.
		String value;

		public Head(String name, String uuid, String value) {

			super();

			this.name = name;
			this.value = value;

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

		}

		@Override
		public void createItem() {

			item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);

			nmsItem = CraftItemStack.asNMSCopy(item);

			/*
			 * add glowing effects, etc here
			 */

			nbt.set("display", getDisplayCompound());

			System.out.println(nbt.toString());

			nmsItem.setTag(nbt);

			item = CraftItemStack.asBukkitCopy(nmsItem);

		}

}
