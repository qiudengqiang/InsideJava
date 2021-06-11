package com.alphabethub.oop;

public class Sub extends Parent {
    @Override
    public Parent test() {
        //重写父类的方法，返回值不能大于父类的返回值
        return new Sub();
    }

    //重载属于一个类中的行为：只是函数的签名相同，签名包括方法名和参数列表。
    //注意：方法签名相同，返回值不同。编译会报错，这不是重载
    public void A(int a){ }

    public void A(int a, int b) { }


}
