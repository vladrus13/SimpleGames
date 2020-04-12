package ru.vladrus13.core.utils.picture;

import ru.vladrus13.core.utils.exception.GameException;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FontService {
    Map<String, Font> fonts;

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
}
