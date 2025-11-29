package com.cem.admodule.manager;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u0000 12\u00020\u0001:\u00011B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J)\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J\n\u0010\"\u001a\u0004\u0018\u00010\u001fH\u0002J\b\u0010#\u001a\u0004\u0018\u00010\u001fJ\u0006\u0010$\u001a\u00020%J\u0006\u0010&\u001a\u00020\bJ\u0006\u0010\'\u001a\u00020\bJ\u0010\u0010(\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020\u001fH\u0002J\u0010\u0010*\u001a\u00020%2\u0006\u0010\n\u001a\u00020\u001fH\u0002J\u000e\u0010+\u001a\u00020%2\u0006\u0010\n\u001a\u00020\u001fJ\u000e\u0010,\u001a\u00020%2\u0006\u0010-\u001a\u00020\bJ\r\u0010.\u001a\u0004\u0018\u00010/\u00a2\u0006\u0002\u00100R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R(\u0010\u000b\u001a\u0004\u0018\u00010\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010\u001a\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00062"}, d2 = {"Lcom/cem/admodule/manager/ConfigManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_adManagement", "Lcom/cem/admodule/data/AdManager;", "_isEnableIntroAds", "", "_isEnableRewardAds", "value", "adManagement", "getAdManagement", "()Lcom/cem/admodule/data/AdManager;", "setAdManagement", "(Lcom/cem/admodule/data/AdManager;)V", "dataStore", "Lcom/tencent/mmkv/MMKV;", "getDataStore", "()Lcom/tencent/mmkv/MMKV;", "setDataStore", "(Lcom/tencent/mmkv/MMKV;)V", "isEnableIntroAds", "()Z", "setEnableIntroAds", "(Z)V", "isEnableRewardAds", "setEnableRewardAds", "fetchConfig", "Lkotlinx/coroutines/flow/Flow;", "remoteKey", "", "fileLocal", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCacheConfig", "getCountry", "initMMKV", "", "isEnable", "isVip", "loadData", "fileName", "setCacheConfig", "setCountry", "setIsVip", "vip", "timeReloadNative", "", "()Ljava/lang/Long;", "Companion", "adModule_release"})
public final class ConfigManager {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.Nullable
    private com.cem.admodule.data.AdManager _adManagement;
    @org.jetbrains.annotations.Nullable
    private com.tencent.mmkv.MMKV dataStore;
    private boolean _isEnableRewardAds = false;
    private boolean _isEnableIntroAds = false;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_IS_VIP = "KEY_IS_VIP";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CACHE_CONFIG = "CACHE_CONFIG";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String COUNTRY_CODE = "country_code";
    @android.annotation.SuppressLint(value = {"StaticFieldLeak"})
    @org.jetbrains.annotations.Nullable
    private static com.cem.admodule.manager.ConfigManager mInstance;
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.manager.ConfigManager.Companion Companion = null;
    
    private ConfigManager(android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.tencent.mmkv.MMKV getDataStore() {
        return null;
    }
    
    public final void setDataStore(@org.jetbrains.annotations.Nullable
    com.tencent.mmkv.MMKV p0) {
    }
    
    public final void setAdManagement(@org.jetbrains.annotations.Nullable
    com.cem.admodule.data.AdManager value) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.cem.admodule.data.AdManager getAdManagement() {
        return null;
    }
    
    public final void setEnableRewardAds(boolean value) {
    }
    
    public final boolean isEnableRewardAds() {
        return false;
    }
    
    public final void setEnableIntroAds(boolean value) {
    }
    
    public final boolean isEnableIntroAds() {
        return false;
    }
    
    public final boolean isEnable() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long timeReloadNative() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object fetchConfig(@org.jetbrains.annotations.Nullable
    java.lang.String remoteKey, @org.jetbrains.annotations.NotNull
    java.lang.String fileLocal, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<java.lang.Boolean>> $completion) {
        return null;
    }
    
    private final java.lang.String loadData(java.lang.String fileName) {
        return null;
    }
    
    public final void initMMKV() {
    }
    
    public final void setIsVip(boolean vip) {
    }
    
    public final boolean isVip() {
        return false;
    }
    
    public final void setCountry(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCountry() {
        return null;
    }
    
    private final void setCacheConfig(java.lang.String value) {
    }
    
    private final java.lang.String getCacheConfig() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0083\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/cem/admodule/manager/ConfigManager$Companion;", "", "()V", "CACHE_CONFIG", "", "COUNTRY_CODE", "KEY_IS_VIP", "mInstance", "Lcom/cem/admodule/manager/ConfigManager;", "getInstance", "context", "Landroid/content/Context;", "adModule_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.cem.admodule.manager.ConfigManager getInstance(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
    }
}