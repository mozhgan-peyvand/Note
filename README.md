# Note

# 3rd-Party Libraries

Used material3 for designing layouts and UI.

Used StateFlow as a data holder for observing the screen's state changes.

Used hilt for using dependency injection all over the app.

Used kotlinx-coroutines for using the concurrency design pattern and doing asynchronous jobs.

Used junit for writing unit tests.

Used truth for easy assertion in tests.

Used mockk for mocking.

Used turbine for testing flows.

Used kotlinx-coroutines-test for testing coroutines.


### Architecture:

I decided to develop this app by using **MVVM clean architecture**. And here are my top 3 reasons for using it:

1. **Low coupling:** Build things in a distributed manner. It's all about the **separation of concerns**. You can also use multiple view models inside a single view.
  
2. **Test Friendly:** ViewModel has no reference to the View (Activity/Fragment) and can be tested easier than MVP for instance.
  
3. **Understand the app easier:** Domain layer tells you everything! what this app is all about and also what it can do for the user!
  

### Modules:

- **base:** This module is responsible for keeping all settings and basic classes used in many modules.
  
- **di:** This module is responsible for module classes that is used by Hilt and providing.
 
- **feature-note:** this module holds all module related to notes feature : 
 
   - **data-note:** This module holds all classes and files, including doa, dataSources, RepositoryImpl, etc related to Note data.
  
   - **domain-note:** I believe that the domain is the app's language; it speaks about all the services it provides. So you can find all use-cases used within the app here. Note that the UI and Data layers depend on this module according to clean architecture concepts.
  
   - **ui-note:** All the ui related to the note's user interface are accessible in this module.

