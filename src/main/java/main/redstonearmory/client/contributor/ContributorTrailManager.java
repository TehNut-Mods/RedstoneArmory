package main.redstonearmory.client.contributor;

import main.redstonearmory.ModInformation;
import main.redstonearmory.util.UUIDUtils;

import java.util.UUID;

public class ContributorTrailManager extends RemoteIdentifierManager {
	private static ContributorTrailManager instance;

	protected ContributorTrailManager() {
		super(ModInformation.WEBASSETSDIR + "trails.txt");
	}

	public boolean hasTrail(UUID uuid) {
		return this.identifierMap.containsKey(UUIDUtils.fromUUID(uuid));
//		return true;
	}

	public String getTrailType(UUID uuid) {
		return this.identifierMap.get(UUIDUtils.fromUUID(uuid));
	}

	public static ContributorTrailManager getInstance() {
		return instance == null ? instance = new ContributorTrailManager() : instance;
	}
}
