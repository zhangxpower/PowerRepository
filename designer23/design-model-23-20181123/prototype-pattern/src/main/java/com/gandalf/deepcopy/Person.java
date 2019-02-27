package com.gandalf.deepcopy;

import java.io.*;

public class Person implements Cloneable, Serializable{

    private String name;

    private Boolean state;

    public Interest interest;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Interest getInterest() {
        return interest;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", state=" + state +
                ", interest=" + interest +
                '}';
    }

    /**
     * 深度复制，复制基本属性和引用属性（引用对象需要单独clone）。
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Interest interest = (Interest)this.interest.clone();
        Person p = (Person)super.clone();
        p.interest = interest;
        return p;
    }

    public Person deepCloneByIO() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.writeObject(this);
        byte[] objectData = out.toByteArray();
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(objectData));
        Person p = (Person)objectInputStream.readObject();
        return p;
    }

    public class Interest implements Cloneable, Serializable {
        private String interestName;

        public Interest(String interestName) {
            this.interestName = interestName;
        }

        public String getInterestName() {
            return interestName;
        }

        public void setInterestName(String interestName) {
            this.interestName = interestName;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return "Interest{" +
                    "interestName='" + interestName + '\'' +
                    '}';
        }
    }
}
