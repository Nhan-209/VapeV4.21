package gg.vape.utils.datas;

import gg.vape.ui.unmap.SearchBlock;
import gg.vape.utils.datas.SearchResultData;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

public class SearchResultDataPool {
    public static final Stack<SearchResultData> L = new Stack();

    public static SearchResultData n(int n, int n2, int n3, int n4, SearchBlock searchBlock, AtomicBoolean atomicBoolean, int n5) {
        if (!L.isEmpty()) {
            SearchResultData eO = L.pop();
            eO.t(n, n2, n3, n4, searchBlock, atomicBoolean, n5);
            return eO;
        }
        return new SearchResultData(n, n2, n3, n4, searchBlock, atomicBoolean, n5);
    }

    public static void h(SearchResultData eO) {
        L.push(eO);
    }
}

