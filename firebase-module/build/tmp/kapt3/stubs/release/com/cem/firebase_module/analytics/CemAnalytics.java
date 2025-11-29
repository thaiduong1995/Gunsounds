package com.cem.firebase_module.analytics;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J@\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042(\b\u0002\u0010\u000e\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000fj\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0010JL\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u0006\u0010\r\u001a\u00020\u00042(\b\u0002\u0010\u000e\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000fj\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0010J\"\u0010\u0012\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u0004J\u0016\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004J\u001a\u0010\u0015\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u0016"}, d2 = {"Lcom/cem/firebase_module/analytics/CemAnalytics;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "logEventAndParams", "", "context", "Landroid/content/Context;", "eventName", "params", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "screenName", "logEventClickView", "actionName", "logEventScreenView", "logEventShowScreen", "firebase-module_release"})
public final class CemAnalytics {
    @org.jetbrains.annotations.NotNull
    private static java.lang.String TAG;
    @org.jetbrains.annotations.NotNull
    public static final com.cem.firebase_module.analytics.CemAnalytics INSTANCE = null;
    
    private CemAnalytics() {
        super();
    }
    
    public final void logEventAndParams(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.String screenName, @org.jetbrains.annotations.NotNull
    java.lang.String eventName, @org.jetbrains.annotations.Nullable
    java.util.HashMap<java.lang.String, java.lang.String> params) {
    }
    
    public final void logEventShowScreen(@org.jetbrains.annotations.Nullable
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.String screenName) {
    }
    
    public final void logEventClickView(@org.jetbrains.annotations.Nullable
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.String screenName, @org.jetbrains.annotations.NotNull
    java.lang.String actionName) {
    }
    
    public final void logEventAndParams(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String eventName, @org.jetbrains.annotations.Nullable
    java.util.HashMap<java.lang.String, java.lang.String> params) {
    }
    
    public final void logEventScreenView(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String eventName) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTAG() {
        return null;
    }
    
    public final void setTAG(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
}