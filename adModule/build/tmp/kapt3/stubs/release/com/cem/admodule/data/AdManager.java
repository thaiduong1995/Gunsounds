package com.cem.admodule.data;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 +2\u00020\u0001:\u0001+B/\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u001c\b\u0003\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\tJ\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u001d\u0010#\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0018\u00010\u0005H\u00c6\u0003J3\u0010$\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u001c\b\u0003\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010%\u001a\u00020\u00192\b\u0010&\u001a\u0004\u0018\u00010\'H\u00d6\u0003J\t\u0010(\u001a\u00020)H\u00d6\u0001J\t\u0010*\u001a\u00020\u0006H\u00d6\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R.\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0018\u00010\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0011R$\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u00198F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u001f\u0010\u0011R\u0011\u0010 \u001a\u00020\u000f8F\u00a2\u0006\u0006\u001a\u0004\b!\u0010\u0011\u00a8\u0006,"}, d2 = {"Lcom/cem/admodule/data/AdManager;", "Lcom/cem/admodule/data/BaseResponseModel;", "adConfig", "Lcom/cem/admodule/data/AdConfig;", "adUnitList", "", "", "", "Lcom/cem/admodule/data/AdUnitItem;", "(Lcom/cem/admodule/data/AdConfig;Ljava/util/Map;)V", "getAdConfig", "()Lcom/cem/admodule/data/AdConfig;", "setAdConfig", "(Lcom/cem/admodule/data/AdConfig;)V", "adInterval", "", "getAdInterval", "()J", "getAdUnitList", "()Ljava/util/Map;", "setAdUnitList", "(Ljava/util/Map;)V", "bannerInterval", "getBannerInterval", "value", "", "isEnable", "()Z", "setEnable", "(Z)V", "nativeInterval", "getNativeInterval", "openInterval", "getOpenInterval", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "Companion", "adModule_release"})
@androidx.annotation.Keep
public final class AdManager extends com.cem.admodule.data.BaseResponseModel {
    @org.jetbrains.annotations.Nullable
    private com.cem.admodule.data.AdConfig adConfig;
    @org.jetbrains.annotations.Nullable
    private java.util.Map<java.lang.String, ? extends java.util.List<com.cem.admodule.data.AdUnitItem>> adUnitList;
    private static final com.squareup.moshi.JsonAdapter<com.cem.admodule.data.AdManager> adapter = null;
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.data.AdManager.Companion Companion = null;
    
    public AdManager(@com.squareup.moshi.Json(name = "configs")
    @org.jetbrains.annotations.Nullable
    com.cem.admodule.data.AdConfig adConfig, @com.squareup.moshi.Json(name = "units")
    @org.jetbrains.annotations.Nullable
    java.util.Map<java.lang.String, ? extends java.util.List<com.cem.admodule.data.AdUnitItem>> adUnitList) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.cem.admodule.data.AdConfig getAdConfig() {
        return null;
    }
    
    public final void setAdConfig(@org.jetbrains.annotations.Nullable
    com.cem.admodule.data.AdConfig p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.Map<java.lang.String, java.util.List<com.cem.admodule.data.AdUnitItem>> getAdUnitList() {
        return null;
    }
    
    public final void setAdUnitList(@org.jetbrains.annotations.Nullable
    java.util.Map<java.lang.String, ? extends java.util.List<com.cem.admodule.data.AdUnitItem>> p0) {
    }
    
    public final boolean isEnable() {
        return false;
    }
    
    public final void setEnable(boolean value) {
    }
    
    public final long getAdInterval() {
        return 0L;
    }
    
    public final long getOpenInterval() {
        return 0L;
    }
    
    public final long getBannerInterval() {
        return 0L;
    }
    
    public final long getNativeInterval() {
        return 0L;
    }
    
    public AdManager() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.cem.admodule.data.AdConfig component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.Map<java.lang.String, java.util.List<com.cem.admodule.data.AdUnitItem>> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.cem.admodule.data.AdManager copy(@com.squareup.moshi.Json(name = "configs")
    @org.jetbrains.annotations.Nullable
    com.cem.admodule.data.AdConfig adConfig, @com.squareup.moshi.Json(name = "units")
    @org.jetbrains.annotations.Nullable
    java.util.Map<java.lang.String, ? extends java.util.List<com.cem.admodule.data.AdUnitItem>> adUnitList) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @kotlin.jvm.JvmStatic
    @org.jetbrains.annotations.Nullable
    public static final com.cem.admodule.data.AdManager fromJson(@org.jetbrains.annotations.Nullable
    java.lang.String json) {
        return null;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @kotlin.jvm.JvmStatic
    @org.jetbrains.annotations.Nullable
    public static final org.json.JSONObject toJson(@org.jetbrains.annotations.Nullable
    com.cem.admodule.data.AdManager adManager) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String toString() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J\u0014\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u0007R2\u0010\u0003\u001a&\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005 \u0006*\u0012\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/cem/admodule/data/AdManager$Companion;", "", "()V", "adapter", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/cem/admodule/data/AdManager;", "kotlin.jvm.PlatformType", "fromJson", "json", "", "toJson", "Lorg/json/JSONObject;", "adManager", "adModule_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @kotlin.jvm.JvmStatic
        @org.jetbrains.annotations.Nullable
        public final com.cem.admodule.data.AdManager fromJson(@org.jetbrains.annotations.Nullable
        java.lang.String json) {
            return null;
        }
        
        @kotlin.jvm.JvmStatic
        @org.jetbrains.annotations.Nullable
        public final org.json.JSONObject toJson(@org.jetbrains.annotations.Nullable
        com.cem.admodule.data.AdManager adManager) {
            return null;
        }
    }
}