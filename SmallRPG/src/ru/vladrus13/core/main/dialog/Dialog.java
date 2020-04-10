package ru.vladrus13.core.main.dialog;

import ru.vladrus13.core.utils.Drawing;
import ru.vladrus13.core.utils.picture.PictureService;

import java.awt.*;

public class Dialog implements Drawing {
    private String[] text;
    int current;

    public Dialog(String[] text) {
        this.text = text;
        current = 0;
    }

    public boolean hasNext() {
        return current + 1 < text.length;
    }

    public void next() {
        if (hasNext()) current++;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new PictureService().getDialogColor());
        graphics.fillRect(0, 500, 800, 300);
        graphics.setColor(Color.BLACK);
        graphics.drawString(text[current], 20, 520);
    }
}
