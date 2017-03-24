One can compile and run the program by compiling the file 
DetectVirus.java and running the resulting file with java 
command. The file must also be executed with the input file
redirected to the program via input redirection.

The program has been implemented using an adjacency list 
data structure with BFS algorithm running (which made 
use of bitmap and queue). 

For adjacency list, I had utilized the Java library classes
ArrayList and Integer class array. I used ArrayList to allow 
for dynamic size allocation of the edges (allowing me to 
save the space by not preallocating M edges for all N nodes) 
and Integer array, as ArrayList do not work with Java primitives.

I have also used the ListIterator and Queue provided by Java
library. I have used ListIterator to allow for O(1) traversal 
across the ArrayList, and Queue for the iterative implementation
of the BFS. 

Furthermoer, I have used an array of N integers to represent the 
weights (time) of each nodes and utilized their values to prevent
from entering unnecessary edges.
