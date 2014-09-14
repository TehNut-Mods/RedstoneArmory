package main.redstonearmory.client.contributor;

import main.redstonearmory.ConfigHandler;
import main.redstonearmory.ModInformation;
import main.redstonearmory.util.interfaces.IVersionChecker;

import java.util.Map.Entry;

public class ParticleVersionChecker extends RemoteIdentifierManager implements IVersionChecker {
	private static ParticleVersionChecker instance;

	private ParticleVersionChecker() {
		super(ModInformation.WEBASSETSDIR + "version.txt");
	}

	@Override
	public String getCurrentVersion(Object... args) {
		String trailsVersion = ConfigHandler.contributorTrailsVersion.getString();

		if (trailsVersion != null && !trailsVersion.isEmpty()) {
			String type = (String)args[0];
			String find = trailsVersion.substring(trailsVersion.indexOf(type));

			return find.substring(find.indexOf(":") + 1, find.indexOf(";"));
		}
		return null;
	}

	@Override
	public boolean isUpToDate(Object... args) {
		String key = (String)args[0];

		if (this.identifierMap.containsKey(key)) {
			String currentVersion = getCurrentVersion(key);
			int version = Integer.parseInt(this.identifierMap.get(key));

			if (currentVersion != null && Integer.parseInt(currentVersion) == version) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void markUpToDate(Object... args) {
		ConfigHandler.contributorTrailsVersion.set(getVersionString());
		ConfigHandler.config.save();
	}

	@Override
	public String getVersionString() {
		String versionString = "";

		for (Entry<String, String> entry : this.identifierMap.entrySet()) {
			versionString += entry.getKey() + ":" + entry.getValue() + ";";
		}
		return versionString;
	}

	public static ParticleVersionChecker getInstance() {
		return instance == null ? instance = new ParticleVersionChecker() : instance;
	}
}