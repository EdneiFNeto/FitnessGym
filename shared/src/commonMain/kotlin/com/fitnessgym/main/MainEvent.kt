package com.fitnessgym.main

sealed class MainEvent {
    data object OnDoneSplash: MainEvent()
}