package org.michocko.javaclassgenerator;

import org.junit.Assert;
import org.junit.Test;

import lombok.val;

public class JavaFieldTest {

    @Test
    public void testToString_basic() {
        val annotation = new JavaField();
        annotation.setType("AwesomeType");
        annotation.setName("myField");

        Assert.assertEquals("private AwesomeType myField;", annotation.toString());
    }

    @Test
    public void testToString_withOneAnnotation() {
        val field = new JavaField();
        field.setType("AwesomeType");
        field.setName("myField");
        field.addAnnotation(new JavaAnnotation("FirstAnnotation"));

        Assert.assertEquals("@FirstAnnotation private AwesomeType myField;", field.toString());
    }

    @Test
    public void testToString_withTwoAnnotations() {
        val field = new JavaField();
        field.setType("AwesomeType");
        field.setName("myField");
        field.addAnnotation(new JavaAnnotation("FirstAnnotation"));
        field.addAnnotation(new JavaAnnotation("OtherAnnotation").addValue("", "42"));

        Assert.assertEquals("@FirstAnnotation @OtherAnnotation(42) private AwesomeType myField;", field.toString());
    }
}
