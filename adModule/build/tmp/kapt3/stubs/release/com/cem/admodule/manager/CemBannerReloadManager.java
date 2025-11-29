package com.cem.admodule.manager;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 !2\u00020\u0001:\u0001!B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000bH\u0002J@\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u000b2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001aJ@\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u000b2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001aJ4\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u000b2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001aJ\u001a\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\u000b2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u000bR\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R*\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nj\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/cem/admodule/manager/CemBannerReloadManager;", "", "()V", "cemBannerManager", "Lcom/cem/admodule/manager/CemBannerManager;", "getCemBannerManager", "()Lcom/cem/admodule/manager/CemBannerManager;", "cemBannerManager$delegate", "Lkotlin/Lazy;", "handlerListBanner", "Ljava/util/HashMap;", "", "Landroid/os/Handler;", "Lkotlin/collections/HashMap;", "getOrPutHandler", "configKey", "loadAndShowBannerByContextReload", "", "context", "Landroid/content/Context;", "viewGroup", "Landroid/view/ViewGroup;", "position", "callback", "Lcom/cem/admodule/inter/BannerAdListener;", "refreshBannerTime", "", "loadBannerAndShowByActivityReload", "activity", "Landroid/app/Activity;", "loadBannerShowNoCollapsibleReload", "removeRunnableAndCallback", "messages", "Companion", "adModule_release"})
public final class CemBannerReloadManager {
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy cemBannerManager$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.HashMap<java.lang.String, android.os.Handler> handlerListBanner = null;
    private static final java.lang.String TAG = null;
    @org.jetbrains.annotations.Nullable
    private static com.cem.admodule.manager.CemBannerReloadManager mInstance;
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.manager.CemBannerReloadManager.Companion Companion = null;
    
    private CemBannerReloadManager() {
        super();
    }
    
    private final com.cem.admodule.manager.CemBannerManager getCemBannerManager() {
        return null;
    }
    
    private final android.os.Handler getOrPutHandler(java.lang.String configKey) {
        return null;
    }
    
    public final void loadBannerShowNoCollapsibleReload(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    android.view.ViewGroup viewGroup, @org.jetbrains.annotations.NotNull
    java.lang.String configKey, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.BannerAdListener callback, int refreshBannerTime) {
    }
    
    public final void loadAndShowBannerByContextReload(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    android.view.ViewGroup viewGroup, @org.jetbrains.annotations.NotNull
    java.lang.String configKey, @org.jetbrains.annotations.Nullable
    java.lang.String position, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.BannerAdListener callback, int refreshBannerTime) {
    }
    
    public final void loadBannerAndShowByActivityReload(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.NotNull
    android.view.ViewGroup viewGroup, @org.jetbrains.annotations.NotNull
    java.lang.String configKey, @org.jetbrains.annotations.Nullable
    java.lang.String position, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.BannerAdListener callback, int refreshBannerTime) {
    }
    
    public final void removeRunnableAndCallback(@org.jetbrains.annotations.NotNull
    java.lang.String configKey, @org.jetbrains.annotations.Nullable
    java.lang.String messages) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\tR\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/cem/admodule/manager/CemBannerReloadManager$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "mInstance", "Lcom/cem/admodule/manager/CemBannerReloadManager;", "getInstance", "adModule_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final java.lang.String getTAG() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.cem.admodule.manager.CemBannerReloadManager getInstance() {
            return null;
        }
    }
}