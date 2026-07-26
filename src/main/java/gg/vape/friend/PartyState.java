package gg.vape.friend;

import gg.vape.Vape;
import gg.vape.friend.GroupUserModel;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.PartyStateModel;
import gg.vape.friend.UserModel;
import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.friend.ui.PartyMemberRow;
import gg.vape.friend.ui.PartyPanel;
import gg.vape.protocol.packet.GroupOption;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.BooleanValue;
import gg.vape.value.Value;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

public class PartyState {
    private Map<GroupOption, Value<?, ?>> T;
    private PartyPanel H;
    private OnlineFriend M;
    private final List<OnlineFriend> b = new ArrayList<OnlineFriend>();
    private final List<OnlineFriend> W = new ArrayList<OnlineFriend>();
    private final List<PartyMemberRow> R = new ArrayList<PartyMemberRow>();
    private final BooleanValue u = BooleanValue.create(null, "Open Party", false);

    private static OnlineFriend lambda$handle$0(GroupUserModel groupUserModel) {
        return new OnlineFriend(groupUserModel);
    }

    public Map<GroupOption, Value<?, ?>> L() {
        if (this.T == null) {
            this.T = new LinkedHashMap();
            this.T.put(GroupOption.OPEN_INVITES, this.u);
            for (Map.Entry<GroupOption, Value<?, ?>> entry : this.T.entrySet()) {
                ((Value)entry.getValue()).o(entry.getKey().z());
            }
        }
        return this.T;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void Q(OnlineFriend onlineFriend) {
        List<OnlineFriend> list = this.b;
        synchronized (list) {
            this.b.add(onlineFriend);
            if (this.H != null) {
                this.H.w$src$V$1sfdd5j();
            }
        }
    }

    public PartyState(OnlineFriend onlineFriend) {
        this.M = onlineFriend;
        this.M.K(0);
        this.b.add(onlineFriend);
    }

    public @UnmodifiableView List<PartyMemberRow> d() {
        return this.R;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void q(OnlineFriend onlineFriend) {
        List<OnlineFriend> list = this.W;
        synchronized (list) {
            this.W.remove(onlineFriend);
            if (this.H != null) {
                this.H.w$src$V$1sfdd5j();
            }
        }
    }

    @Nullable
    public OnlineFriend z(long l) {
        for (OnlineFriend onlineFriend : this.b) {
            if (onlineFriend.S().g() != l) continue;
            return onlineFriend;
        }
        return null;
    }

    public void H(OnlineFriend onlineFriend) {
        this.M = onlineFriend;
        this.b.remove(onlineFriend);
        OnlineFriend onlineFriend2 = this.b.set(0, onlineFriend);
        if (onlineFriend2 != null) {
            this.b.add(onlineFriend2);
        }
        if (this.H != null) {
            this.H.w$src$V$1sfdd5j();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void g(OnlineFriend onlineFriend) {
        List<OnlineFriend> list = this.W;
        synchronized (list) {
            this.W.remove(onlineFriend);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void n(PartyMemberRow partyMemberRow) {
        List<PartyMemberRow> list = this.R;
        synchronized (list) {
            this.R.add(partyMemberRow);
        }
        OnlineFriendUiHelper.y(partyMemberRow);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public PartyState(PartyStateModel partyStateModel) {
        this.M = this.l(partyStateModel.g());
        for (GroupUserModel groupUserModel : partyStateModel.h()) {
            this.b.add(this.l(groupUserModel));
        }
        for (GroupUserModel groupUserModel : partyStateModel.V()) {
            this.W.add(this.l(groupUserModel));
        }
    }

    public BooleanValue h() {
        return this.u;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void o(OnlineFriend onlineFriend) {
        List<OnlineFriend> list = this.W;
        synchronized (list) {
            this.W.add(onlineFriend);
            if (this.H != null) {
                this.H.w$src$V$1sfdd5j();
            }
        }
    }

    @Nullable
    public OnlineFriend X(UserModel userModel) {
        return this.z(userModel.g());
    }

    public @UnmodifiableView List<OnlineFriend> S() {
        return this.W;
    }

    public boolean t() {
        if (this.u.L().booleanValue()) {
            return true;
        }
        return this.r().equals(Vape.INSTANCE.getOnlineManager().r());
    }

    private OnlineFriend l(GroupUserModel groupUserModel) {
        OnlineFriend onlineFriend = Vape.INSTANCE.getOnlineManager().u().Q(groupUserModel.V(), () -> PartyState.lambda$handle$0(groupUserModel));
        onlineFriend.K(groupUserModel.e());
        return onlineFriend;
    }

    public void u(PartyPanel partyPanel) {
        this.H = partyPanel;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void Y(OnlineFriend onlineFriend) {
        List<OnlineFriend> list = this.b;
        synchronized (list) {
            this.b.remove(onlineFriend);
            if (this.H != null) {
                this.H.w$src$V$1sfdd5j();
            }
        }
    }

    public OnlineFriend r() {
        return this.M;
    }

    public @UnmodifiableView List<OnlineFriend> c() {
        return this.b;
    }
}
