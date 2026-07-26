package gg.vape.value;

import gg.vape.unmap.ItemLimitData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemLimitDataGroup {
    private boolean Y = false;
    private final List<ItemLimitData> U;

    public boolean O() {
        return this.Y;
    }

    public void s(boolean bl) {
        this.Y = bl;
    }

    public ItemLimitDataGroup(ItemLimitData ... itemLimitDataArray) {
        this.Y = itemLimitDataArray.length > 4;
        this.U = new ArrayList<ItemLimitData>(Arrays.asList(itemLimitDataArray));
    }

    public ItemLimitDataGroup(List<ItemLimitData> list) {
        this.Y = list.size() > 4;
        this.U = list;
    }
}

