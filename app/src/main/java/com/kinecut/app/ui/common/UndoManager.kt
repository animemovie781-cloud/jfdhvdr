package com.kinecut.app.ui.common

class UndoManager<T> {
    private val history = mutableListOf<T>()
    private var currentIndex = -1

    fun push(state: T) {
        if (currentIndex < history.size - 1) {
            history.subList(currentIndex + 1, history.size).clear()
        }
        history.add(state)
        currentIndex = history.size - 1
    }

    fun undo(): T? {
        if (currentIndex > 0) {
            currentIndex--
            return history[currentIndex]
        }
        return null
    }

    fun redo(): T? {
        if (currentIndex < history.size - 1) {
            currentIndex++
            return history[currentIndex]
        }
        return null
    }

    fun canUndo() = currentIndex > 0
    fun canRedo() = currentIndex < history.size - 1
    fun clear() {
        history.clear()
        currentIndex = -1
    }
}
