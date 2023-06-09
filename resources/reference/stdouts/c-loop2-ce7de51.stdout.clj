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

struct list_str {
    int32_t capacity;
    int32_t current_end_point;
    char** data;
};

inline bool compare_list_str_(struct list_str a, struct list_str b);
inline bool compare_str(char* a, char* b);
inline void print_list_str_(struct list_str a);
inline void print_str(char* a);
inline void list_init_str(struct list_str* x, int32_t capacity);
inline void list_deepcopy_str(struct list_str* src, struct list_str* dest);
inline void resize_if_needed_str(struct list_str* x);
inline void list_append_str(struct list_str* x, char* element);
inline void list_insert_str(struct list_str* x, int pos, char* element);
inline int list_find_item_str(struct list_str* x, char* element);
inline void list_remove_str(struct list_str* x, char* element);
inline void list_clear_str(struct list_str* x);
inline struct list_str* list_concat_str(struct list_str* left, struct list_str* right);
inline struct list_str* list_section_str(struct list_str* x, int32_t idx1, int32_t idx2, int32_t step, bool i1_present, bool i2_present);


// Implementations
struct list_str argv;
int32_t _lpython_get_argc();

char* _lpython_get_argv(int32_t index);

struct list_str _lpython_argv()
{
    struct list_str _lpython_return_variable;
    int32_t argc;
    struct list_str argv;
    int32_t i;
    argc = _lpython_get_argc();
    struct list_str constname0;
    list_init_str(&constname0, 0);
    constname0.current_end_point = 0;
    list_deepcopy_str(&constname0, &argv);

    for (i=0; i<=argc - 1; i++) {
        list_append_str(&argv, _lpython_get_argv(i));
    }
    list_deepcopy_str(&argv, &_lpython_return_variable);

    return _lpython_return_variable;
}

void _xx_lcompilers_changed_exit_xx(int32_t error_code)
{
    exit(error_code);
}

void global_initializer()
{
    struct list_str constname01 = _lpython_argv();
    list_deepcopy_str(&constname01, &argv);

}

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
    _xx_lcompilers_changed_exit_xx(2);
}

void _lpython_main_program()
{
    global_initializer();
    test_for();
}

int main(int argc, char* argv[])
{
    _lpython_set_argv(argc, argv);
    _lpython_main_program();
    return 0;
}

bool compare_str(char* a, char* b) {
    return strcmp(a, b) == 0;
}

bool compare_list_str_(struct list_str a, struct list_str b) {
    if (a.current_end_point != b.current_end_point)
        return false;
    for (int i=0; i<a.current_end_point; i++) {
        if (!compare_str(a.data[i], b.data[i]))
            return false;
    }
    return true;
}

void print_str(char* a) {
    printf("'%s'", a);
}

void print_list_str_(struct list_str a) {
    printf("[");
    for (int i=0; i<a.current_end_point; i++) {
        print_str(a.data[i]);
        if (i+1!=a.current_end_point)
            printf(", ");
    }
    printf("]");
}

void list_init_str(struct list_str* x, int32_t capacity) {
    x->capacity = capacity;
    x->current_end_point = 0;
    x->data = (char**) malloc(capacity * sizeof(char*));
}

void list_deepcopy_str(struct list_str* src, struct list_str* dest) {
    dest->capacity = src->capacity;
    dest->current_end_point = src->current_end_point;
    dest->data = (char**) malloc(src->capacity * sizeof(char*));
    memcpy(dest->data, src->data, src->capacity * sizeof(char*));
}

void resize_if_needed_str(struct list_str* x) {
    if (x->capacity == x->current_end_point) {
        x->capacity = 2 * x->capacity + 1;
        x->data = (char**) realloc(x->data, x->capacity * sizeof(char*));
    }
}

void list_append_str(struct list_str* x, char* element) {
    resize_if_needed_str(x);
    x->data[x->current_end_point] = NULL;
    _lfortran_strcpy(&x->data[x->current_end_point], element);
    x->current_end_point += 1;
}

void list_insert_str(struct list_str* x, int pos, char* element) {
    resize_if_needed_str(x);
    int pos_ptr = pos;
    char* tmp_ptr = x->data[pos];
    char* tmp;
    while (x->current_end_point > pos_ptr) {
        tmp = x->data[pos_ptr + 1];
        x->data[pos_ptr + 1] = tmp_ptr;
        tmp_ptr = tmp;
        pos_ptr++;
    }

    x->data[pos] = NULL;
    _lfortran_strcpy(&x->data[pos], element);
    x->current_end_point += 1;
}

int list_find_item_str(struct list_str* x, char* element) {
    int el_pos = 0;
    while (x->current_end_point > el_pos) {
        if (compare_str(x->data[el_pos], element)) return el_pos;
        el_pos++;
    }
    return -1;
}

void list_remove_str(struct list_str* x, char* element) {
    int el_pos = list_find_item_str(x, element);
    while (x->current_end_point > el_pos) {
        int tmp = el_pos + 1;
        x->data[el_pos] = x->data[tmp];
        el_pos = tmp;
    }
    x->current_end_point -= 1;
}

void list_clear_str(struct list_str* x) {
    free(x->data);
    x->capacity = 4;
    x->current_end_point = 0;
    x->data = (char**) malloc(x->capacity * sizeof(char*));
}

struct list_str* list_concat_str(struct list_str* left, struct list_str* right) {
    struct list_str *result = (struct list_str*)malloc(sizeof(struct list_str));
    list_init_str(result, left->current_end_point + right->current_end_point);
    memcpy(result->data, left->data, left->current_end_point * sizeof(char*));
    memcpy(result->data + left->current_end_point, right->data, right->current_end_point * sizeof(char*));
    result->current_end_point = left->current_end_point + right->current_end_point;
    return result;
}

struct list_str* list_section_str(struct list_str* x, int32_t idx1, int32_t idx2, int32_t step, bool i1_present, bool i2_present) {
    int s_len = x->current_end_point;
    if (step == 0) {
        printf("slice step cannot be zero");
        exit(1);
    }
    idx1 = idx1 < 0 ? idx1 + s_len : idx1;
    idx2 = idx2 < 0 ? idx2 + s_len : idx2;
    idx1 = i1_present ? idx1 : (step > 0 ? 0 : s_len-1);
    idx2 = i2_present ? idx2 : (step > 0 ? s_len : -1);
    idx2 = step > 0 ? (idx2 > s_len ? s_len : idx2) : idx2;
    idx1 = step < 0 ? (idx1 >= s_len ? s_len-1 : idx1) : idx1;
    struct list_str *__tmp = (struct list_str*) malloc(sizeof(struct list_str));
    list_init_str(__tmp, 4);
    int s_i = idx1;
    while((step > 0 && s_i >= idx1 && s_i < idx2) ||
        (step < 0 && s_i <= idx1 && s_i > idx2)) {
    list_append_str(__tmp, x->data[s_i]);
    s_i+=step;
    }
    return __tmp;
}


