package com.cem.firebase_module.analytics;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J8\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2&\u0010\u000f\u001a\"\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010j\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u0001`\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u0015"}, d2 = {"Lcom/cem/firebase_module/analytics/FirebaseTrackingEvent;", "Lcom/cem/firebase_module/analytics/CemEventTracking;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "firebaseEvent", "Lcom/google/firebase/analytics/FirebaseAnalytics;", "getFirebaseEvent", "()Lcom/google/firebase/analytics/FirebaseAnalytics;", "firebaseEvent$delegate", "Lkotlin/Lazy;", "logEvent", "", "eventName", "", "params", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "logEventView", "screenName", "Companion", "firebase-module_release"})
public final class FirebaseTrackingEvent implements com.cem.firebase_module.analytics.CemEventTracking {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy firebaseEvent$delegate = null;
    @android.annotation.SuppressLint(value = {"StaticFieldLeak"})
    @org.jetbrains.annotations.Nullable
    private static com.cem.firebase_module.analytics.FirebaseTrackingEvent mInstance;
    @org.jetbrains.annotations.NotNull
    public static final com.cem.firebase_module.analytics.FirebaseTrackingEvent.Companion Companion = null;
    
    @javax.inject.Inject
    public FirebaseTrackingEvent(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    private final com.google.firebase.analytics.FirebaseAnalytics getFirebaseEvent() {
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
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0083\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/cem/firebase_module/analytics/FirebaseTrackingEvent$Companion;", "", "()V", "mInstance", "Lcom/cem/firebase_module/analytics/FirebaseTrackingEvent;", "getInstance", "activity", "Landroid/content/Context;", "firebase-module_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.cem.firebase_module.analytics.FirebaseTrackingEvent getInstance(@org.jetbrains.annotations.NotNull
        android.content.Context activity) {
            return null;
        }
    }
}