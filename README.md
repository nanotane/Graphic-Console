# Graphic-Console
**What is this?:**

This is a simple console like program that I have designed and made in java using
some simple java graphics. The idea of this program was to make a program that I could
export as a jar file and run my simple console based programs through it while also displaying them in a 
colorful and fun manner. When I went to work for iD Tech camp over the summer I realized
that this program would be an excellent teaching tool for kids. Any console based program can easily be made 
to run through on the GC which makes it a great way for kids to take their first programs and make them more intereting 
while also learning about polymorphism. 

**Plugin structure**

All plugins extend the class 'Plugin' and implement the interface 'RunCommand.' 
Plugins have:
-description
-version
-author
-name
-command (The command to type in to run the plugin)
-runAtStart (A boolean that will force the plugin to run when the console is started)
-theClass (The MainClass object that this plugin points to)
Plugins should (but are not required to) have a start method that contains their relavent code. If most of the code
was previously in the main method, it should be in here. The run method should only be calling the start method for the plugin to work.

**Methods for printing strings and getting user input**

*If you are an iD tech instructor than this is very important to teach to campers.*
Instead of using scanners to grab console input, a programer uses

MainClass.waitForInput() which returns the input as a string
MainClass.waitForInt() which returns the input as an integer

In order to print out something to console use

MainClass.addToChatLog(String stuff) which is the same as System.out.println()
MainClass.addToChatLog(String stuff) which is the same as System.out.print()

**More indepth on how it works**

The MainClass handles all of the graphics, while ProcessCommands handles input from the user. When a user first starts
the program they are given a blank screen with some instructions. This is the menu. Typing /help will display all of the plugins and
relavent information about them such as description, author, command etc. Everytime a user types somethng onto the menu,
ProcessCommands checks to see if that was a command for one of the plugins. If so then it runs that plugin and will return to the menu
once the plugin is finished. 
The MainClass and ProcessCommands run in two different threads, so graphics can be updated while the plugin runs. When ProcessCommands
runs a plugin it is running the method of a plugin object in its thread. This means that the user can't start multiple plugins or exit
the plugin until the method returns. If the plugin crashes then the thread for ProcessCommands is killed but MainClass keeps running,
which is something to keep in mind if someone is confused as to why their program isn't reading inputs. 
There is one odd issue that can come up at times. With certain computers it seems that it only lets the ProcessCommands thread run and not the MainClass one. This seems to mostly be solved by having the threads sleep every so often, but keep this in mind if you are getting weird behavior. 

**Contact information**
If you have any questions on how the program works or need help with it, contact me at nanotane1@yahoo.com and I will try to answer you in a timely manner. 
