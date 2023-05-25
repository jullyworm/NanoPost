package com.example.nanopost.data.mappers

import com.example.nanopost.data.models.CheckUsernameResult
import com.example.nanopost.data.models.api.ApiCheckUsernameResult
import javax.inject.Inject

object CheckUsernameMapper {

    fun toModel(apiCheck: ApiCheckUsernameResult) = when (apiCheck) {
        ApiCheckUsernameResult.TooShort -> CheckUsernameResult.TooShort
        ApiCheckUsernameResult.TooLong -> CheckUsernameResult.TooLong
        ApiCheckUsernameResult.InvalidCharacters -> CheckUsernameResult.InvalidCharacters
        ApiCheckUsernameResult.Taken -> CheckUsernameResult.Taken
        ApiCheckUsernameResult.Free -> CheckUsernameResult.Free
    }
}