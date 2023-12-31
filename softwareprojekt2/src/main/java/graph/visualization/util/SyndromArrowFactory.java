package graph.visualization.util;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

/**
 * Creates different shapes for arrows.
 *
 * @author Nina Unterberg
 */
public class SyndromArrowFactory extends edu.uci.ics.jung.visualization.util.ArrowFactory {

    /**
     * Creates a reinforced arrow shape.
     *
     * @param base        The base dimension for the arrow.
     * @param height      The height of the arrow.
     * @param notchHeight The notch height of the arrow.
     * @return A general path.
     */
    public GeneralPath getReinforcingEdgeArrow(float base, float height, float notchHeight) {
        return getNotchedArrow(base, height, notchHeight);
    }

    /**
     * Creates an extenuating arrow shape.
     *
     * @param r The radius of the arrow.
     * @return A general path.
     */
    public GeneralPath getExtenuatingEdgeArrow(int r) {
        GeneralPath arrow = new GeneralPath();
        arrow.moveTo(0.0F, 0.0F);
        double x = -r;
        double y = 0;
        double kappa = 0.5522847498;
        arrow.moveTo(x, y - (float) r);
        arrow.curveTo(x + r * kappa, y - r, x + r, y - r * kappa, x + r, y);
        arrow.curveTo(x + r, y + r * kappa, x + r * kappa, y + r, x, y + r);
        arrow.curveTo(x - r * kappa, y + r, x - r, y + r * kappa, x - r, y);
        arrow.curveTo(x - r, y - r * kappa, x - r * kappa, y - r, x, y - r);
        arrow.closePath();
        return arrow;
    }

    /**
     * Creates a neutral arrow shape.
     *
     * @return A general path.
     */
    public GeneralPath getNeutralEdgeArrow() {
        GeneralPath shape = new GeneralPath();
        shape.moveTo(5.182781, 14.06675);
        shape.curveTo(7.1870356, 14.06675, 8.485184, 13.245561, 9.504364, 11.781097);
        shape.curveTo(9.68925, 11.515445, 9.632287, 11.151202, 9.37437, 10.955615);
        shape.lineTo(8.291173, 10.1343155);
        shape.curveTo(8.0307, 9.936836, 7.66011, 9.982996, 7.456182, 10.238604);
        shape.curveTo(6.827223, 11.026591, 6.3606577, 11.480259, 5.37819, 11.480259);
        shape.curveTo(4.6057076, 11.480259, 3.6502132, 10.983134, 3.6502132, 10.234038);
        shape.curveTo(3.6502132, 9.667758, 4.1176558, 9.376921, 4.8804345, 8.949342);
        shape.curveTo(5.769875, 8.4506235, 6.9469414, 7.8300047, 6.9469414, 6.2774997);
        shape.lineTo(6.9469414, 6.0317483);
        shape.curveTo(6.9469414, 5.698917, 6.6247816, 5.7433114, 6.2919335, 5.7433114);
        shape.lineTo(4.6816463, 5.7433114);
        shape.curveTo(4.3488493, 5.7433114, 3.9219317, 5.6989174, 3.9219317, 6.0317483);
        shape.lineTo(3.9219317, 6.1766944);
        shape.curveTo(3.9219317, 7.2528963, 0.7764704, 7.2977524, 0.7764704, 10.210021);
        shape.curveTo(0.7764619, 12.403225, 3.0514557, 14.066914, 5.182781, 14.066914);
        shape.closePath();
        shape.moveTo(5.476092, 3.480936);
        shape.curveTo(6.4352155, 3.480936, 7.21546, 2.7006495, 7.21546, 1.7415683);
        shape.curveTo(7.21546, 0.78249556, 6.435216, 0.0021920204, 5.4760923, 0.0021920204);
        shape.curveTo(4.5170026, 0.0021920204, 3.736699, 0.78249556, 3.736699, 1.7416024);
        shape.curveTo(3.736699, 2.7006836, 4.5170026, 3.480936, 5.4760923, 3.480936);
        shape.closePath();

        AffineTransform transform = new AffineTransform();
        transform.setToTranslation(-14, -6.5);
        Shape s = transform.createTransformedShape(shape);
        return new GeneralPath(s);
    }
}
