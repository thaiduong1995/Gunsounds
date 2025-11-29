package com.cem.firebase_module.analytics;

/**
 * Created by Hưng Nguyễn on 28/03/2024
 * Phone: 0335236374
 * Email: nguyenhunghung2806@gmail.com
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J8\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2&\u0010\t\u001a\"\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\nj\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u0001`\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/cem/firebase_module/analytics/AppFlyerTrackingEvent;", "Lcom/cem/firebase_module/analytics/CemEventTracking;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "logEvent", "", "eventName", "", "params", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "logEventView", "screenName", "Companion", "firebase-module_release"})
public final class AppFlyerTrackingEvent implements com.cem.firebase_module.analytics.CemEventTracking {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.Nullable
    private static com.cem.firebase_module.analytics.AppFlyerTrackingEvent mInstance;
    @org.jetbrains.annotations.NotNull
    public static final com.cem.firebase_module.analytics.AppFlyerTrackingEvent.Companion Companion = null;
    
    @javax.inject.Inject
    public AppFlyerTrackingEvent(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @java.lang.Override
    public void logEvent(@org.jetbrains.annotations.NotNull
    java.lang.String eventName, @org.jetbrains.annotations.Nullable
    java.util.HashMap<java.lang.String, java.lang.String> params) {
    }
    
    @java.lang.Override
    public void logEventView(@org.jetbrains.annotations.NotNull
    java.lang.String screenName) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/cem/firebase_module/analytics/AppFlyerTrackingEvent$Companion;", "", "()V", "mInstance", "Lcom/cem/firebase_module/analytics/AppFlyerTrackingEvent;", "getInstance", "context", "Landroid/content/Context;", "firebase-module_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.cem.firebase_module.analytics.AppFlyerTrackingEvent getInstance(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
    }
}