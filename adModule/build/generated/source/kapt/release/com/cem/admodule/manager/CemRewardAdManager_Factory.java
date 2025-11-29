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
public final class CemRewardAdManager_Factory implements Factory<CemRewardAdManager> {
  private final Provider<Context> contextProvider;

  public CemRewardAdManager_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public CemRewardAdManager get() {
    return newInstance(contextProvider.get());
  }

  public static CemRewardAdManager_Factory create(Provider<Context> contextProvider) {
    return new CemRewardAdManager_Factory(contextProvider);
  }

  public static CemRewardAdManager newInstance(Context context) {
    return new CemRewardAdManager(context);
  }
}
