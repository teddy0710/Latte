package com.example.latte.app;

import java.util.IdentityHashMap;
import java.util.Objects;
import java.util.WeakHashMap;

/**
 * Created by 张枫霖 on 2017/8/25
 */

public class Configurator {
    private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();

    public Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    /**
     * 线程安全的懒汉模式Part 1 （get方法）
     */
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final WeakHashMap<String,Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }
    /**
     * 线程安全的懒汉模式Part 2 （静态内部类）
     */
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }
}
