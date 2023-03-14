package com.coldwised.room

class MemberAlreadyExistsException: Exception(
    "There is already a member with that username in this room."
)