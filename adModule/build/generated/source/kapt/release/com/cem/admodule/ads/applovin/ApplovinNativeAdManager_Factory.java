package com.cem.admodule.ads.applovin;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class ApplovinNativeAdManager_Factory implements Factory<ApplovinNativeAdManager> {
  private final Provider<String> adUnitIdProvider;

  public ApplovinNativeAdManager_Factory(Provider<String> adUnitIdProvider) {
    this.adUnitIdProvider = adUnitIdProvider;
  }

  @Override
  public ApplovinNativeAdManager get() {
    return newInstance(adUnitIdProvider.get());
  }

  public static ApplovinNativeAdManager_Factory create(Provider<String> adUnitIdProvider) {
    return new ApplovinNativeAdManager_Factory(adUnitIdProvider);
  }

  public static ApplovinNativeAdManager newInstance(String adUnitId) {
    return new ApplovinNativeAdManager(adUnitId);
  }
}
