package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MScorePlayerTeam;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ITextComponent;
import gg.vape.wrapper.impl.ScorePlayerTeamTextComponent;
import gg.vape.wrapper.impl.Team;
import gg.vape.wrapper.impl.TextComponent;
import gg.vape.wrapper.impl.TextComponentBaseBridge;
import gg.vape.wrapper.impl.TextFormatting;

public class ScorePlayerTeam
extends Team {
    public static TextComponent h(Team team, ITextComponent iTextComponent) {
        if (ForgeVersion.MC_1_16_5.v()) {
            throw new UnsupportedOperationException("This method is only for versions 1.16.5 and above");
        }
        return new TextComponent(MScorePlayerTeam.H(ScorePlayerTeam.c.getMappings().Rh, team.getObject(), iTextComponent.getObject()));
    }

    public static TextComponentBaseBridge j(Team team, ITextComponent iTextComponent) {
        if (ForgeVersion.MC_1_20_6.v()) {
            throw new UnsupportedOperationException("This method is only for versions 1.16.5 and above");
        }
        return new TextComponentBaseBridge(MScorePlayerTeam.H(ScorePlayerTeam.c.getMappings().Rh, team.getObject(), iTextComponent.getObject()));
    }

    private static UnsupportedOperationException a(UnsupportedOperationException unsupportedOperationException) {
        return unsupportedOperationException;
    }

    public String A() {
        if (ForgeVersion.MC_1_20_6.d()) {
            ITextComponent iTextComponent = new ITextComponent(ScorePlayerTeam.c.getMappings().Rh.z(this.getObject()));
            if (iTextComponent.isNull()) {
                return "";
            }
            return iTextComponent.getFormattedText();
        }
        return MScorePlayerTeam.L(ScorePlayerTeam.c.getMappings().Rh, this.getObject());
    }

    public ScorePlayerTeam(Object object) {
        super(object);
    }

    public TextFormatting W() {
        Object object = ScorePlayerTeam.c.getMappings().Rh.b(this.getObject());
        if (object == null) {
            return null;
        }
        return new TextFormatting(object);
    }

    public static String o(Team team, String string) {
        if (ForgeVersion.MC_1_16_5.d()) {
            TextComponent textComponent = new TextComponent(ScorePlayerTeam.c.getMappings().Rh.I(team.getObject(), ScorePlayerTeamTextComponent.B(string).getObject()));
            return textComponent.U();
        }
        return MScorePlayerTeam.M(ScorePlayerTeam.c.getMappings().Rh, team.getObject(), string);
    }
}
