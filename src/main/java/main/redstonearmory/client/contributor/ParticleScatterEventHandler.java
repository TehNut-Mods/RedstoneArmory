package main.redstonearmory.client.contributor;

import main.redstonearmory.RedstoneArmory;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleScatterEventHandler {

	private ContributorTrailManager contributorTrailManager;

	public ParticleScatterEventHandler() {
		this.contributorTrailManager = ContributorTrailManager.getInstance();
	}

	@SubscribeEvent
	public void playerTick(TickEvent.PlayerTickEvent event) {
		if (event.side == Side.CLIENT && event.phase == Phase.START) {
			EntityPlayer player = event.player;
			World world = Minecraft.getMinecraft().theWorld;

			if (contributorTrailManager.hasTrail(player.getUniqueID())) {
				if (player.posX != player.prevPosX || player.posZ != player.prevPosZ) {
					double dx = player.posX + 0.3F - (0.6F * world.rand.nextFloat());
					double dy = player.posY - 2;
					double dz = player.posZ + 0.3F - (0.6F * world.rand.nextFloat());

					if (player != Minecraft.getMinecraft().thePlayer)
						dy += 1;

					int x = MathHelper.floor_double(dx);
					int y = MathHelper.floor_double(dy);
					int z = MathHelper.floor_double(dz);

					if (world.getBlock(x, y, z).isBlockSolid(world, x, y, z, 0)) {
						RedstoneArmory.proxy.spawnParticle("particlescatter", dx, y + 1.01D, dz, ParticleRegistry.trailMap.get(contributorTrailManager.getTrailType(player.getUniqueID())));
					}
				}
			}
		}
	}
}
