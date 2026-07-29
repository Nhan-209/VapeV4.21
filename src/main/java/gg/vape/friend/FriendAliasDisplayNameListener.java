package gg.vape.friend;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.impl.EventPreTick;
import gg.vape.friend.FriendEntry;
import gg.vape.manager.client.FriendManager;
import gg.vape.utils.MutablePair;
import gg.vape.utils.StringUtils;
import gg.vape.utils.TimerUtil;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.jetbrains.annotations.Nullable;

public class FriendAliasDisplayNameListener
implements EventListener {
    private final Map<String, MutablePair<String, TimerUtil>> h = new HashMap<String, MutablePair<String, TimerUtil>>();
    private Set<FriendEntry> G = new HashSet<FriendEntry>();

    private FriendManager g() {
        return Vape.INSTANCE.getFriendManager();
    }

    Set<FriendEntry> R() {
        return this.g().getFriends();
    }

    public boolean S() {
        return !this.R().isEmpty();
    }

    public boolean u() {
        return this.g().C.getEffectiveValue();
    }

    private Set<FriendEntry> D() {
        return this.g().getFriends().stream().filter(FriendEntry::c).collect(Collectors.toSet());
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        this.G = this.D();
    }

    private String P(String string, Iterable<FriendEntry> iterable) {
        String string2 = string;
        for (FriendEntry friendEntry : iterable) {
            String string3;
            String string4 = friendEntry.s().toLowerCase();
            if (string4.equalsIgnoreCase(string3 = friendEntry.o()) || !StringUtils.K(string2, string4)) continue;
            string2 = StringUtils.U(string2, string4, string3);
        }
        return string2;
    }

    @Nullable
    public String G(String string, Iterable<FriendEntry> iterable) {
        return string == null ? null : (String)this.h.compute(string, (arg_0, arg_1) -> this.lambda$getReplacedDisplayName$0(string, iterable, arg_0, arg_1)).O();
    }

    public Set<FriendEntry> e() {
        if (this.G == null) {
            this.G = this.D();
        }
        return this.G;
    }


    private MutablePair lambda$getReplacedDisplayName$0(String string, Iterable iterable, String string2, MutablePair mutablePair) {
        if (mutablePair == null) {
            return new MutablePair<String, TimerUtil>(this.P(string, iterable), new TimerUtil());
        }
        TimerUtil timerUtil = (TimerUtil)mutablePair.K();
        if (timerUtil.hasTimeElapsed(1000L)) {
            timerUtil.reset();
            return mutablePair.w(this.P(string, iterable));
        }
        return mutablePair;
    }

    public boolean N() {
        return this.g().J.getEffectiveValue();
    }
}

