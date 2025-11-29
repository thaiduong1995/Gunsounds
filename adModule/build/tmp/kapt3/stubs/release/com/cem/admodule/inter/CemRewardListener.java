package com.cem.admodule.inter;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000b\u001a\u00020\u0007H\u0016J\u0012\u0010\f\u001a\u00020\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u000e\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\nH&J\u0012\u0010\u000f\u001a\u00020\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u0005H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/cem/admodule/inter/CemRewardListener;", "Lcom/cem/admodule/inter/RewardShowCallback;", "Lcom/cem/admodule/inter/RewardItemCallback;", "()V", "item", "Lcom/cem/admodule/data/RewardAdItem;", "onAdDismissedFullScreenContent", "", "onAdFailedToShowFullScreenContent", "error", "", "onAdShowedFullScreenContent", "onRewardAdded", "rewardAdItem", "onRewardFail", "onUserEarnedReward", "adModule_release"})
public abstract class CemRewardListener implements com.cem.admodule.inter.RewardShowCallback, com.cem.admodule.inter.RewardItemCallback {
    @org.jetbrains.annotations.Nullable
    private com.cem.admodule.data.RewardAdItem item;
    
    public CemRewardListener() {
        super();
    }
    
    public abstract void onRewardAdded(@org.jetbrains.annotations.Nullable
    com.cem.admodule.data.RewardAdItem rewardAdItem);
    
    public abstract void onRewardFail(@org.jetbrains.annotations.Nullable
    java.lang.String error);
    
    @java.lang.Override
    public void onAdDismissedFullScreenContent() {
    }
    
    @java.lang.Override
    public void onAdFailedToShowFullScreenContent(@org.jetbrains.annotations.Nullable
    java.lang.String error) {
    }
    
    @java.lang.Override
    public void onAdShowedFullScreenContent() {
    }
    
    @java.lang.Override
    public void onUserEarnedReward(@org.jetbrains.annotations.Nullable
    com.cem.admodule.data.RewardAdItem rewardAdItem) {
    }
}