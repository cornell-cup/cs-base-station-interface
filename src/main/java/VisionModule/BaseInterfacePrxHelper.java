// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.3
//
// <auto-generated>
//
// Generated from file `VisionModule.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package VisionModule;

/**
 * Provides type-specific helper functions.
 **/
public final class BaseInterfacePrxHelper extends Ice.ObjectPrxHelperBase implements BaseInterfacePrx
{
    private static final String __ping_name = "ping";

    public double ping()
    {
        return ping(null, false);
    }

    public double ping(java.util.Map<String, String> __ctx)
    {
        return ping(__ctx, true);
    }

    private double ping(java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__ping_name);
        return end_ping(begin_ping(__ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_ping()
    {
        return begin_ping(null, false, false, null);
    }

    public Ice.AsyncResult begin_ping(java.util.Map<String, String> __ctx)
    {
        return begin_ping(__ctx, true, false, null);
    }

    public Ice.AsyncResult begin_ping(Ice.Callback __cb)
    {
        return begin_ping(null, false, false, __cb);
    }

    public Ice.AsyncResult begin_ping(java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_ping(__ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_ping(Callback_BaseInterface_ping __cb)
    {
        return begin_ping(null, false, false, __cb);
    }

    public Ice.AsyncResult begin_ping(java.util.Map<String, String> __ctx, Callback_BaseInterface_ping __cb)
    {
        return begin_ping(__ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_ping(IceInternal.Functional_DoubleCallback __responseCb, 
                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_ping(null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_ping(IceInternal.Functional_DoubleCallback __responseCb, 
                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                      IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_ping(null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_ping(java.util.Map<String, String> __ctx, 
                                      IceInternal.Functional_DoubleCallback __responseCb, 
                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_ping(__ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_ping(java.util.Map<String, String> __ctx, 
                                      IceInternal.Functional_DoubleCallback __responseCb, 
                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                      IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_ping(__ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_ping(java.util.Map<String, String> __ctx, 
                                       boolean __explicitCtx, 
                                       boolean __synchronous, 
                                       IceInternal.Functional_DoubleCallback __responseCb, 
                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                       IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_ping(__ctx, __explicitCtx, __synchronous, 
                          new IceInternal.Functional_TwowayCallbackDouble(__responseCb, __exceptionCb, __sentCb)
                              {
                                  public final void __completed(Ice.AsyncResult __result)
                                  {
                                      BaseInterfacePrxHelper.__ping_completed(this, __result);
                                  }
                              });
    }

    private Ice.AsyncResult begin_ping(java.util.Map<String, String> __ctx, 
                                       boolean __explicitCtx, 
                                       boolean __synchronous, 
                                       IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__ping_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__ping_name, __cb);
        try
        {
            __result.prepare(__ping_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            __result.writeEmptyParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public double end_ping(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __ping_name);
        try
        {
            if(!__result.__wait())
            {
                try
                {
                    __result.throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.startReadParams();
            double __ret;
            __ret = __is.readDouble();
            __result.endReadParams();
            return __ret;
        }
        finally
        {
            if(__result != null)
            {
                __result.cacheMessageBuffers();
            }
        }
    }

    static public void __ping_completed(Ice.TwowayCallbackDouble __cb, Ice.AsyncResult __result)
    {
        BaseInterfacePrx __proxy = (BaseInterfacePrx)__result.getProxy();
        double __ret = 0.0;
        try
        {
            __ret = __proxy.end_ping(__result);
        }
        catch(Ice.LocalException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.SystemException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        __cb.response(__ret);
    }

    private static final String __update_name = "update";

    public int update(Blob[] data)
    {
        return update(data, null, false);
    }

    public int update(Blob[] data, java.util.Map<String, String> __ctx)
    {
        return update(data, __ctx, true);
    }

    private int update(Blob[] data, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__update_name);
        return end_update(begin_update(data, __ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_update(Blob[] data)
    {
        return begin_update(data, null, false, false, null);
    }

    public Ice.AsyncResult begin_update(Blob[] data, java.util.Map<String, String> __ctx)
    {
        return begin_update(data, __ctx, true, false, null);
    }

    public Ice.AsyncResult begin_update(Blob[] data, Ice.Callback __cb)
    {
        return begin_update(data, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_update(Blob[] data, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_update(data, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_update(Blob[] data, Callback_BaseInterface_update __cb)
    {
        return begin_update(data, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_update(Blob[] data, java.util.Map<String, String> __ctx, Callback_BaseInterface_update __cb)
    {
        return begin_update(data, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_update(Blob[] data, 
                                        IceInternal.Functional_IntCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_update(data, null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_update(Blob[] data, 
                                        IceInternal.Functional_IntCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                        IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_update(data, null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_update(Blob[] data, 
                                        java.util.Map<String, String> __ctx, 
                                        IceInternal.Functional_IntCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_update(data, __ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_update(Blob[] data, 
                                        java.util.Map<String, String> __ctx, 
                                        IceInternal.Functional_IntCallback __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                        IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_update(data, __ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_update(Blob[] data, 
                                         java.util.Map<String, String> __ctx, 
                                         boolean __explicitCtx, 
                                         boolean __synchronous, 
                                         IceInternal.Functional_IntCallback __responseCb, 
                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                         IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_update(data, __ctx, __explicitCtx, __synchronous, 
                            new IceInternal.Functional_TwowayCallbackInt(__responseCb, __exceptionCb, __sentCb)
                                {
                                    public final void __completed(Ice.AsyncResult __result)
                                    {
                                        BaseInterfacePrxHelper.__update_completed(this, __result);
                                    }
                                });
    }

    private Ice.AsyncResult begin_update(Blob[] data, 
                                         java.util.Map<String, String> __ctx, 
                                         boolean __explicitCtx, 
                                         boolean __synchronous, 
                                         IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__update_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__update_name, __cb);
        try
        {
            __result.prepare(__update_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            IceInternal.BasicStream __os = __result.startWriteParams(Ice.FormatType.DefaultFormat);
            VisionDataHelper.write(__os, data);
            __result.endWriteParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public int end_update(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __update_name);
        try
        {
            if(!__result.__wait())
            {
                try
                {
                    __result.throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.startReadParams();
            int __ret;
            __ret = __is.readInt();
            __result.endReadParams();
            return __ret;
        }
        finally
        {
            if(__result != null)
            {
                __result.cacheMessageBuffers();
            }
        }
    }

    static public void __update_completed(Ice.TwowayCallbackInt __cb, Ice.AsyncResult __result)
    {
        BaseInterfacePrx __proxy = (BaseInterfacePrx)__result.getProxy();
        int __ret = 0;
        try
        {
            __ret = __proxy.end_update(__result);
        }
        catch(Ice.LocalException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.SystemException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        __cb.response(__ret);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static BaseInterfacePrx checkedCast(Ice.ObjectPrx __obj)
    {
        return checkedCastImpl(__obj, ice_staticId(), BaseInterfacePrx.class, BaseInterfacePrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __ctx The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static BaseInterfacePrx checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __ctx, ice_staticId(), BaseInterfacePrx.class, BaseInterfacePrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static BaseInterfacePrx checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return checkedCastImpl(__obj, __facet, ice_staticId(), BaseInterfacePrx.class, BaseInterfacePrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @param __ctx The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static BaseInterfacePrx checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __facet, __ctx, ice_staticId(), BaseInterfacePrx.class, BaseInterfacePrxHelper.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param __obj The untyped proxy.
     * @return A proxy for this type.
     **/
    public static BaseInterfacePrx uncheckedCast(Ice.ObjectPrx __obj)
    {
        return uncheckedCastImpl(__obj, BaseInterfacePrx.class, BaseInterfacePrxHelper.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    public static BaseInterfacePrx uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return uncheckedCastImpl(__obj, __facet, BaseInterfacePrx.class, BaseInterfacePrxHelper.class);
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::VisionModule::BaseInterface"
    };

    /**
     * Provides the Slice type ID of this type.
     * @return The Slice type ID.
     **/
    public static String ice_staticId()
    {
        return __ids[1];
    }

    public static void __write(IceInternal.BasicStream __os, BaseInterfacePrx v)
    {
        __os.writeProxy(v);
    }

    public static BaseInterfacePrx __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            BaseInterfacePrxHelper result = new BaseInterfacePrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }

    public static final long serialVersionUID = 0L;
}
