package com.cem.admodule.inter;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH&J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&R\u0012\u0010\u0002\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004\u00a8\u0006\u000e"}, d2 = {"Lcom/cem/admodule/inter/CemNativeAdView;", "", "isLoaded", "", "()Z", "load", "context", "Landroid/content/Context;", "callback", "Lcom/cem/admodule/inter/NativeAdCallback;", "show", "", "view", "Lcom/cem/admodule/manager/CustomNativeView;", "adModule_release"})
public abstract interface CemNativeAdView {
    
    @org.jetbrains.annotations.NotNull
    public abstract com.cem.admodule.inter.CemNativeAdView load(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.NativeAdCallback callback);
    
    public abstract void show(@org.jetbrains.annotations.NotNull
    com.cem.admodule.manager.CustomNativeView view);
    
    public abstract boolean isLoaded();
}