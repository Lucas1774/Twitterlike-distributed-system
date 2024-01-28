THIS WAS A COLLEGE PROJECT.

## Program

Full stack chatting app, where the database is implemented as a Java object, as opposed to in another more common way.  
I was a little bit lazy writing this, so some functionalities could be better shaped, ie, the "unblock" function from the server doesn't unleash the battery of stacked twits into connected followers, but rather it stores them as if the followers were not connected, even if they are (something that I exposed as a design choice, but my professor considered a mistake).  
The app is of course locally executed, so not applicable to the real world as is. Its main agents are the server requests and process expropriation. Protocol management is not part of it. It would require port forwarding through the router configuration and probably some security to be a somewhat serious application.

## Usage

Run the database and the server. Then you can run the client applications. Notice that you can register clients from any instance, not just the one you are going to use to log in. The program has not been tested for multiple logins of the same user from different terminals, so its behavior in that scenario might be erratic.  
If you want to use the app on your private network you will need to swap my private IP in every instance of the code with the one where the database and the server will be run (or change yours to mine).