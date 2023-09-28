package com.example.cryptoapp.util.paginator

interface Paginator<Key, Item> {

    suspend fun loadNextItems()
    suspend fun reset()
}