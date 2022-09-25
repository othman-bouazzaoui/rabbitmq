package com.oth.rabbitmq.config;

import java.util.Arrays;
import java.util.List;

public class Util {

    public static final List<String> QUEUES = Arrays.asList("QUEUE1", "QUEUE2","QUEUE3", "QUEUE4", "QUEUE5");
    public static final List<String> EXCHANEGES = Arrays.asList("TOPIC","DELAYED_MESSAGE");
    public static final List<String> ROUTING_KEYS = Arrays.asList("key1", "key2");
}
