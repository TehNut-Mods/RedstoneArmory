package codechicken.lib.render;

import codechicken.lib.render.SpriteSheetManager.SpriteSheet;
import codechicken.lib.render.TextureUtils.IIconSelfRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;

import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public class TextureSpecial extends TextureAtlasSprite implements IIconSelfRegister {
    //sprite sheet fields
    private int spriteIndex;
    private SpriteSheet spriteSheet;

    //textureFX fields
    private TextureFX textureFX;

    private int blankSize = -1;
    private ArrayList<TextureDataHolder> baseTextures;

    private boolean selfRegister;
    public int atlasIndex;

    protected TextureSpecial(String par1) {
        super(par1);
    }

    public TextureSpecial addTexture(TextureDataHolder t) {
        if (baseTextures == null)
            baseTextures = new ArrayList<TextureDataHolder>();
        baseTextures.add(t);
        return this;
    }

    public TextureSpecial baseFromSheet(SpriteSheet spriteSheet, int spriteIndex) {
        this.spriteSheet = spriteSheet;
        this.spriteIndex = spriteIndex;
        return this;
    }

    public TextureSpecial addTextureFX(TextureFX fx) {
        textureFX = fx;
        return this;
    }

    @Override
    public boolean hasAnimationMetadata() {
        return textureFX != null || super.hasAnimationMetadata();
    }

    @Override
    public int getFrameCount() {
        if (textureFX != null)
            return 1;

        return super.getFrameCount();
    }

    public TextureSpecial blank(int size) {
        blankSize = size;
        return this;
    }

    public TextureSpecial selfRegister() {
        selfRegister = true;
        TextureUtils.addIconRegistrar(this);
        return this;
    }

    @Override
    public void registerIcons(IconRegister register) {
        if (selfRegister)
            ((TextureMap) register).setTextureEntry(getIconName(), this);
    }

    @Override
    public int atlasIndex() {
        return atlasIndex;
    }
}
