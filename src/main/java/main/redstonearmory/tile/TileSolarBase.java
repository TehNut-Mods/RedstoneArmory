package main.redstonearmory.tile;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileSolarBase extends TileEntity implements IEnergyHandler {

	private EnergyStorage energyStorage = new EnergyStorage(500);
	public int dirtiness;
	public static boolean isProducing;
	public static boolean canProduce;

	public TileSolarBase() {
		canProduce = true;
		isProducing = true;
		dirtiness = 0;
		energyStorage.setMaxTransfer(500);
	}

	@Override
	public void updateEntity() {
		transferEnergy();
		damageSolar();

		if (canProduce) {
			generate();
		}
	}

	public void generate() {
		energyStorage.receiveEnergy((int) Math.round(getGenerationValue() * getGenerationFactor()), false);
	}

	public boolean canProduce() {
		if (worldObj.isDaytime() || worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord)|| !worldObj.isRaining() || !worldObj.isThundering() || dirtiness <= 800) {
			canProduce = true;
			return true;
		} else {
			canProduce = false;
			return false;
		}
	}

	public void transferEnergy() {
		ForgeDirection direction = ForgeDirection.getOrientation(getBlockMetadata() % 6);
		TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
		if (tile instanceof IEnergyHandler && !(tile instanceof TileSolarBase) && canProduce()) {
			IEnergyHandler energyHandler = (IEnergyHandler) tile;
			energyStorage.extractEnergy(energyHandler.receiveEnergy(direction.getOpposite(), energyStorage.getEnergyStored() > 500 ? 500 : energyStorage.getEnergyStored(), false), false);
		}
	}

	public void damageSolar() {
		if (canProduce() && worldObj.rand.nextInt(100) == 0) incrementDamage(1);

		if ((worldObj.isRaining() || worldObj.isThundering()) && worldObj.rand.nextInt(500) == 0) incrementDamage(5);
	}

	void incrementDamage(int amount) {
		if (dirtiness + amount >= getMaxDamage()) {
			dirtiness = getMaxDamage();
			return;
		}

		dirtiness += amount;
	}

	int getMaxDamage() {
		return 1000;
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		dirtiness = tagCompound.getInteger("dirtiness");
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("dirtiness", dirtiness);
	}

	public int getGenerationValue() {
		return 10;
	}

	public double getGenerationFactor() {
		return 10;
	}

	// Start RF API

	@Override
	public int receiveEnergy(ForgeDirection forgeDirection, int maxRecieve, boolean simulate) {
		return energyStorage.receiveEnergy(maxRecieve, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection forgeDirection, int maxExtract, boolean simulate) {
		return energyStorage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection forgeDirection) {
		return 0;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection forgeDirection) {
		return 10000;
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection forgeDirection) {
		return false;
	}
}
