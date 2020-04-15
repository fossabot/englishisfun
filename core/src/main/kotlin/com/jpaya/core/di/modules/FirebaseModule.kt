package com.jpaya.core.di.modules

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jpaya.core.di.CoreComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Class that contributes to the object graph [CoreComponent].
 *
 * @see Module
 */
@Module
class FirebaseModule {

    /**
     * Create a provider method binding for [FirebaseFirestore].
     *
     * @return Instance of FirebaseFirestore.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideFirebaseFirestore() = Firebase.firestore
}
