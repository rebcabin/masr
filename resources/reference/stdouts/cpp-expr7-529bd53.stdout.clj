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
void test_pow();
int32_t test_pow_1(int32_t a, int32_t b);
double __lpython_overloaded_0__pow(int32_t x, int32_t y);
float _lfortran_caimag(:std:complex<float> x);
double _lfortran_zaimag(:std:complex<double> x);
namespace {
}

// Implementations
double __lpython_overloaded_0__pow(int32_t x, int32_t y)
{
    double _lpython_return_variable;
    _lpython_return_variable = (double)(:std:pow(x, y));
    return _lpython_return_variable;
}

float _lfortran_caimag(:std:complex<float> x);

double _lfortran_zaimag(:std:complex<double> x);

void test_pow()
{
    int32_t a;
    a = (int32_t)(__lpython_overloaded_0__pow(2, 2));
}

int32_t test_pow_1(int32_t a, int32_t b)
{
    int32_t _lpython_return_variable;
    int32_t res;
    res = (int32_t)(__lpython_overloaded_0__pow(a, b));
    _lpython_return_variable = res;
    return _lpython_return_variable;
}

void main0()
{
    int32_t c;
    test_pow();
    c = test_pow_1(1, 2);
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
