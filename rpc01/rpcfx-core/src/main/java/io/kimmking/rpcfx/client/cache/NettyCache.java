package io.kimmking.rpcfx.client.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ty
 *
 * 请求结果缓存
 */
public class NettyCache {

    private static final Map<String, String> cache = new ConcurrentHashMap<>(16);

    public static void put(final String key, final String val) {
        cache.put(key, val);
    }

    public static String get(final String key) {
        return cache.getOrDefault(key, "");
    }


}
