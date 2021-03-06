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

public class ColorRGB implements java.lang.Cloneable, java.io.Serializable
{
    public int redValue;

    public int greenValue;

    public int blueValue;

    public ColorRGB()
    {
    }

    public ColorRGB(int redValue, int greenValue, int blueValue)
    {
        this.redValue = redValue;
        this.greenValue = greenValue;
        this.blueValue = blueValue;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        ColorRGB _r = null;
        if(rhs instanceof ColorRGB)
        {
            _r = (ColorRGB)rhs;
        }

        if(_r != null)
        {
            if(redValue != _r.redValue)
            {
                return false;
            }
            if(greenValue != _r.greenValue)
            {
                return false;
            }
            if(blueValue != _r.blueValue)
            {
                return false;
            }

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 5381;
        __h = IceInternal.HashUtil.hashAdd(__h, "::VisionModule::ColorRGB");
        __h = IceInternal.HashUtil.hashAdd(__h, redValue);
        __h = IceInternal.HashUtil.hashAdd(__h, greenValue);
        __h = IceInternal.HashUtil.hashAdd(__h, blueValue);
        return __h;
    }

    public ColorRGB
    clone()
    {
        ColorRGB c = null;
        try
        {
            c = (ColorRGB)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeInt(redValue);
        __os.writeInt(greenValue);
        __os.writeInt(blueValue);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        redValue = __is.readInt();
        greenValue = __is.readInt();
        blueValue = __is.readInt();
    }

    static public void
    __write(IceInternal.BasicStream __os, ColorRGB __v)
    {
        if(__v == null)
        {
            __nullMarshalValue.__write(__os);
        }
        else
        {
            __v.__write(__os);
        }
    }

    static public ColorRGB
    __read(IceInternal.BasicStream __is, ColorRGB __v)
    {
        if(__v == null)
        {
             __v = new ColorRGB();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final ColorRGB __nullMarshalValue = new ColorRGB();

    public static final long serialVersionUID = -1130718811L;
}
