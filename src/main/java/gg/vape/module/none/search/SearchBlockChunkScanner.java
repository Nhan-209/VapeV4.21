package gg.vape.module.none.search;

import gg.vape.module.none.search.SearchBlockRenderEntry;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.unmap.SearchBlock;
import gg.vape.utils.MathUtil;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Chunk;
import gg.vape.wrapper.impl.ChunkSection;
import gg.vape.wrapper.impl.ClientChunkProvider;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.WorldClient;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Predicate;

public class SearchBlockChunkScanner {
    public static final Queue<SearchBlockRenderEntry> C = new LinkedList<SearchBlockRenderEntry>();

    public static SearchBlockRenderEntry F(int n, int n2, int n3, int n4, int n5) {
        SearchBlockRenderEntry searchBlockRenderEntry = C.poll();
        if (searchBlockRenderEntry == null) {
            searchBlockRenderEntry = new SearchBlockRenderEntry(n, n2, n3, n4, n5);
        } else {
            searchBlockRenderEntry.L(n, n2, n3, n4, n5);
        }
        return searchBlockRenderEntry;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static void q(SearchBlockRenderEntry searchBlockRenderEntry) {
        C.offer(searchBlockRenderEntry);
    }

    private static long e(int n, int n2, int n3) {
        return ((long)n & 0x1FFFFFL) << 43 | ((long)n3 & 0x1FFFFFL) << 22 | (long)n2 & 0xFFFL;
    }

    private static void h(char[] cArray, int n, int n2, int n3, SearchBlock[] searchBlockArray, ArrayList<SearchBlockRenderEntry> arrayList, Set<Long> set, boolean bl) {
        for (int i = 0; i < cArray.length; ++i) {
            int n4;
            char c = cArray[i];
            int n5 = c >> 4;
            if (n5 == 0) continue;
            int n6 = c & 0xF;
            boolean bl2 = false;
            for (n4 = 0; n4 < searchBlockArray.length; ++n4) {
                SearchBlock searchBlock = searchBlockArray[n4];
                if (searchBlock.M() == n5 && (searchBlock.i() == -1 || searchBlock.i() == n6)) {
                    bl2 = true;
                    break;
                }
                Predicate<Character> predicate = searchBlock.E();
                if (predicate == null || !predicate.test(Character.valueOf(c))) continue;
                bl2 = true;
                break;
            }
            if (!bl2) continue;
            n4 = i % 16;
            int n7 = i / 256 + n2;
            int n8 = i / 16 % 16;
            int n9 = (n << 4) + n4;
            int n10 = n7;
            int n11 = (n3 << 4) + n8;
            if (bl && !SearchBlockChunkScanner.h(n9, n10, n11, set)) continue;
            SearchBlockRenderEntry searchBlockRenderEntry = SearchBlockChunkScanner.F(n5, n6, n9, n10, n11);
            arrayList.add(searchBlockRenderEntry);
        }
    }

    private static boolean h(int n, int n2, int n3, Set<Long> set) {
        int[][] nArrayArray;
        for (int[] nArray : nArrayArray = new int[][]{{0, 1, 0}, {0, -1, 0}, {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}}) {
            int n4 = n + nArray[0];
            int n5 = n2 + nArray[1];
            int n6 = n3 + nArray[2];
            long l = SearchBlockChunkScanner.e(n4, n5, n6);
            if (!set.contains(l)) continue;
            return true;
        }
        return false;
    }

    public static ArrayList<SearchBlockRenderEntry> f(List<SearchBlock> list, int n, boolean bl) {
        int n2;
        int n3;
        ArrayList<SearchBlockRenderEntry> arrayList = new ArrayList<SearchBlockRenderEntry>();
        HashSet<Long> hashSet = new HashSet<Long>();
        WorldClient worldClient = Minecraft.theWorld();
        ClientChunkProvider clientChunkProvider = worldClient.U();
        List<Chunk> list2 = clientChunkProvider.L();
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        double d = entityPlayerSP.z();
        double d2 = entityPlayerSP.N();
        double d3 = entityPlayerSP.h();
        if (bl) {
            for (Chunk object3 : list2) {
                List<ChunkSection> list3 = object3.U();
                for (Object object : list3) {
                    if (object == null || ((Wrapper)object).isNull() || ((ChunkSection)object).C() == null) continue;
                    int n4 = ((ChunkSection)object).l();
                    char[] cArray = ((ChunkSection)object).C();
                    int n5 = object3.a();
                    n2 = (int)MathUtil.Z(d, 0.0, d3, n5 << 4, 0.0, (n3 = object3.j()) << 4);
                    if (n2 > n) continue;
                    SearchBlockChunkScanner.s(cArray, n5, n4, n3, hashSet);
                }
            }
        }
        SearchBlock[] searchBlockArray = list.toArray(new SearchBlock[0]);
        for (Chunk chunk : list2) {
            List<ChunkSection> list4 = chunk.U();
            for (ChunkSection chunkSection : list4) {
                if (chunkSection == null || chunkSection.isNull() || chunkSection.C() == null) continue;
                int n6 = chunkSection.l();
                char[] cArray = chunkSection.C();
                n3 = chunk.a();
                int n7 = (int)MathUtil.Z(d, 0.0, d3, (n3 << 4) + 8, 0.0, ((n2 = chunk.j()) << 4) + 8);
                if (n7 > n) continue;
                SearchBlockChunkScanner.h(cArray, n3, n6, n2, searchBlockArray, arrayList, hashSet, bl);
            }
        }
        return arrayList;
    }

    private static int[] j(long l) {
        int n = (int)(l >> 43 & 0x1FFFFFL);
        int n2 = (int)(l >> 22 & 0x1FFFFFL);
        int n3 = (int)(l & 0xFFFL);
        return new int[]{n, n3, n2};
    }

    private static void s(char[] cArray, int n, int n2, int n3, Set<Long> set) {
        for (int i = 0; i < cArray.length; ++i) {
            char c = cArray[i];
            int n4 = c >> 4;
            if (n4 != 0 && n4 != 8 && n4 != 9 && n4 != 30) continue;
            int n5 = i % 16;
            int n6 = i / 256 + n2;
            int n7 = i / 16 % 16;
            int n8 = (n << 4) + n5;
            int n9 = n6;
            int n10 = (n3 << 4) + n7;
            long l = SearchBlockChunkScanner.e(n8, n9, n10);
            set.add(l);
        }
    }
}
