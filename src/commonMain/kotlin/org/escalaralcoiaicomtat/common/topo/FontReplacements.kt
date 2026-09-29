package org.escalaralcoiaicomtat.common.topo

/**
 * Free fonts that replace the ones used in the original Illustrator topos, which can't be distributed. The app's importer
 * stores the replacement of each label's font in [Topo.Label.font], and the server draws texts with them.
 *
 * | Illustrator     | Replacement     | Used for                                 |
 * |-----------------|-----------------|------------------------------------------|
 * | Candara         | Source Sans 3   | Route numbers, grades and labels         |
 * | Eras Medium ITC | Signika         | Copyright                                |
 * | Segoe UI        | Work Sans       | Other texts                              |
 *
 * Source Sans 3 is the current name of Source Sans Pro. All of them are under the SIL Open Font License.
 */
object FontReplacements {
    const val SOURCE_SANS = "Source Sans 3"
    const val SIGNIKA = "Signika"
    const val WORK_SANS = "Work Sans"

    /** Font families of the Illustrator topos, and the family that replaces each one. */
    val families: Map<String, String> = mapOf(
        "Candara" to SOURCE_SANS,
        "Eras Medium ITC" to SIGNIKA,
        "Segoe UI" to WORK_SANS
    )

    /**
     * The replacement of the Illustrator [font], or null if it has none. Accepts family names ("Segoe UI") and the
     * PostScript names found in the files ("Candara-Bold", "ErasITC-Medium", "SegoeUI-Semibold").
     */
    fun replacementFor(font: String?): String? {
        val name = font?.replace(" ", "")?.lowercase() ?: return null
        return when {
            name.startsWith("candara") -> SOURCE_SANS
            name.startsWith("erasitc") || name.startsWith("erasmedium") -> SIGNIKA
            name.startsWith("segoeui") -> WORK_SANS
            else -> null
        }
    }
}
