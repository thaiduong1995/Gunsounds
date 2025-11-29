package com.cem.admodule.manager;

import android.content.Context;
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
public final class CemInterstitialManager_Factory implements Factory<CemInterstitialManager> {
  private final Provider<Context> activityProvider;

  public CemInterstitialManager_Factory(Provider<Context> activityProvider) {
    this.activityProvider = activityProvider;
  }

  @Override
  public CemInterstitialManager get() {
    return newInstance(activityProvider.get());
  }

  public static CemInterstitialManager_Factory create(Provider<Context> activityProvider) {
    return new CemInterstitialManager_Factory(activityProvider);
  }

  public static CemInterstitialManager newInstance(Context activity) {
    return new CemInterstitialManager(activity);
  }
}
