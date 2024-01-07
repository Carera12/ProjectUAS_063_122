package com.example.projectuas.ui.menu.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectuas.data.OrderRepository
import com.example.projectuas.model.Order
import com.example.projectuas.ui.DetailOrder
import com.example.projectuas.ui.UIStateOrder
import com.example.projectuas.ui.toOrder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MenuViewModel(private val orderRepository: OrderRepository): ViewModel() {

    private val _stateUI = MutableStateFlow(Order())
    val stateUI: StateFlow<Order> = _stateUI.asStateFlow()

//    fun setContact(listContact: MutableList<String>) {
//        _stateUI.update { stateSaatIni ->
//            stateSaatIni.copy(
//                nama = listContact[0],
//                nim = listContact[1],
//                konsentrasi = listContact[2],
//                judulSkripsi = listContact[3]
//            )
//        }
//    }
    fun setMakanan(pilih:String){
        _stateUI.update { currentState -> currentState.copy(makanan = pilih)
        }
    }

    fun setMinuman(pilih2:String){
        _stateUI.update { currentState -> currentState.copy(minuman = pilih2)
        }
    }
    /*
    * Berisi status Order saat ini
    */
    var uiStateOrder by mutableStateOf(UIStateOrder())
        private set

    fun updateUiState(detailOrder: DetailOrder) {
        uiStateOrder =
            UIStateOrder(detailOrder = detailOrder)
    }


    suspend fun saveOrder() {
        viewModelScope.launch {
            try {
                orderRepository.save(uiStateOrder.detailOrder.toOrder())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}