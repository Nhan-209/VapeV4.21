package gg.vape.unmap;

import gg.vape.unmap.ImageParser$Format;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.zip.Inflater;

public class ImageParser {
    private int T;
    private int k;
    private final byte z = 0;
    private int g;
    private final int i;
    private int F;
    private final int Q;
    private final byte t;
    private byte[] h;
    private final byte l;
    private int u;
    private final byte[] q = new byte[]{-119, 80, 78, 71, 13, 10, 26, 10};
    private final byte o;
    private int V;
    private final int L;
    private byte[] v;
    private byte[] x;
    private final byte[] P;
    private final InputStream n;
    private final int b;
    private int A;
    private final int C;
    private final byte U;
    private int Z;

    private static Exception a(Exception exception) {
        return exception;
    }

    private void n(ByteBuffer byteBuffer, byte[] byArray) {
        int n = byArray.length;
        for (int i = 1; i < n; i += 4) {
            byteBuffer.put(byArray[i + 3]).put(byArray[i + 2]).put(byArray[i + 1]).put(byArray[i]);
        }
    }

    private void C(long l) throws IOException {
        while (l > 0L) {
            long l2 = this.n.skip(l);
            if (l2 < 0L) {
                throw new EOFException();
            }
            l -= l2;
        }
    }

    private boolean E(byte[] byArray) {
        for (int i = 0; i < this.q.length; ++i) {
            if (byArray[i] == this.q[i]) continue;
            return false;
        }
        return true;
    }

    private void L(ByteBuffer byteBuffer, byte[] byArray) {
        int n = byArray.length;
        for (int i = 1; i < n; i += 4) {
            byteBuffer.put(byArray[i]).put(byArray[i + 1]).put(byArray[i + 2]);
        }
    }

    public int k() {
        return this.V;
    }

    public ImageParser$Format Z(ImageParser$Format imageParser$Format) {
        switch (this.g) {
            case 2: {
                switch (imageParser$Format) {
                    case ABGR: 
                    case RGBA: 
                    case BGRA: 
                    case RGB: {
                        return imageParser$Format;
                    }
                }
                return ImageParser$Format.RGB;
            }
            case 6: {
                switch (imageParser$Format) {
                    case ABGR: 
                    case RGBA: 
                    case BGRA: 
                    case RGB: {
                        return imageParser$Format;
                    }
                }
                return ImageParser$Format.RGBA;
            }
            case 0: {
                switch (imageParser$Format) {
                    case LUMINANCE: 
                    case ALPHA: {
                        return imageParser$Format;
                    }
                }
                return ImageParser$Format.LUMINANCE;
            }
            case 4: {
                return ImageParser$Format.LUMINANCE_ALPHA;
            }
            case 3: {
                switch (imageParser$Format) {
                    case ABGR: 
                    case RGBA: 
                    case BGRA: {
                        return imageParser$Format;
                    }
                }
                return ImageParser$Format.RGBA;
            }
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void D(byte[] byArray, byte[] byArray2) throws IOException {
        switch (byArray[0]) {
            case 0: {
                break;
            }
            case 1: {
                this.P(byArray);
                break;
            }
            case 2: {
                this.P(byArray, byArray2);
                break;
            }
            case 3: {
                this.J(byArray, byArray2);
                break;
            }
            case 4: {
                this.O(byArray, byArray2);
                break;
            }
            default: {
                throw new IOException("invalide filter type in scanline: " + byArray[0]);
            }
        }
    }

    private void P(byte[] byArray) {
        int n = this.k;
        int n2 = byArray.length;
        for (int i = n + 1; i < n2; ++i) {
            int n3 = i;
            byArray[n3] = (byte)(byArray[n3] + byArray[i - n]);
        }
    }

    private int X(byte[] byArray, int n, int n2) throws IOException {
        if (n2 > this.u) {
            n2 = this.u;
        }
        this.g(byArray, n, n2);
        this.u -= n2;
        return n2;
    }

    private void p(Inflater inflater) throws IOException {
        while (this.u == 0) {
            this.T();
            this.g(1229209940);
        }
        int n = this.X(this.P, 0, this.P.length);
        inflater.setInput(this.P, 0, n);
    }

    public void u(ByteBuffer byteBuffer, int n, ImageParser$Format imageParser$Format) throws IOException {
        if (n <= 0) {
            throw new IllegalArgumentException("stride");
        }
        int n2 = byteBuffer.position();
        int n3 = (this.V - 1) * n;
        byteBuffer.position(n2 + n3);
        this.i(byteBuffer, -n, imageParser$Format);
        byteBuffer.position(byteBuffer.position() + n3);
    }

    private void P(byte[] byArray, byte[] byArray2) {
        int n = this.k;
        int n2 = byArray.length;
        for (int i = 1; i < n2; ++i) {
            int n3 = i;
            byArray[n3] = (byte)(byArray[n3] + byArray2[i]);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public ImageParser(InputStream inputStream) throws IOException {
        this.C = 1229472850;
        this.L = 1347179589;
        this.b = 1951551059;
        this.i = 1229209940;
        this.Q = 1229278788;
        this.l = (byte)2;
        this.t = (byte)3;
        this.o = (byte)4;
        this.U = (byte)6;
        this.n = inputStream;
        this.P = new byte[4096];
        this.g(this.P, 0, this.q.length);
        if (!this.E(this.P)) {
            throw new IOException("Not a valid PNG file");
        }
        this.g(1229472850);
        this.G();
        this.T();
        block5: while (true) {
            this.Y();
            switch (this.T) {
                case 1229209940: {
                    break block5;
                }
                case 1347179589: {
                    this.t();
                    break;
                }
                case 1951551059: {
                    this.U();
                }
            }
            this.T();
        }
        if (this.g == 3 && this.h == null) {
            throw new IOException("Missing PLTE chunk");
        }
    }

    public boolean O() {
        return this.X() || this.x != null || this.v != null;
    }

    public boolean X() {
        return this.g == 6 || this.g == 4;
    }

    public int q() {
        return this.Z;
    }

    private void l(Inflater inflater, byte[] byArray, int n, int n2) throws IOException {
        try {
            do {
                int n3;
                if ((n3 = inflater.inflate(byArray, n, n2)) <= 0) {
                    if (inflater.finished()) {
                        throw new EOFException();
                    }
                    if (inflater.needsInput()) {
                        this.p(inflater);
                        continue;
                    }
                    throw new IOException("Can't inflate " + n2 + " bytes");
                }
                n += n3;
                n2 -= n3;
            } while (n2 > 0);
        }
        catch (Exception exception) {
            throw new IOException("inflate error", exception);
        }
    }

    private void F(ByteBuffer byteBuffer, byte[] byArray) {
        if (this.x != null) {
            int n = byArray.length;
            for (int i = 1; i < n; ++i) {
                int n2 = byArray[i] & 0xFF;
                byte by = this.h[n2 * 3 + 0];
                byte by2 = this.h[n2 * 3 + 1];
                byte by3 = this.h[n2 * 3 + 2];
                byte by4 = this.x[n2];
                byteBuffer.put(by3).put(by2).put(by).put(by4);
            }
        } else {
            int n = byArray.length;
            for (int i = 1; i < n; ++i) {
                int n3 = byArray[i] & 0xFF;
                byte by = this.h[n3 * 3 + 0];
                byte by5 = this.h[n3 * 3 + 1];
                byte by6 = this.h[n3 * 3 + 2];
                byte by7 = -1;
                byteBuffer.put(by6).put(by5).put(by).put(by7);
            }
        }
    }

    private void n(int n) throws IOException {
        if (this.F != n) {
            throw new IOException("Chunk has wrong size");
        }
    }

    private void c(ByteBuffer byteBuffer, byte[] byArray) {
        if (this.x != null) {
            int n = byArray.length;
            for (int i = 1; i < n; ++i) {
                int n2 = byArray[i] & 0xFF;
                byte by = this.h[n2 * 3 + 0];
                byte by2 = this.h[n2 * 3 + 1];
                byte by3 = this.h[n2 * 3 + 2];
                byte by4 = this.x[n2];
                byteBuffer.put(by4).put(by3).put(by2).put(by);
            }
        } else {
            int n = byArray.length;
            for (int i = 1; i < n; ++i) {
                int n3 = byArray[i] & 0xFF;
                byte by = this.h[n3 * 3 + 0];
                byte by5 = this.h[n3 * 3 + 1];
                byte by6 = this.h[n3 * 3 + 2];
                byte by7 = -1;
                byteBuffer.put(by7).put(by6).put(by5).put(by);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void i(ByteBuffer byteBuffer, int n, ImageParser$Format imageParser$Format) throws IOException {
        int n2 = byteBuffer.position();
        int n3 = (this.Z * this.A + 7) / 8 * this.k;
        byte[] byArray = new byte[n3 + 1];
        byte[] byArray2 = new byte[n3 + 1];
        byte[] byArray3 = this.A < 8 ? new byte[this.Z + 1] : null;
        Inflater inflater = new Inflater();
        try {
            for (int i = 0; i < this.V; ++i) {
                this.l(inflater, byArray, 0, byArray.length);
                this.D(byArray, byArray2);
                byteBuffer.position(n2 + i * n);
                block1 : switch (this.g) {
                    case 2: {
                        switch (imageParser$Format) {
                            case ABGR: {
                                this.R(byteBuffer, byArray);
                                break block1;
                            }
                            case RGBA: {
                                this.j(byteBuffer, byArray);
                                break block1;
                            }
                            case BGRA: {
                                this.z(byteBuffer, byArray);
                                break block1;
                            }
                            case RGB: {
                                this.f(byteBuffer, byArray);
                                break block1;
                            }
                        }
                        throw new UnsupportedOperationException("Unsupported format for this image");
                    }
                    case 6: {
                        switch (imageParser$Format) {
                            case ABGR: {
                                this.n(byteBuffer, byArray);
                                break block1;
                            }
                            case RGBA: {
                                this.f(byteBuffer, byArray);
                                break block1;
                            }
                            case WHITE: {
                                this.O(byteBuffer, byArray);
                                break block1;
                            }
                            case BGRA: {
                                this.e(byteBuffer, byArray);
                                break block1;
                            }
                            case RGB: {
                                this.L(byteBuffer, byArray);
                                break block1;
                            }
                        }
                        throw new UnsupportedOperationException("Unsupported format for this image");
                    }
                    case 0: {
                        switch (imageParser$Format) {
                            case LUMINANCE: 
                            case ALPHA: {
                                this.f(byteBuffer, byArray);
                                break block1;
                            }
                        }
                        throw new UnsupportedOperationException("Unsupported format for this image");
                    }
                    case 4: {
                        switch (imageParser$Format) {
                            case LUMINANCE_ALPHA: {
                                this.f(byteBuffer, byArray);
                                break block1;
                            }
                        }
                        throw new UnsupportedOperationException("Unsupported format for this image");
                    }
                    case 3: {
                        switch (this.A) {
                            case 8: {
                                byArray3 = byArray;
                                break;
                            }
                            case 4: {
                                this.C(byArray, byArray3);
                                break;
                            }
                            case 2: {
                                this.f(byArray, byArray3);
                                break;
                            }
                            case 1: {
                                this.q(byArray, byArray3);
                                break;
                            }
                            default: {
                                throw new UnsupportedOperationException("Unsupported bitdepth for this image");
                            }
                        }
                        switch (imageParser$Format) {
                            case ABGR: {
                                this.c(byteBuffer, byArray3);
                                break block1;
                            }
                            case RGBA: {
                                this.T(byteBuffer, byArray3);
                                break block1;
                            }
                            case BGRA: {
                                this.F(byteBuffer, byArray3);
                                break block1;
                            }
                        }
                        throw new UnsupportedOperationException("Unsupported format for this image");
                    }
                    default: {
                        throw new UnsupportedOperationException("Not yet implemented");
                    }
                }
                byte[] byArray4 = byArray;
                byArray = byArray2;
                byArray2 = byArray4;
            }
        }
        finally {
            inflater.end();
        }
    }

    private void O(ByteBuffer byteBuffer, byte[] byArray) {
        int n = byArray.length;
        for (int i = 1; i < n; i += 4) {
            byteBuffer.put((byte)-1).put((byte)-1).put((byte)-1).put(byArray[i + 3]);
        }
    }

    private void G() throws IOException {
        this.n(13);
        this.X(this.P, 0, 13);
        this.Z = this.h(this.P, 0);
        this.V = this.h(this.P, 4);
        this.A = this.P[8] & 0xFF;
        this.g = this.P[9] & 0xFF;
        block0 : switch (this.g) {
            case 0: {
                if (this.A != 8) {
                    throw new IOException("Unsupported bit depth: " + this.A);
                }
                this.k = 1;
                break;
            }
            case 4: {
                if (this.A != 8) {
                    throw new IOException("Unsupported bit depth: " + this.A);
                }
                this.k = 2;
                break;
            }
            case 2: {
                if (this.A != 8) {
                    throw new IOException("Unsupported bit depth: " + this.A);
                }
                this.k = 3;
                break;
            }
            case 6: {
                if (this.A != 8) {
                    throw new IOException("Unsupported bit depth: " + this.A);
                }
                this.k = 4;
                break;
            }
            case 3: {
                switch (this.A) {
                    case 1: 
                    case 2: 
                    case 4: 
                    case 8: {
                        this.k = 1;
                        break block0;
                    }
                }
                throw new IOException("Unsupported bit depth: " + this.A);
            }
            default: {
                throw new IOException("unsupported color format: " + this.g);
            }
        }
        if (this.P[10] != 0) {
            throw new IOException("unsupported compression method");
        }
        if (this.P[11] != 0) {
            throw new IOException("unsupported filtering method");
        }
        if (this.P[12] != 0) {
            throw new IOException("unsupported interlace method");
        }
    }

    private void T(ByteBuffer byteBuffer, byte[] byArray) {
        if (this.x != null) {
            int n = byArray.length;
            for (int i = 1; i < n; ++i) {
                int n2 = byArray[i] & 0xFF;
                byte by = this.h[n2 * 3 + 0];
                byte by2 = this.h[n2 * 3 + 1];
                byte by3 = this.h[n2 * 3 + 2];
                byte by4 = this.x[n2];
                byteBuffer.put(by).put(by2).put(by3).put(by4);
            }
        } else {
            int n = byArray.length;
            for (int i = 1; i < n; ++i) {
                int n3 = byArray[i] & 0xFF;
                byte by = this.h[n3 * 3 + 0];
                byte by5 = this.h[n3 * 3 + 1];
                byte by6 = this.h[n3 * 3 + 2];
                byte by7 = -1;
                byteBuffer.put(by).put(by5).put(by6).put(by7);
            }
        }
    }

    private void C(byte[] byArray, byte[] byArray2) {
        int n = byArray2.length;
        for (int i = 1; i < n; i += 2) {
            int n2 = byArray[1 + (i >> 1)] & 0xFF;
            switch (n - i) {
                default: {
                    byArray2[i + 1] = (byte)(n2 & 0xF);
                }
                case 1: 
            }
            byArray2[i] = (byte)(n2 >> 4);
        }
    }

    private void J(byte[] byArray, byte[] byArray2) {
        int n;
        int n2 = this.k;
        for (n = 1; n <= n2; ++n) {
            int n3 = n;
            byArray[n3] = (byte)(byArray[n3] + (byte)((byArray2[n] & 0xFF) >>> 1));
        }
        int n4 = byArray.length;
        while (n < n4) {
            int n5 = n;
            byArray[n5] = (byte)(byArray[n5] + (byte)((byArray2[n] & 0xFF) + (byArray[n - n2] & 0xFF) >>> 1));
            ++n;
        }
    }

    private void R(ByteBuffer byteBuffer, byte[] byArray) {
        if (this.v != null) {
            byte by = this.v[1];
            byte by2 = this.v[3];
            byte by3 = this.v[5];
            int n = byArray.length;
            for (int i = 1; i < n; i += 3) {
                byte by4 = byArray[i];
                byte by5 = byArray[i + 1];
                byte by6 = byArray[i + 2];
                byte by7 = -1;
                if (by4 == by && by5 == by2 && by6 == by3) {
                    by7 = 0;
                }
                byteBuffer.put(by7).put(by6).put(by5).put(by4);
            }
        } else {
            int n = byArray.length;
            for (int i = 1; i < n; i += 3) {
                byteBuffer.put((byte)-1).put(byArray[i + 2]).put(byArray[i + 1]).put(byArray[i]);
            }
        }
    }

    private void g(int n) throws IOException {
        this.Y();
        if (this.T != n) {
            throw new IOException("Expected chunk: " + Integer.toHexString(n));
        }
    }

    private void q(byte[] byArray, byte[] byArray2) {
        int n = byArray2.length;
        for (int i = 1; i < n; i += 8) {
            int n2 = byArray[1 + (i >> 3)] & 0xFF;
            switch (n - i) {
                default: {
                    byArray2[i + 7] = (byte)(n2 & 1);
                }
                case 7: {
                    byArray2[i + 6] = (byte)(n2 >> 1 & 1);
                }
                case 6: {
                    byArray2[i + 5] = (byte)(n2 >> 2 & 1);
                }
                case 5: {
                    byArray2[i + 4] = (byte)(n2 >> 3 & 1);
                }
                case 4: {
                    byArray2[i + 3] = (byte)(n2 >> 4 & 1);
                }
                case 3: {
                    byArray2[i + 2] = (byte)(n2 >> 5 & 1);
                }
                case 2: {
                    byArray2[i + 1] = (byte)(n2 >> 6 & 1);
                }
                case 1: 
            }
            byArray2[i] = (byte)(n2 >> 7);
        }
    }

    public boolean u() {
        return this.g == 6 || this.g == 2 || this.g == 3;
    }

    private void f(ByteBuffer byteBuffer, byte[] byArray) {
        byteBuffer.put(byArray, 1, byArray.length - 1);
    }

    private void z(ByteBuffer byteBuffer, byte[] byArray) {
        if (this.v != null) {
            byte by = this.v[1];
            byte by2 = this.v[3];
            byte by3 = this.v[5];
            int n = byArray.length;
            for (int i = 1; i < n; i += 3) {
                byte by4 = byArray[i];
                byte by5 = byArray[i + 1];
                byte by6 = byArray[i + 2];
                byte by7 = -1;
                if (by4 == by && by5 == by2 && by6 == by3) {
                    by7 = 0;
                }
                byteBuffer.put(by6).put(by5).put(by4).put(by7);
            }
        } else {
            int n = byArray.length;
            for (int i = 1; i < n; i += 3) {
                byteBuffer.put(byArray[i + 2]).put(byArray[i + 1]).put(byArray[i]).put((byte)-1);
            }
        }
    }

    private void g(byte[] byArray, int n, int n2) throws IOException {
        int n3;
        do {
            if ((n3 = this.n.read(byArray, n, n2)) < 0) {
                throw new EOFException();
            }
            n += n3;
        } while ((n2 -= n3) > 0);
    }

    private void e(ByteBuffer byteBuffer, byte[] byArray) {
        int n = byArray.length;
        for (int i = 1; i < n; i += 4) {
            byteBuffer.put(byArray[i + 2]).put(byArray[i + 1]).put(byArray[i]).put(byArray[i + 3]);
        }
    }

    private void T() throws IOException {
        if (this.u > 0) {
            this.C(this.u + 4);
        } else {
            this.g(this.P, 0, 4);
        }
        this.u = 0;
        this.F = 0;
        this.T = 0;
    }

    private int h(byte[] byArray, int n) {
        return byArray[n] << 24 | (byArray[n + 1] & 0xFF) << 16 | (byArray[n + 2] & 0xFF) << 8 | byArray[n + 3] & 0xFF;
    }

    private void f(byte[] byArray, byte[] byArray2) {
        int n = byArray2.length;
        for (int i = 1; i < n; i += 4) {
            int n2 = byArray[1 + (i >> 2)] & 0xFF;
            switch (n - i) {
                default: {
                    byArray2[i + 3] = (byte)(n2 & 3);
                }
                case 3: {
                    byArray2[i + 2] = (byte)(n2 >> 2 & 3);
                }
                case 2: {
                    byArray2[i + 1] = (byte)(n2 >> 4 & 3);
                }
                case 1: 
            }
            byArray2[i] = (byte)(n2 >> 6);
        }
    }

    private void Y() throws IOException {
        this.g(this.P, 0, 8);
        this.F = this.h(this.P, 0);
        this.T = this.h(this.P, 4);
        this.u = this.F;
    }

    public void L(byte by, byte by2, byte by3) {
        if (this.X()) {
            throw new UnsupportedOperationException("image has an alpha channel");
        }
        byte[] byArray = this.h;
        if (byArray == null) {
            this.v = new byte[]{0, by, 0, by2, 0, by3};
        } else {
            this.x = new byte[byArray.length / 3];
            int n = 0;
            int n2 = 0;
            while (n < byArray.length) {
                if (byArray[n] != by || byArray[n + 1] != by2 || byArray[n + 2] != by3) {
                    this.x[n2] = -1;
                }
                n += 3;
                ++n2;
            }
        }
    }

    private void j(ByteBuffer byteBuffer, byte[] byArray) {
        if (this.v != null) {
            byte by = this.v[1];
            byte by2 = this.v[3];
            byte by3 = this.v[5];
            int n = byArray.length;
            for (int i = 1; i < n; i += 3) {
                byte by4 = byArray[i];
                byte by5 = byArray[i + 1];
                byte by6 = byArray[i + 2];
                byte by7 = -1;
                if (by4 == by && by5 == by2 && by6 == by3) {
                    by7 = 0;
                }
                byteBuffer.put(by4).put(by5).put(by6).put(by7);
            }
        } else {
            int n = byArray.length;
            for (int i = 1; i < n; i += 3) {
                byteBuffer.put(byArray[i]).put(byArray[i + 1]).put(byArray[i + 2]).put((byte)-1);
            }
        }
    }

    private void t() throws IOException {
        int n = this.F / 3;
        if (n < 1 || n > 256 || this.F % 3 != 0) {
            throw new IOException("PLTE chunk has wrong length");
        }
        this.h = new byte[n * 3];
        this.X(this.h, 0, this.h.length);
    }

    private void U() throws IOException {
        switch (this.g) {
            case 0: {
                this.n(2);
                this.v = new byte[2];
                this.X(this.v, 0, 2);
                break;
            }
            case 2: {
                this.n(6);
                this.v = new byte[6];
                this.X(this.v, 0, 6);
                break;
            }
            case 3: {
                if (this.h == null) {
                    throw new IOException("tRNS chunk without PLTE chunk");
                }
                this.x = new byte[this.h.length / 3];
                Arrays.fill(this.x, (byte)-1);
                this.X(this.x, 0, this.x.length);
            }
        }
    }

    private void O(byte[] byArray, byte[] byArray2) {
        int n;
        int n2 = this.k;
        for (n = 1; n <= n2; ++n) {
            int n3 = n;
            byArray[n3] = (byte)(byArray[n3] + byArray2[n]);
        }
        int n4 = byArray.length;
        while (n < n4) {
            int n5;
            int n6;
            int n7 = byArray[n - n2] & 0xFF;
            int n8 = byArray2[n] & 0xFF;
            int n9 = byArray2[n - n2] & 0xFF;
            int n10 = n7 + n8 - n9;
            int n11 = n10 - n7;
            if (n11 < 0) {
                n11 = -n11;
            }
            if ((n6 = n10 - n8) < 0) {
                n6 = -n6;
            }
            if ((n5 = n10 - n9) < 0) {
                n5 = -n5;
            }
            if (n11 <= n6 && n11 <= n5) {
                n9 = n7;
            } else if (n6 <= n5) {
                n9 = n8;
            }
            int n12 = n++;
            byArray[n12] = (byte)(byArray[n12] + (byte)n9);
        }
    }
}

