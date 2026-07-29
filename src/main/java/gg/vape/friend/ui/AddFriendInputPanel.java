package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.Friend;
import gg.vape.friend.ui.AddFriendInputComponent;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.input.CompactTextInputComponent;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.ui.click.layout.ComponentLayout;
import gg.vape.value.PlayerNameSuggestionProvider;

public class AddFriendInputPanel
extends FrameComponent {
    CompactTextInputComponent FL = new AddFriendInputComponent(this, db);
    private static final String db = "Minecraft username";

    @Override
    public void Y() {
    }

    @Override
    public void c() {
        super.c();
    }


    static void h(AddFriendInputPanel addFriendInputPanel) {
        addFriendInputPanel.b$src$V$y3rl3d();
    }

    @Override
    public void v() {
    }

    public AddFriendInputPanel() {
        ComponentLayout componentLayout = this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij();
        componentLayout.t(false);
        componentLayout.U(false);
        componentLayout.u(false);
        this.FL.setSuggestionProvider(new PlayerNameSuggestionProvider());
        this.addChildren(this.FL);
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    private void b$src$V$y3rl3d() {
        if (this.FL.getText() == "") {
            return;
        }
        if (!this.FL.hasNonBlankText()) {
            this.FL.setText("");
            return;
        }
        String[] stringArray = this.FL.getText().split(" ");
        String string = stringArray[0];
        String string2 = stringArray.length > 1 ? stringArray[1] : stringArray[0];
        Vape.INSTANCE.getFriendManager().u(new Friend(string, string2));
        this.FL.setText("");
    }

    @Override
    public double x() {
        return 104.0;
    }

    @Override
    public double C() {
        return 22.0;
    }

    @Override
    public void V() {
    }
}
