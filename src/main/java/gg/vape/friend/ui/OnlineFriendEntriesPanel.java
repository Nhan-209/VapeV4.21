package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.ui.OnlineFriendListEntry;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.frame.FrameScrollbarPlacement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OnlineFriendEntriesPanel
extends PanelComponent {
    double Iw = 0.0;
    private static final String db = "wrap";
    boolean IM = true;

    @Override
    public void v() {
    }

    public OnlineFriendEntriesPanel() {
        super(100.0, 20.0);
        this.d(false);
        this.F(FrameScrollbarPlacement.OUTSIDE);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(db);
        this.t(90.0);
    }

    public void W() {
        this.S();
        this.h(new SpacerComponent(1.0, 1.0), new Object[0]);
        ArrayList<OnlineFriend> arrayList = new ArrayList<OnlineFriend>(Vape.INSTANCE.getOnlineFriendManager().g());
        arrayList.sort(Comparator.comparingInt(OnlineFriendEntriesPanel::lambda$refreshList$0).thenComparing(OnlineFriend::C));
        for (OnlineFriend onlineFriend : arrayList) {
            OnlineFriendListEntry onlineFriendListEntry = Vape.INSTANCE.getOnlineManager().u().U(onlineFriend, () -> OnlineFriendEntriesPanel.lambda$refreshList$1(onlineFriend));
            this.h(onlineFriendListEntry, new Object[0]);
        }
    }

    private static OnlineFriendListEntry lambda$refreshList$1(OnlineFriend onlineFriend) {
        return new OnlineFriendListEntry(onlineFriend);
    }

    private static int lambda$refreshList$0(OnlineFriend onlineFriend) {
        return onlineFriend.F().ordinal();
    }

    @Override
    public void Y() {
    }

    @Override
    public void c() {
        super.c();
        if (this.IM) {
            this.W();
            this.IM = false;
        }
    }


    @Override
    public void V() {
        double d = 0.0;
        List<GuiComponent> list = this.f();
        for (GuiComponent guiComponent : list) {
            if (!guiComponent.V$src$Z$1xhop3l()) continue;
            d += guiComponent.L();
        }
        this.Iw = d;
        this.u(this.Iw);
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public double x() {
        return 100.0;
    }

    @Override
    public double C() {
        return 20.0;
    }

    public void b$src$V$1a27gcp() {
        this.IM = true;
    }
}

