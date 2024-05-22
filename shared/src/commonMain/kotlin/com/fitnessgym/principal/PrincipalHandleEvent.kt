package com.fitnessgym.principal

sealed class PrincipalHandleEvent {
    data object OnStartTime: PrincipalHandleEvent()
}