package log_management.parameters;

import gui.properties.Language;

import java.awt.*;
import java.util.ArrayList;

/**
 * Used to get a color name a color.
 */
public class ColorNameCreator {

    private static ColorNameCreator colorNameCreator;

    private ColorNameCreator() {}

    public static ColorNameCreator getInstance() {
        if (colorNameCreator != null) {
            return colorNameCreator;
        }
        else {
            colorNameCreator = new ColorNameCreator();
            return colorNameCreator;
        }
    }

    /**
     * Initialize the color list.
     */
    private ArrayList<ColorNames> createColorList() {
        ArrayList<ColorNames> colors = new ArrayList<ColorNames>();
        colors.add(new ColorNames("AliceBlue", "Eisfarben", 0xF0, 0xF8, 0xFF));
        colors.add(new ColorNames("AntiqueWhite", "Antikweiß", 0xFA, 0xEB, 0xD7));
        colors.add(new ColorNames("Aqua", "Wasserfarben", 0x00, 0xFF, 0xFF));
        colors.add(new ColorNames("Aquamarine", "Aquamarinblau", 0x7F, 0xFF, 0xD4));
        colors.add(new ColorNames("Azure", "Himmelblau", 0xF0, 0xFF, 0xFF));
        colors.add(new ColorNames("Beige", "Beige", 0xF5, 0xF5, 0xDC));
        colors.add(new ColorNames("Bisque", "Biskuit", 0xFF, 0xE4, 0xC4));
        colors.add(new ColorNames("Black", "Schwarz", 0x00, 0x00, 0x00));
        colors.add(new ColorNames("BlanchedAlmond", "Mandelweiß", 0xFF, 0xEB, 0xCD));
        colors.add(new ColorNames("Blue", "Blau", 0x00, 0x00, 0xFF));
        colors.add(new ColorNames("BlueViolet", "Blauviolett", 0x8A, 0x2B, 0xE2));
        colors.add(new ColorNames("Brown", "Braun", 0xA5, 0x2A, 0x2A));
        colors.add(new ColorNames("BurlyWood", "Gelbbraun", 0xDE, 0xB8, 0x87));
        colors.add(new ColorNames("CadetBlue", "Kadettenblau", 0x5F, 0x9E, 0xA0));
        colors.add(new ColorNames("Chartreuse", "Hellgrün", 0x7F, 0xFF, 0x00));
        colors.add(new ColorNames("Chocolate", "Schokolade", 0xD2, 0x69, 0x1E));
        colors.add(new ColorNames("Coral", "Koralle", 0xFF, 0x7F, 0x50));
        colors.add(new ColorNames("CornflowerBlue", "Kornblumenblau", 0x64, 0x95, 0xED));
        colors.add(new ColorNames("Cornsilk", "Mais", 0xFF, 0xF8, 0xDC));
        colors.add(new ColorNames("Crimson", "Karmesinrot", 0xDC, 0x14, 0x3C));
        colors.add(new ColorNames("Cyan", "Türkis", 0x00, 0xFF, 0xFF));
        colors.add(new ColorNames("DarkBlue", "Dunkelblau", 0x00, 0x00, 0x8B));
        colors.add(new ColorNames("DarkCyan", "Dunkeltürkis", 0x00, 0x8B, 0x8B));
        colors.add(new ColorNames("DarkGoldenRod", "Dunkle Goldrutenfarbe", 0xB8, 0x86, 0x0B));
        colors.add(new ColorNames("DarkGray", "Dunkelgrau", 0xA9, 0xA9, 0xA9));
        colors.add(new ColorNames("DarkGreen", "Dunkelgrün", 0x00, 0x64, 0x00));
        colors.add(new ColorNames("DarkKhaki", "Dunkelkhaki", 0xBD, 0xB7, 0x6B));
        colors.add(new ColorNames("DarkMagenta", "Dunkelmagenta", 0x8B, 0x00, 0x8B));
        colors.add(new ColorNames("DarkOliveGreen", "Dunkles Olivgrün", 0x55, 0x6B, 0x2F));
        colors.add(new ColorNames("DarkOrange", "Dunkelorange", 0xFF, 0x8C, 0x00));
        colors.add(new ColorNames("DarkOrchid", "Dunkle Orchidee", 0x99, 0x32, 0xCC));
        colors.add(new ColorNames("DarkRed", "Dunkelrot", 0x8B, 0x00, 0x00));
        colors.add(new ColorNames("DarkSalmon", "Dunkle Lachsfarbe", 0xE9, 0x96, 0x7A));
        colors.add(new ColorNames("DarkSeaGreen", "Dunkles Seegrün", 0x8F, 0xBC, 0x8F));
        colors.add(new ColorNames("DarkSlateBlue", "Dunkles Schieferblau", 0x48, 0x3D, 0x8B));
        colors.add(new ColorNames("DarkSlateGray", "Dunkles Schiefergrau", 0x2F, 0x4F, 0x4F));
        colors.add(new ColorNames("DarkTurquoise", "Dunkeltürkis", 0x00, 0xCE, 0xD1));
        colors.add(new ColorNames("DarkViolet", "Dunkelviolett", 0x94, 0x00, 0xD3));
        colors.add(new ColorNames("DeepPink", "Tiefrosa", 0xFF, 0x14, 0x93));
        colors.add(new ColorNames("DeepSkyBlue", "Tiefes Himmelblau", 0x00, 0xBF, 0xFF));
        colors.add(new ColorNames("DimGray", "Dunkelgrau", 0x69, 0x69, 0x69));
        colors.add(new ColorNames("DodgerBlue", "Persenningblau", 0x1E, 0x90, 0xFF));
        colors.add(new ColorNames("FireBrick", "Backstein", 0xB2, 0x22, 0x22));
        colors.add(new ColorNames("FloralWhite", "Blütenweiß", 0xFF, 0xFA, 0xF0));
        colors.add(new ColorNames("ForestGreen", "Waldgrün", 0x22, 0x8B, 0x22));
        colors.add(new ColorNames("Fuchsia", "Magenta", 0xFF, 0x00, 0xFF));
        colors.add(new ColorNames("Gainsboro", "Gainsboro", 0xDC, 0xDC, 0xDC));
        colors.add(new ColorNames("GhostWhite", "", 0xF8, 0xF8, 0xFF));
        colors.add(new ColorNames("Gold", "Gold", 0xFF, 0xD7, 0x00));
        colors.add(new ColorNames("GoldenRod", "Goldrute", 0xDA, 0xA5, 0x20));
        colors.add(new ColorNames("Gray", "Grau", 0x80, 0x80, 0x80));
        colors.add(new ColorNames("Green", "Grün", 0x00, 0x80, 0x00));
        colors.add(new ColorNames("GreenYellow", "Grüngelb", 0xAD, 0xFF, 0x2F));
        colors.add(new ColorNames("HoneyDew", "Honigmelone", 0xF0, 0xFF, 0xF0));
        colors.add(new ColorNames("HotPink", "Leuchtendes Rosa", 0xFF, 0x69, 0xB4));
        colors.add(new ColorNames("IndianRed", "Indischrot", 0xCD, 0x5C, 0x5C));
        colors.add(new ColorNames("Indigo", "Indigo", 0x4B, 0x00, 0x82));
        colors.add(new ColorNames("Ivory", "Elfenbein", 0xFF, 0xFF, 0xF0));
        colors.add(new ColorNames("Khaki", "Stauffarben", 0xF0, 0xE6, 0x8C));
        colors.add(new ColorNames("Lavender", "Lavendel", 0xE6, 0xE6, 0xFA));
        colors.add(new ColorNames("LavenderBlush", "Lavendelrosa", 0xFF, 0xF0, 0xF5));
        colors.add(new ColorNames("LawnGreen", "Rasengrün", 0x7C, 0xFC, 0x00));
        colors.add(new ColorNames("LemonChiffon", "Chiffongelb", 0xFF, 0xFA, 0xCD));
        colors.add(new ColorNames("LightBlue", "Hellblau", 0xAD, 0xD8, 0xE6));
        colors.add(new ColorNames("LightCoral", "Helles Korallenrot", 0xF0, 0x80, 0x80));
        colors.add(new ColorNames("LightCyan", "Helles Cyan", 0xE0, 0xFF, 0xFF));
        colors.add(new ColorNames("LightGoldenRodYellow", "Helles Goldrutengelb", 0xFA, 0xFA, 0xD2));
        colors.add(new ColorNames("LightGray", "Hellgrau", 0xD3, 0xD3, 0xD3));
        colors.add(new ColorNames("LightGreen", "Hellgrün", 0x90, 0xEE, 0x90));
        colors.add(new ColorNames("LightPink", "Hellrosa", 0xFF, 0xB6, 0xC1));
        colors.add(new ColorNames("LightSalmon", "Helle Lachsfarbe", 0xFF, 0xA0, 0x7A));
        colors.add(new ColorNames("LightSeaGreen", "Helles Seegrün", 0x20, 0xB2, 0xAA));
        colors.add(new ColorNames("LightSkyBlue", "Helles Himmelblau", 0x87, 0xCE, 0xFA));
        colors.add(new ColorNames("LightSlateGray", "Helles Schiefergrau", 0x77, 0x88, 0x99));
        colors.add(new ColorNames("LightSteelBlue", "", 0xB0, 0xC4, 0xDE));
        colors.add(new ColorNames("LightYellow", "Helles Stahlblau", 0xFF, 0xFF, 0xE0));
        colors.add(new ColorNames("Lime", "Limone", 0x00, 0xFF, 0x00));
        colors.add(new ColorNames("LimeGreen", "Limonengrün", 0x32, 0xCD, 0x32));
        colors.add(new ColorNames("Linen", "Leinen", 0xFA, 0xF0, 0xE6));
        colors.add(new ColorNames("Magenta", "Magenta", 0xFF, 0x00, 0xFF));
        colors.add(new ColorNames("Maroon", "Kastanie", 0x80, 0x00, 0x00));
        colors.add(new ColorNames("MediumAquaMarine", "Mittleres Aquamarin", 0x66, 0xCD, 0xAA));
        colors.add(new ColorNames("MediumBlue", "Mittleres Blau", 0x00, 0x00, 0xCD));
        colors.add(new ColorNames("MediumOrchid", "Mittlere Orchedee", 0xBA, 0x55, 0xD3));
        colors.add(new ColorNames("MediumPurple", "Mittleres Violett", 0x93, 0x70, 0xDB));
        colors.add(new ColorNames("MediumSeaGreen", "Mittleres Seegrün", 0x3C, 0xB3, 0x71));
        colors.add(new ColorNames("MediumSlateBlue", "Mittleres Schieferblau", 0x7B, 0x68, 0xEE));
        colors.add(new ColorNames("MediumSpringGreen", "Mittleres Frühlingsgrün", 0x00, 0xFA, 0x9A));
        colors.add(new ColorNames("MediumTurquoise", "Mittlere Türkis", 0x48, 0xD1, 0xCC));
        colors.add(new ColorNames("MediumVioletRed", "Mittleres Violettrot", 0xC7, 0x15, 0x85));
        colors.add(new ColorNames("MidnightBlue", "Mitternachtsblau", 0x19, 0x19, 0x70));
        colors.add(new ColorNames("MintCream", "Cremige Minze", 0xF5, 0xFF, 0xFA));
        colors.add(new ColorNames("MistyRose", "Altrosa", 0xFF, 0xE4, 0xE1));
        colors.add(new ColorNames("Moccasin", "Mokassin", 0xFF, 0xE4, 0xB5));
        colors.add(new ColorNames("NavajoWhite", "Navajoweiß", 0xFF, 0xDE, 0xAD));
        colors.add(new ColorNames("Navy", "Marinenblau", 0x00, 0x00, 0x80));
        colors.add(new ColorNames("OldLace", "Alte Spitze", 0xFD, 0xF5, 0xE6));
        colors.add(new ColorNames("Olive", "Olivgrün", 0x80, 0x80, 0x00));
        colors.add(new ColorNames("OliveDrab", "Olivgraubraun", 0x6B, 0x8E, 0x23));
        colors.add(new ColorNames("Orange", "Orange", 0xFF, 0xA5, 0x00));
        colors.add(new ColorNames("OrangeRed", "Orangerot", 0xFF, 0x45, 0x00));
        colors.add(new ColorNames("Orchid", "Orchidee", 0xDA, 0x70, 0xD6));
        colors.add(new ColorNames("PaleGoldenRod", "Blasse Goldrutenfarbe", 0xEE, 0xE8, 0xAA));
        colors.add(new ColorNames("PaleGreen", "Blassgrün", 0x98, 0xFB, 0x98));
        colors.add(new ColorNames("PaleTurquoise", "Blasstürkis", 0xAF, 0xEE, 0xEE));
        colors.add(new ColorNames("PaleVioletRed", "Blasses Violettrot", 0xDB, 0x70, 0x93));
        colors.add(new ColorNames("PapayaWhip", "Papayacreme", 0xFF, 0xEF, 0xD5));
        colors.add(new ColorNames("PeachPuff", "Pfirsich", 0xFF, 0xDA, 0xB9));
        colors.add(new ColorNames("Peru", "Peru", 0xCD, 0x85, 0x3F));
        colors.add(new ColorNames("Pink", "Rosa", 0xFF, 0xC0, 0xCB));
        colors.add(new ColorNames("Plum", "Pflaume", 0xDD, 0xA0, 0xDD));
        colors.add(new ColorNames("PowderBlue", "Taubenblau", 0xB0, 0xE0, 0xE6));
        colors.add(new ColorNames("Purple", "Violett", 0x80, 0x00, 0x80));
        colors.add(new ColorNames("Red", "Rot", 0xFF, 0x00, 0x00));
        colors.add(new ColorNames("RosyBrown", "Rosiges Braun", 0xBC, 0x8F, 0x8F));
        colors.add(new ColorNames("RoyalBlue", "Königsblau", 0x41, 0x69, 0xE1));
        colors.add(new ColorNames("SaddleBrown", "Sattelbraun", 0x8B, 0x45, 0x13));
        colors.add(new ColorNames("Salmon", "Lachsfarben", 0xFA, 0x80, 0x72));
        colors.add(new ColorNames("SandyBrown", "Sandbraun", 0xF4, 0xA4, 0x60));
        colors.add(new ColorNames("SeaGreen", "Seegrün", 0x2E, 0x8B, 0x57));
        colors.add(new ColorNames("SeaShell", "Muschel", 0xFF, 0xF5, 0xEE));
        colors.add(new ColorNames("Sienna", "Siennaerde", 0xA0, 0x52, 0x2D));
        colors.add(new ColorNames("Silver", "Silber", 0xC0, 0xC0, 0xC0));
        colors.add(new ColorNames("SkyBlue", "Himmelblau", 0x87, 0xCE, 0xEB));
        colors.add(new ColorNames("SlateBlue", "Schieferblau", 0x6A, 0x5A, 0xCD));
        colors.add(new ColorNames("SlateGray", "Schiefergrau", 0x70, 0x80, 0x90));
        colors.add(new ColorNames("Snow", "Schneeweiß", 0xFF, 0xFA, 0xFA));
        colors.add(new ColorNames("SpringGreen", "Frühlingsgrün", 0x00, 0xFF, 0x7F));
        colors.add(new ColorNames("SteelBlue", "Stahlblau", 0x46, 0x82, 0xB4));
        colors.add(new ColorNames("Tan", "Hautfarben", 0xD2, 0xB4, 0x8C));
        colors.add(new ColorNames("Teal", "Krickentengrün", 0x00, 0x80, 0x80));
        colors.add(new ColorNames("Thistle", "Distel", 0xD8, 0xBF, 0xD8));
        colors.add(new ColorNames("Tomato", "Tomate", 0xFF, 0x63, 0x47));
        colors.add(new ColorNames("Turquoise", "Türkis", 0x40, 0xE0, 0xD0));
        colors.add(new ColorNames("Violet", "Veilchen", 0xEE, 0x82, 0xEE));
        colors.add(new ColorNames("Wheat", "Weizen", 0xF5, 0xDE, 0xB3));
        colors.add(new ColorNames("White", "Weiß", 0xFF, 0xFF, 0xFF));
        colors.add(new ColorNames("WhiteSmoke", "rauchfarben", 0xF5, 0xF5, 0xF5));
        colors.add(new ColorNames("Yellow", "Gelb", 0xFF, 0xFF, 0x00));
        colors.add(new ColorNames("YellowGreen", "Gelbgrün", 0x9A, 0xCD, 0x32));
        return colors;
    }

    /**
     * Get the closest color name from the color list
     *
     * @param red The red value of the color.
     * @param green The green value of the color.
     * @param blue The blue value of the color.
     * @param language The language of the gui.
     * @return
     */
    private String getColorNameRgb(int red, int green, int blue, Language language) {
        ArrayList<ColorNames> colors = createColorList();
        ColorNames closestMatch = null;
        int minMSE = Integer.MAX_VALUE;
        int mse;
        for (ColorNames c : colors) {
            mse = c.calculate(red, green, blue);
            if (mse < minMSE) {
                minMSE = mse;
                closestMatch = c;
            }
        }

        if (closestMatch != null) {
            if (language == Language.ENGLISH)
                return closestMatch.getEnglishName();
            else
                return closestMatch.getGermanName();
        } else {
            if (language == Language.ENGLISH)
                return "No matched color name.";
            else
                return "Kein passender Farbname";
        }
    }

    public String getColorName(Color color, Language language) {
        return getColorNameRgb(color.getRed(), color.getGreen(),
                color.getBlue(), language);
    }

    /**
     * Used to lookup color name. Stores english and german name.
     */
    public class ColorNames {

        private int red;
        private int green;
        private int blue;

        private String germanName;

        private String englishName;

        public ColorNames(String englishName, String germanName, int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.germanName = germanName;
            this.englishName = englishName;
        }

        public int calculate(int pixRed, int pixGreen, int pixBlue) {
            return (((pixRed - red) * (pixRed - red) + (pixGreen - green) * (pixGreen - green) + (pixBlue - blue)
                    * (pixBlue - blue)) / 3);
        }

        public String getEnglishName() {
            return englishName;
        }

        public String getGermanName() {
            return germanName;
        }
    }
}