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
int64_t fib(int64_t n)
{
    int64_t _lpython_return_variable;
    if (n < 2) {
        _lpython_return_variable = n;
        return _lpython_return_variable;
    } else {
        _lpython_return_variable = fib(n - 1) + fib(n - 2);
        return _lpython_return_variable;
    }
    return _lpython_return_variable;
}

void main0()
{
    int64_t ans;
    ans = fib(15);
    ASSERT(ans == 610);
}

void _xx_lcompilers_changed_main_xx()
{
    int64_t ans;
    ans = fib(10);
    ASSERT(ans == 55);
}

void _lpython_main_program()
{
    main0();
    _xx_lcompilers_changed_main_xx();
}

int main(int argc, char* argv[])
{
    _lpython_set_argv(argc, argv);
    _lpython_main_program();
    return 0;
}
