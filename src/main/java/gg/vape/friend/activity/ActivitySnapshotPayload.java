package gg.vape.friend.activity;

import gg.vape.friend.activity.ActivityHealthData;
import gg.vape.friend.activity.ActivityPositionData;
import gg.vape.friend.activity.ActivityTargetData;
import gg.vape.protocol.ZeusPacketBuffer;
import org.jetbrains.annotations.Nullable;

public class ActivitySnapshotPayload {
    @Nullable
    private ActivityTargetData i;
    @Nullable
    private ActivityHealthData R;
    @Nullable
    private ActivityPositionData T;

    @Nullable
    public ActivityPositionData Q() {
        return this.T;
    }

    public ActivitySnapshotPayload(ZeusPacketBuffer zeusPacketBuffer) {
        if (zeusPacketBuffer.boolean_a()) {
            this.T = new ActivityPositionData(zeusPacketBuffer);
        }
        if (zeusPacketBuffer.boolean_a()) {
            this.R = new ActivityHealthData(zeusPacketBuffer);
        }
        if (zeusPacketBuffer.boolean_a()) {
            this.i = new ActivityTargetData(zeusPacketBuffer);
        }
    }

    @Nullable
    public ActivityHealthData g() {
        return this.R;
    }

    public void i(ZeusPacketBuffer zeusPacketBuffer) {
        ZeusPacketBuffer zeusPacketBuffer2 = zeusPacketBuffer;
        boolean bl = this.T != null;
        zeusPacketBuffer2.Y(bl);
        if (this.T != null) {
            this.T.m(zeusPacketBuffer);
        }
        ZeusPacketBuffer zeusPacketBuffer3 = zeusPacketBuffer;
        boolean bl2 = this.R != null;
        zeusPacketBuffer3.Y(bl2);
        if (this.R != null) {
            this.R.h(zeusPacketBuffer);
        }
        ZeusPacketBuffer zeusPacketBuffer4 = zeusPacketBuffer;
        boolean bl3 = this.i != null;
        zeusPacketBuffer4.Y(bl3);
        if (this.i != null) {
            this.i.i(zeusPacketBuffer);
        }
    }


    @Nullable
    public ActivityTargetData J() {
        return this.i;
    }

    public ActivitySnapshotPayload(@Nullable ActivityPositionData activityPositionData, @Nullable ActivityHealthData activityHealthData, @Nullable ActivityTargetData activityTargetData) {
        this.T = activityPositionData;
        this.R = activityHealthData;
        this.i = activityTargetData;
    }
}

