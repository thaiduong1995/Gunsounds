package com.cem.admodule.inter;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\b\u001a\u0004\u0018\u00010\fH&R\u0012\u0010\u0002\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004\u00a8\u0006\r"}, d2 = {"Lcom/cem/admodule/inter/CemOpenAd;", "", "isLoaded", "", "()Z", "onLoaded", "adUnit", "", "callback", "Lcom/cem/admodule/inter/OpenLoadCallback;", "onShowed", "", "Lcom/cem/admodule/inter/InterstitialShowCallback;", "adModule_release"})
public abstract interface CemOpenAd {
    
    @org.jetbrains.annotations.NotNull
    public abstract com.cem.admodule.inter.CemOpenAd onLoaded(@org.jetbrains.annotations.NotNull
    java.lang.String adUnit, @org.jetbrains.annotations.NotNull
    com.cem.admodule.inter.OpenLoadCallback callback);
    
    public abstract boolean isLoaded();
    
    public abstract void onShowed(@org.jetbrains.annotations.Nullable
    com.cem.admodule.inter.InterstitialShowCallback callback);
}