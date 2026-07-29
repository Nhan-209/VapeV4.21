package gg.vape.asm.transform;

import gg.vape.Vape;
import gg.vape.asm.ITramsformNode;
import gg.vape.asm.helper.EventBuilder;
import gg.vape.asm.helper.MethodInfo;
import gg.vape.asm.transform.TransformClassWriter;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.MappingTask;
import gg.vape.runtime.ClassBytecodeCache;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.LaunchClassLoader;
import java.io.File;
import java.io.FileOutputStream;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

public abstract class ClassTransformer
implements MappingTask {
    protected byte[] E;
    private static int t;
    protected Class G;
    private boolean r;
    protected ClassNode Y;
    private boolean e;
    protected byte[] C;

    public static int Q$src$I$ouk7m6() {
        int n = ClassTransformer.i();
        if (n == 0) {
            return 11;
        }
        return 0;
    }

    public static void setTransformerState(int n) {
        t = n;
    }

    public void injectEventAtEntry(MappingMethod mappingMethod, Class clazz, ITramsformNode ... iTramsformNodeArray) {
        EventBuilder eventBuilder = new EventBuilder(0, clazz, this.Y, new MethodInfo(mappingMethod.v(), mappingMethod.j()), mappingMethod.d(), iTramsformNodeArray);
        eventBuilder.I();
        eventBuilder.W();
    }

    @Override
    public abstract void c();

    public void writeBytesToFile(String string, byte[] byArray) {
        int n = ClassTransformer.Q$src$I$ouk7m6();
        try {
            File file = new File(string);
            boolean bl = file.getParentFile().exists();
            if (n != 0 && !bl) {
                bl = file.getParentFile().mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(byArray);
            fileOutputStream.close();
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    @Override
    public void j() {
    }

    @Override
    public Class B() {
        return this.G;
    }

    public ClassTransformer(Class clazz) {
        if (!Vape.INSTANCE.isNativeAvailable()) {
            LaunchClassLoader.getLaunchClassLoader().cachedClasses().put(clazz.getName(), clazz);
        }
        this.G = clazz;
    }

    public static int i() {
        return t;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    @Override
    public boolean Q() {
        return this.e;
    }

    @Override
    public int J() {
        if (this.r) {
            return -3;
        }
        this.serializeClassNode();
        byte[] byArray = this.C;
        Class clazz = this.G;
        int n = ClassBytecodeCache.setClassBytecode(clazz, byArray);
        if (n == 0) {
            this.e = true;
        }
        return n;
    }

    public void injectEventAtExit(MappingMethod mappingMethod, Class clazz, ITramsformNode ... iTramsformNodeArray) {
        EventBuilder eventBuilder = new EventBuilder(-1, clazz, this.Y, new MethodInfo(mappingMethod.v(), mappingMethod.j()), mappingMethod.d(), iTramsformNodeArray);
        eventBuilder.I();
        eventBuilder.W();
    }

    @Override
    public void K() {
        this.E = ClassBytecodeCache.getClassBytecode(this.G, true);
        this.C = new byte[this.E.length];
        System.arraycopy(this.E, 0, this.C, 0, this.E.length);
        ClassReader classReader = new ClassReader(this.E);
        this.Y = new ClassNode();
        classReader.accept(this.Y, 0);
    }

    static {
        if (ClassTransformer.Q$src$I$ouk7m6() == 0) {
            ClassTransformer.setTransformerState(92);
        }
    }

    @Override
    public void O() {
        byte[] byArray = this.E;
        Class clazz = this.G;
        ClassBytecodeCache.setClassBytecode(clazz, byArray);
    }

    public void serializeClassNode() {
        int n = 3;
        if (ForgeVersion.MC_1_12_2.d()) {
            n = 2;
        }
        TransformClassWriter transformClassWriter = new TransformClassWriter(n);
        this.Y.accept(transformClassWriter);
        this.C = transformClassWriter.toByteArray();
    }
}
