package com.gandalf.staticproxy;

import com.gandalf.Salable;

/**
 * 书店
 */
public class BookstoreProxy implements Salable {

    private Salable delegate;

    public BookstoreProxy(Salable publishHouse) {
        this.delegate = publishHouse;
    }

    @Override
    public void sale() {
        System.out.println("[static] 卖书前");
        delegate.sale();
        System.out.println("[static] 卖书后");
    }
}
