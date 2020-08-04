package ru.vladrus13.RPG.core.graphics;

import ru.vladrus13.RPG.core.utils.exception.GameException;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vladkuznetsov
 * Font service
 */
public class FontService {
    /**
     * Fonts map
     */
    final Map<String, Font> fonts;

    /**
     * Constructor
     */
    public FontService() {
        fonts = new HashMap<>();
    }

    /**
     * Create font
     *
     * @param path path to file
     * @return {@link Font}
     * @throws GameException if we can't find file, or this is incorrect for this OS font
     */
    private Font createFontFromFile(String path) throws GameException {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("resources/assets/fonts/" + path + ".ttf"));
        } catch (FontFormatException | IOException e) {
            throw new GameException("Error on loading font: " + path, e);
        }
    }

    /**
     * Get font from created
     *
     * @param name name of font
     * @return {@link Font}
     * @throws GameException if we not found this font and choose create this, but on creating we 1. Can't find file. 2. Incorrect for this OS font
     */
    public Font getFont(String name) throws GameException {
        if (!fonts.containsKey(name)) {
            fonts.put(name, createFontFromFile(name));
        }
        return fonts.get(name);
    }

    /**
     * Get font from created
     *
     * @param name name of font
     * @param size size of font
     * @return {@link Font}
     * @throws GameException if we not found this font and choose create this, but on creating we 1. Can't find file. 2. Incorrect for this OS font
     */
    public Font getFont(String name, int size) throws GameException {
        return getFont(name).deriveFont(Font.PLAIN, size);
    }

    /**
     * Get font width
     *
     * @param text     text
     * @param font     {@link Font}
     * @param graphics {@link Graphics}
     * @return width
     */
    public int fontWidth(String text, Font font, Graphics graphics) {
        return graphics.getFontMetrics(font).stringWidth(text);
    }

    /**
     * Split text by length 700
     *
     * @param text     text
     * @param font     {@link Font}
     * @param graphics {@link Graphics}
     * @return array of text
     */
    public ArrayList<String> splitByWidth(String text, Font font, Graphics graphics) {
        ArrayList<String> answer = new ArrayList<>();
        int current = 0, last = 0;
        while (current != text.length()) {
            if (graphics.getFontMetrics(font).stringWidth(text.substring(last, current)) > 700) {
                answer.add(text.substring(last, current));
                last = current;
            }
            current++;
        }
        answer.add(text.substring(last, current));
        return answer;
    }
}
