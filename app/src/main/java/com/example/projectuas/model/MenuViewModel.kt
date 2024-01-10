package com.example.projectuas.model

import androidx.lifecycle.ViewModel
import com.example.projectuas.data.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MenuViewModel : ViewModel() {
    private val _stateUI = MutableStateFlow(Order())
    val stateUI: StateFlow<Order> = _stateUI.asStateFlow()

    fun setMakanan(pilih:String){
        _stateUI.update { currentState -> currentState.copy(makanan = pilih)
        }
    }

    fun setMinuman(pilih2:String){
        _stateUI.update { currentState -> currentState.copy(minuman = pilih2)
        }
    }
}