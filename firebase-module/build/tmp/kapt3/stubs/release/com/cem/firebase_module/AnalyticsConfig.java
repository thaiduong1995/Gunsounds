package com.cem.firebase_module;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0007J\u0018\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/cem/firebase_module/AnalyticsConfig;", "", "()V", "TAG", "", "initAppFlyer", "", "app", "Landroid/app/Application;", "yourSdkKey", "initQonVersion", "context", "Landroid/content/Context;", "keySdk", "initializeFlurry", "application", "flurryKey", "firebase-module_release"})
public final class AnalyticsConfig {
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String TAG = "AnalyticsConfig";
    @org.jetbrains.annotations.NotNull
    public static final com.cem.firebase_module.AnalyticsConfig INSTANCE = null;
    
    private AnalyticsConfig() {
        super();
    }
    
    @kotlin.jvm.JvmStatic
    public static final void initializeFlurry(@org.jetbrains.annotations.NotNull
    android.app.Application application, @org.jetbrains.annotations.NotNull
    java.lang.String flurryKey) {
    }
    
    @kotlin.jvm.JvmStatic
    public static final void initQonVersion(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String keySdk) {
    }
    
    @kotlin.jvm.JvmStatic
    public static final void initAppFlyer(@org.jetbrains.annotations.NotNull
    android.app.Application app, @org.jetbrains.annotations.NotNull
    java.lang.String yourSdkKey) {
    }
}