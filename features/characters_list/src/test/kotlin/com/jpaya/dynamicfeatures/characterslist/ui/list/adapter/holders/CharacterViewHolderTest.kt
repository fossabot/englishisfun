/*
 * Copyright 2020 Jose Maria Payá Castillo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jpaya.dynamicfeatures.characterslist.ui.list.adapter.holders

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.ViewDataBinding
import com.jpaya.dynamicfeatures.characterslist.databinding.ListItemCharacterBinding
import com.jpaya.dynamicfeatures.characterslist.ui.list.CharactersListViewModel
import com.jpaya.dynamicfeatures.characterslist.ui.list.model.CharacterItem
import io.mockk.*
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharacterViewHolderTest {

    @MockK
    lateinit var view: View

    @MockK
    lateinit var layoutInflater: LayoutInflater

    @MockK(relaxed = true)
    lateinit var binding: ListItemCharacterBinding
    lateinit var viewHolder: CharacterViewHolder

    @Before
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
