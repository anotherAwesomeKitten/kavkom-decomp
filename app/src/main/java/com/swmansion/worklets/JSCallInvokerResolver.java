package com.swmansion.worklets;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.turbomodule.core.CallInvokerHolderImpl;

/* JADX INFO: loaded from: classes2.dex */
public class JSCallInvokerResolver {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static CallInvokerHolderImpl getJSCallInvokerHolder(ReactApplicationContext reactApplicationContext) {
        try {
            try {
                return (CallInvokerHolderImpl) reactApplicationContext.getClass().getMethod("getJSCallInvokerHolder", new Class[0]).invoke(reactApplicationContext, new Object[0]);
            } catch (Exception unused) {
                Object objInvoke = reactApplicationContext.getClass().getMethod("getCatalystInstance", new Class[0]).invoke(reactApplicationContext, new Object[0]);
                return (CallInvokerHolderImpl) objInvoke.getClass().getMethod("getJSCallInvokerHolder", new Class[0]).invoke(objInvoke, new Object[0]);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to get JSCallInvokerHolder", e);
        }
    }
}
