package gg.vape.module.minigame.bedplates;

import gg.vape.module.minigame.bedplates.BedPlateBlockCountComparator;
import gg.vape.module.minigame.bedplates.BedPlateBlockStateKey;
import gg.vape.module.world.bedbreaker.BedTargetRenderPosition;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BedPlateCountState {
    BedTargetRenderPosition j;
    HashMap<Integer, HashMap<BedPlateBlockStateKey, Integer>> a = new HashMap();
    HashMap<Integer, List<BedPlateBlockStateKey>> b = new HashMap();

    public String toString() {
        return "BedData{position=" + this.j + ", layers=" + this.a + '}';
    }

    public void r() {
        for (int n : this.a.keySet()) {
            ArrayList arrayList = new ArrayList();
            if (!this.a.containsKey(n)) continue;
            Object[] objectArray = this.a.get(n).entrySet().toArray();
            Arrays.sort(objectArray, new BedPlateBlockCountComparator(this));
            for (Object object : objectArray) {
                arrayList.add(((Map.Entry)object).getKey());
            }
            Collections.reverse(arrayList);
            this.b.put(n, arrayList);
        }
    }

    private void W(int n, int n2, int n3) {
        BedPlateBlockStateKey bedPlateBlockStateKey;
        HashMap<BedPlateBlockStateKey, Integer> hashMap;
        if (!this.a.containsKey(n)) {
            this.a.put(n, new HashMap());
        }
        if (!(hashMap = this.a.get(n)).containsKey(bedPlateBlockStateKey = new BedPlateBlockStateKey(n2, n3, null))) {
            hashMap.put(bedPlateBlockStateKey, 0);
        }
        hashMap.merge(bedPlateBlockStateKey, 1, Integer::sum);
    }

    public void y() {
        this.a.clear();
    }

    public List<BedPlateBlockStateKey> c(int n) {
        return this.b.getOrDefault(n, new ArrayList());
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public BedPlateCountState(BedTargetRenderPosition bedTargetRenderPosition) {
        this.j = bedTargetRenderPosition;
    }

    public int n() {
        int n = 0;
        for (int n2 : this.a.keySet()) {
            n += this.a.get(n2).size();
        }
        return n;
    }

    public static void j(BedPlateCountState bedPlateCountState, int n, int n2, int n3) {
        bedPlateCountState.W(n, n2, n3);
    }

    public int H(int n, BedPlateBlockStateKey bedPlateBlockStateKey) {
        return this.a.get(n).get(bedPlateBlockStateKey);
    }

    public BedTargetRenderPosition l() {
        return this.j;
    }

    public int x(int n) {
        HashMap<BedPlateBlockStateKey, Integer> hashMap = this.a.get(n);
        int n2 = 0;
        for (BedPlateBlockStateKey bedPlateBlockStateKey : hashMap.keySet()) {
            n2 += hashMap.get(bedPlateBlockStateKey).intValue();
        }
        return n2;
    }
}

