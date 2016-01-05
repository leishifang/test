package com.example.giggle.oschina2.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Giggle on 2015-11-27.
 */
public interface EntityList<T extends Entity> extends Serializable {

    List<T> getList();
}
