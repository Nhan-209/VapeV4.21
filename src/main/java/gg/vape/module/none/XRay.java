package gg.vape.module.none;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventBlockFluidRender;
import gg.vape.event.impl.EventBlockLayerOverride;
import gg.vape.event.impl.EventBlockLayerRender;
import gg.vape.event.impl.EventBlockModelRender;
import gg.vape.event.impl.EventBlockRenderBounds;
import gg.vape.event.impl.EventBlockRenderColorOpacity;
import gg.vape.event.impl.EventBlockShouldRender;
import gg.vape.event.impl.EventChunkRenderRebuild;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventRenderWorldPassExecutorDrain;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.BooleanValue;
import gg.vape.value.NumberValue;
import gg.vape.value.OptionalLimitValue;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EnumWorldBlockLayer;
import gg.vape.wrapper.impl.Minecraft;
import java.util.ArrayList;
import java.util.List;

public class XRay
extends Mod {
    private final NumberValue U;
    private static final long p = -2807609302372515841L;
    private boolean b;
    private double L = 0.0;
    private float t = 1.0f;
    private final List<Integer> S;
    private final OptionalLimitValue c = OptionalLimitValue.l(this, "xray-blocks", "Xray Blocks", OptionalLimitValue.r, "Gold Ore", "Iron Ore", "Diamond Ore", "Emerald Ore", "Lapis Lazuli Ore", "Gold Block", "Iron Block", "Diamond Block", "Emerald Block");
    private final BooleanValue J;

    public void i(EventChunkRenderRebuild eventChunkRenderRebuild) {
        if (!this.r$src$Z$14eylz9()) {
            return;
        }
        eventChunkRenderRebuild.setCancelled(true);
    }

    private void lambda$new$0(NumberValue numberValue) {
        if (this.r$src$Z$14eylz9()) {
            this.refreshWorldForOpacity();
        }
    }

    public void s(EventBlockFluidRender eventBlockFluidRender) {
        if (!this.r$src$Z$14eylz9()) {
            return;
        }
        eventBlockFluidRender.setCancelled(true);
    }

    public void onBlockModelRender(EventBlockModelRender eventBlockModelRender) {
        if (!this.r$src$Z$14eylz9()) {
            return;
        }
        eventBlockModelRender.setCancelled(true);
    }

    public void onBlockRenderDecision(EventBlockLayerOverride eventBlockLayerOverride) {
        if (!this.r$src$Z$14eylz9()) {
            return;
        }
        eventBlockLayerOverride.setCancelled(true);
        if (this.isTargetBlock(eventBlockLayerOverride.getBlock())) {
            eventBlockLayerOverride.setShouldRender(true);
        }
    }

    @Override
    public boolean isBlatantMod() {
        return true;
    }

    private void refreshWorldForOpacity() {
        if (Minecraft.thePlayer().isNull() || (Double)this.U.K() == this.L) {
            return;
        }
        int n = 4000;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        int n2 = (int)entityPlayerSP.z();
        int n3 = (int)entityPlayerSP.h();
        Minecraft.theWorld().Z(n2 - n, 0, n3 - n, n2 + n, 300, n3 + n);
        this.L = (Double)this.U.K();
    }

    private void reloadRenderers() {
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        Minecraft.O().loadRenderers();
    }

    @Override
    public void onEnable() {
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        if (!Vape.INSTANCE.getPrimaryMappingTaskSet().Q()) {
            Vape.INSTANCE.getPrimaryMappingTaskSet().Y();
        }
        this.b = true;
        this.t = Minecraft.gameSettings().b();
        Minecraft.gameSettings().y(10.0f);
        this.refreshWorldForOpacity();
    }

    @Override
    public void onDisable() {
        EventRenderWorldPassExecutorDrain.E.execute(this::reloadRenderers);
        Minecraft.gameSettings().y(this.t);
    }

    public void onBlockSideRender(EventBlockShouldRender eventBlockShouldRender) {
        if (!this.r$src$Z$14eylz9()) {
            return;
        }
        if (this.isTargetBlock(eventBlockShouldRender.getBlock())) {
            eventBlockShouldRender.setCancelled(this.J.L() == false);
        }
    }

    public void onAmbientOcclusion(EventBlockRenderBounds eventBlockRenderBounds) {
        if (!this.r$src$Z$14eylz9()) {
            return;
        }
        eventBlockRenderBounds.getRenderBlocks().M(this.isTargetBlock(eventBlockRenderBounds.getBlock()));
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        this.S.clear();
        for (String string : this.c.D()) {
            Block block = Block.t(string.replace(" ", "_").toLowerCase());
            if (block == null || this.S.contains(Block.R(block))) continue;
            this.S.add(Block.R(block));
        }
    }

    @EventHandler
    public void onRenderWorldPassComplete(EventPreRenderTick eventPreRenderTick) {
        if (!this.b) {
            return;
        }
        this.b = false;
        this.reloadRenderers();
    }

    public void onBlockRenderColorOpacity(EventBlockRenderColorOpacity eventBlockRenderColorOpacity) {
        eventBlockRenderColorOpacity.setOpacity(((Double)this.U.K()).intValue());
    }

    public XRay() {
        super("Xray", (int)p, Category.m, "Renders whitelisted blocks through walls.");
        this.U = NumberValue.create((Object)this, "Opacity", "#", "", 0.0, 60.0, 255.0, 1.0);
        this.J = BooleanValue.create(this, "Cave Mode", false, "Only shows ores that are exposed to air.");
        this.S = new ArrayList<Integer>();
        this.addValue(this.U, this.J, this.c);
        this.U.B(this::lambda$new$0);
    }

    public int getOpacity() {
        return ((Double)this.U.K()).intValue();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean isTargetBlock(Block block) {
        return this.S.contains(Block.R(block));
    }

    public void onBlockRenderLayer(EventBlockLayerRender eventBlockLayerRender) {
        if (this.isTargetBlock(eventBlockLayerRender.getBlock())) {
            eventBlockLayerRender.setCancelled(true);
            if (eventBlockLayerRender.getEnumWorldBlockLayer().equals(EnumWorldBlockLayer.v())) {
                eventBlockLayerRender.setShouldRender(true);
            } else {
                eventBlockLayerRender.setShouldRender(false);
            }
        }
    }
}

