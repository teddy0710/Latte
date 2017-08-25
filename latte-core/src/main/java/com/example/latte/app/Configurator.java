package com.example.latte.app;

import java.util.WeakHashMap;

/**
 * Created by 张枫霖 on 2017/8/25
 */

public class Configurator {
    //创建一个WeakHashMap用于保存程序配置信息
    private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();

    /**
     * @return 返回用于保存程序信息的WeakHashMap
     */
    final WeakHashMap<String,Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    /**
     * 无参的构造方法
     */
    public Configurator() {
        //初始化时将CONFIG_READY置为 FALSE
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    /**
     * 配置完成时调用，将CONFIG_READY置为 TRUE
     */
    public final void configure() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    /**
     * 线程安全的懒汉模式Part 1 （get方法）
     */
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }


    /**
     * 线程安全的懒汉模式Part 2 （静态内部类）
     */
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    /**
     *向该配置文件 LATTE_CONFIGS 中添加 LATTE_CONFIGS
     */
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
