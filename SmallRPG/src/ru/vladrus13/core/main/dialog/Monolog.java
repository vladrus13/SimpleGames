package ru.vladrus13.core.main.dialog;

import ru.vladrus13.core.person.Person;
import ru.vladrus13.core.utils.Drawing;
import ru.vladrus13.core.utils.GameService;
import ru.vladrus13.core.utils.exception.GameException;
import ru.vladrus13.core.utils.picture.FontService;
import ru.vladrus13.core.utils.picture.PictureService;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;

public class Monolog implements Drawing {

    private final String text;
    private final Font PixelFontGame;
    private final Font WriterFont;
    private final Person author;
    private final BufferedImage faceAuthor;

    public Monolog(String text, Person author, GameService gameService) throws GameException {
        FontService fontService = gameService.getFontService();
        PixelFontGame = fontService.getFont("PixelFontGame", 36);
        WriterFont = fontService.getFont("WriterFont", 36);
        faceAuthor = new PictureService().loadImage(Path.of("assets/pictures/faces/" + author.getName().toLowerCase() + "/Usual.png"));
        this.author = author;
        this.text = text;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new PictureService().getDialogColor());
        graphics.fillRect(0, 500, 800, 300);
        graphics.setColor(Color.BLACK);
        graphics.setFont(WriterFont);
        graphics.drawString(author.getName(), 20, 520);
        graphics.setFont(PixelFontGame);
        graphics.drawString(text, 20, 558);
        graphics.drawImage(faceAuthor, 684, 442, 96, 96, null);
    }
}
