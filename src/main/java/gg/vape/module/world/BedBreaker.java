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
    private World lastWorld;
    private BedTargetRenderState selectedTarget;
    private static final long MODULE_ID = -5914606721811702784L;
    private List<BedTargetRenderPosition> targets = new CopyOnWriteArrayList<BedTargetRenderPosition>();
    private HashMap<BedTargetRenderPosition, BedTargetRenderState> renderStates = new HashMap();

    @Override
    public void q() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        WorldClient worldClient = Minecraft.theWorld();
        if (worldClient.isNull() || !worldClient.equals(this.lastWorld)) {
            this.targets.clear();
        }
        int radius = 100;
        for (int dx = -radius; dx < radius; ++dx) {
            for (int dz = -radius; dz < radius; ++dz) {
                int direction = 0;
                while (direction != -1) {
                    direction = direction == 0 ? 1 : -1;
                    for (int step = 0; step < 20; ++step) {
                        for (BedTargetRenderPosition bedTargetRenderPosition : this.targets) {
                            if (!this.renderStates.containsKey(bedTargetRenderPosition)) continue;
                            BedTargetRenderState.H(this.renderStates.get(bedTargetRenderPosition));
                        }
                        if (entityPlayerSP.isNull() || worldClient.isNull()) {
                            return;
                        }
                        int dy = step * direction;
                        int blockX = (int)entityPlayerSP.z() + dx;
                        int blockY = (int)entityPlayerSP.N() + dy;
                        int blockZ = (int)entityPlayerSP.h() + dz;
                        Block block = worldClient.getBlockByPos(blockX, blockY, blockZ);
                        int blockId = Block.R(block);
                        String blockName = block.U();
                        if (blockId != 26 && (blockName == null || !blockName.matches("block.minecraft.(.+_bed)"))) continue;
                        BedTargetRenderPosition bedTargetRenderPosition = new BedTargetRenderPosition(blockX, blockY, blockZ);
                        BlockBed blockBed = new BlockBed(block);
                        boolean isHead = blockBed.f(worldClient, blockX, blockY, blockZ);
                        if (this.targets.contains(bedTargetRenderPosition) || isHead) continue;
                        this.targets.add(bedTargetRenderPosition);
                    }
                }
            }
        }
        this.lastWorld = worldClient;
    }

    public BedBreaker() {
        super("BedBreaker", (int)MODULE_ID, Category.m, "Allows you to break beds through walls\n\u00a7cWarning: This behavior is normally impossible and may be detected on servers");
    }

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        WorldClient worldClient = Minecraft.theWorld();
        ArrayList<BedTargetRenderState> arrayList = new ArrayList<BedTargetRenderState>();
        Vec3 vec3 = Minecraft.F().O(1.0f);
        for (BedTargetRenderPosition bedTargetRenderPosition : this.targets) {
            boolean isHead;
            int blockZ;
            int blockY;
            int blockX = bedTargetRenderPosition.N();
            Block block = worldClient.getBlockByPos(blockX, blockY = bedTargetRenderPosition.h(), blockZ = bedTargetRenderPosition.D$src$I$nuyd86());
            int blockId = Block.R(block);
            BlockBed blockBed = new BlockBed(block);
            if (blockId != 26 && !block.U().matches("block.minecraft.(.+_bed)") || (isHead = blockBed.f(worldClient, blockX, blockY, blockZ))) continue;
            BedTargetRenderState renderState;
            if (this.renderStates.containsKey(bedTargetRenderPosition)) {
                renderState = this.renderStates.get(bedTargetRenderPosition);
            } else {
                renderState = new BedTargetRenderState(bedTargetRenderPosition);
                this.renderStates.put(bedTargetRenderPosition, renderState);
            }
            renderState.h();
            arrayList.add(renderState);
        }
        OpenGlBackendHolder.d.m();
        GuiRenderPrimitives.Y();
        RenderUtils.g();
        OpenGlBackendHolder.d.m();
        OpenGlBackendHolder.d.H(0.5f, 0.5f, 0.5f);
        double reticleSize = 20.0;
        RectData rectData = new RectData((double)(Minecraft.J() / 2) - reticleSize / 2.0, (double)(Minecraft.h() / 2) - reticleSize / 2.0, reticleSize, reticleSize);
        for (BedTargetRenderState bedTargetRenderState : arrayList) {
            bedTargetRenderState.j(rectData, this.selectedTarget == bedTargetRenderState, Minecraft.playerController().c());
        }
        BedTargetRenderState selectedState = null;
        for (BedTargetRenderState candidateState : arrayList) {
            if (!BedTargetRenderState.f(candidateState)) continue;
            selectedState = candidateState;
        }
        this.setSelectedTarget(selectedState);
        OpenGlBackendHolder.d.F();
        RenderUtils.f();
        GuiRenderPrimitives.D();
        OpenGlBackendHolder.d.F();
    }

    @EventHandler
    public void onBedBreakerUpdate(EventBedBreakerUpdate eventBedBreakerUpdate) {
        if (this.selectedTarget == null) {
            SharedModuleControlClaims.a.Q();
            return;
        }
        int blockX = this.selectedTarget.q().N();
        int blockY = this.selectedTarget.q().h();
        int blockZ = this.selectedTarget.q().D$src$I$nuyd86();
        BlockPos blockPos = BlockPos.create(blockX, blockY, blockZ);
        AxisAlignedBB axisAlignedBB = AxisAlignedBB.create(blockX, blockY, blockZ, blockX + 1, blockY + 1, blockZ + 1);
        EnumFacing enumFacing = null;
        EntityOtherPlayerMP entityOtherPlayerMP = PlayerSimulationUtil.y();
        if (entityOtherPlayerMP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY() > (double)blockY) {
            enumFacing = EnumFacing.T(1);
        } else {
            entityOtherPlayerMP.u((double)blockY + 0.5 + MathUtil.randomRange(new Random(), -0.2, 0.2));
        }
        Vec3d vec3d = RotationUtil.T(entityOtherPlayerMP, axisAlignedBB, 0.0, 0.0, 0.0);
        if (enumFacing == null) {
            double bestDist = 10.0;
            for (EnumFacing enumFacing2 : EnumFacing.t()) {
                BlockPos blockPos2 = blockPos.offset(enumFacing2);
                if (enumFacing2.Y() <= 1) continue;
                double diffX = (double)blockPos2.P() + 0.5 - vec3d.Y();
                double diffZ = (double)blockPos2.d() + 0.5 - vec3d.o();
                double dist = Math.abs(diffX) + Math.abs(diffZ);
                if (!(dist < bestDist)) continue;
                bestDist = dist;
                enumFacing = enumFacing2;
            }
        }
        this.selectedTarget.D(vec3d);
        Vec3 vec3 = Minecraft.F().O(1.0f);
        double eyeDist = vec3.distanceTo(vec3d.n());
        if (eyeDist < 4.5) {
            RayTraceResult rayTraceResult = RayTraceResult.create(RayTraceResult_type.block(), vec3d.n(), enumFacing, blockPos);
            Minecraft.O(rayTraceResult);
            SharedModuleControlClaims.a.M(true);
        } else {
            SharedModuleControlClaims.a.Q();
        }
    }


    public void setSelectedTarget(BedTargetRenderState bedTargetRenderState) {
        this.selectedTarget = bedTargetRenderState;
    }

    @Override
    public void onEnable() {
        this.v(50L, true);
    }
}
