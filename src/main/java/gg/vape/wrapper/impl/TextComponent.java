package gg.vape.wrapper.impl;

import gg.vape.Vape;
import gg.vape.mapping.mappings.MTextComponent;
import gg.vape.wrapper.impl.ITextComponent;
import gg.vape.wrapper.impl.ScorePlayerTeam;
import gg.vape.wrapper.impl.ScorePlayerTeamTextComponent;
import gg.vape.wrapper.impl.Style;

public class TextComponent
extends ITextComponent {
    public static TextComponent p(Object object) {
        return new TextComponent(MTextComponent.n(TextComponent.c.getMappingsMapperCompat().RA, object));
    }

    public TextComponent(Object object) {
        super(object);
    }

    public String U() {
        return (String)MTextComponent.s(TextComponent.c.getMappingsMapperCompat().RA, this.I);
    }

    public Style w() {
        return new Style(MTextComponent.B(TextComponent.c.getMappingsMapperCompat().RA, this.I));
    }

    public TextComponent(ScorePlayerTeam tg_12, String string) {
        super(new TextComponent(Vape.INSTANCE.getMappingsMapperCompat().Rh.I(tg_12.getObject(), ScorePlayerTeamTextComponent.B(string).getObject())).getObject());
    }
}

