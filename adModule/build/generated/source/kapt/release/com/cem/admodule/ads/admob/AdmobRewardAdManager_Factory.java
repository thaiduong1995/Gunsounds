package com.cem.admodule.ads.admob;

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
public final class AdmobRewardAdManager_Factory implements Factory<AdmobRewardAdManager> {
  private final Provider<String> adUnitProvider;

  public AdmobRewardAdManager_Factory(Provider<String> adUnitProvider) {
    this.adUnitProvider = adUnitProvider;
  }

  @Override
  public AdmobRewardAdManager get() {
    return newInstance(adUnitProvider.get());
  }

  public static AdmobRewardAdManager_Factory create(Provider<String> adUnitProvider) {
    return new AdmobRewardAdManager_Factory(adUnitProvider);
  }

  public static AdmobRewardAdManager newInstance(String adUnit) {
    return new AdmobRewardAdManager(adUnit);
  }
}
