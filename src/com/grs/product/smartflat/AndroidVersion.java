package com.grs.product.smartflat;

public class AndroidVersion
{
    public static final int VERSION = getVersion();
    private static int getVersion()
    {
        try 
        {
            return Integer.parseInt(android.os.Build.VERSION.SDK);
        } 
        catch (Exception e) 
        {
        	e.printStackTrace();
            return 1;
        }
    }

}
