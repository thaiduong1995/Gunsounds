package com.cem.admodule.inter;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&\u00a8\u0006\u000b"}, d2 = {"Lcom/cem/admodule/inter/InterstitialShowCallback;", "", "onAdClicked", "", "onAdFailedToShowCallback", "error", "", "onAdShowedCallback", "network", "Lcom/cem/admodule/enums/AdNetwork;", "onDismissCallback", "adModule_release"})
public abstract interface InterstitialShowCallback {
    
    public abstract void onAdFailedToShowCallback(@org.jetbrains.annotations.NotNull
    java.lang.String error);
    
    public abstract void onAdShowedCallback(@org.jetbrains.annotations.NotNull
    com.cem.admodule.enums.AdNetwork network);
    
    public abstract void onDismissCallback(@org.jetbrains.annotations.NotNull
    com.cem.admodule.enums.AdNetwork network);
    
    public abstract void onAdClicked();
}