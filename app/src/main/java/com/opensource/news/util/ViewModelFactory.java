package com.opensource.news.util;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;
import java.util.Map;

/**
 * @author dhruvaraj
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    @Inject
    Map<Class<? extends ViewModel>, ViewModel> vmHashMap;

    @Inject
    ViewModelFactory() {
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(vmHashMap.get(modelClass).getClass())) {
            return (T) vmHashMap.get(modelClass);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
