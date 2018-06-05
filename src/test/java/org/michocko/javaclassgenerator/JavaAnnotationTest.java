package org.michocko.javaclassgenerator;

import org.junit.Assert;
import org.junit.Test;

import lombok.val;

public class JavaAnnotationTest {

    @Test
    public void testToString_basic() {
        val annotation = new JavaAnnotation();
        annotation.setName("MyAnnotation");

        Assert.assertEquals("@MyAnnotation", annotation.toString());
    }

    @Test
    public void testToString_nullValueName() {
        val annotation = new JavaAnnotation();
        annotation.setName("MyAnnotation");
        annotation.addValue("", "17");

        Assert.assertEquals("@MyAnnotation(17)", annotation.toString());
    }

    @Test
    public void testToString_withOneIntValue() {
        val annotation = new JavaAnnotation();
        annotation.setName("MyAnnotation");
        annotation.addValue("val", "17");

        Assert.assertEquals("@MyAnnotation(val = 17)", annotation.toString());
    }

    @Test
    public void testToString_withOneStringValue() {
        val annotation = new JavaAnnotation();
        annotation.setName("MyAnnotation");
        annotation.addValue("val", "\"plop\"");

        Assert.assertEquals("@MyAnnotation(val = \"plop\")", annotation.toString());
    }

    @Test
    public void testToString_withOneClassValue() {
        val annotation = new JavaAnnotation();
        annotation.setName("MyAnnotation");
        annotation.addValue("val", "Yolo.class");

        Assert.assertEquals("@MyAnnotation(val = Yolo.class)", annotation.toString());
    }

    @Test
    public void testToString_withTwoValues() {
        val annotation = new JavaAnnotation();
        annotation.setName("MyAnnotation");
        annotation.addValue("val1", "\"plop\"");
        annotation.addValue("val2", "42");

        Assert.assertEquals("@MyAnnotation(val1 = \"plop\", val2 = 42)", annotation.toString());
    }
}
