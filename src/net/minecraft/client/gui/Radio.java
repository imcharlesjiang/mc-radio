package net.minecraft.client.gui;

import java.io.*;
import java.net.Socket;
import java.io.InputStream;
import java.net.URL;
import javazoom.jl.player.Player;

public class Radio extends Thread {
    private String url;//服务端范围的音乐地址
    private boolean loop;
    private Player starplayer;
    private int frames=2147483647;//服务端返回的当前播放进度(未实现)
    static boolean enabled = false;
    static boolean playing = false;
    static Radio star;

    public Radio(String url, boolean loop) {
        this.url = url;
        this.loop = loop;
    }

    public void run() {
        while(true) {
            try {
                enabled = true;
                System.out.println("Playing STAR");
                get();
                InputStream buff = (new URL(this.url)).openStream();
                this.starplayer = new Player(buff);
                playing = true;
                this.starplayer.play();
                playing = false;
                enabled = false;
                if (this.loop) {
                    continue;
                }
            } catch (Exception var2) {
                System.out.println("Error");
                playing = false;
                enabled = false;
            }

            return;
        }
    }

    public void close() {
        this.loop = false;
        this.starplayer.close();
        this.interrupt();
        playing = false;
        enabled = false;
    }

    public static void enable() {
        if (!enabled) {
            enabled = true;
            star = new Radio("https://XXX.XXX.com/1.MP3", true);//备用的mp3地址,取到的放不出来临时用这个
            star.start();
        }

    }

    public static void disable() {
        if (enabled && playing) {
            star.close();
        }

    }

    public static void toggle() {
        if (!enabled) {
            enable();
        } else {
            disable();
        }
    }
    public void get() {
        try
        {
            //1、创建客户端Socket，指定服务器端口号和地址
            Socket socket = new Socket("XXX.XXX.com",11005);//XXX.XXX.com为你服务端所在服务器的地址
            //2、获取输出流,向服务器发送信息
            OutputStream os = socket.getOutputStream();
            //字节输出流
            PrintWriter pw = new PrintWriter(os);
            //将输出流包装为打印流
            pw.write("Getting");
            pw.flush();
            socket.shutdownOutput();
            //关闭输出流
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String info = null;
            //循环读取
            for(int i=0;(info = br.readLine()) !=null;i++)
            {
                if(i==0)
                    this.url=info;
                if(i==1)
                    this.frames=Integer.parseInt(info);
            }
            System.out.println("Playing:"+this.url+"::"+this.frames);
            br.close();
            is.close();
            isr.close();
            pw.close();
            os.close();
            socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
