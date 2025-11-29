package com.cem.admodule.ads.adx;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u001b\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J&\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0005H\u0016J&\u0010\u000e\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/cem/admodule/ads/adx/AdxBannerAdManager;", "Lcom/cem/admodule/inter/BannerAdView;", "adSize", "Lcom/google/android/gms/ads/AdSize;", "adUnitId", "", "(Lcom/google/android/gms/ads/AdSize;Ljava/lang/String;)V", "createByActivity", "Landroid/view/View;", "activity", "Landroid/app/Activity;", "listener", "Lcom/cem/admodule/inter/BannerAdListener;", "position", "createByContext", "context", "Landroid/content/Context;", "getAdSize", "Companion", "adModule_release"})
public final class AdxBannerAdManager implements com.cem.admodule.inter.BannerAdView {
    @org.jetbrains.annotations.Nullable
    private final com.google.android.gms.ads.AdSize adSize = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String adUnitId = null;
    @org.jetbrains.annotations.NotNull
    private static java.lang.String TAG;
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.ads.adx.AdxBannerAdManager.Companion Companion = null;
    
    @javax.inject.Inject
    public AdxBannerAdManager(@org.jetbrains.annotations.Nullable
    com.google.android.gms.ads.AdSize adSize, @org.jetbrains.annotations.Nullable
    java.lang.String adUnitId) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public android.view.View createByActivity(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.BannerAdListener listener, @org.jetbrains.annotations.Nullable
    java.lang.String position) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public android.view.View createByContext(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.BannerAdListener listener, @org.jetbrains.annotations.Nullable
    java.lang.String position) {
        return null;
    }
    
    private final com.google.android.gms.ads.AdSize getAdSize(android.content.Context context) {
        return null;
    }
    
    @kotlin.jvm.JvmStatic
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.ads.adx.AdxBannerAdManager newInstance(@org.jetbrains.annotations.Nullable
    com.google.android.gms.ads.AdSize adSize, @org.jetbrains.annotations.Nullable
    java.lang.String adUnit) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0007R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u000e"}, d2 = {"Lcom/cem/admodule/ads/adx/AdxBannerAdManager$Companion;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "newInstance", "Lcom/cem/admodule/ads/adx/AdxBannerAdManager;", "adSize", "Lcom/google/android/gms/ads/AdSize;", "adUnit", "adModule_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getTAG() {
            return null;
        }
        
        public final void setTAG(@org.jetbrains.annotations.NotNull
        java.lang.String p0) {
        }
        
        @kotlin.jvm.JvmStatic
        @org.jetbrains.annotations.NotNull
        public final com.cem.admodule.ads.adx.AdxBannerAdManager newInstance(@org.jetbrains.annotations.Nullable
        com.google.android.gms.ads.AdSize adSize, @org.jetbrains.annotations.Nullable
        java.lang.String adUnit) {
            return null;
        }
    }
}