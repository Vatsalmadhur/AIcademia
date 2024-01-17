package com.anurag.firebaseauthflow.common.Searchbar

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anurag.firebaseauthflow.dashboard.profile.SkillsViewModel
import com.anurag.firebaseauthflow.firestore.About
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlin.math.min

class SearchViewModel : ViewModel() {
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _skills = MutableStateFlow(
        listOf(
            "Java",
            "Kotlin",
            "Swift",
            "Python",
            "JavaScript",
            "React",
            "Angular",
            "Vue.js",
            "Node.js",
            "Express.js",
            "Spring Framework",
            "Django",
            "Flask",
            "Ruby on Rails",
            "HTML",
            "CSS",
            "Sass",
            "Bootstrap",
            "Tailwind CSS",
            "Android Development",
            "iOS Development",
            "Flutter",
            "React Native",
            "Vue Native",
            "SwiftUI",
            "Jetpack Compose",
            "Git",
            "GitHub",
            "GitLab",
            "Bitbucket",
            "RESTful APIs",
            "GraphQL",
            "Firebase",
            "MongoDB",
            "MySQL",
            "PostgreSQL",
            "SQLite",
            "Realm",
            "Room",
            "Docker",
            "Kubernetes",
            "Jenkins",
            "Travis CI",
            "CircleCI",
            "AWS",
            "Azure",
            "Google Cloud Platform",
            "Heroku",
            "Netlify",
            "Machine Learning",
            "Deep Learning",
            "Natural Language Processing",
            "Computer Vision",
            "TensorFlow",
            "PyTorch",
            "Scikit-Learn",
            "OpenCV",
            "Data Science",
            "Big Data",
            "Apache Spark",
            "Hadoop",
            "Data Analytics",
            "Blockchain",
            "Smart Contracts",
            "Solidity",
            "Web3.js",
            "Ethereum",
            "Cybersecurity",
            "Penetration Testing",
            "OWASP",
            "Encryption",
            "Firewalls",
            "Agile Methodology",
            "Scrum",
            "Kanban",
            "DevOps",
            "Continuous Integration",
            "Microservices",
            "Serverless Architecture",
            "RESTful Architecture",
            "GraphQL",
            "Linux",
            "Shell Scripting",
            "Bash",
            "PowerShell",
            "Windows Server",
            "UI/UX Design",
            "Adobe XD",
            "Figma",
            "Sketch",
            "InVision",
            "AR/VR Development",
            "Unity",
            "Unreal Engine",
            "ARKit",
            "ARCore",
            "Blockchain Development",
            "Smart Contracts",
            "DApp Development",
            "Web3.js",
            "Solidity",
            "Internet of Things (IoT)",
            "Raspberry Pi",
            "Arduino",
            "IoT Protocols",
            "IoT Security"
        )
    )


    val skills = query.combine(_skills) { str, list ->
        if (str.isBlank()) {
            list.subList(0,5)
        }
        val ret = list.filter { skill ->
            skill.lowercase().contains(str)
        }
        ret.subList(0, min(ret.size, 5))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = _skills.value
    )

    private val _selected = MutableStateFlow(setOf<String>())
    val selected = _selected.asStateFlow()

    private val _globalSkills = MutableStateFlow(About())
    val skillsVM = SkillsViewModel()

    fun toggleSelection(text: String) {
        if (_selected.value.contains(text)) {
            _selected.value = _selected.value - setOf(text)
            _skills.value = _skills.value + setOf(text)
        } else {
            _selected.value = _selected.value + setOf(text)
            _skills.value = _skills.value - setOf(text)
        }
    }

    fun select(idx:Int){
        val skills = skillsVM.skills.value
        if(idx == 1){
            _selected.value = (skills.acquired_skills ?: listOf()).toSet()
        }else{
            add(skills.current_skill)
        }
    }

    fun add(text:String?) {
        if(text!=null)
        if (_selected.value.contains(text)) {
            _selected.value = _selected.value - setOf(text)
        } else
            _selected.value = _selected.value + setOf(text)
        clear()
    }
    fun save() {
        val text = _query.value
        if (_selected.value.contains(text)) {
            _selected.value = _selected.value - setOf(text)
        } else
            _selected.value = _selected.value + setOf(text)
        clear()
    }

    fun onSearchTextChange(text: String) {
        _query.value = text
    }

    fun toggleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange("")
        }
    }

    fun clear(){
        _query.value = ""
    }

    fun closeSearch(){
        _isSearching.value = false
    }
}