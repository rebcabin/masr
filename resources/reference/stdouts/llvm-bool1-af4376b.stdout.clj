; ModuleID = 'LFortran'
source_filename = "LFortran"

__AT__0 = private unnamed_addr constant [2 x i8] c" \00", align 1
__AT__1 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
__AT__2 = private unnamed_addr constant [6 x i8] c"False\00", align 1
__AT__3 = private unnamed_addr constant [5 x i8] c"True\00", align 1
__AT__4 = private unnamed_addr constant [5 x i8] c"%s%s\00", align 1
__AT__5 = private unnamed_addr constant [2 x i8] c" \00", align 1
__AT__6 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
__AT__7 = private unnamed_addr constant [6 x i8] c"False\00", align 1
__AT__8 = private unnamed_addr constant [5 x i8] c"True\00", align 1
__AT__9 = private unnamed_addr constant [5 x i8] c"%s%s\00", align 1
__AT__10 = private unnamed_addr constant [2 x i8] c" \00", align 1
__AT__11 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
__AT__12 = private unnamed_addr constant [6 x i8] c"False\00", align 1
__AT__13 = private unnamed_addr constant [5 x i8] c"True\00", align 1
__AT__14 = private unnamed_addr constant [5 x i8] c"%s%s\00", align 1
__AT__15 = private unnamed_addr constant [2 x i8] c" \00", align 1
__AT__16 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
__AT__17 = private unnamed_addr constant [6 x i8] c"False\00", align 1
__AT__18 = private unnamed_addr constant [5 x i8] c"True\00", align 1
__AT__19 = private unnamed_addr constant [5 x i8] c"%s%s\00", align 1

define void __AT____module__global_symbols__lpython_main_program() {
.:entry
  call void __AT____module__global_symbols_test_bool()
  br label %return

:return                                           ; preds = %.entry
  ret void
}

define void __AT____module__global_symbols_test_bool() {
.:entry
  %b = alloca i1, align 1
  call void (i8*, ...) __AT___lfortran_printf(i8* getelementptr inbounds ([5 x i8], [5 x i8]* __AT__4, i32 0, i32 0), i8* getelementptr inbounds ([5 x i8], [5 x i8]* __AT__3, i32 0, i32 0), i8* getelementptr inbounds ([2 x i8], [2 x i8]* __AT__1, i32 0, i32 0))
  call void (i8*, ...) __AT___lfortran_printf(i8* getelementptr inbounds ([5 x i8], [5 x i8]* __AT__9, i32 0, i32 0), i8* getelementptr inbounds ([6 x i8], [6 x i8]* __AT__7, i32 0, i32 0), i8* getelementptr inbounds ([2 x i8], [2 x i8]* __AT__6, i32 0, i32 0))
  store i1 true, i1* %b, align 1
  %0 = load i1, i1* %b, align 1
  %1 = icmp eq i1 %0, false
  %2 = select i1 %1, i8* getelementptr inbounds ([6 x i8], [6 x i8]* __AT__12, i32 0, i32 0), i8* getelementptr inbounds ([5 x i8], [5 x i8]* __AT__13, i32 0, i32 0)
  call void (i8*, ...) __AT___lfortran_printf(i8* getelementptr inbounds ([5 x i8], [5 x i8]* __AT__14, i32 0, i32 0), i8* %2, i8* getelementptr inbounds ([2 x i8], [2 x i8]* __AT__11, i32 0, i32 0))
  store i1 false, i1* %b, align 1
  %3 = load i1, i1* %b, align 1
  %4 = icmp eq i1 %3, false
  %5 = select i1 %4, i8* getelementptr inbounds ([6 x i8], [6 x i8]* __AT__17, i32 0, i32 0), i8* getelementptr inbounds ([5 x i8], [5 x i8]* __AT__18, i32 0, i32 0)
  call void (i8*, ...) __AT___lfortran_printf(i8* getelementptr inbounds ([5 x i8], [5 x i8]* __AT__19, i32 0, i32 0), i8* %5, i8* getelementptr inbounds ([2 x i8], [2 x i8]* __AT__16, i32 0, i32 0))
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
