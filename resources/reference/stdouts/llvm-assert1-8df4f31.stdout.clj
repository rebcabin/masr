; ModuleID = 'LFortran'
source_filename = "LFortran"

define i32 __AT__main(i32 %0, i8** %1) {
.:entry
  call void __AT___lpython_set_argv(i32 %0, i8** %1)
  ret i32 0
}

declare void __AT___lpython_set_argv(i32, i8**)
