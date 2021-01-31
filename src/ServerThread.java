import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
public  class  ServerThread extends Thread {
    //和本线程相关的Socket
    Socket socket = null;
    String url=null; //准备发送给客户端的音乐名称
    int frames=2147483647;
    public ServerThread(Socket socket,int frames,String url){
        this.socket = socket;
        this.url=url;
        this.frames=frames;
    }
    //线程执行的操作，响应客户端的请求
    public void run(){
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        OutputStream os = null;
        PrintWriter pw = null;
        InetAddress address = socket.getInetAddress();
        System.out.println( address.getHostAddress()+" Connecting");
        try
        {
            //获取一个输入流，并读取客户端的信息
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            //将字节流转化为字符流
            br = new BufferedReader(isr);
            //添加缓冲
            String info = null;
            //循环读取数据
            while((info = br.readLine()) !=null){
                System.out.println(address.getHostAddress()+":"+info);
            }
            socket.shutdownInput();
            //关闭输入流
            //获取输出流，响应客户端的请求
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            //包装为打印流
            pw.println("https://XXX.XXX.com/main/"+getURLEncoderString(url).replaceAll("\\+", "%20"));//XXX.XXX.com为你音乐存放的根目录,相当于本地的music文件夹
            pw.flush();
            pw.println(frames);
            pw.flush();
            //将缓存输出
            System.out.println(address.getHostAddress() + " has gotten " + "https://XXX.XXX.com/main/"+ url + " playing " + frames);//XXX.XXX.com为你音乐存放的根目录,相当于本地的music文件夹
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally{
            try
            {
                if(pw !=null)
                    pw.close();
                if(os !=null)
                    os.close();
                if(is !=null)
                    is.close();
                if(isr !=null)
                    isr.close();
                if(br !=null)
                    br.close();
                if(socket !=null)
                    socket.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //将URL转码防止客户端接收到乱码
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}