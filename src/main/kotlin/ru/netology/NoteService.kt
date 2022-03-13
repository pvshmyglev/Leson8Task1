package ru.netology

import java.util.*

object NoteService {

    private var notes = mutableListOf<Note>()

    //Создает новую заметку у текущего пользователя.
    fun add (title: String, text: String): Note {

        val newId = getMaxGuidInList(notes)
        val note = Note(newId, title, text)
        notes += note

        return notes.last()
    }

    //Добавляет новый комментарий к заметке.
    fun createComment (note: Note, message: String): Comment{

        val newId = getMaxGuidInList(note.commentsList)
        val comment = Comment(note.id, message, newId)
        note.commentsList += comment
        note.comments = note.commentsList.size

        return note.commentsList.last()

    }


    //Удаляет заметку текущего пользователя.
    fun delete(note: Note): Unit{
        note.deleted = true
    }

    //Удаляет комментарий к заметке.
    fun deleteComment(comment: Comment): Unit{
        comment.deleted = true
    }

    //Редактирует заметку текущего пользователя.
    fun edit(note: Note): Unit{
        val lastNote: Note? = getById(note.id)
        if (lastNote != null) {
            val index = notes.indexOf(lastNote)
            notes[index] = note.copy(ownerId = lastNote.ownerId)
        }
    }

    //Редактирует указанный комментарий у заметки.
    fun editComment(note: Note, comment: Comment): Unit{
        val lastComment = getCommentById(note, comment.id)
        if (lastComment != null) {
            val index = note.commentsList.indexOf(lastComment)
            note.commentsList[index] = comment.copy(ownerId = lastComment.ownerId)
        }

    }

    //Возвращает список заметок, созданных пользователем.
    fun get(): List<Note>{
        val noteList = notes.toList()
        return noteList.filter{it.deleted == false}
    }

    //Возвращает заметку по её id.
    fun getById(noteId: Int): Note? {

        for (value in notes) {
            if (noteId == value.id) return value
        }
        return null
    }

    //Возвращает комментарий заметки по id.
    fun getCommentById(note: Note, commentId: Int): Comment? {

        for (value in note.commentsList) {
            if (commentId == value.id) return value
        }
        return null
    }

    //Возвращает список комментариев к заметке.
    fun getComments(note: Note): List<Comment>{
        val commentList = note.commentsList.toList()
        return commentList.filter{it.deleted == false}
    }

    //Возвращает список заметок друзей пользователя.
    fun getFriendsNotes(): List<Note>{
        return  emptyList()
    }

    //Восстанавливает удалённый комментарий.
    fun restoreComment(comment: Comment): Unit{
        comment.deleted = false
    }

    //Получает максимальный идентификатор в коллекции, ограниченное использование
    fun <T> getMaxGuidInList(list: List<T>): Int {

        var currentMaxId = 0

        for (value in list) {
           val id: Int = when{
               (value is Note) -> (value as Note).id
               (value is Comment) -> (value as Comment).id
               (value is Int) -> value
               else -> 0}
            if (id >= currentMaxId)  currentMaxId = id
        }

        return  currentMaxId + 1

    }

}