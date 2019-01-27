package com.example.huchenzhang.myutils.bluetooth;

import java.util.List;
import java.util.UUID;

/**
 * Created by hu on 2019/1/22.
 */

public class BleAdvertisedData {
    private List<UUID> mUuids;
    private String mName;
    public BleAdvertisedData(List<UUID> uuids, String name){
        mUuids = uuids;
        mName = name;
    }

    public List<UUID> getUuids(){
        return mUuids;
    }

    public String getName(){
        return mName;
    }
}
