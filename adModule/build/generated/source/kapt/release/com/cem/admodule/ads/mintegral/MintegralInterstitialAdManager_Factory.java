package com.cem.admodule.ads.mintegral;

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
public final class MintegralInterstitialAdManager_Factory implements Factory<MintegralInterstitialAdManager> {
  private final Provider<String> adUnitProvider;

  private final Provider<String> placementIdProvider;

  public MintegralInterstitialAdManager_Factory(Provider<String> adUnitProvider,
      Provider<String> placementIdProvider) {
    this.adUnitProvider = adUnitProvider;
    this.placementIdProvider = placementIdProvider;
  }

  @Override
  public MintegralInterstitialAdManager get() {
    return newInstance(adUnitProvider.get(), placementIdProvider.get());
  }

  public static MintegralInterstitialAdManager_Factory create(Provider<String> adUnitProvider,
      Provider<String> placementIdProvider) {
    return new MintegralInterstitialAdManager_Factory(adUnitProvider, placementIdProvider);
  }

  public static MintegralInterstitialAdManager newInstance(String adUnit, String placementId) {
    return new MintegralInterstitialAdManager(adUnit, placementId);
  }
}
