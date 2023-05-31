; ModuleID = 'LFortran'
source_filename = "LFortran"

__AT__0 = private unnamed_addr constant [2 x i8] c" \00", align 1
__AT__1 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
__AT__2 = private unnamed_addr constant [5 x i8] c"%d%s\00", align 1

define void __AT____module__global_symbols__lpython_main_program() {
.:entry
  call void __AT____module__global_symbols_main0()
  br label %return

:return                                           ; preds = %.entry
  ret void
}

define void __AT____module__global_symbols_main0() {
.:entry
  %x = alloca i32, align 4
  %x2 = alloca i64, align 8
  %y = alloca float, align 4
  %y2 = alloca double, align 8
  store i32 25, i32* %x, align 4
  %0 = load i32, i32* %x, align 4
  call void (i8*, ...) __AT___lfortran_printf(i8* getelementptr inbounds ([5 x i8], [5 x i8]* __AT__2, i32 0, i32 0), i32 %0, i8* getelementptr inbounds ([2 x i8], [2 x i8]* __AT__1, i32 0, i32 0))
  br label %return

:return                                           ; preds = %.entry
  ret void
}

declare void __AT___lfortran_printf(i8*, ...)

define i32 __AT__main(i32 %0, i8** %1) {
.:entry
  call void __AT___lpython_set_argv(i32 %0, i8** %1)
  call void __AT____module__global_symbols__lpython_main_program()
  ret i32 0
}

declare void __AT___lpython_set_argv(i32, i8**)
