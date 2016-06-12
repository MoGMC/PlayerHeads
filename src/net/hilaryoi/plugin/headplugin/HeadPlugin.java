package net.hilaryoi.plugin.headplugin;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HeadPlugin extends JavaPlugin {

		@Override
		public void onEnable() {

			saveDefaultConfig();

			FileConfiguration config = getConfig();

			try {

					HeadFactory.loadHeads(config.getString("heads_url"));
					CrateFactory.loadCrates(config.getString("crates_url"));

			} catch (IOException e) {

					Bukkit.getLogger().severe("Could not fetch head or crate information. Disabling plugin.");
					Bukkit.getPluginManager().disablePlugin(this);

					e.printStackTrace();

			}

		}

		@Override
		public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

			if (!(sender instanceof Player)) {
					return false;

			}

			Head head = CrateFactory.generateHead("lunchbox");

			((Player) sender).getInventory().addItem(head.getItem());

			return true;

		}

}
