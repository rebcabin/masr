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
void test_for();
namespace {
}

// Implementations
void test_for()
{
    int32_t i;
    for (i=0; i<=10 - 1; i++) {
        if (i == 0) {
            continue;
        }
        if (i > 5) {
            break;
        }
        if (i == 3) {
            exit(0);
        }
    }
}

void _lpython_main_program()
{
    test_for();
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
