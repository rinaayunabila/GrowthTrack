package com.example.growthtrack

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.growthtrack.di.Injection
import com.example.growthtrack.pindahan.UserPreference
import com.example.growthtrack.pindahan.UserRepository
import com.example.growthtrack.ui.ArticleViewModel
import com.example.growthtrack.ui.SignInViewModel
import com.example.growthtrack.ui.SignUpViewModel
import com.example.growthtrack.ui.SplashScreenViewModel

class ViewModelFactory private constructor(
    private val userRepository: UserRepository,
    private val userPreference: UserPreference
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignInViewModel::class.java) -> {
                SignInViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(ArticleViewModel::class.java) -> {
                ArticleViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(SplashScreenViewModel::class.java) -> {
                SplashScreenViewModel(userPreference) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory {
            return instance ?: synchronized(this) {
                val userRepository = Injection.provideRepository(context)
                val userPreference = Injection.provideDataStore(context)
                instance ?: ViewModelFactory(userRepository, userPreference).also { instance = it }
            }
        }
    }
}

//class ViewModelFactory private constructor(
//    private val userRepository: UserRepository,
//    private val userPreference: UserPreference
//) : ViewModelProvider.NewInstanceFactory() {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return when {
//            modelClass.isAssignableFrom(SignInViewModel::class.java) -> {
//                SignInViewModel(userRepository) as T
//            }
//            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
//                SignUpViewModel(userRepository) as T
//            }
//            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
//                MainViewModel(userRepository) as T
//            }
//            modelClass.isAssignableFrom(ArticleViewModel::class.java) -> {
//                ArticleViewModel(userRepository) as T
//            }
//            modelClass.isAssignableFrom(SplashScreenViewModel::class.java) -> {
//                SplashScreenViewModel(userPreference) as T
//            }
//            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
//        }
//    }
//
//    companion object {
//        @Volatile
//        private var instance: ViewModelFactory? = null
//
//        fun getInstance(context: Context): ViewModelFactory {
//            return instance ?: synchronized(this) {
//                val userRepository = Injection.provideRepository(context)
//                val userPreference = Injection.provideDataStore(context)
//                instance ?: ViewModelFactory(userRepository, userPreference).also { instance = it }
//            }
//        }
//    }
//}