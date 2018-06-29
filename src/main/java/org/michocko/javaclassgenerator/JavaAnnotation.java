package org.michocko.javaclassgenerator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.val;

/**
 * Représente une annotation java.<br>
 * {@link #toString()} retourne le code Java correspondant.<br>
 * Génère du code sous la forme suivante :
 * 
 * <pre>
 * &#64;Annotation(valeur1 = "plop", valeur2 = 15)
 * </pre>
 * 
 * @author michocko
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JavaAnnotation {

    private final Map<String, String> values = new LinkedHashMap<>();

    private String name;

    /**
     * Ajoute une valeur à l'annotation.<br>
     * Si le nom de la valeur est vide, le code généré sera :
     * 
     * <pre>
     * &#64;Annotation(valeur)
     * </pre>
     * 
     * Sinon, si le nom de la valeur n'est pas vide :
     * 
     * <pre>
     * &#64;Annotation(nomDeLaValeur = valeur)
     * </pre>
     * 
     * @param name
     *            le nom de la valeur (non-null)
     * @param value
     *            la valeur (non-null)
     * @return l'instance actuelle de {@link JavaAnnotation}
     */
    public JavaAnnotation addValue(String name, @NonNull String value) {
        this.values.put(name, value);
        return this;
    }

    /**
     * Retourne la classe Java sous forme de code Java.<br>
     * Génère du code sous la forme suivante :
     * 
     * <pre>
     * &#64;Annotation(valeur1 = "plop", valeur2 = 15)
     * </pre>
     * 
     * @return le code Java
     */
    @Override
    public String toString() {
        val sb = new StringBuilder();

        sb.append('@');
        sb.append(this.name);

        if (!this.values.isEmpty()) {
            sb.append('(');
            int i = this.values.size();
            for (Entry<String, String> entry : this.values.entrySet()) {
                if (!entry.getKey().isEmpty()) {
                    sb.append(entry.getKey());
                    sb.append(" = ");
                }
                sb.append(entry.getValue());
                if (--i > 0) {
                    sb.append(", ");
                }
            }
            sb.append(')');
        }

        return sb.toString();
    }
}
