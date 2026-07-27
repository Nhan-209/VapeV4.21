package gg.vape.ui.click.frame.impl.main;

import gg.vape.Vape;
import gg.vape.friend.Friend;
import gg.vape.friend.FriendEntry;
import gg.vape.friend.LocalOnlineFriend;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.OnlineStatus;
import gg.vape.friend.PartyInvite;
import gg.vape.friend.PartyManager;
import gg.vape.friend.ui.FriendAliasEditInputComponent;
import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.friend.ui.PartyMemberEntryComponent;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.FriendModuleInteractiveComponent;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.LabeledTextInputComponent;
import gg.vape.ui.click.component.MultilineTextBlockComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.input.TrailingActionTextInputComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.frame.FrameScrollbarPlacement;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendSourceMode;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendSourceModeSwitchMap;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsFriendActionComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsFriendCardFactory;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsFriendListComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsFriendRequestComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsFriendStatusComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsModeToggleComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsNameInputListener;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsThemeConfigFactory;
import gg.vape.ui.click.frame.impl.main.ClickGuiMainFrame;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayPlacement;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlaySpec;
import gg.vape.ui.click.frame.impl.main.ClickGuiPageBase;
import gg.vape.ui.click.frame.impl.main.ClickGuiThemeOverlayFactory;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PlayerInfo;
import gg.vape.wrapper.impl.WorldClient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;

public class ClickGuiFriendsPage
extends ClickGuiPageBase {
    private PanelComponent jb;
    private final ClickGuiMainFrame jB;
    private static final long pb;
    private ClickGuiFriendSourceMode jF = ClickGuiFriendSourceMode.MINECRAFT;
    private PanelComponent jG;
    private GuiComponent jl;
    private LabeledTextInputComponent jc;
    private static int[] j7;
    private GuiComponent ja;
    private PanelComponent jX;
    private String jo = "";

    private void n(String string) {
        Object object;
        Object object2;
        EntityPlayer entityPlayer;
        Object object3;
        this.jb.S();
        ClickGuiFriendsFriendActionComponent clickGuiFriendsFriendActionComponent = new ClickGuiFriendsFriendActionComponent("\"" + string + "\"", "Search Result");
        clickGuiFriendsFriendActionComponent.o(this.jG.A());
        boolean bl = Vape.INSTANCE.getFriendManager().E(string);
        clickGuiFriendsFriendActionComponent.q(bl);
        clickGuiFriendsFriendActionComponent.L(() -> this.lambda$populateNearbyPlayersContainer$8(string));
        clickGuiFriendsFriendActionComponent.F(() -> this.lambda$populateNearbyPlayersContainer$9(string));
        this.jb.h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, clickGuiFriendsFriendActionComponent), new Object[0]);
        ArrayList<Object> arrayList = new ArrayList<Object>();
        WorldClient worldClient = Minecraft.theWorld();
        if (worldClient.isNotNull()) {
            Iterator<?> worldEntities = worldClient.X().iterator();
            while (worldEntities.hasNext()) {
                object3 = worldEntities.next();
                entityPlayer = new EntityPlayer(object3);
                if (entityPlayer.isNull() || !((String)(object2 = entityPlayer.getName())).toLowerCase().contains(string) || arrayList.contains(object2)) continue;
                arrayList.add(object2);
                object = new ClickGuiFriendsFriendActionComponent((String)object2, "Nearby", entityPlayer);
                ((GuiComponent)object).o(this.jG.A());
                boolean bl2 = Vape.INSTANCE.getFriendManager().E((String)object2);
                ((ClickGuiFriendsFriendActionComponent)object).q(bl2);
                String nearbyPlayerName = (String)object2;
                ((ClickGuiFriendsFriendActionComponent)object).L(() -> this.lambda$populateNearbyPlayersContainer$10(nearbyPlayerName));
                ((ClickGuiFriendsFriendActionComponent)object).F(() -> this.lambda$populateNearbyPlayersContainer$11(nearbyPlayerName));
                this.jb.h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, (GuiComponent)object), new Object[0]);
            }
        }
        if (ForgeVersion.MC_1_8_9.d()) {
            Collection<?> playerInfoMap = Minecraft.N().getPlayerInfoMap();
            if (playerInfoMap != null && !playerInfoMap.isEmpty()) {
                Iterator<?> playerInfoIterator = playerInfoMap.iterator();
                while (playerInfoIterator.hasNext()) {
                    Object playerInfoHandle = playerInfoIterator.next();
                    object2 = new PlayerInfo(playerInfoHandle);
                    object = ((PlayerInfo)object2).v().getName();
                    if (!((String)object).toLowerCase().contains(string) || arrayList.contains(object)) continue;
                    arrayList.add(object);
                    ClickGuiFriendsFriendActionComponent clickGuiFriendsFriendActionComponent2 = new ClickGuiFriendsFriendActionComponent((String)object, "Online Player", null, (PlayerInfo)object2);
                    clickGuiFriendsFriendActionComponent2.o(this.jG.A());
                    boolean bl3 = Vape.INSTANCE.getFriendManager().E((String)object);
                    clickGuiFriendsFriendActionComponent2.q(bl3);
                    String onlinePlayerName = (String)object;
                    clickGuiFriendsFriendActionComponent2.L(() -> this.lambda$populateNearbyPlayersContainer$12(onlinePlayerName));
                    clickGuiFriendsFriendActionComponent2.F(() -> this.lambda$populateNearbyPlayersContainer$13(onlinePlayerName));
                    this.jb.h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, clickGuiFriendsFriendActionComponent2), new Object[0]);
                }
            }
        }
    }

    private void lambda$populateNearbyPlayersContainer$11(String string) {
        FriendEntry friendEntry = Vape.INSTANCE.getFriendManager().O(string);
        if (friendEntry != null) {
            Vape.INSTANCE.getFriendManager().E(friendEntry);
            Vape.INSTANCE.getNotificationManager().k("\u00a7cRemoved\u00a7r " + string + " from friends", "", 2000L);
            this.N$src$V$s1q618();
        }
    }

    private Boolean lambda$renderCategoryButtons$2() {
        return this.jF == ClickGuiFriendSourceMode.ONLINE;
    }

    private void e$src$V$sedfoj() {
        this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().S();
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent("Under construction. Use frames GUI mode to use Vape friends for now.");
        simpleTextLabelComponent.i(0.8);
        simpleTextLabelComponent.T$src$V$1orl066(ClickGuiFriendsPage.J.A);
        simpleTextLabelComponent.g(0.0f);
        this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(new PaddedComponent(0.0, 12.0, 0.0, 0.0, simpleTextLabelComponent), "wrap");
        this.p();
    }

    private void n$src$V$sjbl0s() {
        GuiComponent guiComponent = this.H$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$3n0k6u().f().get(0);
        this.H$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$3n0k6u().S();
        GlyphIconComponent glyphIconComponent = new GlyphIconComponent("newsettings", 6.0, 6.0, 10.0, 10.0, null, null, null);
        glyphIconComponent.R(true);
        glyphIconComponent.q(true);
        glyphIconComponent.d(true);
        glyphIconComponent.r(this::k$src$V$sho78p);
        this.H$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$3n0k6u().h(guiComponent, new Object[0]);
        this.H$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$3n0k6u().h(new SpacerComponent(this.H$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$3n0k6u().A() - this.H$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$3n0k6u().l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().C() - glyphIconComponent.A(), 0.0), new Object[0]);
        this.H$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$3n0k6u().h(new PaddedComponent(4.0, 0.0, 0.0, 0.0, glyphIconComponent), new Object[0]);
        this.L$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$m6o1mi().S();
        FriendModuleInteractiveComponent friendModuleInteractiveComponent = new FriendModuleInteractiveComponent("Minecraft Friends", null, this::lambda$renderCategoryButtons$0, null, "expandarrow");
        friendModuleInteractiveComponent.r(this::lambda$renderCategoryButtons$1);
        FriendModuleInteractiveComponent friendModuleInteractiveComponent2 = new FriendModuleInteractiveComponent("VAPE Friends", null, this::lambda$renderCategoryButtons$2, ClickGuiFriendsPage::lambda$renderCategoryButtons$3, "expandarrow");
        friendModuleInteractiveComponent2.r(this::lambda$renderCategoryButtons$4);
        this.L$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$m6o1mi().h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, friendModuleInteractiveComponent), "wrap");
        this.L$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$m6o1mi().h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, friendModuleInteractiveComponent2), "wrap");
        PartyManager partyManager = Vape.INSTANCE.getOnlineManager().y();
        boolean bl = partyManager.j() != null;
        LocalOnlineFriend localOnlineFriend = Vape.INSTANCE.getOnlineManager().r();
        OnlineStatus onlineStatus = localOnlineFriend != null ? localOnlineFriend.F() : null;
        String string = this.W(localOnlineFriend != null ? localOnlineFriend.C() : null, "You");
        String string2 = onlineStatus != null ? onlineStatus.f() : "Offline";
        ClickGuiFriendsFriendStatusComponent clickGuiFriendsFriendStatusComponent = new ClickGuiFriendsFriendStatusComponent(string, string2, bl);
        clickGuiFriendsFriendStatusComponent.C(0.0);
        clickGuiFriendsFriendStatusComponent.G(onlineStatus != null ? onlineStatus.P() : null);
        clickGuiFriendsFriendStatusComponent.j(string2);
        clickGuiFriendsFriendStatusComponent.G(bl);
        clickGuiFriendsFriendStatusComponent.s(this::lambda$renderCategoryButtons$5);
        this.L$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$m6o1mi().h(new SpacerComponent(0.0, this.L$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$m6o1mi().L() - this.L$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$m6o1mi().l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().y() - clickGuiFriendsFriendStatusComponent.L() - 1.0), "wrap");
        this.j$src$V$sh4enc();
    }

    public ClickGuiFriendsPage(ClickGuiMainFrame clickGuiMainFrame, double d, double d2, double d3) {
        super(d, d2, d3, 0.0, "Friends");
        this.jB = clickGuiMainFrame;
        this.n$src$V$sjbl0s();
        this.d$src$V$sdtn36();
    }

    private void lambda$populateNearbyPlayersContainer$12(String string) {
        Vape.INSTANCE.getFriendManager().u(new Friend(string, string));
        Vape.INSTANCE.getNotificationManager().k("\u00a7aAdded\u00a7r " + string + " to friends", "", 2000L);
        this.N$src$V$s1q618();
    }

    private void lambda$renderCategoryButtons$4() {
        if (this.jF != ClickGuiFriendSourceMode.ONLINE) {
            this.jF = ClickGuiFriendSourceMode.ONLINE;
            this.d$src$V$sdtn36();
        }
    }

    private static List lambda$renderOnlineFriendsContent$16(OnlineStatus onlineStatus) {
        return new ArrayList();
    }

    private void y(PanelComponent panelComponent, double d) {
        for (GuiComponent guiComponent : panelComponent.f()) {
            if (guiComponent instanceof PaddedComponent) {
                ClickGuiFriendsFriendRequestComponent clickGuiFriendsFriendRequestComponent = ((PaddedComponent)guiComponent).t(ClickGuiFriendsFriendRequestComponent.class);
                if (clickGuiFriendsFriendRequestComponent != null) {
                    clickGuiFriendsFriendRequestComponent.o(d);
                    continue;
                }
                ClickGuiFriendsFriendActionComponent clickGuiFriendsFriendActionComponent = ((PaddedComponent)guiComponent).t(ClickGuiFriendsFriendActionComponent.class);
                if (clickGuiFriendsFriendActionComponent != null) {
                    clickGuiFriendsFriendActionComponent.o(d);
                    continue;
                }
                SimpleTextLabelComponent simpleTextLabelComponent = ((PaddedComponent)guiComponent).t(SimpleTextLabelComponent.class);
                if (simpleTextLabelComponent == null) continue;
                simpleTextLabelComponent.o(d);
                continue;
            }
            if (guiComponent instanceof ClickGuiFriendsFriendRequestComponent) {
                guiComponent.o(d);
                continue;
            }
            if (!(guiComponent instanceof ClickGuiFriendsFriendActionComponent)) continue;
            guiComponent.o(d);
        }
    }

    private void X(FriendEntry friendEntry) {
        this.jB.Z(ClickGuiOverlaySpec.q().e(friendEntry.s()).C("newsettings").n(ClickGuiOverlayPlacement.DOCKED).N(arg_0 -> this.lambda$createFriendSettingsSidecar$19(friendEntry, arg_0)).w());
    }

    private void lambda$populateNearbyPlayersContainer$10(String string) {
        Vape.INSTANCE.getFriendManager().u(new Friend(string, string));
        Vape.INSTANCE.getNotificationManager().k("\u00a7aAdded\u00a7r " + string + " to friends", "", 2000L);
        this.N$src$V$s1q618();
    }

    private static int lambda$renderOnlineFriendsContent$14(OnlineFriend onlineFriend) {
        OnlineStatus onlineStatus = onlineFriend.F();
        return onlineStatus != null ? onlineStatus.ordinal() : (int)pb;
    }

    private void lambda$populateYourFriendsContainer$6(FriendEntry friendEntry) {
        Vape.INSTANCE.getFriendManager().E(friendEntry);
        Vape.INSTANCE.getNotificationManager().k("\u00a7cRemoved\u00a7r " + friendEntry.s() + " from friends", "", 2000L);
        this.N$src$V$s1q618();
    }

    private static void lambda$createFriendSettingsContent$21(TrailingActionTextInputComponent trailingActionTextInputComponent, String[] stringArray) {
        trailingActionTextInputComponent.k(stringArray[0]);
    }

    private void p() {
        double d = this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().A();
        for (GuiComponent guiComponent : this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().f()) {
            if (guiComponent instanceof PaddedComponent) {
                ClickGuiFriendsFriendRequestComponent clickGuiFriendsFriendRequestComponent = ((PaddedComponent)guiComponent).t(ClickGuiFriendsFriendRequestComponent.class);
                if (clickGuiFriendsFriendRequestComponent != null) {
                    clickGuiFriendsFriendRequestComponent.o(d);
                    continue;
                }
                ClickGuiFriendsFriendActionComponent clickGuiFriendsFriendActionComponent = ((PaddedComponent)guiComponent).t(ClickGuiFriendsFriendActionComponent.class);
                if (clickGuiFriendsFriendActionComponent != null) {
                    clickGuiFriendsFriendActionComponent.o(d);
                    continue;
                }
                PartyMemberEntryComponent partyMemberEntryComponent = ((PaddedComponent)guiComponent).t(PartyMemberEntryComponent.class);
                if (partyMemberEntryComponent != null) {
                    partyMemberEntryComponent.o(d);
                    continue;
                }
                ClickGuiFriendsFriendStatusComponent clickGuiFriendsFriendStatusComponent = ((PaddedComponent)guiComponent).t(ClickGuiFriendsFriendStatusComponent.class);
                if (clickGuiFriendsFriendStatusComponent != null) {
                    clickGuiFriendsFriendStatusComponent.o(Math.min(d, ClickGuiFriendsFriendStatusComponent.m$src$D$zpwkct()));
                    continue;
                }
                ClickGuiFriendsFriendListComponent clickGuiFriendsFriendListComponent = ((PaddedComponent)guiComponent).t(ClickGuiFriendsFriendListComponent.class);
                if (clickGuiFriendsFriendListComponent != null) {
                    clickGuiFriendsFriendListComponent.o(d);
                    continue;
                }
                TrailingActionTextInputComponent trailingActionTextInputComponent = ((PaddedComponent)guiComponent).t(TrailingActionTextInputComponent.class);
                if (trailingActionTextInputComponent != null) {
                    trailingActionTextInputComponent.o(d);
                    continue;
                }
                LabeledTextInputComponent labeledTextInputComponent = ((PaddedComponent)guiComponent).t(LabeledTextInputComponent.class);
                if (labeledTextInputComponent == null) continue;
                labeledTextInputComponent.o(d - 0.0);
                continue;
            }
            if (guiComponent instanceof ClickGuiFriendsFriendRequestComponent) {
                guiComponent.o(d);
                continue;
            }
            if (guiComponent instanceof ClickGuiFriendsFriendActionComponent) {
                guiComponent.o(d);
                continue;
            }
            if (guiComponent instanceof PartyMemberEntryComponent) {
                guiComponent.o(d);
                continue;
            }
            if (guiComponent instanceof ClickGuiFriendsFriendStatusComponent) {
                guiComponent.o(Math.min(d, ClickGuiFriendsFriendStatusComponent.m$src$D$zpwkct()));
                continue;
            }
            if (guiComponent instanceof ClickGuiFriendsFriendListComponent) {
                guiComponent.o(d);
                continue;
            }
            if (guiComponent instanceof TrailingActionTextInputComponent) {
                guiComponent.o(d);
                continue;
            }
            if (guiComponent instanceof LabeledTextInputComponent) {
                guiComponent.o(d - 0.0);
                continue;
            }
            if (!(guiComponent instanceof PanelComponent)) continue;
            this.y((PanelComponent)guiComponent, d);
        }
    }

    private static Integer lambda$renderCategoryButtons$3() {
        return 0;
    }

    private void lambda$populateNearbyPlayersContainer$9(String string) {
        FriendEntry friendEntry = Vape.INSTANCE.getFriendManager().O(string);
        if (friendEntry != null) {
            Vape.INSTANCE.getFriendManager().E(friendEntry);
            Vape.INSTANCE.getNotificationManager().k("\u00a7cRemoved\u00a7r " + string + " from friends", "", 2000L);
            this.N$src$V$s1q618();
        }
    }

    private void B(String string) {
        boolean bl;
        boolean bl2 = bl = !string.isEmpty();
        if (bl) {
            for (FriendEntry friendEntry : Vape.INSTANCE.getFriendManager().getFriends()) {
                boolean bl3;
                Object object = friendEntry.s();
                String string2 = friendEntry.o();
                boolean bl4 = object != null && ((String)object).toLowerCase().contains(string);
                boolean bl5 = bl3 = string2 != null && string2.toLowerCase().contains(string);
                if (!bl4 && !bl3) continue;
                object = new ClickGuiFriendsFriendRequestComponent(friendEntry);
                ((GuiComponent)object).o(this.jG.A());
                ((ClickGuiFriendsFriendRequestComponent)object).H(() -> this.lambda$populateYourFriendsContainer$6(friendEntry));
                ((ClickGuiFriendsFriendRequestComponent)object).Z(() -> this.lambda$populateYourFriendsContainer$7(friendEntry));
                this.jX.h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, (GuiComponent)object), new Object[0]);
            }
            if (this.jX.f().isEmpty()) {
                // empty if block
            }
            return;
        }
        for (FriendEntry friendEntry : Vape.INSTANCE.getFriendManager().getFriends()) {
            ClickGuiFriendsFriendRequestComponent clickGuiFriendsFriendRequestComponent = new ClickGuiFriendsFriendRequestComponent(friendEntry);
            clickGuiFriendsFriendRequestComponent.o(this.jG.A());
            clickGuiFriendsFriendRequestComponent.H(() -> this.lambda$populateYourFriendsContainer$6(friendEntry));
            clickGuiFriendsFriendRequestComponent.Z(() -> this.lambda$populateYourFriendsContainer$7(friendEntry));
            this.jX.h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, clickGuiFriendsFriendRequestComponent), new Object[0]);
        }
        if (this.jX.f().isEmpty()) {
            MultilineTextBlockComponent multilineTextBlockComponent = new MultilineTextBlockComponent("INFO", "Enter a username in the search bar to add a friend.");
            multilineTextBlockComponent.k(this.jG.A());
            this.jX.h(new PaddedComponent(3.0, 3.0, 0.0, 0.0, multilineTextBlockComponent), new Object[0]);
        }
    }

    private void lambda$populateNearbyPlayersContainer$8(String string) {
        Vape.INSTANCE.getFriendManager().u(new Friend(string, string));
        Vape.INSTANCE.getNotificationManager().k("\u00a7aAdded\u00a7r " + string + " to friends", "", 2000L);
        this.N$src$V$s1q618();
    }

    private void d$src$V$sdtn36() {
        switch (ClickGuiFriendSourceModeSwitchMap.Z[this.jF.ordinal()]) {
            case 1: {
                this.e$src$V$sedfoj();
                break;
            }
            default: {
                this.b$src$V$scq1wg();
            }
        }
    }

    private void lambda$renderCategoryButtons$1() {
        if (this.jF != ClickGuiFriendSourceMode.MINECRAFT) {
            this.jF = ClickGuiFriendSourceMode.MINECRAFT;
            this.d$src$V$sdtn36();
        }
    }

    private void O(String string) {
        this.jG.h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, this.q("Your Friends")), "wrap");
        this.jX = new PanelComponent(this.jG.A(), 0.0);
        this.jX.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.jX.d(false);
        this.jX.F(FrameScrollbarPlacement.OUTSIDE);
        this.B(string);
        this.jG.h(this.jX, new Object[0]);
    }

    private void w$src$V$so9qd1() {
        boolean bl;
        if (this.jG == null || this.jX == null) {
            return;
        }
        double d = this.jG.L();
        boolean bl2 = bl = this.jb != null;
        if (bl) {
            double d2 = Math.max(0.0, d - 22.0);
            double d3 = Math.min(this.jb.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().y() + 1.0, d2 * 0.5);
            this.jb.u(d3);
            this.jb.Y(d3);
            this.jb.t(d3);
            double d4 = d2 - d3 - 6.0;
            this.jX.u(d4);
            this.jX.Y(d4);
            this.jX.t(d4);
        } else {
            double d5 = 11.0;
            double d6 = Math.max(0.0, d - 11.0);
            this.jX.u(d6);
            this.jX.Y(d6);
            this.jX.t(d6);
        }
    }

    private void A$src$V$rukubj() {
        OnlineStatus iterator;
        int onlineFriend = 0;
        Object object;
        ArrayList<OnlineFriend> arrayList;
        this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().S();
        PartyManager partyManager = Vape.INSTANCE.getOnlineManager().y();
        Collection<PartyInvite> collection = partyManager.n();
        boolean bl = partyManager.j() != null;
        this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, this.q("Party")), "wrap");
        boolean bl2 = false;
        if (bl) {
            PartyMemberEntryComponent partyMemberEntryComponent = new PartyMemberEntryComponent(partyManager::j);
            partyMemberEntryComponent.o(this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().A());
            this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, partyMemberEntryComponent), new Object[0]);
            bl2 = true;
        }
        for (PartyInvite partyInvite : collection) {
            object = new PartyMemberEntryComponent(partyInvite);
            ((GuiComponent)object).o(this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().A());
            this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, (GuiComponent)object), new Object[0]);
            bl2 = true;
        }
        if (!bl2) {
            this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(new PaddedComponent(0.0, 6.0, 0.0, 0.0, this.K("No active parties yet.")), "wrap");
        } else {
            this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(new SpacerComponent(0.0, 6.0), new Object[0]);
        }
        arrayList = new ArrayList<OnlineFriend>(Vape.INSTANCE.getOnlineFriendManager().g());
        arrayList.sort(Comparator.comparingInt(ClickGuiFriendsPage::lambda$renderOnlineFriendsContent$14).thenComparing(ClickGuiFriendsPage::lambda$renderOnlineFriendsContent$15, String.CASE_INSENSITIVE_ORDER));
        int n = arrayList.size();
        object = n > 0 ? "VAPE Friends (" + n + ")" : "VAPE Friends";
        this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, this.q((String)object)), "wrap");
        if (arrayList.isEmpty()) {
            this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(new PaddedComponent(0.0, 0.0, 0.0, 0.0, this.K("No VAPE friends online yet.")), "wrap");
            this.p();
            return;
        }
        EnumMap<OnlineStatus, List<OnlineFriend>> enumMap = new EnumMap<OnlineStatus, List<OnlineFriend>>(OnlineStatus.class);
        OnlineStatus[] onlineStatuses = OnlineStatus.values();
        int n2 = onlineStatuses.length;
        boolean bl3 = false;
        while (onlineFriend < n2) {
            iterator = onlineStatuses[onlineFriend];
            enumMap.put(iterator, new ArrayList<OnlineFriend>());
            ++onlineFriend;
        }
        List<OnlineFriend> uncategorizedFriends = new ArrayList<OnlineFriend>();
        for (OnlineFriend object3 : arrayList) {
            iterator = object3.F();
            if (iterator == null) {
                uncategorizedFriends.add(object3);
                continue;
            }
            enumMap.computeIfAbsent(iterator, ClickGuiFriendsPage::lambda$renderOnlineFriendsContent$16).add(object3);
        }
        boolean bl32 = false;
        for (OnlineStatus object2 : OnlineStatus.values()) {
            List<OnlineFriend> list = enumMap.getOrDefault(object2, Collections.emptyList());
            if (list.isEmpty()) continue;
            if (bl32) {
                this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(new SpacerComponent(0.0, 4.0), new Object[0]);
            }
            SimpleTextLabelComponent simpleTextLabelComponent = this.s(object2.f());
            this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, simpleTextLabelComponent), "wrap");
            for (OnlineFriend onlineFriend2 : list) {
                ClickGuiFriendsFriendListComponent clickGuiFriendsFriendListComponent = new ClickGuiFriendsFriendListComponent(onlineFriend2);
                clickGuiFriendsFriendListComponent.U(object2 != OnlineStatus.OFFLINE);
                clickGuiFriendsFriendListComponent.o(this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().A());
                clickGuiFriendsFriendListComponent.w(() -> this.lambda$renderOnlineFriendsContent$17(onlineFriend2));
                this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, clickGuiFriendsFriendListComponent), "wrap");
            }
            bl32 = true;
        }
        if (!uncategorizedFriends.isEmpty()) {
            this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(new SpacerComponent(0.0, 4.0), new Object[0]);
            SimpleTextLabelComponent simpleTextLabelComponent = this.s("Other");
            this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, simpleTextLabelComponent), "wrap");
            Iterator<OnlineFriend> iterator2 = uncategorizedFriends.iterator();
            while (iterator2.hasNext()) {
                Object object2;
                OnlineFriend onlineFriend3 = iterator2.next();
                object2 = new ClickGuiFriendsFriendListComponent(onlineFriend3);
                ((ClickGuiFriendsFriendListComponent)object2).U(false);
                ((GuiComponent)object2).o(this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().A());
                ((ClickGuiFriendsFriendListComponent)object2).w(() -> this.lambda$renderOnlineFriendsContent$18(onlineFriend3));
                this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, (GuiComponent)object2), "wrap");
            }
        }
        this.p();
    }

    private void lambda$renderOnlineFriendsContent$18(OnlineFriend onlineFriend) {
        this.jB.Z(ClickGuiFriendsFriendCardFactory.Y(onlineFriend));
    }

    private void a(FriendEntry friendEntry, PanelComponent panelComponent) {
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent("Alias");
        simpleTextLabelComponent.i(0.7);
        simpleTextLabelComponent.T$src$V$1orl066(ClickGuiFriendsPage.J.A);
        simpleTextLabelComponent.l(true);
        simpleTextLabelComponent.g(0.0f);
        simpleTextLabelComponent.c(2);
        panelComponent.h(new PaddedComponent(8.0, 0.0, 8.0, 8.0, simpleTextLabelComponent), new Object[0]);
        String[] stringArray = new String[]{friendEntry.E() != null && !friendEntry.E().equals(friendEntry.s()) ? friendEntry.E() : ""};
        TextButton textButton = new TextButton("Y", 0.7, ClickGuiFriendsPage.J.B, ClickGuiFriendsPage.J.O, null, 2.0f, 1.0f, 12.0, 13.0);
        textButton.a(true);
        textButton.c(true);
        textButton.F(false);
        textButton.h(ClickGuiFriendsPage.J.A);
        textButton.Z(false);
        TextButton textButton2 = new TextButton("N", 0.7, ClickGuiFriendsPage.J.d, ClickGuiFriendsPage.J.c, null, 2.0f, 1.0f, 12.0, 13.0);
        textButton2.a(true);
        textButton2.c(true);
        textButton2.F(false);
        textButton2.h(ClickGuiFriendsPage.J.A);
        textButton2.Z(false);
        ArrayList<TextButton> arrayList = new ArrayList<TextButton>();
        arrayList.add(textButton2);
        arrayList.add(textButton);
        FriendAliasEditInputComponent friendAliasEditInputComponent = new FriendAliasEditInputComponent(this, "Set alias...", arrayList, stringArray, textButton, textButton2, friendEntry);
        friendAliasEditInputComponent.P(true);
        friendAliasEditInputComponent.W(true);
        friendAliasEditInputComponent.o(20.0);
        friendAliasEditInputComponent.Y(20.0);
        friendAliasEditInputComponent.D(0.75f);
        friendAliasEditInputComponent.I(4.0f);
        friendAliasEditInputComponent.s(ColorAnimation.Y(ClickGuiFriendsPage.J.s));
        friendAliasEditInputComponent.W(null);
        friendAliasEditInputComponent.P(stringArray[0]);
        textButton.r(() -> this.lambda$createFriendSettingsContent$20(friendEntry, friendAliasEditInputComponent, stringArray, textButton, textButton2));
        textButton2.r(() -> ClickGuiFriendsPage.lambda$createFriendSettingsContent$21(friendAliasEditInputComponent, stringArray));
        panelComponent.h(new PaddedComponent(0.0, 2.0, 0.0, 0.0, friendAliasEditInputComponent), new Object[0]);
        ClickGuiFriendsModeToggleComponent clickGuiFriendsModeToggleComponent = new ClickGuiFriendsModeToggleComponent(this, "Active", 0.8, friendEntry);
        clickGuiFriendsModeToggleComponent.o(panelComponent.A() - 16.0);
        clickGuiFriendsModeToggleComponent.C(0.0);
        clickGuiFriendsModeToggleComponent.d(false);
        ((BooleanToggleComponent)clickGuiFriendsModeToggleComponent).Q$src$V$11xzx98();
        panelComponent.h(new PaddedComponent(0.0, 0.0, 5.0, 5.0, clickGuiFriendsModeToggleComponent), new Object[0]);
    }

    private void lambda$createFriendSettingsContent$20(FriendEntry friendEntry, TrailingActionTextInputComponent trailingActionTextInputComponent, String[] stringArray, TextButton textButton, TextButton textButton2) {
        this.N(friendEntry, trailingActionTextInputComponent.i$src$Ljava_lang_String_$1n2xf3k());
        stringArray[0] = trailingActionTextInputComponent.i$src$Ljava_lang_String_$1n2xf3k();
        textButton.Z(false);
        textButton2.Z(false);
    }

    private void D(String string) {
        boolean bl;
        boolean bl2 = bl = !string.isEmpty();
        if (!bl) {
            return;
        }
        this.jl = new PaddedComponent(0.0, 3.0, 0.0, 0.0, this.q("Results"));
        this.jG.h(this.jl, "wrap");
        this.jb = new PanelComponent(this.jG.A(), 30.0);
        this.jb.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.jb.d(false);
        this.jb.F(FrameScrollbarPlacement.OUTSIDE);
        this.jG.h(this.jb, new Object[0]);
        this.jG.h(new SpacerComponent(0.0, 6.0), new Object[0]);
    }

    public static void F(ClickGuiFriendsPage clickGuiFriendsPage) {
        clickGuiFriendsPage.N$src$V$s1q618();
    }

    private SimpleTextLabelComponent s(String string) {
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent(string);
        simpleTextLabelComponent.i(0.625);
        simpleTextLabelComponent.T$src$V$1orl066(ClickGuiFriendsPage.J.C);
        simpleTextLabelComponent.l(true);
        simpleTextLabelComponent.g(0.0f);
        simpleTextLabelComponent.c(2);
        return simpleTextLabelComponent;
    }

    private void b$src$V$scq1wg() {
        this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().S();
        this.jc = new ClickGuiFriendsNameInputListener(this, "Search friends...");
        this.jc.o(this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().A());
        this.jc.H(0.0f);
        this.jc.C(0.0);
        this.jc.V(0.0f);
        this.jc.O(0.0f);
        this.jc.W(true);
        this.jc.a(false);
        this.jc.Y(16.0);
        this.jc.D(0.75f);
        this.jc.I(4.0f);
        this.jc.s(ColorAnimation.Y(ClickGuiFriendsPage.J.s));
        this.jc.W(null);
        this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(this.jc, new Object[0]);
        if (this.jo != null && !this.jo.isEmpty()) {
            this.jc.k(this.jo);
        }
        this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(new SpacerComponent(0.0, 6.0), new Object[0]);
        String string = this.jc != null ? this.jc.i$src$Ljava_lang_String_$1n2xf3k() : "";
        String string2 = string == null ? "" : string.trim().toLowerCase();
        double d = this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().L() - this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().y() - 1.0;
        if (d < 0.0) {
            d = 0.0;
        }
        this.jG = new PanelComponent(this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().A(), d);
        this.jG.N(false);
        this.jG.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.jG.d(false);
        this.jG.t(this.jG.L());
        this.jG.F(FrameScrollbarPlacement.OUTSIDE);
        this.D(string2);
        this.O(string2);
        this.w$src$V$so9qd1();
        this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(this.jG, new Object[0]);
        this.p();
    }

    private void lambda$renderCategoryButtons$5() {
        this.jB.Z(ClickGuiFriendsThemeConfigFactory.O());
    }

    private SimpleTextLabelComponent K(String string) {
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent(string);
        simpleTextLabelComponent.i(0.625);
        simpleTextLabelComponent.T$src$V$1orl066(ClickGuiFriendsPage.J.C);
        simpleTextLabelComponent.g(0.0f);
        simpleTextLabelComponent.c(2);
        return simpleTextLabelComponent;
    }

    private String W(String string, String string2) {
        if (string == null || string.trim().isEmpty()) {
            return string2;
        }
        return string;
    }

    private void N$src$V$s1q618() {
        boolean bl;
        boolean bl2;
        if (this.jG == null) {
            return;
        }
        String string = this.jc != null ? this.jc.i$src$Ljava_lang_String_$1n2xf3k() : "";
        String string2 = string == null ? "" : string.trim().toLowerCase();
        boolean bl3 = bl2 = !string2.isEmpty();
        if (bl2) {
            boolean bl4 = this.jb == null;
            boolean bl5 = false;
            if (bl4) {
                this.jG.S();
                this.D(string2);
                this.n(string2);
                this.O(string2);
                this.w$src$V$so9qd1();
                this.jG.H(true);
                this.p();
                return;
            }
            if (this.jb != null) {
                this.n(string2);
                this.jb.H(true);
            }
            if (this.jX != null) {
                this.jX.S();
                this.B(string2);
                this.jX.H(true);
            }
            this.jG.H(true);
            this.w$src$V$so9qd1();
            this.p();
            return;
        }
        boolean bl6 = false;
        boolean bl7 = bl = this.jb != null;
        if (bl) {
            this.jG.S();
            this.jb = null;
            this.jl = null;
            this.O(string2);
            this.w$src$V$so9qd1();
            this.jG.H(true);
            this.p();
            return;
        }
        if (this.jb != null) {
            // empty if block
        }
        if (this.jX != null) {
            this.jX.S();
            this.B(string2);
            this.jX.H(true);
        }
        this.jG.H(true);
        this.w$src$V$so9qd1();
        this.p();
    }

    private void N(FriendEntry friendEntry, String string) {
        if (friendEntry instanceof Friend) {
            String string2;
            Friend friend = (Friend)friendEntry;
            String string3 = string2 = string == null ? "" : string.trim();
            if (string2.isEmpty()) {
                string2 = friend.s();
            }
            friend.T(string2);
        }
        Vape.INSTANCE.getFriendManager().m();
        OnlineFriendUiHelper.U();
    }

    public static String z(ClickGuiFriendsPage clickGuiFriendsPage, String string) {
        clickGuiFriendsPage.jo = string;
        return clickGuiFriendsPage.jo;
    }

    public static int[] P$src$AI$i29w84() {
        return j7;
    }

    private Boolean lambda$renderCategoryButtons$0() {
        return this.jF == ClickGuiFriendSourceMode.MINECRAFT;
    }

    private void lambda$populateYourFriendsContainer$7(FriendEntry friendEntry) {
        this.X(friendEntry);
    }


    static {
        ClickGuiFriendsPage.j((int[])null);
        pb = 3421952634741850111L;
    }

    private void lambda$createFriendSettingsSidecar$19(FriendEntry friendEntry, PanelComponent panelComponent) {
        this.a(friendEntry, panelComponent);
    }

    private void lambda$renderOnlineFriendsContent$17(OnlineFriend onlineFriend) {
        this.jB.Z(ClickGuiFriendsFriendCardFactory.Y(onlineFriend));
    }

    private void j$src$V$sh4enc() {
        double d = this.L$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$m6o1mi().A();
        for (GuiComponent guiComponent : this.L$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$m6o1mi().f()) {
            if (guiComponent instanceof PaddedComponent) {
                FriendModuleInteractiveComponent friendModuleInteractiveComponent = ((PaddedComponent)guiComponent).t(FriendModuleInteractiveComponent.class);
                if (friendModuleInteractiveComponent == null) continue;
                friendModuleInteractiveComponent.o(d);
                continue;
            }
            if (!(guiComponent instanceof FriendModuleInteractiveComponent)) continue;
            guiComponent.o(d);
        }
    }

    private static String lambda$renderOnlineFriendsContent$15(OnlineFriend onlineFriend) {
        String string = onlineFriend.C();
        return string == null ? "" : string;
    }

    public static void d(ClickGuiFriendsPage clickGuiFriendsPage, FriendEntry friendEntry, String string) {
        clickGuiFriendsPage.N(friendEntry, string);
    }

    private void k$src$V$sho78p() {
        this.jB.Z(ClickGuiThemeOverlayFactory.m(this.jB));
    }

    private SimpleTextLabelComponent q(String string) {
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent(string);
        simpleTextLabelComponent.i(0.75);
        simpleTextLabelComponent.T$src$V$1orl066(ClickGuiFriendsPage.J.A);
        simpleTextLabelComponent.l(true);
        simpleTextLabelComponent.g(0.0f);
        simpleTextLabelComponent.c(2);
        return simpleTextLabelComponent;
    }

    private void lambda$populateNearbyPlayersContainer$13(String string) {
        FriendEntry friendEntry = Vape.INSTANCE.getFriendManager().O(string);
        if (friendEntry != null) {
            Vape.INSTANCE.getFriendManager().E(friendEntry);
            Vape.INSTANCE.getNotificationManager().k("\u00a7cRemoved\u00a7r " + string + " from friends", "", 2000L);
            this.N$src$V$s1q618();
        }
    }

    public static void j(int[] nArray) {
        j7 = nArray;
    }
}
