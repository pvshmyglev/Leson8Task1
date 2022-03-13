package ru.netology

import org.junit.Test

import org.junit.Assert.*

internal class NoteServiceTest {

    @Test
    fun add_this_test_only() {
        val note = NoteService.add("заголовок заметки", "текст заметкм")
        assertNotEquals(note.id, 0)
    }

    @Test
    fun createComment_this_test_only() {
        val note = NoteService.add("заголовок заметки", "текст заметкм")
        val comment = NoteService.createComment(note, "текст комментария")
        assertNotEquals(comment.id, 0)
    }

    @Test
    fun delete_this_test_only() {
        val note = NoteService.add("заголовок заметки", "текст заметкм")
        NoteService.delete(note)
        assertTrue(note.deleted)
    }

    @Test
    fun deleteComment_this_test_only() {
        val note = NoteService.add("заголовок заметки", "текст заметкм")
        val comment = NoteService.createComment(note, "текст комментария")
        NoteService.deleteComment(comment)
        assertTrue(comment.deleted)
    }

    @Test
    fun edit_NotNull() {
        val note = NoteService.add("заголовок заметки", "текст заметкм")
        val noteNew = note.copy(title = "новый заголовок заметки")
        NoteService.edit(noteNew)

        val noteInList = NoteService.getById(noteNew.id)

        assertEquals(noteInList?.title, noteNew.title)

    }

    @Test
    fun edit_Null() {
        val note = Note(0,"заголовок заметки", "текст заметкм")
        val noteNew = note.copy(title = "новый заголовок заметки")
        NoteService.edit(noteNew)
        val noteInList = NoteService.getById(noteNew.id)

        assertEquals(noteInList?.title, null)

    }

    @Test
    fun editComment_NotNull() {
        val note = NoteService.add("заголовок заметки", "текст заметкм")
        val comment = NoteService.createComment(note, "текст комментария")

        val commentNew = comment.copy(message = "новый текст комментария")
        NoteService.editComment(note, commentNew)

        val commentInList = NoteService.getCommentById(note, commentNew.id)

        assertEquals(commentInList?.message, commentNew.message)

    }

    @Test
    fun editComment_Null() {
        val note = NoteService.add("заголовок заметки", "текст заметкм")
        val comment = Comment(0, "текст комментария", 0)

        val commentNew = comment.copy(message = "новый текст комментария")
        NoteService.editComment(note, commentNew)

        val commentInList = NoteService.getCommentById(note, commentNew.id)

        assertEquals(commentInList?.message, null)

    }

    @Test
    fun get_this_test_only() {
        val note = NoteService.add("заголовок заметки", "текст заметкм")
        val note2 = NoteService.add("заголовок заметки 2", "текст заметкм 2")

        NoteService.delete(note)

        val list = NoteService.get()

        assertNotEquals(list.size, 0)

    }

    @Test
    fun getById_NotNull() {
        val note = NoteService.add("заголовок заметки", "текст заметкм")
        val note2 = NoteService.getById(note.id)

        assertNotEquals(note2, null)
    }

    @Test
    fun getById_Null() {
        val note2 = NoteService.getById(-1)
        assertEquals(note2, null)
    }

    @Test
    fun getCommentById_NotNull() {
        val note = NoteService.add("заголовок заметки", "текст заметкм")
        val comment = NoteService.createComment(note, "текст комментария")
        val comment2 = NoteService.createComment(note, "текст комментария 2")
        val comment3 = NoteService.getCommentById(note, comment2.id)

        assertNotEquals(comment3, null)
    }

    @Test
    fun getCommentById_Null() {
        val note = NoteService.add("заголовок заметки", "текст заметкм")
        val comment2 = NoteService.getCommentById(note,-1)
        assertEquals(comment2, null)
    }

    @Test
    fun getComments_this_test_only() {
        val note = NoteService.add("заголовок заметки", "текст заметкм")
        val comment = NoteService.createComment(note, "текст комментария")
        val comment2 = NoteService.createComment(note, "текст комментария 2")
        NoteService.deleteComment(comment)

        val list = NoteService.getComments(note)
        assertEquals(list.size, 1)

    }

    @Test
    fun getFriendsNotes_this_test_only() {
        val value = NoteService.getFriendsNotes()
        assertNotEquals(value, null)
    }

    @Test
    fun restoreComment_restoreNotDeletedElement() {
        val note = NoteService.add("заголовок заметки", "текст заметкм")
        val comment = NoteService.createComment(note, "текст комментария")
        val comment2 = NoteService.createComment(note, "текст комментария 2")
        NoteService.deleteComment(comment)

        val list = NoteService.getComments(note)

        NoteService.restoreComment(comment2)

        val list2 = NoteService.getComments(note)

        assertEquals(list2.size, 1)
    }

    @Test
    fun restoreComment_restoreDeletedElement() {
        val note = NoteService.add("заголовок заметки", "текст заметкм")
        val comment = NoteService.createComment(note, "текст комментария")
        val comment2 = NoteService.createComment(note, "текст комментария 2")
        NoteService.deleteComment(comment)

        val list = NoteService.getComments(note)

        NoteService.restoreComment(comment)

        val list2 = NoteService.getComments(note)

        assertEquals(list.size + list2.size, 3)
    }

    @Test
    fun getMaxGuidInList_Empty() {
        val note = NoteService.add("заголовок заметки", "текст заметкм")
        val result = NoteService.getMaxGuidInList(note.commentsList)

        assertEquals(result, 1)
    }

    @Test
    fun getMaxGuidInList_Int() {
        val result = NoteService.getMaxGuidInList(mutableListOf<Int>(1,3,2))
        assertEquals(result, 4)
    }

    @Test
    fun getMaxGuidInList_OtherTipe() {
        val result = NoteService.getMaxGuidInList(mutableListOf<String>("1","3","2"))
        assertEquals(result, 1)
    }

    @Test
    fun getMaxGuidInList_NotEmpty() {
        val note = NoteService.add("заголовок заметки", "текст заметкм")
        val comment = NoteService.createComment(note, "текст комментария")
        val result = NoteService.getMaxGuidInList(note.commentsList)

        assertNotEquals(result, 1)
    }

}