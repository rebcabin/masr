; ModuleID = 'LFortran'
source_filename = "LFortran"

__AT__0 = private unnamed_addr constant [16 x i8] c"AssertionError\0A\00", align 1
__AT__1 = private unnamed_addr constant [16 x i8] c"AssertionError\0A\00", align 1

define void __AT____module__global_symbols__lpython_main_program() {
.:entry
  call void __AT____module__global_symbols_main0()
  call void __AT____module__global_symbols__xx_lcompilers_changed_main_xx()
  br label %return

:return                                           ; preds = %.entry
  ret void
}

define i64 __AT____module__global_symbols_fib(i64* %n) {
.:entry
  %call_arg_value1 = alloca i64, align 8
  %call_arg_value = alloca i64, align 8
  %_lpython_return_variable = alloca i64, align 8
  %0 = load i64, i64* %n, align 4
  %1 = icmp slt i64 %0, 2
  br i1 %1, label %then, label %else

:then                                             ; preds = %.entry
  %2 = load i64, i64* %n, align 4
  store i64 %2, i64* %_lpython_return_variable, align 4
  br label %return

:unreachable_after_return                         ; No predecessors!
  br label %ifcont

:else                                             ; preds = %.entry
  %3 = load i64, i64* %n, align 4
  %4 = sub i64 %3, 1
  store i64 %4, i64* %call_arg_value, align 4
  %5 = call i64 __AT____module__global_symbols_fib(i64* %call_arg_value)
  %6 = load i64, i64* %n, align 4
  %7 = sub i64 %6, 2
  store i64 %7, i64* %call_arg_value1, align 4
  %8 = call i64 __AT____module__global_symbols_fib(i64* %call_arg_value1)
  %9 = add i64 %5, %8
  store i64 %9, i64* %_lpython_return_variable, align 4
  br label %return

:unreachable_after_return2                        ; No predecessors!
  br label %ifcont

:ifcont                                           ; preds = %unreachable_after_return2, %unreachable_after_return
  br label %return

:return                                           ; preds = %ifcont, %else, %then
  %10 = load i64, i64* %_lpython_return_variable, align 4
  ret i64 %10
}

define void __AT____module__global_symbols__xx_lcompilers_changed_main_xx() {
.:entry
  %call_arg_value = alloca i64, align 8
  %ans = alloca i64, align 8
  store i64 10, i64* %call_arg_value, align 4
  %0 = call i64 __AT____module__global_symbols_fib(i64* %call_arg_value)
  store i64 %0, i64* %ans, align 4
  %1 = load i64, i64* %ans, align 4
  %2 = icmp eq i64 %1, 55
  br i1 %2, label %then, label %else

:then                                             ; preds = %.entry
  br label %ifcont

:else                                             ; preds = %.entry
  call void (i8*, ...) __AT___lcompilers_print_error(i8* getelementptr inbounds ([16 x i8], [16 x i8]* __AT__0, i32 0, i32 0))
  call void __AT__exit(i32 1)
  br label %ifcont

:ifcont                                           ; preds = %else, %then
  br label %return

:return                                           ; preds = %ifcont
  ret void
}

define void __AT____module__global_symbols_main0() {
.:entry
  %call_arg_value = alloca i64, align 8
  %ans = alloca i64, align 8
  store i64 15, i64* %call_arg_value, align 4
  %0 = call i64 __AT____module__global_symbols_fib(i64* %call_arg_value)
  store i64 %0, i64* %ans, align 4
  %1 = load i64, i64* %ans, align 4
  %2 = icmp eq i64 %1, 610
  br i1 %2, label %then, label %else

:then                                             ; preds = %.entry
  br label %ifcont

:else                                             ; preds = %.entry
  call void (i8*, ...) __AT___lcompilers_print_error(i8* getelementptr inbounds ([16 x i8], [16 x i8]* __AT__1, i32 0, i32 0))
  call void __AT__exit(i32 1)
  br label %ifcont

:ifcont                                           ; preds = %else, %then
  br label %return

:return                                           ; preds = %ifcont
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
