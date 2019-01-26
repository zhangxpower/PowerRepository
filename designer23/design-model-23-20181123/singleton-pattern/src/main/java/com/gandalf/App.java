package com.gandalf;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        final EnumSingleton instance = EnumSingleton.getInstance();
        System.out.println(instance.toString());
    }
}
