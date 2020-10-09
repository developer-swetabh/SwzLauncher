# SwzLauncher
This is a Simple Launcher application for android.
<br>
<H2>What does it contain?</H2>
It contains a list of applications installed on your device and a search widget.
Here I have used a recycler view with GridLayout as its layout manager. 
It is a 3 column grid.
<br>
<H2>Application architecture</H2>
The project has been split into two modules:
<ul><li> SDK - All query functions for all data required in the UI/App.</li>
<li>App - UI implementation for listing and launching applications.</li></ul>
<H3>SDK</H3>
Launcher SDK will return all the launcher apps list which are currently installed on the phone. Here are the main features of the SDK: 
<ul><li>List of apps containing following information - App name, Package name, Icon, Main Activity class name, Version code, and Version name.</li>
<li>The list is in ascending order based on app-name</li></ul>
<H3>App</H3>
UI implementation for listing and launching applications. Here are the main features of app:
<ul><li>List application data in a recycler view.</li>
<li>Launch the application when clicked on the app icon/list item.</li> 
<li>Add a search bar on top which will filter the application list based on the name.</li></ul>

