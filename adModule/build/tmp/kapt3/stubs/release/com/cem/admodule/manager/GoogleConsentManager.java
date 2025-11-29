package com.cem.admodule.manager;

/**
 * Created by Hưng Nguyễn on 26/12/2023
 * Phone: 0335236374
 * Email: nguyenhunghung2806@gmail.com
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001f B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J,\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013J6\u0010\u0014\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013J \u0010\u0018\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0006\u0010\u001b\u001a\u00020\fJ\u0016\u0010\u001c\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b8F\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u0011\u0010\n\u001a\u00020\b8F\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\t\u00a8\u0006!"}, d2 = {"Lcom/cem/admodule/manager/GoogleConsentManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "consentInformation", "Lcom/google/android/ump/ConsentInformation;", "isPrivacyOptions", "", "()Z", "isRequestAds", "gatherConsent", "", "activity", "Landroid/app/Activity;", "underAge", "appId", "", "onConsentGatheringCompleteListener", "Lcom/cem/admodule/manager/GoogleConsentManager$OnConsentGatheringCompleteListener;", "gatherConsentDebugWithGeography", "geography", "", "hashedId", "requestConsentInfoUpdate", "params", "Lcom/google/android/ump/ConsentRequestParameters;", "resetConsent", "showPrivacyOptionsForm", "onConsentFormDismissedListener", "Lcom/google/android/ump/ConsentForm$OnConsentFormDismissedListener;", "Companion", "OnConsentGatheringCompleteListener", "adModule_release"})
public final class GoogleConsentManager {
    @org.jetbrains.annotations.NotNull
    private final com.google.android.ump.ConsentInformation consentInformation = null;
    @kotlin.jvm.Volatile
    @org.jetbrains.annotations.Nullable
    private static volatile com.cem.admodule.manager.GoogleConsentManager instance;
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.manager.GoogleConsentManager.Companion Companion = null;
    
    private GoogleConsentManager(android.content.Context context) {
        super();
    }
    
    public final boolean isRequestAds() {
        return false;
    }
    
    public final boolean isPrivacyOptions() {
        return false;
    }
    
    /**
     * Helper method to call the UMP SDK methods to request consent information and load/show a
     * consent form if necessary.
     */
    public final void gatherConsentDebugWithGeography(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, int geography, @org.jetbrains.annotations.Nullable
    java.lang.String hashedId, @org.jetbrains.annotations.Nullable
    java.lang.String appId, @org.jetbrains.annotations.NotNull
    com.cem.admodule.manager.GoogleConsentManager.OnConsentGatheringCompleteListener onConsentGatheringCompleteListener) {
    }
    
    public final void gatherConsent(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, boolean underAge, @org.jetbrains.annotations.Nullable
    java.lang.String appId, @org.jetbrains.annotations.NotNull
    com.cem.admodule.manager.GoogleConsentManager.OnConsentGatheringCompleteListener onConsentGatheringCompleteListener) {
    }
    
    private final void requestConsentInfoUpdate(android.app.Activity activity, com.google.android.ump.ConsentRequestParameters params, com.cem.admodule.manager.GoogleConsentManager.OnConsentGatheringCompleteListener onConsentGatheringCompleteListener) {
    }
    
    public final void showPrivacyOptionsForm(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.NotNull
    com.google.android.ump.ConsentForm.OnConsentFormDismissedListener onConsentFormDismissedListener) {
    }
    
    public final void resetConsent() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/cem/admodule/manager/GoogleConsentManager$Companion;", "", "()V", "instance", "Lcom/cem/admodule/manager/GoogleConsentManager;", "getInstance", "context", "Landroid/content/Context;", "adModule_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.cem.admodule.manager.GoogleConsentManager getInstance(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00e6\u0080\u0001\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/cem/admodule/manager/GoogleConsentManager$OnConsentGatheringCompleteListener;", "", "consentGatheringComplete", "", "error", "Lcom/google/android/ump/FormError;", "adModule_release"})
    public static abstract interface OnConsentGatheringCompleteListener {
        
        public abstract void consentGatheringComplete(@org.jetbrains.annotations.Nullable
        com.google.android.ump.FormError error);
    }
}