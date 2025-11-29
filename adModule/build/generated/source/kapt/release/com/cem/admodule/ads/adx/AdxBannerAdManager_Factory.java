package com.cem.admodule.ads.adx;

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
public final class AdxBannerAdManager_Factory implements Factory<AdxBannerAdManager> {
  private final Provider<AdSize> adSizeProvider;

  private final Provider<String> adUnitIdProvider;

  public AdxBannerAdManager_Factory(Provider<AdSize> adSizeProvider,
      Provider<String> adUnitIdProvider) {
    this.adSizeProvider = adSizeProvider;
    this.adUnitIdProvider = adUnitIdProvider;
  }

  @Override
  public AdxBannerAdManager get() {
    return newInstance(adSizeProvider.get(), adUnitIdProvider.get());
  }

  public static AdxBannerAdManager_Factory create(Provider<AdSize> adSizeProvider,
      Provider<String> adUnitIdProvider) {
    return new AdxBannerAdManager_Factory(adSizeProvider, adUnitIdProvider);
  }

  public static AdxBannerAdManager newInstance(AdSize adSize, String adUnitId) {
    return new AdxBannerAdManager(adSize, adUnitId);
  }
}
