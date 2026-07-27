package gg.vape.runtime;

import gg.vape.mapping.EventInjectionSpec;
import gg.vape.mapping.MappedClasses;
import gg.vape.runtime.ClassByteCursor;
import gg.vape.runtime.NativeBridge;
import gg.vape.ui.click.component.GuiComponent;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class ClassBytecodeCache {
    private static final Map<Class<?>, byte[]> V = new HashMap();

    public static int C(Class<?> clazz, byte[] byArray) {
        byte[] byArray2 = byArray;
        Class<?> clazz2 = clazz;
        int n = NativeBridge.scb(clazz2, byArray2);
        GuiComponent[] guiComponentArray = EventInjectionSpec.F$src$ALgg_vape_ui_click_component_GuiComponent_$1y0d4sz();
        int n2 = n;
        if (guiComponentArray == null) {
            if (n2 == 0) {
                V.put(clazz, byArray);
            }
            n2 = n;
        }
        return n2;
    }

    public static byte[] U(Class<?> clazz, boolean bl) {
        Class<?> clazz2 = clazz;
        Map<Class<?>, byte[]> map = V;
        byte[] byArray = map.get(clazz2);
        GuiComponent[] guiComponentArray = EventInjectionSpec.F$src$ALgg_vape_ui_click_component_GuiComponent_$1y0d4sz();
        if (byArray != null) {
            return byArray;
        }
        Class<?> clazz3 = clazz;
        byte[] byArray2 = NativeBridge.gcb(clazz3);
        if (byArray2 != null) {
            byte[] byArray3;
            int n;
            if (MappedClasses.x()[19] != null) {
                n = 0;
                n += 4;
                n += 2;
                ClassByteCursor classByteCursor = new ClassByteCursor();
                classByteCursor.G = n += 2;
                ClassByteCursor classByteCursor2 = classByteCursor;
                byte[] byArray4 = byArray2;
                int n2 = ClassBytecodeCache.J(byArray4, classByteCursor2);
                for (int i = 0; i < n2 - 1; ++i) {
                    int n3;
                    int n4;
                    byte[] byArray5 = byArray2;
                    ClassByteCursor classByteCursor3 = classByteCursor;
                    Byte by = ClassBytecodeCache.u$src$Ljava_lang_Byte_$1i3qt9n(byArray5, classByteCursor3);
                    int n5 = by.intValue();
                    if (n5 == 7) {
                        ClassByteCursor classByteCursor4 = classByteCursor;
                        byte[] byArray6 = byArray2;
                        n4 = ClassBytecodeCache.J(byArray6, classByteCursor4);
                        continue;
                    }
                    if (n5 == 9 || n5 == 10 || n5 == 11) {
                        ClassByteCursor classByteCursor5 = classByteCursor;
                        byte[] byArray7 = byArray2;
                        n4 = ClassBytecodeCache.J(byArray7, classByteCursor5);
                        ClassByteCursor classByteCursor6 = classByteCursor;
                        byte[] byArray8 = byArray2;
                        n3 = ClassBytecodeCache.J(byArray8, classByteCursor6);
                        continue;
                    }
                    if (n5 == 8) {
                        ClassByteCursor classByteCursor7 = classByteCursor;
                        byte[] byArray9 = byArray2;
                        n4 = ClassBytecodeCache.J(byArray9, classByteCursor7);
                        continue;
                    }
                    if (n5 == 3 || n5 == 4) {
                        ClassByteCursor classByteCursor8 = classByteCursor;
                        byte[] byArray10 = byArray2;
                        n4 = ClassBytecodeCache.u(byArray10, classByteCursor8);
                        continue;
                    }
                    if (n5 == 5 || n5 == 6) {
                        ClassByteCursor classByteCursor9 = classByteCursor;
                        byte[] byArray11 = byArray2;
                        n4 = ClassBytecodeCache.u(byArray11, classByteCursor9);
                        ClassByteCursor classByteCursor10 = classByteCursor;
                        byte[] byArray12 = byArray2;
                        n3 = ClassBytecodeCache.u(byArray12, classByteCursor10);
                        ++i;
                        if (n5 != 5) continue;
                        long l = ((long)n4 << 32) + (long)n3;
                        l += 14L;
                        l /= 3L;
                        l -= (long)n2;
                        l /= 7L;
                        if (Math.abs((long)byArray2.length - (l -= 7383L)) >= 100L) continue;
                        MappedClasses.x()[18] = MappedClasses.x()[29];
                        MappedClasses.x()[19] = null;
                        continue;
                    }
                    if (n5 == 12) {
                        ClassByteCursor classByteCursor11 = classByteCursor;
                        byte[] byArray13 = byArray2;
                        n4 = ClassBytecodeCache.J(byArray13, classByteCursor11);
                        ClassByteCursor classByteCursor12 = classByteCursor;
                        byte[] byArray14 = byArray2;
                        n3 = ClassBytecodeCache.J(byArray14, classByteCursor12);
                        continue;
                    }
                    if (n5 == 1) {
                        ClassByteCursor classByteCursor13 = classByteCursor;
                        byte[] byArray15 = byArray2;
                        n4 = ClassBytecodeCache.J(byArray15, classByteCursor13);
                        for (n3 = 0; n3 < n4; ++n3) {
                            ClassByteCursor classByteCursor14 = classByteCursor;
                            byte[] byArray16 = byArray2;
                            Byte by2 = ClassBytecodeCache.u$src$Ljava_lang_Byte_$1i3qt9n(byArray16, classByteCursor14);
                            byte by3 = by2;
                        }
                        continue;
                    }
                    if (n5 == 15) {
                        ClassByteCursor classByteCursor15 = classByteCursor;
                        byte[] byArray17 = byArray2;
                        Byte by4 = ClassBytecodeCache.u$src$Ljava_lang_Byte_$1i3qt9n(byArray17, classByteCursor15);
                        n4 = by4.byteValue();
                        ClassByteCursor classByteCursor16 = classByteCursor;
                        byte[] byArray18 = byArray2;
                        n3 = ClassBytecodeCache.J(byArray18, classByteCursor16);
                        continue;
                    }
                    if (n5 == 16) {
                        ClassByteCursor classByteCursor17 = classByteCursor;
                        byte[] byArray19 = byArray2;
                        n4 = ClassBytecodeCache.J(byArray19, classByteCursor17);
                        continue;
                    }
                    if (n5 != 18) continue;
                    ClassByteCursor classByteCursor18 = classByteCursor;
                    byte[] byArray20 = byArray2;
                    n4 = ClassBytecodeCache.J(byArray20, classByteCursor18);
                    ClassByteCursor classByteCursor19 = classByteCursor;
                    byte[] byArray21 = byArray2;
                    n3 = ClassBytecodeCache.J(byArray21, classByteCursor19);
                }
            }
            if (bl && (n = ClassBytecodeCache.L(byArray3 = byArray2)) > 64) {
                int n6 = 64;
                byte[] byArray22 = byArray2;
                ClassBytecodeCache.Z(byArray22, n6);
            }
            byte[] byArray23 = byArray2;
            Class<?> clazz4 = clazz;
            Map<Class<?>, byte[]> map2 = V;
            map2.put(clazz4, byArray23);
            return byArray2;
        }
        if (bl) {
            // empty if block
        }
        byte[] byArray24 = byArray2;
        Class<?> clazz5 = clazz;
        Map<Class<?>, byte[]> map3 = V;
        map3.put(clazz5, byArray24);
        return byArray2;
    }

    static Byte u$src$Ljava_lang_Byte_$1i3qt9n(byte[] byArray, ClassByteCursor classByteCursor) {
        return byArray[classByteCursor.G++];
    }


    static int J(byte[] byArray, ClassByteCursor classByteCursor) {
        ByteBuffer byteBuffer;
        block2: {
            ByteBuffer byteBuffer2 = ByteBuffer.allocate(4);
            GuiComponent[] guiComponentArray = EventInjectionSpec.F$src$ALgg_vape_ui_click_component_GuiComponent_$1y0d4sz();
            for (int i = 2; i < 4; ++i) {
                byteBuffer = byteBuffer2.put(i, byArray[classByteCursor.G++]);
                if (guiComponentArray == null) {
                    continue;
                }
                break block2;
            }
            byteBuffer = byteBuffer2;
        }
        return byteBuffer.getInt();
    }

    public static byte[] I(Class<?> clazz) {
        boolean bl = false;
        Class<?> clazz2 = clazz;
        return ClassBytecodeCache.U(clazz2, bl);
    }

    public static void Z(byte[] byArray, int n) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.putInt(n);
        int n2 = 0;
        n2 += 4;
        GuiComponent[] guiComponentArray = EventInjectionSpec.F$src$ALgg_vape_ui_click_component_GuiComponent_$1y0d4sz();
        ClassByteCursor classByteCursor = new ClassByteCursor();
        classByteCursor.G = n2 += 2;
        byte[] byArray2 = byteBuffer.array();
        for (int i = 2; i < 4; ++i) {
            ClassByteCursor classByteCursor2 = classByteCursor;
            byte[] byArray3 = byArray;
            Byte by = byArray2[i];
            ClassBytecodeCache.x(by, byArray3, classByteCursor2);
            if (guiComponentArray == null) continue;
            GuiComponent.D(new GuiComponent[5]);
            break;
        }
    }

    public static int L(byte[] byArray) {
        int n = 0;
        n += 4;
        ClassByteCursor classByteCursor = new ClassByteCursor();
        classByteCursor.G = n += 2;
        int n2 = ClassBytecodeCache.J(byArray, classByteCursor);
        return n2;
    }

    public static void T(byte[] byArray, int n) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.putInt(n);
        int n2 = 0;
        ClassByteCursor classByteCursor = new ClassByteCursor();
        classByteCursor.G = n2 += 4;
        byte[] byArray2 = byteBuffer.array();
        GuiComponent[] guiComponentArray = EventInjectionSpec.F$src$ALgg_vape_ui_click_component_GuiComponent_$1y0d4sz();
        for (int i = 2; i < 4; ++i) {
            ClassByteCursor classByteCursor2 = classByteCursor;
            byte[] byArray3 = byArray;
            Byte by = byArray2[i];
            ClassBytecodeCache.x(by, byArray3, classByteCursor2);
            if (guiComponentArray == null) continue;
        }
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            EventInjectionSpec.o(new GuiComponent[1]);
        }
    }

    public static int C(byte[] byArray) {
        int n = 0;
        ClassByteCursor classByteCursor = new ClassByteCursor();
        classByteCursor.G = n += 4;
        int n2 = ClassBytecodeCache.J(byArray, classByteCursor);
        return n2;
    }

    static int u(byte[] byArray, ClassByteCursor classByteCursor) {
        ByteBuffer byteBuffer;
        block2: {
            ByteBuffer byteBuffer2 = ByteBuffer.allocate(4);
            GuiComponent[] guiComponentArray = EventInjectionSpec.F$src$ALgg_vape_ui_click_component_GuiComponent_$1y0d4sz();
            for (int i = 0; i < 4; ++i) {
                byteBuffer = byteBuffer2.put(i, byArray[classByteCursor.G++]);
                if (guiComponentArray == null) {
                    continue;
                }
                break block2;
            }
            byteBuffer = byteBuffer2;
        }
        return byteBuffer.getInt();
    }

    static void x(Byte by, byte[] byArray, ClassByteCursor classByteCursor) {
        int n = 0;
        byte[] byArray2 = new byte[]{by};
        if (EventInjectionSpec.F$src$ALgg_vape_ui_click_component_GuiComponent_$1y0d4sz() != null) {
            if (n < 1) {
                int n2 = classByteCursor.G;
            }
            return;
        }
        for (n = 0; n < 1; ++n) {
            int n3 = classByteCursor.G;
            if (byArray != null) {
                byArray[n3] = byArray2[n];
            }
            classByteCursor.G = n3 + 1;
        }
    }
}
