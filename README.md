# CryptoApp

## Technologies
- Language: Kotlin
- Architecture: MVI
- UI: Compose
- Coroutines
- Retrofit
- Koin
- DataStore
- Pagination
- Material3

## Content

App consists of 3 screens: List of crypto assets, Info about chosen asset and app settings. First is just a paginated list of assets, clicking on any of which opens second screen. Info screen shows detailed information about asset and a list of markets with this asset. Setting screen allows to change app theme.

## How it works

Data comes from https://api.coincap.io/v2/. First screen loads all the assets. Navigating to second screen passes the id of an asset, then second screen sends the request that loads information about this asset and markets, associated with it. Dark theme settings are stored in DataStore. 
