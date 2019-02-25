package graph.visualization.renderers;

import java.awt.*;

public class RenderHelperFunction {
    public Color getLuminanceColor(Paint drawColor) {
        double luminance = (0.2126 * ((Color) drawColor).getRed() + 0.7152 * ((Color) drawColor).getGreen() + 0.0722 * ((Color) drawColor).getGreen());
        return luminance > 127 ? new Color(20, 20, 20) : new Color(245, 245, 245);
    }

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
            }
            i++;
        }
        if (lengthLabel.length() != 0) {
            label.append(lengthLabel);
        }
        return label.toString();
    }
}
