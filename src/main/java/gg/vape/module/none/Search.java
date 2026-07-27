package gg.vape.module.none;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventRender3D;
import gg.vape.event.impl.EventRenderTracers3D;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.none.search.SearchBlockChunkScanner;
import gg.vape.module.none.search.SearchBlockRenderEntry;
import gg.vape.rotation.LocalPlayerRotationUtil;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.unmap.SearchBlock;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.Vec3d;
import gg.vape.utils.datas.SearchResultData;
import gg.vape.utils.datas.SearchResultDataPool;
import gg.vape.utils.processors.SearchProcessor;
import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.BufferedRenderPrimitives;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderBatchState;
import gg.vape.utils.render.RenderUtil;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.BooleanValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.ActiveRenderInfo;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RenderManager;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.lwjgl.opengl.GL11;

public class Search
extends Mod {
    private SearchProcessor primaryProcessor;
    private static final long MODULE_HASH = -323757894669395457L;
    TimerUtil I;
    private final NumberValue range;
    TimerUtil P;
    private final NumberValue dummyRange;
    ArrayList<SearchBlockRenderEntry> Z;
    private final List<SearchBlock> searchBlocks = new ArrayList<SearchBlock>();
    private final BooleanValue onlyCaves;
    private final List<SearchResultData> searchResults;
    private final BooleanValue useTracers;
    private SearchProcessor secondaryProcessor;

    @Override
    public void onEnable() {
        if (ForgeVersion.MC_1_8_9.A()) {
            this.primaryProcessor = new SearchProcessor(this.searchResults, this.searchBlocks, this.range);
            this.secondaryProcessor = new SearchProcessor(this.searchResults, this.searchBlocks, this.dummyRange);
            this.primaryProcessor.X(this.onlyCaves.L());
            this.secondaryProcessor.X(this.onlyCaves.L());
            this.primaryProcessor.n();
            this.secondaryProcessor.n();
        }
    }

    private void updateSearchResults() {
        if (ForgeVersion.MC_1_8_9.L() && this.Z != null) {
            if (Minecraft.theWorld().isNull()) {
                return;
            }
            this.searchResults.clear();
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            for (SearchBlockRenderEntry object : this.Z) {
                SearchBlock searchBlock = this.j(object.R(), object.k());
                if (searchBlock != null) {
                    int n = object.i();
                    int n2 = object.f();
                    int n3 = object.N();
                    int n4 = (int)RotationUtil.p(entityPlayerSP, n, n2, n3);
                    SearchResultData searchResultData = SearchResultDataPool.n(n, n2, n3, object.R(), searchBlock, searchBlock.I$src$Ljava_util_concurrent_atomic_AtomicBoolean_$10pq3bz(), n4);
                    if ((double)n4 < (Double)this.range.K() + 10.0) {
                        this.searchResults.add(searchResultData);
                    } else {
                        SearchResultDataPool.h(searchResultData);
                    }
                }
                SearchBlockChunkScanner.q(object);
            }
            for (SearchResultData searchResultData : this.searchResults) {
                SearchResultDataPool.h(searchResultData);
            }
            this.Z = null;
        }
    }

    public void T(SearchBlock searchBlock) {
        this.searchBlocks.add(searchBlock);
    }

    @Override
    public void q() {
        if (ForgeVersion.MC_1_8_9.L() && this.I.hasTimeElapsed(1000L)) {
            this.I.reset();
            if (Minecraft.theWorld().isNull() || Minecraft.thePlayer().isNull()) {
                return;
            }
            if (this.Z == null) {
                ArrayList<SearchBlock> arrayList = new ArrayList<SearchBlock>(this.searchBlocks);
                this.Z = SearchBlockChunkScanner.f(arrayList, ((Double)this.range.K()).intValue(), this.onlyCaves.L());
            }
        }
    }

    @Override
    public void onDisable() {
        if (ForgeVersion.MC_1_8_9.A()) {
            this.primaryProcessor.w();
            this.secondaryProcessor.w();
        }
        this.searchResults.clear();
        RenderBatchState.r();
    }

    public SearchBlock j(int n, int n2) {
        for (SearchBlock searchBlock : this.searchBlocks) {
            if (!searchBlock.b(n, n2)) continue;
            return searchBlock;
        }
        return null;
    }

    public Search() {
        super("Search", (int)MODULE_HASH, Category.k, "Draws outline around selected blocks\nAdd blocks in Search frame");
        this.range = NumberValue.create((Object)this, "Range", "#", "", 1.0, 50.0, 100.0, 1.0);
        this.dummyRange = NumberValue.create((Object)this, "-", "-", "-", 5.0, 5.0, 5.0, 1.0);
        this.onlyCaves = BooleanValue.create(this, "Only caves", false, "Only looks for ores exposed to air");
        this.useTracers = BooleanValue.create(this, "Use tracers", false, "Add tracers to search blocks");
        this.I = new TimerUtil();
        this.P = new TimerUtil();
        this.searchResults = ForgeVersion.MC_1_8_9.L() ? new ArrayList<SearchResultData>() : new CopyOnWriteArrayList<SearchResultData>();
        this.addValue(this.range, this.onlyCaves, this.useTracers);
        this.v(50L, true);
        this.onlyCaves.B(this::onOnlyCavesChanged);
    }

    private void onOnlyCavesChanged(BooleanValue booleanValue) {
        if (this.primaryProcessor != null) {
            this.primaryProcessor.X(this.onlyCaves.L());
            this.secondaryProcessor.X(this.onlyCaves.L());
        }
    }

    public void u(SearchBlock searchBlock) {
        this.searchBlocks.remove(searchBlock);
        this.searchResults.clear();
    }

    @EventHandler
    public void onRender(EventRenderTracers3D eventRenderTracers3D) {
        if (!this.useTracers.L().booleanValue()) {
            return;
        }
        EntityPlayerSP entityPlayerSP = eventRenderTracers3D.getThePlayer();
        RenderUtil.d();
        RenderUtils.g();
        OpenGlBackendHolder.d.u$src$V$hntn98(2929);
        eventRenderTracers3D.getEntityRenderer().B(0.0);
        double d = RenderManager.getInterpolatedRenderPosX();
        double d2 = RenderManager.getInterpolatedRenderPosY();
        double d3 = RenderManager.getInterpolatedRenderPosZ();
        double d4 = ForgeVersion.MC_1_7_10.Y() ? (double)entityPlayerSP.X() : 0.0;
        for (SearchResultData searchResultData : this.searchResults) {
            if (!searchResultData.q() || searchResultData.N == 0 || !searchResultData.n()) continue;
            this.renderTracerLine(entityPlayerSP, searchResultData, searchResultData.O(), 2.0f, eventRenderTracers3D.getTicks(), d, d2, d3, d4, false);
        }
        OpenGlBackendHolder.d.b(1.0, 1.0f, 1.0f);
        eventRenderTracers3D.getEntityRenderer().O(0.0);
        OpenGlBackendHolder.d.l(2929);
        RenderUtils.f();
        OpenGlBackendHolder.d.F();
    }

    private static ObfuscatedRuntimeException rethrow(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        if (this.P.hasTimeElapsed(50L)) {
            this.updateSearchResults();
            this.P.reset();
        }
        Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().B(1.0);
        RenderUtil.d();
        boolean bl = OpenGlBackendHolder.d.L(3042);
        if (bl) {
            GL11.glBlendFunc((int)770, (int)771);
            OpenGlBackendHolder.d.r(1.5f);
            OpenGlBackendHolder.d.u$src$V$hntn98(3553);
            OpenGlBackendHolder.d.l(2848);
            OpenGlBackendHolder.d.u$src$V$hntn98(2929);
            OpenGlBackendHolder.d.U(false);
            double d = RenderManager.getInterpolatedRenderPosX();
            double d2 = RenderManager.getInterpolatedRenderPosY();
            double d3 = RenderManager.getInterpolatedRenderPosZ();
            double d4 = 0.0;
            if (ForgeVersion.MC_1_16_5.d()) {
                d4 = Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().l().o().getY() - d2;
            }
            if (GuiRenderPrimitives.d()) {
                RenderBatchState renderBatchState = RenderBatchState.E();
                renderBatchState.f();
                double d5 = d2 + d4;
                for (SearchResultData searchResultData : this.searchResults) {
                    if (!searchResultData.q() || searchResultData.N == 0) continue;
                    Color color = searchResultData.O();
                    renderBatchState.D((float)((double)searchResultData.F - d), (float)((double)searchResultData.O - d5), (float)((double)searchResultData.D - d3), (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
                }
                LocalPlayerRotationUtil.Q(eventRender3D.getTicks());
                renderBatchState.K(BufferedGuiRenderPrimitives.k, BufferedGuiRenderPrimitives.l, BufferedGuiRenderPrimitives.X.c());
            } else {
                for (SearchResultData searchResultData : this.searchResults) {
                    if (!searchResultData.q() || searchResultData.N == 0) continue;
                    RenderUtil.m(searchResultData, d, d2 + d4, d3);
                }
            }
            OpenGlBackendHolder.d.U(true);
            OpenGlBackendHolder.d.l(2929);
            OpenGlBackendHolder.d.l(3553);
            OpenGlBackendHolder.d.u$src$V$hntn98(2848);
            OpenGlBackendHolder.d.F();
            Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().O(1.0);
            return;
        }
        OpenGlBackendHolder.d.l(3042);
        GL11.glBlendFunc((int)770, (int)771);
        OpenGlBackendHolder.d.r(1.5f);
        OpenGlBackendHolder.d.u$src$V$hntn98(3553);
        OpenGlBackendHolder.d.l(2848);
        OpenGlBackendHolder.d.u$src$V$hntn98(2929);
        OpenGlBackendHolder.d.U(false);
        double d = RenderManager.getInterpolatedRenderPosX();
        double d6 = RenderManager.getInterpolatedRenderPosY();
        double d7 = RenderManager.getInterpolatedRenderPosZ();
        double d8 = 0.0;
        if (ForgeVersion.MC_1_16_5.d()) {
            d8 = Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().l().o().getY() - d6;
        }
        if (GuiRenderPrimitives.d()) {
            RenderBatchState renderBatchState = RenderBatchState.E();
            renderBatchState.f();
            double d9 = d6 + d8;
            for (SearchResultData searchResultData : this.searchResults) {
                if (!searchResultData.q() || searchResultData.N == 0) continue;
                Color color = searchResultData.O();
                renderBatchState.D((float)((double)searchResultData.F - d), (float)((double)searchResultData.O - d9), (float)((double)searchResultData.D - d7), (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
            }
            LocalPlayerRotationUtil.Q(eventRender3D.getTicks());
            renderBatchState.K(BufferedGuiRenderPrimitives.k, BufferedGuiRenderPrimitives.l, BufferedGuiRenderPrimitives.X.c());
        } else {
            for (SearchResultData searchResultData : this.searchResults) {
                if (!searchResultData.q() || searchResultData.N == 0) continue;
                RenderUtil.m(searchResultData, d, d6 + d8, d7);
            }
        }
        OpenGlBackendHolder.d.U(true);
        OpenGlBackendHolder.d.l(2929);
        OpenGlBackendHolder.d.l(3553);
        OpenGlBackendHolder.d.u$src$V$hntn98(2848);
        OpenGlBackendHolder.d.u$src$V$hntn98(3042);
        OpenGlBackendHolder.d.F();
        Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().O(1.0);
    }

    private void renderTracerLine(EntityPlayerSP entityPlayerSP, SearchResultData searchResultData, Color color, float f, float f2, double d, double d2, double d3, double d4, boolean bl) {
        double d5 = (double)searchResultData.U() + 0.5 - d;
        double d6 = (double)searchResultData.D() + 0.5 - d2;
        double d7 = (double)searchResultData.Y() + 0.5 - d3;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!GuiRenderPrimitives.d()) {
            bl2 = GL11.glIsEnabled((int)3042);
            bl3 = GL11.glIsEnabled((int)2896);
            GL11.glBlendFunc((int)770, (int)771);
            if (!bl2) {
                OpenGlBackendHolder.d.l(3042);
            }
            if (bl3) {
                OpenGlBackendHolder.d.u$src$V$hntn98(2896);
            }
            GL11.glBlendFunc((int)770, (int)771);
            OpenGlBackendHolder.d.l(2848);
            OpenGlBackendHolder.d.u$src$V$hntn98(3553);
        }
        double d8 = 0.0;
        double d9 = 0.0;
        if (ForgeVersion.MC_1_12_2.d()) {
            Vec3d vec3d = new Vec3d(0.0, 0.0, 1.0);
            if (ForgeVersion.MC_1_16_5.d()) {
                vec3d.k((float)(-Math.toRadians(Minecraft.D().getPlayerViewY())));
                vec3d.Y((float)(-Math.toRadians(Minecraft.D().getPlayerViewX())));
            } else {
                vec3d.k((float)(-Math.toRadians(entityPlayerSP.V())));
                vec3d.Y((float)(-Math.toRadians(entityPlayerSP.J())));
            }
            d8 = vec3d.Y();
            d4 += ForgeVersion.MC_1_16_5.d() ? vec3d.t() - (double)entityPlayerSP.X() : vec3d.t();
            d9 = vec3d.o();
            if (ForgeVersion.MC_1_16_5.d() && Minecraft.gameSettings().x() != 0) {
                ActiveRenderInfo activeRenderInfo = Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().l();
                double d10 = RenderManager.getInterpolatedRenderPosX() - activeRenderInfo.o().getX();
                double d11 = RenderManager.getInterpolatedRenderPosY() - activeRenderInfo.o().getY();
                double d12 = RenderManager.getInterpolatedRenderPosZ() - activeRenderInfo.o().getZ();
                d5 += d10;
                d6 += d11;
                d7 += d12;
            }
        }
        if (GuiRenderPrimitives.d()) {
            if (bl) {
                BufferedRenderPrimitives.Q(d8, d4, d9, d5, d6, d7, f + f * 0.5f, Color.black);
            }
            BufferedRenderPrimitives.Q(d8, d4, d9, d5, d6, d7, f, color);
        } else {
            if (bl) {
                GL11.glLineWidth((float)(f + f * 0.5f));
                GL11.glBegin((int)1);
                RenderUtils.w(Color.black);
                GL11.glVertex3d((double)d8, (double)d4, (double)d9);
                GL11.glVertex3d((double)d5, (double)d6, (double)d7);
                GL11.glEnd();
            }
            GL11.glLineWidth((float)f);
            GL11.glBegin((int)1);
            RenderUtils.w(color);
            GL11.glVertex3d((double)d8, (double)d4, (double)d9);
            GL11.glVertex3d((double)d5, (double)d6, (double)d7);
            GL11.glEnd();
        }
        if (!GuiRenderPrimitives.d()) {
            if (bl3) {
                OpenGlBackendHolder.d.l(2896);
            }
            if (!bl2) {
                OpenGlBackendHolder.d.u$src$V$hntn98(3042);
            }
            OpenGlBackendHolder.d.l(3553);
            OpenGlBackendHolder.d.u$src$V$hntn98(2848);
        }
    }
}

