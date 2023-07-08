# MovieListApplication - Clean Architecture MVVM

A simple project using [The Movie DB](https://www.themoviedb.org) based on Kotlin MVVM architecture and material designs.
We will use clean architecture to achieve below goals so our business logic is completely decoupled from our UI. It makes our code very easy to maintain and test.


<img width="413" alt="image" src="https://github.com/akab11ee/MovieListApplication/assets/44242039/277e405d-981d-42f7-8586-7811be8ae0ea">
<img width="411" alt="image" src="https://github.com/akab11ee/MovieListApplication/assets/44242039/674b08b5-89a4-4d68-afc2-7fc2538e9944">

<img width="713" alt="image" src="https://github.com/akab11ee/MovieListApplication/assets/44242039/b6541e61-3579-48ec-b763-6accc50f2dfa">

## The Goal
* Visualize the movie list in different sections i.e. now playing, most popular, top rated and upcoming as a vertical list with collapsible toolbar
* Vertical list should have 4 collapsible sections, each with title and horizontally scrollable list
* We will integrate following APIs in each collapsible sections as horizontal list in order:

  * [Latest movies](https://developers.themoviedb.org/3/movies/get-now-playing)

  * [Popular movies](https://developers.themoviedb.org/3/movies/get-popular-movies)

  * [Top Rated movies](https://developers.themoviedb.org/3/movies/get-top-rated-movies)

  * [Upcoming movies](https://developers.themoviedb.org/3/movies/get-upcoming)
    
* To showcase UI we will populate the first two sections when user lands on Main screen keeping other two in collapsed state but show the section title. Each item in the horizontal list should have movie image and the movie title.

* We will fetch data for third and fourth section when user clicks to expand any of those sections. 

* User should be able to collapse, expanded section and vice versa from the section title bar.
* Clicking on any item in horizontal list should open item details bottom sheet.

## Features 🔥
### :domain

3rd party libraries:
 - [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
 - [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependencies injection
 - [Mockk](https://mockk.io/) for unit testing of UseCases

Features:
 - Use cases
 - Domain level models
 - Repository interface
 - Unit tests

### :data

Features:
 - Repository implementation to get data from API
 - Create mapper to serves as an adapter between domain entities and data transfer entities
 - Retrofit interface
 - Unit tests

 3rd party libraries and technologies used:
  - [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
  - [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependencies injection
  - [Mockk](https://mockk.io/) for unit testing of Repository
  - [Kotlin flows](https://developer.android.com/kotlin/flow)
  - [Retrofit](https://square.github.io/retrofit/)
  - [OkHttp](https://square.github.io/okhttp/)

  Testing:
  - [Mockk](https://mockk.io/)


### :app (presentation layer)

3rd party libraries and technologies used:
  - [Android ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?gclid=CjwKCAiAoL6eBhA3EiwAXDom5oKABL8-HMrHV2XjQTCwKqtV-iMS4fTKJwgFsJDnzSwuNmDy0vEHyxoCqwkQAvD_BwE&gclsrc=aw.ds0)
  - [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependencies injection
  - [Kotlin flows](https://developer.android.com/kotlin/flow)
  - [Glide](https://github.com/bumptech/glide)
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
  - [Coordinator Layout](https://developer.android.com/reference/androidx/coordinatorlayout/widget/CoordinatorLayout)
  - [Collapsing toolbar layout](https://developer.android.com/reference/com/google/android/material/appbar/CollapsingToolbarLayout)
  - [Constraint layout](https://developer.android.com/reference/androidx/constraintlayout/widget/ConstraintLayout)
  - [Data Binding](https://developer.android.com/topic/libraries/data-binding)
  - [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
