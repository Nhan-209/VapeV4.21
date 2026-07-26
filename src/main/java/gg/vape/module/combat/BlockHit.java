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
    public final LimitValue L = LimitValue.N(this, "noitemrelease-alloweditems", "Allowed items", LimitValue.r, new ItemLimitData("swords"), new ItemLimitData("food"), new ItemLimitData("potions"));
    private static final long k = -2073545794591715018L;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public BlockHit() {
        super("NoItemRelease", (int)k, Category.w);
        this.addValue(this.L);
    }

    @EventHandler
    public void onPacketSend(EventPacketSend eventPacketSend) {
        CPacketPlayerDigging cPacketPlayerDigging;
        Packet packet = eventPacketSend.getPacket();
        boolean bl = Minecraft.l$src$Z$b9uwii();
        if (bl) {
            CPacketPlayerDigging cPacketPlayerDigging2;
            if (packet.isInstance(MappedClasses.DN) && (cPacketPlayerDigging2 = new CPacketPlayerDigging(packet)).f()) {
                eventPacketSend.setCancelled(true);
            }
            GuiComponent.D(new GuiComponent[4]);
            return;
        }
        if (packet.isInstance(MappedClasses.DN) && (cPacketPlayerDigging = new CPacketPlayerDigging(packet)).f() && this.L.A(Minecraft.thePlayer().B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt())) {
            eventPacketSend.setCancelled(true);
        }
    }
}

