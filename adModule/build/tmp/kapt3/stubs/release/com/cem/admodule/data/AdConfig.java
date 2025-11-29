package com.cem.admodule.data;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u001c\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0087\b\u0018\u0000 *2\u00020\u0001:\u0001*BC\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0003\u0010\b\u001a\u00020\u0005\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010 \u001a\u00020\u0005H\u00c6\u0003J\t\u0010!\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\nH\u00c6\u0003JG\u0010$\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u00052\b\b\u0003\u0010\b\u001a\u00020\u00052\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\nH\u00c6\u0001J\u0013\u0010%\u001a\u00020\u00032\b\u0010&\u001a\u0004\u0018\u00010\'H\u00d6\u0003J\t\u0010(\u001a\u00020\u0005H\u00d6\u0001J\t\u0010)\u001a\u00020\nH\u00d6\u0001R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\r\"\u0004\b\u001b\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\r\"\u0004\b\u001d\u0010\u000f\u00a8\u0006+"}, d2 = {"Lcom/cem/admodule/data/AdConfig;", "Lcom/cem/admodule/data/BaseResponseModel;", "enable", "", "openInterval", "", "adInterval", "bannerInterval", "nativeInterval", "appId", "", "(ZIIIILjava/lang/String;)V", "getAdInterval", "()I", "setAdInterval", "(I)V", "getAppId", "()Ljava/lang/String;", "setAppId", "(Ljava/lang/String;)V", "getBannerInterval", "setBannerInterval", "getEnable", "()Z", "setEnable", "(Z)V", "getNativeInterval", "setNativeInterval", "getOpenInterval", "setOpenInterval", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "", "hashCode", "toString", "Companion", "adModule_release"})
@androidx.annotation.Keep
public final class AdConfig extends com.cem.admodule.data.BaseResponseModel {
    private boolean enable;
    private int openInterval;
    private int adInterval;
    private int bannerInterval;
    private int nativeInterval;
    @org.jetbrains.annotations.Nullable
    private java.lang.String appId;
    @org.jetbrains.annotations.NotNull
    private static final com.squareup.moshi.JsonAdapter<com.cem.admodule.data.AdConfig> adapter = null;
    @org.jetbrains.annotations.NotNull
    private static final com.cem.admodule.data.AdConfig emptyValue = null;
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.data.AdConfig.Companion Companion = null;
    
    public AdConfig(@com.squareup.moshi.Json(name = "enable")
    boolean enable, @com.squareup.moshi.Json(name = "open_interval")
    int openInterval, @com.squareup.moshi.Json(name = "interval")
    int adInterval, @com.squareup.moshi.Json(name = "banner_interval")
    int bannerInterval, @com.squareup.moshi.Json(name = "native_interval")
    int nativeInterval, @com.squareup.moshi.Json(name = "app_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String appId) {
        super();
    }
    
    public final boolean getEnable() {
        return false;
    }
    
    public final void setEnable(boolean p0) {
    }
    
    public final int getOpenInterval() {
        return 0;
    }
    
    public final void setOpenInterval(int p0) {
    }
    
    public final int getAdInterval() {
        return 0;
    }
    
    public final void setAdInterval(int p0) {
    }
    
    public final int getBannerInterval() {
        return 0;
    }
    
    public final void setBannerInterval(int p0) {
    }
    
    public final int getNativeInterval() {
        return 0;
    }
    
    public final void setNativeInterval(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getAppId() {
        return null;
    }
    
    public final void setAppId(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    public AdConfig() {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int component5() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.cem.admodule.data.AdConfig copy(@com.squareup.moshi.Json(name = "enable")
    boolean enable, @com.squareup.moshi.Json(name = "open_interval")
    int openInterval, @com.squareup.moshi.Json(name = "interval")
    int adInterval, @com.squareup.moshi.Json(name = "banner_interval")
    int bannerInterval, @com.squareup.moshi.Json(name = "native_interval")
    int nativeInterval, @com.squareup.moshi.Json(name = "app_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String appId) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @kotlin.jvm.JvmStatic
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.data.AdConfig fromJson(@org.jetbrains.annotations.Nullable
    org.json.JSONObject config) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.data.AdConfig getEmptyValue() {
        return null;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @kotlin.jvm.JvmStatic
    @org.jetbrains.annotations.Nullable
    public static final org.json.JSONObject toJson(@org.jetbrains.annotations.Nullable
    com.cem.admodule.data.AdConfig adConfig) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String toString() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\n\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007J\u0014\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005H\u0007R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\u0002\u001a\u0004\b\b\u0010\t\u00a8\u0006\u000f"}, d2 = {"Lcom/cem/admodule/data/AdConfig$Companion;", "", "()V", "adapter", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/cem/admodule/data/AdConfig;", "emptyValue", "getEmptyValue$annotations", "getEmptyValue", "()Lcom/cem/admodule/data/AdConfig;", "fromJson", "config", "Lorg/json/JSONObject;", "toJson", "adConfig", "adModule_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.cem.admodule.data.AdConfig getEmptyValue() {
            return null;
        }
        
        @kotlin.jvm.JvmStatic
        @java.lang.Deprecated
        public static void getEmptyValue$annotations() {
        }
        
        @kotlin.jvm.JvmStatic
        @org.jetbrains.annotations.NotNull
        public final com.cem.admodule.data.AdConfig fromJson(@org.jetbrains.annotations.Nullable
        org.json.JSONObject config) {
            return null;
        }
        
        @kotlin.jvm.JvmStatic
        @org.jetbrains.annotations.Nullable
        public final org.json.JSONObject toJson(@org.jetbrains.annotations.Nullable
        com.cem.admodule.data.AdConfig adConfig) {
            return null;
        }
    }
}