package karabaka.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import karabaka.game.client.KarabakaClient;
import karabaka.game.client.utils.GameSettings;
import karabaka.game.server.KarabakaServer;

public class DesktopLauncher {

    private static final boolean RUN_AS_SERVER = true;

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = GameSettings.GAME_HEIGHT;
        config.width = GameSettings.GAME_WIDHT;

        if (RUN_AS_SERVER)
            new LwjglApplication(new KarabakaServer(), config);
        else
            new LwjglApplication(new KarabakaClient(), config);
    }
}
