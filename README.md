## Android Developer Test

### Overview

The Android Developer Test is a simple test we give our prospective Android developers to see how they do given a coding task.

The premise is simple, create a simple app that performs a search on Rotten Tomatoes using the Search API for movies containing the word: â€œAndroidâ€?.

The output should be a ListView that displays each matching movies details (title, year, runtime) on a page.

* You may use any method/external libraries you wish to connect to the REST service.
* The list of results needs to be clean and easy to read.

### Instructions

1. Clone this repository to your Github account (don't fork it please)
2. Create your android app source files, commit it and then push it to Github (please include a generated APK)
3. Email us a link to your completed code
4. You're done!

### Handy Links

* [http://developer.rottentomatoes.com/docs/read/json/v10/Movies_Search](http://developer.rottentomatoes.com/docs/read/json/v10/Movies_Search)

### Result

I created a simple activity with a field for the search and icon button to start the search. I wasn't specified in the overview that is why I initialized the search with the word Android.
The search with the word Android returns 11 items. I used the default parameters for the Page and Page Limit, so if you do a search that returns more than 30 items the App test will only shows the first 30 items.

