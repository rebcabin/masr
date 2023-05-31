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
int32_t test_factorial_1(int32_t x);
int32_t test_factorial_2(int32_t x);
int64_t test_factorial_3(int32_t x);
namespace {
}

// Implementations
int32_t test_factorial_1(int32_t x)
{
    int32_t _lpython_return_variable;
    int32_t result;
    if (x < 0) {
        _lpython_return_variable = 0;
        return _lpython_return_variable;
    }
    result = 1;
    while (x > 0) {
        result = result*x;
        x = x - 1;
    }
    _lpython_return_variable = result;
    return _lpython_return_variable;
}

int32_t test_factorial_2(int32_t x)
{
    int32_t _lpython_return_variable;
    int32_t i;
    int32_t result;
    result = 1;
    for (i=1; i<=x + 1 - 1; i++) {
        result = result*i;
    }
    _lpython_return_variable = result;
    return _lpython_return_variable;
}

int64_t test_factorial_3(int32_t x)
{
    int64_t _lpython_return_variable;
    int64_t result;
    result = 0;
    if (x < 0) {
        _lpython_return_variable = result;
        return _lpython_return_variable;
    }
    result = 1;
    while (x > 0) {
        result = result*x;
        x = x - 1;
    }
    _lpython_return_variable = result;
    return _lpython_return_variable;
}

void main0()
{
    int32_t i;
    int64_t j;
    i = test_factorial_1(4);
    i = test_factorial_2(4);
    j = test_factorial_3(5);
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
