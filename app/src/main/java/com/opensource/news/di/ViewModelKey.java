package com.opensource.news.di;

import androidx.lifecycle.ViewModel;
import dagger.MapKey;

/**
 * @author dhruvaraj
 */
@MapKey
public @interface ViewModelKey {
    Class<? extends ViewModel> value();
}