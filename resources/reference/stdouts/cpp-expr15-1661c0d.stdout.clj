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
double test1();
:std:complex<double> test2();
int32_t test3();
:std:complex<double> __lpython_overloaded_9__complex(int32_t x, int32_t y);
float _lfortran_caimag(:std:complex<float> x);
double _lfortran_zaimag(:std:complex<double> x);
namespace {
}

// Implementations
:std:complex<double> __lpython_overloaded_9__complex(int32_t x, int32_t y)
{
    :std:complex<double> _lpython_return_variable;
    _lpython_return_variable = :std:complex<double>(x) + :std:complex<double>(y)*:std:complex<double>(0.000000, 1.000000);
    return _lpython_return_variable;
}

float _lfortran_caimag(:std:complex<float> x);

double _lfortran_zaimag(:std:complex<double> x);

double test1()
{
    double _lpython_return_variable;
    double x;
    x =   1.00000000000000000e+00;
    _lpython_return_variable = x;
    return _lpython_return_variable;
}

:std:complex<double> test2()
{
    :std:complex<double> _lpython_return_variable;
    :std:complex<double> x;
    x = __lpython_overloaded_9__complex(2, 2);
    _lpython_return_variable = x;
    return _lpython_return_variable;
}

int32_t test3()
{
    int32_t _lpython_return_variable;
    :std:complex<double> x;
    :std:complex<float> y;
    x = :std:complex<double>(0.000000, 4.000000);
    y = :std:complex<double>(0.000000, 4.000000);
    _lpython_return_variable = 0;
    return _lpython_return_variable;
}

void _lpython_main_program()
{
    :std:cout << test1() << :std:endl;
    :std:cout << test2() << :std:endl;
    :std:cout << test3() << :std:endl;
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
