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
    BedTargetRenderPosition position;
    HashMap<Integer, HashMap<BedPlateBlockStateKey, Integer>> layerCounts = new HashMap();
    HashMap<Integer, List<BedPlateBlockStateKey>> sortedLayers = new HashMap();

    public String toString() {
        return "BedData{position=" + this.position + ", layers=" + this.layerCounts + '}';
    }

    public void r() {
        for (int layer : this.layerCounts.keySet()) {
            ArrayList arrayList = new ArrayList();
            if (!this.layerCounts.containsKey(layer)) continue;
            Object[] objectArray = this.layerCounts.get(layer).entrySet().toArray();
            Arrays.sort(objectArray, new BedPlateBlockCountComparator(this));
            for (Object object : objectArray) {
                arrayList.add(((Map.Entry)object).getKey());
            }
            Collections.reverse(arrayList);
            this.sortedLayers.put(layer, arrayList);
        }
    }

    private void incrementBlock(int layer, int id, int meta) {
        BedPlateBlockStateKey bedPlateBlockStateKey;
        HashMap<BedPlateBlockStateKey, Integer> hashMap;
        if (!this.layerCounts.containsKey(layer)) {
            this.layerCounts.put(layer, new HashMap());
        }
        if (!(hashMap = this.layerCounts.get(layer)).containsKey(bedPlateBlockStateKey = new BedPlateBlockStateKey(id, meta, null))) {
            hashMap.put(bedPlateBlockStateKey, 0);
        }
        hashMap.merge(bedPlateBlockStateKey, 1, Integer::sum);
    }

    public void y() {
        this.layerCounts.clear();
    }

    public List<BedPlateBlockStateKey> c(int n) {
        return this.sortedLayers.getOrDefault(n, new ArrayList());
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public BedPlateCountState(BedTargetRenderPosition bedTargetRenderPosition) {
        this.position = bedTargetRenderPosition;
    }

    public int totalDistinctBlockCount() {
        int total = 0;
        for (int layer : this.layerCounts.keySet()) {
            total += this.layerCounts.get(layer).size();
        }
        return total;
    }

    public static void j(BedPlateCountState bedPlateCountState, int n, int n2, int n3) {
        bedPlateCountState.incrementBlock(n, n2, n3);
    }

    public int getBlockCount(int layer, BedPlateBlockStateKey bedPlateBlockStateKey) {
        return this.layerCounts.get(layer).get(bedPlateBlockStateKey);
    }

    public BedTargetRenderPosition l() {
        return this.position;
    }

    public int getLayerTotalCount(int layer) {
        HashMap<BedPlateBlockStateKey, Integer> hashMap = this.layerCounts.get(layer);
        int total = 0;
        for (BedPlateBlockStateKey bedPlateBlockStateKey : hashMap.keySet()) {
            total += hashMap.get(bedPlateBlockStateKey).intValue();
        }
        return total;
    }
}

