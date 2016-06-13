package net.hilaryoi.plugin.headplugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class CrateFactory {

		Storage storage;

		ConfigurationSection crates;

		String active_crate;

		Random random = new Random();

		// UUID meant to be the player's uuid but can be any unique id
		public Crate generateCrate(UUID uuid, String crateId) {

			Crate crate = new Crate(storage.generateCrate(uuid, crateId), crateId);

			ConfigurationSection crateConfig = crates.getConfigurationSection(crateId);

			crate.setName(ChatColor.translateAlternateColorCodes('&', crateConfig.getString("name")));

			for (String line : crateConfig.getStringList("lore")) {
					crate.addLore(ChatColor.translateAlternateColorCodes('&', line));

			}

			// TODO: add a list of the possible heads

			return crate;

		}

		public Head generateHead(String crateId) {

			ConfigurationSection crateConfig = crates.getConfigurationSection(crateId);

			int chance = random.nextInt(1000);

			ConfigurationSection headChances = crateConfig.getConfigurationSection("heads");

			Head head = null;

			for (String headId : headChances.getKeys(false)) {

					if (chance <= headChances.getInt(headId)) {

						// this is the crate that has been picked

						head = HeadFactory.generateHead(headId);

						break;

					}

			}

			if (head == null) {

					Bukkit.getLogger().severe("Crate " + crateConfig.getString("name") + " has a chance error. Please fix ASAP.");
					return null;

			}

			// TODO: generate random effects

			// add crate lore (after head's own lore)

			// add space before crate lore
			head.addLore("");

			for (String line : crateConfig.getStringList("lore")) {
					head.addLore(ChatColor.translateAlternateColorCodes('&', line));

			}

			return head;

		}

		// loads crate data from a url of raw text
		public CrateFactory(String url, Storage storage) throws IOException {

			this.storage = storage;

			URL website = new URL(url);

			ReadableByteChannel rbc = Channels.newChannel(website.openStream());

			File file = new File("crates.yml");

			FileOutputStream fos = new FileOutputStream(file);

			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

			fos.close();

			YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

			active_crate = config.getString("active_crate");

			crates = config.getConfigurationSection("crates");

		}

}
