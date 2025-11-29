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
public final class AdmobInterstitialAdManager_Factory implements Factory<AdmobInterstitialAdManager> {
  private final Provider<String> unitIdProvider;

  public AdmobInterstitialAdManager_Factory(Provider<String> unitIdProvider) {
    this.unitIdProvider = unitIdProvider;
  }

  @Override
  public AdmobInterstitialAdManager get() {
    return newInstance(unitIdProvider.get());
  }

  public static AdmobInterstitialAdManager_Factory create(Provider<String> unitIdProvider) {
    return new AdmobInterstitialAdManager_Factory(unitIdProvider);
  }

  public static AdmobInterstitialAdManager newInstance(String unitId) {
    return new AdmobInterstitialAdManager(unitId);
  }
}
