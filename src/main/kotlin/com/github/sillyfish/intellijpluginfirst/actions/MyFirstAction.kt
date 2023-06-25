package com.github.sillyfish.intellijpluginfirst.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction

class MyFirstAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val editor = event.getRequiredData(CommonDataKeys.EDITOR)
        val project = event.getRequiredData(CommonDataKeys.PROJECT)

        WriteCommandAction.runWriteCommandAction(project) {
//            editor.document.insertString(editor.caretModel.primaryCaret.selectionStart, "Hello, World!\n")
            editor.inlayModel.addAfterLineEndElement(
                editor.caretModel.primaryCaret.selectionStart,
                true, InlineSuffixRenderer(
                    editor, "Inline Hello, World!"
                )
            )
            editor.inlayModel.addBlockElement(
                editor.caretModel.primaryCaret.selectionStart,
                true, false, 0,
                InlineBlockElementRenderer(
                    editor, listOf("Hello, World!", "abc")
                )
            )
            /* editor.inlayModel.addInlineElement(
                editor.caretModel.primaryCaret.selectionStart,
                true, InlineBlockElementRenderer(
                    editor, listOf("Hello, World!", "abc")
                )
            )
*/
        }
    }

    override fun update(event: AnActionEvent) {
        val editor = event.getData(CommonDataKeys.EDITOR)
        event.presentation.isEnabledAndVisible = editor != null
    }
}