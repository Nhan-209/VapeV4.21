package gg.vape.ui.click.frame.impl.target;

import gg.vape.event.EventBus;
import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.impl.EventEntityJoinWorld;
import gg.vape.event.impl.EventLivingUpdate;
import gg.vape.event.impl.EventPlayerUseItem;
import gg.vape.event.impl.EventPostAttack;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.impl.target.TargetInfoCombatStatStripComponent;
import gg.vape.ui.click.frame.impl.target.TargetInfoDistanceStatStripComponent;
import gg.vape.ui.click.frame.impl.target.TargetInfoEntityPreviewComponent;
import gg.vape.ui.click.frame.impl.target.TargetInfoHealthBarComponent;
import gg.vape.ui.click.frame.impl.target.TargetInfoLiveEntityPreviewComponent;
import gg.vape.ui.click.frame.impl.target.TargetInfoPositiveStatStripComponent;
import gg.vape.ui.click.frame.impl.target.TargetInfoPreviewHealthBarComponent;
import gg.vape.ui.click.frame.impl.target.TargetInfoResettingCombatStatStripComponent;
import gg.vape.ui.click.frame.impl.target.TargetInfoSettingsFrame;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.StringUtils;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.render.BlurRegionRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPotion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import java.awt.Color;
import java.util.function.Predicate;

public class TargetInfoPreviewComponent
extends PaddedComponent
implements EventListener {
    private final BlurRegionRenderer w_;
    private int wz;
    private final TargetInfoResettingCombatStatStripComponent wU;
    private final SpacerComponent w7;
    private final FlowLayoutComponent w6;
    private int wF = -1;
    private static GuiComponent[] wo;
    private final PanelComponent wi;
    private final TimerUtil wO = new TimerUtil();
    private final PanelComponent w5;
    private final FlowLayoutComponent wX;
    private EntityLivingBase wb;
    private final TargetInfoCombatStatStripComponent wL;
    private final TargetInfoDistanceStatStripComponent wE;
    private final FlowLayoutComponent wk;
    private final TargetInfoSettingsFrame wr;
    private static final int wp;
    private final TargetInfoEntityPreviewComponent wN;
    private final FlowLayoutComponent wK;
    private final TargetInfoPositiveStatStripComponent wR;
    private final TargetInfoHealthBarComponent wn;

    @EventHandler
    public void onUpdate(EventLivingUpdate eventLivingUpdate) {
        if (!this.n$src$Z$213g15()) {
            return;
        }
        if (this.h$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1x3oue() == null || this.h$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1x3oue().isNull() || Minecraft.thePlayer().isNull()) {
            return;
        }
        if (Minecraft.thePlayer().getDistanceToEntity(this.h$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1x3oue()) > 6.0f) {
            return;
        }
        if (eventLivingUpdate.getEntity().getObject().equals(Minecraft.thePlayer().getObject())) {
            this.wR.w$src$V$vtqvn7();
            this.wU.o$src$V$fgvspr();
        }
        if (eventLivingUpdate.getEntity().getObject().equals(this.h$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1x3oue().getObject())) {
            this.wR.Q$src$V$v8up3h();
            this.wU.R();
        }
    }

    private boolean R$src$Z$1lp7f1() {
        return !ClientSettings.fW.P;
    }

    public EntityLivingBase h$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1x3oue() {
        return this.R$src$Z$1lp7f1() ? Minecraft.thePlayer() : this.wb;
    }

    public static GuiComponent[] y$src$ALgg_vape_ui_click_component_GuiComponent_$16gmu6q() {
        return wo;
    }

    public TargetInfoPreviewComponent(TargetInfoSettingsFrame targetInfoSettingsFrame) {
        super(10.0, new FlowLayoutComponent(100.0));
        this.wi = new PanelComponent(100.0, 10.0);
        this.w6 = new FlowLayoutComponent(100.0);
        this.wX = new FlowLayoutComponent(90.0);
        this.w7 = new SpacerComponent(12.0, 12.0);
        this.wN = new TargetInfoLiveEntityPreviewComponent(this, 12.0, 12.0);
        this.wn = new TargetInfoPreviewHealthBarComponent(this, 100, 4);
        this.w_ = new BlurRegionRenderer(0, 0);
        this.w5 = new PanelComponent(100.0, 14.0);
        this.wK = new FlowLayoutComponent(100.0);
        this.wE = new TargetInfoDistanceStatStripComponent();
        this.wR = new TargetInfoPositiveStatStripComponent();
        this.wL = new TargetInfoCombatStatStripComponent();
        this.wU = new TargetInfoResettingCombatStatStripComponent();
        this.wr = targetInfoSettingsFrame;
        this.wk = (FlowLayoutComponent)super.H$src$Lgg_vape_ui_click_component_GuiComponent_$kfnvup();
        this.wk.h(new SpacerComponent(1.0, 2.0), new Object[0]);
        this.w5.h(this.wK, "wrap, alignright");
        this.wk.h(this.w5, new Object[0]);
        this.wX.h(this.w7, new Object[0]);
        this.wN.M(targetInfoSettingsFrame);
        this.wi.h(this.wN, new Object[0]);
        this.wi.h(this.wX, new Object[0]);
        this.wk.H(this.wi);
        this.wk.h(new SpacerComponent(100.0, 8.0), new Object[0]);
        this.wn.y(targetInfoSettingsFrame);
        this.w6.h(this.wn, new Object[0]);
        this.wk.H(this.w6);
        this.wk.d(false);
        this.wi.d(false);
        this.wX.d(false);
        this.w6.d(false);
        this.w5.d(false);
        this.wX.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().Q(true);
        this.wi.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().Q(true);
        this.wK.d(false);
        this.wR.E(targetInfoSettingsFrame);
        this.wL.E(targetInfoSettingsFrame);
        this.wU.E(targetInfoSettingsFrame);
        this.wE.E(targetInfoSettingsFrame);
        this.wK.h(this.wR, new Object[0]);
        this.wK.h(this.wL, new Object[0]);
        this.wK.h(this.wU, new Object[0]);
        this.wK.h(this.wE, new Object[0]);
        EventBus.getInstance().registerListener(this, new Predicate[0]);
    }

    private void j$src$V$1yw9k9() {
        this.wR.Z(this.wr.U$src$Lgg_vape_ui_click_frame_impl_target_TargetInfoSe$5b5o15().I.L());
        this.wL.Z(this.wr.U$src$Lgg_vape_ui_click_frame_impl_target_TargetInfoSe$5b5o15().A.L());
        this.wU.Z(this.wr.U$src$Lgg_vape_ui_click_frame_impl_target_TargetInfoSe$5b5o15().P.L());
        this.wE.Z(this.wr.U$src$Lgg_vape_ui_click_frame_impl_target_TargetInfoSe$5b5o15().Q.L());
        boolean bl = false;
        for (GuiComponent guiComponent : this.wK.f()) {
            if (!guiComponent.V$src$Z$1xhop3l()) continue;
            bl = true;
            break;
        }
        this.w5.Z(bl);
    }

    private double e() {
        return this.n() + 2.0;
    }

    public void I(EntityLivingBase entityLivingBase) {
        this.wb = entityLivingBase;
    }

    @Override
    public void u() {
        Entity entity;
        RayTraceResult rayTraceResult;
        if (!this.n$src$Z$213g15()) {
            return;
        }
        this.A$src$V$1ccp8g();
        this.j$src$V$1yw9k9();
        if (this.wO.hasTimeElapsed(1000L) && this.h$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1x3oue() != null && !this.h$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1x3oue().isNull() && this.h$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1x3oue().w$src$F$15l9epb() <= 0.0f) {
            this.S(null);
        }
        if (this.wO.hasTimeElapsed(3000L)) {
            this.S(null);
        }
        if (this.wr.U$src$Lgg_vape_ui_click_frame_impl_target_TargetInfoSe$5b5o15().l.L().booleanValue() && (rayTraceResult = Minecraft.p$src$Lgg_vape_wrapper_impl_RayTraceResult_$5rw6n0()).isNotNull() && (entity = rayTraceResult.getEntity()).isNotNull() && entity.isInstance(MappedClasses.zm) && !entity.isInstance(MappedClasses.FT)) {
            this.S(new EntityLivingBase(entity));
        }
    }

    private void S(EntityLivingBase entityLivingBase) {
        if (entityLivingBase == null) {
            this.I((EntityLivingBase)null);
            return;
        }
        this.wk.Z(true);
        this.wO.reset();
        if (this.h$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1x3oue() != null && this.h$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1x3oue().equals(entityLivingBase)) {
            return;
        }
        int n = entityLivingBase.S();
        this.I(entityLivingBase);
        this.wn.a(entityLivingBase);
        if (n != this.wF) {
            this.wR.p(0);
            this.wL.i(0);
            this.wU.c(0);
        }
        this.wF = n;
        this.H(true);
    }

    @EventHandler
    public void r(EventPostAttack eventPostAttack) {
        if (!this.n$src$Z$213g15()) {
            this.S(null);
            return;
        }
        Entity entity = eventPostAttack.getTarget();
        if (entity.isInstance(MappedClasses.zm) && !entity.isInstance(MappedClasses.FT)) {
            this.S(new EntityLivingBase(entity));
        }
    }

    public String O$src$Ljava_lang_String_$1k5li7c() {
        if (this.h$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1x3oue() == null) {
            return "";
        }
        return this.h$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1x3oue().getName();
    }

    private boolean n$src$Z$213g15() {
        return this.wr.y$src$Z$1f55jvh() && Minecraft.theWorld().isNotNull();
    }

    private void A$src$V$1ccp8g() {
        if (this.h$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1x3oue() == null) {
            return;
        }
        this.wE.c((int)RotationUtil.y(Minecraft.thePlayer(), this.h$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1x3oue()));
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static boolean X(TargetInfoPreviewComponent targetInfoPreviewComponent) {
        return targetInfoPreviewComponent.R$src$Z$1lp7f1();
    }

    public static void T(GuiComponent[] guiComponentArray) {
        wo = guiComponentArray;
    }

    @Override
    public void H() {
        this.o$src$V$21n8j2();
    }

    static {
        TargetInfoPreviewComponent.T((GuiComponent[])null);
        long l = 5322866816630915172L;
        wp = (int)l;
    }

    private void o$src$V$21n8j2() {
        this.wk.Z(this.h$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1x3oue() != null || this.R$src$Z$1lp7f1());
        if (!this.wk.V$src$Z$1xhop3l()) {
            return;
        }
        this.wN.Z(this.h$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1x3oue().isInstance(MappedClasses.Yl));
        if (this.w5.V$src$Z$1xhop3l()) {
            this.N(5.0);
        } else {
            this.N(10.0);
        }
        this.H(true);
        float f = this.wr.r$src$F$35g3yx();
        this.w_.L((int)this.A() * 2, (int)this.L() * 2);
        if (f >= 1.0f) {
            this.w_.t((int)this.G$src$D$1b2f02a(), (int)this.e(), 20.0f, 3.0f);
        }
        GuiRenderPrimitives.e(this.G$src$D$1b2f02a(), this.e(), this.A(), this.L(), this.wr.l(new Color(18, 18, 18, 173)), false, 3.0f, 1.0f);
        String string = this.O$src$Ljava_lang_String_$1k5li7c();
        String string2 = StringUtils.l(string);
        if (string2.isEmpty()) {
            string = "\u00a77(Empty Name)";
        }
        TruncatedTextComponent truncatedTextComponent = new TruncatedTextComponent(string, "...", 80.0, 1.3, this.wr.m$src$Ljava_awt_Color_$ppsp8z(), false);
        double d = this.wN.V$src$Z$1xhop3l() ? this.wN.n() + this.wN.L() / 2.0 - truncatedTextComponent.f$src$D$ldt7xy() / 2.0 : this.wi.n() + this.wi.L() / 2.0 - truncatedTextComponent.f$src$D$ldt7xy() / 2.0;
        truncatedTextComponent.V(this.w7.G$src$D$1b2f02a() + (double)(this.wN.V$src$Z$1xhop3l() ? 5 : 2), d);
    }

    @EventHandler
    public void G(EventPlayerUseItem eventPlayerUseItem) {
        if (!this.n$src$Z$213g15()) {
            return;
        }
        if (this.h$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1x3oue() == null || this.h$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1x3oue().isNull()) {
            return;
        }
        ItemStack itemStack = eventPlayerUseItem.getItemStack();
        if (itemStack.isNotNull() && MappedClasses.Di.isInstance(itemStack.getItem().getObject()) && ItemStackScoreUtil.i(itemStack)) {
            ++this.wz;
        }
    }

    @EventHandler
    public void k(EventEntityJoinWorld eventEntityJoinWorld) {
        if (!this.n$src$Z$213g15()) {
            return;
        }
        if (this.h$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1x3oue() == null || this.h$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1x3oue().isNull() || Minecraft.thePlayer().isNull()) {
            return;
        }
        if (!eventEntityJoinWorld.getEntity().isInstance(MappedClasses.Zf)) {
            return;
        }
        EntityPotion entityPotion = new EntityPotion(eventEntityJoinWorld.getEntity());
        if (entityPotion.getPotion().isNull() || !ItemStackScoreUtil.i(entityPotion.getPotion())) {
            return;
        }
        if (this.wz > 0) {
            this.wL.V$src$V$1wi2ydp();
            --this.wz;
        } else {
            this.wL.l$src$V$1wu6ffn();
        }
    }

    @Override
    public void I() {
        this.c();
    }
}
