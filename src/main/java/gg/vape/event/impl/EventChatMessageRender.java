package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.ChatMessageRenderBridge;
import gg.vape.wrapper.impl.ITextComponent;

public class EventChatMessageRender
extends Event {
    private final Object H;
    private ChatMessageRenderBridge s;
    private Object G;
    private static final EventListeners m = new EventListeners();
    private final Object S;
    private final Object e;
    private final Object E;
    private ITextComponent j;

    public Object getMessageSignature() {
        return this.H;
    }


    public ITextComponent getContentComponent() {
        if (this.j == null) {
            this.j = new ITextComponent(this.e);
        }
        return this.j;
    }

    public static EventListeners getEventListeners() {
        return m;
    }

    public Object getOutputContentComponent() {
        return this.G;
    }

    @Override
    public EventListeners getListeners() {
        return m;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    public EventChatMessageRender(Object object, Object object2, Object object3, Object object4) {
        this.S = object;
        this.e = object2;
        this.H = object3;
        this.E = object4;
        this.G = object2;
    }

    public void setOutputContentComponent(ITextComponent iTextComponent) {
        this.G = iTextComponent.getObject();
    }

    public Object getGuiMessageTag() {
        return this.E;
    }

    public ChatMessageRenderBridge getChatComponent() {
        if (this.s == null) {
            this.s = new ChatMessageRenderBridge(this.S);
        }
        return this.s;
    }
}

