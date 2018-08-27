# Rating Star
> A nice-looking new concept for rating bars implemented for Android. 

![Platform](https://img.shields.io/badge/platform-Androd-green.svg)
[![API](https://img.shields.io/badge/API-17%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=17)
[![Download](https://api.bintray.com/packages/mohaka/maven/rating-star/images/download.svg) ](https://bintray.com/mohaka/maven/rating-star/_latestVersion)
[![GitHub issues](https://img.shields.io/github/issues-raw/mohakapt/ratingStar-android.svg)](https://github.com/mohakapt/ratingStar-android/issues)
[![License](https://img.shields.io/github/license/mohakapt/ratingStar-android.svg)](https://github.com/mohakapt/ratingStar-android)

The concept looks like a flower with every petal representing a specific rating.<br/>
*The idea of this project is originally inspired by this [concept](https://goo.gl/Yn2CCh).*
<br/><br/>

![Example](https://raw.githubusercontent.com/mohakapt/ratingStar-android/master/images/star.gif)


### Installation
Add This dependency to your module-level gradle file:
```groovy
dependencies {
    implementation 'com.github.mohaka:rating-star:0.1.1'
}
```
For an iOS implementation please check this [repository](https://github.com/mohakapt/ratingStar-ios).


### Usage
* Please check the example in the source code for more detailed information, or check the table below for a list of available properties.

```xml
<!--You need to provide an id in order to save and restore view state-->
<com.github.mohaka.ratingstar.RatingStar
    android:id="@+id/star"  
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:rating="1" />
```

* List of available properties:

|Attribute Name|Default Value|Description|
|---|---|---|
|app:rating|0|The initial rating of the view.|
|app:showNumbers|true|Determines weather to show numbers on the petals of the star.|
|app:starColor|R.attr.primaryColor|The color of the star.|
|app:highlightColor|R.attr.accentColor|The color of highlighted petals of the star.| 

## Roadmap
The library is still not as elegant as the original concept, so I might work more on adding some animations in my spare time. (But unfortunately since I have another job, I can't give any promises or deadlines).


## Contributing
If you encounter a bug or you have a feature in mind please make a pull request and i will merge it as soon as possible, if you can't (for some reason) make a pull request please open an issue and i will happily do respond to it.


## Versioning
I use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/mohakapt/ratingStar-android/tags).<br/>
I will try to provide release notes with future releases.


## License
This project is licensed under the Apache-2.0 license - see the [LICENSE.md](https://github.com/mohakapt/ratingStar-android/blob/master/LICENSE) file for more details.
