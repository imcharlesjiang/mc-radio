import java.io.*;
public class Server {
    static String mainPath="D:\\music\\main";
    static String[] originlist=new File(mainPath).list();
    public static void main(String[] args) {
            for(int j=0;j<originlist.length;j++)
            {
                System.out.println(j + " " + originlist[j]);
            }
            System.out.println("等待GIT提交......");
            startgit.main(args);//将music文件夹的内容提交至远程仓库,然后自动部署到静态页面
            System.out.println("***服务器即将启动，等待客户端的链接***");
            //循环监听等待客户端的链接
            WaitingForConnection waitingforconnection=new WaitingForConnection();
            waitingforconnection.start();
            System.out.println("服务器线程已创建");
    }

}