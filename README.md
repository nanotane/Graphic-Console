# Graphic-Console
**What is this?:**
This is a simple console like program that I have designed and made in java using
some simple java graphics. The idea of this program was to make a program that I could
export as a jar file and run my simple console based programs through it while also displaying them in a 
colorful and fun manner. When I went to work for iD Tech camp over the summer I realized
that this program would be great for students to play. Any console based program can easily be made 
to run through this console which makes it a great way for kids to take their first programs 
and transform them while also learning about polymorphism. 

**How does it work**
There are two main components, the MainClass which consists of all the graphics code
as well as methods for printing to the console, and the CommandProcessor which runs in
a seperate thread and, as the name suggests, processes what has been typed in to see
if it is a registered command. The class also creates all of the program objects and
keeps track of them. 

Program objects extend the Program class, and contain information such as the command
the user types in to run the program, the program author, program name etc. In order to
add a program to the console, you must first make a class that extends the Program class.
After you have designed your program add it to the program list inside of the CommandProcessor class.
The list is inside of the constructor.

There are two seperate threads running for this program, the MainClass thread for graphics, and the 
ProcessorCommand thread. When a program is run, currently the Process Command thread calls a method to 
run that program. I want to improve this later on so that when a program is started a new thread is made.

**What will be added?**
I want to make this program easier to use for new programmers as well as simplify the process
of adding programs. Here is a quick list of features I plan to add but keep in mind this is not
a definite list.
1. Commands to exit any program as it is running
2. Settings to change font text, background and foreground colors, etc
3. Ability to add new programs without touching the CommandProcessor class
4. More standard programs such as reading and writing to files
