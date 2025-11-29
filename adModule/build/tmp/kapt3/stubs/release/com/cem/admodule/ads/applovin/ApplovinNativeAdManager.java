package com.cem.admodule.ads.applovin;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0011\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/cem/admodule/ads/applovin/ApplovinNativeAdManager;", "Lcom/cem/admodule/inter/CemNativeAdView;", "adUnitId", "", "(Ljava/lang/String;)V", "isLoaded", "", "()Z", "maxNativeAd", "Lcom/applovin/mediation/nativeAds/MaxNativeAd;", "nativeMaxAdView", "Lcom/applovin/mediation/nativeAds/MaxNativeAdView;", "load", "context", "Landroid/content/Context;", "callback", "Lcom/cem/admodule/inter/NativeAdCallback;", "show", "", "view", "Lcom/cem/admodule/manager/CustomNativeView;", "Companion", "adModule_release"})
public final class ApplovinNativeAdManager implements com.cem.admodule.inter.CemNativeAdView {
    @org.jetbrains.annotations.Nullable
    private final java.lang.String adUnitId = null;
    @org.jetbrains.annotations.Nullable
    private com.applovin.mediation.nativeAds.MaxNativeAdView nativeMaxAdView;
    @org.jetbrains.annotations.Nullable
    private com.applovin.mediation.nativeAds.MaxNativeAd maxNativeAd;
    private static final java.lang.String TAG = null;
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.ads.applovin.ApplovinNativeAdManager.Companion Companion = null;
    
    @javax.inject.Inject
    public ApplovinNativeAdManager(@org.jetbrains.annotations.Nullable
    java.lang.String adUnitId) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.cem.admodule.inter.CemNativeAdView load(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.NativeAdCallback callback) {
        return null;
    }
    
    @java.lang.Override
    public void show(@org.jetbrains.annotations.NotNull
    com.cem.admodule.manager.CustomNativeView view) {
    }
    
    @java.lang.Override
    public boolean isLoaded() {
        return false;
    }
    
    @kotlin.jvm.JvmStatic
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.ads.applovin.ApplovinNativeAdManager newInstance(@org.jetbrains.annotations.Nullable
    java.lang.String adUnit) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0004H\u0007R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u000b"}, d2 = {"Lcom/cem/admodule/ads/applovin/ApplovinNativeAdManager$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "newInstance", "Lcom/cem/admodule/ads/applovin/ApplovinNativeAdManager;", "adUnit", "adModule_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final java.lang.String getTAG() {
            return null;
        }
        
        @kotlin.jvm.JvmStatic
        @org.jetbrains.annotations.NotNull
        public final com.cem.admodule.ads.applovin.ApplovinNativeAdManager newInstance(@org.jetbrains.annotations.Nullable
        java.lang.String adUnit) {
            return null;
        }
    }
}