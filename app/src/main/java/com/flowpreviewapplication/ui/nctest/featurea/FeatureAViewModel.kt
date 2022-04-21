package com.flowpreviewapplication.ui.nctest.featurea

import android.util.Log
import androidx.lifecycle.*
import com.flowpreviewapplication.domain.usecase.test.TestUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FeatureAViewModel(
//    private val navController: NavController,
    private val state: SavedStateHandle,
    private val testUseCase: TestUseCase
) : ViewModel() {

    private val events = MutableLiveData<FeatureAEvent>()
    fun getEvents(): LiveData<FeatureAEvent> = events

    private val stateEvents = MutableStateFlow<FeatureAEvent>(FeatureAEvent.Initial)
    fun getStateEvents(): StateFlow<FeatureAEvent> = stateEvents
    private val sharedEvents = MutableSharedFlow<FeatureAEvent>(1, 0, BufferOverflow.DROP_OLDEST)
    fun getSharedEvents(): SharedFlow<FeatureAEvent> = sharedEvents

//    private val sharedEvents = MutableSharedFlow<FeatureAEvent>()
//    fun getSharedEvents(): StateFlow<FeatureAEvent> = sharedEvents

//    private val events = state.getLiveData<FeatureAEvent>(
//        PROPERTY_LAST_CONTENT_STATE,
//        FeatureAEvent.DisplayContent("Default")
//    )
//    fun getEvents(): LiveData<FeatureAEvent> = events

    init {
        Log.d(
            "TestNC",
            "ViewModel created {state.get<FeatureAEvent.DisplayContent>(PROPERTY_LAST_CONTENT_STATE)}"
        )
    }

    fun start() {
//        events.value = FeatureAEvent.ActionFirst
//        stateEvents.value = FeatureAEvent.ActionFirst
//        sharedEvents.tryEmit(FeatureAEvent.ActionFirst)
        viewModelScope.launch {
            delay(300)
            events.value = FeatureAEvent.ActionFirst
            stateEvents.value = FeatureAEvent.ActionFirst
            sharedEvents.emit(FeatureAEvent.ActionFirst)

            delay(3000)
            events.value = FeatureAEvent.ActionSecond
            stateEvents.value = FeatureAEvent.ActionSecond
            sharedEvents.emit(FeatureAEvent.ActionSecond)

            return@launch

            delay(3000)
            events.value = FeatureAEvent.ActionThird
            stateEvents.value = FeatureAEvent.ActionThird
        }
//        viewModelScope.launch {
//            delay(300)
//            sharedEvents.emit(FeatureAEvent.ActionFirst)
//            delay(3000)
//            sharedEvents.emit(FeatureAEvent.ActionSecond)
//            delay(3000)
//            sharedEvents.emit(FeatureAEvent.ActionThird)
//        }

//        val defaultEvent = FeatureAEvent.DisplayContent("Default")
//        events.value =
//            state.get<FeatureAEvent.DisplayContent>(PROPERTY_LAST_CONTENT_STATE) ?: defaultEvent
    }

    fun onGoBackClicked() {
        events.value = FeatureAEvent.BackAction
        stateEvents.value = FeatureAEvent.BackAction
        sharedEvents.tryEmit(FeatureAEvent.BackAction)

//        val event = FeatureAEvent.DisplayContent("GoBack")
//        events.value = event

//        state.set(PROPERTY_LAST_CONTENT_STATE, event)
//        state[PROPERTY_LAST_CONTENT_STATE] = event
    }

    fun onActionClicked() {
        val event = FeatureAEvent.DisplayContent("DoSmth")
        events.value = event
//        state.set(PROPERTY_LAST_CONTENT_STATE, event)
//        state[PROPERTY_LAST_CONTENT_STATE] = event
    }

    companion object {
        private const val PROPERTY_LAST_CONTENT_STATE = "ContentState"
    }
}