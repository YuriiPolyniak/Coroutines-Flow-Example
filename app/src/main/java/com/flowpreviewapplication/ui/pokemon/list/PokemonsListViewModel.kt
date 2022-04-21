package com.flowpreviewapplication.ui.pokemon.list

import android.util.Log
import androidx.lifecycle.*
import com.flowpreviewapplication.domain.model.Pokemon
import com.flowpreviewapplication.domain.usecase.pokemon.CatchPokemonUseCase
import com.flowpreviewapplication.domain.usecase.pokemon.GetPokemonsInfoUseCase
import com.flowpreviewapplication.domain.usecase.pokemon.IncreasePokemonLevelUseCase
import com.flowpreviewapplication.domain.usecase.pokemon.ReleaseAllPokemonsUseCase
import com.flowpreviewapplication.ui.base.SingleLiveEvent
import com.flowpreviewapplication.ui.pokemon.list.model.PokemonListEvent
import com.flowpreviewapplication.ui.pokemon.list.model.PokemonsListResult
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.selects.select

class PokemonsListViewModel(
    private val getPokemonsInfoUseCase: GetPokemonsInfoUseCase,
    private val catchPokemonUseCase: CatchPokemonUseCase,
    private val increasePokemonLevelUseCase: IncreasePokemonLevelUseCase,
    private val releaseAllPokemonsUseCase: ReleaseAllPokemonsUseCase
) : ViewModel() {

    val pokemonsListLiveData: LiveData<PokemonsListResult> = liveData<PokemonsListResult> {
        emit(PokemonsListResult.Loading)
        emitSource(getPokemonsInfoUseCase.execute()
            .map<List<Pokemon>, PokemonsListResult> { PokemonsListResult.Success(it) }
            .catch { emit(PokemonsListResult.Error(it)) }
            .distinctUntilChanged()
            .asLiveData())
    }

    private val sharedEvents0 = MutableSharedFlow<PokemonListEvent>(replay = 0)
    fun getSharedEvents0(): Flow<PokemonListEvent> = sharedEvents0

//    private val sharedEvents0 = MutableSharedFlow<PokemonListEvent>(replay = 0, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
//    fun getSharedEvents0(): Flow<PokemonListEvent> = sharedEvents0

    private val sharedEvents1 = MutableSharedFlow<PokemonListEvent>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    fun getSharedEvents1(): Flow<PokemonListEvent> = sharedEvents1

    private val stateEvents = MutableStateFlow<PokemonListEvent>(PokemonListEvent.Initial)
    fun getStateEvents(): Flow<PokemonListEvent> = stateEvents

    private val eventsLiveData = MutableLiveData<PokemonListEvent>()
    fun getEventsLiveData(): LiveData<PokemonListEvent> = eventsLiveData

    private val singleEventsLiveData = SingleLiveEvent<PokemonListEvent>()
    fun getSingleEventsLiveData(): LiveData<PokemonListEvent> = singleEventsLiveData

//    val pokemonsListLiveDataAlternative: LiveData<PokemonsListResult> =
//        getPokemonsInfoUseCase.execute()
//            .map<List<Pokemon>, PokemonsListResult> { PokemonsListResult.Success(it) }
//            .onStart { emit(PokemonsListResult.Loading) }
//            .catch { emit(PokemonsListResult.Error(it)) }
//            .distinctUntilChanged()
//            .asLiveData()

    fun onCreate() {
        Log.d("TestLifecycleEvents", "On create")

//        flowOf(1,2,3).flatMapConcat {  }
//        flowOf(1,2,3).flatMapMerge {  }
//        flowOf(1,2,3).flatMapLatest {  }


//        events.value = PokemonListEvent.Progress
        viewModelScope.launch {
            delay(5000)
            Log.d("TestLifecycleEvents", "sharedEvents0: Try send: EventFirst")
            sharedEvents0.emit(PokemonListEvent.EventFirst)
            Log.d("TestLifecycleEvents", "sharedEvents0: Try send: EventSecond")
            sharedEvents0.emit(PokemonListEvent.EventSecond)
            Log.d("TestLifecycleEvents", "sharedEvents0: Try send: EventThird")
            sharedEvents0.emit(PokemonListEvent.EventThird)
            Log.d("TestLifecycleEvents", "sharedEvents0: Finish sending")
        }
//        viewModelScope.launch {
//            delay(5000)
//            Log.d("TestLifecycleEvents", "sharedEvents1: Try send: EventFirst")
//            sharedEvents1.emit(PokemonListEvent.EventFirst)
//            Log.d("TestLifecycleEvents", "sharedEvents1: Try send: EventSecond")
//            sharedEvents1.emit(PokemonListEvent.EventSecond)
//            Log.d("TestLifecycleEvents", "sharedEvents1: Finish sending")
//        }
//        viewModelScope.launch {
//            delay(5000)
//            Log.d("TestLifecycleEvents", "stateEvents: Try send: EventFirst")
//            stateEvents.value =  PokemonListEvent.EventFirst
//            Log.d("TestLifecycleEvents", "stateEvents: Try send: EventSecond")
//            stateEvents.value = PokemonListEvent.EventSecond
//            Log.d("TestLifecycleEvents", "stateEvents: Try send: EventThird")
//            stateEvents.value = PokemonListEvent.EventThird
//            Log.d("TestLifecycleEvents", "stateEvents: Finish sending")
//        }
//
//        viewModelScope.launch {
//            delay(5000)
//            eventsLiveData.value = PokemonListEvent.EventFirst
//            eventsLiveData.value = PokemonListEvent.EventSecond
//        }
//        viewModelScope.launch {
//            delay(5000)
//            singleEventsLiveData.value = PokemonListEvent.EventFirst
//            singleEventsLiveData.value = PokemonListEvent.EventSecond
//        }

//        val flow = MutableSharedFlow<Number>()
//        viewModelScope.launch {
//            flow.emit(1)
//            delay(200)
//            flow.emit(2)
//            delay(100)
//            flow.emit(3)
//            delay(1000)
//            flow.emit(3.1)
//            delay(800)
//            flow.emit(4)
//            delay(100)
//            flow.emit(5)
//            delay(1500)
//            flow.emit(6)
//            delay(1500)
//            flow.emit(7)
//            flow.emit(8)
//            flow.emit(9)
//            delay(1500)
//            flow.emit(10)
//        }

        val flow = flow<Number> {
            emit(1)
            delay(200)
            emit(2)
            delay(100)
            emit(3)
            delay(1000)
            emit(3.1)
            delay(800)
            emit(4)
            delay(100)
            emit(5)
            delay(1500)
            emit(6)
            delay(1500)
            emit(7)
            emit(8)
            emit(9)
            delay(1500)
            emit(10)
        }

        viewModelScope.launch {
            val deferred = async(Dispatchers.IO) {
                val result = "Resource<String>()"//
                Log.d("TEST_FLOW", "Send: $result")

                Thread.sleep(10000)
//                delay(1000)
                result
            }

            launch {
                val result = deferred.await()
                Log.d("TEST_FLOW", "Result 1: $result")

                delay(1000)
//                launch {
//                    val result = deferred.await()
//                    Log.d("TEST_FLOW", "Result 2: $result")
//                }
            }

//            var time = System.currentTimeMillis()
//            flow.queueItems {
//                // send to the backend
//                Log.d("TEST_FLOW", "Ready to send $it")
//                Thread.sleep(1000)
//                Log.d("TEST_FLOW", "Finished sending $it")
//                it
//            }.onEach {
////                delay(1000)
//                val timeDif = System.currentTimeMillis() - time
//                time = System.currentTimeMillis()
//                Log.d("TEST_FLOW", "El: $it - $timeDif")
//            }.collect()
        }
    }

    fun <T, Result> Flow<T>.queueItems(action: (List<T>) -> Result): Flow<Result> {
        return channelFlow {
            var isRunning = true
            var isActionInProgress = false
            val valuesChannel = produce(capacity = Channel.CONFLATED) {
                collect { value ->
                    Log.d("TEST_FLOW", "Values Collect $value")
                    send(value)
                }
            }
            val actionChannel = Channel<Result> { }

            val pendingItems = mutableListOf<T>()
            val pendingAction = {
                isActionInProgress = true
                val list = pendingItems.toList()
                pendingItems.clear()

                launch(Dispatchers.IO) {
                    val result = action(list)
                    isActionInProgress = false
                    actionChannel.send(result)
                }
            }

            while (isRunning) {
                select<Unit> {
                    valuesChannel.onReceiveCatching {
                        it.onSuccess {
                            Log.d("TEST_FLOW", "Values onSuccess $it")

                            pendingItems.add(it)
                            if (!isActionInProgress) {
                                Log.d("TEST_FLOW", "Values onSuccess - Start pending action")
                                pendingAction()
                            }
                        }.onFailure {
                            Log.d("TEST_FLOW", "Values on Failure")
                            valuesChannel.cancel()
                            actionChannel.consumeEach {
                                if (pendingItems.isNotEmpty()) {
                                    pendingAction()
                                } else {
                                    actionChannel.cancel()
                                    isRunning = false
                                }
                            }
                        }
                    }
                    actionChannel.onReceiveCatching {
                        it.onSuccess {
                            Log.d("TEST_FLOW", "Pending action onSuccess $it")
                            if (pendingItems.isNotEmpty()) {
                                pendingAction()
                            }
                        }.onFailure {
                            Log.d("TEST_FLOW", "Pending action on Failure")
//                            actionChannel.cancel()

//                            channel.close()
                        }
                    }
                }

            }
        }
    }

    fun <T> Flow<T>.throttleLatest(windowDuration: Long, apiCall: () -> Unit): Flow<T> {
        return channelFlow {
            var isRunning = true
            val values = produce(capacity = Channel.CONFLATED) {
                collect { value -> send(value) }
            }

            var lastValue: T? = null
            val ticker = Ticker(windowDuration)
            while (isRunning) {
                select<Unit> {
                    values.onReceiveCatching {
                        it
                            .onSuccess {
                                lastValue = it
                                if (!ticker.isStarted) {
                                    ticker.start(this@channelFlow)
                                }
                            }
                            .onFailure {
                                ticker.cancel()
                                lastValue = null
                                isRunning = false
                            }
                    }
                    ticker.getTicker().onReceive {
                        if (lastValue != null) {
                            val value = lastValue
                            lastValue = null
                            value?.let {
                                send(it)
                            }
                        } else {
                            // stop
                            ticker.stop()
                        }
                    }
                }

            }
        }
    }

    class Ticker(private val delay: Long) {

        private var channel: ReceiveChannel<Unit> = Channel()

        var isStarted: Boolean = false
            private set

        fun getTicker(): ReceiveChannel<Unit> {
            return channel
        }

        fun start(scope: CoroutineScope) {
            isStarted = true
            channel.cancel()
            channel = scope.produce(capacity = 0) {
                while (true) {
                    channel.send(Unit)
                    delay(delay)
                }
            }
        }

        fun stop() {
            isStarted = false
            channel.cancel()
            channel = Channel()
        }

        fun cancel() {
            isStarted = false
            channel.cancel()
        }
    }

//        var windowStartTime = System.currentTimeMillis()
//        val list = mutableListOf<T>()
//        var emitted = false
//        collect { value ->
//            val currentTime = System.currentTimeMillis()
//            val delta = currentTime - windowStartTime
//
//            list.add(value)
//            if (delta >= windowDuration) {
//                windowStartTime += delta / windowDuration * windowDuration
//                emitted = false
//            }
//            if (!emitted) {
//                emit(list.toList())
//                list.clear()
//                emitted = true
//            }
//        }

//    fun <T> Flow<T>.throttleFist(windowDuration: Long): Flow<T> = flow {
//        var windowStartTime = System.currentTimeMillis()
//        var emitted = false
//        collect { value ->
//            val currentTime = System.currentTimeMillis()
//            val delta = currentTime - windowStartTime
//            if (delta >= windowDuration) {
//                windowStartTime += delta / windowDuration * windowDuration
//                emitted = false
//            }
//            if (!emitted) {
//                emit(value)
//                emitted = true
//            }
//        }
//    }


    fun onPokemonSelected(item: Pokemon) = viewModelScope.launch {
        increasePokemonLevelUseCase.execute(item)
    }

    fun onCatchPokemonAction() = viewModelScope.launch {
        catchPokemonUseCase.execute()
    }

    fun onReleaseAllPokemonsAction() = viewModelScope.launch {
        eventsLiveData.value = PokemonListEvent.EventButton()
        singleEventsLiveData.value = PokemonListEvent.EventButton()

        launch {
            sharedEvents0.emit(PokemonListEvent.EventButton())
        }
        launch {
            sharedEvents1.emit(PokemonListEvent.EventButton())
        }
        launch {
            stateEvents.emit(PokemonListEvent.EventButton())
        }

//        events.tryEmit(PokemonListEvent.EventButton)
//        events.value = PokemonListEvent.EventButton
//        releaseAllPokemonsUseCase.execute()
    }

}