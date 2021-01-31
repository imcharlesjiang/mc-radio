import org.eclipse.jgit.api.*;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.util.Set;

public class startgit {
        //定义本地git路径
        public static final String LOCALPATH = "D:/music/";//此目录下的main文件夹内为你存放的音乐
        //.git文件路径
        public static final String LOCALGITFILE = LOCALPATH + ".git";
        //远程仓库地址
        public static final String REMOTEREPOURI = "https://github.com/imcharlesjiang/music.git";//将音乐存储到远程仓库上
        //操作git的用户名
        public static final String USER = "USERNAME";
        //密码
        public static final String PASSWORD = "PASSWORD";
        //git远程仓库服务器ip
        public static final String HOST = "github.com";
        public static void main(String[] args) {
            setupRepo();
            if(commitFiles())
                System.out.println("GIT提交成功");
            else
                System.out.println("GIT提交失败");
        }
        //建立与远程仓库的联系
        public static String setupRepo() {
            String msg = "";
            try {
                Git git = Git.cloneRepository()
                    .setURI(REMOTEREPOURI)
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider(USER, PASSWORD))
                    .setBranch("master")
                    .setDirectory(new File(LOCALPATH)).call();
                msg = "git init success！";
            } catch (Exception e) {
                msg = "git已经初始化！";
            }
            return msg;
        }
    //提交git
    public static boolean commitFiles() {
        Git git = null;
        try {
            git = Git.open(new File(LOCALGITFILE));
            AddCommand addCommand = git.add();
            //add操作 不是add -A
            addCommand.addFilepattern(".").call();

            RmCommand rm=git.rm();
            Status status=git.status().call();
            //循环add missing 的文件
            Set<String> missing=status.getMissing();
            for(String m : missing){
                rm.addFilepattern(m).call();
                //每次需重新获取rm status
                rm=git.rm();
                status=git.status().call();
            }
            //循环add remove 的文件
            Set<String> removed=status.getRemoved();
            for(String r : removed){
                rm.addFilepattern(r).call();
                rm=git.rm();
                status=git.status().call();
            }
            //提交
            git.add().addFilepattern(".").call();
            git.commit().setMessage("update").call();
            //推送
            git.push().setCredentialsProvider(new UsernamePasswordCredentialsProvider(USER, PASSWORD)).call();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}