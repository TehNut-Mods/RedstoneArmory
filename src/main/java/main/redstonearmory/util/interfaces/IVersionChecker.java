package main.redstonearmory.util.interfaces;

public interface IVersionChecker {

	// Oh look, BoP code!

	public String getCurrentVersion(Object... args);
	public String getVersionString();

	public boolean isUpToDate(Object... args);

	public void markUpToDate(Object... args);
}
