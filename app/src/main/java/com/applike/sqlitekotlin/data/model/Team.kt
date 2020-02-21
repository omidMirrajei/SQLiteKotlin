package com.applike.sqlitekotlin.data.model

data class Team(val id: Long, val name: String, val ground: String, val manager: String?) {
    constructor(name: String, ground: String, manager: String) : this(0, name, ground, manager)
}
