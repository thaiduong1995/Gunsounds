package com.cem.admodule.inter;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H&J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\tH&\u00a8\u0006\n"}, d2 = {"Lcom/cem/admodule/inter/OpenLoadCallback;", "", "onAdFailedToLoaded", "", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onAdLoaded", "cemOpenAd", "Lcom/cem/admodule/inter/CemOpenAd;", "adModule_release"})
public abstract interface OpenLoadCallback {
    
    public abstract void onAdLoaded(@org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.CemOpenAd cemOpenAd);
    
    public abstract void onAdFailedToLoaded(@org.jetbrains.annotations.NotNull
    java.lang.Exception error);
}