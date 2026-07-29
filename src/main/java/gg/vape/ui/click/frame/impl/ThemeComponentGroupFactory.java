package gg.vape.ui.click.frame.impl;

import gg.vape.Vape;
import gg.vape.manager.client.FriendManager;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.manager.client.OnlineSettings;
import gg.vape.notification.FriendNotificationSettings;
import gg.vape.ui.click.component.ColorDividerComponent;
import gg.vape.ui.click.component.DropdownSelectComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.InsetFilledSpacerComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.input.BindValueRowComponent;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.component.value.ColorValueEditorComponent;
import gg.vape.ui.click.frame.impl.ThemeComponentGroupKey;
import gg.vape.ui.theme.ThemeColors;
import java.util.LinkedHashMap;

public final class ThemeComponentGroupFactory {
    public static GuiComponent[] k(ThemeColors themeColors) {
        return new GuiComponent[]{new BooleanToggleComponent(OnlineConnectionManager.T.S().X$src$Lgg_vape_value_BooleanValue_$7rygmo()), new BooleanToggleComponent(OnlineConnectionManager.T.S().z()), new BooleanToggleComponent(OnlineConnectionManager.T.S().O()), new BooleanToggleComponent(OnlineConnectionManager.T.S().l()), new ColorDividerComponent(themeColors.i)};
    }

    private static Throwable a(Throwable throwable) {
        return throwable;
    }

    public static GuiComponent[] E(ThemeColors themeColors) {
        BindValueRowComponent bindValueRowComponent = new BindValueRowComponent("Ping Keybind", OnlineConnectionManager.T.S().p(), themeColors.Z);
        bindValueRowComponent.getBindInput().setActiveAlpha(20);
        return new GuiComponent[]{bindValueRowComponent};
    }

    public static LinkedHashMap<ThemeComponentGroupKey, GuiComponent[]> R(ThemeColors themeColors) {
        FriendManager friendManager = Vape.INSTANCE.getFriendManager();
        LinkedHashMap<ThemeComponentGroupKey, GuiComponent[]> linkedHashMap = new LinkedHashMap<ThemeComponentGroupKey, GuiComponent[]>();
        linkedHashMap.put(new ThemeComponentGroupKey("Friend Settings", "newfriends"), new GuiComponent[]{new BooleanToggleComponent(friendManager.q), new ColorValueEditorComponent(friendManager.R), new BooleanToggleComponent(friendManager.z), new BooleanToggleComponent(friendManager.J), new BooleanToggleComponent(friendManager.C), new BindValueRowComponent(Vape.INSTANCE.getClientSettings().Z), new InsetFilledSpacerComponent(90.0, 2.0, 0.5, 4.0, themeColors.l), new SpacerComponent(1.0, 2.0)});
        FriendNotificationSettings friendNotificationSettings = null;
        OnlineSettings onlineSettings = null;
        try {
            onlineSettings = OnlineConnectionManager.T.S();
            if (onlineSettings != null) {
                friendNotificationSettings = onlineSettings.m();
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        if (friendNotificationSettings != null) {
            linkedHashMap.put(new ThemeComponentGroupKey("Notification Settings", null), new GuiComponent[]{new BooleanToggleComponent(friendNotificationSettings.general), new BooleanToggleComponent(friendNotificationSettings.friendRequests), new BooleanToggleComponent(friendNotificationSettings.chats), new BooleanToggleComponent(friendNotificationSettings.friendOnline), new BooleanToggleComponent(friendNotificationSettings.partyInvites), new BooleanToggleComponent(friendNotificationSettings.partyInviteAccepted)});
        }
        if (onlineSettings != null) {
            linkedHashMap.put(new ThemeComponentGroupKey("Party Settings", null), new GuiComponent[]{new DropdownSelectComponent(onlineSettings.x()), new BooleanToggleComponent(onlineSettings.y()), new BooleanToggleComponent(onlineSettings.k$src$Lgg_vape_value_BooleanValue_$ffgfgd()), new BooleanToggleComponent(onlineSettings.U())});
        }
        return linkedHashMap;
    }

    private ThemeComponentGroupFactory() {
    }
}
