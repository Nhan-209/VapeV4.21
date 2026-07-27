package gg.vape.module.none;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import func.skidline.RectData;
import gg.vape.Vape;
import gg.vape.config.PublicProfileSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventKeyPress;
import gg.vape.event.impl.EventMouseButton;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.event.impl.EventPreTick;
import gg.vape.friend.ui.EnemySettingsFrame;
import gg.vape.friend.ui.OnlineActivitySettingsFrame;
import gg.vape.friend.ui.OnlineCombatStatsSettingsFrame;
import gg.vape.friend.ui.OnlineFriendsFrame;
import gg.vape.friend.ui.OnlinePlayerPreviewSettingsFrame;
import gg.vape.friend.ui.OnlineRadarSettingsFrame;
import gg.vape.input.KeyboardInput;
import gg.vape.input.MouseInput;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.blatant.InvWalkSettingsState;
import gg.vape.module.none.ClientSettingsBooleanValue;
import gg.vape.module.none.ClientSettingsGuiBindBendable;
import gg.vape.module.none.TextGuiSettingsFrame;
import gg.vape.module.utility.inventory.HotbarSlotRuleItemPickerFrame;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryCleanerPopupFrame;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryFilterRuleEditorFrame;
import gg.vape.notification.NotificationManager;
import gg.vape.render.OffscreenRenderContext;
import gg.vape.render.ShaderGroupRenderStateManager;
import gg.vape.tutorial.TutorialFrame;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.MousePosition;
import gg.vape.ui.click.component.AnimatedIconButtonComponent;
import gg.vape.ui.click.component.DropdownSelectComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PopupMenuButtonComponent;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.component.ToolTips;
import gg.vape.ui.click.component.value.ListValueDropdownLayer;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.FrameStackManager;
import gg.vape.ui.click.frame.PopupFrame;
import gg.vape.ui.click.frame.impl.ClientSettingsFrame;
import gg.vape.ui.click.frame.impl.ClientSettingsSearchFrame;
import gg.vape.ui.click.frame.impl.ClientSettingsSectionFrame;
import gg.vape.ui.click.frame.impl.FrameMacros;
import gg.vape.ui.click.frame.impl.ModuleCategoryFrame;
import gg.vape.ui.click.frame.impl.ModuleCategoryFrameHeader;
import gg.vape.ui.click.frame.impl.ModuleSearchFrame;
import gg.vape.ui.click.frame.impl.SessionSpoofFrame;
import gg.vape.ui.click.frame.impl.VisibleModuleListFrame;
import gg.vape.ui.click.frame.impl.hud.ActiveModuleStackFrame;
import gg.vape.ui.click.frame.impl.hud.AnchoredHudModuleConfigFrame;
import gg.vape.ui.click.frame.impl.hud.ArmorStatusHudFrame;
import gg.vape.ui.click.frame.impl.hud.ClockHudFrame;
import gg.vape.ui.click.frame.impl.hud.CompassHudFrame;
import gg.vape.ui.click.frame.impl.hud.CoordinatesHudFrame;
import gg.vape.ui.click.frame.impl.hud.FpsDisplayHudFrame;
import gg.vape.ui.click.frame.impl.hud.HudEditorReturnToMainLayerFrame;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrame;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameBase;
import gg.vape.ui.click.frame.impl.hud.HudModuleFrameBase;
import gg.vape.ui.click.frame.impl.hud.HudModuleOverviewFrame;
import gg.vape.ui.click.frame.impl.hud.HudModuleSelectorFrame;
import gg.vape.ui.click.frame.impl.hud.HudSettingsFrameBase;
import gg.vape.ui.click.frame.impl.hud.KeystrokesHudFrame;
import gg.vape.ui.click.frame.impl.hud.PotionEffectsHudFrame;
import gg.vape.ui.click.frame.impl.hud.ReachDisplayHudFrame;
import gg.vape.ui.click.frame.impl.hud.ScoreboardHudFrame;
import gg.vape.ui.click.frame.impl.main.ClickGuiFrameManager;
import gg.vape.ui.click.frame.impl.main.ClickGuiLayer;
import gg.vape.ui.click.frame.impl.profile.ProfileSnapshotFrame;
import gg.vape.ui.click.frame.impl.profile.ProfilesSettingsFrame;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrame;
import gg.vape.ui.click.frame.impl.quickactions.QuickActionsFrame;
import gg.vape.ui.click.frame.impl.target.TargetInfoSettingsFrame;
import gg.vape.ui.font.FontManager;
import gg.vape.ui.font.FontSelector;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.Bendable;
import gg.vape.unmap.ColorUtil;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.util.ThreadBoundExecutor;
import gg.vape.utils.MutableColor;
import gg.vape.utils.render.BlurRegionRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderBatchManager;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.BooleanValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import java.awt.Color;
import java.awt.Point;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClientSettings
extends Mod {
    private boolean closeRequested;
    public BooleanValue O;
    private boolean initialized = false;
    public static ClientSettingsSearchFrame J;
    public static ClickGuiFrameManager f5;
    public BooleanValue fc;
    public final ModeOption c;
    public static GuiComponent fT;
    private float rainbowHue = 0.0f;
    public BooleanValue fe;
    public int f7 = 0;
    public static Color fd;
    private static final int CACHED_SEED;
    private static final List<OffscreenRenderContext> offscreenContexts;
    private double lastGuiScale = -1.0;
    public static boolean Y;
    public BooleanValue U;
    public boolean P = true;
    public BooleanValue b = BooleanValue.create(this, "Blur background", true, "Blur the background of the GUI");
    public static ModuleCategoryFrame F;
    Set<Frame> v;
    public static FrameStackManager fX;
    private static RectData lastScreenRect;
    private boolean unusedFlag = false;
    public static FrameStackManager L;
    public static ClientSettings fW;
    public BooleanValue fE = BooleanValue.create(this, "Enable Multi-Keybinding", false, "Allows you to set multiple keys to be held together to toggle modules");
    private FrameStackManager currentStack;
    public NumberValue fD;
    public ModeValue H;
    public static ModuleSearchFrame fN;
    public final ModeOption fM;
    private static final List<FrameStackManager> allStacks;
    private static HashSet<Frame> positionedFrames;
    public static FrameStackManager fG;
    public static boolean S;
    private static final boolean ALWAYS_TRUE = true;
    public static final ThreadBoundExecutor f6;
    public static FrameStackManager p;
    public final ModeOption fL;
    private static ImmutableList<Frame> frameSnapshot;
    private final boolean alwaysFalse = false;
    public ModeValue fz;
    public static FrameStackManager a;
    public BooleanValue fH;
    private final BlurRegionRenderer blurRenderer;
    private static Frame lastOpenedFrame;
    private static final HashMap<Class<?>, Frame> frameCache;
    public static ToolTips V;
    private boolean savedChatKeyState;
    public static FrameStackManager f0;
    private Frame pendingFrame;
    public static FrameStackManager t;
    public static FrameStackManager fr;
    boolean fI;
    private static int[] savedPositions;
    private static final List<Frame> allFrames;

    private void renderFrames() {
        for (Frame frame : allFrames) {
            if (!frame.V$src$Z$1xhop3l() || !frame.y$src$Z$1f55jvh()) continue;
            frame.q$src$V$1x8c1kv();
        }
    }

    public static Frame z(String string) {
        for (Frame frame : allFrames) {
            if (frame.getName() == null || !frame.getName().equalsIgnoreCase(string)) continue;
            return frame;
        }
        return null;
    }

    private void lambda$updateStandaloneState$1(PublicProfileSettings publicProfileSettings) {
        if (this.currentStack == a || this.currentStack == f5) {
            if (publicProfileSettings.P.o()) {
                if (this.currentStack == f5) {
                    return;
                }
                if (f5.l() == null) {
                    f5 = new ClickGuiFrameManager();
                }
                this.I(f5);
            } else {
                if (this.currentStack == a) {
                    return;
                }
                this.I(a);
            }
        }
    }

    public static void x(Frame frame) {
        boolean bl;
        if (frameSnapshot == null) {
            return;
        }
        if (!frameSnapshot.contains((Object)frame)) {
            return;
        }
        if (positionedFrames.contains(frame)) {
            return;
        }
        if (!frame.J$src$Z$1eqdghz() || !frame.l$src$Z$193vdc5()) {
            return;
        }
        double d = 32.0;
        double d2 = 32.0;
        do {
            bl = false;
            for (Frame frame2 : frameSnapshot) {
                RectData rectData;
                if (!ClientSettings.fW.currentStack.Y().contains(frame2) || frame2.equals(frame) || !frame2.V$src$Z$1xhop3l() || !(rectData = frame2.Q().y(2.0, 4.0)).J(d, d2)) continue;
                bl = true;
            }
            if (!((d += 2.0) + frame.A() > (double)Minecraft.G().T())) continue;
            d = 32.0;
            d2 += 2.0;
        } while (bl);
        frame.K(d);
        frame.S(d2);
        frame.l$src$V$1mibm4x();
        positionedFrames.add(frame);
    }

    @Deprecated
    public static void f(Class clazz, boolean bl) {
        Object t = ClientSettings.g(clazz);
        if (t != null) {
            ((Frame)t).t(!((GuiComponent)t).V$src$Z$1xhop3l(), bl);
            ((Frame)t).U();
        }
    }

    public static void l(Category category) {
        for (Frame frame : allFrames) {
            if (!((ModeSelection)ClientSettings.fW.fz.K()).equals(ClientSettings.fW.fM) && frame instanceof ClientSettingsSearchFrame || !(frame instanceof ModuleCategoryFrame) || !((ModuleCategoryFrame)frame).G$src$Lgg_vape_module_Category_$qyt4o7().equals(category)) continue;
            int n = ((ModuleCategoryFrame)frame).A$src$I$wwnvku();
            if (!(frame.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc() instanceof ModuleCategoryFrameHeader)) continue;
            ((ModuleCategoryFrameHeader)frame.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc()).y(n);
        }
    }

    public boolean H(EventKeyPress eventKeyPress) {
        int n = eventKeyPress.getKey();
        boolean bl = eventKeyPress.isDown();
        int n2 = 70;
        if ((KeyboardInput.isKeyDown(163) || KeyboardInput.isKeyDown(162)) && n == 70 && bl) {
            ClickGuiFrameManager clickGuiFrameManager;
            if (this.currentStack instanceof ClickGuiFrameManager && (clickGuiFrameManager = (ClickGuiFrameManager)this.currentStack).l() != null) {
                return clickGuiFrameManager.l().u$src$Z$1fj0nz();
            }
            if (!this.fL.o()) {
                if (this.c.o()) {
                    fN.n$src$Lgg_vape_ui_click_frame_impl_ModuleSearchFrameHe$xia8v2().s();
                    fN.n$src$Lgg_vape_ui_click_frame_impl_ModuleSearchFrameHe$xia8v2().A$src$Lgg_vape_ui_click_component_input_ModuleSearchIn$1efzz7n().b$src$V$17wa4kz();
                } else if (this.fM.o()) {
                    J.o$src$Lgg_vape_ui_click_frame_impl_ClientSettingsSearc$hz70uz().O$src$Lgg_vape_ui_click_component_input_ModuleSearchIn$1smhagf().k("");
                    J.K$src$V$1nbah4f();
                    J.o$src$Lgg_vape_ui_click_frame_impl_ClientSettingsSearc$hz70uz().V$src$V$enocyv();
                }
            }
            return true;
        }
        return false;
    }

    public static boolean P(GuiComponent guiComponent) {
        return fT == null || fT instanceof TextInputComponentBase || fT instanceof DropdownSelectComponent || fT instanceof PopupMenuButtonComponent || fT.equals(guiComponent) || fT.f().contains(guiComponent);
    }

    public boolean R() {
        return this.closeRequested;
    }

    @Override
    public boolean k() {
        return false;
    }

    private static Throwable passThrough(Throwable throwable) {
        return throwable;
    }

    @Override
    public void F() {
        super.F();
        if (Minecraft.currentScreen().getObject() != null && !Minecraft.currentScreen().isInstance(MappedClasses.u5)) {
            return;
        }
        if (this.r$src$Z$14eylz9()) {
            Minecraft.R();
            this.savedChatKeyState = Minecraft.gameSettings().m$src$Z$1s8ei5l();
            Minecraft.gameSettings().P(false);
            this.P = false;
        } else {
            Minecraft.gameSettings().P(this.savedChatKeyState);
            Vape.debugLog("Gui Closed 2");
            this.P = true;
        }
    }

    private static void lambda$new$0(ModeValue modeValue) {
        J.N$src$V$1ncxuwi();
    }

    public static boolean p$src$Z$1h1jsli() {
        return true;
    }

    public void p() {
        this.updateFrames();
        NotificationManager notificationManager = Vape.INSTANCE.getNotificationManager();
        notificationManager.J();
    }

    public void C(int n, int n2, int n3) {
        MousePosition mousePosition = RenderUtils.h();
        GuiMouseEvent guiMouseEvent = new GuiMouseEvent(mousePosition.O, mousePosition.H, MouseButton.A(n3));
        if (guiMouseEvent.fire()) {
            return;
        }
        if (fT != null) {
            boolean bl = !(fT instanceof TextInputComponentBase);
            fT.D(guiMouseEvent);
            if (bl) {
                return;
            }
        }
        CopyOnWriteArrayList<Frame> copyOnWriteArrayList = new CopyOnWriteArrayList<Frame>(this.currentStack.Y());
        Collections.reverse(copyOnWriteArrayList);
        if (!(this.currentStack instanceof ClickGuiFrameManager)) {
            for (Frame object : copyOnWriteArrayList) {
                if (!object.V$src$Z$1xhop3l() || !object.t$src$Lgg_vape_ui_click_component_gui_TextButton_$p5ute3().V$src$Z$1xhop3l() || !object.t$src$Lgg_vape_ui_click_component_gui_TextButton_$p5ute3().w$src$Z$e457mb()) continue;
                object.t$src$Lgg_vape_ui_click_component_gui_TextButton_$p5ute3().P$src$V$q7uwbv();
                return;
            }
        }
        boolean bl = false;
        block1: for (Frame frame : copyOnWriteArrayList) {
            if (!frame.V$src$Z$1xhop3l() || this.currentStack instanceof ClickGuiFrameManager && ((ClickGuiFrameManager)this.currentStack).i(frame)) continue;
            for (GuiMouseListener guiMouseListener : frame.o$src$Ljava_util_List_$10z72du()) {
                if (guiMouseListener.Q(new Point(guiMouseEvent.getX(), guiMouseEvent.getY()))) {
                    bl = true;
                    break;
                }
                guiMouseListener.g(new Point(guiMouseEvent.getX(), guiMouseEvent.getY()), guiMouseEvent.getAction().G());
            }
            for (GuiMouseListener guiMouseListener : frame.O$src$Ljava_util_List_$148rlrm()) {
                if (!guiMouseListener.Q(new Point(guiMouseEvent.getX(), guiMouseEvent.getY()))) continue;
                bl = true;
                continue block1;
            }
        }
        if (!bl) {
            for (Frame frame : copyOnWriteArrayList) {
                boolean bl2;
                if (!frame.V$src$Z$1xhop3l()) continue;
                boolean bl3 = bl2 = this.currentStack instanceof ClickGuiFrameManager && ((ClickGuiFrameManager)this.currentStack).i(frame);
                if (!bl2) {
                    frame.e(guiMouseEvent);
                }
                if (frame.G(guiMouseEvent.getX(), guiMouseEvent.getY())) {
                    if (bl2) {
                        if (guiMouseEvent.getAction() != MouseButton.LEFT_CLICK) break;
                        f5.G(ClickGuiLayer.OVERLAYS);
                        frame.U();
                        frame.D(guiMouseEvent);
                        break;
                    }
                    if (frame instanceof HudModuleConfigFrameBase) {
                        HudModuleConfigFrameBase hudModuleConfigFrameBase = (HudModuleConfigFrameBase)frame;
                        if (!hudModuleConfigFrameBase.h$src$Z$1tlh1co()) continue;
                    }
                    frame.U();
                    frame.D(guiMouseEvent);
                    break;
                }
                if (!(frame instanceof HudModuleFrameBase)) continue;
                HudModuleFrameBase hudModuleFrameBase = (HudModuleFrameBase)frame;
                hudModuleFrameBase.i(false);
            }
        }
    }

    public void u$src$V$1h4argv() {
        this.e(false);
    }

    public static Frame g$src$Lgg_vape_ui_click_frame_Frame_$b7tr6h() {
        return lastOpenedFrame;
    }

    @EventHandler
    public void Y(EventMouseButton eventMouseButton) {
        if (!this.P) {
            eventMouseButton.setCancelled(true);
        }
    }

    public JsonArray J$src$Lcom_google_gson_JsonArray_$albj9k() {
        JsonArray jsonArray = new JsonArray();
        for (Frame frame : ClientSettings.G()) {
            if (frame.getName().startsWith("sidecar_")) continue;
            jsonArray.add((JsonElement)frame.Z());
        }
        return jsonArray;
    }

    public static void q(Frame frame) {
        if (!frame.d$src$Z$1lx9d06()) {
            return;
        }
        ClientSettings.fW.pendingFrame = frame;
    }

    public static void j(OffscreenRenderContext offscreenRenderContext) {
        offscreenContexts.add(offscreenRenderContext);
    }

    public void m() {
        this.fI = true;
    }

    public boolean v() {
        return this.P;
    }

    public void X(EventPreRenderTick eventPreRenderTick) {
        Vape.INSTANCE.getClientSettings().w.F();
        double d = Vape.INSTANCE.getClientSettings().s();
        if (this.lastGuiScale != d && this.lastGuiScale != -1.0) {
            FontManager fontManager = Vape.INSTANCE.getFontManager();
            for (Map<Integer, SmoothFontRenderer> map : fontManager.n().values()) {
                for (SmoothFontRenderer smoothFontRenderer : map.values()) {
                    smoothFontRenderer.f();
                }
            }
            this.b$src$V$1gtuo70();
        }
        this.lastGuiScale = d;
    }

    public static void M$src$V$1giazqf() {
        for (Frame frame : allFrames) {
            if (!(frame instanceof ModuleCategoryFrame) || frame instanceof ClientSettingsSearchFrame) continue;
            int n = ((ModuleCategoryFrame)frame).A$src$I$wwnvku();
            if (!(frame.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc() instanceof ModuleCategoryFrameHeader)) continue;
            ((ModuleCategoryFrameHeader)frame.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc()).y(n);
        }
    }

    public static void K(PopupFrame popupFrame) {
        FrameStackManager frameStackManager = fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v();
        f6.execute(() -> ClientSettings.lambda$removePopup$3(frameStackManager, popupFrame));
        popupFrame.Q$src$Lgg_vape_ui_click_frame_Frame_$1y8ivjg().s$src$Ljava_util_ArrayList_$1a2240q().remove(popupFrame);
    }

    public void D() {
        MutableColor mutableColor = Vape.INSTANCE.getClientSettings().w.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
        float[] fArray = new float[3];
        Color.RGBtoHSB(((Color)mutableColor).getRed(), ((Color)mutableColor).getGreen(), ((Color)mutableColor).getBlue(), fArray);
        this.rainbowHue = fArray[0];
    }

    public void j(JsonArray jsonArray) {
        if (jsonArray.size() == 0) {
            return;
        }
        jsonArray = jsonArray.get(0).getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); ++i) {
            try {
                JsonObject jsonObject;
                JsonElement jsonElement = jsonArray.get(i);
                if (!jsonElement.isJsonObject() || jsonElement.isJsonNull() || (jsonObject = jsonElement.getAsJsonObject()).get("title") == null || jsonObject.get("title").isJsonNull()) continue;
                for (Frame frame : ClientSettings.G()) {
                    if (!frame.getName().equals(jsonObject.get("title").getAsString())) continue;
                    frame.t(jsonObject);
                }
                continue;
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public static void p(Frame frame) {
        FrameStackManager frameStackManager = fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v();
        f6.execute(() -> ClientSettings.lambda$removePopups$4(frame, frameStackManager));
        frame.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa().s$src$Ljava_util_ArrayList_$1a2240q().clear();
    }

    public static ArrayList<PopupFrame> a$src$Ljava_util_ArrayList_$1scwlwh() {
        ArrayList<PopupFrame> arrayList = new ArrayList<PopupFrame>();
        for (Frame frame : ClientSettings.fW.currentStack.Y()) {
            arrayList.addAll(frame.s$src$Ljava_util_ArrayList_$1a2240q());
        }
        return arrayList;
    }

    public void i() {
        int n = OpenGlBackendHolder.d.K(3009);
        float f = OpenGlBackendHolder.d.u(3010);
        OpenGlBackendHolder.d.k(516, 0.0f);
        if (fW.v()) {
            OpenGlBackendHolder.d.m();
            double d = Vape.INSTANCE.getClientSettings().s();
            OpenGlBackendHolder.d.G(d, d, d);
            if (fW.v()) {
                this.renderFrames();
                NotificationManager notificationManager = Vape.INSTANCE.getNotificationManager();
                notificationManager.J();
            }
            OpenGlBackendHolder.d.F();
        }
        OpenGlBackendHolder.d.k(n, f);
    }

    public static int[] A() {
        return savedPositions;
    }

    public static void y(String string) {
        Frame frame = null;
        for (Frame frame2 : allFrames) {
            if (!(frame2 instanceof ModuleCategoryFrame) || !((ModuleCategoryFrame)frame2).L$src$Ljava_lang_String_$ahld16().equalsIgnoreCase(string)) continue;
            frame2.Z(!frame2.V$src$Z$1xhop3l());
            frame = frame2;
        }
        if (frame != null) {
            frame.U();
        }
    }

    @Override
    public void t() {
    }

    private static void registerFrame(Frame frame, FrameStackManager ... stacks) {
        allFrames.add(frame);
        for (FrameStackManager stack : stacks) {
            stack.q(frame);
            if (allStacks.contains(stack)) continue;
            allStacks.add(stack);
        }
    }

    private void lambda$update$2(ModeValue modeValue) {
        this.updateStandaloneState();
    }

    private void removeClosedPopups() {
        ArrayList<PopupFrame> arrayList = new ArrayList<PopupFrame>();
        for (Frame frame : this.currentStack.Y()) {
            for (PopupFrame popupFrame : frame.s$src$Ljava_util_ArrayList_$1a2240q()) {
                if (!popupFrame.c$src$Z$1kex42k()) continue;
                arrayList.add(popupFrame);
            }
        }
        for (PopupFrame popupFrame : arrayList) {
            popupFrame.Q$src$Lgg_vape_ui_click_frame_Frame_$1y8ivjg().s$src$Ljava_util_ArrayList_$1a2240q().remove(popupFrame);
            this.currentStack.m(popupFrame);
        }
        if (fT instanceof DropdownSelectComponent) {
            ((DropdownSelectComponent)fT).A$src$V$1rc4p11();
        }
    }

    private void updateFrames() {
        Frame frame;
        Object object;
        this.currentStack.A();
        if (this.fI) {
            for (Frame copyOnWriteArrayList2 : this.currentStack.Y()) {
                copyOnWriteArrayList2.l$src$V$1mibm4x();
            }
            this.fI = false;
        }
        ArrayList<Frame> arrayList = new ArrayList<Frame>(this.currentStack.Y());
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            object = (Frame)iterator.next();
            ((Frame)object).A$src$V$4ceaf0();
        }
        this.D();
        CopyOnWriteArrayList<Frame> copyOnWriteArrayList = new CopyOnWriteArrayList<Frame>();
        copyOnWriteArrayList.addAll(arrayList);
        CopyOnWriteArrayList<Frame> frameSnapshot = new CopyOnWriteArrayList<Frame>();
        frameSnapshot.addAll(copyOnWriteArrayList);
        Collections.reverse(copyOnWriteArrayList);
        MouseInput.O().S();
        Iterator<Frame> iterator2 = frameSnapshot.iterator();
        while (iterator2.hasNext()) {
            frame = iterator2.next();
            if (frame instanceof TutorialFrame) continue;
            this.renderFrame(frame, copyOnWriteArrayList);
        }
        MouseInput.O().resetScrollDelta();
        if (fT instanceof DropdownSelectComponent || fT instanceof PopupMenuButtonComponent) {
            boolean bl = fT.Z$src$Z$16e8vsp();
            fT.d(false);
            fT.c();
            fT.d(bl);
            if (fT.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb() != null && fT.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().k$src$Z$if6xeb()) {
                fT.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().M();
            }
        }
        iterator2 = frameSnapshot.iterator();
        while (iterator2.hasNext()) {
            frame = iterator2.next();
            if (frame.V$src$Z$1xhop3l() && frame.a$src$Z$1f30q5a() && !(this.currentStack instanceof ClickGuiFrameManager)) {
                frame.t$src$Lgg_vape_ui_click_component_gui_TextButton_$p5ute3().Z(true);
                frame.t$src$Lgg_vape_ui_click_component_gui_TextButton_$p5ute3().c();
                if (!frame.t$src$Lgg_vape_ui_click_component_gui_TextButton_$p5ute3().t()) continue;
                frame.t$src$Lgg_vape_ui_click_component_gui_TextButton_$p5ute3().J();
                continue;
            }
            frame.t$src$Lgg_vape_ui_click_component_gui_TextButton_$p5ute3().Z(false);
        }
        if (V != null) {
            V.c();
        }
    }

    private static void lambda$removePopups$4(Frame frame, FrameStackManager frameStackManager) {
        for (PopupFrame popupFrame : frame.s$src$Ljava_util_ArrayList_$1a2240q()) {
            frameStackManager.m(popupFrame);
        }
    }

    private static void registerHudFrames() {
        ClientSettings.registerFrame(new CompassHudFrame().Y(Minecraft.J() / 4 - 154, 38.0), t, a);
        ClientSettings.registerFrame(new KeystrokesHudFrame().Y(40.0, 40.0), t, a);
        ClientSettings.registerFrame(new ArmorStatusHudFrame().Y(40.0, 150.0), t, a);
        ClientSettings.registerFrame(new ClockHudFrame().Y(Minecraft.J() / 2 - 90, 10.0), t, a);
        ClientSettings.registerFrame(new PotionEffectsHudFrame().Y(100.0, 150.0), t, a);
        ClientSettings.registerFrame(new FpsDisplayHudFrame().Y(140.0, 40.0), t, a);
        ClientSettings.registerFrame(new CoordinatesHudFrame().Y(140.0, 70.0), t, a);
        ClientSettings.registerFrame(new ReachDisplayHudFrame().Y(140.0, 110.0), t, a);
        if (ForgeVersion.MC_1_20_6.v()) {
            ClientSettings.registerFrame(new ScoreboardHudFrame(), t, a);
        }
    }

    public boolean l$src$Z$1gzcm82() {
        FrameStackManager frameStackManager = this.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v();
        return frameStackManager.equals(a) || frameStackManager instanceof ClickGuiFrameManager;
    }

    private static void lambda$removePopup$3(FrameStackManager frameStackManager, PopupFrame popupFrame) {
        frameStackManager.m(popupFrame);
    }

    public Color O$src$Ljava_awt_Color_$19t4jn1() {
        if (Vape.INSTANCE.getClientSettings().w.g()) {
            this.rainbowHue = (float)((double)this.rainbowHue - 0.03);
            if (this.rainbowHue <= 0.0f) {
                this.rainbowHue = 1.0f - -this.rainbowHue;
            }
            return ColorUtil.Y(this.rainbowHue, 0.9f, 1.0f);
        }
        return Vape.INSTANCE.getClientSettings().w.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
    }

    public static void w$src$V$1h5ecnl() {
        for (FrameStackManager frameStackManager : allStacks) {
            frameStackManager.Y().clear();
        }
        allFrames.clear();
        Vape.INSTANCE.initializeRender();
        ClientSettings.registerHudFrames();
        ClientSettings.registerFrame(new ActiveModuleStackFrame(), t, a);
        fN = new ModuleSearchFrame();
        ClientSettings.registerFrame(fN, a);
        J = new ClientSettingsSearchFrame();
        ClientSettings.registerFrame(J, a);
        ClientSettings.registerFrame(new ClientSettingsFrame(), a);
        ClientSettings.registerFrame(new ClientSettingsSectionFrame(), a);
        F = new ModuleCategoryFrame(Category.g);
        ClientSettings.registerFrame(F, a);
        Vape vape = Vape.INSTANCE;
        if (vape.isFeatureDisabled()) {
            ClientSettings.registerFrame(new ModuleCategoryFrame(Category.w), a);
        }
        ClientSettings.registerFrame(new ModuleCategoryFrame(Category.k), a);
        ClientSettings.registerFrame(new ModuleCategoryFrame(Category.Y), a);
        ClientSettings.registerFrame(new ModuleCategoryFrame(Category.m), a);
        ClientSettings.registerFrame(new ModuleCategoryFrame(Category.M), a);
        ClientSettings.registerFrame(new VisibleModuleListFrame(), a);
        ClientSettings.registerFrame(new ProfilesSettingsFrame(), a);
        ClientSettings.registerFrame(new FrameMacros(), a);
        ClientSettings.registerFrame(new OnlineFriendsFrame(), a);
        ClientSettings.registerFrame(new QuickActionsFrame(), a);
        ClientSettings.registerFrame(new OnlinePlayerPreviewSettingsFrame(), a);
        ClientSettings.registerFrame(new TextGuiSettingsFrame(), a);
        ClientSettings.registerFrame(new OnlineCombatStatsSettingsFrame(), a);
        ClientSettings.registerFrame(new OnlineRadarSettingsFrame(), a);
        ClientSettings.registerFrame(new TargetInfoSettingsFrame(), a);
        ClientSettings.registerFrame(new OnlineActivitySettingsFrame(), a);
        ClientSettings.registerFrame(new EnemySettingsFrame(), a);
        ClientSettings.registerFrame(new HotbarSlotRuleItemPickerFrame(), L);
        ClientSettings.registerFrame(ClientSettings.g(HotbarSlotRuleItemPickerFrame.class).D$src$Lgg_vape_module_utility_inventory_HotbarSlotRule$dqviyt(), L);
        ClientSettings.registerFrame(new PublicProfilesFrame(), f0);
        ClientSettings.registerFrame(new HudModuleSelectorFrame(), t);
        ClientSettings.registerFrame(HudModuleSelectorFrame.WN, t);
        ClientSettings.registerFrame(new HudModuleOverviewFrame(), t);
        ClientSettings.registerFrame(ClientSettings.g(HudModuleOverviewFrame.class).s$src$Lgg_vape_ui_click_frame_impl_hud_HudModuleOvervi$1xo3dwo(), t);
        ClientSettings.registerFrame(new HudEditorReturnToMainLayerFrame(), t);
        ClientSettings.registerFrame(new HudModuleConfigFrame(), t);
        ClientSettings.registerFrame(new SessionSpoofFrame(), fG);
        ClientSettings.registerFrame(new ProfileSnapshotFrame(), fr);
        ClientSettings.registerFrame(new InventoryCleanerPopupFrame(), p);
        ClientSettings.registerFrame(new InventoryFilterRuleEditorFrame(), p);
        frameSnapshot = ImmutableList.copyOf(allFrames);
        ClientSettings.M$src$V$1giazqf();
        VisibleModuleListFrame.e();
        S = true;
    }

    @EventHandler
    public void I(EventPreRenderTick eventPreRenderTick) {
        if (this.pendingFrame != null) {
            this.currentStack.v(this.pendingFrame);
            this.pendingFrame.s$src$Ljava_util_ArrayList_$1a2240q().forEach(PopupFrame::A$src$V$4ceaf0);
            lastOpenedFrame = this.pendingFrame;
            this.pendingFrame = null;
        }
    }

    public void b$src$V$1gtuo70() {
        for (FrameStackManager object : allStacks) {
            for (Frame frame : object.Y()) {
                if (!frame.n$src$Z$1fa61uz()) continue;
                frame.j(true);
                frame.H(true);
            }
        }
        if (f5 != null) {
            for (Frame frame : f5.Y()) {
                if (!frame.n$src$Z$1fa61uz()) continue;
                frame.j(true);
                frame.H(true);
            }
        }
        ClientSettings.g(ModuleSearchFrame.class).p();
        ClientSettings.g(HudEditorReturnToMainLayerFrame.class).Z$src$V$1vz8z77();
    }

    @Deprecated
    public static void T(Class clazz) {
        ClientSettings.f(clazz, true);
    }

    public static <T extends PopupFrame> T g(GuiComponent guiComponent, GuiComponent guiComponent2, Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getConstructor(GuiComponent.class, GuiComponent.class);
            PopupFrame popupFrame = (PopupFrame)constructor.newInstance(guiComponent, guiComponent2);
            ClientSettings.fW.currentStack.Y().add(popupFrame);
            guiComponent.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa().s$src$Ljava_util_ArrayList_$1a2240q().add(popupFrame);
            return (T)popupFrame;
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
            return null;
        }
    }

    public static void d() {
        for (Frame frame : ClientSettings.fW.currentStack.Y()) {
            if (!(frame instanceof ListValueDropdownLayer)) continue;
            ((ListValueDropdownLayer)frame).e();
        }
    }

    static {
        ClientSettings.Z(null);
        long l = 1733723352155029506L;
        CACHED_SEED = (int)l;
        offscreenContexts = new ArrayList<OffscreenRenderContext>();
        fd = Color.WHITE;
        allFrames = new ArrayList<Frame>();
        allStacks = new ArrayList<FrameStackManager>();
        a = new FrameStackManager();
        t = new FrameStackManager();
        f0 = new FrameStackManager();
        f5 = new ClickGuiFrameManager();
        L = new FrameStackManager();
        fG = new FrameStackManager();
        fr = new FrameStackManager();
        p = new FrameStackManager();
        fX = new FrameStackManager();
        f6 = new ThreadBoundExecutor();
        positionedFrames = new HashSet();
        frameCache = new HashMap();
    }

    public void e() {
        try {
            GuiRenderPrimitives.Y();
            RenderUtils.g();
            if (!GuiRenderPrimitives.d()) {
                if (this.b.L().booleanValue()) {
                    ShaderGroupRenderStateManager.Q().K();
                } else {
                    ShaderGroupRenderStateManager.Q().f();
                }
            } else if (this.b.L().booleanValue()) {
                int n = Minecraft.J();
                int n2 = Minecraft.h();
                this.blurRenderer.L(n, n2);
                this.blurRenderer.t(0, 0, 16.0f, 0.0f);
            }
            RectData rectData = new RectData(0.0, 0.0, Minecraft.J(), Minecraft.h());
            if (lastScreenRect != null && (rectData.e() != lastScreenRect.e() || rectData.R() != lastScreenRect.R())) {
                this.M(lastScreenRect, rectData);
            }
            lastScreenRect = rectData;
            OpenGlBackendHolder.d.m();
            double d = Vape.INSTANCE.getClientSettings().s();
            OpenGlBackendHolder.d.G(d, d, d);
            try {
                f6.runPending();
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            this.p();
            OpenGlBackendHolder.d.F();
            RenderUtils.f();
            GuiRenderPrimitives.D();
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
        OpenGlBackendHolder.d.q(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static <T extends Frame> T g(Class<T> clazz) {
        Frame frame = frameCache.get(clazz);
        if (frame != null) {
            return (T)frame;
        }
        for (Frame frame2 : allFrames) {
            if (!frame2.getClass().getCanonicalName().equals(clazz.getCanonicalName())) continue;
            frameCache.put(clazz, frame2);
            return (T)frame2;
        }
        return null;
    }

    private void renderFrame(Frame frame, List<Frame> list) {
        if (!frame.V$src$Z$1xhop3l()) {
            return;
        }
        frame.c();
        if (this.currentStack instanceof ClickGuiFrameManager && ((ClickGuiFrameManager)this.currentStack).i(frame)) {
            if (frame instanceof HudModuleFrameBase) {
                HudModuleFrameBase hudModuleFrameBase = (HudModuleFrameBase)frame;
                boolean bl = frame.t();
                if (bl) {
                    boolean bl2 = true;
                    for (Frame frame2 : list) {
                        if (frame2.equals(frame)) break;
                        if (!frame2.V$src$Z$1xhop3l() || !frame2.t()) continue;
                        bl2 = false;
                    }
                    hudModuleFrameBase.U(bl2);
                } else {
                    hudModuleFrameBase.U(false);
                }
            }
            return;
        }
        if (frame.t()) {
            boolean bl = true;
            for (Frame frame3 : list) {
                if (frame3.equals(frame)) break;
                if (!frame3.V$src$Z$1xhop3l() || !frame3.t()) continue;
                bl = false;
            }
            if (bl) {
                frame.J();
            }
        }
    }

    public static void P(Frame frame) {
        positionedFrames.add(frame);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.r$src$V$1h2ndos();
        InvWalkSettingsState.L(Minecraft.gameSettings().y$src$Lgg_vape_wrapper_impl_KeyBinding_$1hvjjoh(), false);
    }

    public void l() {
        if (this.P) {
            this.i();
            if (GuiRenderPrimitives.d()) {
                RenderBatchManager.M().G(0.0f);
            }
        }
    }

    private void updateStandaloneState() {
        PublicProfileSettings publicProfileSettings = Vape.INSTANCE.getPublicProfileSettings();
        if (publicProfileSettings.n.L$src$Z$1a65ikz()) {
            return;
        }
        f6.execute(() -> this.lambda$updateStandaloneState$1(publicProfileSettings));
    }

    public void T() {
        double d = 32.0;
        double d2 = 32.0;
        double d3 = 0.0;
        for (Frame frame : frameSnapshot) {
            if (!frame.J$src$Z$1eqdghz() || !frame.l$src$Z$193vdc5()) continue;
            if (d + frame.A() > (double)Minecraft.G().T()) {
                d = 24.0;
                d2 += d3 + 8.0;
                d3 = 0.0;
            }
            if (frame.L() > d3) {
                d3 = frame.L();
            }
            frame.K(d);
            frame.S(d2);
            frame.l$src$V$1mibm4x();
            d += frame.A() + 2.0;
        }
    }

    public static void h$src$V$1gx5fr6() {
        positionedFrames.clear();
    }

    public void N(Frame frame, Frame frame2) {
        this.currentStack.R(frame, frame2);
    }

    @Override
    protected Bendable C$src$Lgg_vape_unmap_Bendable_$1we4j6l() {
        return new ClientSettingsGuiBindBendable(this);
    }

    public static List<Frame> G() {
        return allFrames;
    }

    public ClientSettings() {
        super("GUI", 161, 0, Category.b, "Shift click any module button to bind it to a key.\n(You can shift click this to bind the GUI)\nHold TAB to view modules binds.");
        this.U = BooleanValue.create(this, "GUI bind indicator", true, "Displays a message indicating your GUI keybind upon injecting.\nI.E. \"Press RSHIFT to open GUI\"");
        this.fH = new ClientSettingsBooleanValue(this, this, "Smooth Font", true);
        this.fe = BooleanValue.create(this, "Show tooltips", true, "Toggles visibility of these");
        this.fD = NumberValue.create(this, "Rainbow speed", "#.#", "", 0.1, 1.0, 10.0, 0.1, "Adjusts the speed of rainbow values");
        this.H = (ModeValue)ModeValue.create((Object)this, "Language", FontSelector.j, FontSelector.j, FontSelector.S, FontSelector.c, FontSelector.a, FontSelector.P).n(false);
        this.O = BooleanValue.create(this, "Show legit mode", true, "Shows the button to switch to the legit mod menu");
        this.fc = BooleanValue.create(this, "Show enabled count", true, "Shows the number of enabled modules in the standalone gui");
        this.blurRenderer = new BlurRegionRenderer(0, 0);
        this.currentStack = a;
        this.v = new HashSet<Frame>(allFrames);
        fW = this;
        this.c = new ModeOption("Floating", 0.8);
        this.fM = new ModeOption("Integrated", 0.8);
        this.fL = new ModeOption("None", 0.8);
        this.fz = ModeValue.create((Object)this, "Search bar style", "Switch between search bar styles", (ModeSelection)this.c, this.fM, this.fL, this.c);
        PublicProfileSettings publicProfileSettings = Vape.INSTANCE.getPublicProfileSettings();
        publicProfileSettings.R.K(publicProfileSettings.r);
        publicProfileSettings.R.K(publicProfileSettings.A);
        publicProfileSettings.R.K(publicProfileSettings.H);
        this.fz.B(ClientSettings::lambda$new$0);
        this.addValue(this.b, this.fE, this.U, this.fe, this.fD, this.fz);
    }

    public void r$src$V$1h2ndos() {
        if (Vape.INSTANCE.getModManager().getMod(ClientSettings.class).b.L().booleanValue()) {
            ShaderGroupRenderStateManager.Q().f();
        }
    }

    public Color C(Object object) {
        if (!MappedClasses.zm.isInstance(object)) {
            return null;
        }
        if (object.equals(Minecraft.thePlayer())) {
            return null;
        }
        EntityLivingBase entityLivingBase = new EntityLivingBase(object);
        float f = entityLivingBase.getDistanceToEntity(Minecraft.thePlayer());
        if (MappedClasses.lG.isInstance(object)) {
            if (Vape.INSTANCE.getFriendManager().E(entityLivingBase.getName()) && Vape.INSTANCE.getFriendManager().q.L().booleanValue()) {
                return Vape.INSTANCE.getFriendManager().R.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
            }
            if (Vape.INSTANCE.getEnemyManager().q(entityLivingBase.getName()) && Vape.INSTANCE.getEnemyManager().p.L().booleanValue()) {
                return Vape.INSTANCE.getEnemyManager().i.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
            }
            return Color.WHITE;
        }
        return null;
    }

    public void M(RectData rectData, RectData rectData2) {
        this.b$src$V$1gtuo70();
    }

    public static void Z(int[] nArray) {
        savedPositions = nArray;
    }

    public void I(FrameStackManager frameStackManager) {
        fT = null;
        this.removeClosedPopups();
        V = null;
        FrameStackManager frameStackManager2 = this.currentStack;
        if (frameStackManager2 instanceof ClickGuiFrameManager) {
            ClickGuiFrameManager clickGuiFrameManager = (ClickGuiFrameManager)frameStackManager2;
            if (clickGuiFrameManager.l() != null) {
                clickGuiFrameManager.l().K$src$V$sfnnd();
            }
            clickGuiFrameManager.G();
        }
        for (Frame frame : allFrames) {
            if (!(frame instanceof HudModuleFrameBase)) continue;
            HudModuleFrameBase hudModuleFrameBase = (HudModuleFrameBase)frame;
            hudModuleFrameBase.i(false);
            hudModuleFrameBase.N$src$V$bhucvl();
            AnchoredHudModuleConfigFrame<AnimatedIconButtonComponent> anchoredHudModuleConfigFrame = hudModuleFrameBase.r$src$Lgg_vape_ui_click_frame_impl_hud_AnchoredHudModu$9c1t7s();
            if (frameStackManager2 != null) {
                frameStackManager2.m(anchoredHudModuleConfigFrame);
            }
            frameStackManager.m(anchoredHudModuleConfigFrame);
        }
        this.currentStack = frameStackManager;
        for (Frame frame : frameStackManager.Y()) {
            if (!frame.n$src$Z$1fa61uz()) continue;
            frame.b$src$V$1f3kin7();
        }
    }

    public void e(boolean bl) {
        this.P = bl;
    }

    public FrameStackManager b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v() {
        return this.currentStack;
    }

    public void d(EventPreTick eventPreTick) {
        if (!this.initialized) {
            this.initialized = true;
            Vape.INSTANCE.getPublicProfileSettings().n.B(this::lambda$update$2);
            this.updateStandaloneState();
        }
        try {
            if (Minecraft.currentScreen().getObject() != null) {
                if (Minecraft.currentScreen().isInstance(MappedClasses.u5) && this.a().K() && this.P) {
                    this.F();
                    this.I(fG);
                } else if (this.P || !this.a().K()) {
                    // empty if block
                }
                if (this.currentStack.equals(fG) && !Minecraft.currentScreen().isInstance(MappedClasses.u5)) {
                    this.I(a);
                }
            } else if (!this.P) {
                if (ForgeVersion.MC_1_16_5.d()) {
                    if (Minecraft.s().Z()) {
                        this.F();
                        return;
                    }
                } else if (Minecraft.a()) {
                    this.F();
                    return;
                }
            }
            if (fT != null && !fT.V$src$Z$1xhop3l()) {
                fT = null;
            }
            this.v.clear();
            this.v.addAll(allFrames);
            this.v.addAll(this.currentStack.Y());
            if (ClientSettings.fW.P) {
                for (Frame frame : allFrames) {
                    if (!(frame instanceof HudSettingsFrameBase)) continue;
                    this.v.add(frame);
                }
            }
            for (Frame frame : this.v) {
                try {
                    frame.T$src$V$1wse0de();
                }
                catch (Exception exception) {
                    Vape.debugLog("" + frame.getName());
                    Vape.logThrowable(exception);
                }
            }
            if (this.f7++ > 20) {
                this.f7 = 0;
            }
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    public void D(boolean bl) {
        this.closeRequested = bl;
    }

    @Override
    public void u(EventKeyPress eventKeyPress) {
        super.u(eventKeyPress);
        if (!this.P) {
            if (eventKeyPress.getKey() == 27 && eventKeyPress.isDown()) {
                ClickGuiFrameManager clickGuiFrameManager;
                this.P = true;
                this.Y(false);
                if (this.currentStack instanceof ClickGuiFrameManager && (clickGuiFrameManager = (ClickGuiFrameManager)this.currentStack).Y$src$Lgg_vape_ui_click_frame_impl_hud_HudOverlaySelec$z60fv4() != null && clickGuiFrameManager.Y$src$Lgg_vape_ui_click_frame_impl_hud_HudOverlaySelec$z60fv4().V$src$Z$1xhop3l()) {
                    clickGuiFrameManager.G(ClickGuiLayer.MAIN);
                }
                if (!this.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v().equals(fG)) {
                    Minecraft.F$src$V$aoypvc();
                }
            }
            eventKeyPress.setCancelled(true);
        }
    }

    public static boolean I$src$Z$1gg3tgf() {
        return fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v().equals(t);
    }
}
