package com.github.sillyfish.intellijpluginfirst.actions

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorCustomElementRenderer
import com.intellij.openapi.editor.Inlay
import com.intellij.openapi.editor.colors.EditorFontType
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.ui.JBColor
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Rectangle

class InlineSuffixRenderer(private val editor: Editor, val suffix: String) : EditorCustomElementRenderer {
    private val width = editor.contentComponent.getFontMetrics(InlineFontUtils.font(editor)).stringWidth(suffix)

    override fun calcWidthInPixels(inlay: Inlay<*>): Int {
        return width
    }

    override fun calcHeightInPixels(inlay: Inlay<*>): Int {
        return editor.contentComponent.getFontMetrics(InlineFontUtils.font(editor)).height
    }

    override fun paint(inlay: Inlay<*>, g: Graphics, targetRegion: Rectangle, textAttributes: TextAttributes) {
        g.color = InlineFontUtils.color
        g.font = InlineFontUtils.font(editor)
        g.drawString(suffix, targetRegion.x, targetRegion.y + editor.ascent)
    }
}

object InlineFontUtils {
    fun font(editor: Editor): Font {
        return editor.colorsScheme.getFont(EditorFontType.ITALIC)
    }

    val color: Color
        get() {
            return JBColor.GRAY
        }
}