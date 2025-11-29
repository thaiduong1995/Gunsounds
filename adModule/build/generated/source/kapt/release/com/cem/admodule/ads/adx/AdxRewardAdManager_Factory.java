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
public final class AdxRewardAdManager_Factory implements Factory<AdxRewardAdManager> {
  private final Provider<String> adUnitProvider;

  public AdxRewardAdManager_Factory(Provider<String> adUnitProvider) {
    this.adUnitProvider = adUnitProvider;
  }

  @Override
  public AdxRewardAdManager get() {
    return newInstance(adUnitProvider.get());
  }

  public static AdxRewardAdManager_Factory create(Provider<String> adUnitProvider) {
    return new AdxRewardAdManager_Factory(adUnitProvider);
  }

  public static AdxRewardAdManager newInstance(String adUnit) {
    return new AdxRewardAdManager(adUnit);
  }
}
