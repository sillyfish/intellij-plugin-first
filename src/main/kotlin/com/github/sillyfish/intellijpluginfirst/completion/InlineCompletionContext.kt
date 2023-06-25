package com.github.sillyfish.intellijpluginfirst.completion

import com.intellij.openapi.Disposable
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.util.Key

class InlineCompletionContext private constructor(private val editor: Editor) : Disposable {
    private val inlay = InlineCompletion.forEditor(editor)

    init {
        editor.caretModel.addCaretListener(InlineCompletionCaretListener())
        if (editor is EditorEx) {
            editor.addFocusListener(InlineCompletionFocusListener())
        }
    }

    val isCurrentlyDisplayingInlays: Boolean
        get() = !inlay.isEmpty

    fun insert() {

    }

    override fun dispose() {

    }


    companion object {
        private val INLINE_COMPLETION_CONTEXT_KEY =
            Key.create<InlineCompletionContext>("inline.completion.completion.context")

        fun Editor.getInlineCompletionContextOrNull(): InlineCompletionContext? =
            getUserData(INLINE_COMPLETION_CONTEXT_KEY)

        fun Editor.resetInlineCompletionContext(): Unit? = getInlineCompletionContextOrNull()?.let {
            if (it.isCurrentlyDisplayingInlays) {
                removeInlineCompletionContext()
                Disposer.dispose(it)
            }
        }

        private fun Editor.removeInlineCompletionContext(): Unit = putUserData(INLINE_COMPLETION_CONTEXT_KEY, null)
    }
}