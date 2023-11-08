# Project Description
This is my coding assessment solution for the Android Mobile Engineer Apprenticeship position at Fetch. I dedicated approximately 3.5 hours in total to develop and refine this solution.

# Project Screenshot
<img width="322" alt="Screen Shot 2023-11-08 at 11 25 05 AM" src="https://github.com/ezbuckeye/EdisonZhangSolution/assets/105604551/8dbab168-0f03-449b-a780-801a3415d8b0">

# Project Architecture

This Kotlin project follows the MVVM (Model-View-ViewModel) design pattern, a common approach in Android development that separates the concerns of data representation, user interface, and the logic that connects them, providing a clear and organized structure.

## `model` Package

- **Candidate.kt:** This data class represents the individual candidate with its attributes. It serves as a simple and convenient way to encapsulate candidate information.

- **MainRepository.kt:** The `MainRepository` is responsible for managing data retrieval from the remote data source. The `ViewModel` will interact with this repository to fetch candidate data and populate the UI.

## `view` Package

- **MainActivity.kt:** As the entry point of the app, `MainActivity` is where the app's core components are initialized. This includes setting up API services, creating the repository instance, and initializing the ViewModel.

- **CandidatesScreen.kt:** The `CandidatesScreen` class focuses on rendering the table layout for displaying the candidate information. It utilizes the data provided by the ViewModel to populate the UI.

## `viewModel` Package

- **MainViewModel.kt:** The `MainViewModel` is designed to store the state of the candidates, encapsulating the logic required to manage this data. It acts as a bridge between the data in the model and the UI in the view.

- **MyViewModelFactory.kt:** The `MyViewModelFactory` class is responsible for providing instances of the `MainViewModel`. This factory helps create ViewModel instances with the necessary dependencies and configurations. It ensures that the ViewModel is correctly set up and ready to serve as the intermediary between the model and the view.
