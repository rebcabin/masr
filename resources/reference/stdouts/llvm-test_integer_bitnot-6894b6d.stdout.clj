; ModuleID = 'LFortran'
source_filename = "LFortran"

__AT__0 = private unnamed_addr constant [16 x i8] c"AssertionError\0A\00", align 1
__AT__1 = private unnamed_addr constant [16 x i8] c"AssertionError\0A\00", align 1

define void __AT____module__global_symbols__lpython_main_program() {
.:entry
  call void __AT____module__global_symbols_f()
  br label %return

:return                                           ; preds = %.entry
  ret void
}

define void __AT____module__global_symbols_f() {
.:entry
  %i = alloca i32, align 4
  %res = alloca i32, align 4
  store i32 5, i32* %i, align 4
  %0 = load i32, i32* %i, align 4
  %1 = xor i32 %0, -1
  store i32 %1, i32* %res, align 4
  %2 = load i32, i32* %res, align 4
  %3 = icmp eq i32 %2, -6
  br i1 %3, label %then, label %else

:then                                             ; preds = %.entry
  br label %ifcont

:else                                             ; preds = %.entry
  call void (i8*, ...) __AT___lcompilers_print_error(i8* getelementptr inbounds ([16 x i8], [16 x i8]* __AT__0, i32 0, i32 0))
  call void __AT__exit(i32 1)
  br label %ifcont

:ifcont                                           ; preds = %else, %then
  store i32 -235346, i32* %i, align 4
  %4 = load i32, i32* %i, align 4
  %5 = xor i32 %4, -1
  %6 = icmp eq i32 %5, 235345
  br i1 %6, label %then1, label %else2

:then1                                            ; preds = %ifcont
  br label %ifcont3

:else2                                            ; preds = %ifcont
  call void (i8*, ...) __AT___lcompilers_print_error(i8* getelementptr inbounds ([16 x i8], [16 x i8]* __AT__1, i32 0, i32 0))
  call void __AT__exit(i32 1)
  br label %ifcont3

:ifcont3                                          ; preds = %else2, %then1
  br label %return

:return                                           ; preds = %ifcont3
  ret void
}

declare void __AT___lcompilers_print_error(i8*, ...)

declare void __AT__exit(i32)

define i32 __AT__main(i32 %0, i8** %1) {
.:entry
  call void __AT___lpython_set_argv(i32 %0, i8** %1)
  call void __AT____module__global_symbols__lpython_main_program()
  ret i32 0
}

declare void __AT___lpython_set_argv(i32, i8**)
