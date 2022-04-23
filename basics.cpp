#include <iostream>
#include <string>
using namespace std;

void passByValue(int a) {
    ++a;
    cout << a << "\n";
}

void passByReference(int* a) {
    ++(*a);
    cout << *a << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    //-------------------------------------------

    int a = 10, b = 20;
    cout << a << " " << b << "\n"; //10 20
    swap(a, b);
    cout << a << " " << b << "\n"; //20 10

    const int c = 10;
    //c = 20; //error: assignment of read-only variable 'c'
    //-------------------------------------------

    for (int i = 0; i < 10; ++i) {
        cout << i << " ";
    } //0 1 2 3 4 5 6 7 8 9

    int i = 0;
    while (i < 10) {
        cout << i << " ";
        ++i; 
    } //0 1 2 3 4 5 6 7 8 9
    //-------------------------------------------

    int x = 5;
    passByValue(x); //6
    cout << x << "\n"; //5
    
    x = 5;
    passByReference(&x); //6
    cout << x << "\n"; //6
    //-------------------------------------------

    //static arrays can't be resized, use std::vector for dynamic resizing
    int ar[5] = {1, 2, 3, 4, 5};
    for (int i = 0; i < 5; ++i) {
        cout << ar[i] << " ";
    } //1 2 3 4 5

    int br[5] = {100};
    for (int i = 0; i < 5; ++i) {
        cout << br[i] << " ";
    } //100 0 0 0 0
    //-------------------------------------------

    //bitwise operations (use std::bitset for space optimization)
    int m = 10, n = 20; //00001010 , 00010100
    cout << (m & n) << "\n"; //0
    cout << (m | n) << "\n"; //30
    cout << (m ^ n) << "\n"; //30
    cout << ~m << "\n"; //-11
    for (int i = 31; i >= 0; --i) {
        cout << ((m >> i) & 1);
    } //00000000000000000000000000001010
    //-------------------------------------------
    
    //accuracy of floating point numbers
    int a = 10, b = 0;
    cout << a / b; //Exception: STATUS_INTEGER_DIVIDE_BY_ZERO

    float a = 10, b = 0;
    cout << c / d; //inf

    double a = 0.3 * 3 + 0.1, b = 1;
    cout << "a=" << a << " " << "b=" << b << "\n"; //a=1 b=1
    if (a < b) {
        cout << "a is less than b\n";
    } //a is less than b
    //-------------------------------------------

    //characters and strings
    cout << (char) 97 << "\n"; //a
    cout << (int) 'A' << "\n"; //65
    int a = '0'; cout << a << "\n"; //48
    for (char ch = 'a'; ch <= 'z'; ++ch) cout << ch; //abcdefghijklmnopqrstuvwxyz

    string s = "hello";
    cout << s << "\n"; //hello
    cout << s + "world" << "\n"; //helloworld
    cout << s.size() << "\n"; //5
    cout << s[1] << "\n"; //e
    s[0] = 'H'; cout << s << "\n"; //Hello
    cout << s.substr(1) << "\n"; //ello
    cout << s.substr(1,3) << "\n"; //ell
    cout << s.find("ell") << "\n"; //1
    cout << s.find("ek") << "\n"; //std::string::npos = 18446744073709551615 (== -1)
    int a = 12345; cout << to_string(a) << "\n"; //12345
    string b = "123"; cout << stoi(b) << "\n"; //123
    //-------------------------------------------
    
    return 0;
}
