package gg.vape.module.minigame;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventRender3D;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.minigame.bedplates.BedPlateBlockStateKey;
import gg.vape.module.minigame.bedplates.BedPlateCountState;
import gg.vape.module.render.hud.FreeLookHudModule;
import gg.vape.module.world.bedbreaker.BedTargetRenderPosition;
import gg.vape.ui.font.BufferedSmoothFontRenderer;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.BlockUtil;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.render.BufferedRenderPrimitives;
import gg.vape.utils.render.GlFramebuffer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ItemIconRenderer;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtil;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.BooleanValue;
import gg.vape.wrapper.impl.ActiveRenderInfo;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.BlockBed;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.BlockState;
import gg.vape.wrapper.impl.Chunk;
import gg.vape.wrapper.impl.ChunkSection;
import gg.vape.wrapper.impl.ClientChunkProvider;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EnumFacing;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RenderManager;
import gg.vape.wrapper.impl.Timer;
import gg.vape.wrapper.impl.World;
import gg.vape.wrapper.impl.WorldClient;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.lwjgl.opengl.GL11;

public class BedPlates
extends Mod {
    private World C;
    private BooleanValue a = BooleanValue.create(this, "Show distance", true);
    private List<BedTargetRenderPosition> k = new CopyOnWriteArrayList<BedTargetRenderPosition>();
    private static final long p = 8840478352411230313L;
    private HashMap<BedTargetRenderPosition, BedPlateCountState> I = new HashMap();
    int A = 0;
    private TimerUtil U = new TimerUtil();

    private static Exception a(Exception exception) {
        return exception;
    }

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        Object object;
        WorldClient worldClient = eventRender3D.getWorld();
        if (worldClient.isNull()) {
            return;
        }
        if (!worldClient.equals(this.C)) {
            this.k.clear();
            this.I.clear();
            return;
        }
        double d = eventRender3D.getThePlayer().z();
        double d2 = eventRender3D.getThePlayer().h();
        for (BedTargetRenderPosition object2 : this.k) {
            object = worldClient.getBlockByPos(object2.N(), object2.h(), object2.D$src$I$nuyd86());
            if (BlockUtil.f((Block)object) && !(MathUtil.Z(d, 0.0, d2, object2.N(), 0.0, object2.D$src$I$nuyd86()) > 100.0)) continue;
            this.k.remove(object2);
            this.I.remove(object2);
        }
        try {
            if (this.U.hasTimeElapsed(500L)) {
                this.U.reset();
                this.W();
            }
            if (GuiRenderPrimitives.d()) {
                for (Map.Entry entry : this.I.entrySet()) {
                    object = (BedPlateCountState)entry.getValue();
                    this.P(eventRender3D, (BedPlateCountState)object);
                }
            } else {
                Object object2;
                int n;
                for (Map.Entry entry : this.I.entrySet()) {
                    object = (BedPlateCountState)entry.getValue();
                    for (n = 1; n < 4; ++n) {
                        List<BedPlateBlockStateKey> list = ((BedPlateCountState)object).c(n);
                        Iterator<BedPlateBlockStateKey> iterator = list.iterator();
                        while (iterator.hasNext()) {
                            object2 = iterator.next();
                            ItemIconRenderer.k(((BedPlateBlockStateKey)object2).i, ((BedPlateBlockStateKey)object2).f);
                        }
                    }
                }
                GlFramebuffer.E = true;
                GuiRenderPrimitives.U = true;
                int n2 = GL11.glGetInteger((int)((int)p));
                boolean bl = GL11.glIsEnabled((int)2884);
                boolean bl2 = GL11.glIsEnabled((int)3042);
                n = GL11.glIsEnabled((int)2896) ? 1 : 0;
                eventRender3D.getEntityRenderer().B(1.0);
                if (n != 0) {
                    GlStateManager.disableLighting();
                }
                GlStateManager.depthMask(false);
                GlStateManager.disableDepth();
                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                if (bl) {
                    GlStateManager.Y();
                }
                if (!bl2) {
                    GlStateManager.enableBlend();
                }
                GuiRenderPrimitives.G.f();
                RenderUtils.g();
                for (Map.Entry entry : this.I.entrySet()) {
                    object2 = (BedPlateCountState)entry.getValue();
                    this.P(eventRender3D, (BedPlateCountState)object2);
                }
                RenderUtils.f();
                GuiRenderPrimitives.G.L();
                GlStateManager.enableTexture2D();
                GlStateManager.enableDepth();
                GlStateManager.depthMask(true);
                if (n != 0) {
                    GlStateManager.enableLighting();
                }
                GlStateManager.disableBlend();
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                eventRender3D.getEntityRenderer().O(1.0);
                if (!bl2) {
                    GlStateManager.disableBlend();
                }
                if (bl) {
                    GlStateManager.L();
                }
                GuiRenderPrimitives.U = false;
                GlFramebuffer.E = false;
                GlStateManager.bindTexture(n2);
            }
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    @Override
    public void q() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return;
        }
        WorldClient worldClient = Minecraft.theWorld();
        if (worldClient.isNull()) {
            return;
        }
        int n = (int)entityPlayerSP.z();
        int n2 = (int)entityPlayerSP.N();
        int n3 = (int)entityPlayerSP.h();
        if (worldClient.isNull() || !worldClient.equals(this.C)) {
            this.k.clear();
            this.I.clear();
        }
        this.C = worldClient;
        if (ForgeVersion.MC_1_8_9.A()) {
            int n4 = 100;
            for (int i = -n4; i < n4; ++i) {
                for (int j = -n4; j < n4; ++j) {
                    int n5 = 0;
                    while (n5 != -1) {
                        n5 = n5 == 0 ? 1 : -1;
                        for (int k = 0; k < 20; ++k) {
                            if (entityPlayerSP.isNull() || worldClient.isNull()) {
                                return;
                            }
                            int n6 = n + i;
                            int n7 = k * n5;
                            int n8 = n2 + n7;
                            int n9 = n3 + j;
                            Block block = worldClient.getBlockByPos(n6, n8, n9);
                            if (!BlockUtil.f(block)) continue;
                            BedTargetRenderPosition bedTargetRenderPosition = new BedTargetRenderPosition(n6, n8, n9);
                            BlockBed blockBed = new BlockBed(block);
                            boolean bl = blockBed.f(worldClient, n6, n8, n9);
                            if (this.k.contains(bedTargetRenderPosition) || bl) continue;
                            this.k.add(bedTargetRenderPosition);
                        }
                    }
                }
            }
        }
    }

    private void c() {
        WorldClient worldClient = Minecraft.theWorld();
        ClientChunkProvider clientChunkProvider = worldClient.U();
        List<Chunk> list = clientChunkProvider.L();
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        double d = entityPlayerSP.z();
        double d2 = entityPlayerSP.N();
        double d3 = entityPlayerSP.h();
        for (Chunk chunk : list) {
            List<ChunkSection> list2 = chunk.U();
            for (ChunkSection chunkSection : list2) {
                int n;
                if (!chunkSection.isNotNull()) continue;
                char[] cArray = chunkSection.C();
                int n2 = chunkSection.l();
                int n3 = chunk.a();
                int n4 = (int)MathUtil.Z(d, 0.0, d3, n3 << 4, 0.0, (n = chunk.j()) << 4);
                if (n4 > 100) continue;
                this.A(cArray, n3, n2, n);
            }
        }
    }

    private void P(EventRender3D eventRender3D, BedPlateCountState bedPlateCountState) {
        Object object;
        Object object2;
        float f;
        EntityPlayerSP entityPlayerSP = eventRender3D.getThePlayer();
        double d = RenderManager.getInterpolatedRenderPosX();
        double d2 = RenderManager.getInterpolatedRenderPosY();
        double d3 = RenderManager.getInterpolatedRenderPosZ();
        Timer timer = Minecraft.getTimer();
        float f2 = timer.renderPartialTicks();
        double d4 = (double)bedPlateCountState.l().N() - d + 0.5;
        double d5 = (double)bedPlateCountState.l().h() - d2;
        double d6 = (double)bedPlateCountState.l().D$src$I$nuyd86() - d3 + 0.5;
        double d7 = entityPlayerSP.M() + (entityPlayerSP.z() - entityPlayerSP.M()) * (double)f2 - d;
        double d8 = entityPlayerSP.W() + (entityPlayerSP.N() - entityPlayerSP.W()) * (double)f2 - d2;
        double d9 = entityPlayerSP.m$src$D$fwnne5() + (entityPlayerSP.h() - entityPlayerSP.m$src$D$fwnne5()) * (double)f2 - d3;
        Color color = new Color(0, 0, 0, 170);
        double d10 = RotationUtil.y(d4, d5, d6, d7, d8, d9);
        double d11 = d10 / 5.0;
        double d12 = 0.01666666753590107 * (d11 * 0.3 * 3.0);
        RenderUtil.d();
        float f3 = FreeLookHudModule.z() ? FreeLookHudModule.w$src$F$1kb9hl5() : eventRender3D.getRenderManager().getPlayerViewX();
        float f4 = f = FreeLookHudModule.z() ? FreeLookHudModule.c() : eventRender3D.getRenderManager().getPlayerViewY();
        if (ForgeVersion.MC_1_16_5.d()) {
            if (Minecraft.gameSettings().x() == 0) {
                OpenGlBackendHolder.d.I(d4 + 0.0, d5 + 1.0, d6);
                OpenGlBackendHolder.d.F(0.0f, 1.0f, 0.0f);
                OpenGlBackendHolder.d.X(-f3, 0.0f, 1.0f, 0.0f);
                OpenGlBackendHolder.d.X(-f, -1.0f, 0.0f, 0.0f);
            } else {
                object2 = Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().l();
                double d13 = GuiRenderPrimitives.d() ? 0.0 : RenderManager.getInterpolatedRenderPosX() - ((ActiveRenderInfo)object2).o().getX();
                double d14 = GuiRenderPrimitives.d() ? 0.0 : RenderManager.getInterpolatedRenderPosY() - ((ActiveRenderInfo)object2).o().getY();
                double d15 = GuiRenderPrimitives.d() ? 0.0 : RenderManager.getInterpolatedRenderPosZ() - ((ActiveRenderInfo)object2).o().getZ();
                OpenGlBackendHolder.d.I(d4 + d13, d5 + d14 + 1.0, d6 + d15);
                OpenGlBackendHolder.d.F(0.0f, 1.0f, 0.0f);
                OpenGlBackendHolder.d.X(-f3, 0.0f, 1.0f, 0.0f);
                OpenGlBackendHolder.d.X(f, 1.0f, 0.0f, 0.0f);
            }
        } else {
            OpenGlBackendHolder.d.I(d4 + 0.0, d5 + 1.0, d6);
            OpenGlBackendHolder.d.F(0.0f, 1.0f, 0.0f);
            if (Minecraft.gameSettings().x() == 2) {
                OpenGlBackendHolder.d.X(-f3, 0.0f, 1.0f, 0.0f);
                OpenGlBackendHolder.d.X(f, -1.0f, 0.0f, 0.0f);
            } else {
                OpenGlBackendHolder.d.X(-f3, 0.0f, 1.0f, 0.0f);
                OpenGlBackendHolder.d.X(f, 1.0f, 0.0f, 0.0f);
            }
        }
        OpenGlBackendHolder.d.G(-d12, -d12, d12);
        List<BedPlateBlockStateKey> visibleBlockStates = new ArrayList<BedPlateBlockStateKey>();
        for (int i = 1; i < 4; ++i) {
            List<BedPlateBlockStateKey> layer = bedPlateCountState.c(i);
            int n = 0;
            for (int j = 0; j < layer.size(); ++j) {
                BedPlateBlockStateKey bedPlateBlockStateKey = layer.get(j);
                if (bedPlateBlockStateKey.U()) continue;
                ++n;
                if (visibleBlockStates.contains(bedPlateBlockStateKey)) continue;
                visibleBlockStates.add(bedPlateBlockStateKey);
            }
            if (n == 0) break;
        }
        String string = (int)d10 + "m";
        object = Vape.INSTANCE.getFontManager().W(1.2, false);
        int n = Math.max(visibleBlockStates.size(), 1);
        float f5 = 22.0f;
        float f6 = n * 18 + 6;
        if (this.a.L().booleanValue()) {
            f6 = (float)Math.max(((SmoothFontRenderer)object).N(string) + 10.0, (double)f6);
        }
        float f7 = -f6 / 2.0f;
        float f8 = -f5;
        if (this.a.L().booleanValue()) {
            f8 -= 8.0f;
            f5 += 8.0f;
        }
        if (GuiRenderPrimitives.d()) {
            boolean bl = visibleBlockStates.isEmpty();
            float f9 = this.a.L() != false ? 4.0f : 0.0f;
            float f10 = bl ? 0.8f : 0.0f;
            BufferedRenderPrimitives.P(f7, f8 - 1.0f, f6, f5 + 1.0f, 12.0f, 6.0f, new Color(0, 0, 0, 150), 0.0f, 0.0f, 1.0f, new Color(45, 45, 45, 255), new Color(0, 0, 0, 170), f10, f9, new Color(51, 51, 51, 255), false);
            float f11 = f7 + 4.0f;
            float f12 = f8 + f5 - 20.0f;
            Iterator<BedPlateBlockStateKey> iterator = visibleBlockStates.iterator();
            while (iterator.hasNext()) {
                BedPlateBlockStateKey bedPlateBlockStateKey = iterator.next();
                if (bedPlateBlockStateKey.U()) continue;
                ItemStack itemStack = ItemStack.S(Item.T(bedPlateBlockStateKey.i));
                ItemIconRenderer.C(itemStack, f11, f12, 16, 16, 1.0f, true);
                f11 += 18.0f;
            }
            if (this.a.L().booleanValue()) {
                ((BufferedSmoothFontRenderer)object).B(string, f7 + f6 / 2.0f, f8 + 2.0f, -1, false, true);
            }
        } else {
            GuiRenderPrimitives.G.r(false);
            GuiRenderPrimitives.G.C(visibleBlockStates.isEmpty(), this.a.L());
            GuiRenderPrimitives.n(f7, (double)f8, (double)f6, (double)f5);
            GuiRenderPrimitives.G.r(true);
            float f13 = f7 + 4.0f;
            float f14 = f8 + f5 - 20.0f;
            Iterator<BedPlateBlockStateKey> iterator = visibleBlockStates.iterator();
            while (iterator.hasNext()) {
                BedPlateBlockStateKey bedPlateBlockStateKey = iterator.next();
                if (bedPlateBlockStateKey.U()) continue;
                ItemIconRenderer.m(bedPlateBlockStateKey.i, bedPlateBlockStateKey.f, f13, f14, 16, 16);
                f13 += 18.0f;
            }
            if (this.a.L().booleanValue()) {
                ((SmoothFontRenderer)object).L(string, f7 + f6 / 2.0f, f8 + 2.0f, -1);
            }
        }
        OpenGlBackendHolder.d.F();
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (ForgeVersion.MC_1_8_9.L() && Minecraft.theWorld().isNotNull() && this.A++ >= 20) {
            this.c();
            this.A = 0;
        }
    }

    private void W() {
        WorldClient worldClient = Minecraft.theWorld();
        for (BedTargetRenderPosition bedTargetRenderPosition : this.k) {
            if (!this.I.containsKey(bedTargetRenderPosition)) {
                this.I.put(bedTargetRenderPosition, new BedPlateCountState(bedTargetRenderPosition));
            }
            BedPlateCountState bedPlateCountState = this.I.get(bedTargetRenderPosition);
            bedPlateCountState.y();
            Block block = worldClient.getBlockByPos(bedTargetRenderPosition.N(), bedTargetRenderPosition.h(), bedTargetRenderPosition.D$src$I$nuyd86());
            BlockBed blockBed = new BlockBed(block);
            EnumFacing enumFacing = blockBed.e(worldClient, bedTargetRenderPosition.N(), bedTargetRenderPosition.h(), bedTargetRenderPosition.D$src$I$nuyd86());
            int n = enumFacing.Y();
            int n2 = 4;
            for (int i = 1; i < n2; ++i) {
                int n3 = -i;
                int n4 = -i;
                int n5 = i;
                int n6 = i;
                int n7 = 0;
                int n8 = 0;
                if (n == 2) {
                    ++n6;
                    ++n8;
                }
                if (n == 3) {
                    --n4;
                    --n8;
                }
                if (n == 4) {
                    ++n5;
                    ++n7;
                }
                if (n == 5) {
                    --n3;
                    --n7;
                }
                for (int j = 0; j <= i; ++j) {
                    for (int k = n3; k <= n5; ++k) {
                        for (int i2 = n4; i2 <= n6; ++i2) {
                            int n9;
                            int n10;
                            if (k != n3 && k != n5 && i2 != n4 && i2 != n6 && Math.abs(j) != Math.abs(i)) continue;
                            double d = RotationUtil.r(bedTargetRenderPosition.N(), bedTargetRenderPosition.D$src$I$nuyd86(), bedTargetRenderPosition.N() + k, bedTargetRenderPosition.D$src$I$nuyd86() + i2) + (double)j;
                            double d2 = RotationUtil.r(bedTargetRenderPosition.N() + n7, bedTargetRenderPosition.D$src$I$nuyd86() + n8, bedTargetRenderPosition.N() + k, bedTargetRenderPosition.D$src$I$nuyd86() + i2) + (double)j;
                            boolean bl = false;
                            if (d > (double)i && d2 > (double)i) {
                                bl = true;
                            }
                            Block block2 = worldClient.getBlockByPos(bedTargetRenderPosition.N() + k, bedTargetRenderPosition.h() + j, bedTargetRenderPosition.D$src$I$nuyd86() + i2);
                            if (ForgeVersion.MC_1_16_5.d()) {
                                BlockPos blockPos = BlockPos.create(bedTargetRenderPosition.N() + k, bedTargetRenderPosition.h() + j, bedTargetRenderPosition.D$src$I$nuyd86() + i2);
                                BlockState blockState = new BlockState(Vape.INSTANCE.getMappings().Cy.u(worldClient.getObject(), blockPos.getObject()));
                                n10 = block2.Z(worldClient, blockPos, blockState).getItem().P();
                            } else {
                                n10 = Block.R(block2);
                            }
                            int n11 = ForgeVersion.MC_1_16_5.d() ? -1 : block2.d(bedTargetRenderPosition.N() + k, bedTargetRenderPosition.h() + j, bedTargetRenderPosition.D$src$I$nuyd86() + i2);
                            int n12 = n9 = bl ? i + 1 : i;
                            if (n9 >= n2) continue;
                            BedPlateCountState.j(bedPlateCountState, n9, n10, n11);
                        }
                    }
                }
            }
            bedPlateCountState.r();
        }
    }

    public BedPlates() {
        super("BedPlates", new Color(245, 0, 37).getRGB(), Category.k, "Shows block types around beds");
        this.v(10L, true);
        this.addValue(this.a);
    }

    private void A(char[] cArray, int n, int n2, int n3) {
        for (int i = 0; i < cArray.length; ++i) {
            char c = cArray[i];
            int n4 = c >> 4;
            int n5 = c & 0xF;
            if (n4 != 26) continue;
            int n6 = i % 16;
            int n7 = i / 256 + n2;
            int n8 = i / 16 % 16;
            int n9 = (n << 4) + n6;
            int n10 = (n3 << 4) + n8;
            Block block = Minecraft.theWorld().getBlockByPos(n9, n7, n10);
            BedTargetRenderPosition bedTargetRenderPosition = new BedTargetRenderPosition(n9, n7, n10);
            BlockBed blockBed = new BlockBed(block);
            boolean bl = blockBed.f(Minecraft.theWorld(), n9, n7, n10);
            if (this.k.contains(bedTargetRenderPosition) || bl) continue;
            this.k.add(bedTargetRenderPosition);
            this.I.put(bedTargetRenderPosition, new BedPlateCountState(bedTargetRenderPosition));
        }
    }
}
