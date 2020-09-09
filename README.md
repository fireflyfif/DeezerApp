# DeezerApp

![text](https://github.com/fireflyfif/DeezerApp/blob/master/app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.png)

## App Description
Deezer App consumes the [Deezer API](http://developers.deezer.com/api) and displays relevant information from it.

- The app presents to the user a search option for finding their favorite artist. 
- When the detail screen is opened, it presents all albums of the current artist. 
- Further the user can check all tracks of the selected album.

## Architecture
The app is written in Kotlin and uses the MVVM architecture pattern, which allows separating the user interface logic from the business (or the back-end) logic. 
 
Repository module handles data operations. It abstracts the data sources from the rest of the app and provides a clean API so that the rest of the app can retrieve this data easily. The Repository currently holds only the data source from the web service, but later we could add to it different data sources, such as persistent models, and caches, such as persistent models, and caches.

## Libraries
- [Retrofit](https://github.com/square/retrofit) & [OkHttp](https://github.com/square/okhttp)
- [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html)
- [Picasso](https://github.com/square/picasso) 
- [Architecture Components](https://developer.android.com/topic/libraries/architecture/) - ViewModel, LiveData, Navigation Component (partially)
- [Koin](https://insert-koin.io/) 
- [Palette](https://developer.android.com/training/material/palette-colors) 
