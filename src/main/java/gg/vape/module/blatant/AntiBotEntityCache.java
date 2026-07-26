package gg.vape.module.blatant;

import gg.vape.mapping.MappedClasses;
import gg.vape.unmap.TextComponentBase;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.GameProfile;
import gg.vape.wrapper.impl.ITextComponent;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.NetHandlerPlayClientImpl;
import gg.vape.wrapper.impl.PlayerInfo;
import gg.vape.wrapper.impl.ScorePlayerTeam;
import gg.vape.wrapper.impl.ScorePlayerTeamTextComponent;
import gg.vape.wrapper.impl.TextFormatting;
import java.util.List;

public class AntiBotEntityCache {
    private static final Integer C;
    private static String[] v;

    private String C(String string) {
        if (string == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            if (!(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9') && c != '_') continue;
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public static void Z(String[] stringArray) {
        v = stringArray;
    }

    static {
        AntiBotEntityCache.Z(null);
        long l = -1068649375749636097L;
        C = (int)l;
    }

    public Integer r(EntityPlayer entityPlayer) {
        NetHandlerPlayClientImpl netHandlerPlayClientImpl = Minecraft.N();
        if (netHandlerPlayClientImpl.isNull()) {
            return null;
        }
        String string = entityPlayer.getName();
        Integer n = null;
        for (Object e : netHandlerPlayClientImpl.getPlayerInfoMap()) {
            Object object2;
            PlayerInfo playerInfo = new PlayerInfo(e);
            if (playerInfo.isNull()) continue;
            ITextComponent iTextComponent = playerInfo.R();
            if (iTextComponent.isNotNull()) {
                object2 = iTextComponent.C();
                if (object2 == null || !((String)object2).contains(string)) continue;
                Integer formattedColor = this.y(playerInfo, string);
                if (formattedColor != null) {
                    return formattedColor;
                }
            }
            if (n != null || playerInfo.v().isNull() || !((GameProfile)(object2 = playerInfo.v())).getName().equals(string)) continue;
            try {
                Integer n2;
                TextFormatting textFormatting;
                ScorePlayerTeam scorePlayerTeam = playerInfo.X();
                if (!scorePlayerTeam.isNotNull() || (textFormatting = scorePlayerTeam.W()) == null || (n2 = textFormatting.K()) == null || n2.equals(C)) continue;
                n = n2;
            }
            catch (Exception exception) {}
        }
        return n;
    }

    public boolean r(EntityPlayer entityPlayer, EntityPlayer entityPlayer2) {
        Integer n = this.r(entityPlayer);
        Integer n2 = this.r(entityPlayer2);
        if (n != null && n2 != null) {
            return n.equals(n2);
        }
        return false;
    }

    private String k(ITextComponent iTextComponent) {
        Object object;
        try {
            object = iTextComponent.F();
            if (((Wrapper)object).isNotNull() && ((Wrapper)object).isInstance(MappedClasses.qT)) {
                ScorePlayerTeamTextComponent scorePlayerTeamTextComponent = new ScorePlayerTeamTextComponent(((Wrapper)object).getObject());
                return scorePlayerTeamTextComponent.Y();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        try {
            object = iTextComponent.C();
            if (object != null) {
                return this.C((String)object);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return null;
    }

    private Integer g(ITextComponent iTextComponent, String string) {
        if (iTextComponent.isNull()) {
            return null;
        }
        String string2 = this.k(iTextComponent);
        Integer componentColor;
        if (string2 != null && this.Q(string2, string) && (componentColor = this.c(iTextComponent)) != null) {
            return componentColor;
        }
        try {
            List<ITextComponent> siblings = iTextComponent.G();
            for (int i = 0; i < siblings.size(); ++i) {
                ITextComponent iTextComponent2 = siblings.get(i);
                Integer n = this.g(iTextComponent2, string);
                if (n == null) continue;
                return n;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return null;
    }

    private Integer y(PlayerInfo playerInfo, String string) {
        ITextComponent iTextComponent = playerInfo.R();
        if (iTextComponent.isNull()) {
            return null;
        }
        return this.g(iTextComponent, string);
    }

    private Integer c(ITextComponent iTextComponent) {
        try {
            TextComponentBase textComponentBase = iTextComponent.J();
            if (textComponentBase.isNull()) {
                return null;
            }
            String string = textComponentBase.getObject().toString();
            if (string.contains("color=")) {
                int n = string.indexOf("color=") + 6;
                int n2 = string.indexOf(",", n);
                if (n2 == -1) {
                    n2 = string.indexOf("}", n);
                }
                if (n2 > n) {
                    String string2 = string.substring(n, n2);
                    return this.b(string2);
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return null;
    }

    public static String[] B() {
        return v;
    }

    private boolean Q(String string, String string2) {
        if (string == null || string2 == null) {
            return false;
        }
        if (string.equals(string2)) {
            return true;
        }
        String string3 = this.C(string);
        return string3.equals(string2);
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    private Integer b(String string) {
        if (string == null || string.isEmpty()) {
            return null;
        }
        if (string.startsWith("#")) {
            try {
                return Integer.parseInt(string.substring(1), 16);
            }
            catch (NumberFormatException numberFormatException) {
                return null;
            }
        }
        TextFormatting textFormatting = TextFormatting.q(string);
        if (textFormatting != null) {
            return textFormatting.K();
        }
        return null;
    }
}
