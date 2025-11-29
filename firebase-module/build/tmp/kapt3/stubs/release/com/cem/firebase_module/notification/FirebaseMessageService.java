package com.cem.firebase_module.notification;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0012\u0010\u000e\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J\u0012\u0010\u0011\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002\u00a8\u0006\u0013"}, d2 = {"Lcom/cem/firebase_module/notification/FirebaseMessageService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "()V", "getLauncherActivity", "Ljava/lang/Class;", "context", "Landroid/content/Context;", "onMessageReceived", "", "message", "Lcom/google/firebase/messaging/RemoteMessage;", "onNewToken", "token", "", "sendNotification", "messageBody", "Lcom/google/firebase/messaging/RemoteMessage$Notification;", "sendRegistrationToServer", "Companion", "firebase-module_release"})
public final class FirebaseMessageService extends com.google.firebase.messaging.FirebaseMessagingService {
    @org.jetbrains.annotations.Nullable
    private static final java.lang.String TAG = null;
    @org.jetbrains.annotations.NotNull
    public static final com.cem.firebase_module.notification.FirebaseMessageService.Companion Companion = null;
    
    public FirebaseMessageService() {
        super();
    }
    
    @java.lang.Override
    public void onMessageReceived(@org.jetbrains.annotations.NotNull
    com.google.firebase.messaging.RemoteMessage message) {
    }
    
    private final void sendNotification(com.google.firebase.messaging.RemoteMessage.Notification messageBody) {
    }
    
    @java.lang.Override
    public void onNewToken(@org.jetbrains.annotations.NotNull
    java.lang.String token) {
    }
    
    private final void sendRegistrationToServer(java.lang.String token) {
    }
    
    private final java.lang.Class<?> getLauncherActivity(android.content.Context context) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/cem/firebase_module/notification/FirebaseMessageService$Companion;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "firebase-module_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getTAG() {
            return null;
        }
    }
}