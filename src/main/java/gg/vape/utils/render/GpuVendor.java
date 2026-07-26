package gg.vape.utils.render;

public enum GpuVendor {
    NVIDIA,
    AMD,
    INTEL,
    UNKNOWN;

    private static final /* synthetic */ GpuVendor[] V;

    static {
        String[] stringArray = new String[]{"UNKNOWN", "NVIDIA", "AMD", "INTEL"};




        V = new GpuVendor[]{NVIDIA, AMD, INTEL, UNKNOWN};
    }
}

