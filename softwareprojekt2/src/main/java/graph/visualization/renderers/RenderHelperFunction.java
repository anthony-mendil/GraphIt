package graph.visualization.renderers;

import edu.uci.ics.jung.graph.util.Pair;

import java.awt.*;

/**
 * Helper functions for rendering.
 */
public class RenderHelperFunction {

    /**
     * Returns white/ black depending of the colors brightness.
     *
     * @param drawColor The color.
     * @return Black or white.
     */
    public Color getLuminanceColor(Paint drawColor) {
        double luminance = (0.2126 * ((Color) drawColor).getRed() + 0.7152 * ((Color) drawColor).getGreen() + 0.0722 * ((Color) drawColor).getGreen());
        return luminance > 127 ? new Color(20, 20, 20) : new Color(245, 245, 245);
    }

    /**
     * TODO
     * @param sphereWidth  The shape width.
     * @param sphereHeight The shape height.
     * @param annotation   The sphere annotation.
     * @param fontMetrics  The font metrics.
     * @return The new formatted string, so it fits into the shape.
     */
    String shrinkAnnotation(double sphereWidth, double sphereHeight, String annotation, FontMetrics
            fontMetrics) {
        String[] lines = breakAnnotation(sphereWidth, annotation, fontMetrics).split("\n");
        int count = (int) sphereHeight / fontMetrics.getHeight();
        StringBuilder label = new StringBuilder();
        for (int i = 0; i < lines.length && i < count; i++) {
            if (!lines[i].isEmpty()) {
                int c = count - 1;
                if (i == c) {
                    if (lines[c].length() >= 3) {
                        lines[c] = lines[c].substring(0, lines[c].length() - 3);
                        lines[c] += "...";
                    } else {
                        lines[c] = "...";
                    }
                }
                label.append(lines[i]).append("\n");
            }
        }
        return label.toString();
    }

    /**
     * Breaks the annotation so it fits into the shape.
     *
     * @param sphereWidth The shape width.
     * @param annotation  The title.
     * @param fontMetrics The font metrics.
     * @return The new formatted label fitting into the shape.
     */
    public String breakAnnotation(double sphereWidth, String annotation, FontMetrics fontMetrics) {
        StringBuilder label = new StringBuilder();
        StringBuilder lengthLabel = new StringBuilder();
        int i = 0;
        String[] annotationSplit = annotation.split("\\s+");
        for (String line : annotationSplit) {
            if (fontMetrics.stringWidth(lengthLabel.toString() + line) + 10 < sphereWidth) {
                lengthLabel.append(line);
                if (i != annotationSplit.length - 1) {
                    lengthLabel.append(" ");
                }

            } else {
                Pair<StringBuilder> stringBuilders = stringToBigForShapeWidth(fontMetrics, line, sphereWidth, i, lengthLabel, label);
                lengthLabel = stringBuilders.getFirst();
                label = stringBuilders.getSecond();
            }
            i++;
        }
        if (lengthLabel.length() != 0) {
            label.append(lengthLabel);
        }
        return label.toString();
    }

    /**
     * Splits and rebuild the string label, if he string length is higher than the shape length.
     *
     * @param fontMetrics The font metrics.
     * @param line        The line to break.
     * @param sphereWidth The shape width.
     * @param i           The counter.
     * @param lengthLabel The length label StringBuilder.
     * @param label       The label StringBuilder.
     * @return The new line.
     */
    private Pair<StringBuilder> stringToBigForShapeWidth(FontMetrics fontMetrics, String line, double sphereWidth, int i, StringBuilder lengthLabel, StringBuilder label) {
        if (fontMetrics.stringWidth(line) + 10 >= sphereWidth) {
            char[] chars = line.toCharArray();
            if (i != 0) {
                lengthLabel.append(" ");
            }
            for (char c : chars) {
                if (fontMetrics.stringWidth(lengthLabel.toString() + c) + 10 < sphereWidth) {
                    lengthLabel.append(c);
                } else {
                    label.append(lengthLabel).append("\n");
                    lengthLabel.delete(0, lengthLabel.length()).append(c);
                }
            }
        } else {
            label.append(lengthLabel);
            lengthLabel.delete(0, lengthLabel.length());
            lengthLabel.append("\n").append(line).append(" ");
        }
        return new Pair<>(lengthLabel, label);
    }
}
