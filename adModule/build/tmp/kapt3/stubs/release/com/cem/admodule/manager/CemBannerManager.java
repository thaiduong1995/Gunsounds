package com.cem.admodule.manager;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0005\u0018\u0000 %2\u00020\u0001:\u0001%B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0005H\u0002J\u0018\u0010\r\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u0005J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0002JB\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\f\u001a\u00020\u00052\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001bJB\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\f\u001a\u00020\u00052\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001bJR\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\f\u001a\u00020\u00052\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\n0!2\u0006\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0002JR\u0010\"\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u00052\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\n0!2\u0006\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0002J6\u0010#\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\f\u001a\u00020\u00052\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\u000e\u0010$\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/cem/admodule/manager/CemBannerManager;", "", "()V", "adsBannerManager", "", "", "Lcom/cem/admodule/data/BannerLoaded;", "createBanner", "Lcom/cem/admodule/inter/BannerAdView;", "adUnitItem", "Lcom/cem/admodule/data/AdUnitItem;", "getOrPutHandler", "configKey", "getPosition", "context", "Landroid/content/Context;", "isLoadedSuccess", "", "timeInterval", "", "loadAndShowBannerByContext", "", "viewGroup", "Landroid/view/ViewGroup;", "nameScreen", "position", "callback", "Lcom/cem/admodule/inter/BannerAdListener;", "loadBannerAndShowByActivity", "activity", "Landroid/app/Activity;", "loadBannerShowByActivity", "units", "", "loadBannerShowByContext", "loadBannerShowNoCollapsible", "removeBannerLoaded", "Companion", "adModule_release"})
public final class CemBannerManager {
    @org.jetbrains.annotations.NotNull
    private java.util.Map<java.lang.String, com.cem.admodule.data.BannerLoaded> adsBannerManager;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = null;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ADS_FULL = "ads_banner_";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ADS_FAILED = "ads_banner_failed_";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CLICK_ADS_FULL = "click_banner_";
    @org.jetbrains.annotations.Nullable
    private static com.cem.admodule.manager.CemBannerManager mInstance;
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.manager.CemBannerManager.Companion Companion = null;
    
    private CemBannerManager() {
        super();
    }
    
    private final com.cem.admodule.data.BannerLoaded getOrPutHandler(java.lang.String configKey) {
        return null;
    }
    
    private final boolean isLoadedSuccess(java.lang.String configKey, long timeInterval) {
        return false;
    }
    
    public final void removeBannerLoaded(@org.jetbrains.annotations.NotNull
    java.lang.String configKey) {
    }
    
    private final com.cem.admodule.inter.BannerAdView createBanner(com.cem.admodule.data.AdUnitItem adUnitItem) {
        return null;
    }
    
    private final void loadBannerShowByActivity(android.app.Activity activity, java.lang.String configKey, java.util.List<com.cem.admodule.data.AdUnitItem> units, android.view.ViewGroup viewGroup, java.lang.String nameScreen, java.lang.String position, com.cem.admodule.inter.BannerAdListener callback) {
    }
    
    private final void loadBannerShowByContext(android.content.Context context, java.lang.String configKey, java.util.List<com.cem.admodule.data.AdUnitItem> units, android.view.ViewGroup viewGroup, java.lang.String nameScreen, java.lang.String position, com.cem.admodule.inter.BannerAdListener callback) {
    }
    
    public final void loadBannerShowNoCollapsible(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    android.view.ViewGroup viewGroup, @org.jetbrains.annotations.NotNull
    java.lang.String configKey, @org.jetbrains.annotations.Nullable
    java.lang.String nameScreen, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.BannerAdListener callback) {
    }
    
    public final void loadAndShowBannerByContext(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    android.view.ViewGroup viewGroup, @org.jetbrains.annotations.NotNull
    java.lang.String configKey, @org.jetbrains.annotations.Nullable
    java.lang.String nameScreen, @org.jetbrains.annotations.Nullable
    java.lang.String position, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.BannerAdListener callback) {
    }
    
    public final void loadBannerAndShowByActivity(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.NotNull
    android.view.ViewGroup viewGroup, @org.jetbrains.annotations.NotNull
    java.lang.String configKey, @org.jetbrains.annotations.Nullable
    java.lang.String nameScreen, @org.jetbrains.annotations.Nullable
    java.lang.String position, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.BannerAdListener callback) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPosition(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String configKey) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/cem/admodule/manager/CemBannerManager$Companion;", "", "()V", "ADS_FAILED", "", "ADS_FULL", "CLICK_ADS_FULL", "TAG", "getTAG", "()Ljava/lang/String;", "mInstance", "Lcom/cem/admodule/manager/CemBannerManager;", "getInstance", "adModule_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getTAG() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.cem.admodule.manager.CemBannerManager getInstance() {
            return null;
        }
    }
}