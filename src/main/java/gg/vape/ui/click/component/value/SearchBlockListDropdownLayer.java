package gg.vape.ui.click.component.value;

import gg.vape.Vape;
import gg.vape.ui.click.component.value.FloatingValueDropdownLayer;
import gg.vape.ui.click.component.value.SearchBlockEditorComponent;
import gg.vape.ui.click.component.value.SearchBlockListAddInputComponent;
import gg.vape.ui.click.component.value.SearchBlockListComponent;
import gg.vape.ui.click.component.value.SearchBlockListDropdownCloseHandler;
import gg.vape.ui.click.component.value.SearchBlockRemoveHandler;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrameHeaderActionComponent;
import gg.vape.ui.unmap.SearchBlock;

public class SearchBlockListDropdownLayer
extends FloatingValueDropdownLayer<SearchBlockListComponent> {
    public SearchBlockListDropdownLayer(SearchBlockListComponent searchBlockListComponent) {
        super(searchBlockListComponent);
        this.Y(new PublicProfilesFrameHeaderActionComponent(this, "allowedicon", searchBlockListComponent.t$src$Ljava_lang_String_$1kbjhi6()).Q(new SearchBlockListDropdownCloseHandler(this, searchBlockListComponent)));
        this.e();
    }

    @Override
    public void e() {
        this.S();
        SearchBlockListAddInputComponent searchBlockListAddInputComponent = new SearchBlockListAddInputComponent("Block name / ID");
        this.h(searchBlockListAddInputComponent, new Object[0]);
        for (SearchBlock searchBlock : Vape.INSTANCE.getSearch().O()) {
            SearchBlockEditorComponent searchBlockEditorComponent = new SearchBlockEditorComponent(searchBlock);
            searchBlockEditorComponent.g(new SearchBlockRemoveHandler(this, searchBlock));
            this.h(searchBlockEditorComponent, new Object[0]);
        }
    }
}

