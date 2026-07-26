package gg.vape.mapping;

import gg.vape.Vape;
import gg.vape.mapping.MappingTask;
import gg.vape.notification.NotificationType;
import java.util.ArrayList;
import java.util.List;

public class MappingTaskSet {
    protected List<MappingTask> D = new ArrayList<MappingTask>();

    public void d() {
        int n = 0;
        for (MappingTask mappingTask : this.D) {
            ++n;
            if (mappingTask.Q()) continue;
            int n2 = 0;
            try {
                mappingTask.K();
                mappingTask.c();
                mappingTask.j();
                n2 = mappingTask.J();
            }
            catch (Throwable throwable) {
                n2 = -1;
                Vape.debugLog("Error transforming " + mappingTask.B().getName());
                Vape.logThrowable(throwable);
            }
            if (n2 == 0) continue;
            String string = n2 + " " + n;
            if (Vape.INSTANCE.getNotificationManager() == null) continue;
            Vape.INSTANCE.getNotificationManager().K("Error with injection", "Please report to support:\nError code " + string + "\n\nSome features may not function", NotificationType.ALERT, 30000L, true);
        }
    }

    private static Throwable a(Throwable throwable) {
        return throwable;
    }

    public void C() {
        for (MappingTask mappingTask : this.D) {
            mappingTask.O();
        }
    }
}

