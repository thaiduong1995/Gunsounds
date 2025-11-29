package com.cem.admodule.ads.adx;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0011\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006H\u0016J%\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\n0\u00132\u0006\u0010\u000f\u001a\u00020\u0010H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u001a\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\bH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lcom/cem/admodule/ads/adx/AdxInterstitialAdManager;", "Lcom/cem/admodule/inter/CemInterstitialAd;", "adUnitId", "", "(Ljava/lang/String;)V", "callbackLoadAd", "Lcom/cem/admodule/inter/InterstitialLoadCallback;", "callbackShowAd", "Lcom/cem/admodule/inter/InterstitialShowCallback;", "interstitialAd", "Lcom/google/android/gms/ads/admanager/AdManagerInterstitialAd;", "isLoaded", "", "()Z", "load", "activity", "Landroid/app/Activity;", "callback", "loadAdInternal", "Larrow/core/Either;", "Lcom/cem/admodule/data/ErrorCode;", "(Landroid/app/Activity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "show", "", "Companion", "adModule_release"})
@androidx.annotation.MainThread
@dagger.hilt.android.scopes.ActivityScoped
public final class AdxInterstitialAdManager implements com.cem.admodule.inter.CemInterstitialAd {
    @org.jetbrains.annotations.Nullable
    private final java.lang.String adUnitId = null;
    @org.jetbrains.annotations.Nullable
    private com.google.android.gms.ads.admanager.AdManagerInterstitialAd interstitialAd;
    @org.jetbrains.annotations.Nullable
    private com.cem.admodule.inter.InterstitialLoadCallback callbackLoadAd;
    @org.jetbrains.annotations.Nullable
    private com.cem.admodule.inter.InterstitialShowCallback callbackShowAd;
    @org.jetbrains.annotations.NotNull
    private static java.lang.String TAG;
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.ads.adx.AdxInterstitialAdManager.Companion Companion = null;
    
    @javax.inject.Inject
    public AdxInterstitialAdManager(@org.jetbrains.annotations.Nullable
    java.lang.String adUnitId) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.cem.admodule.inter.CemInterstitialAd load(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.InterstitialLoadCallback callback) {
        return null;
    }
    
    private final java.lang.Object loadAdInternal(android.app.Activity activity, kotlin.coroutines.Continuation<? super arrow.core.Either<com.cem.admodule.data.ErrorCode, ? extends com.google.android.gms.ads.admanager.AdManagerInterstitialAd>> $completion) {
        return null;
    }
    
    @java.lang.Override
    public boolean isLoaded() {
        return false;
    }
    
    @java.lang.Override
    public void show(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.InterstitialShowCallback callback) {
    }
    
    @kotlin.jvm.JvmStatic
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.ads.adx.AdxInterstitialAdManager newInstance(@org.jetbrains.annotations.Nullable
    java.lang.String adUnit) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/cem/admodule/ads/adx/AdxInterstitialAdManager$Companion;", "", "()V", "TAG", "", "newInstance", "Lcom/cem/admodule/ads/adx/AdxInterstitialAdManager;", "adUnit", "adModule_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @kotlin.jvm.JvmStatic
        @org.jetbrains.annotations.NotNull
        public final com.cem.admodule.ads.adx.AdxInterstitialAdManager newInstance(@org.jetbrains.annotations.Nullable
        java.lang.String adUnit) {
            return null;
        }
    }
}