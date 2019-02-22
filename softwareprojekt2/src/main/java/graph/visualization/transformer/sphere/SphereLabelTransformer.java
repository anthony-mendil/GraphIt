package graph.visualization.transformer.sphere;

import graph.graph.Sphere;
import gui.Values;
import gui.properties.Language;
import org.apache.commons.collections15.Transformer;

/**
 * Defines a functor that transform a sphere into its annotation. The input sphere is left unchanged.
 * Its extracting the annotation of a sphere.
 *
 * @param <S> The sphere type.
 */
public class SphereLabelTransformer<S> implements Transformer<S, String> {
    @Override
    public String transform(S s){
        try{
            Sphere sphere = (Sphere) s;
            return sphere.getAnnotation().get(Values.getInstance().getGraphLanguage().name());
        } catch (Exception e){
            throw new IllegalArgumentException();
        }
    }
}
