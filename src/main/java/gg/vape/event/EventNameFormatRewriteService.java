package gg.vape.event;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventChat;
import gg.vape.event.impl.EventChatMessageRender;
import gg.vape.event.impl.EventEntityRenderState;
import gg.vape.event.impl.EventNameFormat;
import gg.vape.event.impl.EventPlayerTabOverlayDisplayName;
import gg.vape.friend.FriendAliasDisplayNameListener;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.world.ChestStealInventoryState;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ITextComponent;
import gg.vape.wrapper.impl.ScorePlayerTeamTextComponent;
import gg.vape.wrapper.impl.StringTextComponentBase;
import gg.vape.wrapper.impl.TextComponentBaseBridge;
import gg.vape.wrapper.impl.TextComponentString;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class EventNameFormatRewriteService
extends FriendAliasDisplayNameListener {
    private static String[] E;

    private ITextComponent m(ITextComponent iTextComponent) {
        if (ForgeVersion.MC_1_20_6.d()) {
            return this.P(iTextComponent);
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            this.J(iTextComponent);
            return iTextComponent;
        }
        Vape.notifyNativeStackTrace();
        return iTextComponent;
    }

    private Set<TextComponentString> a(ITextComponent iTextComponent, Set<TextComponentString> set) {
        if (ForgeVersion.MC_1_16_5_ACTUAL.Y()) {
            throw new UnsupportedOperationException("getTextComponents should only be called on versions <= 1.16.5");
        }
        if (iTextComponent.isInstance(MappedClasses.ux)) {
            ChestStealInventoryState chestStealInventoryState = new ChestStealInventoryState(iTextComponent.getObject());
            for (Object object : chestStealInventoryState.b()) {
                ITextComponent iTextComponent2 = new ITextComponent(object);
                if (iTextComponent2.isNull() || !iTextComponent2.isInstance(MappedClasses.Yr)) continue;
                this.a(iTextComponent2, set);
            }
        }
        for (ITextComponent iTextComponent3 : iTextComponent.G()) {
            Object object;
            if (iTextComponent3.isNull() || !iTextComponent3.isInstance(MappedClasses.z9)) continue;
            object = new TextComponentString(iTextComponent3.getObject());
            this.a((ITextComponent)object, set);
        }
        if (iTextComponent.isInstance(MappedClasses.z9)) {
            set.add(new TextComponentString(iTextComponent.getObject()));
        }
        return set;
    }

    @EventHandler
    public void K(EventNameFormat eventNameFormat) {
        ITextComponent iTextComponent;
        if (!(this.N() && this.u() && this.S())) {
            return;
        }
        ITextComponent iTextComponent2 = eventNameFormat.getDisplayName();
        if (EventNameFormatRewriteService.S(iTextComponent2, iTextComponent = this.m(iTextComponent2))) {
            eventNameFormat.setDisplayName(iTextComponent);
        }
    }

    static {
        EventNameFormatRewriteService.w(null);
    }

    @EventHandler
    public void G(EventPlayerTabOverlayDisplayName eventPlayerTabOverlayDisplayName) {
        ITextComponent iTextComponent;
        if (!(this.N() && this.u() && this.S())) {
            return;
        }
        ITextComponent iTextComponent2 = eventPlayerTabOverlayDisplayName.getDisplayName();
        if (EventNameFormatRewriteService.S(iTextComponent2, iTextComponent = this.m(iTextComponent2))) {
            eventPlayerTabOverlayDisplayName.setDisplayName(iTextComponent);
        }
    }

    private static boolean S(ITextComponent iTextComponent, ITextComponent iTextComponent2) {
        return iTextComponent.isNotNull() && iTextComponent2.isNotNull() && (ForgeVersion.MC_1_20_6.v() || !iTextComponent.equals(iTextComponent2));
    }

    public static String[] x() {
        return E;
    }

    private StringTextComponentBase O(StringTextComponentBase stringTextComponentBase) {
        ITextComponent iTextComponent;
        ITextComponent iTextComponent2;
        if (stringTextComponentBase.isInstance(MappedClasses.qT) && !(iTextComponent2 = new ScorePlayerTeamTextComponent(stringTextComponentBase.getObject())).equals(iTextComponent = this.a((ScorePlayerTeamTextComponent)iTextComponent2))) {
            return new StringTextComponentBase(iTextComponent.getObject());
        }
        if (stringTextComponentBase.isInstance(MappedClasses.ux) && !(iTextComponent2 = new ChestStealInventoryState(stringTextComponentBase.getObject())).equals(iTextComponent = this.B((ChestStealInventoryState)iTextComponent2))) {
            return new StringTextComponentBase(iTextComponent.getObject());
        }
        return stringTextComponentBase;
    }

    @EventHandler
    public void Z(EventChat eventChat) {
        ITextComponent iTextComponent;
        if (ForgeVersion.MC_1_16_5_ACTUAL.Y() || !this.u() || !this.S()) {
            return;
        }
        ITextComponent iTextComponent2 = eventChat.getMessage();
        if (EventNameFormatRewriteService.S(iTextComponent2, iTextComponent = this.m(iTextComponent2))) {
            eventChat.setMessage(iTextComponent);
        }
    }

    private ITextComponent P(ITextComponent iTextComponent) {
        StringTextComponentBase stringTextComponentBase;
        StringTextComponentBase stringTextComponentBase2 = iTextComponent.F();
        boolean bl = !stringTextComponentBase2.equals(stringTextComponentBase = this.O(stringTextComponentBase2));
        List<ITextComponent> list = iTextComponent.G();
        ArrayList<ITextComponent> arrayList = new ArrayList<ITextComponent>();
        for (ITextComponent iTextComponent2 : list) {
            ITextComponent iTextComponent3 = this.m(iTextComponent2);
            arrayList.add(iTextComponent3);
            if (bl || iTextComponent3.equals(iTextComponent2)) continue;
            bl = true;
        }
        return bl ? TextComponentBaseBridge.l(stringTextComponentBase, arrayList, iTextComponent.J()) : iTextComponent;
    }

    @EventHandler
    public void H(EventChatMessageRender eventChatMessageRender) {
        ITextComponent iTextComponent;
        ITextComponent iTextComponent2 = eventChatMessageRender.getContentComponent();
        if (EventNameFormatRewriteService.S(iTextComponent2, iTextComponent = this.m(iTextComponent2))) {
            eventChatMessageRender.setOutputContentComponent(iTextComponent);
        }
    }

    private static UnsupportedOperationException a(UnsupportedOperationException unsupportedOperationException) {
        return unsupportedOperationException;
    }

    private ScorePlayerTeamTextComponent a(ScorePlayerTeamTextComponent scorePlayerTeamTextComponent) {
        if (ForgeVersion.MC_1_16_5_ACTUAL.B()) {
            throw new UnsupportedOperationException("processStringTextComponent should only be called on versions >= 1.16.6");
        }
        if (scorePlayerTeamTextComponent.isNull()) {
            return scorePlayerTeamTextComponent;
        }
        String string = scorePlayerTeamTextComponent.Y();
        if (string == null) {
            return scorePlayerTeamTextComponent;
        }
        String string2 = this.G(string, this.e());
        if (string2 == null) {
            return scorePlayerTeamTextComponent;
        }
        return string.equalsIgnoreCase(string2) ? scorePlayerTeamTextComponent : (ForgeVersion.MC_1_20_6.d() ? ScorePlayerTeamTextComponent.P(string2) : ScorePlayerTeamTextComponent.B(string2));
    }

    private ChestStealInventoryState B(ChestStealInventoryState chestStealInventoryState) {
        Object[] objectArray;
        if (ForgeVersion.MC_1_20_6.v()) {
            throw new UnsupportedOperationException("processChatComponentTranslation should only be called on versions >= 1.20.6");
        }
        boolean bl = false;
        ArrayList<Object> arrayList = new ArrayList<Object>();
        for (Object object : objectArray = chestStealInventoryState.c()) {
            Object object2;
            Object object3;
            if (object == null) {
                arrayList.add(null);
                continue;
            }
            if (MappedClasses.Yr.isInstance(object)) {
                object3 = new ITextComponent(object);
                object2 = this.m((ITextComponent)object3);
                arrayList.add(((Wrapper)object2).getObject());
                if (bl || ((Wrapper)object3).equals(object2)) continue;
                bl = true;
                continue;
            }
            if (object instanceof String) {
                object3 = (String)object;
                object2 = this.G((String)object3, this.e());
                arrayList.add(object2);
                if (bl || ((String)object3).equalsIgnoreCase((String)object2)) continue;
                bl = true;
                continue;
            }
            arrayList.add(object);
        }
        return !bl ? chestStealInventoryState : (ForgeVersion.MC_1_20_6.d() ? ChestStealInventoryState.A(chestStealInventoryState.U(), chestStealInventoryState.p(), arrayList.toArray()) : ChestStealInventoryState.v(chestStealInventoryState.U(), arrayList.toArray()));
    }

    public static void w(String[] stringArray) {
        E = stringArray;
    }

    @EventHandler
    public void g(EventEntityRenderState eventEntityRenderState) {
        if (!(this.N() && this.u() && this.S())) {
            return;
        }
        ITextComponent iTextComponent = eventEntityRenderState.getEntityRenderState().d();
        if (iTextComponent.isNull()) {
            return;
        }
        ITextComponent iTextComponent2 = this.m(iTextComponent);
        if (EventNameFormatRewriteService.S(iTextComponent, iTextComponent2)) {
            eventEntityRenderState.getEntityRenderState().Z(iTextComponent2);
        }
    }

    private void J(ITextComponent iTextComponent) {
        Set<TextComponentString> set = this.a(iTextComponent, new HashSet<TextComponentString>());
        for (TextComponentString textComponentString : set) {
            String string = textComponentString.getText();
            String string2 = this.G(string, this.e());
            if (string2 == null || string.equalsIgnoreCase(string2)) continue;
            textComponentString.setText(string2);
        }
    }
}

