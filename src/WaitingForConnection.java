import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Random;

public class WaitingForConnection extends Thread {
    Socket socket = null;
    String name = null;
    int frames = 2147483647;
    static String mainPath="D:\\music\\main";
    static String[] originlist=new File(mainPath).list();
    public WaitingForConnection() {

    }

    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(11005);
            Socket socket = null;
            final Player[] playeri = new Player[1];
                try {
                    new Thread(() -> {
                        //调用播放方法进行播放
                        while(true) {
                            name=originlist[new Random(new Random().nextInt(10000)).nextInt(this.originlist.length)];
                            FileInputStream inputStream = null;
                            BufferedInputStream bufferedInputStream = null;
                            try {
                                //声明一个File对象
                                File mp3 = new File(mainPath+'\\'+name);
                                //创建一个输入流
                                inputStream = new FileInputStream(mp3);
                                //创建一个缓冲流
                                bufferedInputStream = new BufferedInputStream(inputStream);
                                //创建播放器对象，把文件的缓冲流传入进去
                                final Player player = new Player(bufferedInputStream);
                                playeri[0] =player;
                                player.play();
                            } catch (JavaLayerException | FileNotFoundException e) {
                                e.printStackTrace();
                            }finally {
                                try {
                                    inputStream.close();
                                    bufferedInputStream.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            while (true)
            {
                //调用accept()方法开始监听，等待客户端的链接
                socket = serverSocket.accept();
                //创建一个新的线程
                frames = playeri[0].getPosition();
                ServerThread serverThread = new ServerThread(socket, frames, name);
                //启动线程
                serverThread.start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
