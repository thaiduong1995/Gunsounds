package com.cem.admodule.manager;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u000eJ\u001e\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\r\u001a\u00020\u000eJ(\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0004H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\""}, d2 = {"Lcom/cem/admodule/manager/AppFlyersLogManager;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "logEventBannerAdRevenue", "", "bannerAd", "Lcom/google/android/gms/ads/AdView;", "adValue", "Lcom/google/android/gms/ads/AdValue;", "logEventInterstitialAdRevenue", "interstitialAd", "Lcom/google/android/gms/ads/interstitial/InterstitialAd;", "logEventNativeAdRevenue", "nativeAd", "Lcom/google/android/gms/ads/nativead/NativeAd;", "adUnitId", "logEventOpenAdsRevenue", "openAds", "Lcom/google/android/gms/ads/appopen/AppOpenAd;", "logEventRewardAdRevenue", "rewardAd", "Lcom/google/android/gms/ads/rewarded/RewardedAd;", "responseMessageLog", "format", "unitId", "revenue", "", "currencyCode", "adModule_release"})
public final class AppFlyersLogManager {
    @org.jetbrains.annotations.NotNull
    private static java.lang.String TAG = "AppsflyerRevenue";
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.manager.AppFlyersLogManager INSTANCE = null;
    
    private AppFlyersLogManager() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTAG() {
        return null;
    }
    
    public final void setTAG(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    public final void logEventInterstitialAdRevenue(@org.jetbrains.annotations.NotNull
    com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd, @org.jetbrains.annotations.NotNull
    com.google.android.gms.ads.AdValue adValue) {
    }
    
    public final void logEventNativeAdRevenue(@org.jetbrains.annotations.NotNull
    com.google.android.gms.ads.nativead.NativeAd nativeAd, @org.jetbrains.annotations.NotNull
    java.lang.String adUnitId, @org.jetbrains.annotations.NotNull
    com.google.android.gms.ads.AdValue adValue) {
    }
    
    public final void logEventBannerAdRevenue(@org.jetbrains.annotations.NotNull
    com.google.android.gms.ads.AdView bannerAd, @org.jetbrains.annotations.NotNull
    com.google.android.gms.ads.AdValue adValue) {
    }
    
    public final void logEventRewardAdRevenue(@org.jetbrains.annotations.NotNull
    com.google.android.gms.ads.rewarded.RewardedAd rewardAd, @org.jetbrains.annotations.NotNull
    com.google.android.gms.ads.AdValue adValue) {
    }
    
    public final void logEventOpenAdsRevenue(@org.jetbrains.annotations.NotNull
    com.google.android.gms.ads.appopen.AppOpenAd openAds, @org.jetbrains.annotations.NotNull
    com.google.android.gms.ads.AdValue adValue) {
    }
    
    private final void responseMessageLog(java.lang.String format, java.lang.String unitId, double revenue, java.lang.String currencyCode) {
    }
}