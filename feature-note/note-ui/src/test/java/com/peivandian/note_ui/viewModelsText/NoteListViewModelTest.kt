package com.peivandian.note_ui.viewModelsText

import app.cash.turbine.test
import com.peivandian.note_domain.usecases.local.GetLocalNotes
import com.peivandian.note_ui.models.noteEntity1
import com.peivandian.note_ui.models.noteEntity2
import com.peivandian.note_ui.screens.NoteListViewModel
import com.peivandian.note_ui.util.CoroutineRule
import com.peivandian.note_ui.util.FakeNoteListRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
@OptIn(ExperimentalCoroutinesApi::class)
class NoteListViewModelTest {


    @get:Rule
    val coroutineRule = CoroutineRule()

    @Test
    fun `creating a viewModel retrieves note list from local`() = runTest {

        // Arrange
        val fakeRepository = FakeNoteListRepository()
        fakeRepository.noteList = flowOf(listOf(noteEntity1, noteEntity2))

        val noteListViewModel = NoteListViewModel(
            getLocalNotes = GetLocalNotes(fakeRepository)
        )

        val expectedState =  listOf(noteEntity1, noteEntity2)

        // Act
        runCurrent()

        // Assert
        noteListViewModel.notes.test {
            assertEquals(expectedState, awaitItem())
            awaitComplete()
        }
    }
}