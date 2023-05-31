#include <inttypes.h>

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
static int64_t fib(int64_t n)
{
    int64_t _lpython_return_variable;
    if (n < 2) {
        _lpython_return_variable = n;
        return _lpython_return_variable;
    }
    _lpython_return_variable = fib(n - 1) + fib(n - 2);
    return _lpython_return_variable;
}

void main0()
{
    int64_t ans;
    int64_t x;
    x = 5;
    ans = fib(x);
    ASSERT(ans == 5);
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
