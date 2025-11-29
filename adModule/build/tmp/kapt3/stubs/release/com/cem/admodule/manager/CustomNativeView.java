package com.cem.admodule.manager;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nB\'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u00a2\u0006\u0002\u0010\fJ\u0010\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u000108J\u0010\u00109\u001a\u00020\t2\u0006\u0010:\u001a\u00020;H\u0002J\b\u0010<\u001a\u00020=H\u0002J\u0018\u0010>\u001a\u00020=2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010?\u001a\u00020\tH\u0002J\u001a\u0010@\u001a\u00020=2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u000e\u0010\u0011\u001a\u00020=2\u0006\u0010A\u001a\u00020\tJ\u000e\u0010B\u001a\u00020=2\u0006\u0010A\u001a\u00020\tR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010%\u001a\u0004\u0018\u00010\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0016\"\u0004\b\'\u0010\u0018R\u001c\u0010(\u001a\u0004\u0018\u00010)X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001c\u0010.\u001a\u0004\u0018\u00010\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0016\"\u0004\b0\u0010\u0018R\u000e\u00101\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u00102\u001a\u0004\u0018\u00010\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u0016\"\u0004\b4\u0010\u0018\u00a8\u0006C"}, d2 = {"Lcom/cem/admodule/manager/CustomNativeView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "background", "Landroid/view/View;", "getBackground", "()Landroid/view/View;", "setBackground", "(Landroid/view/View;)V", "callToActionView", "Landroid/widget/TextView;", "getCallToActionView", "()Landroid/widget/TextView;", "setCallToActionView", "(Landroid/widget/TextView;)V", "iconView", "Landroid/widget/ImageView;", "getIconView", "()Landroid/widget/ImageView;", "setIconView", "(Landroid/widget/ImageView;)V", "mediaView", "Lcom/google/android/gms/ads/nativead/MediaView;", "getMediaView", "()Lcom/google/android/gms/ads/nativead/MediaView;", "setMediaView", "(Lcom/google/android/gms/ads/nativead/MediaView;)V", "primaryView", "getPrimaryView", "setPrimaryView", "ratingBar", "Landroid/widget/RatingBar;", "getRatingBar", "()Landroid/widget/RatingBar;", "setRatingBar", "(Landroid/widget/RatingBar;)V", "secondaryView", "getSecondaryView", "setSecondaryView", "templateType", "tertiaryView", "getTertiaryView", "setTertiaryView", "adHasOnlyStore", "", "nativeAd", "Lcom/google/android/gms/ads/nativead/NativeAd;", "getTemplateType", "attributes", "Landroid/content/res/TypedArray;", "initIds", "", "initLayout", "layoutRes", "initView", "int", "setTemplateType", "adModule_release"})
public final class CustomNativeView extends android.widget.FrameLayout {
    private int templateType = 0;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView primaryView;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView secondaryView;
    @org.jetbrains.annotations.Nullable
    private android.widget.RatingBar ratingBar;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tertiaryView;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView iconView;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView callToActionView;
    @org.jetbrains.annotations.Nullable
    private com.google.android.gms.ads.nativead.MediaView mediaView;
    @org.jetbrains.annotations.Nullable
    private android.view.View background;
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getPrimaryView() {
        return null;
    }
    
    public final void setPrimaryView(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getSecondaryView() {
        return null;
    }
    
    public final void setSecondaryView(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.RatingBar getRatingBar() {
        return null;
    }
    
    public final void setRatingBar(@org.jetbrains.annotations.Nullable
    android.widget.RatingBar p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getTertiaryView() {
        return null;
    }
    
    public final void setTertiaryView(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.ImageView getIconView() {
        return null;
    }
    
    public final void setIconView(@org.jetbrains.annotations.Nullable
    android.widget.ImageView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.TextView getCallToActionView() {
        return null;
    }
    
    public final void setCallToActionView(@org.jetbrains.annotations.Nullable
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.google.android.gms.ads.nativead.MediaView getMediaView() {
        return null;
    }
    
    public final void setMediaView(@org.jetbrains.annotations.Nullable
    com.google.android.gms.ads.nativead.MediaView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.view.View getBackground() {
        return null;
    }
    
    public final void setBackground(@org.jetbrains.annotations.Nullable
    android.view.View p0) {
    }
    
    public CustomNativeView(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super(null);
    }
    
    public CustomNativeView(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    public CustomNativeView(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    public CustomNativeView(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    android.util.AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(null);
    }
    
    private final void initView(android.content.Context context, android.util.AttributeSet attrs) {
    }
    
    private final void initLayout(android.content.Context context, int layoutRes) {
    }
    
    private final int getTemplateType(android.content.res.TypedArray attributes) {
        return 0;
    }
    
    private final void initIds() {
    }
    
    public final boolean adHasOnlyStore(@org.jetbrains.annotations.Nullable
    com.google.android.gms.ads.nativead.NativeAd nativeAd) {
        return false;
    }
    
    public final void setTemplateType(int p0_52215) {
    }
    
    public final void setBackground(int p0_52215) {
    }
}