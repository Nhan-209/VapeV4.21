package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MBlockPosCarrier
extends Mapping {
    private static int[] controlFlowState;
    private final MappingMethod getPosMethod;
    private static final String GET_POS_METHOD_NAME;

    public MBlockPosCarrier() {
        super(MappedClasses.uD);
        this.getPosMethod = this.Y(GET_POS_METHOD_NAME, true, MappedClasses.lf, new Class[]{});
    }

    static {
        MBlockPosCarrier.setBlockPosCarrierControlFlowState(new int[5]);
        GET_POS_METHOD_NAME = "getPos";
    }

    private Object invokeGetPos(Object carrier) {
        return this.getPosMethod.invokeObject(carrier, new Object[0]);
    }

    public static Object getPos(MBlockPosCarrier mapping, Object carrier) {
        return mapping.invokeGetPos(carrier);
    }

    public static int[] getBlockPosCarrierControlFlowState() {
        return controlFlowState;
    }

    public static void setBlockPosCarrierControlFlowState(int[] state) {
        controlFlowState = state;
    }
}

