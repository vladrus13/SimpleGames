package ru.vladrus13.RPG.core.main.dialog;

import ru.vladrus13.RPG.core.person.Person;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.graphics.ColorService;
import ru.vladrus13.RPG.core.graphics.Drawing;
import ru.vladrus13.RPG.core.graphics.FontService;
import ru.vladrus13.RPG.core.graphics.PictureService;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Monologue class for dialogs
 */
public class Monologue extends Drawing {

    /**
     * Texts, parsed
     */
    private ArrayList<String> text;
    /**
     * Text, not parsed
     */
    private final String fullText;
    /**
     * {@link Font} pixel font for author
     */
    private final Font PixelFontGame;
    /**
     * {@link Font} font for texts
     */
    private final Font WriterFont;
    /**
     * {@link Person} author
     */
    private final Person author;
    /**
     * Face for monologue
     */
    private final BufferedImage faceAuthor;
    /**
     * {@link FontService} for fonts
     */
    private final FontService fontService;

    /**
     * Constructor for class
     *
     * @param text           text
     * @param author         {@link Person} author
     * @param dungeonService {@link DungeonService}
     * @throws GameException if load of font or image is failed
     */
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
        graphics.setColor(ColorService.dialogColor);
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
