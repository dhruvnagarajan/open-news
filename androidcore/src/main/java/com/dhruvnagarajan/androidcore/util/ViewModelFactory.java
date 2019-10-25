package com.dhruvnagarajan.androidcore.util;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import javax.inject.Inject;

/**
 * @author Dhruvaraj Nagarajan
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
        throw new IllegalArgumentException();
    }
}
