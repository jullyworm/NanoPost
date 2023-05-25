package com.example.nanopost.data.models

enum class CheckUsernameResult(val err:Boolean){
    TooShort(true),
    TooLong(true),
    InvalidCharacters(true),
    Taken(false),
    Free(false)
}