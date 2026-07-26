package gg.vape.protocol;

import gg.vape.protocol.ZeusAuthenticatedProtocolState;
import gg.vape.protocol.ZeusPacketDirection;
import gg.vape.protocol.ZeusUnauthenticatedProtocolState;
import gg.vape.protocol.packet.HeartbeatPacket;
import gg.vape.protocol.packet.HeartbeatResponsePacket;
import gg.vape.protocol.packet.ServerDisconnectPacket;
import gg.vape.protocol.packet.ZeusSerializablePacket;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public enum ZeusProtocolState {
    UNAUTHENTICATED(ZeusUnauthenticatedProtocolState::register),
    AUTHENTICATED(ZeusAuthenticatedProtocolState::register);

    private final Map<ZeusPacketDirection, List<Class<? extends ZeusSerializablePacket>>> K = new HashMap<ZeusPacketDirection, List<Class<? extends ZeusSerializablePacket>>>();

    ZeusProtocolState(Consumer<ZeusProtocolState> registerStatePackets) {
        for (ZeusPacketDirection zeusPacketDirection : ZeusPacketDirection.values()) {
            this.K.put(zeusPacketDirection, new ArrayList<Class<? extends ZeusSerializablePacket>>());
        }
        this.p(ZeusPacketDirection.CLIENT, HeartbeatResponsePacket.class);
        this.p(ZeusPacketDirection.SERVER, HeartbeatPacket.class);
        this.p(ZeusPacketDirection.CLIENT, ServerDisconnectPacket.class);
        registerStatePackets.accept(this);
    }

    protected void p(ZeusPacketDirection zeusPacketDirection, Class<? extends ZeusSerializablePacket> clazz) {
        List<Class<? extends ZeusSerializablePacket>> list = this.K.get((Object)zeusPacketDirection);
        list.add(clazz);
    }

    private static InstantiationException a(InstantiationException instantiationException) {
        return instantiationException;
    }

    public int Z(ZeusPacketDirection zeusPacketDirection, ZeusSerializablePacket zeusSerializablePacket) {
        List<Class<? extends ZeusSerializablePacket>> list = this.K.get((Object)zeusPacketDirection);
        return list.indexOf(zeusSerializablePacket.getClass());
    }

    public ZeusSerializablePacket V(ZeusPacketDirection zeusPacketDirection, int n) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        List<Class<? extends ZeusSerializablePacket>> list = this.K.get((Object)zeusPacketDirection);
        if (n < 0 || n >= list.size()) {
            throw new RuntimeException("Attempted to get packet by id " + n + " in direction " + (Object)((Object)zeusPacketDirection) + " but it's out of bounds.");
        }
        Class<? extends ZeusSerializablePacket> clazz = list.get(n);
        if (clazz == null) {
            System.out.println("Failed to find packet with id " + n);
            return null;
        }
        return clazz.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
    }
}
