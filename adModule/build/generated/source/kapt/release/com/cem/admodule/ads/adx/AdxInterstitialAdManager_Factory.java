package com.cem.admodule.ads.adx;

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
public final class AdxInterstitialAdManager_Factory implements Factory<AdxInterstitialAdManager> {
  private final Provider<String> adUnitIdProvider;

  public AdxInterstitialAdManager_Factory(Provider<String> adUnitIdProvider) {
    this.adUnitIdProvider = adUnitIdProvider;
  }

  @Override
  public AdxInterstitialAdManager get() {
    return newInstance(adUnitIdProvider.get());
  }

  public static AdxInterstitialAdManager_Factory create(Provider<String> adUnitIdProvider) {
    return new AdxInterstitialAdManager_Factory(adUnitIdProvider);
  }

  public static AdxInterstitialAdManager newInstance(String adUnitId) {
    return new AdxInterstitialAdManager(adUnitId);
  }
}
