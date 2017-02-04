JSketch
Made by: Kamil Ladhani

Made on: OSX El Capitan
JDK version: 1.8.0_91


Tools, Colors, & Thicknesses:

You are able to draw the 3 types of shapes required (line,circle,rectangle) in any direction by clicking, dragging, and releasing. Note that drawing circles must be done in a diagonal direction.

You can then moved shapes by selecting the "select" tool, and clicking and dragging on a shape. Note that a shape is selected if it has a dotted border. If multiple shapes overlap, the front one will be chosen. A shape will be deselected if you click anywhere not inside a shape, or any other tool (other than the select tool). 

You can erase shapes by first selecting the erase tool and then clicking on a shape. Again, if shapes overlap, the front one will be erased. The fill tool works similarly. Select the fill tool and a color, and then select a shape on the canvas to fill it with the appropriate color.

There are 6 available colors with the option of using the chooser for more colors. There are 4 thicknesses to choose from. 

Note that a black dotted outline will indicate the selected tool, colour, and thickness.


File Saving/Loading:

To save a file, click file->save, and save as a .txt file in any directory. Use file->load to load that file back into the program. Use file->new to create a new file. Note: saving and loading a file does not take into account canvas size. Therefore shapes will load as the same size irrespective of the canvas size when they are loaded.


Views:

The default view is fit-to-window. Resizing the window will change the size of the canvas. Note that the canvas will change uniformly (and scale shapes appropriately).

Switching views to Full-size will show scrollbars if you make the window smaller than the current size (and will keep the canvas the same size).



Overall Design:

The overall layout manager is a BorderLayout. There are 6 tools, 6 default colors to choose from, and 4 thicknesses on the west sidebar, all using setMinimum, setPreferred, and setMaximum sizes (in order to be dynamic). The main canvas is in the center, also uses a preferred size, and is wrapped inside a JScrollPane in order to enable scrollbars when needed.













