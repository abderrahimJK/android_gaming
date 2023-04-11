package ma.aitbouna.androidgame;

public class GameLoop implements Runnable{

    private GamePanel gamePanel;
    private Thread gameThread;

    public GameLoop(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        gameThread = new Thread(this);
    }

    @Override
    public void run() {

        long lastFPSScheck = System.currentTimeMillis();
        int fps = 0;
        while(true){
            gamePanel.update();
            gamePanel.render();
            fps++;

            long now = System.currentTimeMillis();
            if(now - lastFPSScheck >= 1000){
                System.out.println("FPS : "+fps);
                fps = 0;
                lastFPSScheck += 1000;
            }
        }
    }

    public void startGameLoog(){
        gameThread.start();
    }
}
