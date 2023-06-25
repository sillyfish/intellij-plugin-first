package com.github.sillyfish.intellijpluginfirst.completion

import com.intellij.openapi.editor.Editor

interface InlineCompletion {
    val isEmpty: Boolean

    companion object {
        fun forEditor(editor: Editor): InlineCompletion {
            return EditorInlineInlineCompletion(editor)
        }
    }
}