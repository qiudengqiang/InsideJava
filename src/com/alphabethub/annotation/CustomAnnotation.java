package com.alphabethub.annotation;

import com.alphabethub.enumeration.Gender;

import java.lang.annotation.*;

@Documented//可以再javadoc中显示

@Target({//@Target注解标记另外的注解用于限制此注解可以应用哪种Java元素类型
        ElementType.TYPE,//接口、类、枚举
        ElementType.FIELD,//字段、枚举的常量
        ElementType.METHOD,//方法
        ElementType.PARAMETER,//方法参数
        ElementType.CONSTRUCTOR,//构造函数
        ElementType.LOCAL_VARIABLE,//构造函数
        ElementType.ANNOTATION_TYPE,//注解
        ElementType.PACKAGE,//包
        ElementType.TYPE_PARAMETER,//Java8 引进，类型参数声明，可以应用于类的泛型声明之处
        ElementType.TYPE_USE//Java8 引进，此类型包括类型声明和类型参数声明，是为了方便设计者进行类型检查。
        // ElementType.TYPE_USE包含了ElementType.TYPE和ElementType.TYPE_PARAMETER
        //如果一个注解没有指定@Target注解，则此注解可以用于除了TYPE_PARAMETER和TYPE_USE以外的任何地方。
})

//@Retention注解标记其他的注解用于指明标记的注解保留策略：
@Retention(RetentionPolicy.SOURCE)//表示注解会在编译时被丢弃
//@Retention(RetentionPolicy.CLASS):默认策略，表示注解会在编译后的class文件中存在，但是在运行时，不会被JVM保留。
//@Retention(RetentionPolicy.RUNTIME):表示不仅会在编译后的class文件中存在，而且在运行时保留，因此它们主要用于反射场景，可以通过getAnnotation方法获取。

@Inherited//@Inherited表示注解可以被继承，使用到该注解所标记的注解的类的所有子类，都继承当前注解

public @interface CustomAnnotation {
    //1.基本数据类型
    int value() default 1;
    //2.String类型
    String name() default "test";
    //3.枚举类型
    Gender gender() default Gender.WOMEN;
    //4.其他注解类型
    MyAnnotation mAnno();
    //5.数组类型
    String[] strs();
}
