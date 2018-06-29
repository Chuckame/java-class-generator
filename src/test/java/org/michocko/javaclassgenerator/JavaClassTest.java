package org.michocko.javaclassgenerator;

import org.junit.Assert;
import org.junit.Test;
import org.michocko.javaclassgenerator.JavaClass.ModifierEnum;

import lombok.val;

public class JavaClassTest {

    @Test
    public void testToString_all() {
        val expected = String.format("package my.package;%n%n" //
                + "import one.import.OtherType;%n%n" //
                + "@Annotation(\"plop\")%n" //
                + "public class AwesomeClass extends ExtendedClass implements ImplementedInterface1, ImpltedItf2 {%n%n" //
                + "    private String champ1;%n%n" //
                + "    @OneAnnotation(64) private OtherType champ42;%n%n" //
                + "}%n");
        val javaClass = new JavaClass();
        javaClass.setPackageName("my.package");
        javaClass.addImport("one.import.OtherType");
        javaClass.addAnnotation(new JavaAnnotation("Annotation").addValue("", "\"plop\""));
        javaClass.addModifier(ModifierEnum.PUBLIC);
        javaClass.setName("AwesomeClass");
        javaClass.setExtendedClass("ExtendedClass");
        javaClass.addImplementedInterfaces("ImplementedInterface1");
        javaClass.addImplementedInterfaces("ImpltedItf2");
        javaClass.addField(new JavaField("String", "champ1"));
        javaClass.addField(new JavaField("OtherType", "champ42").addAnnotation(new JavaAnnotation("OneAnnotation").addValue("", "64")));

        Assert.assertEquals(expected, javaClass.toString());
    }

    @Test
    public void testToString_withoutImport() {
        val expected = String.format("package my.package;%n%n" //
                + "@Annotation(\"plop\")%n" //
                + "public class AwesomeClass extends ExtendedClass implements ImplementedInterface1, ImpltedItf2 {%n%n" //
                + "    private String champ1;%n%n" //
                + "    @OneAnnotation(64) private OtherType champ42;%n%n" //
                + "}%n");
        val javaClass = new JavaClass();
        javaClass.setPackageName("my.package");
        javaClass.addAnnotation(new JavaAnnotation("Annotation").addValue("", "\"plop\""));
        javaClass.addModifier(ModifierEnum.PUBLIC);
        javaClass.setName("AwesomeClass");
        javaClass.setExtendedClass("ExtendedClass");
        javaClass.addImplementedInterfaces("ImplementedInterface1");
        javaClass.addImplementedInterfaces("ImpltedItf2");
        javaClass.addField(new JavaField("String", "champ1"));
        javaClass.addField(new JavaField("OtherType", "champ42").addAnnotation(new JavaAnnotation("OneAnnotation").addValue("", "64")));

        Assert.assertEquals(expected, javaClass.toString());
    }

    @Test
    public void testToString_javaBeanDefaults() {
        val expected = String.format("import lombok.Data;%n" //
                + "import lombok.NoArgsConstructor;%n" //
                + "import lombok.AllArgsConstructor;%n%n" //
                + "@Data%n" //
                + "@NoArgsConstructor%n" //
                + "@AllArgsConstructor%n" //
                + "public class AwesomeClass {%n" //
                + "}%n");
        val javaClass = new JavaClass();
        javaClass.setName("AwesomeClass");
        javaClass.addJavaBeanDefaults();

        Assert.assertEquals(expected, javaClass.toString());
    }

    @Test
    public void testToString_withoutPackage() {
        val expected = String.format("import one.import.OtherType;%n%n" //
                + "@Annotation(\"plop\")%n" //
                + "public class AwesomeClass extends ExtendedClass implements ImplementedInterface1, ImpltedItf2 {%n%n" //
                + "    private String champ1;%n%n" //
                + "    @OneAnnotation(64) private OtherType champ42;%n%n" //
                + "}%n");
        val javaClass = new JavaClass();
        javaClass.addImport("one.import.OtherType");
        javaClass.addAnnotation(new JavaAnnotation("Annotation").addValue("", "\"plop\""));
        javaClass.addModifier(ModifierEnum.PUBLIC);
        javaClass.setName("AwesomeClass");
        javaClass.setExtendedClass("ExtendedClass");
        javaClass.addImplementedInterfaces("ImplementedInterface1");
        javaClass.addImplementedInterfaces("ImpltedItf2");
        javaClass.addField(new JavaField("String", "champ1"));
        javaClass.addField(new JavaField("OtherType", "champ42").addAnnotation(new JavaAnnotation("OneAnnotation").addValue("", "64")));

        Assert.assertEquals(expected, javaClass.toString());
    }

    @Test
    public void testToString_withoutModifiers() {
        val expected = String.format("class AwesomeClass {%n" //
                + "}%n");
        val javaClass = new JavaClass();
        javaClass.setName("AwesomeClass");

        Assert.assertEquals(expected, javaClass.toString());
    }
}
