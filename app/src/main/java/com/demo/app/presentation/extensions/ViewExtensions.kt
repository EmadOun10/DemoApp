package com.demo.app.presentation.extensions

import android.view.LayoutInflater
import android.view.ViewGroup


val ViewGroup.layoutInflater: LayoutInflater get() = LayoutInflater.from(this.context)
