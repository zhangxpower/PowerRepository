package com.gandalf;

public class EnumSingleton {

    private EnumSingleton() {}

    public static EnumSingleton getInstance(){
        return ESGetter.INSTANCE.singleton;
    }

    private enum ESGetter {
        INSTANCE;
        private EnumSingleton singleton = null;

        ESGetter() {
            singleton = new EnumSingleton();
        }

        public EnumSingleton getInstance() {
            return singleton;
        }
    }
}
