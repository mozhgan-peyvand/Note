package com.peivandian.note_ui.viewModelsText

import androidx.lifecycle.SavedStateHandle
import com.peivandian.note_domain.usecases.local.GetLocalNoteById
import com.peivandian.note_domain.usecases.local.SetLocalNotes
import com.peivandian.note_models.NoteEntity
import com.peivandian.note_ui.screens.NoteDetailViewModel
import com.peivandian.note_ui.util.CoroutineRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NoteDetailViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    // Subject under test
    private lateinit var viewModel: NoteDetailViewModel

    // Mocked dependencies
    var savedStateHandle: SavedStateHandle = mockk<SavedStateHandle>()

    var setLocalNotes: SetLocalNotes = mockk<SetLocalNotes>()

    var getLocalNoteById: GetLocalNoteById = mockk<GetLocalNoteById>()

    @Test
    fun `init should load note when noteId is provided`() = runTest {

        // Given
        val noteId = 1
        val noteEntity = NoteEntity(id = 1, title = "Title", description = "Description")
        every { savedStateHandle.get<Int>("noteId") } returns (noteId)
        coEvery { getLocalNoteById(noteId) } returns (noteEntity)

        // When
        viewModel = NoteDetailViewModel(savedStateHandle, setLocalNotes, getLocalNoteById)
        viewModel.getLocalNoteById()

        // Act
        runCurrent()

        // Then
        assertEquals(noteEntity.title, viewModel.title)
        assertEquals(noteEntity.description ?: "", viewModel.description)
        assertEquals(noteEntity, viewModel.note)
    }

}