package gg.vape.movement;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventPreLocalPlayerTick;
import gg.vape.event.impl.EventPreTick;
import gg.vape.movement.MovementInputHelper;
import gg.vape.movement.PlayerMovementTask;
import gg.vape.wrapper.impl.Minecraft;

public class PlayerMovementTaskManager
implements EventListener {
    private static String y;
    public static PlayerMovementTaskManager G;
    private PlayerMovementTask x;
    private boolean k;

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    public static void C(String string) {
        y = string;
    }

    public void Q(PlayerMovementTask playerMovementTask) {
        if (this.x != null && this.x.equals(playerMovementTask)) {
            this.x.s(true);
        }
    }

    private PlayerMovementTaskManager() {
        G = this;
    }

    public boolean e$src$Z$17ayaq9() {
        return this.x != null && !this.x.q$src$Z$naak2i();
    }

    @EventHandler(A=EventPriority.HIGH)
    public void u(EventPreLocalPlayerTick eventPreLocalPlayerTick) {
        if (this.x == null && Minecraft.thePlayer().isNotNull()) {
            return;
        }
        this.x.r();
        if (this.x.q$src$Z$naak2i()) {
            if (this.x.n()) {
                MovementInputHelper.q();
            } else {
                MovementInputHelper.i();
            }
            this.x = null;
        }
    }

    public PlayerMovementTask e() {
        return this.x;
    }

    public static String S() {
        return y;
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (this.x != null && Minecraft.thePlayer().isNotNull()) {
            try {
                this.x.i(eventPreTick);
            }
            catch (NullPointerException nullPointerException) {
                Vape.logThrowable(nullPointerException);
            }
        }
    }

    public void i(PlayerMovementTask playerMovementTask) {
        this.x = playerMovementTask;
    }

    static {
        G = new PlayerMovementTaskManager();
        PlayerMovementTaskManager.C(null);
    }
}

