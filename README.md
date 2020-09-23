# oop ex-2
Our project represents a collection of mathematical functions type of Monom, Polynom, complex function. The mathematical functions can be presented on a GUI window and can be saved (and load) to file.
In our project we have many methods which can be applied on Monoms, Polynoms and complex function.
![Untitled Document](https://user-images.githubusercontent.com/57639675/93993679-b4987200-fd97-11ea-8d63-5d29e10c31e0.png)

# function - interface
This interface extends Serializable, function represents a simple function of type y=f(x), where both y and x are real numbers.
The methods in this interface are:
* initFromString
* copy
* equals
* toString

# Monom
Monom is an expression of the form ax^b (axb) or ax^b (axb) when a is a real number and b is a non-negative integer.
The class implements function and support simple operations as:
* derivative
* add
* multiply
* f-  f(x), returns the Monom's value within the given x.
* isZero - checks whether the Monom's value is 0.
* constructor - that builds a new Monom according to a string.

# cont_function - interface
This interface extends function, cont_function represents a continuance function.
The methods in this interface are:
* root- compute a value x' (x0<=x'<=x1) for with |f(x')| < eps.
* area- compute a Riman's integral from x0 to x1 in eps steps.
# Polynom_able - interface
This interface represents a general Polynom: f(x) = a_1X^b_1 + a_2*X^b_2 ... a_n*Xb_n,
where: a_1, a_2 ... a_n are real numbers and b_1<b_2..<b_n are none negative integers (naturals).
For formal definitions see: https://en.wikipedia.org/wiki/Polynomial 
The methods in this interface are:
* add- Polynom to a Polynom and Monom to a Polynom
* subtract
* multiply- by a Monom or a Polynom
* f- f(x),returns the Polynom's value within the given x
* derivative
* isZero - checks whether the Polynom's value is 0
 
# Polynom
The class implements Polynom_able.
Polynom is an ArrayList of Monom (every _cell_ represent a diffrent power)
This class can also build Polynom from a given string such as ax^b+ax^b+... without spaces when every Monom should be in accordence with the rules of the Monom.

# complex_function - interface
This interface extends function, complex_function represents a complex function of type y=g(f1(x), f2(x)), where both f1, f2 are functions (or complex functions), 
y and x are real numbers and g is an operation: plus, mul, div, max, min, comp (f1(f2(x))).
The left side should always exists.
The methods in this interface are:
* plus
* mul
* div
* max
* min
* comp
* left- returns the left side of the complex function.
* right- returns the right side of the complex function.
* getOp

# ComplexFunction
The class ComplexFunction implements complex_function.
The class defined as follows:
* Left field- type function which means it could be a Polynom, a Monom or a complex function as well.
* Right field- type function which means it could be a Polynom, a Monom or a complex function as well.
* Op- type Operation -Enum that could be one of the following only- Plus, Times, Divid, Max, Min, Comp, None, Error.
# functions - interface
This interface extends Collection<function>, this interface represents a collection of mathematical functions, 
 which can be presented on a GUI window and can be saved (and load) to file. 
The methods in this interface are:
* initFromFile
* saveToFile
* drawFunctions- draws all the functions in the collection in a GUI window using the given parameters for the GUI windo and the range & resolution.
* drawFunctions- draws all the functions in the collection in a GUI window using the given JSON file.
 
# Function_GUI 
The class Function_GUI implements functions. Function_GUI is an ArrayList of functions(complexFunction,Polynom, or Monom).

![draw](https://user-images.githubusercontent.com/57639675/94000373-c4b44f80-fd9f-11ea-9f5a-2db06c858d81.png)
