package ru.vladrus13.RPG.core.main.dialog;

import ru.vladrus13.RPG.core.person.Person;
import ru.vladrus13.RPG.core.utils.picture.Drawing;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.picture.FontService;
import ru.vladrus13.RPG.core.utils.picture.PictureService;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.ArrayList;

public class Monologue extends Drawing {

    private ArrayList<String> text;
    private final String fullText;
    private final Font PixelFontGame;
    private final Font WriterFont;
    private final Person author;
    private final BufferedImage faceAuthor;
    private final FontService fontService;

    public Monologue(String text, Person author, DungeonService dungeonService) throws GameException {
        fontService = dungeonService.getFontService();
        PixelFontGame = fontService.getFont("PixelFontGame", 36);
        WriterFont = fontService.getFont("WriterFont", 36);
        faceAuthor = new PictureService().loadImage(Path.of("assets/pictures/faces/" + author.getName().toLowerCase() + "/Usual.png"));
        this.author = author;
        this.fullText = text;
        this.text = new ArrayList<>();
    }

    @Override
    public void draw(Graphics graphics) {
        text = fontService.splitByWidth(fullText, PixelFontGame, graphics);
        graphics.setColor(new PictureService().getDialogColor());
        graphics.fillRect(0, 500, 800, 300);
        graphics.setColor(Color.BLACK);
        graphics.setFont(WriterFont);
        graphics.drawString(author.getName(), 20, 520);
        graphics.setFont(PixelFontGame);
        for (int i = 0; i < text.size(); i++) {
            graphics.drawString(text.get(i), 20, 558 + i * 38);
        }
        graphics.drawImage(faceAuthor, 684, 442, 96, 96, null);
    }
}
