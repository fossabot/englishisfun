package com.jpaya.dynamicfeatures.abbreviations.ui.model

import com.jpaya.core.mapper.Mapper

/**
 * Helper class to transforms network response to visual model, catching the necessary data.
 *
 * @see Mapper
 */
class AbbreviationItemMapper :
    Mapper<MutableList<HashMap<String, String>>, MutableList<AbbreviationItem>> {

    /**
     * Transform network response to [AbbreviationItem].
     *
     * @param from Network abbreviations response.
     * @return List of parsed abbreviations items.
     */
    override suspend fun map(from: MutableList<HashMap<String, String>>): MutableList<AbbreviationItem> {
        val result = mutableListOf<AbbreviationItem>()
        var counter = 1L
        from.map {
            result.add(
                AbbreviationItem(
                    id = counter,
                    abbr = it["abbr"]!!,
                    desc = it["desc"]!!
                )
            )
            counter++
        }
        return result
    }
}
