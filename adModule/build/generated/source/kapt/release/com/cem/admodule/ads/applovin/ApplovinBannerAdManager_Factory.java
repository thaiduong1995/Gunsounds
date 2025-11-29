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
public final class ApplovinBannerAdManager_Factory implements Factory<ApplovinBannerAdManager> {
  private final Provider<String> adUnitProvider;

  public ApplovinBannerAdManager_Factory(Provider<String> adUnitProvider) {
    this.adUnitProvider = adUnitProvider;
  }

  @Override
  public ApplovinBannerAdManager get() {
    return newInstance(adUnitProvider.get());
  }

  public static ApplovinBannerAdManager_Factory create(Provider<String> adUnitProvider) {
    return new ApplovinBannerAdManager_Factory(adUnitProvider);
  }

  public static ApplovinBannerAdManager newInstance(String adUnit) {
    return new ApplovinBannerAdManager(adUnit);
  }
}
