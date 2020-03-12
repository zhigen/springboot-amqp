package com.zglu.amqp;

/**
 * @author zglu
 */
public class QueueConstant {

    private QueueConstant() {
    }

    public static final String DLXQUEUE = "dlxQueue";

    public static final String DLXEXCHANGE = "dlxExchange";

    public static final String DIRECTQUEUE = "directQueue";

    public static final String FANOUTQUEUE0 = "fanoutQueue0";

    public static final String FANOUTQUEUE1 = "fanoutQueue1";

    public static final String FANOUTEXCHANGE = "fanoutExchange";

    public static final String TOPICQUEUE0 = "topicQueue0.*";

    public static final String TOPICQUEUE1 = "topicQueue1.*";

    public static final String TOPICEXCHANGE = "topicExchange";
}
