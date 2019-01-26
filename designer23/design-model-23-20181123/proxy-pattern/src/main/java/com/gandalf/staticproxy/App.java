package com.gandalf.staticproxy;

import com.gandalf.PublishHouse;
import com.gandalf.Salable;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Salable proxy = new BookstoreProxy(new PublishHouse());
        proxy.sale();
    }
}
