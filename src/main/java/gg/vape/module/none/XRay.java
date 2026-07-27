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
    private final NumberValue opacityValue;
    private static final long MODULE_ID_KEY = -2807609302372515841L;
    private boolean needsReload;
    private double lastOpacity = 0.0;
    private float savedGamma = 1.0f;
    private final List<Integer> blockIds;
    private final OptionalLimitValue blocksValue = OptionalLimitValue.l(this, "xray-blocks", "Xray Blocks", OptionalLimitValue.r, "Gold Ore", "Iron Ore", "Diamond Ore", "Emerald Ore", "Lapis Lazuli Ore", "Gold Block", "Iron Block", "Diamond Block", "Emerald Block");
    private final BooleanValue caveModeValue;

    public void i(EventChunkRenderRebuild eventChunkRenderRebuild) {
        if (!this.r$src$Z$14eylz9()) {
            return;
        }
        eventChunkRenderRebuild.setCancelled(true);
    }

    private void onOpacityChanged(NumberValue numberValue) {
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
        if (Minecraft.thePlayer().isNull() || (Double)this.opacityValue.K() == this.lastOpacity) {
            return;
        }
        int radius = 4000;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        int playerX = (int)entityPlayerSP.z();
        int playerZ = (int)entityPlayerSP.h();
        Minecraft.theWorld().Z(playerX - radius, 0, playerZ - radius, playerX + radius, 300, playerZ + radius);
        this.lastOpacity = (Double)this.opacityValue.K();
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
        this.needsReload = true;
        this.savedGamma = Minecraft.gameSettings().b();
        Minecraft.gameSettings().y(10.0f);
        this.refreshWorldForOpacity();
    }

    @Override
    public void onDisable() {
        EventRenderWorldPassExecutorDrain.E.execute(this::reloadRenderers);
        Minecraft.gameSettings().y(this.savedGamma);
    }

    public void onBlockSideRender(EventBlockShouldRender eventBlockShouldRender) {
        if (!this.r$src$Z$14eylz9()) {
            return;
        }
        if (this.isTargetBlock(eventBlockShouldRender.getBlock())) {
            eventBlockShouldRender.setCancelled(this.caveModeValue.L() == false);
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
        this.blockIds.clear();
        for (String string : this.blocksValue.D()) {
            Block block = Block.t(string.replace(" ", "_").toLowerCase());
            if (block == null || this.blockIds.contains(Block.R(block))) continue;
            this.blockIds.add(Block.R(block));
        }
    }

    @EventHandler
    public void onRenderWorldPassComplete(EventPreRenderTick eventPreRenderTick) {
        if (!this.needsReload) {
            return;
        }
        this.needsReload = false;
        this.reloadRenderers();
    }

    public void onBlockRenderColorOpacity(EventBlockRenderColorOpacity eventBlockRenderColorOpacity) {
        eventBlockRenderColorOpacity.setOpacity(((Double)this.opacityValue.K()).intValue());
    }

    public XRay() {
        super("Xray", (int)MODULE_ID_KEY, Category.m, "Renders whitelisted blocks through walls.");
        this.opacityValue = NumberValue.create((Object)this, "Opacity", "#", "", 0.0, 60.0, 255.0, 1.0);
        this.caveModeValue = BooleanValue.create(this, "Cave Mode", false, "Only shows ores that are exposed to air.");
        this.blockIds = new ArrayList<Integer>();
        this.addValue(this.opacityValue, this.caveModeValue, this.blocksValue);
        this.opacityValue.B(this::onOpacityChanged);
    }

    public int getOpacity() {
        return ((Double)this.opacityValue.K()).intValue();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean isTargetBlock(Block block) {
        return this.blockIds.contains(Block.R(block));
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

