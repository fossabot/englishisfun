package com.jpaya.dynamicfeatures.caractersfavorites.ui.favorite.adapter

// import android.view.LayoutInflater
// import android.view.View
// import androidx.databinding.ViewDataBinding
// import com.jpaya.core.database.characterfavorite.CharacterFavorite
// import com.jpaya.dynamicfeatures.charactersfavorites.databinding.ListItemCharactersFavoriteBinding
// import com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.adapter.holders.CharacterFavoriteViewHolder
// import io.mockk.MockKAnnotations
// import io.mockk.every
// import io.mockk.impl.annotations.MockK
// import io.mockk.mockk
// import io.mockk.mockkStatic
// import io.mockk.verify
// import org.junit.Assert
// import org.junit.Before
// import org.junit.Test
//
// class CharacterFavoriteViewHolderTest {
//
//    @MockK
//    lateinit var view: View
//    @MockK
//    lateinit var layoutInflater: LayoutInflater
//    @MockK(relaxed = true)
//    lateinit var binding: ListItemCharactersFavoriteBinding
//    lateinit var viewHolder: CharacterFavoriteViewHolder
//
//    @Before
//    fun setUp() {
//        MockKAnnotations.init(this)
//    }
//
//    @Test
//    fun createViewHolder_ShouldInitializeCorrectly() {
//        mockkStatic(ListItemCharactersFavoriteBinding::class)
//        every { (binding as ViewDataBinding).root } returns view
//        every { ListItemCharactersFavoriteBinding.inflate(layoutInflater) } returns binding
//
//        viewHolder = CharacterFavoriteViewHolder(layoutInflater)
//
//        Assert.assertEquals(binding, viewHolder.binding)
//    }
//
//    @Test
//    fun bindViewHolder_ShouldBindingDataVariable() {
//        mockkStatic(ListItemCharactersFavoriteBinding::class)
//        every { (binding as ViewDataBinding).root } returns view
//        every { ListItemCharactersFavoriteBinding.inflate(layoutInflater) } returns binding
//
//        val characterFavorite = mockk<CharacterFavorite>()
//        viewHolder = CharacterFavoriteViewHolder(layoutInflater)
//        viewHolder.bind(characterFavorite)
//
//        verify { binding.character = characterFavorite }
//        verify { binding.executePendingBindings() }
//    }
// }
