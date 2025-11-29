package com.cem.admodule.inter;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007H&J\u0015\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00028\u0000H&\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/cem/admodule/inter/Callback;", "T", "", "onFailure", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "data", "(Ljava/lang/Object;)V", "adModule_release"})
public abstract interface Callback<T extends java.lang.Object> {
    
    public abstract void onSuccess(T data);
    
    public abstract void onFailure(@org.jetbrains.annotations.Nullable
    java.lang.Exception e);
}