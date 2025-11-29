package com.cem.admodule.ads.admob;

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
public final class AdmobBannerAdManager_Factory implements Factory<AdmobBannerAdManager> {
  private final Provider<AdSize> adSizeProvider;

  private final Provider<String> adUnitProvider;

  public AdmobBannerAdManager_Factory(Provider<AdSize> adSizeProvider,
      Provider<String> adUnitProvider) {
    this.adSizeProvider = adSizeProvider;
    this.adUnitProvider = adUnitProvider;
  }

  @Override
  public AdmobBannerAdManager get() {
    return newInstance(adSizeProvider.get(), adUnitProvider.get());
  }

  public static AdmobBannerAdManager_Factory create(Provider<AdSize> adSizeProvider,
      Provider<String> adUnitProvider) {
    return new AdmobBannerAdManager_Factory(adSizeProvider, adUnitProvider);
  }

  public static AdmobBannerAdManager newInstance(AdSize adSize, String adUnit) {
    return new AdmobBannerAdManager(adSize, adUnit);
  }
}
