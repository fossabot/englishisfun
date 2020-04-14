/**
 * Configuration of build modules
 */
object BuildModules {
    const val APP = ":app"
    const val CORE = ":core"

    object Features {
        const val HOME = ":features:home"
        const val CHARACTERS_LIST = ":features:characters_list"
        const val CHARACTERS_FAVORITES = ":features:characters_favorites"
        const val ABBREVIATIONS = ":features:abbreviations"
    }

    object Commons {
        const val UI = ":commons:ui"
        const val VIEWS = ":commons:views"
    }

    object Libraries {
        const val TEST_UTILS = ":libraries:test_utils"
    }
}
