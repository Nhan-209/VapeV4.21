package gg.vape.friend;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventChat;
import gg.vape.event.impl.EventNameFormat;
import gg.vape.event.impl.EventPlayerTabOverlayDisplayNameLegacy;
import gg.vape.friend.FriendAliasDisplayNameListener;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.world.ChestStealInventoryState;
import gg.vape.wrapper.impl.ITextComponent;
import gg.vape.wrapper.impl.TextComponentString;
import java.util.HashSet;
import java.util.Set;

public final class FriendAliasEventListener
extends FriendAliasDisplayNameListener {
    private void Y(ITextComponent t3_02) {
        Set<TextComponentString> set = this.J(t3_02, new HashSet<TextComponentString>());
        for (TextComponentString tl_22 : set) {
            String string = tl_22.getText();
            String string2 = this.G(string, this.e());
            if (string2 == null || string.equalsIgnoreCase(string2)) continue;
            tl_22.setText(string2);
        }
    }


    @EventHandler
    public void D(EventNameFormat qr_02) {
        if (!(this.N() && this.u() && this.S())) {
            return;
        }
        ITextComponent t3_02 = qr_02.getDisplayName();
        if (t3_02.isNull() || !t3_02.isInstance(MappedClasses.z9)) {
            return;
        }
        TextComponentString tl_22 = new TextComponentString(t3_02.getObject());
        this.Y(tl_22);
        for (ITextComponent t3_03 : tl_22.G()) {
            if (t3_03.isNull() || !t3_03.isInstance(MappedClasses.z9)) continue;
            TextComponentString tl_23 = new TextComponentString(t3_03.getObject());
            this.Y(tl_23);
        }
    }

    @EventHandler
    public void Y(EventChat qA) {
        if (!this.u() || !this.S()) {
            return;
        }
        this.Y(qA.getMessage());
    }

    @EventHandler
    public void c(EventPlayerTabOverlayDisplayNameLegacy ez_22) {
        if (!(this.N() && this.u() && this.S())) {
            return;
        }
        String string = ez_22.getDisplayName();
        String string2 = this.G(string, this.e());
        if (string2 != null && !string.equalsIgnoreCase(string2)) {
            ez_22.setDisplayName(string2);
        }
    }

    private Set<TextComponentString> J(ITextComponent t3_02, Set<TextComponentString> set) {
        if (t3_02.isInstance(MappedClasses.ux)) {
            ChestStealInventoryState tq_22 = new ChestStealInventoryState(t3_02.getObject());
            for (Object object : tq_22.b()) {
                ITextComponent t3_03 = new ITextComponent(object);
                if (t3_03.isNull() || !t3_03.isInstance(MappedClasses.Yr)) continue;
                this.J(t3_03, set);
            }
        }
        for (ITextComponent t3_04 : t3_02.G()) {
            Object object;
            if (t3_04.isNull() || !t3_04.isInstance(MappedClasses.z9)) continue;
            object = new TextComponentString(t3_04.getObject());
            this.J((ITextComponent)object, set);
        }
        if (t3_02.isInstance(MappedClasses.z9)) {
            set.add(new TextComponentString(t3_02.getObject()));
        }
        return set;
    }
}

