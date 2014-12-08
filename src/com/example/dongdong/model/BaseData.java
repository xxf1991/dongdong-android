package com.example.dongdong.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author deofly
 * @since 1.0 2014/11/30
 */
public abstract class BaseData<E extends BaseEntry> {

    public abstract List<E> getEntryList();

    public abstract int getPage();
}
