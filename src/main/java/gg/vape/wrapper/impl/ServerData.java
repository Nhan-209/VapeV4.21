package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MServerData;
import gg.vape.wrapper.Wrapper;

public class ServerData
extends Wrapper {
    public ServerData(Object object) {
        super(object);
    }

    public String f() {
        return MServerData.T(ServerData.c.getMappingsMapperCompat().ht, this.I);
    }
}

