package gg.vape.sync;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import gg.vape.Vape;
import gg.vape.api.ApiHttpClient;
import gg.vape.api.ApiHttpStatusException;
import gg.vape.api.ApiResponse;
import gg.vape.api.ApiServices;
import gg.vape.api.UserDataResponse;
import gg.vape.config.Profile;
import gg.vape.config.SettingsDataType;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.notification.SettingsSyncStatusNotification;
import gg.vape.runtime.NativeBridge;
import gg.vape.sync.RemoteProfileData;
import gg.vape.sync.RemoteProfileDataMap;
import gg.vape.sync.SyncDebounceWorker;
import gg.vape.sync.SyncStoreRequestWorker;
import gg.vape.utils.Base64Util;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public class SyncThread {
    private SyncStoreRequestWorker z;
    static final boolean q;
    private final SyncDebounceWorker i;
    private final AtomicBoolean E = new AtomicBoolean();
    private static final String[] g;
    private long a = 0L;
    private static final Object[] f;
    private final Vape F;
    private boolean y = false;
    public static final int U;

    private static ApiResponse lambda$fetchSettings$0(Throwable throwable) {
        return null;
    }

    public void z() {
        try {
            SettingsSyncStatusNotification settingsSyncStatusNotification = new SettingsSyncStatusNotification();
            if (!this.F.getPublicProfileSettings().o.L().booleanValue()) {
                this.F.getNotificationManager().x(settingsSyncStatusNotification, true);
            }
            this.M();
            try {
                if (Vape.INSTANCE.getProfilesManager().M() != null) {
                    Vape.INSTANCE.getProfilesManager().M().a();
                }
            }
            catch (Throwable throwable) {
                Vape.logThrowable(throwable);
            }
            JsonObject jsonObject = this.I(true);
            JsonObject jsonObject2 = this.F.getProfilesManager().q(true);
            for (Profile object2 : this.F.getProfilesManager().b()) {
                object2.T(true);
            }
            ApiResponse apiResponse = (ApiResponse)((CompletableFuture)ApiServices.d().c().a(jsonObject).exceptionally(arg_0 -> SyncThread.lambda$storeSettings$1(settingsSyncStatusNotification, arg_0))).join();
            if (apiResponse != null) {
                if (apiResponse.t()) {
                    settingsSyncStatusNotification.I(1);
                } else if (apiResponse.N() != null && (apiResponse.N().contains("Slow down") || apiResponse.N().contains("Rate limited"))) {
                    settingsSyncStatusNotification.I(3);
                } else {
                    settingsSyncStatusNotification.I(4);
                }
            } else if (settingsSyncStatusNotification.i() == 1) {
                settingsSyncStatusNotification.I(5);
            }
            ApiResponse apiResponse2 = (ApiResponse)((CompletableFuture)ApiServices.d().c().F(jsonObject2).exceptionally(arg_0 -> SyncThread.lambda$storeSettings$2(settingsSyncStatusNotification, arg_0))).join();
            if (apiResponse2 != null) {
                if (apiResponse2.t()) {
                    settingsSyncStatusNotification.F(1);
                    for (Profile profile : this.F.getProfilesManager().b()) {
                        profile.c(false);
                    }
                } else if (apiResponse2.N() != null && (apiResponse2.N().contains("Slow down") || apiResponse2.N().contains("Rate limited"))) {
                    settingsSyncStatusNotification.F(3);
                } else {
                    settingsSyncStatusNotification.F(4);
                    settingsSyncStatusNotification.f(apiResponse2.N());
                }
            } else if (settingsSyncStatusNotification.v$src$Ljava_lang_Integer_$1kwyf2e() == 1) {
                settingsSyncStatusNotification.F(5);
            }
            if (apiResponse2 != null && apiResponse2.t()) {
                if (!q && apiResponse2.T() == null) {
                    throw new AssertionError();
                }
                RemoteProfileDataMap remoteProfileDataMap = (RemoteProfileDataMap)apiResponse2.T();
                for (Profile profile : this.F.getProfilesManager().b()) {
                    RemoteProfileData remoteProfileData = remoteProfileDataMap.S().values().stream().filter(arg_0 -> SyncThread.lambda$storeSettings$3(profile, arg_0)).findFirst().orElse(null);
                    if (remoteProfileData == null || remoteProfileData.d().equals(profile.P$src$Ljava_util_UUID_$kdhg08())) continue;
                    profile.K(remoteProfileData.d());
                }
            }
            settingsSyncStatusNotification.B();
            if (settingsSyncStatusNotification.t$src$Z$1jerbif() && this.F.getPublicProfileSettings().o.L().booleanValue()) {
                this.F.getNotificationManager().m(settingsSyncStatusNotification);
            }
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
        this.E.set(false);
    }

    private static Field b(Class clazz, String string, Class clazz2) {
        Field field = SyncThread.a(clazz, string, clazz2);
        if (field != null) {
            return field;
        }
        Class<?>[] classArray = clazz.getInterfaces();
        if (classArray != null) {
            for (int i = 0; i < classArray.length; ++i) {
                field = SyncThread.b(classArray[i], string, clazz2);
                if (field == null) continue;
                return field;
            }
        }
        return null;
    }

    private static boolean lambda$storeSettings$3(Profile profile, RemoteProfileData remoteProfileData) {
        return remoteProfileData.i().equals(profile.n$src$Ljava_lang_String_$xqhelw()) || remoteProfileData.i().equals("b64:" + Base64Util.encodeUtf8Base64(profile.n$src$Ljava_lang_String_$xqhelw()));
    }

    private static Field a(Class clazz, String string, Class clazz2) {
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.getName().equals(string) || field.getType() != clazz2) continue;
            return field;
        }
        return null;
    }

    private static ApiResponse lambda$storeSettings$2(SettingsSyncStatusNotification settingsSyncStatusNotification, Throwable throwable) {
        Throwable throwable2;
        Throwable throwable3 = throwable;
        while ((throwable2 = throwable.getCause()) != null) {
            throwable = throwable2;
        }
        if (throwable instanceof ApiHttpStatusException) {
            settingsSyncStatusNotification.F(((ApiHttpStatusException)throwable).m());
        } else {
            settingsSyncStatusNotification.F(2);
            if (throwable instanceof IOException) {
                settingsSyncStatusNotification.K(throwable.getClass().getName());
                settingsSyncStatusNotification.Z(throwable.getMessage());
            }
        }
        return null;
    }

    public void o() {
        this.a = System.currentTimeMillis();
        this.E.set(false);
        if (this.z == null) {
            this.z = new SyncStoreRequestWorker();
            new Thread(this.z).start();
        }
        this.z.Y();
    }

    private static ApiResponse lambda$storeSettings$1(SettingsSyncStatusNotification settingsSyncStatusNotification, Throwable throwable) {
        Throwable throwable2;
        Throwable throwable3 = throwable;
        while ((throwable2 = throwable.getCause()) != null) {
            throwable = throwable2;
        }
        if (throwable instanceof ApiHttpStatusException) {
            settingsSyncStatusNotification.I(((ApiHttpStatusException)throwable).m());
        } else {
            settingsSyncStatusNotification.I(2);
        }
        return null;
    }

    public boolean a$src$Z$5edl1q() {
        return this.E.get();
    }

    public long B() {
        return this.a;
    }

    private static CallSite b(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_0().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string, methodType), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("a/gH" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static Object a(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, MethodType methodType, Object[] objectArray) throws Throwable {
        int n = objectArray.length - 2;
        long l = (Long)objectArray[n];
        long l2 = (Long)objectArray[++n];
        MethodHandle methodHandle = SyncThread.a(lookup, mutableCallSite, string, methodType, l, l2);
        mutableCallSite.setTarget(MethodHandles.explicitCastArguments(methodHandle, methodType));
        return methodHandle.asSpreader(Object[].class, objectArray.length).invoke(objectArray);
    }

    private static Method d(long l, long l2) {
        int n = SyncThread.a(l, l2);
        Object object = f[n];
        if (object instanceof String) {
            Class<?>[] classArray;
            Method method;
            Class clazz;
            Class clazz2;
            Class[] classArray2;
            int n2;
            String string;
            Class clazz3;
            block10: {
                String string2 = g[n];
                int n3 = string2.indexOf(8);
                clazz3 = SyncThread.b(Long.parseLong(string2.substring(0, n3), 36), 0L);
                int n4 = string2.indexOf(8, ++n3);
                string = string2.substring(n3, n4);
                int n5 = -1;
                int n6 = n4;
                do {
                    ++n5;
                    ++n6;
                } while ((n6 = string2.indexOf(8, n6)) > -1);
                n2 = n5 - 1;
                classArray2 = new Class[n2];
                clazz2 = null;
                n6 = n4 + 1;
                for (int i = 0; i < n5; ++i) {
                    int n7 = string2.indexOf(8, n6);
                    clazz2 = SyncThread.b(Long.parseLong(string2.substring(n6, n7), 36), 0L);
                    if (i < n2) {
                        classArray2[i] = clazz2;
                    }
                    n6 = n7 + 1;
                }
                clazz = clazz3;
                do {
                    if ((method = SyncThread.a(clazz, string, clazz2, n2, classArray2)) != null) {
                        SyncThread.f[n] = method;
                        return method;
                    }
                    if (clazz.getName().equals("java.lang.Object")) break block10;
                } while ((clazz = clazz.getSuperclass()) != null);
                clazz = SyncThread.b(211101821953761L, 0L);
            }
            clazz = clazz3;
            while (true) {
                if ((classArray = clazz.getInterfaces()) != null) {
                    for (int i = 0; i < classArray.length; ++i) {
                        method = SyncThread.b(classArray[i], string, clazz2, n2, classArray2);
                        if (method == null) continue;
                        SyncThread.f[n] = method;
                        return method;
                    }
                }
                if (clazz.getName().equals("java.lang.Object")) break;
                if ((clazz = clazz.getSuperclass()) != null) continue;
                clazz = SyncThread.b(211101821953761L, 0L);
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("NoSuchMethodException in ").append(clazz3.getName()).append(' ').append(clazz2.getName()).append(' ').append(string).append('(');
            int n8 = 0;
            while (n8 < n2) {
                stringBuffer.append(classArray2[n8].getName());
                if (++n8 >= n2) continue;
                stringBuffer.append(", ");
            }
            stringBuffer.append(')');
            throw new RuntimeException(stringBuffer.toString());
        }
        return (Method)object;
    }

    private static Method a(Class clazz, String string, Class clazz2, int n, Class[] classArray) {
        block0: for (Method method : clazz.getDeclaredMethods()) {
            Class<?>[] classArray2;
            if (!method.getName().equals(string) || method.getReturnType() != clazz2 || (classArray2 = method.getParameterTypes()).length != n) continue;
            for (int i = 0; i < n; ++i) {
                if (classArray2[i] != classArray[i]) continue block0;
            }
            return method;
        }
        return null;
    }

    private static Method b(Class clazz, String string, Class clazz2, int n, Class[] classArray) {
        Method method = SyncThread.a(clazz, string, clazz2, n, classArray);
        if (method != null) {
            return method;
        }
        Class<?>[] classArray2 = clazz.getInterfaces();
        if (classArray2 != null) {
            for (int i = 0; i < classArray2.length; ++i) {
                method = SyncThread.b(classArray2[i], string, clazz2, n, classArray);
                if (method == null) continue;
                return method;
            }
        }
        return null;
    }

    static {
        f = new Object[4];
        g = new String[4];
        SyncThread.b();
        long l = -4373648610076595616L;
        U = (int)l;
        q = !SyncThread.class.desiredAssertionStatus();
    }

    private static int a(long l, long l2) {
        int n;
        int n2;
        int n3;
        int n4 = (int)((l ^= l2 << 48 | l2) >>> 46);
        if (g[n4] != null) {
            return n4;
        }
        Object object = f[n4];
        if (!(object instanceof String)) {
            return n4;
        }
        int n5 = 0;
        switch ((int)(l >>> 42 & 0x3FL)) {
            case 0: {
                n3 = 57;
                break;
            }
            case 1: {
                n3 = 34;
                break;
            }
            case 2: {
                n3 = 27;
                break;
            }
            case 3: {
                n3 = 40;
                break;
            }
            case 4: {
                n3 = 28;
                break;
            }
            case 5: {
                n3 = 45;
                break;
            }
            case 6: {
                n3 = 18;
                break;
            }
            case 7: {
                n3 = 32;
                break;
            }
            case 8: {
                n3 = 39;
                break;
            }
            case 9: {
                n3 = 23;
                break;
            }
            case 10: {
                n3 = 50;
                break;
            }
            case 11: {
                n3 = 35;
                break;
            }
            case 12: {
                n3 = 9;
                break;
            }
            case 13: {
                n3 = 42;
                break;
            }
            case 14: {
                n3 = 26;
                break;
            }
            case 15: {
                n3 = 52;
                break;
            }
            case 16: {
                n3 = 55;
                break;
            }
            case 17: {
                n3 = 30;
                break;
            }
            case 18: {
                n3 = 44;
                break;
            }
            case 19: {
                n3 = 16;
                break;
            }
            case 20: {
                n3 = 21;
                break;
            }
            case 21: {
                n3 = 22;
                break;
            }
            case 22: {
                n3 = 6;
                break;
            }
            case 23: {
                n3 = 13;
                break;
            }
            case 24: {
                n3 = 58;
                break;
            }
            case 25: {
                n3 = 38;
                break;
            }
            case 26: {
                n3 = 43;
                break;
            }
            case 27: {
                n3 = 10;
                break;
            }
            case 28: {
                n3 = 25;
                break;
            }
            case 29: {
                n3 = 59;
                break;
            }
            case 30: {
                n3 = 51;
                break;
            }
            case 31: {
                n3 = 20;
                break;
            }
            case 32: {
                n3 = 15;
                break;
            }
            case 33: {
                n3 = 48;
                break;
            }
            case 34: {
                n3 = 14;
                break;
            }
            case 35: {
                n3 = 36;
                break;
            }
            case 36: {
                n3 = 63;
                break;
            }
            case 37: {
                n3 = 17;
                break;
            }
            case 38: {
                n3 = 8;
                break;
            }
            case 39: {
                n3 = 4;
                break;
            }
            case 40: {
                n3 = 33;
                break;
            }
            case 41: {
                n3 = 0;
                break;
            }
            case 42: {
                n3 = 3;
                break;
            }
            case 43: {
                n3 = 1;
                break;
            }
            case 44: {
                n3 = 54;
                break;
            }
            case 45: {
                n3 = 5;
                break;
            }
            case 46: {
                n3 = 56;
                break;
            }
            case 47: {
                n3 = 47;
                break;
            }
            case 48: {
                n3 = 31;
                break;
            }
            case 49: {
                n3 = 53;
                break;
            }
            case 50: {
                n3 = 11;
                break;
            }
            case 51: {
                n3 = 62;
                break;
            }
            case 52: {
                n3 = 61;
                break;
            }
            case 53: {
                n3 = 24;
                break;
            }
            case 54: {
                n3 = 37;
                break;
            }
            case 55: {
                n3 = 7;
                break;
            }
            case 56: {
                n3 = 41;
                break;
            }
            case 57: {
                n3 = 29;
                break;
            }
            case 58: {
                n3 = 2;
                break;
            }
            case 59: {
                n3 = 19;
                break;
            }
            case 60: {
                n3 = 46;
                break;
            }
            case 61: {
                n3 = 49;
                break;
            }
            case 62: {
                n3 = 60;
                break;
            }
            default: {
                n3 = 12;
            }
        }
        n5 = n3;
        int[] nArray = new int[6];
        int n6 = 0;
        while (n6 < 6) {
            n2 = 7 * (5 - n6);
            n = (int)(l >>> n2 & 0x7FL);
            if ((n -= n5) < 0) {
                n += 128;
            }
            nArray[n6] = n;
            ++n6;
        }
        char[] cArray = ((String)object).toCharArray();
        n2 = 0;
        while (n2 < cArray.length) {
            n = nArray[n2 % nArray.length];
            if (n == 0) break;
            cArray[n2] = (char)(cArray[n2] ^ n);
            ++n2;
        }
        SyncThread.g[n4] = new String(cArray);
        return n4;
    }

    private static Field c(long l, long l2) {
        int n = SyncThread.a(l, l2);
        Object object = f[n];
        if (object instanceof String) {
            String string = g[n];
            int n2 = string.indexOf(8);
            Class clazz = SyncThread.b(Long.parseLong(string.substring(0, n2), 36), 0L);
            int n3 = string.indexOf(8, ++n2);
            String string2 = string.substring(n2, n3);
            Class clazz2 = SyncThread.b(Long.parseLong(string.substring(++n3), 36), 0L);
            Class clazz3 = clazz;
            while (true) {
                Field field;
                if ((field = SyncThread.a(clazz3, string2, clazz2)) != null) {
                    SyncThread.f[n] = field;
                    return field;
                }
                Class<?>[] classArray = clazz3.getInterfaces();
                if (classArray != null) {
                    for (int i = 0; i < classArray.length; ++i) {
                        field = SyncThread.b(classArray[i], string2, clazz2);
                        if (field == null) continue;
                        SyncThread.f[n] = field;
                        return field;
                    }
                }
                if (clazz3.getName().equals("java.lang.Object")) break;
                if ((clazz3 = clazz3.getSuperclass()) != null) continue;
                clazz3 = SyncThread.b(211101821953761L, 0L);
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("NoSuchFieldException in ").append(clazz.getName()).append(' ').append(clazz2.getName()).append(' ').append(string2);
            throw new RuntimeException(stringBuffer.toString());
        }
        return (Field)object;
    }

    private static Class b(long l, long l2) {
        Object object;
        Class<?> clazz;
        block2: {
            clazz = null;
            int n = SyncThread.a(l, l2);
            object = f[n];
            try {
                if (!(object instanceof String)) break block2;
                SyncThread.f[n] = clazz = Class.forName(g[n]);
            }
            catch (Exception exception) {
                throw new RuntimeException(exception.toString());
            }
        }
        clazz = (Class<?>)object;
        return clazz;
    }

    public void x() {
        this.E.set(false);
    }

    public void B$src$V$4xbyk3() {
        try {
            boolean bl = this.F.getAccountInfo().r();
            if (!bl) {
                // empty if block
            }
            if (bl) {
                ApiResponse apiResponse = (ApiResponse)((CompletableFuture)ApiServices.d().c().R().exceptionally(SyncThread::lambda$fetchSettings$0)).join();
                if (apiResponse != null && apiResponse.t()) {
                    if (!q && apiResponse.T() == null) {
                        throw new AssertionError();
                    }
                    UserDataResponse userDataResponse = (UserDataResponse)apiResponse.T();
                    HashMap<UUID, JsonObject> hashMap = new HashMap<UUID, JsonObject>();
                    for (RemoteProfileData remoteProfileData : userDataResponse.F().values()) {
                        hashMap.put(remoteProfileData.d(), remoteProfileData.G());
                    }
                    this.F.getPublicProfileManager().j(userDataResponse.s().values());
                    JsonObject jsonObject = new JsonObject();
                    if (userDataResponse.D() != null) {
                        jsonObject.add("friends", (JsonElement)userDataResponse.D());
                    }
                    jsonObject.add("profiles", ApiHttpClient.Z.toJsonTree(hashMap));
                    if (userDataResponse.y() != null) {
                        jsonObject.add("otherData", (JsonElement)userDataResponse.y());
                    }
                    Vape.INSTANCE.loadConfigData(jsonObject, true);
                }
            } else {
                String encodedSettings = NativeBridge.gat();
                String string = encodedSettings == null ? "" : new String(Base64Util.decodeBase64(encodedSettings)).trim();
                JsonReader jsonReader = new JsonReader((Reader)new StringReader(string));
                jsonReader.setLenient(true);
                JsonObject jsonObject = (JsonObject)new Gson().fromJson(jsonReader, JsonObject.class);
                if (jsonObject != null) {
                    Vape.INSTANCE.loadConfigData(jsonObject, false);
                    for (Profile profile : Vape.INSTANCE.getProfilesManager().b()) {
                        profile.c(true);
                    }
                }
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    public SyncThread(Vape vape) {
        this.i = new SyncDebounceWorker();
        this.F = vape;
    }

    public void I() {
        this.E.set(true);
        this.i.I();
    }

    private static Throwable a(Throwable throwable) {
        return throwable;
    }

    public SyncDebounceWorker a() {
        return this.i;
    }

    private static MethodHandle a(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, MethodType methodType, long l, long l2) {
        char c = string.charAt(0);
        MethodHandle methodHandle = null;
        Field field = null;
        Method method = null;
        try {
            if (c == '\u00d4' || c == '\u00b5' || c == '\u00d5' || c == '\u00fb') {
                field = SyncThread.c(l, l2);
                Class<?> clazz = field.getDeclaringClass();
                String string2 = field.getName();
                Class<?> clazz2 = field.getType();
                methodHandle = c == '\u00d4' ? lookup.findGetter(clazz, string2, clazz2) : (c == '\u00b5' ? lookup.findSetter(clazz, string2, clazz2) : (c == '\u00d5' ? lookup.findStaticGetter(clazz, string2, clazz2) : lookup.findStaticSetter(clazz, string2, clazz2)));
            } else {
                method = SyncThread.d(l, l2);
                Class<?> clazz = method.getDeclaringClass();
                String string3 = method.getName();
                MethodType methodType2 = MethodType.methodType(method.getReturnType(), method.getParameterTypes());
                methodHandle = c == 'w' ? lookup.findVirtual(clazz, string3, methodType2) : (c == 'i' ? lookup.findStatic(clazz, string3, methodType2) : lookup.findSpecial(clazz, string3, methodType2, clazz));
            }
            return MethodHandles.dropArguments(methodHandle, methodType.parameterCount() - 2, Long.TYPE, Long.TYPE);
        }
        catch (Exception exception) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(exception.getClass().getName()).append(" : ").append(field != null ? field.toString() : (method != null ? method.toString() : " null ")).append(" : ").append(exception.toString());
            throw new RuntimeException(stringBuilder.toString());
        }
    }

    private void M() {
        if (!OnlineConnectionManager.T.S().M()) {
            try {
                ApiServices.d().v().u(SettingsDataType.ONLINE, OnlineConnectionManager.T.S().X());
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        try {
            boolean[] blArray = NativeBridge.gls();
            boolean bl = blArray[0];
            boolean bl2 = blArray[1];
            if (!this.y && bl) {
                OnlineConnectionManager.T.g().F().f(true);
                OnlineConnectionManager.T.g().F().o(bl2);
                OnlineConnectionManager.T.g().F().f(false);
                this.y = true;
            }
            OnlineConnectionManager.T.g().i();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private static void b() {
        Object[] objectArray = f;
        f[0] = "F\u00061";
        objectArray[1] = "B\u00073($\u001dI\b\"gY\u0005Z\u000f+.";
        objectArray[2] = ":!o!0^1.~nQP:%z4";
        Object[] objectArray2 = objectArray;
        objectArray[3] = "\u0011\u001f!b%9\u0003\u001d/\f%x|]pc&s\u0016\u000b.m+\u0003";
    }

    public JsonObject I(boolean bl) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("friends", (JsonElement)Vape.INSTANCE.getFriendManager().toJson());
        jsonObject.add(bl ? "otherData" : "otherdata", (JsonElement)Vape.INSTANCE.getSettingsManager().toJson());
        if (!bl) {
            jsonObject.add("profiles", (JsonElement)Vape.INSTANCE.getProfilesManager().q(false));
        }
        return jsonObject;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SyncThread.class, "a", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;Ljava/lang/invoke/MethodType;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}
