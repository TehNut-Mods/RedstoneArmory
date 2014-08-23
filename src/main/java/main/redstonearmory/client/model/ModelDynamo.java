package main.redstonearmory.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDynamo extends ModelBase {
	//fields
	ModelRenderer dynamoBase;
	ModelRenderer redstoneCoil;

	public ModelDynamo() {
		textureWidth = 64;
		textureHeight = 32;

		dynamoBase = new ModelRenderer(this, 0, 0);
		dynamoBase.addBox(0F, 0F, 0F, 16, 10, 16);
		dynamoBase.setRotationPoint(-8F, 14F, -8F);
		dynamoBase.setTextureSize(64, 32);
		dynamoBase.mirror = true;
		setRotation(dynamoBase, 0F, 0F, 0F);
		redstoneCoil = new ModelRenderer(this, 0, 0);
		redstoneCoil.addBox(0F, 0F, 0F, 8, 6, 8);
		redstoneCoil.setRotationPoint(-4F, 8F, -4F);
		redstoneCoil.setTextureSize(64, 32);
		redstoneCoil.mirror = true;
		setRotation(redstoneCoil, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		dynamoBase.render(f5);
		redstoneCoil.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}