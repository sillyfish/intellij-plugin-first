package com.github.sillyfish.intellijpluginfirst.completion

import com.github.sillyfish.intellijpluginfirst.completion.InlineCompletionContext.Companion.getInlineCompletionContextOrNull
import com.github.sillyfish.intellijpluginfirst.completion.InlineCompletionContext.Companion.resetInlineCompletionContext
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorAction
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler
import com.intellij.openapi.editor.event.CaretEvent
import com.intellij.openapi.editor.event.CaretListener
import com.intellij.openapi.editor.ex.FocusChangeListener

class InsertInlineCompletionAction : EditorAction(InsertInlineCompletionHandler()) {
    class InsertInlineCompletionHandler : EditorWriteActionHandler() {
        override fun executeWriteAction(editor: Editor, caret: Caret?, dataContext: DataContext) {
            editor.getInlineCompletionContextOrNull()?.insert()
        }
    }
}

class InlineCompletionCaretListener : CaretListener {
    override fun caretPositionChanged(event: CaretEvent) {
        event.editor.resetInlineCompletionContext()
    }
}

class InlineCompletionFocusListener : FocusChangeListener {
    override fun focusGained(editor: Editor) {}
    override fun focusLost(editor: Editor) {
        editor.resetInlineCompletionContext()
    }
}