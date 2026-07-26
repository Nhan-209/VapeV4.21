package gg.vape.module.render;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPacketReceive;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventRender3D;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtil;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.RenderManager;
import gg.vape.wrapper.impl.SPacketBlockChange;
import gg.vape.wrapper.impl.Vec3i;
import java.awt.Color;
import java.util.concurrent.ConcurrentHashMap;
import org.lwjgl.opengl.GL11;

public class Explosions
extends Mod {
    private final ConcurrentHashMap<Object, Integer> K = new ConcurrentHashMap();
    private final RenderManager a;
    private final TimerUtil P = new TimerUtil();

    public Explosions() {
        super("PropHunt", 0, 15962879, Category.m, "Renders where all the hidden props are.");
        this.a = Minecraft.D();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @EventHandler
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
        SPacketBlockChange sPacketBlockChange;
        BlockPos blockPos;
        Packet packet = eventPacketReceive.getPacket();
        if (packet.isInstance(MappedClasses.DD) && (blockPos = (sPacketBlockChange = new SPacketBlockChange(packet.getObject())).B()).P() >= -30000000 && blockPos.d() >= -30000000 && blockPos.P() < 30000000 && blockPos.d() < 30000000 && blockPos.o() >= 0 && blockPos.o() < 256 && !this.K.containsKey(blockPos.getObject()) && this.K.size() < 1024) {
            this.K.put(blockPos.getObject(), 0);
        }
    }

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        double d;
        double d2;
        double d3;
        Wrapper wrapper;
        Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().B(1.0);
        RenderUtil.d();
        boolean bl = OpenGlBackendHolder.d.L(3042);
        if (bl) {
            double d4;
            double d5;
            double d6;
            Wrapper wrapper2;
            OpenGlBackendHolder.d.l(3042);
            GL11.glBlendFunc((int)770, (int)771);
            OpenGlBackendHolder.d.r(1.5f);
            OpenGlBackendHolder.d.u$src$V$hntn98(3553);
            OpenGlBackendHolder.d.l(2848);
            OpenGlBackendHolder.d.u$src$V$hntn98(2929);
            OpenGlBackendHolder.d.U(false);
            double d7 = RenderManager.getInterpolatedRenderPosX();
            double d8 = RenderManager.getInterpolatedRenderPosY();
            double d9 = RenderManager.getInterpolatedRenderPosZ();
            for (Object object : Minecraft.theWorld().z()) {
                wrapper2 = new Entity(object);
                if (!wrapper2.isInstance(MappedClasses.F2)) continue;
                d6 = ((Entity)wrapper2).M() + (((Entity)wrapper2).z() - ((Entity)wrapper2).M()) * (double)eventRender3D.getTicks();
                d5 = ((Entity)wrapper2).W() + (((Entity)wrapper2).N() - ((Entity)wrapper2).W()) * (double)eventRender3D.getTicks();
                d4 = ((Entity)wrapper2).m$src$D$fwnne5() + (((Entity)wrapper2).h() - ((Entity)wrapper2).m$src$D$fwnne5()) * (double)eventRender3D.getTicks();
                float f = ((Entity)wrapper2).b();
                AxisAlignedBB axisAlignedBB = ((Entity)wrapper2).R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
                AxisAlignedBB axisAlignedBB2 = axisAlignedBB.expand(f, f, f);
                Color color = new Color(0x23FFFFFF, true);
                RenderUtil.u(d6 - (axisAlignedBB2.getMinX() - axisAlignedBB2.getMaxX()) / 2.0, d5 + (axisAlignedBB2.getMinY() - ((Entity)wrapper2).N()), d4 - (axisAlignedBB2.getMinZ() - axisAlignedBB2.getMaxZ()) / 2.0, axisAlignedBB2.getMinZ() - axisAlignedBB2.getMaxZ(), axisAlignedBB2.getMaxY() - axisAlignedBB2.getMinY(), axisAlignedBB2.getMinX() - axisAlignedBB2.getMaxX(), 0.1, Color.WHITE, color, d7, d8, d9);
            }
            for (Object object : this.K.keySet()) {
                wrapper2 = new BlockPos(object);
                d6 = ((Vec3i)wrapper2).P();
                d5 = ((Vec3i)wrapper2).o();
                d4 = ((Vec3i)wrapper2).d();
                Color color = new Color(603914752, true);
                RenderUtil.u(d6, d5, d4, 1.0, 1.0, 1.0, 0.1, Color.RED, color, d7, d8, d9);
            }
            OpenGlBackendHolder.d.U(true);
            OpenGlBackendHolder.d.l(2929);
            OpenGlBackendHolder.d.l(3553);
            OpenGlBackendHolder.d.u$src$V$hntn98(2848);
            RenderUtil.Y();
            Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().O(1.0);
            return;
        }
        OpenGlBackendHolder.d.l(3042);
        OpenGlBackendHolder.d.l(3042);
        GL11.glBlendFunc((int)770, (int)771);
        OpenGlBackendHolder.d.r(1.5f);
        OpenGlBackendHolder.d.u$src$V$hntn98(3553);
        OpenGlBackendHolder.d.l(2848);
        OpenGlBackendHolder.d.u$src$V$hntn98(2929);
        OpenGlBackendHolder.d.U(false);
        double d10 = RenderManager.getInterpolatedRenderPosX();
        double d11 = RenderManager.getInterpolatedRenderPosY();
        double d12 = RenderManager.getInterpolatedRenderPosZ();
        for (Object object : Minecraft.theWorld().z()) {
            wrapper = new Entity(object);
            if (!wrapper.isInstance(MappedClasses.F2)) continue;
            d3 = ((Entity)wrapper).M() + (((Entity)wrapper).z() - ((Entity)wrapper).M()) * (double)eventRender3D.getTicks();
            d2 = ((Entity)wrapper).W() + (((Entity)wrapper).N() - ((Entity)wrapper).W()) * (double)eventRender3D.getTicks();
            d = ((Entity)wrapper).m$src$D$fwnne5() + (((Entity)wrapper).h() - ((Entity)wrapper).m$src$D$fwnne5()) * (double)eventRender3D.getTicks();
            float f = ((Entity)wrapper).b();
            AxisAlignedBB axisAlignedBB = ((Entity)wrapper).R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
            AxisAlignedBB axisAlignedBB3 = axisAlignedBB.expand(f, f, f);
            Color color = new Color(0x23FFFFFF, true);
            RenderUtil.u(d3 - (axisAlignedBB3.getMinX() - axisAlignedBB3.getMaxX()) / 2.0, d2 + (axisAlignedBB3.getMinY() - ((Entity)wrapper).N()), d - (axisAlignedBB3.getMinZ() - axisAlignedBB3.getMaxZ()) / 2.0, axisAlignedBB3.getMinZ() - axisAlignedBB3.getMaxZ(), axisAlignedBB3.getMaxY() - axisAlignedBB3.getMinY(), axisAlignedBB3.getMinX() - axisAlignedBB3.getMaxX(), 0.1, Color.WHITE, color, d10, d11, d12);
        }
        for (Object object : this.K.keySet()) {
            wrapper = new BlockPos(object);
            d3 = ((Vec3i)wrapper).P();
            d2 = ((Vec3i)wrapper).o();
            d = ((Vec3i)wrapper).d();
            Color color = new Color(603914752, true);
            RenderUtil.u(d3, d2, d, 1.0, 1.0, 1.0, 0.1, Color.RED, color, d10, d11, d12);
        }
        OpenGlBackendHolder.d.U(true);
        OpenGlBackendHolder.d.l(2929);
        OpenGlBackendHolder.d.l(3553);
        OpenGlBackendHolder.d.u$src$V$hntn98(2848);
        OpenGlBackendHolder.d.u$src$V$hntn98(3042);
        RenderUtil.Y();
        Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().O(1.0);
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        for (Object k : this.K.keySet()) {
            Integer n = this.K.get(k);
            if (n >= 200) {
                this.K.remove(k);
                continue;
            }
            this.K.put(k, n + 1);
        }
    }
}

