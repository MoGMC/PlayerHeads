package net.hilaryoi.plugin.headplugin;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_9_R2.util.CraftMagicNumbers;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_9_R2.Blocks;
import net.minecraft.server.v1_9_R2.Items;
import net.minecraft.server.v1_9_R2.NBTTagCompound;
import net.minecraft.server.v1_9_R2.NBTTagList;
import net.minecraft.server.v1_9_R2.NBTTagString;

public class HeadPlugin extends JavaPlugin {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			return false;

		}

		String uuid = "d8412cf4-2b84-11e6-b67b-9e71128cae77";
		String valueString = "eyJ0aW1lc3RhbXAiOjE0NjUxNjQ1MTU2NjAsInByb2ZpbGVJZCI6ImI4MzQ2NTJiM2VkMjRhZmQ5MmRlYWZjZTRkY2Y4MGYxIiwicHJvZmlsZU5hbWUiOiJrYXdhaWlidXR0ZXJmbDExIiwidGV4dHVyZXMiOnsiU0tJTiI6eyJtZXRhZGF0YSI6eyJtb2RlbCI6InNsaW0ifSwidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yN2VjZjU0YzkzMjM5NmVmM2VjMDQ3N2FhOWViYzEyZjJlYjhmMmM3NjA5YThiNDU3NGFlM2Q4NTYzODVjNDUifX19";

		Player player = (Player) sender;

		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);

		net.minecraft.server.v1_9_R2.ItemStack mHead = CraftItemStack.asNMSCopy(head);

		NBTTagCompound tag = new NBTTagCompound();

		// display

		NBTTagCompound display = new NBTTagCompound();

		display.setString("Name", ChatColor.BLACK + "bob's head");

		tag.set("display", display);

		// skull information

		NBTTagCompound skullOwner = new NBTTagCompound();

		// optional unique identifier for a skull
		skullOwner.set("Id", new NBTTagString(uuid));

		NBTTagCompound properties = new NBTTagCompound();

		NBTTagList textures = new NBTTagList();

		NBTTagCompound value = new NBTTagCompound();

		value.setString("Value", valueString);

		textures.add(value);

		properties.set("textures", textures);

		skullOwner.set("Properties", properties);

		tag.set("SkullOwner", skullOwner);

		mHead.setTag(tag);

		head = CraftItemStack.asBukkitCopy(mHead);

		player.getInventory().addItem(head);

		return true;

	}

}
