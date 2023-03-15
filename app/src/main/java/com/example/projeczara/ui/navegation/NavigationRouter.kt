package com.example.projeczara.ui.navegation

sealed class NavigationRouter(var router: String) {
    object Main : NavigationRouter("main")
    object Detail : NavigationRouter("detail") {
        fun routerWithId(id: Int): String {
            return "$router/$id"
        }
    }
}