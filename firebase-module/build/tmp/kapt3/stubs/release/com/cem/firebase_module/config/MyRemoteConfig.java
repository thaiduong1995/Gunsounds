package com.cem.firebase_module.config;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0017\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016\u00a2\u0006\u0002\u0010\u000fJ\u0017\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\r\u001a\u00020\u000eH\u0016\u00a2\u0006\u0002\u0010\u0012J\u0017\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\r\u001a\u00020\u000eH\u0016\u00a2\u0006\u0002\u0010\u0015J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0017"}, d2 = {"Lcom/cem/firebase_module/config/MyRemoteConfig;", "Lcom/cem/firebase_module/config/RemoteConfigInterface;", "()V", "remoteConfig", "Lcom/google/firebase/remoteconfig/FirebaseRemoteConfig;", "getRemoteConfig", "()Lcom/google/firebase/remoteconfig/FirebaseRemoteConfig;", "remoteConfig$delegate", "Lkotlin/Lazy;", "fetchConfig", "Lkotlinx/coroutines/flow/Flow;", "", "getBoolean", "value", "", "(Ljava/lang/String;)Ljava/lang/Boolean;", "getDouble", "", "(Ljava/lang/String;)Ljava/lang/Double;", "getLong", "", "(Ljava/lang/String;)Ljava/lang/Long;", "getString", "firebase-module_release"})
public final class MyRemoteConfig implements com.cem.firebase_module.config.RemoteConfigInterface {
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy remoteConfig$delegate = null;
    
    public MyRemoteConfig() {
        super();
    }
    
    private final com.google.firebase.remoteconfig.FirebaseRemoteConfig getRemoteConfig() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.String getString(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Long getLong(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Boolean getBoolean(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Double getDouble(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<java.lang.Boolean> fetchConfig() {
        return null;
    }
}