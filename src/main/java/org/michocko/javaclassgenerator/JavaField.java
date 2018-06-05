package org.michocko.javaclassgenerator;

import java.util.LinkedHashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.val;

/**
 * Représente un attribut d'une classe java.<br>
 * {@link #toString()} retourne le code Java correspondant.<br>
 * Génère du code sous la forme suivante (sur une seule ligne) :
 * 
 * <pre>
 * &#64;Annotation1(valeur1 = "plop", valeur2 = 15)
 * &#64;Annotation2(17)
 * private TypeDuChamp nomDuChamp;
 * </pre>
 * 
 * @author michocko
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "name" })
public class JavaField {
    private String type;
    private String name;
    @Singular
    private final Set<JavaAnnotation> annotations = new LinkedHashSet<>();

    /**
     * Ajoute une annotation sur le champ.<br>
     * 
     * 
     * @param annotation
     *            l'annotation à ajouter
     * @return l'instance actuelle de {@link JavaField}
     */
    public JavaField addAnnotation(JavaAnnotation annotation) {
        this.annotations.add(annotation);
        return this;
    }

    /**
     * Retourne l'attribut Java sous forme de code Java.
     * 
     * @return le code Java
     */
    @Override
    public String toString() {
        val sb = new StringBuilder();

        for (JavaAnnotation annotation : this.annotations) {
            sb.append(annotation);
            sb.append(' ');
        }

        sb.append("private ");
        sb.append(this.type);
        sb.append(' ');
        sb.append(this.name);
        sb.append(';');

        return sb.toString();
    }
}
