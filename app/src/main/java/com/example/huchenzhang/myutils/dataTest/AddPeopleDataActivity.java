package com.example.huchenzhang.myutils.dataTest;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import com.example.huchenzhang.myutils.databinding.ActivityAddPeopleBinding;
import org.litepal.LitePal;
import org.litepal.crud.callback.FindMultiCallback;
import org.litepal.crud.callback.SaveCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * 增加人员信息页面
 * Created by hu on 2018/12/24.
 */

public class AddPeopleDataActivity extends BaseActivity<ActivityAddPeopleBinding>{
    private int count = 0;
    public static String dbName = "listPeople.db";//数据库的名字
    private static String DATABASE_PATH = "/data/data/com.example.huchenzhang.myutils/databases/";//数据库在手机里的路径
    int alpha = 255;
    int b = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCountView(this, R.layout.activity_add_people);
        final MyThread thread = new MyThread();

        binding.btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.start();
            }
        });
        binding.btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearch();
            }
        });
        clearDatabases();

//        if(checkDataBase()){
//
//        }else{//不存在就把raw里的数据库写入手机
//            try{
//                copyDataBase();
//            }catch(IOException e){
//                throw new Error("Error copying database");
//            }
//        }
    }

    /**
     * *清除所有数据库(/data/data/包名/databases)
     */
    public void clearDatabases() {
        File file = new File(DATABASE_PATH);
        if (file != null && file.exists() && file.isDirectory()) {
            for (File item : file.listFiles()) {
                item.delete();
            }
        }
    }

    /**
     * 只删除文件夹下的文件
     *
     */
    private void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }


    /**
     * 判断数据库是否存在
     * @return false or true
     */
    public boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String databaseFilename = DATABASE_PATH + dbName;
            checkDB = SQLiteDatabase.openDatabase(databaseFilename, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){

        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    /**
     * 复制数据库到手机指定文件夹下
     * @throws IOException
     */
    public void copyDataBase() throws IOException{
        String databaseFilenames = DATABASE_PATH + dbName;
        File dir = new File(DATABASE_PATH);
        if(!dir.exists())//判断文件夹是否存在，不存在就新建一个
            dir.mkdir();
        FileOutputStream os = null;
        try{
            os = new FileOutputStream(databaseFilenames);//得到数据库文件的写入流
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        InputStream is = getResources().openRawResource(R.raw.new_order);//得到数据库文件的数据流
        byte[] buffer = new byte[8192];
        int count = 0;
        try{
            while((count = is.read(buffer)) > 0){
                os.write(buffer, 0, count);
                os.flush();
            }
        }catch(IOException e){

        }
        try{
            is.close();
            os.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    private void onSearch(){
        String name = binding.et1.getText().toString();
//        int age = Integer.parseInt(binding.et2.getText().toString());
//        int gender = Integer.parseInt(binding.et3.getText().toString());
//        long birthDate = Long.parseLong(binding.et4.getText().toString());
        String icon =  binding.et5.getText().toString();
        String address = binding.et6.getText().toString();
        String content =  binding.et7.getText().toString();
        String idCard =  binding.et8.getText().toString();
        String str1 =  binding.et9.getText().toString();
        String str2 =  binding.et10.getText().toString();
        String str3 =  binding.et11.getText().toString();
        String str4 =  binding.et12.getText().toString();
        String str5 =  binding.et13.getText().toString();
        String str6 =  binding.et14.getText().toString();
        String str7 =  binding.et15.getText().toString();
        StringBuffer sql = new StringBuffer();
        //姓名
        if (!TextUtils.isEmpty(name)){
            sql.append("name like "+  "'" + "%" + name + "%" + "'" );
        }
        //年龄
        if (!TextUtils.isEmpty(binding.et2.getText().toString())){
            if (sql.length() > 0){
                sql.append(" and ");
            }
            sql.append("age = "+ Integer.parseInt(binding.et2.getText().toString()));
        }
        //性别
        if (!TextUtils.isEmpty(binding.et3.getText().toString())){
            if (sql.length() > 0){
                sql.append(" and ");
            }
            sql.append("gender = "+ Integer.parseInt(binding.et3.getText().toString()));
        }
        //生日
        if (!TextUtils.isEmpty(binding.et4.getText().toString())){
            if (sql.length() > 0){
                sql.append(" and ");
            }
            sql.append("birthDate = "+ Long.parseLong(binding.et4.getText().toString()));
        }
        //头像
        if (!TextUtils.isEmpty(icon)){
            if (sql.length() > 0){
                sql.append(" and ");
            }
            sql.append("icon = " +  "'" + icon +  "'" );
        }
        //住址
        if (!TextUtils.isEmpty(address)){
            if (sql.length() > 0){
                sql.append(" and ");
            }
            sql.append("address = " + "'" + address + "'");
        }
        //内容
        if (!TextUtils.isEmpty(content)){
            if (sql.length() > 0){
                sql.append(" and ");
            }
            sql.append("content = " +  "'" + content +  "'" );
        }
        //身份证号
        if (!TextUtils.isEmpty(idCard)){
            if (sql.length() > 0){
                sql.append(" and ");
            }
            sql.append("idCard = " +   "'" + idCard +  "'");
        }

        //str1
        if (!TextUtils.isEmpty(str1)){
            if (sql.length() > 0){
                sql.append(" and ");
            }
            sql.append("str1 = "+ "'" + str1 +  "'");
        }

        //str2
        if (!TextUtils.isEmpty(str2)){
            if (sql.length() > 0){
                sql.append(" and ");
            }
            sql.append("str2 = "+  "'" + str2 +  "'");
        }

        //str3
        if (!TextUtils.isEmpty(str3)){
            if (sql.length() > 0){
                sql.append(" and ");
            }
            sql.append("str3 = "+ "'" +  str3 +  "'");
        }

        //str4
        if (!TextUtils.isEmpty(str4)){
            if (sql.length() > 0){
                sql.append(" and ");
            }
            sql.append("str4 = "+  "'" + str4 +  "'");
        }

        //str5
        if (!TextUtils.isEmpty(str5)){
            if (sql.length() > 0){
                sql.append(" and ");
            }
            sql.append("str5 = "+  "'" + str5 +  "'");
        }

        //str6
        if (!TextUtils.isEmpty(str6)){
            if (sql.length() > 0){
                sql.append(" and ");
            }
            sql.append("str6 = "+ "'" + str6 +  "'");
        }

        //str7
        if (!TextUtils.isEmpty(str7)){
            if (sql.length() > 0){
                sql.append(" and ");
            }
            sql.append("str7 = "+ "'" + str7 +  "'");
        }
        if (sql.length() == 0){
            binding.tvResult.setText("请填写搜索内容");
            return;
        }
        String str = sql.toString();
        Log.e("huchenzhang",str);
        final Long start = System.currentTimeMillis();
        showLoadingDialog();
        LitePal.where(str).findAsync(PeopleData.class).listen(new FindMultiCallback() {
            @Override
            public <T> void onFinish(List<T> t) {
                dismissLoadingDialog();
                List<PeopleData> list = (List<PeopleData>)t;
                Long end = System.currentTimeMillis();
                Log.e("huchenzhang","匹配结果共计： " + list.size() + "个");
                if (list != null && !list.isEmpty()){
                    Log.e("huchenzhang","输出结果：name = " + list.get(0).getName());
                    Log.e("huchenzhang","\n共耗时 = " + (end - start));
                    binding.tvResult.setText("匹配结果共计： " + list.size() + "个" + "\n输出结果：name = " + list.get(0).getName() + "\n共耗时 = " + (end - start));
                }else {
                    binding.tvResult.setText("无匹配结果" + "\n共耗时 = " + (end - start));
                }
            }
        });
    }


    private void getData(){
        PeopleData peopleData = new PeopleData();
        if (count >= 300000){
            return;
        }
        peopleData.setName(UUID.randomUUID().toString());
        peopleData.setAge(count);
        peopleData.setGender(count);
        peopleData.setBirthDate(count);
        peopleData.setIcon(UUID.randomUUID().toString());
        peopleData.setAddress(UUID.randomUUID().toString());
        peopleData.setContent(UUID.randomUUID().toString());
        peopleData.setIdCard(UUID.randomUUID().toString());
        peopleData.setStr1(UUID.randomUUID().toString());
        peopleData.setStr2(UUID.randomUUID().toString());
        peopleData.setStr3(UUID.randomUUID().toString());
        peopleData.setStr4(UUID.randomUUID().toString());
        peopleData.setStr5(UUID.randomUUID().toString());
        peopleData.setStr6(UUID.randomUUID().toString());
        peopleData.setStr7(UUID.randomUUID().toString());
        peopleData.saveAsync().listen(new SaveCallback() {
            @Override
            public void onFinish(boolean success) {
                if (success){
                    ++count;
                    Log.e("huchenzhang",count + "");
                    getData();
                }
            }
        });
    }

    private class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            getData();
        }
    }

}
