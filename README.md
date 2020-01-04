# Lights Simulator
The Lights Simulator is a Java program that displays a grid of 20x7 with methods that allow you to draw pixels using x y coordinates, where (0, 0) is top left.

The idea behind the simulator is that you can program and test your sequences first, then transfer the logic over to the Arduino code, making only minor syntax changes for C++ compatibility.

The Simulator comes with several helper functions that exist in both the C++ and Java code. The reason is because the LEDs are set up in a snaking pattern, so the helper functions abstract the differences between Simulator and Reality.

## Using the Simulator
This is a purely native Java application, so no libraries are required. Simply import into either VSCode (recommended) or Eclipse and run:

VSCode: Install the Java extension, then click Debug > Start Debugging (shortcut F5)

Eclipse: Run the program by clicking on the green start button.