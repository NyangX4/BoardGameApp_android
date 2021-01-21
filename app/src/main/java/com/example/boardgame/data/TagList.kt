package com.example.boardgame.data

object TagList {
    private var genreList : MutableList<String> = mutableListOf()
    private var themeList : MutableList<String> = mutableListOf()

    fun getGenreList() = genreList
    fun getThemeList() = themeList

    fun getGenreId(title : String) : Int {
        genreList.add(title)
        genreList = genreList.toSet().toMutableList()

        return genreList.indexOf(title)
    }
    fun getThemeId(title : String) : Int {
        themeList.add(title)
        themeList = themeList.toSet().toMutableList()

        return themeList.indexOf(title)
    }

    fun getGenreTitle(id : Int) : String = genreList[id]
    fun getThemeTitle(id : Int) : String = themeList[id]
}