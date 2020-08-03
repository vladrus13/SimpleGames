/**
 * Module with core and game
 */
@SuppressWarnings("JavaModuleNaming")
module ru.vladrus13.RPG {
    requires java.desktop;
    requires java.logging;

    requires com.goxr3plus.streamplayer;

    exports ru.vladrus13.RPG;
    opens ru.vladrus13.RPG;
}