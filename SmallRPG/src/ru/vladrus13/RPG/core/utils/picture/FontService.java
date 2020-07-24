package ru.vladrus13.RPG.core.utils.picture;

import ru.vladrus13.RPG.core.utils.exception.GameException;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class FontService {
    final Map<String, Font> fonts;

    public FontService() {
        fonts = new HashMap<>();
    }

    private Font createFontFromFile(String path) throws GameException {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("assets/fonts/" + path + ".ttf"));
        } catch (FontFormatException | IOException e) {
            throw new GameException("Error on loading font: " + path, e);
        }
    }

    public Font getFont(String name) throws GameException {
        if (!fonts.containsKey(name)) {
            fonts.put(name, createFontFromFile(name));
        }
        return fonts.get(name);
    }

    public Font getFont(String name, int size) throws GameException {
        return getFont(name).deriveFont(Font.PLAIN, size);
    }

    public int fontWidth(String text, Font font, Graphics graphics) {
        return graphics.getFontMetrics(font).stringWidth(text);
    }

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
