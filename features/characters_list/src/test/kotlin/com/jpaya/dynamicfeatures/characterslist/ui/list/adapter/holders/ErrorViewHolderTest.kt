package com.jpaya.dynamicfeatures.characterslist.ui.list.adapter.holders

// import android.view.LayoutInflater
// import android.view.View
// import androidx.databinding.ViewDataBinding
// import com.jpaya.dynamicfeatures.characterslist.databinding.ListItemErrorBinding
// import com.jpaya.dynamicfeatures.characterslist.ui.list.CharactersListViewModel
// import com.jpaya.dynamicfeatures.characterslist.ui.list.adapter.holders.ErrorViewHolder
// import io.mockk.MockKAnnotations
// import io.mockk.every
// import io.mockk.impl.annotations.MockK
// import io.mockk.mockk
// import io.mockk.mockkStatic
// import io.mockk.verify
// import org.junit.Assert.assertEquals
// import org.junit.Before
// import org.junit.Test
//
// class ErrorViewHolderTest {
//
//    @MockK
//    lateinit var view: View
//    @MockK
//    lateinit var layoutInflater: LayoutInflater
//    @MockK(relaxed = true)
//    lateinit var binding: ListItemErrorBinding
//    lateinit var viewHolder: ErrorViewHolder
//
//    @Before
//    fun setUp() {
//        MockKAnnotations.init(this)
//    }
//
//    @Test
//    fun createViewHolder_ShouldInitializeCorrectly() {
//        mockkStatic(ListItemErrorBinding::class)
//        every { (binding as ViewDataBinding).root } returns view
//        every { ListItemErrorBinding.inflate(layoutInflater) } returns binding
//
//        viewHolder = ErrorViewHolder(layoutInflater)
//
//        assertEquals(binding, viewHolder.binding)
//    }
//
//    @Test
//    fun bindViewHolder_ShouldBindingDataVariable() {
//        mockkStatic(ListItemErrorBinding::class)
//        every { (binding as ViewDataBinding).root } returns view
//        every { ListItemErrorBinding.inflate(layoutInflater) } returns binding
//
//        val viewModel = mockk<CharactersListViewModel>()
//        viewHolder = ErrorViewHolder(layoutInflater)
//        viewHolder.bind(viewModel)
//
//        verify { binding.viewModel = viewModel }
//        verify { binding.executePendingBindings() }
//    }
// }
