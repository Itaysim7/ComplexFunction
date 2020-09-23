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


ComplexFunction has 3 variables, function left, function right,and operation.the functions can be Monom, Polynom or ComplexFunction. the operation is an enom that contains {Plus, Times, Divid, Max, Min, Comp , None, Error}.
 this class can also build ComplexFunction from a given String such as div(mul(x,2x),x), or x^2+5(the bulider applys the polynom in function left , right is null, and the operation is none)
 this class has two more constuctors, a regular constructor that get two functions and a string of the operation.and a constructor that gets only one function.
the class also has:
a get funcion for every variable.
a function for every opration(except none and error) that recieves another function and adds the complex function with the function according the operation.
toString function
f- that returns the value in the given x
copy
initfromstring that gets a string and returns the complexfunction


Function_GUI is an ArrayList of functions(complexFunction,Polynom, or Monom).it has all the functions that a collaction in java has.
in addition it can do:
init from file
save to file
draw functions given:int width, int height, Range rx, Range ry, int resolution.
and draw function givin a json file
