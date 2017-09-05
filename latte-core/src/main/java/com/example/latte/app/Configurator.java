package com.example.latte.app;

import android.app.Activity;
import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by 张枫霖 on 2017/8/25
 */

public class Configurator {
    //创建一个WeakHashMap用于保存程序配置信息
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    private static final Handler HANDLER = new Handler();
    /**
     * @return 返回用于保存程序信息的WeakHashMap
     */
    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    /**
     * 无参的构造方法
     */
    public Configurator() {
        //初始化时将CONFIG_READY置为 FALSE
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        LATTE_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    /**
     * 配置完成时调用，将CONFIG_READY置为 TRUE
     */
    public final void configure() {
        initIcons();
        Logger.addLogAdapter(new AndroidLogAdapter());
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);
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
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    /**
     * 向该配置文件 LATTE_CONFIGS 中添加 LATTE_CONFIGS
     */
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST.name(), host);
        return this;
    }

    public final Configurator withLoaderDelayed(long delayed) {
        LATTE_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptr(Interceptor interceptr) {
        INTERCEPTORS.add(interceptr);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptrs(ArrayList<Interceptor> interceptrs) {
        INTERCEPTORS.addAll(interceptrs);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withWechatAppId(String id) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, id);
        return this;
    }

    public final Configurator withWechatAppSecret(String secret) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, secret);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, activity);
        return this;
    }


    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + "IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }
}
