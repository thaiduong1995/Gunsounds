package com.cem.admodule.manager;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 72\u00020\u0001:\u00017B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J#\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J#\u0010\u0015\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0012\u001a\u00020\u0013J\"\u0010\u001c\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0006\u0010\u001e\u001a\u00020\u0013H\u0002J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\u001e\u001a\u00020\u0013H\u0002J\u0010\u0010!\u001a\u00020 2\u0006\u0010\u001e\u001a\u00020\u0013H\u0002J\u0010\u0010\"\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u001b\u0010#\u001a\u00020\u00172\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010$J\u0011\u0010%\u001a\u00020\u000fH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010&J\u001b\u0010\'\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010$J\u0011\u0010(\u001a\u00020\u000fH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010&J\u0019\u0010)\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ\u0011\u0010*\u001a\u00020\u0017H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010&J#\u0010+\u001a\u00020\u00172\b\u0010\u0010\u001a\u0004\u0018\u00010,2\u0006\u0010\u001e\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010-J#\u0010.\u001a\u00020\u00172\b\u0010\u0010\u001a\u0004\u0018\u00010,2\u0006\u0010\u001e\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010-J#\u0010/\u001a\u00020\u00172\b\u0010\u0010\u001a\u0004\u0018\u00010,2\u0006\u0010\u001e\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010-J\u001b\u00100\u001a\u00020\u00172\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010$J\u000e\u00101\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u0006J\u001d\u00102\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010403*\u00020\u0004H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00105J\u0017\u00106\u001a\u0004\u0018\u000104*\u00020\u0004H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00105R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00068"}, d2 = {"Lcom/cem/admodule/manager/PurchaseManager;", "", "()V", "billingClient", "Lcom/android/billingclient/api/BillingClient;", "callback", "Lcom/cem/admodule/inter/PurchaseCallback;", "purchasesUpdatedListener", "Lcom/android/billingclient/api/PurchasesUpdatedListener;", "skuDetailsListIAP", "", "Lcom/android/billingclient/api/ProductDetails;", "skuDetailsListNIAP", "skuDetailsListSUB", "checkPurchaseItemFirst", "", "context", "Landroid/content/Context;", "productId", "", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkPurchaseSubFirst", "consumeItem", "", "purchase", "Lcom/android/billingclient/api/Purchase;", "(Lcom/android/billingclient/api/Purchase;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPrice", "getProductDetails", "skuDetailsList", "product", "getProductINAPP", "Lcom/android/billingclient/api/QueryProductDetailsParams$Product;", "getProductSUB", "handlePurchase", "initBilling", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isPurchased", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isRemovedAds", "isSubscribed", "noConsume", "processPurchases", "purchaseItem", "Landroid/app/Activity;", "(Landroid/app/Activity;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "purchasePremium", "purchaseSub", "restorePurchases", "setCallback", "startConnect", "Lkotlinx/coroutines/flow/Flow;", "Lcom/android/billingclient/api/BillingResult;", "(Lcom/android/billingclient/api/BillingClient;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startConnectSuspend", "Companion", "adModule_release"})
public final class PurchaseManager {
    @org.jetbrains.annotations.Nullable
    private com.cem.admodule.inter.PurchaseCallback callback;
    @org.jetbrains.annotations.Nullable
    private com.android.billingclient.api.BillingClient billingClient;
    @org.jetbrains.annotations.Nullable
    private java.util.List<com.android.billingclient.api.ProductDetails> skuDetailsListIAP;
    @org.jetbrains.annotations.Nullable
    private java.util.List<com.android.billingclient.api.ProductDetails> skuDetailsListSUB;
    @org.jetbrains.annotations.Nullable
    private java.util.List<com.android.billingclient.api.ProductDetails> skuDetailsListNIAP;
    @org.jetbrains.annotations.NotNull
    private final com.android.billingclient.api.PurchasesUpdatedListener purchasesUpdatedListener = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = null;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ID_PURCHASE = "com.aesthetic.iconpack.iconchanger.premium";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PURCHASE_YEAR = "com.aesthetic.iconpack.yearly";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PURCHASE_MONTH = "com.aesthetic.iconpack.monthly";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PURCHASE_WEEK = "com.aesthetic.iconpack.weekly";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ONE_CREDITS = "com.aesthetic.iconpack.iconchanger.5coins";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String TWO_CREDITS = "com.aesthetic.iconpack.iconchanger.10coins";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String THREE_CREDITS = "com.aesthetic.iconpack.iconchanger.15coins";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String FOUR_CREDITS = "com.aesthetic.iconpack.iconchanger.20coins";
    @org.jetbrains.annotations.Nullable
    private static com.cem.admodule.manager.PurchaseManager instance;
    @org.jetbrains.annotations.NotNull
    public static final com.cem.admodule.manager.PurchaseManager.Companion Companion = null;
    
    private PurchaseManager() {
        super();
    }
    
    public final void setCallback(@org.jetbrains.annotations.NotNull
    com.cem.admodule.inter.PurchaseCallback callback) {
    }
    
    private final java.lang.Object initBilling(android.content.Context context, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object startConnect(com.android.billingclient.api.BillingClient $this$startConnect, kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<com.android.billingclient.api.BillingResult>> $completion) {
        return null;
    }
    
    private final java.lang.Object startConnectSuspend(com.android.billingclient.api.BillingClient $this$startConnectSuspend, kotlin.coroutines.Continuation<? super com.android.billingclient.api.BillingResult> $completion) {
        return null;
    }
    
    private final java.lang.Object processPurchases(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final com.android.billingclient.api.QueryProductDetailsParams.Product getProductINAPP(java.lang.String product) {
        return null;
    }
    
    private final com.android.billingclient.api.QueryProductDetailsParams.Product getProductSUB(java.lang.String product) {
        return null;
    }
    
    private final void handlePurchase(com.android.billingclient.api.Purchase purchase) {
    }
    
    private final java.lang.Object consumeItem(com.android.billingclient.api.Purchase purchase, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object noConsume(com.android.billingclient.api.Purchase purchase, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object isRemovedAds(@org.jetbrains.annotations.Nullable
    android.content.Context context, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    private final java.lang.Object isPurchased(kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    private final java.lang.Object isSubscribed(kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object purchasePremium(@org.jetbrains.annotations.Nullable
    android.app.Activity context, @org.jetbrains.annotations.NotNull
    java.lang.String product, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object purchaseItem(@org.jetbrains.annotations.Nullable
    android.app.Activity context, @org.jetbrains.annotations.NotNull
    java.lang.String product, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object purchaseSub(@org.jetbrains.annotations.Nullable
    android.app.Activity context, @org.jetbrains.annotations.NotNull
    java.lang.String product, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object restorePurchases(@org.jetbrains.annotations.Nullable
    android.content.Context context, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object checkPurchaseItemFirst(@org.jetbrains.annotations.Nullable
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String productId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object checkPurchaseSubFirst(@org.jetbrains.annotations.Nullable
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String productId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    private final com.android.billingclient.api.ProductDetails getProductDetails(java.util.List<com.android.billingclient.api.ProductDetails> skuDetailsList, java.lang.String product) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPrice(@org.jetbrains.annotations.NotNull
    java.lang.String productId) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R$\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u00108F@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0014"}, d2 = {"Lcom/cem/admodule/manager/PurchaseManager$Companion;", "", "()V", "FOUR_CREDITS", "", "ID_PURCHASE", "ONE_CREDITS", "PURCHASE_MONTH", "PURCHASE_WEEK", "PURCHASE_YEAR", "TAG", "getTAG", "()Ljava/lang/String;", "THREE_CREDITS", "TWO_CREDITS", "<set-?>", "Lcom/cem/admodule/manager/PurchaseManager;", "instance", "getInstance", "()Lcom/cem/admodule/manager/PurchaseManager;", "adModule_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getTAG() {
            return null;
        }
        
        @kotlin.jvm.Synchronized
        @org.jetbrains.annotations.Nullable
        public final synchronized com.cem.admodule.manager.PurchaseManager getInstance() {
            return null;
        }
    }
}