package com.cem.firebase_module.analytics;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J8\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132&\u0010\u0014\u001a\"\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0015j\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013\u0018\u0001`\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0013H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lcom/cem/firebase_module/analytics/TrackingEventImpl;", "Lcom/cem/firebase_module/analytics/CemEventTracking;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "appFlyerTrackingEvent", "Lcom/cem/firebase_module/analytics/AppFlyerTrackingEvent;", "getAppFlyerTrackingEvent", "()Lcom/cem/firebase_module/analytics/AppFlyerTrackingEvent;", "appFlyerTrackingEvent$delegate", "Lkotlin/Lazy;", "firebaseTrackingEvent", "Lcom/cem/firebase_module/analytics/FirebaseTrackingEvent;", "getFirebaseTrackingEvent", "()Lcom/cem/firebase_module/analytics/FirebaseTrackingEvent;", "firebaseTrackingEvent$delegate", "logEvent", "", "eventName", "", "params", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "logEventView", "screenName", "Companion", "firebase-module_release"})
public final class TrackingEventImpl implements com.cem.firebase_module.analytics.CemEventTracking {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy firebaseTrackingEvent$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy appFlyerTrackingEvent$delegate = null;
    @android.annotation.SuppressLint(value = {"StaticFieldLeak"})
    @org.jetbrains.annotations.Nullable
    private static com.cem.firebase_module.analytics.TrackingEventImpl mInstance;
    @org.jetbrains.annotations.NotNull
    public static final com.cem.firebase_module.analytics.TrackingEventImpl.Companion Companion = null;
    
    @javax.inject.Inject
    public TrackingEventImpl(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    private final com.cem.firebase_module.analytics.FirebaseTrackingEvent getFirebaseTrackingEvent() {
        return null;
    }
    
    private final com.cem.firebase_module.analytics.AppFlyerTrackingEvent getAppFlyerTrackingEvent() {
        return null;
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
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0083\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/cem/firebase_module/analytics/TrackingEventImpl$Companion;", "", "()V", "mInstance", "Lcom/cem/firebase_module/analytics/TrackingEventImpl;", "getInstance", "activity", "Landroid/content/Context;", "firebase-module_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.cem.firebase_module.analytics.TrackingEventImpl getInstance(@org.jetbrains.annotations.NotNull
        android.content.Context activity) {
            return null;
        }
    }
}