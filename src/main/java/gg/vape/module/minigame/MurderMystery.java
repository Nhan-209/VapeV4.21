package gg.vape.module.minigame;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.event.impl.EventRender2D;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ItemLimitData;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.LimitValue;
import gg.vape.value.NumberValue;
import gg.vape.value.OptionalLimitValue;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.FontRenderer;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.ScaledResolution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MurderMystery
extends Mod {
    private final OptionalLimitValue o;
    private final TimerUtil v;
    private Object a;
    private final Queue<String> Z = new ConcurrentLinkedQueue<String>();
    private final BooleanValue j;
    private final NumberValue U;
    private final LimitValue O;
    private final List<Integer> H = new ArrayList<Integer>();

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean P(EntityLivingBase entityLivingBase) {
        if (!this.r$src$Z$14eylz9()) {
            return false;
        }
        if (!entityLivingBase.isInstance(MappedClasses.Yl)) {
            return false;
        }
        return this.H.contains(entityLivingBase.S());
    }

    @EventHandler
    public void onRender2D(EventRender2D eventRender2D) {
        if (!this.H.isEmpty()) {
            ScaledResolution scaledResolution = new ScaledResolution();
            int n = 25;
            FontRenderer fontRenderer = eventRender2D.getFontRenderer();
            fontRenderer.drawStringWithShadow("\u00a7nMurderer List", (double)(scaledResolution.T() / 2 - 20), 15.0, -1);
            for (Object e : Minecraft.theWorld().z()) {
                EntityPlayer entityPlayer;
                if (!MappedClasses.Yl.isAssignableFrom(e.getClass()) || MappedClasses.z5.isAssignableFrom(e.getClass()) || !this.H.contains((entityPlayer = new EntityPlayer(e)).S())) continue;
                fontRenderer.drawStringWithShadow(entityPlayer.getName(), (double)(scaledResolution.T() / 2 - 20), (double)n, -1);
                n += 10;
            }
        }
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        if (this.a == null || !Minecraft.theWorld().getObject().equals(this.a)) {
            this.H.clear();
            this.Z.clear();
            this.a = Minecraft.theWorld().getObject();
        }
        if (this.Z.size() > 0 && this.v.hasTimeElapsed(((Double)this.U.K()).longValue())) {
            Minecraft.thePlayer().sendChatMessage(this.Z.poll());
            this.v.reset();
        }
        for (Object e : Minecraft.theWorld().z()) {
            EntityPlayer entityPlayer;
            if (!MappedClasses.Yl.isAssignableFrom(e.getClass()) || MappedClasses.z5.isAssignableFrom(e.getClass()) || this.H.contains((entityPlayer = new EntityPlayer(e)).S()) || !entityPlayer.getHeldItemHand().isNotNull() || !this.O.A(entityPlayer.getHeldItemHand())) continue;
            this.H.add(entityPlayer.S());
            if (!this.j.L().booleanValue()) continue;
            List<String> list = this.o.D();
            int n = (int)Math.round((double)list.size() * Math.random());
            if (n >= list.size()) {
                n = list.size() - 1;
            }
            String string = list.get(n).replace("%s", entityPlayer.getName());
            this.Z.add(string);
        }
    }

    public MurderMystery() {
        super("MurdererFinder", -11859, Category.m, "Shows a list of suspected Murderers.");
        this.j = BooleanValue.create(this, "Callout", false, "Calls out who the suspected murderer is in chat.");
        this.o = OptionalLimitValue.Q(this, "murder-messages", "Messages", "Use %s to use the murderer's name", OptionalLimitValue.O, Arrays.asList("%s is the murderer!", "i saw that %s!"));
        this.U = NumberValue.E(this, "Delay", "#", "ms", 0.0, 3100.0, 5000.0, "Delay between murderer callouts.");
        this.O = LimitValue.n(this, "murderer-items", "Murderer Items", LimitValue.G, Arrays.asList(new ItemLimitData("swords"), new ItemLimitData("shovels"), new ItemLimitData("axes"), new ItemLimitData("pickaxes"), new ItemLimitData(288), new ItemLimitData(396), new ItemLimitData(421), new ItemLimitData(398), new ItemLimitData(369), new ItemLimitData(75), new ItemLimitData(50), new ItemLimitData(352)));
        this.v = new TimerUtil();
        this.j.K(this.U, this.o);
        this.addValue(this.j, this.U, this.o, this.O);
    }
}

