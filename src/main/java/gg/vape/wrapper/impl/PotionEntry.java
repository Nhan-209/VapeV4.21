package gg.vape.wrapper.impl;

import gg.vape.Vape;
import gg.vape.mapping.MappedClasses;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.utils.StringUtils;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.EntityLiving;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.I18n;
import gg.vape.wrapper.impl.Potion;
import gg.vape.wrapper.impl.PotionEntryBuilder;
import gg.vape.wrapper.impl.PotionEntryResolveException;
import gg.vape.wrapper.impl.StatusEffect;
import java.lang.invoke.MethodHandles;
import java.util.LinkedHashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class PotionEntry {
    private final String W;
    private final ForgeVersion L;
    @Nullable
    private Potion X;
    private static final long b;
    private final Map<ForgeVersion, Integer> T;
    private final short M;
    private static final long a;
    @Nullable
    private StatusEffect o;

    @Nullable
    public Potion j() {
        return this.X;
    }

    public boolean q(Potion potion) {
        return this.X.equals(potion);
    }

    static {
        a = ZkmLongKeyState.a(-8936105033957281546L, 3117451763444770599L, MethodHandles.lookup().lookupClass()).a(223103126945105L);
        long l = a ^ 0x7AEA175B43FL;
        b = 3010331125236105215L;
    }

    public boolean H() {
        return ForgeVersion.c() >= this.L.i();
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public static PotionEntryBuilder o() {
        return new PotionEntryBuilder();
    }

    void S() throws PotionEntryResolveException {
        long l = a ^ 0x2FA57A08E3B0L;
        if (!this.H()) {
            return;
        }
        Integer n = null;
        for (Map.Entry<ForgeVersion, Integer> entry : this.T.entrySet()) {
            ForgeVersion forgeVersion = entry.getKey();
            int n2 = entry.getValue();
            if (!forgeVersion.d()) continue;
            n = n2;
        }
        if (n != null) {
            if (ForgeVersion.MC_1_16_5.d()) {
                if (ForgeVersion.MC_1_21_4.d()) {
                    n = n - 1;
                }
                this.o = StatusEffect.E(n);
            } else {
                this.X = Potion.getPotionById(n);
            }
            if (this.o != null && this.o.isNotNull() || this.X != null && this.X.isNotNull()) {
                return;
            }
        }
        int n3 = 0;
        int n4 = (int)b;
        int n5 = -1;
        while (true) {
            String string;
            Wrapper wrapper;
            if (ForgeVersion.MC_1_16_5.d()) {
                wrapper = StatusEffect.E(n3);
                if (wrapper.isNull()) {
                    if (n3 != 0) break;
                    ++n3;
                    continue;
                }
                string = ((StatusEffect)wrapper).d();
            } else {
                wrapper = Potion.getPotionById(n3);
                if (wrapper.isNull()) {
                    if (n3 != 0) break;
                    ++n3;
                    continue;
                }
                string = I18n.f(((Potion)wrapper).y$src$Ljava_lang_String_$yl6pfj(), new Object[0]);
            }
            int n6 = StringUtils.Q(this.W, string);
            if (n6 < n4) {
                n5 = n3;
                n4 = n6;
            }
            ++n3;
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            this.o = StatusEffect.E(n5);
        } else {
            this.X = Potion.getPotionById(n5);
        }
        if (this.X == null && this.o == null) {
            throw new PotionEntryResolveException(this);
        }
    }

    public void t(EntityLivingBase entityLivingBase, Object object, int n) {
        EntityLiving entityLiving;
        Map map;
        if (ForgeVersion.MC_1_16_5.d() && entityLivingBase.isInstance(MappedClasses.zQ) && (map = (entityLiving = new EntityLiving(entityLivingBase.getObject())).T$src$Ljava_util_Map_$f5d6t2()).containsKey(this.o.getObject())) {
            map.remove(this.o.getObject());
            entityLiving.C(true);
        }
        Vape.INSTANCE.getMappings().qU.b(this.l(), entityLivingBase.getObject(), object, n);
    }

    @Nullable
    public StatusEffect K() {
        return this.o;
    }

    public short T() {
        return this.M;
    }

    public int D() {
        if (ForgeVersion.MC_1_16_5.d()) {
            if (this.o == null) {
                return -1;
            }
            return StatusEffect.v(this.o);
        }
        if (this.X == null) {
            return -1;
        }
        return this.X.getId();
    }

    public PotionEntry(PotionEntryBuilder potionEntryBuilder) {
        this.W = PotionEntryBuilder.y(potionEntryBuilder);
        this.M = PotionEntryBuilder.g(potionEntryBuilder);
        this.T = new LinkedHashMap<ForgeVersion, Integer>(PotionEntryBuilder.X(potionEntryBuilder));
        this.L = PotionEntryBuilder.l(potionEntryBuilder);
    }

    public boolean L() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return !this.o.p();
        }
        return this.X.n();
    }

    public Object l() {
        if (ForgeVersion.MC_1_16_5.d()) {
            if (this.o == null) {
                return null;
            }
            return this.o.getObject();
        }
        if (this.X == null) {
            return null;
        }
        return this.X.getObject();
    }

    public String G() {
        return this.W;
    }
}

