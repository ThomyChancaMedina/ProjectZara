package com.example.projeczara.ui.common

import android.content.Context

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.projeczara.RickAndMortyApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


val Context.app: RickAndMortyApp get() = applicationContext as RickAndMortyApp
val Fragment.app: RickAndMortyApp get() = requireContext().app

