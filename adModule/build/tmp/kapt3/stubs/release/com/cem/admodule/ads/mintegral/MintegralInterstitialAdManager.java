package com.cem.admodule.ads.mintegral;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u001b\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0005J\u001a\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0007H\u0016J1\u0010\u0015\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u00162\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0003H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J)\u0010\u0019\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u00162\u0006\u0010\u0012\u001a\u00020\u0013H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ\u001a\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\tH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001e"}, d2 = {"Lcom/cem/admodule/ads/mintegral/MintegralInterstitialAdManager;", "Lcom/cem/admodule/inter/CemInterstitialAd;", "adUnit", "", "placementId", "(Ljava/lang/String;Ljava/lang/String;)V", "callbackLoadAd", "Lcom/cem/admodule/inter/InterstitialLoadCallback;", "callbackShowAd", "Lcom/cem/admodule/inter/InterstitialShowCallback;", "isLoaded", "", "()Z", "mBidInterstitialHandler", "Lcom/mbridge/msdk/newinterstitial/out/MBBidNewInterstitialHandler;", "mMBInterstitialHandler", "Lcom/mbridge/msdk/newinterstitial/out/MBNewInterstitialHandler;", "load", "activity", "Landroid/app/Activity;", "callback", "loadAdBidding", "Larrow/core/Either;", "bidToken", "(Landroid/app/Activity;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadAdTraditional", "(Landroid/app/Activity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "show", "", "Companion", "adModule_release"})
@androidx.annotation.MainThread
@dagger.hilt.android.scopes.ActivityScoped
public final class MintegralInterstitialAdManager implements com.cem.admodule.inter.CemInterstitialAd {
    @org.jetbrains.annotations.Nullable
    private final java.lang.String adUnit = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String placementId = null;
    @org.jetbrains.annotations.Nullable
    private com.mbridge.msdk.newinterstitial.out.MBNewInterstitialHandler mMBInterstitialHandler;
    @org.jetbrains.annotations.Nullable
    private com.mbridge.msdk.newinterstitial.out.MBBidNewInterstitialHandler mBidInterstitialHandler;
    @org.jetbrains.annotations.Nullable
    private com.cem.admodule.inter.InterstitialLoadCallback callbackLoadAd;
    @org.jetbrains.annotations.Nullable
    private com.cem.admodule.inter.InterstitialShowCallback callbackShowAd;
    private static java.lang.String TAG;
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.ads.mintegral.MintegralInterstitialAdManager.Companion Companion = null;
    
    @javax.inject.Inject
    public MintegralInterstitialAdManager(@org.jetbrains.annotations.Nullable
    java.lang.String adUnit, @org.jetbrains.annotations.Nullable
    java.lang.String placementId) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.cem.admodule.inter.CemInterstitialAd load(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.InterstitialLoadCallback callback) {
        return null;
    }
    
    private final java.lang.Object loadAdTraditional(android.app.Activity activity, kotlin.coroutines.Continuation<? super arrow.core.Either<java.lang.String, ? extends com.mbridge.msdk.newinterstitial.out.MBNewInterstitialHandler>> $completion) {
        return null;
    }
    
    private final java.lang.Object loadAdBidding(android.app.Activity activity, java.lang.String bidToken, kotlin.coroutines.Continuation<? super arrow.core.Either<java.lang.String, ? extends com.mbridge.msdk.newinterstitial.out.MBBidNewInterstitialHandler>> $completion) {
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
    public static final com.cem.admodule.ads.mintegral.MintegralInterstitialAdManager newInstance(@org.jetbrains.annotations.Nullable
    java.lang.String adUnit, @org.jetbrains.annotations.Nullable
    java.lang.String placementId) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0007R\"\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t\u00a8\u0006\u000e"}, d2 = {"Lcom/cem/admodule/ads/mintegral/MintegralInterstitialAdManager$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "newInstance", "Lcom/cem/admodule/ads/mintegral/MintegralInterstitialAdManager;", "adUnit", "placementId", "adModule_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final java.lang.String getTAG() {
            return null;
        }
        
        public final void setTAG(java.lang.String p0) {
        }
        
        @kotlin.jvm.JvmStatic
        @org.jetbrains.annotations.NotNull
        public final com.cem.admodule.ads.mintegral.MintegralInterstitialAdManager newInstance(@org.jetbrains.annotations.Nullable
        java.lang.String adUnit, @org.jetbrains.annotations.Nullable
        java.lang.String placementId) {
            return null;
        }
    }
}