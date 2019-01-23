package log_management.parameters;

import gui.properties.Language;

import java.awt.Color;
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
    private ArrayList<ColorNames> initColorList() {
        ArrayList<ColorNames> colorList = new ArrayList<ColorNames>();
        colorList.add(new ColorNames("AliceBlue", "Eisfarben", 0xF0, 0xF8, 0xFF));
        colorList.add(new ColorNames("AntiqueWhite", "Antikweiß", 0xFA, 0xEB, 0xD7));
        colorList.add(new ColorNames("Aqua", "Wasserfarben", 0x00, 0xFF, 0xFF));
        colorList.add(new ColorNames("Aquamarine", "Aquamarinblau", 0x7F, 0xFF, 0xD4));
        colorList.add(new ColorNames("Azure", "Himmelblau", 0xF0, 0xFF, 0xFF));
        colorList.add(new ColorNames("Beige", "Beige", 0xF5, 0xF5, 0xDC));
        colorList.add(new ColorNames("Bisque", "Biskuit", 0xFF, 0xE4, 0xC4));
        colorList.add(new ColorNames("Black", "Schwarz", 0x00, 0x00, 0x00));
        colorList.add(new ColorNames("BlanchedAlmond", "Mandelweiß", 0xFF, 0xEB, 0xCD));
        colorList.add(new ColorNames("Blue", "Blau", 0x00, 0x00, 0xFF));
        colorList.add(new ColorNames("BlueViolet", "Blauviolett", 0x8A, 0x2B, 0xE2));
        colorList.add(new ColorNames("Brown", "Braun", 0xA5, 0x2A, 0x2A));
        colorList.add(new ColorNames("BurlyWood", "Gelbbraun", 0xDE, 0xB8, 0x87));
        colorList.add(new ColorNames("CadetBlue", "Kadettenblau", 0x5F, 0x9E, 0xA0));
        colorList.add(new ColorNames("Chartreuse", "Hellgrün", 0x7F, 0xFF, 0x00));
        colorList.add(new ColorNames("Chocolate", "Schokolade", 0xD2, 0x69, 0x1E));
        colorList.add(new ColorNames("Coral", "Koralle", 0xFF, 0x7F, 0x50));
        colorList.add(new ColorNames("CornflowerBlue", "Kornblumenblau", 0x64, 0x95, 0xED));
        colorList.add(new ColorNames("Cornsilk", "Mais", 0xFF, 0xF8, 0xDC));
        colorList.add(new ColorNames("Crimson", "Karmesinrot", 0xDC, 0x14, 0x3C));
        colorList.add(new ColorNames("Cyan", "Türkis", 0x00, 0xFF, 0xFF));
        colorList.add(new ColorNames("DarkBlue", "Dunkelblau", 0x00, 0x00, 0x8B));
        colorList.add(new ColorNames("DarkCyan", "Dunkeltürkis", 0x00, 0x8B, 0x8B));
        colorList.add(new ColorNames("DarkGoldenRod", "Dunkle Goldrutenfarbe", 0xB8, 0x86, 0x0B));
        colorList.add(new ColorNames("DarkGray", "Dunkelgrau", 0xA9, 0xA9, 0xA9));
        colorList.add(new ColorNames("DarkGreen", "Dunkelgrün", 0x00, 0x64, 0x00));
        colorList.add(new ColorNames("DarkKhaki", "Dunkelkhaki", 0xBD, 0xB7, 0x6B));
        colorList.add(new ColorNames("DarkMagenta", "Dunkelmagenta", 0x8B, 0x00, 0x8B));
        colorList.add(new ColorNames("DarkOliveGreen", "Dunkles Olivgrün", 0x55, 0x6B, 0x2F));
        colorList.add(new ColorNames("DarkOrange", "Dunkelorange", 0xFF, 0x8C, 0x00));
        colorList.add(new ColorNames("DarkOrchid", "Dunkle Orchidee", 0x99, 0x32, 0xCC));
        colorList.add(new ColorNames("DarkRed", "Dunkelrot", 0x8B, 0x00, 0x00));
        colorList.add(new ColorNames("DarkSalmon", "Dunkle Lachsfarbe", 0xE9, 0x96, 0x7A));
        colorList.add(new ColorNames("DarkSeaGreen", "Dunkles Seegrün", 0x8F, 0xBC, 0x8F));
        colorList.add(new ColorNames("DarkSlateBlue", "Dunkles Schieferblau", 0x48, 0x3D, 0x8B));
        colorList.add(new ColorNames("DarkSlateGray", "Dunkles Schiefergrau", 0x2F, 0x4F, 0x4F));
        colorList.add(new ColorNames("DarkTurquoise", "Dunkeltürkis", 0x00, 0xCE, 0xD1));
        colorList.add(new ColorNames("DarkViolet", "Dunkelviolett", 0x94, 0x00, 0xD3));
        colorList.add(new ColorNames("DeepPink", "Tiefrosa", 0xFF, 0x14, 0x93));
        colorList.add(new ColorNames("DeepSkyBlue", "Tiefes Himmelblau", 0x00, 0xBF, 0xFF));
        colorList.add(new ColorNames("DimGray", "Dunkelgrau", 0x69, 0x69, 0x69));
        colorList.add(new ColorNames("DodgerBlue", "Persenningblau", 0x1E, 0x90, 0xFF));
        colorList.add(new ColorNames("FireBrick", "Backstein", 0xB2, 0x22, 0x22));
        colorList.add(new ColorNames("FloralWhite", "Blütenweiß", 0xFF, 0xFA, 0xF0));
        colorList.add(new ColorNames("ForestGreen", "Waldgrün", 0x22, 0x8B, 0x22));
        colorList.add(new ColorNames("Fuchsia", "Magenta", 0xFF, 0x00, 0xFF));
        colorList.add(new ColorNames("Gainsboro", "Gainsboro", 0xDC, 0xDC, 0xDC));
        colorList.add(new ColorNames("GhostWhite", "", 0xF8, 0xF8, 0xFF));
        colorList.add(new ColorNames("Gold", "", 0xFF, 0xD7, 0x00));
        colorList.add(new ColorNames("GoldenRod", "", 0xDA, 0xA5, 0x20));
        colorList.add(new ColorNames("Gray", "", 0x80, 0x80, 0x80));
        colorList.add(new ColorNames("Green", "", 0x00, 0x80, 0x00));
        colorList.add(new ColorNames("GreenYellow", "", 0xAD, 0xFF, 0x2F));
        colorList.add(new ColorNames("HoneyDew", "", 0xF0, 0xFF, 0xF0));
        colorList.add(new ColorNames("HotPink", "", 0xFF, 0x69, 0xB4));
        colorList.add(new ColorNames("IndianRed", "", 0xCD, 0x5C, 0x5C));
        colorList.add(new ColorNames("Indigo", "", 0x4B, 0x00, 0x82));
        colorList.add(new ColorNames("Ivory", "", 0xFF, 0xFF, 0xF0));
        colorList.add(new ColorNames("Khaki", "", 0xF0, 0xE6, 0x8C));
        colorList.add(new ColorNames("Lavender", "", 0xE6, 0xE6, 0xFA));
        colorList.add(new ColorNames("LavenderBlush", "", 0xFF, 0xF0, 0xF5));
        colorList.add(new ColorNames("LawnGreen", "", 0x7C, 0xFC, 0x00));
        colorList.add(new ColorNames("LemonChiffon", "", 0xFF, 0xFA, 0xCD));
        colorList.add(new ColorNames("LightBlue", "", 0xAD, 0xD8, 0xE6));
        colorList.add(new ColorNames("LightCoral", "", 0xF0, 0x80, 0x80));
        colorList.add(new ColorNames("LightCyan", "", 0xE0, 0xFF, 0xFF));
        colorList.add(new ColorNames("LightGoldenRodYellow", "", 0xFA, 0xFA, 0xD2));
        colorList.add(new ColorNames("LightGray", "", 0xD3, 0xD3, 0xD3));
        colorList.add(new ColorNames("LightGreen", "", 0x90, 0xEE, 0x90));
        colorList.add(new ColorNames("LightPink", "", 0xFF, 0xB6, 0xC1));
        colorList.add(new ColorNames("LightSalmon", "", 0xFF, 0xA0, 0x7A));
        colorList.add(new ColorNames("LightSeaGreen", "", 0x20, 0xB2, 0xAA));
        colorList.add(new ColorNames("LightSkyBlue", "", 0x87, 0xCE, 0xFA));
        colorList.add(new ColorNames("LightSlateGray", "", 0x77, 0x88, 0x99));
        colorList.add(new ColorNames("LightSteelBlue", "", 0xB0, 0xC4, 0xDE));
        colorList.add(new ColorNames("LightYellow", "", 0xFF, 0xFF, 0xE0));
        colorList.add(new ColorNames("Lime", "", 0x00, 0xFF, 0x00));
        colorList.add(new ColorNames("LimeGreen", "", 0x32, 0xCD, 0x32));
        colorList.add(new ColorNames("Linen", "", 0xFA, 0xF0, 0xE6));
        colorList.add(new ColorNames("Magenta", "", 0xFF, 0x00, 0xFF));
        colorList.add(new ColorNames("Maroon", "", 0x80, 0x00, 0x00));
        colorList.add(new ColorNames("MediumAquaMarine", "", 0x66, 0xCD, 0xAA));
        colorList.add(new ColorNames("MediumBlue", "", 0x00, 0x00, 0xCD));
        colorList.add(new ColorNames("MediumOrchid", "", 0xBA, 0x55, 0xD3));
        colorList.add(new ColorNames("MediumPurple", "", 0x93, 0x70, 0xDB));
        colorList.add(new ColorNames("MediumSeaGreen", "", 0x3C, 0xB3, 0x71));
        colorList.add(new ColorNames("MediumSlateBlue", "", 0x7B, 0x68, 0xEE));
        colorList.add(new ColorNames("MediumSpringGreen", "", 0x00, 0xFA, 0x9A));
        colorList.add(new ColorNames("MediumTurquoise", "", 0x48, 0xD1, 0xCC));
        colorList.add(new ColorNames("MediumVioletRed", "", 0xC7, 0x15, 0x85));
        colorList.add(new ColorNames("MidnightBlue", "", 0x19, 0x19, 0x70));
        colorList.add(new ColorNames("MintCream", "", 0xF5, 0xFF, 0xFA));
        colorList.add(new ColorNames("MistyRose", "", 0xFF, 0xE4, 0xE1));
        colorList.add(new ColorNames("Moccasin", "", 0xFF, 0xE4, 0xB5));
        colorList.add(new ColorNames("NavajoWhite", "", 0xFF, 0xDE, 0xAD));
        colorList.add(new ColorNames("Navy", "", 0x00, 0x00, 0x80));
        colorList.add(new ColorNames("OldLace", "", 0xFD, 0xF5, 0xE6));
        colorList.add(new ColorNames("Olive", "", 0x80, 0x80, 0x00));
        colorList.add(new ColorNames("OliveDrab", "", 0x6B, 0x8E, 0x23));
        colorList.add(new ColorNames("Orange", "", 0xFF, 0xA5, 0x00));
        colorList.add(new ColorNames("OrangeRed", "", 0xFF, 0x45, 0x00));
        colorList.add(new ColorNames("Orchid", "", 0xDA, 0x70, 0xD6));
        colorList.add(new ColorNames("PaleGoldenRod", "", 0xEE, 0xE8, 0xAA));
        colorList.add(new ColorNames("PaleGreen", "", 0x98, 0xFB, 0x98));
        colorList.add(new ColorNames("PaleTurquoise", "", 0xAF, 0xEE, 0xEE));
        colorList.add(new ColorNames("PaleVioletRed", "", 0xDB, 0x70, 0x93));
        colorList.add(new ColorNames("PapayaWhip", "", 0xFF, 0xEF, 0xD5));
        colorList.add(new ColorNames("PeachPuff", "", 0xFF, 0xDA, 0xB9));
        colorList.add(new ColorNames("Peru", "", 0xCD, 0x85, 0x3F));
        colorList.add(new ColorNames("Pink", "", 0xFF, 0xC0, 0xCB));
        colorList.add(new ColorNames("Plum", "", 0xDD, 0xA0, 0xDD));
        colorList.add(new ColorNames("PowderBlue", "", 0xB0, 0xE0, 0xE6));
        colorList.add(new ColorNames("Purple", "", 0x80, 0x00, 0x80));
        colorList.add(new ColorNames("Red", "", 0xFF, 0x00, 0x00));
        colorList.add(new ColorNames("RosyBrown", "", 0xBC, 0x8F, 0x8F));
        colorList.add(new ColorNames("RoyalBlue", "", 0x41, 0x69, 0xE1));
        colorList.add(new ColorNames("SaddleBrown", "", 0x8B, 0x45, 0x13));
        colorList.add(new ColorNames("Salmon", "", 0xFA, 0x80, 0x72));
        colorList.add(new ColorNames("SandyBrown", "", 0xF4, 0xA4, 0x60));
        colorList.add(new ColorNames("SeaGreen", "", 0x2E, 0x8B, 0x57));
        colorList.add(new ColorNames("SeaShell", "", 0xFF, 0xF5, 0xEE));
        colorList.add(new ColorNames("Sienna", "", 0xA0, 0x52, 0x2D));
        colorList.add(new ColorNames("Silver", "", 0xC0, 0xC0, 0xC0));
        colorList.add(new ColorNames("SkyBlue", "", 0x87, 0xCE, 0xEB));
        colorList.add(new ColorNames("SlateBlue", "", 0x6A, 0x5A, 0xCD));
        colorList.add(new ColorNames("SlateGray", "", 0x70, 0x80, 0x90));
        colorList.add(new ColorNames("Snow", "", 0xFF, 0xFA, 0xFA));
        colorList.add(new ColorNames("SpringGreen", "", 0x00, 0xFF, 0x7F));
        colorList.add(new ColorNames("SteelBlue", "", 0x46, 0x82, 0xB4));
        colorList.add(new ColorNames("Tan", "", 0xD2, 0xB4, 0x8C));
        colorList.add(new ColorNames("Teal", "", 0x00, 0x80, 0x80));
        colorList.add(new ColorNames("Thistle", "", 0xD8, 0xBF, 0xD8));
        colorList.add(new ColorNames("Tomato", "", 0xFF, 0x63, 0x47));
        colorList.add(new ColorNames("Turquoise", "", 0x40, 0xE0, 0xD0));
        colorList.add(new ColorNames("Violet", "", 0xEE, 0x82, 0xEE));
        colorList.add(new ColorNames("Wheat", "", 0xF5, 0xDE, 0xB3));
        colorList.add(new ColorNames("White", "", 0xFF, 0xFF, 0xFF));
        colorList.add(new ColorNames("WhiteSmoke", "", 0xF5, 0xF5, 0xF5));
        colorList.add(new ColorNames("Yellow", "", 0xFF, 0xFF, 0x00));
        colorList.add(new ColorNames("YellowGreen", "", 0x9A, 0xCD, 0x32));
        return colorList;
    }

    /**
     * Get the closest color name from the color list
     *
     * @param red The red value of the color.
     * @param green The green value of the color.
     * @param blue The blue value of the color.
     * @return
     */
    private String getColorNameFromRgb(int red, int green, int blue, Language language) {
        ArrayList<ColorNames> colorList = initColorList();
        ColorNames closestMatch = null;
        int minMSE = Integer.MAX_VALUE;
        int mse;
        for (ColorNames c : colorList) {
            mse = c.computeMSE(red, green, blue);
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
            return "No matched color name.";
        }
    }

    public String getColorNameFromColor(Color color, Language language) {
        return getColorNameFromRgb(color.getRed(), color.getGreen(),
                color.getBlue(), language);
    }

    /**
     * Used to lookup color name. Stores english and german name.
     */
    public class ColorNames {

        public int red, green, blue;

        public String germanName;

        public String englishName;

        public ColorNames(String englishName, String germanName, int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.germanName = englishName;
            this.englishName = englishName;
        }

        public int computeMSE(int pixR, int pixG, int pixB) {
            return (int) (((pixR - red) * (pixR - red) + (pixG - green) * (pixG - green) + (pixB - blue)
                    * (pixB - blue)) / 3);
        }

        public String getEnglishName() {
            return englishName;
        }

        public String getGermanName() {
            return germanName;
        }
    }
}