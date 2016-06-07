package net.hilaryoi.plugin.headplugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class HeadFactory {

	static FileConfiguration heads;

	public static Head generateHead(String headId) {

		Head head = loadHead(headId);

		// TODO: add randomized things here

		return head;

	}

	private static Head loadHead(String headId) {

		ConfigurationSection headConfig = heads.getConfigurationSection(headId);

		if (headConfig == null) {
			return null;

		}

		Head head = new Head(ChatColor.translateAlternateColorCodes('&', headConfig.getString("name")),
				headConfig.getString("uuid"), headConfig.getString("value"));

		for (String line : headConfig.getStringList("lore")) {

			head.addLore(ChatColor.translateAlternateColorCodes('&', line));

		}

		// TODO: other features here?

		return head;

	}

	// loads head data from a url of raw text
	public static void loadHeads(String url) throws IOException {

		URL website = new URL(url);

		ReadableByteChannel rbc = Channels.newChannel(website.openStream());

		File file = new File("heads.yml");

		FileOutputStream fos = new FileOutputStream(file);

		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

		fos.close();

		heads = YamlConfiguration.loadConfiguration(file);

	}

}
