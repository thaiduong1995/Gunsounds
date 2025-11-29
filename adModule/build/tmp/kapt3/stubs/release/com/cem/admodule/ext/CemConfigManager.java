package com.cem.admodule.ext;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0007JH\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112%\b\u0002\u0010\u0012\u001a\u001f\u0012\u0013\u0012\u00110\b\u00a2\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0013H\u0087@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0018"}, d2 = {"Lcom/cem/admodule/ext/CemConfigManager;", "", "()V", "initAppsflyer", "", "application", "Landroid/app/Application;", "flurryKey", "", "initFlurry", "initQonVersion", "context", "Landroid/content/Context;", "keySdk", "initializeAds", "app", "configuration", "Lcom/cem/admodule/data/Configuration;", "callbackCountry", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "country", "(Landroid/app/Application;Lcom/cem/admodule/data/Configuration;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "adModule_release"})
public final class CemConfigManager {
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.ext.CemConfigManager INSTANCE = null;
    
    private CemConfigManager() {
        super();
    }
    
    @kotlin.jvm.JvmStatic
    @org.jetbrains.annotations.Nullable
    public static final java.lang.Object initializeAds(@org.jetbrains.annotations.NotNull
    android.app.Application app, @org.jetbrains.annotations.NotNull
    com.cem.admodule.data.Configuration configuration, @org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> callbackCountry, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.jvm.JvmStatic
    public static final void initQonVersion(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String keySdk) {
    }
    
    @kotlin.jvm.JvmStatic
    public static final void initFlurry(@org.jetbrains.annotations.NotNull
    android.app.Application application, @org.jetbrains.annotations.NotNull
    java.lang.String flurryKey) {
    }
    
    @kotlin.jvm.JvmStatic
    public static final void initAppsflyer(@org.jetbrains.annotations.NotNull
    android.app.Application application, @org.jetbrains.annotations.NotNull
    java.lang.String flurryKey) {
    }
}