package com.cem.admodule.inter;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0012\u0010\u0005\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u0003H&\u00a8\u0006\u000e"}, d2 = {"Lcom/cem/admodule/inter/BannerAdListener;", "", "onBannerClicked", "", "onBannerClose", "onBannerFailed", "error", "", "onBannerLoaded", "banner", "Lcom/cem/admodule/inter/BannerAdView;", "view", "Landroid/view/View;", "onBannerOpen", "adModule_release"})
public abstract interface BannerAdListener {
    
    public abstract void onBannerLoaded(@org.jetbrains.annotations.NotNull
    com.cem.admodule.inter.BannerAdView banner, @org.jetbrains.annotations.NotNull
    android.view.View view);
    
    public abstract void onBannerFailed(@org.jetbrains.annotations.Nullable
    java.lang.String error);
    
    public abstract void onBannerClicked();
    
    public abstract void onBannerOpen();
    
    public abstract void onBannerClose();
}