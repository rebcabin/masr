; ModuleID = 'LFortran'
source_filename = "LFortran"

__AT__queries = global void* null
__AT__x = global i16* null
__AT__0 = private unnamed_addr constant [2 x i8] c" \00", align 1
__AT__1 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
__AT__2 = private unnamed_addr constant [13 x i8] c"%lld%s%lld%s\00", align 1

define void __AT____module__global_symbols__lpython_main_program() {
.:entry
  %0 = load void*, void** __AT__queries, align 8
  %1 = bitcast void* %0 to i16*
  store i16* %1, i16** __AT__x, align 8
  %2 = load void*, void** __AT__queries, align 8
  %3 = ptrtoint void* %2 to i64
  %4 = load i16*, i16** __AT__x, align 8
  %5 = ptrtoint i16* %4 to i64
  call void (i8*, ...) __AT___lfortran_printf(i8* getelementptr inbounds ([13 x i8], [13 x i8]* __AT__2, i32 0, i32 0), i64 %3, i8* getelementptr inbounds ([2 x i8], [2 x i8]* __AT__0, i32 0, i32 0), i64 %5, i8* getelementptr inbounds ([2 x i8], [2 x i8]* __AT__1, i32 0, i32 0))
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
