package gg.vape.utils.render;

import gg.vape.Vape;
import gg.vape.unmap.ImageParser;
import gg.vape.unmap.ImageParser$Format;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.TextureAtlasRegion;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;

public class TextureAtlas {
    private int s = 128;
    private final Map<String, TextureAtlasRegion> A;
    private final int f;
    private int Q = 128;
    private final List<Rectangle> v;
    private BufferedImage q = new BufferedImage(this.s, this.Q, 2);
    private GlImageTexture Z;
    private final int M;

    private BufferedImage E(BufferedImage bufferedImage) {
        int n;
        int n2;
        int n3;
        int n4;
        int n5 = bufferedImage.getWidth() + 20;
        int n6 = bufferedImage.getHeight() + 20;
        BufferedImage bufferedImage2 = new BufferedImage(n5, n6, 2);
        Graphics2D graphics2D = bufferedImage2.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.drawImage((Image)bufferedImage, 10, 10, null);
        for (n4 = 0; n4 < bufferedImage.getWidth(); ++n4) {
            n3 = bufferedImage.getRGB(n4, 0);
            for (n2 = 0; n2 < 10; ++n2) {
                bufferedImage2.setRGB(n4 + 10, n2, n3);
            }
            n2 = bufferedImage.getRGB(n4, bufferedImage.getHeight() - 1);
            for (n = 0; n < 10; ++n) {
                bufferedImage2.setRGB(n4 + 10, n6 - 1 - n, n2);
            }
        }
        for (n4 = 0; n4 < bufferedImage.getHeight(); ++n4) {
            n3 = bufferedImage.getRGB(0, n4);
            for (n2 = 0; n2 < 10; ++n2) {
                bufferedImage2.setRGB(n2, n4 + 10, n3);
            }
            n2 = bufferedImage.getRGB(bufferedImage.getWidth() - 1, n4);
            for (n = 0; n < 10; ++n) {
                bufferedImage2.setRGB(n5 - 1 - n, n4 + 10, n2);
            }
        }
        graphics2D.dispose();
        return bufferedImage2;
    }

    private static int S(int n) {
        int n2;
        for (n2 = 1; n2 < n; n2 *= 2) {
        }
        return n2;
    }

    private int[] i(int n, int n2) {
        int n3 = n + 10;
        int n4 = n2 + 10;
        for (int i = 0; i <= this.Q - n4; ++i) {
            for (int j = 0; j <= this.s - n3; ++j) {
                Rectangle rectangle = new Rectangle(j, i, n3, n4);
                boolean bl = true;
                for (Rectangle rectangle2 : this.v) {
                    if (!rectangle.intersects(rectangle2)) continue;
                    bl = false;
                    break;
                }
                if (!bl) continue;
                this.v.add(rectangle);
                return new int[]{j, i};
            }
        }
        return null;
    }

    private void y() {
        if (this.Z != null) {
            this.Z.O();
        }
        int[] nArray = new int[this.q.getHeight() * this.q.getWidth()];
        this.q.getRGB(0, 0, this.q.getWidth(), this.q.getHeight(), nArray, 0, this.q.getWidth());
        ByteBuffer byteBuffer = BufferUtils.createByteBuffer((int)(this.q.getWidth() * this.q.getHeight() * 4));
        for (int i = 0; i < this.q.getHeight(); ++i) {
            for (int j = 0; j < this.q.getWidth(); ++j) {
                int n = nArray[i * this.q.getWidth() + j];
                byteBuffer.put((byte)(n >> 16 & 0xFF));
                byteBuffer.put((byte)(n >> 8 & 0xFF));
                byteBuffer.put((byte)(n & 0xFF));
                byteBuffer.put((byte)(n >> 24 & 0xFF));
            }
        }
        byteBuffer.flip();
        this.Z = GlImageTexture.J(this.s, this.Q, byteBuffer, 6408, 9987, 10496);
        byteBuffer.clear();
    }

    public GlImageTexture d() {
        return this.Z;
    }

    public TextureAtlas() {
        this.f = 128;
        this.M = 10;
        this.A = new HashMap<String, TextureAtlasRegion>();
        this.v = new ArrayList<Rectangle>();
    }

    private void s() {
        int n = TextureAtlas.S(this.s + 128);
        int n2 = TextureAtlas.S(this.Q + 128);
        BufferedImage bufferedImage = new BufferedImage(n, n2, 2);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage((Image)this.q, 0, 0, null);
        graphics2D.dispose();
        this.q = bufferedImage;
        this.s = n;
        this.Q = n2;
        for (TextureAtlasRegion textureAtlasRegion : this.A.values()) {
            textureAtlasRegion.d = (float)textureAtlasRegion.N / (float)this.s;
            textureAtlasRegion.s = (float)textureAtlasRegion.p / (float)this.Q;
            textureAtlasRegion.X = (float)(textureAtlasRegion.N + textureAtlasRegion.q) / (float)this.s;
            textureAtlasRegion.n = (float)(textureAtlasRegion.p + textureAtlasRegion.g) / (float)this.Q;
        }
    }

    public void j(String string) {
        try {
            File file = new File(string);
            ImageIO.write((RenderedImage)this.q, "PNG", file);
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    public void P(String string, byte[] byArray, boolean bl) {
        try {
            if (this.A.containsKey(string)) {
                return;
            }
            ImageParser imageParser = new ImageParser(new ByteArrayInputStream(byArray));
            int n = imageParser.k();
            int n2 = imageParser.q();
            int n3 = 4;
            ByteBuffer byteBuffer = BufferUtils.createByteBuffer((int)(4 * n * n2));
            imageParser.i(byteBuffer, n2 * 4, bl ? ImageParser$Format.WHITE : ImageParser$Format.RGBA);
            byteBuffer.flip();
            byte[] byArray2 = new byte[byteBuffer.remaining()];
            byteBuffer.get(byArray2);
            BufferedImage bufferedImage = this.b(byArray2, n2, n);
            bufferedImage = this.E(bufferedImage);
            int[] nArray = this.i(n2 += 20, n += 20);
            while (nArray == null) {
                this.s();
                nArray = this.i(n2, n);
            }
            Graphics2D graphics2D = this.q.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics2D.drawImage((Image)bufferedImage, nArray[0], nArray[1], null);
            graphics2D.dispose();
            this.b(string, nArray[0] + 10, nArray[1] + 10, n2 - 20, n - 20);
            this.y();
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    private void b(String string, int n, int n2, int n3, int n4) {
        float f = (float)n / (float)this.s;
        float f2 = (float)n2 / (float)this.Q;
        float f3 = (float)(n + n3) / (float)this.s;
        float f4 = (float)(n2 + n4) / (float)this.Q;
        this.A.put(string, new TextureAtlasRegion(n, n2, n3, n4, f, f2, f3, f4));
    }

    public TextureAtlasRegion B(String string) {
        return this.A.get(string);
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    private BufferedImage b(byte[] byArray, int n, int n2) {
        if (byArray.length != n * n2 * 4) {
            throw new IllegalArgumentException("Unexpected image data length for decoded texture");
        }
        BufferedImage bufferedImage = new BufferedImage(n, n2, 2);
        int n3 = 0;
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n; ++j) {
                int n4 = byArray[n3++] & 0xFF;
                int n5 = byArray[n3++] & 0xFF;
                int n6 = byArray[n3++] & 0xFF;
                int n7 = byArray[n3++] & 0xFF;
                bufferedImage.setRGB(j, i, n7 << 24 | n4 << 16 | n5 << 8 | n6);
            }
        }
        return bufferedImage;
    }
}

