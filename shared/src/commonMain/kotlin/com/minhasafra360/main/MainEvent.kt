package com.minhasafra360.main

sealed class MainEvent {
    data object OnDoneSplash: MainEvent()
}