package com.cem.admodule.inter;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH&J$\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH&R\u0012\u0010\u0002\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004\u00a8\u0006\u000f"}, d2 = {"Lcom/cem/admodule/inter/CemRewardAd;", "", "isLoaded", "", "()Z", "load", "activity", "Landroid/app/Activity;", "callback", "Lcom/cem/admodule/inter/RewardLoadCallback;", "show", "", "Lcom/cem/admodule/inter/RewardShowCallback;", "callbackItem", "Lcom/cem/admodule/inter/RewardItemCallback;", "adModule_release"})
public abstract interface CemRewardAd {
    
    @org.jetbrains.annotations.Nullable
    public abstract com.cem.admodule.inter.CemRewardAd load(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.RewardLoadCallback callback);
    
    public abstract boolean isLoaded();
    
    public abstract void show(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.RewardShowCallback callback, @org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.RewardItemCallback callbackItem);
}