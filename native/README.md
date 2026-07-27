# Vape 4.21 native test bridge

This directory contains an x64 Windows JNI/JVMTI bridge reconstructed from
the nine-method `RegisterNatives` table in `sample.dll`. It is intended for an
isolated Minecraft 1.8.9 test instance owned by the research project.

The authoritative bridge surface is:

```text
scb(Class, byte[]) : int
smd(int, int) : void
gks(int) : short
gkn(long) : String
mvk(int, int) : int
gcb(Class) : byte[]
gfb(String) : byte[]
trs(int) : void
inv(Method, Object, Object[]) : Object
```

The additional native declarations currently present in the recovered Java
class are not registered by `sample.dll`, and the PE has no export table or
second registration path. They are intentionally not invented here.

## Build

Use Gradle 8.8 from `product` to build the Java 8 payload, embed all remotely
managed runtime dependencies, compile the native targets, and assemble the
bundle:

```powershell
.\gradlew.bat prepareInjectionBundle -PtargetRelease=8 `
  -PnativeJavaHome="C:\Program Files\Java\jdk1.8.0_301"
```

For native-only development, invoke CMake directly with the injection JAR:

```powershell
cmake -S . -B build -A x64 `
  -DVAPE421_JAVA_HOME="C:\Program Files\Java\jdk1.8.0_301" `
  -DVAPE421_PRODUCT_JAR="..\build\libs\vape421-product-recovery-4.21-recovered-injection.jar"
cmake --build build --config Release
```

Outputs are written to `build/dist`:

- `Vape421Native.dll`
- `Vape421Injector.exe`

GLFW and MinHook are not required. The 2024 implementation is retained as
`legacy_dllmain_2024.c` for audit only and is not compiled.

## Direct injection

`Vape421Native.dll` contains the recovered Java product as an `RCDATA`
resource. Start Minecraft 1.8.9 with a 64-bit JVM and inject the DLL:

```powershell
Vape421Injector.exe <pid> Vape421Native.dll
```

The injector only performs `LoadLibraryW`. Once loaded, the DLL worker waits
for the JVM and Minecraft `Client thread`, materializes its embedded product
JAR into the process temp directory, loads it through the context ClassLoader,
registers the nine native methods, and calls
`NativeBridge.start()` automatically. No second command or start flag is
required. Inspect `vape421-native.log` beside the DLL for the exact result.

The injection payload is compiled with `--release 8`; its project classes use
class-file major version 52. Runtime dependencies are resolved from the
repositories declared in Gradle and merged into that payload, not restored as
vendored source directories. The injector rejects non-x64 processes.
