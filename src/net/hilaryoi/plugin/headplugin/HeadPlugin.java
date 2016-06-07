package net.hilaryoi.plugin.headplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HeadPlugin extends JavaPlugin {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			return false;

		}

		Head head = CrateFactory.generateHead("beta_test");

		((Player) sender).getInventory().addItem(head.getItem());

		return true;

	}

}
