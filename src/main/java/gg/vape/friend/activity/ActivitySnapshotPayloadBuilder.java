package gg.vape.friend.activity;

import gg.vape.friend.activity.ActivityHealthData;
import gg.vape.friend.activity.ActivityPositionData;
import gg.vape.friend.activity.ActivitySnapshotPayload;
import gg.vape.friend.activity.ActivityTargetData;
import org.jetbrains.annotations.Nullable;

public class ActivitySnapshotPayloadBuilder {
    @Nullable
    private ActivityTargetData O;
    @Nullable
    private ActivityHealthData a;
    @Nullable
    private ActivityPositionData T;

    public ActivitySnapshotPayloadBuilder H(@Nullable ActivityPositionData activityPositionData) {
        this.T = activityPositionData;
        return this;
    }

    public ActivitySnapshotPayloadBuilder t(@Nullable ActivityTargetData activityTargetData) {
        this.O = activityTargetData;
        return this;
    }

    public ActivitySnapshotPayload X() {
        return new ActivitySnapshotPayload(this.T, this.a, this.O);
    }

    public ActivitySnapshotPayloadBuilder M(@Nullable ActivityHealthData activityHealthData) {
        this.a = activityHealthData;
        return this;
    }
}

