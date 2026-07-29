package gg.vape.config;

import gg.vape.config.ProfileModuleSnapshot;
import gg.vape.config.ProfileSnapshot;
import java.util.Comparator;

public class ProfileModuleSnapshotOrderComparator
implements Comparator<ProfileModuleSnapshot> {
    @Override
    public int compare(ProfileModuleSnapshot profileModuleSnapshot, ProfileModuleSnapshot profileModuleSnapshot2) {
        return Integer.compare(profileModuleSnapshot2.getSortPriority(), profileModuleSnapshot.getSortPriority());
    }
}
