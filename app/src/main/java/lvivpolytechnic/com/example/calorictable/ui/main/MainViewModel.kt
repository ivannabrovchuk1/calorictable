package lvivpolytechnic.com.example.calorictable.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lvivpolytechnic.com.example.calorictable.data.repositories.user.UserRepository
import lvivpolytechnic.com.example.calorictable.models.User

class MainViewModel(
        private val userRepository: UserRepository
) : ViewModel() {

    private val _currentUser = MutableLiveData<User?>()
    val currentUser: LiveData<User?> = _currentUser

    private val _isAuthorized = MutableLiveData<Boolean>(false)
    val isAuthorized: LiveData<Boolean> = _isAuthorized

    private var _isCommonFragmentVisible = MutableLiveData<Boolean>(false)
    val isCommonFragmentVisible = _isCommonFragmentVisible

    //Maybe it's a unnecessary parameter
    var isDataLoad = false

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val gettingUser = userRepository.getUser()
            withContext(Dispatchers.Main) {
                _currentUser.value = gettingUser
                isDataLoad = true
            }
        }
    }

    fun isUserAuthorized(isAuthorized: Boolean) {
        this._isAuthorized.value = isAuthorized
    }

    fun setIsCommonFragmentVisible(isVisible: Boolean) {
        _isCommonFragmentVisible.value = isVisible
    }



}
