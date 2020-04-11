package com.jpaya.dynamicfeatures.characterslist.ui.detail.model

import com.jpaya.core.mapper.Mapper
import com.jpaya.core.network.responses.BaseResponse
import com.jpaya.core.network.responses.CharacterResponse

private const val IMAGE_URL_FORMAT = "%s.%s"

/**
 * Helper class to transforms network response to visual model, catching the necessary data.
 *
 * @see Mapper
 */
class CharacterDetailMapper : Mapper<BaseResponse<CharacterResponse>, CharacterDetail> {

    /**
     * Transform network response to [CharacterDetail].
     *
     * @param from Network characters response.
     * @return List of parsed characters items.
     * @throws NoSuchElementException If the response results are empty.
     */
    @Throws(NoSuchElementException::class)
    override suspend fun map(from: BaseResponse<CharacterResponse>): CharacterDetail {
        val characterResponse = from.data.results.first()
        return CharacterDetail(
            id = characterResponse.id,
            name = characterResponse.name,
            description = characterResponse.description,
            imageUrl = IMAGE_URL_FORMAT.format(
                characterResponse.thumbnail.path.replace("http", "https"),
                characterResponse.thumbnail.extension
            )
        )
    }
}
