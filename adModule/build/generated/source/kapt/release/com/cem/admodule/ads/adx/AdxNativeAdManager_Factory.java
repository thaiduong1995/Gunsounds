package com.cem.admodule.ads.adx;

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
public final class AdxNativeAdManager_Factory implements Factory<AdxNativeAdManager> {
  private final Provider<String> adUnitIdProvider;

  public AdxNativeAdManager_Factory(Provider<String> adUnitIdProvider) {
    this.adUnitIdProvider = adUnitIdProvider;
  }

  @Override
  public AdxNativeAdManager get() {
    return newInstance(adUnitIdProvider.get());
  }

  public static AdxNativeAdManager_Factory create(Provider<String> adUnitIdProvider) {
    return new AdxNativeAdManager_Factory(adUnitIdProvider);
  }

  public static AdxNativeAdManager newInstance(String adUnitId) {
    return new AdxNativeAdManager(adUnitId);
  }
}
