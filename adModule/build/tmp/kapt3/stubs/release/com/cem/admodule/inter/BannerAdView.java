package com.cem.admodule.inter;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH&J*\u0010\n\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH&\u00a8\u0006\r"}, d2 = {"Lcom/cem/admodule/inter/BannerAdView;", "", "createByActivity", "Landroid/view/View;", "activity", "Landroid/app/Activity;", "listener", "Lcom/cem/admodule/inter/BannerAdListener;", "position", "", "createByContext", "context", "Landroid/content/Context;", "adModule_release"})
public abstract interface BannerAdView {
    
    @org.jetbrains.annotations.Nullable
    public abstract android.view.View createByActivity(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.BannerAdListener listener, @org.jetbrains.annotations.Nullable
    java.lang.String position);
    
    @org.jetbrains.annotations.Nullable
    public abstract android.view.View createByContext(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.BannerAdListener listener, @org.jetbrains.annotations.Nullable
    java.lang.String position);
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}