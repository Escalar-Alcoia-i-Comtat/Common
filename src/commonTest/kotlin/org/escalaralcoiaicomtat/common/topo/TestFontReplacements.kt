package org.escalaralcoiaicomtat.common.topo

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class TestFontReplacements {
    @Test
    fun `test family names`() {
        assertEquals(FontReplacements.SOURCE_SANS, FontReplacements.replacementFor("Candara"))
        assertEquals(FontReplacements.SIGNIKA, FontReplacements.replacementFor("Eras Medium ITC"))
        assertEquals(FontReplacements.WORK_SANS, FontReplacements.replacementFor("Segoe UI"))
    }

    @Test
    fun `test PostScript names`() {
        assertEquals(FontReplacements.SOURCE_SANS, FontReplacements.replacementFor("Candara-Bold"))
        assertEquals(FontReplacements.SIGNIKA, FontReplacements.replacementFor("ErasITC-Medium"))
        assertEquals(FontReplacements.WORK_SANS, FontReplacements.replacementFor("SegoeUI-Semibold"))
    }

    @Test
    fun `test fonts without replacement`() {
        assertNull(FontReplacements.replacementFor("ComicSansMS"))
        assertNull(FontReplacements.replacementFor(null))
    }

    @Test
    fun `test every family has a replacement`() {
        FontReplacements.families.forEach { (family, replacement) ->
            assertEquals(replacement, FontReplacements.replacementFor(family))
        }
    }
}
