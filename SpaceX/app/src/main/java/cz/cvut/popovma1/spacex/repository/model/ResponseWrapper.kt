package cz.cvut.popovma1.spacex.repository.model

data class ResponseWrapper<T>(
    val state: State,
    val data: T?
)