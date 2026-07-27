package gg.vape.module.combat;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPacketSend;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.unmap.ItemLimitData;
import gg.vape.value.LimitValue;
import gg.vape.wrapper.impl.CPacketPlayerDigging;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;

public class BlockHit
extends Mod {
    public final LimitValue allowedItems = LimitValue.N(this, "noitemrelease-alloweditems", "Allowed items", LimitValue.r, new ItemLimitData("swords"), new ItemLimitData("food"), new ItemLimitData("potions"));
    private static final long MODULE_ID = -2073545794591715018L;

    private static ObfuscatedRuntimeException rethrow(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public BlockHit() {
        super("NoItemRelease", (int)MODULE_ID, Category.w);
        this.addValue(this.allowedItems);
    }

    @EventHandler
    public void onPacketSend(EventPacketSend eventPacketSend) {
        CPacketPlayerDigging digging;
        Packet packet = eventPacketSend.getPacket();
        boolean inGui = Minecraft.l$src$Z$b9uwii();
        if (inGui) {
            CPacketPlayerDigging guiDigging;
            if (packet.isInstance(MappedClasses.DN) && (guiDigging = new CPacketPlayerDigging(packet)).f()) {
                eventPacketSend.setCancelled(true);
            }
            GuiComponent.D(new GuiComponent[4]);
            return;
        }
        if (packet.isInstance(MappedClasses.DN) && (digging = new CPacketPlayerDigging(packet)).f() && this.allowedItems.A(Minecraft.thePlayer().B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt())) {
            eventPacketSend.setCancelled(true);
        }
    }
}

