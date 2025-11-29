package com.cem.admodule.ads.mintegral;

import com.google.android.gms.ads.AdSize;
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
public final class MintegralBannerAdManager_Factory implements Factory<MintegralBannerAdManager> {
  private final Provider<AdSize> adSizeProvider;

  private final Provider<String> adUnitProvider;

  private final Provider<String> placementIdProvider;

  public MintegralBannerAdManager_Factory(Provider<AdSize> adSizeProvider,
      Provider<String> adUnitProvider, Provider<String> placementIdProvider) {
    this.adSizeProvider = adSizeProvider;
    this.adUnitProvider = adUnitProvider;
    this.placementIdProvider = placementIdProvider;
  }

  @Override
  public MintegralBannerAdManager get() {
    return newInstance(adSizeProvider.get(), adUnitProvider.get(), placementIdProvider.get());
  }

  public static MintegralBannerAdManager_Factory create(Provider<AdSize> adSizeProvider,
      Provider<String> adUnitProvider, Provider<String> placementIdProvider) {
    return new MintegralBannerAdManager_Factory(adSizeProvider, adUnitProvider, placementIdProvider);
  }

  public static MintegralBannerAdManager newInstance(AdSize adSize, String adUnit,
      String placementId) {
    return new MintegralBannerAdManager(adSize, adUnit, placementId);
  }
}
