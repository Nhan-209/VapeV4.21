package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.PartyInvite;
import gg.vape.friend.ui.PartyInviteRow;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.frame.FrameScrollbarPlacement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PartyInvitesPanel
extends PanelComponent {
    private static final String db;
    double eg = 0.0;
    private final Map<PartyInvite, PartyInviteRow> eA = new LinkedHashMap<PartyInvite, PartyInviteRow>();
    private static String eI;
    private boolean eE;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void v() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void u(PartyInviteRow partyInviteRow) {
        Map<PartyInvite, PartyInviteRow> map = this.eA;
        synchronized (map) {
            this.eA.put(partyInviteRow.T(), partyInviteRow);
            this.h(partyInviteRow, new Object[0]);
            this.b$src$V$172vuhc();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void P(PartyInvite partyInvite) {
        Map<PartyInvite, PartyInviteRow> map = this.eA;
        synchronized (map) {
            PartyInviteRow partyInviteRow = this.eA.get(partyInvite);
            if (partyInviteRow == null) {
                return;
            }
            this.b(partyInviteRow);
        }
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    public void n$src$V$179hdlo() {
        this.eE = !this.eE;
        this.N(this.eE);
        this.b$src$V$172vuhc();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void b(PartyInviteRow partyInviteRow) {
        Map<PartyInvite, PartyInviteRow> map = this.eA;
        synchronized (map) {
            this.eA.remove(partyInviteRow.T());
            this.I(partyInviteRow);
            this.b$src$V$172vuhc();
        }
    }

    @Override
    public double C() {
        return this.eg;
    }

    @Override
    public double x() {
        return 100.0;
    }

    @Override
    public void V() {
    }

    public static void f(String string) {
        eI = string;
    }

    public PartyInvitesPanel() {
        super(100.0, 0.0);
        this.d(false);
        this.F(FrameScrollbarPlacement.OUTSIDE);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(db);
    }

    static {
        PartyInvitesPanel.f("KA2HLb");
        db = "wrap";
    }

    public void b$src$V$172vuhc() {
        List<GuiComponent> list = this.f();
        for (int i = 0; i < list.size(); ++i) {
            if (i == 0) {
                list.get(i).Z(true);
                continue;
            }
            list.get(i).Z(this.eE);
        }
        this.W(0.0);
    }

    @Override
    public void H() {
        int n = Vape.INSTANCE.getOnlineManager().y().n().size();
        this.eg = n < 1 ? 1.0 : (n < 2 ? 17.0 : (this.eE ? 48.0 : 17.0));
        this.t(this.eg);
    }

    @Override
    public void Y() {
    }

    public static String getName() {
        return eI;
    }
}

