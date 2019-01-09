package com.example.huchenzhang.myutils.myPoi;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import com.example.huchenzhang.myutils.databinding.ActivityMyPoiBinding;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * poi读取各种文件
 * Created by hu on 2019/1/9.
 */

public class PoiActivity extends BaseActivity<ActivityMyPoiBinding>{

    private  PoiAdapter adapter;
    private static final int TYPE_DOCX = 0;
    private static final int TYPE_DOC = 1;
    private static final int TYPE_XLSX = 2;
    private static final int TYPE_PDF = 3;
    private static final int TYPE_UNKNOW = -1;
    private String localPath = Environment.getExternalStorageDirectory().getPath()+"/hu/";
    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCountView(this, R.layout.activity_my_poi);
        initView();
        initData();
    }

    /**
     * 初始化view
     */
    private void initView(){
        adapter = new PoiAdapter(this,getAllFiles(localPath,""));
        binding.rv1.setLayoutManager(new LinearLayoutManager(this));
        binding.rv1.setAdapter(adapter);

    }

    private void initData(){
        adapter.setOnItemClickListener(new PoiAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view,String data, int position) {
             binding.tv1.setText(readDoc(data));
            }
        });
    }

    private String readDoc(String pathname ){
        String content = "";
        HWPFDocument doc = null;
        doc = openDocFromPath(pathname);

        Range r = doc.getRange();
        content = r.text();
        r.delete();
        return content;
    }

    /*
     * Fuction:	openDocFormPath
     * Open a file use file path
     * Input: 	pathname
     * Return:	A HWPFdocument object
     * Author:	j.ghang@hotmail.com 2013.05.22
     */
    private HWPFDocument openDocFromPath(String pathname) {
        File docFile = null;
        InputStream fileInputS = null;
        HWPFDocument doc = null;
        docFile	= new File(pathname);
        try {
            fileInputS = new FileInputStream(docFile);
        } catch (FileNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

        try {
            doc = new HWPFDocument(fileInputS);
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return doc;
    }


    /**
     * 根据文件后缀判断文件类型
     * @param path 文件路径
     * @return int
     */
    public static int getFileType(String path) {
        path = path.toLowerCase();
        if (path.endsWith(".docx")) {
            return TYPE_DOCX;
        } else if (path.endsWith(".doc")) {
            return TYPE_DOC;
        } else if (path.endsWith(".xlsx")) {
            return TYPE_XLSX;
        } else if (path.endsWith(".pdf")) {
            return TYPE_PDF;
        }else {
            return TYPE_UNKNOW;
        }
    }

    /**
     * 获取指定目录内所有文件路径
     * @param dirPath 需要查询的文件目录
     */
    public static List<String> getAllFiles(String dirPath, String type) {
        File f = new File(dirPath);
        if (!f.exists()) {//判断路径是否存在
            return null;
        }
        File[] files = f.listFiles();
        if(files == null){//判断权限
            return null;
        }

        List<String> dataList = new ArrayList<>();
        for (File file : files) {//遍历目录
//            if(file.isFile() && file.getName().endsWith(type)){
            if(file.isFile() && getFileType(file.getName()) != -1 ){
                String _name = file.getName();
                String filePath = file.getAbsolutePath();//获取文件路径
                String fileName = file.getName().substring(0,_name.length()-4);//获取文件名
                try {
                    dataList.add(filePath);
//                    JSONObject _fInfo = new JSONObject();
//                    _fInfo.put("name", fileName);
//                    _fInfo.put("path", filePath);
//                    fileList.put(_fInfo);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
//            else if(_file.isDirectory()){//查询子目录
//                getAllFiles(_file.getAbsolutePath(), _type);
//            } else{
//            }
        }
        return dataList;
    }

}
