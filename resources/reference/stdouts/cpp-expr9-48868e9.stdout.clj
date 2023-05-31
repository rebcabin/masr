#include <iostream>
#include <string>
#include <vector>
#include <cassert>
#include <cmath>
#include <complex>
#include <Kokkos_Core.hpp>
#include <lfortran_intrinsics.h>

template <typename T>
:Kokkos:View<T*> from_std_vector(const :std:vector<T> &v)
{
    :Kokkos:View<T*> r("r", v.size());
    for (size_t i=0; i < v.size(); i++) {
        r(i) = v[i];
    }
    return r;
}


struct dimension_descriptor
{
    int32_t lower_bound, length;
};
// Forward declarations
void _lpython_main_program();
void main0();
int32_t test_return_1(int32_t a);
:std:string test_return_2(int32_t a);
int32_t test_return_3(int32_t a);
void test_return_4(int32_t a);
namespace {
}

// Implementations
int32_t test_return_1(int32_t a)
{
    int32_t _lpython_return_variable;
    int32_t x;
    x = 5;
    _lpython_return_variable = x;
    return _lpython_return_variable;
}

:std:string test_return_2(int32_t a)
{
    :std:string _lpython_return_variable;
    :std:string x;
    x = "test";
    _lpython_return_variable = x;
    return _lpython_return_variable;
}

int32_t test_return_3(int32_t a)
{
    int32_t _lpython_return_variable;
    a = 3;
    _lpython_return_variable = a;
    return _lpython_return_variable;
}

void test_return_4(int32_t a)
{
    a = 1;
    return;
}

void main0()
{
    int32_t i;
    :std:string s;
    i = test_return_1(4);
    s = test_return_2(4);
    i = test_return_3(4);
    test_return_4(4);
}

void _lpython_main_program()
{
    main0();
}

namespace {

void main2() {
    _lpython_main_program();
}

}
int main(int argc, char* argv[])
{
    :Kokkos:initialize(argc, argv);
    main2();
    :Kokkos:finalize();
    return 0;
}
