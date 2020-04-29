package com.jpaya.dynamicfeatures.characterslist.ui.list.adapter.holders

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.ViewDataBinding
import com.jpaya.dynamicfeatures.characterslist.databinding.ListItemCharacterBinding
import com.jpaya.dynamicfeatures.characterslist.ui.list.CharactersListViewModel
import com.jpaya.dynamicfeatures.characterslist.ui.list.model.CharacterItem
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CharacterViewHolderTest {

    @MockK
    lateinit var view: View

    @MockK
    lateinit var layoutInflater: LayoutInflater

    @MockK(relaxed = true)
    lateinit var binding: ListItemCharacterBinding
    lateinit var viewHolder: CharacterViewHolder

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun createViewHolder_ShouldInitializeCorrectly() {
        mockkStatic(ListItemCharacterBinding::class)
        every { (binding as ViewDataBinding).root } returns view
        every { ListItemCharacterBinding.inflate(layoutInflater) } returns binding

        viewHolder = CharacterViewHolder(layoutInflater)

        assertEquals(binding, viewHolder.binding)
    }

    @Test
    fun bindViewHolder_ShouldBindingDataVariable() {
        mockkStatic(ListItemCharacterBinding::class)
        every { (binding as ViewDataBinding).root } returns view
        every { ListItemCharacterBinding.inflate(layoutInflater) } returns binding

        val viewModel = mockk<CharactersListViewModel>()
        val characterItem = mockk<CharacterItem>()
        viewHolder = CharacterViewHolder(layoutInflater)
        viewHolder.bind(viewModel, characterItem)

        verify { binding.viewModel = viewModel }
        verify { binding.character = characterItem }
        verify { binding.executePendingBindings() }
    }
}
