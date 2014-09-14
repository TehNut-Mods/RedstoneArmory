package main.redstonearmory.proxies;

import cpw.mods.fml.common.FMLCommonHandler;
import main.redstonearmory.client.contributor.EntityContributorParticleFX;
import main.redstonearmory.util.KeyHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.util.IIcon;

public class ClientProxy extends CommonProxy {

	public static Minecraft minecraft = Minecraft.getMinecraft();

    @Override
    public void load() {
        FMLCommonHandler.instance().bus().register(new KeyHandler());
    }

	@Override
	public void spawnParticle(String string, double x, double y, double z, Object... args) {

		EntityFX entityfx = null;

		if (string == "particlescatter") {
			entityfx = new EntityContributorParticleFX(minecraft.theWorld, x, y, z, (IIcon)args[0]);
		}

		minecraft.effectRenderer.addEffect(entityfx);
	}
}
