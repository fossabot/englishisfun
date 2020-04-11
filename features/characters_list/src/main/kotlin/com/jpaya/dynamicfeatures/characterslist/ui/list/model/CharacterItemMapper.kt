package com.jpaya.dynamicfeatures.characterslist.ui.list.model

import com.jpaya.core.mapper.Mapper
import com.jpaya.core.network.responses.BaseResponse
import com.jpaya.core.network.responses.CharacterResponse

private const val IMAGE_URL_FORMAT = "%s.%s"

/**
 * Helper class to transforms network response to visual model, catching the necessary data.
 *
 * @see Mapper
 */
open class CharacterItemMapper : Mapper<BaseResponse<CharacterResponse>, List<CharacterItem>> {

    /**
     * Transform network response to [CharacterItem].
     *
     * @param from Network characters response.
     * @return List of parsed characters items.
     */
    override suspend fun map(from: BaseResponse<CharacterResponse>) =
        from.data.results.map {
            CharacterItem(
                id = it.id,
                name = it.name,
                description = it.description,
                imageUrl = IMAGE_URL_FORMAT.format(
                    it.thumbnail.path.replace("http", "https"),
                    it.thumbnail.extension
                )
            )
        }
}
