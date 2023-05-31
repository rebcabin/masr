; ModuleID = 'LFortran'
source_filename = "LFortran"

__AT__u = global i64 -922337203685477580
__AT__x = global i32 -2147483648
__AT__y = global i16 -32768
__AT__z = global i8 -128
__AT__0 = private unnamed_addr constant [2 x i8] c" \00", align 1
__AT__1 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
__AT__2 = private unnamed_addr constant [7 x i8] c"%lld%s\00", align 1
__AT__3 = private unnamed_addr constant [2 x i8] c" \00", align 1
__AT__4 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
__AT__5 = private unnamed_addr constant [5 x i8] c"%d%s\00", align 1
__AT__6 = private unnamed_addr constant [2 x i8] c" \00", align 1
__AT__7 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
__AT__8 = private unnamed_addr constant [6 x i8] c"%hi%s\00", align 1
__AT__9 = private unnamed_addr constant [2 x i8] c" \00", align 1
__AT__10 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
__AT__11 = private unnamed_addr constant [7 x i8] c"%hhi%s\00", align 1

define void __AT____module__global_symbols__lpython_main_program() {
.:entry
  %0 = load i64, i64* __AT__u, align 4
  call void (i8*, ...) __AT___lfortran_printf(i8* getelementptr inbounds ([7 x i8], [7 x i8]* __AT__2, i32 0, i32 0), i64 %0, i8* getelementptr inbounds ([2 x i8], [2 x i8]* __AT__1, i32 0, i32 0))
  %1 = load i32, i32* __AT__x, align 4
  call void (i8*, ...) __AT___lfortran_printf(i8* getelementptr inbounds ([5 x i8], [5 x i8]* __AT__5, i32 0, i32 0), i32 %1, i8* getelementptr inbounds ([2 x i8], [2 x i8]* __AT__4, i32 0, i32 0))
  %2 = load i16, i16* __AT__y, align 2
  call void (i8*, ...) __AT___lfortran_printf(i8* getelementptr inbounds ([6 x i8], [6 x i8]* __AT__8, i32 0, i32 0), i16 %2, i8* getelementptr inbounds ([2 x i8], [2 x i8]* __AT__7, i32 0, i32 0))
  %3 = load i8, i8* __AT__z, align 1
  call void (i8*, ...) __AT___lfortran_printf(i8* getelementptr inbounds ([7 x i8], [7 x i8]* __AT__11, i32 0, i32 0), i8 %3, i8* getelementptr inbounds ([2 x i8], [2 x i8]* __AT__10, i32 0, i32 0))
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
