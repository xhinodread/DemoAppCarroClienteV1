package com.example.democarrodespacho

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MvvDemoDespachoApp @Inject constructor(): Application()