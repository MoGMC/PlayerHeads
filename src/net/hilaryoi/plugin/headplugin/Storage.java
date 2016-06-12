package net.hilaryoi.plugin.headplugin;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Storage {

		final String KEYS_NODE = "keys";
		final String CRATES_NODE = "crates";

		final String SALT;

		ArrayList<String> keys;
		ArrayList<String> crates;

		File storageFile;
		YamlConfiguration storage;

		MessageDigest hasher;
		Encoder encoder;

		// UUID is meant to be player UUID but it could always be something else, as long as it's something unique.

		public String generateKey(UUID uuid) {

			String key = hash(uuid.toString());

			// puts key in list to be used or stored later
			keys.add(key);

			return key;

		}

		// crateId is the crate series

		public String generateCrate(UUID uuid, String crateId) {

			String crate = hash(uuid.toString() + crateId);

			crates.add(crate);

			return crate;

		}

		public void removeKey(String key) {

			keys.remove(key);

		}

		public void removeCrate(String crate) {

			keys.remove(crate);

		}

		public boolean isValidKey(String key) {

			return keys.contains(keys);

		}

		public boolean isValidCrate(String crate) {

			return crates.contains(crate);

		}

		public void save() throws IOException {

			storage.set(KEYS_NODE, keys);
			storage.set(CRATES_NODE, crates);

			storage.save(storageFile);

		}

		public Storage(JavaPlugin plugin, String algorithm, String salt) throws NoSuchAlgorithmException {

			this.SALT = salt;

			// gets data file for reading and writing to
			storageFile = new File(plugin.getDataFolder(), "storage.yml");

			// checks if it exists & creates it if it doesn't
			if (!storageFile.exists()) {

					try {
						storageFile.getParentFile().mkdirs();
						storageFile.createNewFile();

					} catch (IOException e) {
						// disable plugin if it can't get the data
						Bukkit.getLogger().severe("Could not create new storage.yml file, disabling plugin.");
						Bukkit.getPluginManager().disablePlugin(plugin);
						e.printStackTrace();

					}

			}

			storage = YamlConfiguration.loadConfiguration(storageFile);

			keys = new ArrayList<String>();
			crates = new ArrayList<String>();

			keys.addAll(storage.getStringList(KEYS_NODE));
			crates.addAll(storage.getStringList(CRATES_NODE));

			hasher = MessageDigest.getInstance(algorithm);
			encoder = Base64.getEncoder();

		}

		// adds salt
		public String hash(String data) {

			String toHash = System.nanoTime() + data + SALT;

			return encoder.encodeToString(hasher.digest(toHash.getBytes()));

		}

}
