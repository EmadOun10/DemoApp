package com.demo.app.presentation.resource


class Resource<out T> constructor(
  val state: ResourceState,
  val data: T? = null,
  val errorMessage: String? = null
)