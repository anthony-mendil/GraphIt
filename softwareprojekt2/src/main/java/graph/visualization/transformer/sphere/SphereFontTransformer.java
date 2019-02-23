package graph.visualization.transformer.sphere;

import graph.graph.Sphere;
import graph.visualization.control.HelperFunctions;
import gui.Values;
import org.apache.commons.collections15.Transformer;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Defines a functor that transform a sphere into its annotation font. The input sphere is left unchanged.
 * Its extracting the font annotation of a sphere.
 *
 * @param <S> The sphere type.
 */
public class SphereFontTransformer<S> implements Transformer<S, Font> {
    private HelperFunctions helperFunctions = new HelperFunctions();
    @Override
    public Font transform(S s) {
        Sphere sphere = (Sphere) s;
            Font newFont = helperFunctions.returnFont(sphere.getFont());
            return newFont.deriveFont(Font.PLAIN, sphere.getFontSize());
    }
}
