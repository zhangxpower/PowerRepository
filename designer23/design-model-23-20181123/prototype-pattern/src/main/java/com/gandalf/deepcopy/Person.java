package com.gandalf.basecopy;

public class Person implements Cloneable{

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
     * 浅复制，只复制基本属性。
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public class Interest {
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
        public String toString() {
            return "Interest{" +
                    "interestName='" + interestName + '\'' +
                    '}';
        }
    }
}
