package com.tvs.asynctaskdemo;

public interface IReportBack {
    void preExecute();
    void reportBack(String tag, int result);
    void postExecute();
}
