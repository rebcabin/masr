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

int main(int argc, char* argv[])
{
    _lpython_set_argv(argc, argv);
    _lpython_main_program();
    return 0;
}
