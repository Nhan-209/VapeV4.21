package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.none.ClientSettings;
import gg.vape.module.utility.inventory.cleaner.ui.TextSuggestionInputComponent;
import gg.vape.module.utility.inventory.cleaner.ui.TextSuggestionRow;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import java.awt.Point;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

class TextSuggestionClickListener
implements GuiMouseListener {
    final TextSuggestionInputComponent n;
    final AtomicBoolean B;
    final Consumer Z;

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        for (TextSuggestionRow textSuggestionRow : TextSuggestionInputComponent.a(this.n)) {
            if (!textSuggestionRow.w$src$Z$e457mb()) continue;
            if (this.B.get()) {
                return;
            }
            this.B.set(true);
            ClientSettings.f6.execute(() -> this.handleRowClick(textSuggestionRow, this.Z, this.B));
            return;
        }
    }

    TextSuggestionClickListener(TextSuggestionInputComponent textSuggestionInputComponent, AtomicBoolean atomicBoolean, Consumer consumer) {
        this.n = textSuggestionInputComponent;
        this.B = atomicBoolean;
        this.Z = consumer;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void handleRowClick(TextSuggestionRow textSuggestionRow, Consumer consumer, AtomicBoolean atomicBoolean) {
        try {
            this.n.Q(textSuggestionRow);
            consumer.accept(textSuggestionRow);
        }
        finally {
            atomicBoolean.set(false);
        }
    }

}

