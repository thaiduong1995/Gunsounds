package com.cem.firebase_module.analytics;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J8\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062&\u0010\u0007\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\bj\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\tH\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0006H\u0016\u00a8\u0006\r"}, d2 = {"Lcom/cem/firebase_module/analytics/FlurryTrackingEvent;", "Lcom/cem/firebase_module/analytics/CemEventTracking;", "()V", "logEvent", "", "eventName", "", "params", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "logEventView", "screenName", "Companion", "firebase-module_release"})
public final class FlurryTrackingEvent implements com.cem.firebase_module.analytics.CemEventTracking {
    @org.jetbrains.annotations.Nullable
    private static com.cem.firebase_module.analytics.FlurryTrackingEvent mInstance;
    @org.jetbrains.annotations.NotNull
    public static final com.cem.firebase_module.analytics.FlurryTrackingEvent.Companion Companion = null;
    
    @javax.inject.Inject
    public FlurryTrackingEvent() {
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
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/cem/firebase_module/analytics/FlurryTrackingEvent$Companion;", "", "()V", "mInstance", "Lcom/cem/firebase_module/analytics/FlurryTrackingEvent;", "getInstance", "firebase-module_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.cem.firebase_module.analytics.FlurryTrackingEvent getInstance() {
            return null;
        }
    }
}