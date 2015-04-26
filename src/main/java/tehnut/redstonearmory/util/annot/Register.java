package tehnut.redstonearmory.util.annot;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Shamelessly borrowed from <a href="https://github.com/hilburn/HilburnLib">HilburnLib</a>.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Register {

    public String name() default "";

    public String unlocalizedName() default "";

    public String dependency() default "";

    public String enabled() default "true";

    public Class<? extends TileEntity> tileEntity() default TileEntity.class;

    @SideOnly(Side.CLIENT)
    public Class<? extends TileEntitySpecialRenderer> TESR() default TileEntitySpecialRenderer.class;

    @SideOnly(Side.CLIENT)
    public Class<IItemRenderer> IItemRenderer() default IItemRenderer.class;

    @SideOnly(Side.CLIENT)
    public Class<ISimpleBlockRenderingHandler> SBRH() default ISimpleBlockRenderingHandler.class;

    public Class<? extends ItemBlock> itemBlock() default ItemBlock.class;
}
