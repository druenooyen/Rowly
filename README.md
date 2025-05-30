# Rowly: Rowing Workout Tracker

## Project Description

**Purpose and Target Audience:**  
Rowly is aimed to help users track and review their rowing workouts. It allows users to input key rowing workout statistics, including date, distance, and time, and adds them into a personal workout library. It includes additional features like tracking total distance and highlighting personal bests to keep rowers motivated. My target audience is rowers that are new to the sport and are looking for a simple, straightforward tool to help track and improve their performance over time.

**Why this project is meaningful to me:**  
I joined the UBC rowing team as a novice rower in September 2024 and have been looking for a simple rowing workout tracker. Many existing workout trackers are either not designed for rowing or overwhelm users with extensive data and statistics. My goal is to build a straightforward tracker that only encorporates key statistics and includes a few useful data tracking features that help keep users motivated and support their performance improvement.

## User Stories

- As a user, I want to be able to add a workout to my workout log.
- As a user, I want to be able to view a list of all the workouts in my workout log.
- As a user, I want to be able to flag workouts that are personal bests or significant milestones.
- As a user, I want to be able to see the total number of workouts completed, total distance rowed and total time rowed.
- As a user, I want to be able to see my current personal bests. 
- As a user, I want to be prompted with an option to save my logbook to a file when selecting quit from the menu.
- As a user, I want to be prompted with an option to open my saved logbook from file upon starting the application. 

## Instructions for End User

- To add a new entry to their Logbook, the user can select "Add an Entry" from the dropdown menu, fill in their data and click "Add Entry to Logbook".
- To view all the entries in the logbook, the user can select the "View Entries" from the dropdown menu.
- To view personal bests, the user can select the "Personal Bests" from the dropdown menu.
- To view logbook totals, the user can select the "Logbook Totals" from the dropdown menu.
- You can locate the visual component on a splash screen upon opening the app.
- You can save the state of the application by selecting "Save and Exit" from the dropdown menu or by closing the window,  which will prompt a pop up to save to file.
- You can reload the state of the application via the pop up upon opening the application.

## Phase 4
### Task 2
#### Sample log:  
Entry added to logbook.  
2km personal best updated.  
Totals recalculated.  
Entry added to logbook.  
Totals recalculated.  
Entry added to logbook.  
6km personal best updated.  
Totals recalculated.  
Entries saved to file.   

### Task 3
After generating a UML diagram, I noticed all three GUI classes reference the same logbook instance to retrieve and update information. This leads to a high level of coupling, and I ran into issues where updating the logbook in one class led to having to update it accordingly in the other classes as well. To address this, I could implement the Singleton design pattern, as only one logbook instance is ever needed. This approach would ensure consistent access across classes and prevent unnecessary passing of the logbook.

I also recognize refactoring opportunities to improve cohesion in my GUI classes. My ActionPanelGUI currently includes all the user navigation panels, but I could refactor by creating separate classes for each panel that extend JPanel, with a superclass (ActionPanel) that manages navigation behavior. Similarly, my RowlyGUI classes contain logic that could be separated into dedicated classes, such as extracting persistence-related behavior and the splash screen into their own components.




 
