package gg.vape.module.blatant;

import gg.vape.config.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.unmap.ModeOption;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GameProfile;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PlayerInfo;
import gg.vape.wrapper.impl.ScorePlayerTeam;
import java.lang.invoke.MethodHandles;
import java.util.Map;
import java.util.UUID;

public class AntiBotStateTracker {
    private final Map<ModeOption, Character> W;
    public static final char f;

    public char s(EntityPlayer entityPlayer) {
        char c = this.S(entityPlayer);
        if (c == '\u00ff') {
            c = this.E(entityPlayer);
        }
        return c;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public AntiBotStateTracker(Map<ModeOption, Character> map) {
        this.W = map;
    }

    static {
        long l = ZkmLongKeyState.a(-7375381742493683848L, 3774037022871011756L, MethodHandles.lookup().lookupClass()).a(186948238376750L) ^ 0x1A312167D68BL;
        long l2 = 4935014296091361535L;
        f = (char)l2;
    }

    public ModeOption c(char c) {
        for (ModeOption modeOption : this.W.keySet()) {
            if (this.W.get(modeOption).charValue() != c) continue;
            return modeOption;
        }
        return null;
    }

    public char S(EntityPlayer entityPlayer) {
        String string = entityPlayer.Q().C();
        String string2 = entityPlayer.getName();
        return this.S(string2, string);
    }

    public char E(EntityPlayer entityPlayer) {
        if (ForgeVersion.MC_1_8_9.A()) {
            return '\u00ff';
        }
        GameProfile gameProfile = entityPlayer.c$src$Lgg_vape_wrapper_impl_GameProfile_$ir8937();
        UUID uUID = gameProfile.getUUID();
        for (Object e : Minecraft.N().getPlayerInfoMap()) {
            String string;
            ScorePlayerTeam scorePlayerTeam;
            PlayerInfo playerInfo = new PlayerInfo(e);
            GameProfile gameProfile2 = playerInfo.v();
            if (!gameProfile2.getUUID().equals(uUID) || !(scorePlayerTeam = playerInfo.X()).isNotNull() || !(string = scorePlayerTeam.A()).contains(ClientSettings.F)) continue;
            for (int i = string.length(); i > 0; --i) {
                char c;
                String string2 = String.valueOf(string.charAt(i - 1));
                if (!string2.equals(ClientSettings.F) || (c = string.charAt(i)) > 'f') continue;
                return c;
            }
        }
        return '\u00ff';
    }

    public char S(String string, String string2) {
        int n;
        if (string2.contains(ClientSettings.F) && (n = string2.indexOf(string)) > 0) {
            for (int i = n - 1; i >= 0; --i) {
                char c;
                String string3 = String.valueOf(string2.charAt(i));
                if (!string3.equals(ClientSettings.F) || (c = string2.charAt(i + 1)) > 'f') continue;
                return c;
            }
        }
        return '\u00ff';
    }

    public boolean A(ModeOption modeOption, EntityPlayer entityPlayer) {
        char c;
        char c2 = this.W.get(modeOption).charValue();
        return c2 == (c = this.S(entityPlayer));
    }
}

