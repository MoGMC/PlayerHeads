package net.hilaryoi.plugin.headplugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HeadPlugin extends JavaPlugin {

		Storage storage;
		CrateFactory crates;

		@Override
		public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

			if (!(sender instanceof Player)) {
					return false;

			}

			Player player = (Player) sender;

			if (command.getName().equalsIgnoreCase("crate")) {

					// Head head = crates.generateHead("lunchbox");

					Crate crate = crates.generateCrate(player.getUniqueId(), "lunchbox");

					player.getInventory().addItem(crate.getItem());

					return true;

			}

			// key command

			Key key = new Key(storage.generateKey(player.getUniqueId()));

			player.getInventory().addItem(key.getItem());

			return true;

		}

		@Override
		public void onEnable() {

			saveDefaultConfig();

			FileConfiguration config = getConfig();

			try {

					storage = new Storage(this, config.getString("algorithm"), config.getString("salt"));

			} catch (NoSuchAlgorithmException e) {

					Bukkit.getLogger().severe("Could not find specified algorithm. Disabling plugin.");
					e.printStackTrace();
					Bukkit.getPluginManager().disablePlugin(this);

			}

			try {

					HeadFactory.loadHeads(config.getString("heads_url"));
					crates = new CrateFactory(config.getString("crates_url"), storage);

			} catch (IOException e) {

					Bukkit.getLogger().severe("Could not fetch head or crate information. Disabling plugin.");
					Bukkit.getPluginManager().disablePlugin(this);

					e.printStackTrace();

			}

			Bukkit.getPluginManager().registerEvents(new GUI(), this);

		}

		@Override
		public void onDisable() {

			try {

					storage.save();

			} catch (IOException e) {

					Bukkit.getLogger().severe("Could not save storage.yml, this is a very big problem.");
					e.printStackTrace();

			}

		}

		public void loadExternalConfig() throws IOException {

			URL website = new URL(getConfig().getString("config_url"));

			ReadableByteChannel rbc = Channels.newChannel(website.openStream());

			File file = new File("config.yml");

			FileOutputStream fos = new FileOutputStream(file);

			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

			fos.close();

			YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

			ArrayList<String> key_lore = new ArrayList<String>();

			for (String line : config.getStringList("key_display.lore")) {

					key_lore.add(ChatColor.translateAlternateColorCodes('&', line));

			}

			Key.setDisplay(ChatColor.translateAlternateColorCodes('&', config.getString("key.name")), key_lore);

		}

}
