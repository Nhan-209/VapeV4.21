package gg.vape.module.world;

import func.skidline.RectData;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventBedBreakerUpdate;
import gg.vape.event.impl.EventRender3D;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.world.bedbreaker.BedTargetRenderPosition;
import gg.vape.module.world.bedbreaker.BedTargetRenderState;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.utils.PlayerSimulationUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.Vec3d;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtils;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.BlockBed;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.EntityOtherPlayerMP;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EnumFacing;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.RayTraceResult_type;
import gg.vape.wrapper.impl.Vec3;
import gg.vape.wrapper.impl.World;
import gg.vape.wrapper.impl.WorldClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class BedBreaker
extends Mod {
    private World K;
    private BedTargetRenderState t;
    private static final long k = -5914606721811702784L;
    private List<BedTargetRenderPosition> C = new CopyOnWriteArrayList<BedTargetRenderPosition>();
    private HashMap<BedTargetRenderPosition, BedTargetRenderState> D = new HashMap();

    @Override
    public void q() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        WorldClient worldClient = Minecraft.theWorld();
        if (worldClient.isNull() || !worldClient.equals(this.K)) {
            this.C.clear();
        }
        int n = 100;
        for (int i = -n; i < n; ++i) {
            for (int j = -n; j < n; ++j) {
                int n2 = 0;
                while (n2 != -1) {
                    n2 = n2 == 0 ? 1 : -1;
                    for (int k = 0; k < 20; ++k) {
                        for (BedTargetRenderPosition bedTargetRenderPosition : this.C) {
                            if (!this.D.containsKey(bedTargetRenderPosition)) continue;
                            BedTargetRenderState.H(this.D.get(bedTargetRenderPosition));
                        }
                        if (entityPlayerSP.isNull() || worldClient.isNull()) {
                            return;
                        }
                        int n3 = k * n2;
                        int n4 = (int)entityPlayerSP.z() + i;
                        int n5 = (int)entityPlayerSP.N() + n3;
                        int n6 = (int)entityPlayerSP.h() + j;
                        Block block = worldClient.getBlockByPos(n4, n5, n6);
                        int n7 = Block.R(block);
                        String string = block.U();
                        if (n7 != 26 && (string == null || !string.matches("block.minecraft.(.+_bed)"))) continue;
                        BedTargetRenderPosition bedTargetRenderPosition = new BedTargetRenderPosition(n4, n5, n6);
                        BlockBed blockBed = new BlockBed(block);
                        boolean bl = blockBed.f(worldClient, n4, n5, n6);
                        if (this.C.contains(bedTargetRenderPosition) || bl) continue;
                        this.C.add(bedTargetRenderPosition);
                    }
                }
            }
        }
        this.K = worldClient;
    }

    public BedBreaker() {
        super("BedBreaker", (int)k, Category.m, "Allows you to break beds through walls\n\u00a7cWarning: This behavior is normally impossible and may be detected on servers");
    }

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        WorldClient worldClient = Minecraft.theWorld();
        ArrayList<BedTargetRenderState> arrayList = new ArrayList<BedTargetRenderState>();
        Vec3 vec3 = Minecraft.F().O(1.0f);
        for (BedTargetRenderPosition bedTargetRenderPosition : this.C) {
            boolean bl;
            int n;
            int n2;
            int n3 = bedTargetRenderPosition.N();
            Block block = worldClient.getBlockByPos(n3, n2 = bedTargetRenderPosition.h(), n = bedTargetRenderPosition.D$src$I$nuyd86());
            int n4 = Block.R(block);
            BlockBed blockBed = new BlockBed(block);
            if (n4 != 26 && !block.U().matches("block.minecraft.(.+_bed)") || (bl = blockBed.f(worldClient, n3, n2, n))) continue;
            BedTargetRenderState renderState;
            if (this.D.containsKey(bedTargetRenderPosition)) {
                renderState = this.D.get(bedTargetRenderPosition);
            } else {
                renderState = new BedTargetRenderState(bedTargetRenderPosition);
                this.D.put(bedTargetRenderPosition, renderState);
            }
            renderState.h();
            arrayList.add(renderState);
        }
        OpenGlBackendHolder.d.m();
        GuiRenderPrimitives.Y();
        RenderUtils.g();
        OpenGlBackendHolder.d.m();
        OpenGlBackendHolder.d.H(0.5f, 0.5f, 0.5f);
        double d = 20.0;
        RectData rectData = new RectData((double)(Minecraft.J() / 2) - d / 2.0, (double)(Minecraft.h() / 2) - d / 2.0, d, d);
        for (BedTargetRenderState bedTargetRenderState : arrayList) {
            bedTargetRenderState.j(rectData, this.t == bedTargetRenderState, Minecraft.playerController().c());
        }
        BedTargetRenderState selectedState = null;
        for (BedTargetRenderState candidateState : arrayList) {
            if (!BedTargetRenderState.f(candidateState)) continue;
            selectedState = candidateState;
        }
        this.u(selectedState);
        OpenGlBackendHolder.d.F();
        RenderUtils.f();
        GuiRenderPrimitives.D();
        OpenGlBackendHolder.d.F();
    }

    @EventHandler
    public void s(EventBedBreakerUpdate eventBedBreakerUpdate) {
        if (this.t == null) {
            SharedModuleControlClaims.a.Q();
            return;
        }
        int n = this.t.q().N();
        int n2 = this.t.q().h();
        int n3 = this.t.q().D$src$I$nuyd86();
        BlockPos blockPos = BlockPos.create(n, n2, n3);
        AxisAlignedBB axisAlignedBB = AxisAlignedBB.create(n, n2, n3, n + 1, n2 + 1, n3 + 1);
        EnumFacing enumFacing = null;
        EntityOtherPlayerMP entityOtherPlayerMP = PlayerSimulationUtil.y();
        if (entityOtherPlayerMP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY() > (double)n2) {
            enumFacing = EnumFacing.T(1);
        } else {
            entityOtherPlayerMP.u((double)n2 + 0.5 + MathUtil.randomRange(new Random(), -0.2, 0.2));
        }
        Vec3d vec3d = RotationUtil.T(entityOtherPlayerMP, axisAlignedBB, 0.0, 0.0, 0.0);
        if (enumFacing == null) {
            double d = 10.0;
            for (EnumFacing enumFacing2 : EnumFacing.t()) {
                BlockPos blockPos2 = blockPos.offset(enumFacing2);
                if (enumFacing2.Y() <= 1) continue;
                double d2 = (double)blockPos2.P() + 0.5 - vec3d.Y();
                double d3 = (double)blockPos2.d() + 0.5 - vec3d.o();
                double d4 = Math.abs(d2) + Math.abs(d3);
                if (!(d4 < d)) continue;
                d = d4;
                enumFacing = enumFacing2;
            }
        }
        this.t.D(vec3d);
        Vec3 vec3 = Minecraft.F().O(1.0f);
        double d = vec3.distanceTo(vec3d.n());
        if (d < 4.5) {
            RayTraceResult rayTraceResult = RayTraceResult.create(RayTraceResult_type.block(), vec3d.n(), enumFacing, blockPos);
            Minecraft.O(rayTraceResult);
            SharedModuleControlClaims.a.M(true);
        } else {
            SharedModuleControlClaims.a.Q();
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void u(BedTargetRenderState bedTargetRenderState) {
        this.t = bedTargetRenderState;
    }

    @Override
    public void onEnable() {
        this.v(50L, true);
    }
}
