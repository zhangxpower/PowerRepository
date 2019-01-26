package com.gandalf;

import com.gandalf.Salable;

/**
 * 出版社
 */
public class PublishHouse implements Salable {

    @Override
    public void sale() {
        System.out.println("~~~~~卖书~~~~~");
    }
}
