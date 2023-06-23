![badge](http://img.shields.io/badge/-android-6EDB8D.svg?style=flat)
![badge](http://img.shields.io/badge/-ios-CDCDCD.svg?style=flat)
![Maven Central](https://img.shields.io/maven-central/v/com.hexascribe/vertexai-kt)
[![License: Apache](https://img.shields.io/badge/license-Apache-blue)](https://opensource.org/license/apache-2-0/)
![VertexAI-KT](art/logo.png)

# VertexAI-KT

VertexAI-KT is a Kotlin multiplatform library designed to simplify the integration with the [Vertex AI PaLM API](https://cloud.google.com/vertex-ai/docs/start/introduction-unified-platform), a powerful service provided by Google Cloud for creating and training generative models. This library provides an abstraction layer on top of the VertexAI API, enabling seamless integration with Android and iOS platforms.

## ⭐ Highlights

- **Simplified Integration**: VertexAI-KT simplifies the process of integrating your Kotlin-based mobile applications with the Vertex AI PaLM API;
- **Cross-Platform Support**: The library supports both Android and iOS platforms, allowing you to leverage the power of Vertex AI PaLM in your mobile apps;
- **Abstraction Layer**: VertexAI-KT creates an abstraction layer on top of the VertexAI API, providing an intuitive and easy-to-use interface for creating and training generative models.

## ⚡️ Getting Started

To get started with the SDK, you'll need the following information for initialization:

- **Project ID**: The Project ID refers to your Google Cloud Project ID.
- **Access Token**: You'll need an access token for authentication. For more details, please refer to the [Google Cloud Platform Authentication](https://cloud.google.com/docs/authentication) documentation.

It's important to have these details ready before proceeding with the SDK setup.

### Android setup

Add the following line to import the library via Gradle. First, make sure Maven Central has been added:


```kotlin
repositories {
    mavenCentral()
    // ...
}
```

Then, simply import the dependency to your `build.gradle` dependencies:

```kotlin
implementation("com.hexascribe:vertexai-kt:0.0.1")
```

Take a look at the Kotlin code snippet below for an example of how to initialize and use one of the supported features:

```kotlin
val vertexAI by lazy {
    VertexAI.Builder()
        .setAccessToken("YOUR_ACCESS_TOKEN")
        .setProjectId("YOUR_GOOGLE_CLOUD_PROJECT_ID")
        .build()
}
val textRequest by lazy {
    vertexAI.textRequest()
        .setMaxTokens(256)
        .setTemperature(0.8)
}
viewModelScope.launch {
    val result = textRequest.execute("Say this is a test")
    print(result.getOrThrow())
}
```

## iOS setup
To use this library in your iOS project, follow these steps:

1. Go to your project’s file settings and click "Add Package".
2. In the search bar at the top right, enter https://github.com/hexascribe/vertexai-kt.git
3. Once you have found the package, click the "Add Package" button.

You can now start using the VertexAI-KT in your iOS project!

Take a look at the Swift code snippet below for an example of how to initialize and use one of the supported features:

```swift
var vertexAI: VertexAI {
    VertexAI.Builder()
        .setAccessToken(accessToken: "YOUR_ACCESS_TOKEN")
        .setProjectId(projectId: "YOUR_GOOGLE_CLOUD_PROJECT_ID")
        .build()
}
var textRequest: TextRequest {
    vertexAI.textRequest()
}
Task.init {
    let result = try await textRequest.execute(prompt: "Say this is a test")
    print(result.getOrThrow())
}
```

## ℹ️ Sample apps

Take a look at our sample apps to learn how to use the SDK on different platforms:

[Android Sample](samples/android)
<br />
[iOS Sample](samples/ios)

## 🤝 Contributions

Feel free to make a suggestion or if you find any error in this project, please open an issue. Make sure to read our [contribution guidelines](CONTRIBUTING.md) before.

## 📄 License

```
    Copyright 2023 Hexa Scribe

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```
