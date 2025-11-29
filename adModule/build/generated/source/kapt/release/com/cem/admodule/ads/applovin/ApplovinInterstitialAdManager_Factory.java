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
public final class ApplovinInterstitialAdManager_Factory implements Factory<ApplovinInterstitialAdManager> {
  private final Provider<String> unitIdProvider;

  public ApplovinInterstitialAdManager_Factory(Provider<String> unitIdProvider) {
    this.unitIdProvider = unitIdProvider;
  }

  @Override
  public ApplovinInterstitialAdManager get() {
    return newInstance(unitIdProvider.get());
  }

  public static ApplovinInterstitialAdManager_Factory create(Provider<String> unitIdProvider) {
    return new ApplovinInterstitialAdManager_Factory(unitIdProvider);
  }

  public static ApplovinInterstitialAdManager newInstance(String unitId) {
    return new ApplovinInterstitialAdManager(unitId);
  }
}
