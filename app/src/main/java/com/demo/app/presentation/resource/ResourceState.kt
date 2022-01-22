package com.demo.app.presentation.resource


sealed class ResourceState {
  object LOADING : ResourceState()
  object SUCCESS : ResourceState()
  object ERROR : ResourceState()
}