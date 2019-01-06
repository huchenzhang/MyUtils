package com.example.huchenzhang.myutils.updateFile;


import android.text.TextUtils;
import android.util.Log;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hu on 2018/12/26.
 */

public class FTP {
    /**
     * 服务器名.
     */
    private String hostName;

    /**
     * 用户名.
     */
    private String userName;

    /**
     * 密码.
     */
    private String password;

    /**
     * FTP连接.
     */
    private FTPClient ftpClient;
    private static String LOCAL_CHARSET = "GBK";
    /**
     * 构造函数.
     *
     * @param host
     *            hostName 服务器名
     * @param user
     *            userName 用户名
     * @param pass
     *            password 密码
     */
    public FTP(String host, String user, String pass) {
        this.hostName = host;
        this.userName = user;
        this.password = pass;
        this.ftpClient = new FTPClient();
    }

    /**
     * 打开FTP服务.
     */
    public void openConnect(){
        try{
            ftpClient = new FTPClient();
            ftpClient.connect(hostName);
            if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
                Log.e("huchenzhang", "login");
                boolean status = ftpClient.login(userName,password);
                ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
                if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {
                    LOCAL_CHARSET = "UTF-8";
                }
                ftpClient.setControlEncoding(LOCAL_CHARSET);
            }
        }catch (Exception e) {
            e.printStackTrace();
            Log.d("huchenzhang", "Error: could not connect to host " + hostName);
        }
    }



    /**
     * 下载.
     * 1.先查看本地文件夹是否存在
     * 2.获取ftp上目录下所有文件
     * 3.遍历每个文件，
     *  a如果本地存在同名文件，执行fileIsReady()
     *  b如果不存在同名文件，直接下载就行了
     *
     * @param remotePath
     *            FTP目录
     * @param localPath
     *            本地目录
     * @return Result
     */
    public void downloadFile1(String remotePath, String localPath){
        try{
            // 更改FTP目录
            ftpClient.changeWorkingDirectory(remotePath);
            // 得到FTP当前目录下所有文件
            FTPFile[] ftpFiles = ftpClient.listFiles();
            Log.e("huchenzhang" ,"ftp上共：" + ftpFiles.length + "个文件");
            //遍历每个文件，挨个下载
            for (FTPFile ftpFile : ftpFiles){
                //获取本地文件路径
                File localFile = new File( localPath + new String(ftpFile.getName().getBytes(LOCAL_CHARSET)));
                //比较大小，如果本地文件存在并且大小与ftp上文件大小不一样，就删除从新下载，如果本地存在，并且大小与ftp上一样，就继续
                if (localFile.exists()){
                    Log.e("huchenzhang" ,"本地存在同名文件："+ localPath + new String(ftpFile.getName().getBytes(LOCAL_CHARSET)));
                    fileIsReady(localFile, ftpFile);
                }else {
                    Log.e("huchenzhang" ,"本地不存在同名文件，开始下载："+ localPath + new String(ftpFile.getName().getBytes(LOCAL_CHARSET)));
                    long start = System.currentTimeMillis();
                    boolean flag = downloadSingle(localFile , ftpFile);
                    long end = System.currentTimeMillis();
                    Log.e("huchenzhang" , flag ? "下载成功，共耗时：" + (end - start) : "下载失败，共耗时：" + (end - start));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ftp上存在与本地同名文件
     *  1.获取本地文件与ftp上文件最后的更新时间，和两端的文件大小
     *  2.如果ftp上文件最后的更新时间大于本地文件最后的更新时间或大小不等，就删除本地文件从新下载
     *  3.如果两者都不满足，就说明本文件不需要更新
     * @param localFile 本地文件夹下文件对象
     * @param ftpFile ft文件对象
     * @throws UnsupportedEncodingException
     */
    private void fileIsReady(File localFile, FTPFile ftpFile) throws UnsupportedEncodingException {
        //ftp上文件的大小
        long serverSize = ftpFile.getSize();
        //本地文件大小
        long localSize = localFile.length();
        //获取本地文件最后更新时间
        long localLastTime = localFile.lastModified();
        //获取ftp上文件最后更新时间
        long ftpLastTime = ftpFile.getTimestamp().getTime().getTime();

        //如果ftp上文件最后的更新时间大于本地文件最后的更新时间或大小不等，就删除本地文件从新下载
        if (ftpLastTime > localLastTime || serverSize != localSize) {
            Log.e("huchenzhang" ,"文件更新时间不等或文件大小不等");
            boolean a = localFile.delete();
            Log.e("huchenzhang" ,a ? "本地文件存在，删除成功，开始重新下载" : "本地文件存在，删除失败");

            long start = System.currentTimeMillis();
            boolean flag = downloadSingle(localFile , ftpFile);
            long end = System.currentTimeMillis();
            Log.e("huchenzhang" , flag ? "下载成功，共耗时：" + (end - start) : "下载失败" + (end - start));
        } else {
            Log.e("huchenzhang" ,  "本文件不需要更新");
        }
    }


    /**
     * 下载单个文件.
     *
     * @param localFile
     *            本地目录
     * @param ftpFile
     *            FTP目录
     * @return true下载成功, false下载失败
     * @throws IOException
     */
    private boolean downloadSingle(File localFile, FTPFile ftpFile){
        boolean a = false;
        try {
            // 创建输出流
            OutputStream outputStream = new FileOutputStream(localFile);
            // 下载单个文件
            // ftp需使用ISO-8859-1编码格式
//            String localFileName = new String(ftpFile.getName().getBytes("GBK"), "ISO-8859-1");
            String localFileName = new String(ftpFile.getName().getBytes(LOCAL_CHARSET), "ISO-8859-1");
            a = ftpClient.retrieveFile(localFileName, outputStream);
//            a = ftpClient.retrieveFile(localFileName, outputStream);
            // 关闭文件流
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return a;
    }

    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bytesToHexString(digest.digest());
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


    /**
     * 关闭FTP服务.
     *
     * @throws IOException
     */
    public void closeConnect() throws IOException {
        if (ftpClient != null) {
            if (ftpClient.isConnected()) {
                // 登出FTP
                ftpClient.logout();
                // 断开连接
                ftpClient.disconnect();
                Log.e("huchenzhang", "logout");
            }
        }
    }

}
