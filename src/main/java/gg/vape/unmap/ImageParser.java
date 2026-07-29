package gg.vape.unmap;

import gg.vape.unmap.ImageParser$Format;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.zip.Inflater;

public class ImageParser {
    private int currentChunkType;
    private int bytesPerPixel;
    private final byte zeroByte = 0;
    private int colorType;
    private final int idatChunkType;
    private int currentChunkLength;
    private final int iendChunkType;
    private final byte indexedColorType = 3;
    private byte[] palette;
    private final byte truecolorType = 2;
    private int remainingChunkBytes;
    private final byte[] pngSignature = new byte[]{-119, 80, 78, 71, 13, 10, 26, 10};
    private final byte rgbaColorType = 4;
    private int imageHeight;
    private final int plteChunkType;
    private byte[] transparencyData;
    private byte[] paletteAlpha;
    private final byte[] ioBuffer;
    private final InputStream inputStream;
    private final int trnsChunkType;
    private int bitDepth;
    private final int ihdrChunkType;
    private final byte alphaColorType = 6;
    private int imageWidth;

    private static Exception propagateException(Exception exception) {
        return exception;
    }

    private void writeAbgrPixels(ByteBuffer byteBuffer, byte[] byArray) {
        int n = byArray.length;
        for (int i = 1; i < n; i += 4) {
            byteBuffer.put(byArray[i + 3]).put(byArray[i + 2]).put(byArray[i + 1]).put(byArray[i]);
        }
    }

    private void skipInputBytes(long l) throws IOException {
        while (l > 0L) {
            long l2 = this.inputStream.skip(l);
            if (l2 < 0L) {
                throw new EOFException();
            }
            l -= l2;
        }
    }

    private boolean hasPngSignature(byte[] byArray) {
        for (int i = 0; i < this.pngSignature.length; ++i) {
            if (byArray[i] == this.pngSignature[i]) continue;
            return false;
        }
        return true;
    }

    private void writeRgbPixels(ByteBuffer byteBuffer, byte[] byArray) {
        int n = byArray.length;
        for (int i = 1; i < n; i += 4) {
            byteBuffer.put(byArray[i]).put(byArray[i + 1]).put(byArray[i + 2]);
        }
    }

    public int getHeight() {
        return this.imageHeight;
    }

    public ImageParser$Format resolveOutputFormat(ImageParser$Format requestedFormat) {
        switch (this.colorType) {
            case 2: {
                switch (requestedFormat) {
                    case ABGR: 
                    case RGBA: 
                    case BGRA: 
                    case RGB: {
                        return requestedFormat;
                    }
                }
                return ImageParser$Format.RGB;
            }
            case 6: {
                switch (requestedFormat) {
                    case ABGR: 
                    case RGBA: 
                    case BGRA: 
                    case RGB: {
                        return requestedFormat;
                    }
                }
                return ImageParser$Format.RGBA;
            }
            case 0: {
                switch (requestedFormat) {
                    case LUMINANCE: 
                    case ALPHA: {
                        return requestedFormat;
                    }
                }
                return ImageParser$Format.LUMINANCE;
            }
            case 4: {
                return ImageParser$Format.LUMINANCE_ALPHA;
            }
            case 3: {
                switch (requestedFormat) {
                    case ABGR: 
                    case RGBA: 
                    case BGRA: {
                        return requestedFormat;
                    }
                }
                return ImageParser$Format.RGBA;
            }
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void unfilterScanline(byte[] byArray, byte[] byArray2) throws IOException {
        switch (byArray[0]) {
            case 0: {
                break;
            }
            case 1: {
                this.unfilterSub(byArray);
                break;
            }
            case 2: {
                this.unfilterUp(byArray, byArray2);
                break;
            }
            case 3: {
                this.unfilterAverage(byArray, byArray2);
                break;
            }
            case 4: {
                this.unfilterPaeth(byArray, byArray2);
                break;
            }
            default: {
                throw new IOException("invalide filter type in scanline: " + byArray[0]);
            }
        }
    }

    private void unfilterSub(byte[] byArray) {
        int n = this.bytesPerPixel;
        int n2 = byArray.length;
        for (int i = n + 1; i < n2; ++i) {
            int n3 = i;
            byArray[n3] = (byte)(byArray[n3] + byArray[i - n]);
        }
    }

    private int readChunkBytes(byte[] byArray, int n, int n2) throws IOException {
        if (n2 > this.remainingChunkBytes) {
            n2 = this.remainingChunkBytes;
        }
        this.readFully(byArray, n, n2);
        this.remainingChunkBytes -= n2;
        return n2;
    }

    private void refillInflaterInput(Inflater inflater) throws IOException {
        while (this.remainingChunkBytes == 0) {
            this.skipToNextChunk();
            this.expectChunk(1229209940);
        }
        int n = this.readChunkBytes(this.ioBuffer, 0, this.ioBuffer.length);
        inflater.setInput(this.ioBuffer, 0, n);
    }

    public void decodeFlipped(ByteBuffer byteBuffer, int stride, ImageParser$Format outputFormat) throws IOException {
        if (stride <= 0) {
            throw new IllegalArgumentException("stride");
        }
        int initialPosition = byteBuffer.position();
        int rowOffset = (this.imageHeight - 1) * stride;
        byteBuffer.position(initialPosition + rowOffset);
        this.decode(byteBuffer, -stride, outputFormat);
        byteBuffer.position(byteBuffer.position() + rowOffset);
    }

    private void unfilterUp(byte[] byArray, byte[] byArray2) {
        int n = this.bytesPerPixel;
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
        this.ihdrChunkType = 1229472850;
        this.plteChunkType = 1347179589;
        this.trnsChunkType = 1951551059;
        this.idatChunkType = 1229209940;
        this.iendChunkType = 1229278788;
        this.inputStream = inputStream;
        this.ioBuffer = new byte[4096];
        this.readFully(this.ioBuffer, 0, this.pngSignature.length);
        if (!this.hasPngSignature(this.ioBuffer)) {
            throw new IOException("Not a valid PNG file");
        }
        this.expectChunk(1229472850);
        this.readIhdrChunk();
        this.skipToNextChunk();
        block5: while (true) {
            this.readChunkHeader();
            switch (this.currentChunkType) {
                case 1229209940: {
                    break block5;
                }
                case 1347179589: {
                    this.readPaletteChunk();
                    break;
                }
                case 1951551059: {
                    this.readTransparencyChunk();
                }
            }
            this.skipToNextChunk();
        }
        if (this.colorType == 3 && this.palette == null) {
            throw new IOException("Missing PLTE chunk");
        }
    }

    public boolean hasTransparency() {
        return this.hasAlphaChannel() || this.paletteAlpha != null || this.transparencyData != null;
    }

    public boolean hasAlphaChannel() {
        return this.colorType == 6 || this.colorType == 4;
    }

    public int getWidth() {
        return this.imageWidth;
    }

    private void inflateFully(Inflater inflater, byte[] byArray, int n, int n2) throws IOException {
        try {
            do {
                int n3;
                if ((n3 = inflater.inflate(byArray, n, n2)) <= 0) {
                    if (inflater.finished()) {
                        throw new EOFException();
                    }
                    if (inflater.needsInput()) {
                        this.refillInflaterInput(inflater);
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

    private void writeBgraPalettePixels(ByteBuffer byteBuffer, byte[] byArray) {
        if (this.paletteAlpha != null) {
            int n = byArray.length;
            for (int i = 1; i < n; ++i) {
                int n2 = byArray[i] & 0xFF;
                byte by = this.palette[n2 * 3 + 0];
                byte by2 = this.palette[n2 * 3 + 1];
                byte by3 = this.palette[n2 * 3 + 2];
                byte by4 = this.paletteAlpha[n2];
                byteBuffer.put(by3).put(by2).put(by).put(by4);
            }
        } else {
            int n = byArray.length;
            for (int i = 1; i < n; ++i) {
                int n3 = byArray[i] & 0xFF;
                byte by = this.palette[n3 * 3 + 0];
                byte by5 = this.palette[n3 * 3 + 1];
                byte by6 = this.palette[n3 * 3 + 2];
                byte by7 = -1;
                byteBuffer.put(by6).put(by5).put(by).put(by7);
            }
        }
    }

    private void requireChunkLength(int n) throws IOException {
        if (this.currentChunkLength != n) {
            throw new IOException("Chunk has wrong size");
        }
    }

    private void writeAbgrPalettePixels(ByteBuffer byteBuffer, byte[] byArray) {
        if (this.paletteAlpha != null) {
            int n = byArray.length;
            for (int i = 1; i < n; ++i) {
                int n2 = byArray[i] & 0xFF;
                byte by = this.palette[n2 * 3 + 0];
                byte by2 = this.palette[n2 * 3 + 1];
                byte by3 = this.palette[n2 * 3 + 2];
                byte by4 = this.paletteAlpha[n2];
                byteBuffer.put(by4).put(by3).put(by2).put(by);
            }
        } else {
            int n = byArray.length;
            for (int i = 1; i < n; ++i) {
                int n3 = byArray[i] & 0xFF;
                byte by = this.palette[n3 * 3 + 0];
                byte by5 = this.palette[n3 * 3 + 1];
                byte by6 = this.palette[n3 * 3 + 2];
                byte by7 = -1;
                byteBuffer.put(by7).put(by6).put(by5).put(by);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void decode(ByteBuffer byteBuffer, int stride, ImageParser$Format outputFormat) throws IOException {
        int n2 = byteBuffer.position();
        int n3 = (this.imageWidth * this.bitDepth + 7) / 8 * this.bytesPerPixel;
        byte[] byArray = new byte[n3 + 1];
        byte[] byArray2 = new byte[n3 + 1];
        byte[] byArray3 = this.bitDepth < 8 ? new byte[this.imageWidth + 1] : null;
        Inflater inflater = new Inflater();
        try {
            for (int i = 0; i < this.imageHeight; ++i) {
                this.inflateFully(inflater, byArray, 0, byArray.length);
                this.unfilterScanline(byArray, byArray2);
                byteBuffer.position(n2 + i * stride);
                block1 : switch (this.colorType) {
                    case 2: {
                        switch (outputFormat) {
                            case ABGR: {
                                this.writeAbgrPixelsWithTransparency(byteBuffer, byArray);
                                break block1;
                            }
                            case RGBA: {
                                this.writeRgbaPixelsWithTransparency(byteBuffer, byArray);
                                break block1;
                            }
                            case BGRA: {
                                this.writeBgraPixelsWithTransparency(byteBuffer, byArray);
                                break block1;
                            }
                            case RGB: {
                                this.writeRawPixels(byteBuffer, byArray);
                                break block1;
                            }
                        }
                        throw new UnsupportedOperationException("Unsupported format for this image");
                    }
                    case 6: {
                        switch (outputFormat) {
                            case ABGR: {
                                this.writeAbgrPixels(byteBuffer, byArray);
                                break block1;
                            }
                            case RGBA: {
                                this.writeRawPixels(byteBuffer, byArray);
                                break block1;
                            }
                            case WHITE: {
                                this.writeWhitePixels(byteBuffer, byArray);
                                break block1;
                            }
                            case BGRA: {
                                this.writeBgraPixels(byteBuffer, byArray);
                                break block1;
                            }
                            case RGB: {
                                this.writeRgbPixels(byteBuffer, byArray);
                                break block1;
                            }
                        }
                        throw new UnsupportedOperationException("Unsupported format for this image");
                    }
                    case 0: {
                        switch (outputFormat) {
                            case LUMINANCE: 
                            case ALPHA: {
                                this.writeRawPixels(byteBuffer, byArray);
                                break block1;
                            }
                        }
                        throw new UnsupportedOperationException("Unsupported format for this image");
                    }
                    case 4: {
                        switch (outputFormat) {
                            case LUMINANCE_ALPHA: {
                                this.writeRawPixels(byteBuffer, byArray);
                                break block1;
                            }
                        }
                        throw new UnsupportedOperationException("Unsupported format for this image");
                    }
                    case 3: {
                        switch (this.bitDepth) {
                            case 8: {
                                byArray3 = byArray;
                                break;
                            }
                            case 4: {
                                this.unpack4BitSamples(byArray, byArray3);
                                break;
                            }
                            case 2: {
                                this.unpack2BitSamples(byArray, byArray3);
                                break;
                            }
                            case 1: {
                                this.unpack1BitSamples(byArray, byArray3);
                                break;
                            }
                            default: {
                                throw new UnsupportedOperationException("Unsupported bitdepth for this image");
                            }
                        }
                        switch (outputFormat) {
                            case ABGR: {
                                this.writeAbgrPalettePixels(byteBuffer, byArray3);
                                break block1;
                            }
                            case RGBA: {
                                this.writeRgbaPalettePixels(byteBuffer, byArray3);
                                break block1;
                            }
                            case BGRA: {
                                this.writeBgraPalettePixels(byteBuffer, byArray3);
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

    private void writeWhitePixels(ByteBuffer byteBuffer, byte[] byArray) {
        int n = byArray.length;
        for (int i = 1; i < n; i += 4) {
            byteBuffer.put((byte)-1).put((byte)-1).put((byte)-1).put(byArray[i + 3]);
        }
    }

    private void readIhdrChunk() throws IOException {
        this.requireChunkLength(13);
        this.readChunkBytes(this.ioBuffer, 0, 13);
        this.imageWidth = this.readInt32(this.ioBuffer, 0);
        this.imageHeight = this.readInt32(this.ioBuffer, 4);
        this.bitDepth = this.ioBuffer[8] & 0xFF;
        this.colorType = this.ioBuffer[9] & 0xFF;
        block0 : switch (this.colorType) {
            case 0: {
                if (this.bitDepth != 8) {
                    throw new IOException("Unsupported bit depth: " + this.bitDepth);
                }
                this.bytesPerPixel = 1;
                break;
            }
            case 4: {
                if (this.bitDepth != 8) {
                    throw new IOException("Unsupported bit depth: " + this.bitDepth);
                }
                this.bytesPerPixel = 2;
                break;
            }
            case 2: {
                if (this.bitDepth != 8) {
                    throw new IOException("Unsupported bit depth: " + this.bitDepth);
                }
                this.bytesPerPixel = 3;
                break;
            }
            case 6: {
                if (this.bitDepth != 8) {
                    throw new IOException("Unsupported bit depth: " + this.bitDepth);
                }
                this.bytesPerPixel = 4;
                break;
            }
            case 3: {
                switch (this.bitDepth) {
                    case 1: 
                    case 2: 
                    case 4: 
                    case 8: {
                        this.bytesPerPixel = 1;
                        break block0;
                    }
                }
                throw new IOException("Unsupported bit depth: " + this.bitDepth);
            }
            default: {
                throw new IOException("unsupported color format: " + this.colorType);
            }
        }
        if (this.ioBuffer[10] != 0) {
            throw new IOException("unsupported compression method");
        }
        if (this.ioBuffer[11] != 0) {
            throw new IOException("unsupported filtering method");
        }
        if (this.ioBuffer[12] != 0) {
            throw new IOException("unsupported interlace method");
        }
    }

    private void writeRgbaPalettePixels(ByteBuffer byteBuffer, byte[] byArray) {
        if (this.paletteAlpha != null) {
            int n = byArray.length;
            for (int i = 1; i < n; ++i) {
                int n2 = byArray[i] & 0xFF;
                byte by = this.palette[n2 * 3 + 0];
                byte by2 = this.palette[n2 * 3 + 1];
                byte by3 = this.palette[n2 * 3 + 2];
                byte by4 = this.paletteAlpha[n2];
                byteBuffer.put(by).put(by2).put(by3).put(by4);
            }
        } else {
            int n = byArray.length;
            for (int i = 1; i < n; ++i) {
                int n3 = byArray[i] & 0xFF;
                byte by = this.palette[n3 * 3 + 0];
                byte by5 = this.palette[n3 * 3 + 1];
                byte by6 = this.palette[n3 * 3 + 2];
                byte by7 = -1;
                byteBuffer.put(by).put(by5).put(by6).put(by7);
            }
        }
    }

    private void unpack4BitSamples(byte[] byArray, byte[] byArray2) {
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

    private void unfilterAverage(byte[] byArray, byte[] byArray2) {
        int n;
        int n2 = this.bytesPerPixel;
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

    private void writeAbgrPixelsWithTransparency(ByteBuffer byteBuffer, byte[] byArray) {
        if (this.transparencyData != null) {
            byte by = this.transparencyData[1];
            byte by2 = this.transparencyData[3];
            byte by3 = this.transparencyData[5];
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

    private void expectChunk(int n) throws IOException {
        this.readChunkHeader();
        if (this.currentChunkType != n) {
            throw new IOException("Expected chunk: " + Integer.toHexString(n));
        }
    }

    private void unpack1BitSamples(byte[] byArray, byte[] byArray2) {
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

    public boolean isColorTypeSupported() {
        return this.colorType == 6 || this.colorType == 2 || this.colorType == 3;
    }

    private void writeRawPixels(ByteBuffer byteBuffer, byte[] byArray) {
        byteBuffer.put(byArray, 1, byArray.length - 1);
    }

    private void writeBgraPixelsWithTransparency(ByteBuffer byteBuffer, byte[] byArray) {
        if (this.transparencyData != null) {
            byte by = this.transparencyData[1];
            byte by2 = this.transparencyData[3];
            byte by3 = this.transparencyData[5];
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

    private void readFully(byte[] byArray, int n, int n2) throws IOException {
        int n3;
        do {
            if ((n3 = this.inputStream.read(byArray, n, n2)) < 0) {
                throw new EOFException();
            }
            n += n3;
        } while ((n2 -= n3) > 0);
    }

    private void writeBgraPixels(ByteBuffer byteBuffer, byte[] byArray) {
        int n = byArray.length;
        for (int i = 1; i < n; i += 4) {
            byteBuffer.put(byArray[i + 2]).put(byArray[i + 1]).put(byArray[i]).put(byArray[i + 3]);
        }
    }

    private void skipToNextChunk() throws IOException {
        if (this.remainingChunkBytes > 0) {
            this.skipInputBytes(this.remainingChunkBytes + 4);
        } else {
            this.readFully(this.ioBuffer, 0, 4);
        }
        this.remainingChunkBytes = 0;
        this.currentChunkLength = 0;
        this.currentChunkType = 0;
    }

    private int readInt32(byte[] byArray, int n) {
        return byArray[n] << 24 | (byArray[n + 1] & 0xFF) << 16 | (byArray[n + 2] & 0xFF) << 8 | byArray[n + 3] & 0xFF;
    }

    private void unpack2BitSamples(byte[] byArray, byte[] byArray2) {
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

    private void readChunkHeader() throws IOException {
        this.readFully(this.ioBuffer, 0, 8);
        this.currentChunkLength = this.readInt32(this.ioBuffer, 0);
        this.currentChunkType = this.readInt32(this.ioBuffer, 4);
        this.remainingChunkBytes = this.currentChunkLength;
    }

    public void setTransparentColor(byte red, byte green, byte blue) {
        if (this.hasAlphaChannel()) {
            throw new UnsupportedOperationException("image has an alpha channel");
        }
        byte[] byArray = this.palette;
        if (byArray == null) {
            this.transparencyData = new byte[]{0, red, 0, green, 0, blue};
        } else {
            this.paletteAlpha = new byte[byArray.length / 3];
            int n = 0;
            int n2 = 0;
            while (n < byArray.length) {
                if (byArray[n] != red || byArray[n + 1] != green || byArray[n + 2] != blue) {
                    this.paletteAlpha[n2] = -1;
                }
                n += 3;
                ++n2;
            }
        }
    }

    private void writeRgbaPixelsWithTransparency(ByteBuffer byteBuffer, byte[] byArray) {
        if (this.transparencyData != null) {
            byte by = this.transparencyData[1];
            byte by2 = this.transparencyData[3];
            byte by3 = this.transparencyData[5];
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

    private void readPaletteChunk() throws IOException {
        int n = this.currentChunkLength / 3;
        if (n < 1 || n > 256 || this.currentChunkLength % 3 != 0) {
            throw new IOException("PLTE chunk has wrong length");
        }
        this.palette = new byte[n * 3];
        this.readChunkBytes(this.palette, 0, this.palette.length);
    }

    private void readTransparencyChunk() throws IOException {
        switch (this.colorType) {
            case 0: {
                this.requireChunkLength(2);
                this.transparencyData = new byte[2];
                this.readChunkBytes(this.transparencyData, 0, 2);
                break;
            }
            case 2: {
                this.requireChunkLength(6);
                this.transparencyData = new byte[6];
                this.readChunkBytes(this.transparencyData, 0, 6);
                break;
            }
            case 3: {
                if (this.palette == null) {
                    throw new IOException("tRNS chunk without PLTE chunk");
                }
                this.paletteAlpha = new byte[this.palette.length / 3];
                Arrays.fill(this.paletteAlpha, (byte)-1);
                this.readChunkBytes(this.paletteAlpha, 0, this.paletteAlpha.length);
            }
        }
    }

    private void unfilterPaeth(byte[] byArray, byte[] byArray2) {
        int n;
        int n2 = this.bytesPerPixel;
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
