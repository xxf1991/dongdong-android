package com.example.dongdong.model.coachmain;

import com.example.dongdong.model.BaseData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author deofly
 * @since 1.0 2014/11/24
 */
public class CoachMainData extends BaseData<CoachMain> {

    @SerializedName("data")
    private List<CoachMain> data;

    @Override
    public List<CoachMain> getEntryList() {
        return null;
    }

    @Override
    public int getPage() {
        return 0;
    }
}
