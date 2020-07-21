# Silent Order Post

Braspag Integration offered to retailers.

Payment data is securely trafficked maintaining complete control over the checkout experience.

This library enables you to securely send your customer payment data directly to our system. Payment data such as card number and expiration date are stored in the Braspag environment, which is PCI DSS 3.2 certified.

It is ideal for retailers who demand a high degree of security without losing the identity of their page, allowing full customization on their checkout page.

## Setup

### Dependency

Add this dependency to your module level build.gradle in dependencies node

```groovy
dependencies {
    ...
    implementation 'br.com.braspag:silentorderpost:1.0.0'
}
```

> Don't forget to check if you have **jcenter()** as a repository.

- Or download *simplewebclient-release.aar* from [releases](https://github.com/Braspag/silent-order-post-android/releases), move it to your app module libs folder and add this dependency to your app module level build.gradle in dependencies node

```kotlin
dependencies {
    ...
    implementation files('libs/silentorderpost-release.aar')
}
```

## Usage

### Instantiate

Instantiate **SilentOrderPost** as example below:

```kotlin
val sop = SilentOrderPost(
    merchantId = "MERCHANT-ID",
    environment = SilentOrderPostEnvironment.SANDBOX
)
```

### Set parameters

Parameters that can be set are **binQuery**, **verifyCard** and **tokenize**.

| Parameters 	| Products         	| Response                                               	|
|------------	|------------------	|--------------------------------------------------------	|
| binQuery   	| Consulta BIN     	| Card Data                                              	|
| verifyCard 	| Zero Auth        	| Returns if card is valid                               	|
| tokenize   	| Cartão Protegido 	| CardToken if set to true, PaymentToken if set to false 	|


```kotlin
sop.binQuery = true
sop.verifyCard = true
sop.tokenize = true
```

### Send Card Data

```kotlin
sop.sendCardData(
    request = SilentOrderPostRequest(
        cardHolder = "Maurici Ferreira Junior",
        cardNumber = "4111111111111111",
        cardExpirationDate = "01/2030",
        cardSecurityCode = "123"
    )
) {
    // callback
}
```