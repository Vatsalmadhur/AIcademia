package com.anurag.firebaseauthflow.dashboard.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.anurag.firebaseauthflow.auth.AuthViewModel
import com.anurag.firebaseauthflow.common.CustomButtonV2
import com.anurag.firebaseauthflow.common.Header
import com.anurag.firebaseauthflow.common.card.HomeCard
import com.anurag.firebaseauthflow.dashboard.profile.SkillsViewModel
import com.anurag.firebaseauthflow.gemini.Gemini

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Home(authVM: AuthViewModel, navController: NavHostController) {
    val scrollState = rememberScrollState()
    val authStatus by authVM.currentUser.collectAsState()
    val fs = remember {
        SkillsViewModel()
    }
    val skills = fs.skills.collectAsState()
    val scope = rememberCoroutineScope()
    val gemini = Gemini(scope)
    val intro = "Welcome to the vibrant world of Ruby, a dynamic, object-oriented programming language known for its simplicity and elegance. Developed with a focus on programmer happiness, Ruby offers a clean syntax that prioritizes readability and ease of use. Whether you're an experienced developer in Java, JavaScript, or Python, transitioning to Ruby brings a fresh perspective to your coding journey. Embrace the beauty of Ruby's expressive nature, allowing you to write concise yet powerful code. Its flexibility shines in both web development, through the popular Ruby on Rails framework, and scripting tasks. As you embark on this journey, you'll discover the joy of building applications with fewer lines of code, increased productivity, and a strong community to support your growth. Get ready to explore the unique and delightful features that make Ruby a fantastic addition to your programming toolkit."
    val goals = "Dive into the core concepts of Ruby programming. Explore variables, data types, and control structures. Additionally, delve into the object-oriented paradigm, understanding classes and methods. By the end of the day, aim for a solid grasp of Ruby's foundational elements, setting the stage for more intricate explorations in your coding journey."
    val expert = "As you embark on your journey to learn Ruby, remember that programming is both an art and a science. The elegance and expressiveness of Ruby offer a unique canvas for your creativity. Regular practice is the cornerstone of mastering any language, so dedicate time to hands-on coding exercises. Leverage your existing programming knowledge as a bridge to understanding Ruby's syntax and idiosyncrasies. The robust Ruby community is a valuable asset;;; engage in forums, ask questions, and share your insights. Real-world projects provide the opportunity to apply theoretical concepts;;; solidifying your understanding. Embrace experimentation;;; as Ruby encourages a playful and explorative coding approach. As you navigate the language's intricacies;;; be patient with yourself and celebrate the small victories;;; each line of code is a step toward mastery. Happy coding on your Ruby adventure!"
    val resources = "Ruby Official Documentation: ;;; https://www.ruby-lang.org/en/documentation/;;; The Net Ninja's Ruby Programming Playlist on YouTube: ;;;https://www.youtube.com/playlist?list=PL4cUxeGkcC9gxxVqxdMEpVsPh2lCPxP2V;;; Ruby on Rails Guides: ;;;https://guides.rubyonrails.org/;;; RubyMonk: https://rubymonk.com/;;; \"The Well-Grounded Rubyist\" by David A. Black: ;;;https://www.manning.com/books/the-well-grounded-rubyist-third-edition;;; Udemy - \"Ruby on Rails - The Complete Guide\": ;;;https://www.udemy.com/course/the-complete-ruby-on-rails-developer-course/;;;"
    gemini.setSkills(skills.value)
    FlowColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
    ) {

        Header(title = "Hello ${authStatus.data?.username?.split(" ")?.get(0) ?: ""}")

        HomeCard(title = "Introduction", onClick = { /*TODO*/ }) {
            if (skills.value.current_skill == null) {
                Text("Please choose a skill from profile section")
            } else {
                intro.split(";;;").forEach {
                    Text(text = it.trim(), modifier = Modifier)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
        HomeCard(title = "Today's goal", onClick = { /*TODO*/ }) {
//            gemini.getRes("Today's goal").forEach {
//                Text(text = it)
//            }
            if (skills.value.current_skill == null) {
                Text("Please choose a skill from profile section")
            } else {
                goals.split(";;;").forEach {
                    Text(text = it.trim())
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

        }
        HomeCard(title = "Expert advice", onClick = { /*TODO*/ }) {
//            gemini.getRes("Expert's advice").forEach {
//                Text(text = it)
//            }
            if (skills.value.current_skill == null) {
                Text("Please choose a skill from profile section")
            } else {
                expert.split(";;;").forEach {
                    Text(text = it.trim())
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

        }
        HomeCard(title = "Resources", onClick = { /*TODO*/ }) {
//            gemini.getRes("Resources").forEach {
//                Text(text = it)
//            }
            if (skills.value.current_skill == null) {
                Text("Please choose a skill from profile section")
            } else {
                resources.split(";;;").forEach {
                    Text(text = it.trim())
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

        }
        HomeCard(title = "Mark as Complete", onClick = { /*TODO*/ }) {
            CustomButtonV2(label = "Mark as Complete", icon = Icons.Default.Done, onClick = { /*TODO*/ })
        }
    }
}