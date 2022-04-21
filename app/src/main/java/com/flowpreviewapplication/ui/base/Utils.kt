package com.flowpreviewapplication.ui.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ViewModelOwner
import org.koin.androidx.viewmodel.koin.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.java.KoinJavaComponent.getKoin
import kotlin.reflect.KClass

inline fun <reified T : ViewModel> Fragment.stateSharedGraphViewModel(
    @IdRes navGraphId: Int,
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE) { getStateSharedGraphViewModel(navGraphId, T::class, qualifier, parameters) }
}

inline fun <reified T : ViewModel> Fragment.getStateSharedGraphViewModel(
    @IdRes navGraphId: Int,
    clazz: KClass<T>,
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): T {
    val owner = {ViewModelOwner(findNavController().getBackStackEntry(navGraphId).viewModelStore)}

    return getKoin().getViewModel(qualifier,owner, clazz,  parameters)
}