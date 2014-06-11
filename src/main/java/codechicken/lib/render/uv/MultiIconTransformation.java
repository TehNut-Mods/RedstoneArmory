package codechicken.lib.render.uv;

import codechicken.lib.vec.IrreversibleTransformationException;
import net.minecraft.util.Icon;

public class MultiIconTransformation extends UVTransformation {
    public Icon[] icons;

    public MultiIconTransformation(Icon... icons) {
        this.icons = icons;
    }

    @Override
    public void apply(UV uv) {
        Icon icon = icons[uv.tex % icons.length];
        uv.u = icon.getInterpolatedU(uv.u * 16);
        uv.v = icon.getInterpolatedV(uv.v * 16);
    }

    @Override
    public UVTransformation inverse() {
        throw new IrreversibleTransformationException(this);
    }
}
