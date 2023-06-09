#include <complex.h>
#include <inttypes.h>
#include <math.h>

#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>
#include <string.h>
#include <lfortran_intrinsics.h>

#define ASSERT(cond)                                                           \
    {                                                                          \
        if (!(cond)) {                                                         \
            printf("%s%s", "ASSERT :failed ", __FILE__);                       \
            printf("%s%s", "\nfunction ", __func__);                           \
            printf("%s%d%s", "(), line number ", __LINE__, " at \n");          \
            printf("%s%s", #cond, "\n");                                       \
            exit(1);                                                           \
        }                                                                      \
    }
#define ASSERT_MSG(cond, msg)                                                  \
    {                                                                          \
        if (!(cond)) {                                                         \
            printf("%s%s", "ASSERT :failed ", __FILE__);                       \
            printf("%s%s", "\nfunction ", __func__);                           \
            printf("%s%d%s", "(), line number ", __LINE__, " at \n");          \
            printf("%s%s", #cond, "\n");                                       \
            printf("%s", "ERROR :MESSAGE\n");                                  \
            printf("%s%s", msg, "\n");                                         \
            exit(1);                                                           \
        }                                                                      \
    }


struct dimension_descriptor
{
    int32_t lower_bound, length;
};

// Implementations
double __lpython_overloaded_0__pow(int32_t x, int32_t y)
{
    double _lpython_return_variable;
    _lpython_return_variable = (double)(pow(x, y));
    return _lpython_return_variable;
}

float _lfortran_caimag(float complex x);

double _lfortran_zaimag(double complex x);

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

int main(int argc, char* argv[])
{
    _lpython_set_argv(argc, argv);
    _lpython_main_program();
    return 0;
}
