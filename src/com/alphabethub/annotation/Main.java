package com.alphabethub.annotation;

import com.alphabethub.enumeration.Gender;

@CustomAnnotation(value = 2, name = "techbird", gender = Gender.MEN, mAnno = @MyAnnotation, strs = "c")
public class Main {

    @CustomAnnotation(value = 1, mAnno = @MyAnnotation, strs = {"a", "b"})
    public void test() {

    }
}
