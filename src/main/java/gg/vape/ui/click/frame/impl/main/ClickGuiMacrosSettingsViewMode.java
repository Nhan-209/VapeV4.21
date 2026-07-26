package gg.vape.ui.click.frame.impl.main;

public enum ClickGuiMacrosSettingsViewMode {
    NAME_INPUT,
    KEYBIND_INPUT,
    FULL_SETTINGS;

    private static final ClickGuiMacrosSettingsViewMode[] A;

    static {
        String[] stringArray = new String[]{"KEYBIND_INPUT", "FULL_SETTINGS", "NAME_INPUT"};



        A = new ClickGuiMacrosSettingsViewMode[]{NAME_INPUT, KEYBIND_INPUT, FULL_SETTINGS};
    }
}

