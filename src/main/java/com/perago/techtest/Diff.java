package com.perago.techtest;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * The object representing a diff.
 * Implement this class as you see fit. 
 *
 */
public class Diff<T extends Serializable > {

    private List<DiffContainer> diffList;

    public List<DiffContainer> getDiffList() {
        return diffList;
    }

    public void setDiffList(List<DiffContainer> diffList) {
        this.diffList = diffList;
    }
}