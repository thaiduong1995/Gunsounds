package com.cem.admodule.manager;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 (2\u00020\u0001:\u0001(B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0013\u001a\u0004\u0018\u00010\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0002J\u000e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0007J*\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u00072\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u001dH\u0007J\"\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u00072\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001fJ2\u0010 \u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u00072\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00150\"2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001fH\u0002J\"\u0010#\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u00072\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010$J,\u0010%\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00102\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010$H\u0002J\"\u0010\'\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u00072\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010$R\u001c\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006)"}, d2 = {"Lcom/cem/admodule/manager/CemRewardAdManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "adsListRewardAd", "", "", "Lcom/cem/admodule/inter/CemRewardAd;", "configManager", "Lcom/cem/admodule/manager/ConfigManager;", "getConfigManager", "()Lcom/cem/admodule/manager/ConfigManager;", "configManager$delegate", "Lkotlin/Lazy;", "<set-?>", "", "isShowingAd", "()Z", "createRewardManager", "adUnitItem", "Lcom/cem/admodule/data/AdUnitItem;", "isRewardLoaded", "configKey", "load", "", "activity", "Landroid/app/Activity;", "callback", "Lkotlin/Function0;", "loadAds", "Lcom/cem/admodule/inter/RewardLoadCallback;", "loadAdsReward", "units", "", "showAndReload", "Lcom/cem/admodule/inter/CemRewardListener;", "showAsync", "reload", "showNotReload", "Companion", "adModule_release"})
public final class CemRewardAdManager {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy configManager$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.Map<java.lang.String, com.cem.admodule.inter.CemRewardAd> adsListRewardAd = null;
    private boolean isShowingAd = false;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "CemRewardAdManager";
    @android.annotation.SuppressLint(value = {"StaticFieldLeak"})
    @org.jetbrains.annotations.Nullable
    private static com.cem.admodule.manager.CemRewardAdManager mInstance;
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.manager.CemRewardAdManager.Companion Companion = null;
    
    @javax.inject.Inject
    public CemRewardAdManager(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    private final com.cem.admodule.manager.ConfigManager getConfigManager() {
        return null;
    }
    
    public final boolean isShowingAd() {
        return false;
    }
    
    private final com.cem.admodule.inter.CemRewardAd createRewardManager(com.cem.admodule.data.AdUnitItem adUnitItem) {
        return null;
    }
    
    private final void loadAdsReward(android.app.Activity activity, java.lang.String configKey, java.util.List<com.cem.admodule.data.AdUnitItem> units, com.cem.admodule.inter.RewardLoadCallback callback) {
    }
    
    public final void loadAds(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.NotNull
    java.lang.String configKey, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.RewardLoadCallback callback) {
    }
    
    @kotlin.jvm.JvmOverloads
    public final void load(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.NotNull
    java.lang.String configKey, @org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function0<kotlin.Unit> callback) {
    }
    
    private final void showAsync(android.app.Activity activity, java.lang.String configKey, boolean reload, com.cem.admodule.inter.CemRewardListener callback) {
    }
    
    public final void showNotReload(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.NotNull
    java.lang.String configKey, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.CemRewardListener callback) {
    }
    
    public final void showAndReload(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.NotNull
    java.lang.String configKey, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.CemRewardListener callback) {
    }
    
    public final boolean isRewardLoaded(@org.jetbrains.annotations.NotNull
    java.lang.String configKey) {
        return false;
    }
    
    @kotlin.jvm.JvmOverloads
    public final void load(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.NotNull
    java.lang.String configKey) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0083\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/cem/admodule/manager/CemRewardAdManager$Companion;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "mInstance", "Lcom/cem/admodule/manager/CemRewardAdManager;", "getInstance", "activity", "Landroid/content/Context;", "adModule_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getTAG() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.cem.admodule.manager.CemRewardAdManager getInstance(@org.jetbrains.annotations.NotNull
        android.content.Context activity) {
            return null;
        }
    }
}