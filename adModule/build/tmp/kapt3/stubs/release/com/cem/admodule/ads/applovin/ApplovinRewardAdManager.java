package com.cem.admodule.ads.applovin;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0011\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\'\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00102\u0006\u0010\u000b\u001a\u00020\fH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J$\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0018"}, d2 = {"Lcom/cem/admodule/ads/applovin/ApplovinRewardAdManager;", "Lcom/cem/admodule/inter/CemRewardAd;", "adUnitId", "", "(Ljava/lang/String;)V", "isLoaded", "", "()Z", "rewardAd", "Lcom/applovin/mediation/ads/MaxRewardedAd;", "load", "activity", "Landroid/app/Activity;", "callback", "Lcom/cem/admodule/inter/RewardLoadCallback;", "loadAdInternal", "Larrow/core/Either;", "(Landroid/app/Activity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "show", "", "Lcom/cem/admodule/inter/RewardShowCallback;", "callbackItem", "Lcom/cem/admodule/inter/RewardItemCallback;", "Companion", "adModule_release"})
@androidx.annotation.MainThread
@dagger.hilt.android.scopes.ActivityScoped
public final class ApplovinRewardAdManager implements com.cem.admodule.inter.CemRewardAd {
    @org.jetbrains.annotations.Nullable
    private final java.lang.String adUnitId = null;
    @org.jetbrains.annotations.Nullable
    private com.applovin.mediation.ads.MaxRewardedAd rewardAd;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = null;
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.ads.applovin.ApplovinRewardAdManager.Companion Companion = null;
    
    @javax.inject.Inject
    public ApplovinRewardAdManager(@org.jetbrains.annotations.Nullable
    java.lang.String adUnitId) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.cem.admodule.inter.CemRewardAd load(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.RewardLoadCallback callback) {
        return null;
    }
    
    private final java.lang.Object loadAdInternal(android.app.Activity activity, kotlin.coroutines.Continuation<? super arrow.core.Either<java.lang.String, ? extends com.applovin.mediation.ads.MaxRewardedAd>> $completion) {
        return null;
    }
    
    @java.lang.Override
    public boolean isLoaded() {
        return false;
    }
    
    @java.lang.Override
    public void show(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.RewardShowCallback callback, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.RewardItemCallback callbackItem) {
    }
    
    @kotlin.jvm.JvmStatic
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.ads.applovin.ApplovinRewardAdManager newInstance(@org.jetbrains.annotations.Nullable
    java.lang.String adUnit) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0007R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\n"}, d2 = {"Lcom/cem/admodule/ads/applovin/ApplovinRewardAdManager$Companion;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "newInstance", "Lcom/cem/admodule/ads/applovin/ApplovinRewardAdManager;", "adUnit", "adModule_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getTAG() {
            return null;
        }
        
        @kotlin.jvm.JvmStatic
        @org.jetbrains.annotations.NotNull
        public final com.cem.admodule.ads.applovin.ApplovinRewardAdManager newInstance(@org.jetbrains.annotations.Nullable
        java.lang.String adUnit) {
            return null;
        }
    }
}