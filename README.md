# Lynk
A home automation client for my dorm using a Spark Core, the Particle Cloud SDK, Android and Firebase.

**Status:** Under development

**To do**
* ~~Basic interaction between the Spark Core and Android client~~
* ~~Firebase integration to restore Core's state, or fetch the state on multiple clients~~
* Events that get triggered based on device location
* XMPP server scripts for pushing alerts to devices
* Ability to control common applicances
* Background service for location awareness
* UI/UX
* Widget for quicker access
* ~~Script to dump on the Spark Core~~
* Spark Core's circuit

## Setup
* Create a Credentials class in ~/com/vathsav/lynk/utils containing credentials to your Particle account.

```java
public static String particleEmail = "user@domain.com";
public static String particlePassword = "your_password";
```
* Set the backend up. Refer [Add Firebase to your app.](https://firebase.google.com/docs/android/setup#add_firebase_to_your_app)

## License

Copyright 2016 Vathsav Harikrishnan

Licensed under the GPLv3: http://www.gnu.org/licenses/gpl-3.0.html
