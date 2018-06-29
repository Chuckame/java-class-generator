package org.michocko.javaclassgenerator;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Représente une classe java.<br>
 * {@link #toString()} retourne le code Java correspondant.<br>
 * Génère du code sous la forme suivante :
 * 
 * <pre>
 * package le.nom.du.package;
 * 
 * &#64;Annotation("plop")
 * public class AwesomeClass extends ExtendedClass implements ImplementedInterface1, ImpltedItf2 {
 *    &#64;Annotation private String champ1;
 *    &#64;Annotation(64) private String champ42;
 * }
 * </pre>
 * 
 * @author michocko
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "packageName", "name" })
public class JavaClass {
    private static final String INDENTATION = "    ";

    private final Set<ModifierEnum> modifiers = new LinkedHashSet<>();
    private final Set<String> implementedInterfaces = new LinkedHashSet<>();
    private final Set<String> imports = new LinkedHashSet<>();
    private final Set<JavaAnnotation> annotations = new LinkedHashSet<>();
    private final Set<JavaField> fields = new LinkedHashSet<>();

    private String packageName;
    private String name;
    private String extendedClass;

    /**
     * Ajoute le modifier "public" et ajoute les annotations &#64;Data, &#64;NoArgsConstructor et &#64;AllArgsConstructor de lombok, et leurs
     * imports correspondants pour faire de cette cette classe un "Java Bean" (un objet ne contenant pas de méthodes, juste des
     * getters/setters).
     * 
     * @return l'instance actuelle de {@link JavaClass}
     */
    public JavaClass addJavaBeanDefaults() {
        addModifier(ModifierEnum.PUBLIC);
        addAnnotation(new JavaAnnotation("Data"));
        addImport("lombok.Data");
        addAnnotation(new JavaAnnotation("NoArgsConstructor"));
        addImport("lombok.NoArgsConstructor");
        addAnnotation(new JavaAnnotation("AllArgsConstructor"));
        addImport("lombok.AllArgsConstructor");

        return this;
    }

    public JavaClass addModifier(ModifierEnum modifier) {
        this.modifiers.add(modifier);
        return this;
    }

    public JavaClass addImplementedInterfaces(String implementedInterface) {
        this.implementedInterfaces.add(implementedInterface);
        return this;
    }

    public JavaClass addImport(String newImport) {
        this.imports.add(newImport);
        return this;
    }

    public JavaClass addAnnotation(JavaAnnotation annotation) {
        this.annotations.add(annotation);
        return this;
    }

    public JavaClass addField(JavaField field) {
        this.fields.add(field);
        return this;
    }

    /**
     * Retourne la classe Java sous forme de code Java.
     * 
     * @return le code Java
     */
    @Override
    public String toString() {
        StringWriter out = new StringWriter();
        PrintWriter writer = new PrintWriter(out, false);

        if (this.packageName != null) {
            writer.println(String.format("package %s;", this.packageName));
            writer.println();
        }
        if (!this.imports.isEmpty()) {
            for (String _import : this.imports) {
                writer.print("import ");
                writer.print(_import);
                writer.println(';');
            }
            writer.println();
        }
        this.annotations.forEach(writer::println);
        if (!this.modifiers.isEmpty()) {
            for (ModifierEnum modifier : this.modifiers) {
                writer.print(modifier.toString());
                writer.print(' ');
            }
        }
        writer.print(String.format("class %s", this.name));
        if (this.extendedClass != null) {
            writer.print(String.format(" extends %s", this.extendedClass));
        }
        if (!this.implementedInterfaces.isEmpty()) {
            writer.print(" implements ");
            writer.print(String.join(", ", this.implementedInterfaces));
        }
        writer.println(" {");
        if (!this.fields.isEmpty()) {
            writer.println();
            for (JavaField field : this.fields) {
                writer.print(INDENTATION);
                writer.println(field);
                writer.println();
            }
        }
        writer.println('}');

        writer.flush();

        return out.toString();
    }

    @RequiredArgsConstructor
    enum ModifierEnum {
        PUBLIC("public"),
        ABSTRACT("abstract"),
        FINAL("final");

        private final String modifier;

        @Override
        public String toString() {
            return this.modifier;
        }
    }
}
