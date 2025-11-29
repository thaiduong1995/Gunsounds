package com.cem.admodule.manager;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 +2\u00020\u0001:\u0001+B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\bJ\u0014\u0010\u0015\u001a\u0004\u0018\u00010\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002J \u0010\u0018\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001bJ \u0010\u001c\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u0007J.\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00072\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00170 2\u0006\u0010!\u001a\u00020\"H\u0002J\u001e\u0010#\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0013\u001a\u00020\u0007J.\u0010$\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00072\u0016\b\u0002\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u0012\u0018\u00010&J(\u0010$\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00072\u0010\b\u0002\u0010%\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\'J6\u0010(\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00072\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00170 2\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\'H\u0002J\u001e\u0010)\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\"J\u0016\u0010*\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\"R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e\u00a8\u0006,"}, d2 = {"Lcom/cem/admodule/manager/CemNativeManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "adsDataNative", "", "", "Lcom/cem/admodule/inter/CemNativeAdView;", "adsListNative", "", "configManager", "Lcom/cem/admodule/manager/ConfigManager;", "getConfigManager", "()Lcom/cem/admodule/manager/ConfigManager;", "configManager$delegate", "Lkotlin/Lazy;", "addNativeWithList", "", "configKey", "view", "createNative", "adUnitItem", "Lcom/cem/admodule/data/AdUnitItem;", "getNative", "activity", "reload", "", "getNativeByList", "isNativeLoaded", "loadAndShow", "units", "", "nativeAdView", "Lcom/cem/admodule/manager/CustomNativeView;", "loadAndShowNative", "loadNative", "callback", "Lkotlin/Function1;", "Lcom/cem/admodule/inter/Callback;", "loadNativeInternal", "showNative", "showNativeByList", "Companion", "adModule_release"})
public final class CemNativeManager {
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy configManager$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.Map<java.lang.String, com.cem.admodule.inter.CemNativeAdView> adsDataNative = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.Map<java.lang.String, java.util.List<com.cem.admodule.inter.CemNativeAdView>> adsListNative = null;
    private static final java.lang.String TAG = null;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ads_native = "ads_native_show_";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ads_loaded = "ads_native_loaded_";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ads_loaded_failed = "ads_load_failed_";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ads_show_failed = "ads_show_failed_";
    @org.jetbrains.annotations.Nullable
    private static com.cem.admodule.manager.CemNativeManager mInstance;
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.manager.CemNativeManager.Companion Companion = null;
    
    private CemNativeManager(android.content.Context context) {
        super();
    }
    
    private final com.cem.admodule.manager.ConfigManager getConfigManager() {
        return null;
    }
    
    private final com.cem.admodule.inter.CemNativeAdView createNative(com.cem.admodule.data.AdUnitItem adUnitItem) {
        return null;
    }
    
    private final void loadAndShow(android.content.Context context, java.lang.String configKey, java.util.List<com.cem.admodule.data.AdUnitItem> units, com.cem.admodule.manager.CustomNativeView nativeAdView) {
    }
    
    public final void loadAndShowNative(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.cem.admodule.manager.CustomNativeView nativeAdView, @org.jetbrains.annotations.NotNull
    java.lang.String configKey) {
    }
    
    private final void loadNativeInternal(android.content.Context context, java.lang.String configKey, java.util.List<com.cem.admodule.data.AdUnitItem> units, com.cem.admodule.inter.Callback<com.cem.admodule.inter.CemNativeAdView> callback) {
    }
    
    public final void loadNative(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String configKey, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.Callback<com.cem.admodule.inter.CemNativeAdView> callback) {
    }
    
    public final void showNative(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String configKey, @org.jetbrains.annotations.NotNull
    com.cem.admodule.manager.CustomNativeView view) {
    }
    
    public final void showNativeByList(@org.jetbrains.annotations.NotNull
    java.lang.String configKey, @org.jetbrains.annotations.NotNull
    com.cem.admodule.manager.CustomNativeView view) {
    }
    
    public final void loadNative(@org.jetbrains.annotations.NotNull
    android.content.Context activity, @org.jetbrains.annotations.NotNull
    java.lang.String configKey, @org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> callback) {
    }
    
    public final boolean isNativeLoaded(@org.jetbrains.annotations.NotNull
    java.lang.String configKey) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.cem.admodule.inter.CemNativeAdView getNative(@org.jetbrains.annotations.NotNull
    android.content.Context activity, @org.jetbrains.annotations.NotNull
    java.lang.String configKey, boolean reload) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.cem.admodule.inter.CemNativeAdView getNativeByList(@org.jetbrains.annotations.NotNull
    android.content.Context activity, @org.jetbrains.annotations.NotNull
    java.lang.String configKey, boolean reload) {
        return null;
    }
    
    public final void addNativeWithList(@org.jetbrains.annotations.NotNull
    java.lang.String configKey, @org.jetbrains.annotations.NotNull
    com.cem.admodule.inter.CemNativeAdView view) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/cem/admodule/manager/CemNativeManager$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "ads_loaded", "ads_loaded_failed", "ads_native", "ads_show_failed", "mInstance", "Lcom/cem/admodule/manager/CemNativeManager;", "getInstance", "context", "Landroid/content/Context;", "adModule_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final java.lang.String getTAG() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.cem.admodule.manager.CemNativeManager getInstance(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
    }
}