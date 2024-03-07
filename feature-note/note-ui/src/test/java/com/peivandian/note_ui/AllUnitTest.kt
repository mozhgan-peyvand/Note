package com.peivandian.note_ui

import com.peivandian.note_ui.viewModelsText.NoteDetailViewModelTest
import com.peivandian.note_ui.viewModelsText.NoteListViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    NoteDetailViewModelTest::class,
    NoteListViewModelTest::class
)
class AllUnitTests