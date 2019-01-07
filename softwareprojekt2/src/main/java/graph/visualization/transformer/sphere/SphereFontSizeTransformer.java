package graph.visualization.transformer.sphere;

import graph.graph.Sphere;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

public class SphereFontSizeTransformer<S> implements Transformer<S, Integer> {
    @Override
    public Integer transform(S s) {
        try{
            Sphere sphere = (Sphere) s;
            return sphere.getFontSize();
        } catch (Exception e){
            throw new IllegalArgumentException();
        }
    }
}
