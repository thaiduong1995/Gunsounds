package com.cem.admodule.ads.applovin;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("dagger.hilt.android.scopes.ActivityScoped")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class ApplovinRewardAdManager_Factory implements Factory<ApplovinRewardAdManager> {
  private final Provider<String> adUnitIdProvider;

  public ApplovinRewardAdManager_Factory(Provider<String> adUnitIdProvider) {
    this.adUnitIdProvider = adUnitIdProvider;
  }

  @Override
  public ApplovinRewardAdManager get() {
    return newInstance(adUnitIdProvider.get());
  }

  public static ApplovinRewardAdManager_Factory create(Provider<String> adUnitIdProvider) {
    return new ApplovinRewardAdManager_Factory(adUnitIdProvider);
  }

  public static ApplovinRewardAdManager newInstance(String adUnitId) {
    return new ApplovinRewardAdManager(adUnitId);
  }
}
