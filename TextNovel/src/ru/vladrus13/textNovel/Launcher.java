package ru.vladrus13.textNovel;

import ru.vladrus13.textNovel.core.Action;
import ru.vladrus13.textNovel.core.MainForm;

public class Launcher {

    public static void main(String[] args) {
        MainForm.main(new Action("Выбери персонажа", "Автор", new String[]{"Маг", "Воин", "Вор", "Король"}, new Action[]{
                new Action("Ты проиграл", "Автор", new String[0], new Action[0]),
                new Action("Ты проиграл", "Автор", new String[0], new Action[0]),
                new Action("Ты проиграл", "Автор", new String[0], new Action[0]),
                new Action("Ты проиграл", "Автор", new String[0], new Action[0])
        }));
    }
}

// "null", "not", new String[4], new Action[4])
// new Action("null", "not", new String[4], new Action[4])