package gg.vape.runtime.model;

import gg.vape.runtime.model.DetachedStringTreeEntry;
import gg.vape.runtime.model.DetachedStringTreeNode;
import java.util.List;

class DetachedStringTreeListEntry
extends DetachedStringTreeEntry {
    final DetachedStringTreeNode B;
    List<String> L;

    DetachedStringTreeListEntry(DetachedStringTreeNode detachedStringTreeNode) {
        super(detachedStringTreeNode);
        this.B = detachedStringTreeNode;
    }
}
