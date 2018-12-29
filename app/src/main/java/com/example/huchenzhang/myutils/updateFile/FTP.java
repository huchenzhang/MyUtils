package com.example.huchenzhang.myutils.updateFile;


import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.io.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public boolean openConnect(){
        try{
            ftpClient = new FTPClient();
            ftpClient.connect(hostName,21);
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

                return status;
            }
        }catch (Exception e) {
            e.printStackTrace();
            Log.d("huchenzhang", "Error: could not connect to host " + hostName);
        }
        return true;
    }



    /**
     * 下载.
     *
     * @param remotePath
     *            FTP目录
     * @param localPath
     *            本地目录
     * @return Result
     * @throws IOException
     */
    public void downloadFile1(String remotePath, String localPath){
        try{
            File localFile = new File(localPath);
            if (!localFile.exists()){
                localFile.mkdirs();
            }
            // 更改FTP目录
            ftpClient.changeWorkingDirectory(remotePath);
            //获取文件最后修改时间
            String dateString = ftpClient.getModificationTime(remotePath);
            if (!TextUtils.isEmpty(dateString)){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                Date modificationDate = dateFormat.parse(dateString.substring(dateString.indexOf(" ") + 1));
            }
            // 得到FTP当前目录下所有文件
            FTPFile[] ftpFiles = ftpClient.listFiles();
            Log.e("huchenzhang" ,"ftp上共：" + ftpFiles.length + "个文件");
            //遍历每个文件，挨个下载
            for (FTPFile ftpFile : ftpFiles){
                Date date = ftpFile.getTimestamp().getTime();
                Long l = date.getTime();
                //获取本地文件路径
                File file = new File( localPath + new String(ftpFile.getName().getBytes(LOCAL_CHARSET)));
//                File file = new File( localPath + ftpFile.getName());
//                Log.e("huchenzhang" ,"开始检查 ：" + localPath + ftpFile.getName());
                Log.e("huchenzhang" ,"开始检查 ：" + localPath + new String(ftpFile.getName().getBytes(LOCAL_CHARSET)));
                //比较大小，如果本地文件存在并且大小与ftp上文件大小不一样，就删除从新下载，如果本地存在，并且大小与ftp上一样，就继续
                long serverSize = ftpFile.getSize();
                if (file.exists()){
                    long localSize = file.length();// 如果本地文件存在，获取本地文件的长度
                    if (localSize != serverSize) {
//                        file = new File(localPath + ftpFile.getName());
                        file = new File(localPath + new String(ftpFile.getName().getBytes(LOCAL_CHARSET)));
                        boolean a = file.delete();
                        Log.e("huchenzhang" ,a ? "本地文件存在，删除成功，开始重新下载" : "本地文件存在，删除失败");

                        long start = System.currentTimeMillis();
                        boolean flag = downloadSingle(file , ftpFile);
                        long end = System.currentTimeMillis();
                        Log.e("huchenzhang" , flag ? "下载成功，共耗时：" + (end - start) : "下载失败" + (end - start));
                    }else{
                        Log.e("huchenzhang" ,"本地文件存在，并且与服务器上文件大小一致，开始下载下一个");
                    }
                }else {
                    long start = System.currentTimeMillis();
                    boolean flag = downloadSingle(file , ftpFile);
                    long end = System.currentTimeMillis();
                    Log.e("huchenzhang" , flag ? "下载成功，共耗时：" + (end - start) : "下载失败，共耗时：" + (end - start));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
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
    public boolean downloadSingle(File localFile, FTPFile ftpFile){
        boolean a = false;
        try {
            // 创建输出流
            OutputStream outputStream = new FileOutputStream(localFile);
            // 下载单个文件
            a = ftpClient.retrieveFile(localFile.getName(), outputStream);
            // 关闭文件流
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return a;
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



//    /**
//     * 列出FTP下所有文件.
//     *
//     * @param remotePath
//     *            服务器目录
//     * @return FTPFile集合
//     * @throws IOException
//     */
//    public List<FTPFile> listFiles(String remotePath) throws IOException {
//        if (ftpClient != null) {
//            // 获取文件
//            try {
//                FTPFile[] files = ftpClient.listFiles(remotePath);
//                if (files != null && files.length > 0) {
//                    // 遍历并且添加到集合
//                    for (FTPFile file : files) {
//                        list.add(file);
//                    }
//                }
//            } catch (Exception e) {
//                Log.e("huchenzhang", "请稍等...");
//            }
//        }
//        return list;
//    }
//
//    // 实现下载文件功能，可实现断点下载
//    public synchronized boolean downloadFile(String localPath, String serverPath) throws Exception {
//        // 先判断服务器文件是否存在
//
//        FTPFile[] files = ftpClient.listFiles(serverPath);
//        if (files.length == 0) {
//            Log.e("huchenzhang" ,"服务器文件不存在");
//            return false;
//        }
//        Log.e("huchenzhang" ,"远程文件存在,名字为：" + files[0].getName());
//        localPath = localPath + files[0].getName();
//        // 接着判断下载的文件是否能断点下载
//        long serverSize = files[0].getSize(); // 获取远程文件的长度
//        File localFile = new File(localPath);
//        long localSize = 0;
//        if (localFile.exists()) {
//            localSize = localFile.length(); // 如果本地文件存在，获取本地文件的长度
//            if (localSize >= serverSize) {
//                Log.e("huchenzhang" ,"文件已经下载完了");
//                File file = new File(localPath);
//                file.delete();
//                Log.e("huchenzhang" ,"本地文件存在，删除成功，开始重新下载");
//                return false;
//            }
//        }
//        // 进度
//        long step = serverSize / 100;
//        long process = 0;
//        long currentSize = 0;
//
//
//        ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
////        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//
//        OutputStream out = new FileOutputStream(localFile, true);
//        ftpClient.setRestartOffset(localSize);
//        // 开始准备下载文件
//        ftpClient.enterLocalActiveMode();
////        InputStream input = ftpClient.retrieveFileStream(serverPath);
//        InputStream input = ftpClient.retrieveFileStream(new String(serverPath.getBytes("GBK"), "ISO-8859-1"));
//        byte[] b = new byte[1024];
//        int length = 0;
//        while ((length = input.read(b)) != -1) {
//            out.write(b, 0, length);
//            currentSize = currentSize + length;
//            if (currentSize / step != process) {
//                process = currentSize / step;
//                if (process % 10 == 0) {
//                    Log.e("huchenzhang" ,"下载进度：" + process);
//                }
//            }
//        }
//        out.flush();
//        out.close();
//        input.close();
//        // 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
//        ftpClient.getReply();
//        // 此方法是来确保流处理完毕，如果没有此方法，可能会造成现程序死掉
//        if (ftpClient.completePendingCommand()) {
//            Log.e("huchenzhang" ,"文件下载成功");
//            return true;
//        } else {
//            Log.e("huchenzhang" ,"文件下载失败");
//            return false;
//        }
//    }

//    /**
//     * 下载多个文件.
//     *
//     * @param localFile
//     *            本地目录
//     * @return true下载成功, false下载失败
//     * @throws IOException
//     */
//    private boolean downloadMany(File localFile) throws IOException {
//        boolean flag = true;
//        // FTP当前目录
//        if (!currentPath.equals(REMOTE_PATH)) {
//            currentPath = currentPath + REMOTE_PATH + localFile.getName();
//        } else {
//            currentPath = currentPath + localFile.getName();
//        }
//        // 创建本地文件夹
//        localFile.mkdir();
//        // 更改FTP当前目录
//        ftpClient.changeWorkingDirectory(currentPath);
//        // 得到FTP当前目录下所有文件
//        FTPFile[] ftpFiles = ftpClient.listFiles();
//        // 循环遍历
//        for (FTPFile ftpFile : ftpFiles) {
//            // 创建文件
//            File file = new File(localFile.getPath() + "/" + ftpFile.getName());
//            if (ftpFile.isDirectory()) {
//                // 下载多个文件
//                flag = downloadMany(file);
//            } else {
//                // 下载单个文件
//                flag = downloadSingle(file, ftpFile);
//            }
//        }
//        return flag;
//    }


//    /**
//     * 上传单个文件.
//     *
//     * @param localFile
//     *            本地文件
//     * @return true上传成功, false上传失败
//     * @throws IOException
//     */
//    private boolean uploadingSingle(File localFile) throws IOException {
//        boolean flag = true;
//        // 创建输入流
//        InputStream inputStream = new FileInputStream(localFile);
//        // 统计流量
//        response += (double) inputStream.available() / 1;
//        // 上传单个文件
//        flag = ftpClient.storeFile(localFile.getName(), inputStream);
//        // 关闭文件流
//        inputStream.close();
//        return flag;
//    }
//
//    /**
//     * 上传多个文件.
//     *
//     * @param localFile
//     *            本地文件夹
//     * @return true上传成功, false上传失败
//     * @throws IOException
//     */
//    private boolean uploadingMany(File localFile) throws IOException {
//        boolean flag = true;
//        // FTP当前目录
//        if (!currentPath.equals(REMOTE_PATH)) {
//            currentPath = currentPath + REMOTE_PATH + localFile.getName();
//        } else {
//            currentPath = currentPath + localFile.getName();
//        }
//        // FTP下创建文件夹
//        ftpClient.makeDirectory(currentPath);
//        // 更改FTP目录
//        ftpClient.changeWorkingDirectory(currentPath);
//        // 得到当前目录下所有文件
//        File[] files = localFile.listFiles();
//        // 遍历得到每个文件并上传
//        for (File file : files) {
//            if (file.isHidden()) {
//                continue;
//            }
//            if (file.isDirectory()) {
//                // 上传多个文件
//                flag = uploadingMany(file);
//            } else {
//                // 上传单个文件
//                flag = uploadingSingle(file);
//            }
//        }
//        return flag;
//    }

}
